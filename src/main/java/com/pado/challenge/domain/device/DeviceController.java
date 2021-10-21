package com.pado.challenge.domain.device;

import com.pado.challenge.connections.RabbitMQConnection;
import com.pado.challenge.domain.device.dto.DeviceCriadoDto;
import com.pado.challenge.domain.device.dto.DeviceCriarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pado")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private RabbitmqService rabbitmqService;

    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    public DeviceCriadoDto registrar(@Valid @RequestBody DeviceCriarDto deviceCriarDto) {
        Device newDevice = deviceService.registrar(deviceCriarDto);
        return new DeviceCriadoDto(newDevice);
    }

    @GetMapping("/listar")
    public List<Device> listar() {
        return deviceService.listar();
    }

    @GetMapping("/listar/{deviceId}")
    public Device listarById(@PathVariable("deviceId") UUID id) {
        return deviceService.findById(id);
    }

    @PostMapping("/registrar/async")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void registrarLista(@RequestBody List<DeviceCriarDto> deviceCriarDtoLista) {
        rabbitmqService.enviaMensagem(RabbitMQConnection.FILA_DISPOSITIVO, deviceCriarDtoLista);
    }

}
