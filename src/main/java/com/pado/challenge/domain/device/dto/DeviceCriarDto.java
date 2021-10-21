package com.pado.challenge.domain.device.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@NoArgsConstructor
public class DeviceCriarDto implements Serializable {

    @NotBlank
    private String name;

    @NotBlank
    private String mac;

    @NotBlank
    private String email;

    @NotBlank
    private String latitude;

    @NotBlank
    private String longitude;

}
