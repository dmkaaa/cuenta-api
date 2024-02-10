package com.github.dmkaaa.cuenta.config

import com.fasterxml.jackson.annotation.JsonFormat
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.HandlerMethodValidationException
import java.time.Instant

@ControllerAdvice
class ExceptionHandler {

    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(value = [HttpMessageNotReadableException::class])
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        val message = if (ex.rootCause != null) ex.rootCause!!.message else ex.message
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(Instant.now(), message))
    }

    @ExceptionHandler(value = [HandlerMethodValidationException::class])
    fun handleHandlerMethodValidationException(ex: HandlerMethodValidationException): ResponseEntity<ErrorResponse> {
        val message = ex.allValidationResults[0].resolvableErrors[0].defaultMessage
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(Instant.now(), message))
    }

    @ExceptionHandler(value = [NoSuchElementException::class])
    fun handleNoSuchElementException(ex: NoSuchElementException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(Instant.now(), "Not found"))
    }

    @ExceptionHandler(value = [Exception::class])
    fun handle(ex: Exception): ResponseEntity<ErrorResponse> {
        logger.info("Unhandled error", ex)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse(Instant.now(), "Internal error"))
    }
}

data class ErrorResponse(
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    val timestamp: Instant,
    val message: String?
)