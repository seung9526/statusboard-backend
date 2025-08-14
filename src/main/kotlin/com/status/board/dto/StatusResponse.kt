package com.status.board.dto

import java.time.LocalDateTime

data class StatusResponse(
    val url: String,
    val name: String,
    val status: String,
    val responseTimeMs: Long,
    val checkedAt: LocalDateTime
)