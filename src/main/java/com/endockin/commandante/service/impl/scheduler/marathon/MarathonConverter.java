package com.endockin.commandante.service.impl.scheduler.marathon;

import com.endockin.commandante.model.DockerShip;
import com.endockin.commandante.model.MesosShip;
import com.endockin.commandante.model.Ship;
import com.endockin.commandante.service.impl.scheduler.marathon.dto.AppDto;
import org.springframework.stereotype.Component;

@Component
public class MarathonConverter {

  private enum MarathonContainerTypes {
    DOCKER("DOCKER"), MESOS("MESOS");

    private String name;

    MarathonContainerTypes(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }

  public Ship getShip(AppDto app) {
    Ship ship = null;

    String containerType = app.getContainer().getType();
    if (MarathonContainerTypes.DOCKER.getName().equals(containerType)) {
      ship = new DockerShip(app.getId());
    }
    else if (MarathonContainerTypes.MESOS.getName().equals(containerType)){
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
