package com.endockin.commandante.resource;

import com.endockin.commandante.model.Ship;
import com.endockin.commandante.service.scheduler.SchedulerService;
import com.endockin.commandante.service.scheduler.SchedulerServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/ships")
public class ShipResource {

    private static final Logger LOG = LoggerFactory.getLogger(ShipResource.class);

    @Autowired
    private SchedulerService schedulerService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ship>> getAll() {
        try {
            List<Ship> ships = schedulerService.getAll();

            return new ResponseEntity<>(ships, HttpStatus.OK);
        } catch (SchedulerServiceException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ship> get(@PathVariable String id) {
        try {
            Ship ship = schedulerService.get(id);

            if (ship == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(ship, HttpStatus.OK);
        } catch (SchedulerServiceException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
