package com.almacen.logger.repository;

import com.almacen.logger.LoggerMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoggerMessageRepository extends JpaRepository<LoggerMessage, Integer> {

    @Query("SELECT lm FROM LoggerMessage lm WHERE lm.user.id = :userId ORDER BY lm.date DESC")
    List<LoggerMessage> findAllByUserId(@Param("userId") Integer id);

    @Query("SELECT lm FROM LoggerMessage lm ORDER BY date DESC")
    List<LoggerMessage> findAll();
}
