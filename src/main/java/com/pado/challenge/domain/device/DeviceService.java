package com.pado.challenge.domain.device;

import com.pado.challenge.domain.device.dto.DeviceCriarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public Device registrar(DeviceCriarDto deviceCriarDto) {
        Device newDevice = new Device(deviceCriarDto);
        return deviceRepository.save(newDevice);
    }

    public List<Device> listar() {
        return deviceRepository.listar();
    }

    public Device findById(UUID id) {
        return deviceRepository.findById(id);
    }

}
