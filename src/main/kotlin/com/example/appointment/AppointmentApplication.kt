package com.example.appointment

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AppointmentApplication

fun main(args: Array<String>) {
    runApplication<AppointmentApplication>(*args)
}
