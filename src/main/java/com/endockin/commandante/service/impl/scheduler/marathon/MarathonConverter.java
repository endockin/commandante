package com.endockin.commandante.service.impl.scheduler.marathon;

import com.endockin.commandante.model.DockerShip;
import com.endockin.commandante.model.MesosShip;
import com.endockin.commandante.model.Ship;
import com.endockin.commandante.service.impl.scheduler.marathon.dto.AppDto;
import org.springframework.stereotype.Component;

@Component
public class MarathonConverter {

  public Ship getShip(AppDto app) {
    Ship ship = null;

    String containerType = app.getContainer().getType();
    if (DockerShip.SHIP_TYPE.equals(containerType)) {
      ship = new DockerShip(app.getId());
    }
    else if (DockerShip.SHIP_TYPE.equals(containerType)){
      ship = new MesosShip(app.getId());
    }

    if (ship!=null) {
      ship.setCpu(app.getCpus());
      ship.setMemory(app.getMem());
      ship.setInstanceNumber(app.getInstances());
    }

    return ship;
  }

  public AppDto getAppDto(Ship ship) {
    AppDto app = new AppDto();
    app.setId(ship.getId());

    app.setCpus(ship.getCpu());
    app.setInstances(ship.getInstanceNumber());
    app.setMem(ship.getMemory());
    return app;
  }
}
