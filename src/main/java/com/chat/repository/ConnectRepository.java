package com.chat.repository;

import com.chat.model.Chat;
import com.chat.model.Connect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectRepository extends JpaRepository<Connect, Integer> {}
