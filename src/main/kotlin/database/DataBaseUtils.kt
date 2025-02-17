package database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object DataBaseUtils {
    init {
        Database.connect(
            "jdbc:postgresql://localhost:5432/postgres",
            "org.postgresql.Driver",
            "postgres",
            "")
    }
    fun create() {
        transaction {
            SchemaUtils.create(
                Department,
                Division,
                Unit,
                EventType,
                EventStatus,
                Event,
                TrainingMaterial,
                EmployeeEvents,
                Employee
            )
        }
    }
    fun getAllDepartments(): List<String> {
        return transaction {
            Department.selectAll().map { it[Department.name] }
        }
    }
    fun getAllDivisions(): List<String> {
        return transaction {
            Division.selectAll().map { it[Division.name] }
        }
    }
    fun getAllUnits(): List<String> {
        return transaction {
            Unit.selectAll().map { it[Unit.name] }
        }
    }

    fun getDepartmentIdByName(
        departmentName: String
    ): Int {
        return transaction {
            Department.selectAll().where { Department.name eq departmentName }
                .map { it[Department.departmentId] }
                .firstOrNull() ?: -1
        }
    }

    fun getDivisionIdByName(
        divisionName: String
    ): Int {
        return transaction {
            Division.selectAll().where { Division.name eq divisionName }
                .map { it[Division.divisionId] }
                .firstOrNull() ?: -1
        }
    }

    fun getUnitIdByName(
        unitName: String
    ): Int {
        return transaction {
            Unit.selectAll().where { Unit.name eq unitName }
                .map { it[Unit.unitId] }
                .firstOrNull() ?: -1
        }
    }

    fun getEmployeeByDepartment(
        departmentId: Int
    ): List<EmployeeInfo> {
        return transaction {
            val allEmployees = Employee.selectAll().where { Employee.departmentId eq departmentId }
            allEmployees.map { it ->
                EmployeeInfo(
                    fullName = it[Employee.fullName],
                    birthDate = it[Employee.birthdate],
                    phone = it[Employee.phone],
                    room = it[Employee.room],
                    email = it[Employee.email],
                    position = it[Employee.position]
                )
            }
        }
    }
    fun getEmployeeByDivisions(
        divisionId: Int
    ): List<EmployeeInfo> {
        return transaction {
            val allEmployees = Employee.selectAll().where { Employee.departmentId eq divisionId }
            allEmployees.map { it ->
                EmployeeInfo(
                    fullName = it[Employee.fullName],
                    birthDate = it[Employee.birthdate],
                    phone = it[Employee.phone],
                    room = it[Employee.room],
                    email = it[Employee.email],
                    position = it[Employee.position]
                )
            }
        }
    }
    fun getEmployeeByUnits(
        unitId: Int
    ): List<EmployeeInfo> {
        return transaction {
            val allEmployees = Employee.selectAll().where { Employee.unitId eq unitId }
            allEmployees.map { it ->
                EmployeeInfo(
                    fullName = it[Employee.fullName],
                    birthDate = it[Employee.birthdate],
                    phone = it[Employee.phone],
                    room = it[Employee.room],
                    email = it[Employee.email],
                    position = it[Employee.position]
                )
            }
        }
    }
}