package com.pado.challenge.domain.device;

import com.pado.challenge.domain.device.dto.DeviceCriarDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "tb_device")
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Device {

    @Id
    @EqualsAndHashCode.Include
    private UUID deviceId;

    private String name;
    private String mac;
    private String email;
    private String latitude;
    private String longitude;

    public Device() {
        this.deviceId = UUID.randomUUID();
    }

    public Device(DeviceCriarDto deviceCriarDto) {
        this();
        this.name = deviceCriarDto.getName();
        this.mac = deviceCriarDto.getMac();
        this.email = deviceCriarDto.getEmail();
        this.latitude = deviceCriarDto.getLatitude();
        this.longitude = deviceCriarDto.getLongitude();
    }

    public Device(String name, String mac, String email, String latitude, String longitude) {
        this();
        this.name = name;
        this.mac = mac;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
