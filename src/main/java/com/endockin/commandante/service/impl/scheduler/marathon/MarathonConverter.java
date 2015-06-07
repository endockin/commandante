package com.endockin.commandante.service.impl.scheduler.marathon;

import com.endockin.commandante.model.DockerShip;
import com.endockin.commandante.model.MesosShip;
import com.endockin.commandante.model.Ship;
import com.endockin.commandante.service.impl.scheduler.marathon.dto.MarathonApp;
import org.springframework.stereotype.Component;

@Component
public class MarathonConverter {

    public Ship getShip(MarathonApp marathonApp) {
        Ship ship = null;

        String containerType = marathonApp.getContainer().getType();
        if (DockerShip.SHIP_TYPE.equals(containerType)) {
            ship = new DockerShip(marathonApp.getId());
        } else if (DockerShip.SHIP_TYPE.equals(containerType)) {
            ship = new MesosShip(marathonApp.getId());
        }

        if (ship != null) {
            ship.setCpu(marathonApp.getCpus());
            ship.setMemory(marathonApp.getMem());
            ship.setInstanceNumber(marathonApp.getInstances());
        }

        return ship;
    }

    public MarathonApp getMarathonApp(Ship ship) {
        MarathonApp app = new MarathonApp();
        app.setId(ship.getId());

        app.setCpus(ship.getCpu());
        app.setInstances(ship.getInstanceNumber());
        app.setMem(ship.getMemory());
        return app;
    }
}
