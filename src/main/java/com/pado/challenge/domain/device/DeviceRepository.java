package com.pado.challenge.domain.device;

import java.util.List;
import java.util.UUID;

public interface DeviceRepository {

    Device save(Device newDevice);

    List<Device> listar();

    Device findById(UUID id);

}
