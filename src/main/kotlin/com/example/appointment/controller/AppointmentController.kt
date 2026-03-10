package com.example.appointment.controller

import com.example.appointment.model.Appointment
import com.example.appointment.service.AppointmentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/appointments")
class AppointmentController(private val service: AppointmentService) {

    @GetMapping
    fun getAll(): List<Appointment> = service.getAllAppointments()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Appointment> {
        return service.getAppointmentById(id)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun create(@RequestBody appointment: Appointment): Appointment = service.createAppointment(appointment)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        service.deleteAppointment(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/search")
    fun search(@RequestParam query: String): List<Appointment> = service.searchAppointments(query)
}
