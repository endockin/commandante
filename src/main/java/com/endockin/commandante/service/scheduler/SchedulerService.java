package com.endockin.commandante.service.scheduler;

import com.endockin.commandante.model.Ship;

import java.util.List;

/**
 *
 * @author mate
 */
public interface SchedulerService {

    void schedule(Ship ship) throws SchedulerException;

    List<Ship> getAll();
}
