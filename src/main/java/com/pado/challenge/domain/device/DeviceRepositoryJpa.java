package com.pado.challenge.domain.device;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface DeviceRepositoryJpa extends JpaRepository<Device, UUID> {

}
