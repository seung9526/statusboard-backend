package com.status.board.controller

import com.status.board.dto.RegisterUrlRequest
import com.status.board.dto.StatusResponse
import com.status.board.service.MonitorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/monitor")
class MonitorController(
    private val monitorService: MonitorService
) {

    @PostMapping
    fun register(@RequestBody dto: RegisterUrlRequest): ResponseEntity<String> {
        monitorService.registerUrl(dto)
        return ResponseEntity.ok("URL 등록 완료!")
    }

    @GetMapping
    fun getStatuses(): List<StatusResponse> {
        return monitorService.getLatestStatuses()
    }
}