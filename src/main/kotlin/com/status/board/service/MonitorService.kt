package com.status.board.service

import com.status.board.dto.RegisterUrlRequest
import com.status.board.dto.StatusResponse
import com.status.board.entity.MonitoredUrl
import com.status.board.repository.MonitorResultRepository
import com.status.board.repository.MonitoredUrlRepository
import org.springframework.stereotype.Service

@Service
class MonitorService(
    private val urlRepo: MonitoredUrlRepository,
    private val resultRepo: MonitorResultRepository
) {

    fun registerUrl(dto: RegisterUrlRequest) {
        val entity = MonitoredUrl(
            url = dto.url,
            name = dto.name
        )
        urlRepo.save(entity)
    }

    fun getLatestStatuses(): List<StatusResponse> {
        return resultRepo.findLatestResults().map {
            StatusResponse(
                url = it.monitoredUrl.url,
                name = it.monitoredUrl.name,
                status = if (it.status == "UP") "✅" else "❌",
                responseTimeMs = it.responseTimeMs,
                checkedAt = it.checkedAt
            )
        }
    }
}