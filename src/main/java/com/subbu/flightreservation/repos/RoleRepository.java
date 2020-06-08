package com.subbu.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subbu.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
