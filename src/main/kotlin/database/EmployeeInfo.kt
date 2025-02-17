package database

import kotlinx.datetime.LocalDateTime

data class EmployeeInfo(
    val fullName: String,
    val birthDate: LocalDateTime,
    val phone: String,
    val room: String,
    val email: String,
    val position: String
)
