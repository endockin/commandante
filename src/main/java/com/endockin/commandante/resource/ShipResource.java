package com.endockin.commandante.resource;

import com.endockin.commandante.model.DockerShip;
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
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/ships")
public class ShipResource {

    private static final Logger LOG = LoggerFactory.getLogger(ShipResource.class);

    @Autowired
    private SchedulerService schedulerService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ship>> getAll() {
        try {
            List<Ship> ships = schedulerService.findAll();

            return new ResponseEntity<>(ships, HttpStatus.OK);
        } catch (SchedulerServiceException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ship> get(@PathVariable String id) {
        try {
            Ship ship = schedulerService.find(id);
            return new ResponseEntity<>(ship, HttpStatus.OK);
        } catch (SchedulerServiceException ex) {
            if (SchedulerServiceException.Type.NOT_FOUND.equals(ex.getType())) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ship> postDockerShip(@RequestBody DockerShip ship) {
        //TODO create custom deserializer for ship type do not use explicit docker ship
        try {
            return new ResponseEntity<>(schedulerService.schedule(ship), HttpStatus.OK);
        } catch (SchedulerServiceException ex) {
            if (ex.getType() == SchedulerServiceException.Type.ALREADY_EXISTS) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
