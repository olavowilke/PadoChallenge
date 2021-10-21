package com.pado.challenge.domain.device.dto;

import com.pado.challenge.domain.device.Device;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class DeviceCriadoDto {

    private UUID deviceId;
    private String mac;

    public DeviceCriadoDto(Device device) {
        this.deviceId = device.getDeviceId();
        this.mac = device.getMac();
    }

}
