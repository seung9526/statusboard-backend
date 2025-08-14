package com.status.board.repository

import com.status.board.entity.MonitorResult
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MonitorResultRepository : JpaRepository<MonitorResult, Long> {

    @Query("""
        SELECT r FROM MonitorResult r 
        WHERE r.checkedAt IN (
            SELECT MAX(r2.checkedAt) FROM MonitorResult r2 
            WHERE r2.monitoredUrl.id = r.monitoredUrl.id
        )
    """)
    fun findLatestResults(): List<MonitorResult>
}