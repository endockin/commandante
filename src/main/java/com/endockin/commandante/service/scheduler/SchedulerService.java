package com.endockin.commandante.service.scheduler;

import com.endockin.commandante.model.Ship;

import java.util.List;

/**
 *
 * @author mate
 */
public interface SchedulerService {

    Ship schedule(Ship ship) throws SchedulerServiceException;

    List<Ship> findAll() throws SchedulerServiceException;

    Ship find(String id) throws SchedulerServiceException;
}
