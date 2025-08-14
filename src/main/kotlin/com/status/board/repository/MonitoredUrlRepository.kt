package com.status.board.repository

import com.status.board.entity.MonitoredUrl
import org.springframework.data.jpa.repository.JpaRepository

interface MonitoredUrlRepository : JpaRepository<MonitoredUrl, Long>

