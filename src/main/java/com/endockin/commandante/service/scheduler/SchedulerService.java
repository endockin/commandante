package com.endockin.commandante.service.scheduler;

import com.endockin.commandante.model.Fleet;

import java.util.List;

public interface SchedulerService {

    Fleet schedule(Fleet fleet) throws SchedulerServiceException;

    List<Fleet> findAll() throws SchedulerServiceException;

    Fleet find(String id) throws SchedulerServiceException;
}
