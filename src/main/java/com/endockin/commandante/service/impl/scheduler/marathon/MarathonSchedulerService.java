package com.endockin.commandante.service.impl.scheduler.marathon;

import com.endockin.commandante.model.Ship;
import com.endockin.commandante.service.impl.scheduler.marathon.dto.AppDto;
import com.endockin.commandante.service.impl.scheduler.marathon.dto.AppsDto;
import com.endockin.commandante.service.scheduler.SchedulerServiceException;
import com.endockin.commandante.service.scheduler.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

@Service
public class MarathonSchedulerService implements SchedulerService {

    //TODO: determine URL based on service discovery instead of hardcoding.
    private static final String MARATHON_ROOT = "http://endockin-master1:8080";
    private static final MarathonVersion MARATHON_VERSION = MarathonVersion.V2;
    private static final Logger LOG = LoggerFactory.getLogger(MarathonSchedulerService.class);

    @Autowired
    private MarathonConverter converter;

    @Override
    public void schedule(Ship ship) throws SchedulerServiceException {
        RestTemplate restTemplate = new RestTemplate();
    }

    @Override
    public List<Ship> getAll() throws SchedulerServiceException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<AppsDto> result = restTemplate.getForEntity(getURI(MARATHON_ROOT, MarathonResource.APPS),
                    AppsDto.class);

            return result.getBody().getMarathonApps().stream().map(converter::getShip).
                    collect(Collectors.toCollection(() -> new LinkedList<>()));
        } catch (RestClientException rce) {
            LOG.warn("Trouble getting all ships from Marathon", rce);
            throw new SchedulerServiceException(rce.getMessage());
        }
    }

    @Override
    public Ship get(String id) throws SchedulerServiceException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<AppDto> result = restTemplate.getForEntity(getURI(MARATHON_ROOT, MarathonResource.APPS) + "/" + id,
                    AppDto.class);

            return converter.getShip(result.getBody().getMarathonApp());
        } catch (HttpStatusCodeException hsce) {
            if (hsce.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            }

            LOG.warn("Trouble getting ship with id [" + id + "] from Marathon", hsce);
            throw new SchedulerServiceException(hsce.getMessage());
        } catch (RestClientException rce) {
            LOG.warn("Trouble getting ship with id [" + id + "] from Marathon", rce);
            throw new SchedulerServiceException(rce.getMessage());
        }
    }

    private enum MarathonResource {

        APPS("/apps"), GROUPS("/groups"), TASKS("/tasks"), DEPLOYMENTS("/deployments"),
        EVENT_SUBSCRIPTIONS("/eventSubscriptions"), QUEUE("/queue"), INFO("/info"), LEADER("/leader"),
        PING("/ping"), LOGGING("/logging"), HELP("/help"), METRICS("/metrics");

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
