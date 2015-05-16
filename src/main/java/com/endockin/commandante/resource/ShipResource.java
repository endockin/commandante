package com.endockin.commandante.resource;

import com.endockin.commandante.model.Ship;
import com.endockin.commandante.service.scheduler.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mate
 */
@RestController
@RequestMapping("/api")
public class ShipResource {

  private static final String RESOURCE_SHIPS = "/ships";
  private static final Logger LOG = LoggerFactory.getLogger(ShipResource.class);

  @Autowired
  private SchedulerService schedulerService;

  @RequestMapping(value = RESOURCE_SHIPS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Ship> get() {
    List<Ship> ships = schedulerService.getAll();
    LOG.debug(ships.toString());
    return ships;
  }
}
