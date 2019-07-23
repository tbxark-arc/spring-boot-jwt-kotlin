package com.tbxark.jwt.security.core.controller

import com.tbxark.jwt.security.core.model.AuthenticationException
import com.tbxark.jwt.security.model.ResponseWrapper
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class AuthenticationExceptionController {

    @ExceptionHandler(AccessDeniedException::class)
    @ResponseBody
    fun handle(e: AccessDeniedException): ResponseWrapper<Any> {
        val code = 403
        val message = e.message ?: "Access Denied"
        return ResponseWrapper.failure(code, message)
    }

    @ExceptionHandler(AuthenticationException::class)
    @ResponseBody
    fun handleAuthenticationException(e: AuthenticationException): ResponseWrapper<Any> {
        return ResponseWrapper.failure(HttpStatus.UNAUTHORIZED.value(), e.message ?: "")
    }
}