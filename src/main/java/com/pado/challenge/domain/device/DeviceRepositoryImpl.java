package com.pado.challenge.domain.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
class DeviceRepositoryImpl implements DeviceRepository {

    private static final String DISPOSITIVO_NAO_ENCONTRADO = "DISPOSITIVO NÃO ENCONTRADO";

    @Autowired
    private DeviceRepositoryJpa deviceRepositoryJpa;

    @Override
    public Device save(Device newDevice) {
        return deviceRepositoryJpa.save(newDevice);
    }

    @Override
    public List<Device> listar() {
        return deviceRepositoryJpa.findAll();
    }

    @Override
    public Device findById(UUID id) {
        Optional<Device> deviceOptional = deviceRepositoryJpa.findById(id);
        return deviceOptional.orElseThrow(() -> new EntityNotFoundException(DISPOSITIVO_NAO_ENCONTRADO));
    }

}
