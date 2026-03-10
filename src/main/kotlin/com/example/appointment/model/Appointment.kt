package com.example.appointment.model

import jakarta.persistence.*

@Entity
@Table(name = "appointments")
data class Appointment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var clientName: String,

    @Column(nullable = false)
    var clientId: String,

    @Column(nullable = false)
    var serviceType: String,

    @Column(nullable = false)
    var email: String,

    @Column(nullable = false)
    var date: String,

    @Column(nullable = false)
    var time: String,

    @Column(nullable = false)
    var status: String = "Confirmed"
)
