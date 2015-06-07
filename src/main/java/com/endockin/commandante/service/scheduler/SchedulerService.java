package com.endockin.commandante.service.scheduler;

import com.endockin.commandante.model.Ship;

import java.util.List;

/**
 *
 * @author mate
 */
public interface SchedulerService {

    void schedule(Ship ship) throws SchedulerServiceException;

    List<Ship> getAll() throws SchedulerServiceException;

    Ship get(String id) throws SchedulerServiceException;
}
