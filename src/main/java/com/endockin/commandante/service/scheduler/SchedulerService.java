package com.endockin.commandante.service.scheduler;

import java.util.List;

import com.endockin.commandante.model.Fleet;

public interface SchedulerService {

	Fleet schedule(Fleet fleet) throws SchedulerServiceException;

	List<Fleet> findAll() throws SchedulerServiceException;

	Fleet find(String id) throws SchedulerServiceException;

	void delete(String id) throws SchedulerServiceException;
}
