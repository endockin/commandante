package com.endockin.commandante.service.impl.scheduler.marathon;

import com.endockin.commandante.model.DockerFleet;
import com.endockin.commandante.model.MesosFleet;
import com.endockin.commandante.model.Fleet;
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

    public Fleet getFleet(MarathonApp marathonApp) {
        Fleet fleet = null;

        String containerType = marathonApp.getContainer().getType();
        if (DockerFleet.FLEET_TYPE.equals(containerType)) {
            ContainerDto containerDto = marathonApp.getContainer();

            DockerFleet dockerFleet = new DockerFleet();
            dockerFleet.setImage(containerDto.getDockerContainerInfoDto().getImage());

            fleet = dockerFleet;
        } else {
            fleet = new MesosFleet();
        }

        if (marathonApp.getTasks() != null) {
            List<Ship> ships = new ArrayList<>();
            marathonApp.getTasks().stream().map((task) -> {
                Ship ship = new Ship();
                ship.setAppId(task.getAppId());
                ship.setHost(task.getHost());
                ship.setId(task.getId());
                ship.setPorts(task.getPorts());
                ship.setStagedAt(task.getStagedAt());
                ship.setStartedAt(task.getStartedAt());
                ship.setVersion(task.getVersion());
                return ship;
            }).forEach((ship) -> {
                ships.add(ship);
            });
            fleet.setShips(ships);
        }

        fleet.setId(marathonApp.getId());
        fleet.setCommand(marathonApp.getCmd());
        fleet.setCpu(marathonApp.getCpus());
        fleet.setMemory(marathonApp.getMem());
        fleet.setInstanceNumber(marathonApp.getInstances());

        return fleet;
    }

    public MarathonApp getMarathonApp(Fleet ship) {
        MarathonApp app = new MarathonApp();
        app.setId(ship.getId());
        app.setCmd(ship.getCommand());
        app.setCpus(ship.getCpu());
        app.setInstances(ship.getInstanceNumber());
        app.setMem(ship.getMemory());

        if (DockerFleet.FLEET_TYPE.equals(ship.getType())) {
            DockerFleet dockerFleet = (DockerFleet) ship;
            ContainerDto container = new ContainerDto();

            container.setType(DockerFleet.FLEET_TYPE);
            DockerContainerInfoDto dockerInfo = new DockerContainerInfoDto();
            dockerInfo.setImage(dockerFleet.getImage());

            List<PortMapping> portMappings = new ArrayList<>();
            dockerFleet.getPorts().stream().map((portNumber) -> {
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
