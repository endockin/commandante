package com.endockin.commandante.service.impl.scheduler.marathon;

import com.endockin.commandante.model.DockerShip;
import com.endockin.commandante.model.MesosShip;
import com.endockin.commandante.model.Ship;
import com.endockin.commandante.service.impl.scheduler.marathon.dto.ContainerDto;
import com.endockin.commandante.service.impl.scheduler.marathon.dto.DockerContainerInfoDto;
import com.endockin.commandante.service.impl.scheduler.marathon.dto.DockerContainerInfoDto.PortMapping;
import com.endockin.commandante.service.impl.scheduler.marathon.dto.internal.MarathonApp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MarathonConverter {

    public Ship getShip(MarathonApp marathonApp) {
        Ship ship = null;

        String containerType = marathonApp.getContainer().getType();
        if (DockerShip.SHIP_TYPE.equals(containerType)) {
            ContainerDto containerDto = marathonApp.getContainer();

            DockerShip dockerShip = new DockerShip();
            dockerShip.setImage(containerDto.getDockerContainerInfoDto().getImage());

            ship = dockerShip;
        } else if (MesosShip.SHIP_TYPE.equals(containerType)) {
            ship = new MesosShip();
        }

        if (ship != null) {
            ship.setId(marathonApp.getId());
            ship.setCommand(marathonApp.getCmd());
            ship.setCpu(marathonApp.getCpus());
            ship.setMemory(marathonApp.getMem());
            ship.setInstanceNumber(marathonApp.getInstances());
        }

        return ship;
    }

    public MarathonApp getMarathonApp(Ship ship) {
        MarathonApp app = new MarathonApp();
        app.setId(ship.getId());
        app.setCmd(ship.getCommand());
        app.setCpus(ship.getCpu());
        app.setInstances(ship.getInstanceNumber());
        app.setMem(ship.getMemory());

        if (DockerShip.SHIP_TYPE.equals(ship.getType())) {
            DockerShip dockerShip = (DockerShip) ship;
            ContainerDto container = new ContainerDto();

            container.setType(DockerShip.SHIP_TYPE);
            DockerContainerInfoDto dockerInfo = new DockerContainerInfoDto();
            dockerInfo.setImage(dockerShip.getImage());

            List<PortMapping> portMappings = new ArrayList<>();
            dockerShip.getPorts().stream().map((portNumber) -> {
                PortMapping portMapping = new PortMapping();
                portMapping.setContainerPort(portNumber);
                return portMapping;
            }).forEach((portMapping) -> {
                portMappings.add(portMapping);
            });
            dockerInfo.setPortMappings(portMappings);

            container.setDockerContainerInfoDto(dockerInfo);
            app.setContainer(container);
        }

        return app;
    }
}
