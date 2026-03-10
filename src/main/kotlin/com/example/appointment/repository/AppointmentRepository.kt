package com.example.appointment.repository

import com.example.appointment.model.Appointment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AppointmentRepository : JpaRepository<Appointment, Long> {
    fun findByClientNameContainingIgnoreCaseOrClientIdContainingIgnoreCase(nameQuery: String, idQuery: String): List<Appointment>
}
