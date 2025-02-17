package database

import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object Department: Table() {
    val departmentId: Column<Int> = integer("department_id").autoIncrement()
    val name: Column<String> = varchar("name", 255)
    override val primaryKey = PrimaryKey(departmentId)
}

object Division: Table() {
    val divisionId: Column<Int> = integer("division_id").autoIncrement()
    val name: Column<String> = varchar("name", 255)
    override val primaryKey = PrimaryKey(divisionId)
    val departmentId: Column<Int> = integer("department_id") references Department.departmentId
}

object Unit: Table() {
    val unitId: Column<Int> = integer("unit_id").autoIncrement()
    override val primaryKey = PrimaryKey(unitId)
    val name: Column<String> = varchar("name", 255)
    val divisionId: Column<Int> = integer("division_id") references Division.divisionId
}

object EventType: Table() {
    val eventTypeId: Column<Int> = integer("event_type_id").autoIncrement()
    override val primaryKey = PrimaryKey(eventTypeId)
    val name: Column<String> = varchar("name", 255)
}

object EventStatus: Table() {
    val eventStatusId: Column<Int> = integer("event_status_id").autoIncrement()
    override val primaryKey = PrimaryKey(eventStatusId)
    val name: Column<String> = varchar("name", 255)
}

object Event: Table() {
    val eventId: Column<Int> = integer("event_id").autoIncrement()
    override val primaryKey = PrimaryKey(eventId)
    val name: Column<String> = varchar("name", 255)
    val eventTypeId: Column<Int> = integer("event_type_id") references EventType.eventTypeId
    val eventStatusId: Column<Int> = integer("event_status_id") references EventStatus.eventStatusId
    val date: Column<LocalDateTime> = datetime("date")
    val comment: Column<String> = varchar("comment", 1000)
}

object TrainingMaterial: Table() {
    val trainingMaterialId: Column<Int> = integer("training_material_id").autoIncrement()
    override val primaryKey = PrimaryKey(trainingMaterialId)
    val confirmDate: Column<LocalDateTime> = datetime("confirm_date")
    val changedDate: Column<LocalDateTime> = datetime("changed_date")
    val eventId: Column<Int> = integer("event_id") references Event.eventId
    val status: Column<Boolean> = bool("status")
    val type: Column<String> = varchar("type", 255)
    val field: Column<String> = varchar("field", 255)
    val author: Column<String> = varchar("author", 255)
}

object EmployeeEvents: Table() {
    val eventId: Column<Int> = integer("event_id") references Event.eventId
    val employeeId: Column<Int> = integer("employee_id") references Employee.employee_id
    override val primaryKey =  PrimaryKey(eventId, employeeId)
}

object Employee: Table() {
    val employee_id: Column<Int> = integer("employee_id").autoIncrement()
    override val primaryKey = PrimaryKey(employee_id)
    val fullName: Column<String> = varchar("full_name", 255)
    val birthdate: Column<LocalDateTime> = datetime("birthdate")
    val phone: Column<String> = varchar("phone", 20)
    val room: Column<String> = varchar("room", 10)
    val email: Column<String> = varchar("email", 30)
    val departmentId: Column<Int> = integer("department_id") references Department.departmentId
    val divisionId: Column<Int?> = (integer("division_id") references Division.divisionId).nullable()
    val unitId: Column<Int?> = (integer("unit_id") references Unit.unitId).nullable()
    val position: Column<String> = varchar("position", 255)
}
