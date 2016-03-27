package com.almacen.logger.repository;

import com.almacen.logger.LoggerMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface LoggerMessageRepository extends JpaRepository<LoggerMessage, Integer> {

}
