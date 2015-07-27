package com.endockin.commandante.service.impl.scheduler.marathon;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.endockin.commandante.model.Fleet;
import com.endockin.commandante.service.impl.scheduler.marathon.dto.AppDto;
import com.endockin.commandante.service.impl.scheduler.marathon.dto.AppsDto;
import com.endockin.commandante.service.impl.scheduler.marathon.dto.internal.MarathonApp;
import com.endockin.commandante.service.scheduler.SchedulerService;
import com.endockin.commandante.service.scheduler.SchedulerServiceException;

@Service
public class MarathonSchedulerService implements SchedulerService {

	// TODO: determine URL based on service discovery instead of hardcoding.
	private static final String MARATHON_ROOT = "http://rocj-inolab-d01:8080";
	private static final MarathonVersion MARATHON_VERSION = MarathonVersion.V2;
	private static final Logger LOG = LoggerFactory
			.getLogger(MarathonSchedulerService.class);

	@Autowired
	private MarathonConverter converter;

	@Override
	public Fleet schedule(Fleet ship) throws SchedulerServiceException {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<MarathonApp> result = restTemplate.postForEntity(
					getURI(MARATHON_ROOT, MarathonResource.APPS),
					converter.getMarathonApp(ship), MarathonApp.class);

			return converter.getFleet(result.getBody());
		} catch (RestClientException rce) {
			handleRestClientException(rce);
			return null;
		}

	}

	@Override
	public List<Fleet> findAll() throws SchedulerServiceException {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<AppsDto> result = restTemplate
					.getForEntity(getURI(MARATHON_ROOT, MarathonResource.APPS),
							AppsDto.class);

			return result.getBody().getMarathonApps().stream()
					.map(converter::getFleet)
					.collect(Collectors.toCollection(() -> new LinkedList<>()));
		} catch (RestClientException rce) {
			handleRestClientException(rce);
			return null;
		}

	}

	@Override
	public Fleet find(String id) throws SchedulerServiceException {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<AppDto> result = restTemplate.getForEntity(
					getURI(MARATHON_ROOT, MarathonResource.APPS) + "/" + id,
					AppDto.class);

			return converter.getFleet(result.getBody().getMarathonApp());
		} catch (RestClientException rce) {
			handleRestClientException(rce);
			return null;
		}
	}

	@Override
	public void delete(String id) throws SchedulerServiceException {
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.delete(getURI(MARATHON_ROOT, MarathonResource.APPS)
					+ "/" + id, AppDto.class);
		} catch (RestClientException rce) {
			handleRestClientException(rce);
		}
	}

	private static void handleRestClientException(RestClientException e)
			throws SchedulerServiceException {
		if (e instanceof HttpStatusCodeException) {
			HttpStatusCodeException hsce = (HttpStatusCodeException) e;
			LOG.info("Marathon request failed with HttpStatusCodeException. \n"
					+ hsce.getResponseBodyAsString());

			if (hsce.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new SchedulerServiceException("Ship does not exist.",
						SchedulerServiceException.Type.NOT_FOUND);
			} else if (hsce.getStatusCode() == HttpStatus.CONFLICT) {
				throw new SchedulerServiceException("Ship already exists.",
						SchedulerServiceException.Type.ALREADY_EXISTS);
			}
		}

		LOG.warn("Unhandled RestClientException from Marathon.", e);
		throw new SchedulerServiceException(e.getMessage(),
				SchedulerServiceException.Type.OTHER);
	}

	private enum MarathonResource {

		APPS("/apps"), GROUPS("/groups"), TASKS("/tasks"), DEPLOYMENTS(
				"/deployments"), EVENT_SUBSCRIPTIONS("/eventSubscriptions"), QUEUE(
				"/queue"), INFO("/info"), LEADER("/leader"), PING("/ping"), LOGGING(
				"/logging"), HELP("/help"), METRICS("/metrics");

		private final String fragment;

		MarathonResource(String fragment) {
			this.fragment = fragment;
		}

		public String getFragment() {
			return fragment;
		}
	}

	private enum MarathonVersion {

		V1("/v1"), V2("/v2");

		private final String fragment;

		MarathonVersion(String fragment) {
			this.fragment = fragment;
		}

		public String getFragment() {
			return fragment;
		}

	}

	private static String getURI(String base, MarathonResource resource) {
		return base + MARATHON_VERSION.getFragment() + resource.getFragment();
	}

}
