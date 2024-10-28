package com.bytebard.controllers

import com.bytebard.dtos.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class BaseController {

    @GetMapping("ok")
    fun ok(): ResponseEntity<ApiResponse<String>> {
        return ResponseEntity.ok().body(ApiResponse("Service is up and running"))
    }

    @GetMapping("/")
    fun root(): ResponseEntity<ApiResponse<String>> {
        return ResponseEntity.ok().body(ApiResponse("Root service..."))
    }
}