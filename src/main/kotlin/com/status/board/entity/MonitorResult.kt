package com.status.board.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "monitor_result")
data class MonitorResult(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monitored_url_id")
    val monitoredUrl: MonitoredUrl,

    @Column(nullable = false)
    val status: String, // "UP" or "DOWN"

    @Column(nullable = false)
    val responseTimeMs: Long,

    @Column(nullable = false)
    val checkedAt: LocalDateTime = LocalDateTime.now()
)