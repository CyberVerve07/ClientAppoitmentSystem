package com.example.appointment.service

import com.example.appointment.model.Appointment
import com.example.appointment.repository.AppointmentRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AppointmentService(private val repository: AppointmentRepository) {

    fun getAllAppointments(): List<Appointment> = repository.findAll()

    fun getAppointmentById(id: Long): Optional<Appointment> = repository.findById(id)

    fun createAppointment(appointment: Appointment): Appointment = repository.save(appointment)

    fun deleteAppointment(id: Long) = repository.deleteById(id)

    fun searchAppointments(query: String): List<Appointment> {
        return repository.findByClientNameContainingIgnoreCaseOrClientIdContainingIgnoreCase(query, query)
    }
}
