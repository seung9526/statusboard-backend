package com.status.board.scheduler

import com.status.board.entity.MonitorResult
import com.status.board.repository.MonitorResultRepository
import com.status.board.repository.MonitoredUrlRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.net.HttpURLConnection
import java.net.URL

@Component
class PingScheduler(
    private val urlRepository: MonitoredUrlRepository,
    private val resultRepository: MonitorResultRepository,
) {

    @Scheduled(fixedRate = 1000000) // 5분
    fun checkAllUrls() {
        val urls = urlRepository.findAll()
        for (url in urls) {
            val (status, time) = pingUrl(url.url)
            resultRepository.save(
                MonitorResult(
                    monitoredUrl = url,
                    status = status,
                    responseTimeMs = time
                )
            )
        }
    }

    fun pingUrl(url: String): Pair<String, Long> {
        val start = System.currentTimeMillis()
        return try {
            val conn = URL(url).openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.setRequestProperty("User-Agent", "Mozilla/5.0") // 브라우저처럼 보이게!
            conn.connectTimeout = 3000
            conn.readTimeout = 3000
            conn.connect()

            val code = conn.responseCode
            val status = if (code in 200..399) "UP" else "DOWN"
            Pair(status, System.currentTimeMillis() - start)
        } catch (e: Exception) {
            Pair("DOWN", 0)
        }
    }
}