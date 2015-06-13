package com.endockin.commandante.resource;

import com.endockin.commandante.model.DockerFleet;
import com.endockin.commandante.model.Fleet;
import com.endockin.commandante.service.scheduler.SchedulerService;
import com.endockin.commandante.service.scheduler.SchedulerServiceException;
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
@RequestMapping("/api/fleet")
public class FleetResource {

    @Autowired
    private SchedulerService schedulerService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Fleet>> getAll() {
        try {
            List<Fleet> fleets = schedulerService.findAll();

            return new ResponseEntity<>(fleets, HttpStatus.OK);
        } catch (SchedulerServiceException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Fleet> get(@PathVariable String id) {
        try {
            Fleet fleet = schedulerService.find(id);
            return new ResponseEntity<>(fleet, HttpStatus.OK);
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
    public ResponseEntity<Fleet> postDockerShip(@RequestBody DockerFleet fleet) {
        //TODO create custom deserializer for ship type do not use explicit docker ship
        try {
            return new ResponseEntity<>(schedulerService.schedule(fleet), HttpStatus.OK);
        } catch (SchedulerServiceException ex) {
            if (ex.getType() == SchedulerServiceException.Type.ALREADY_EXISTS) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
