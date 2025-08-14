package com.status.board.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "monitored_url")
data class MonitoredUrl(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val url: String,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)