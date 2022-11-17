package co.edu.escuelaing.taskplannerapp.dto

import co.edu.escuelaing.taskplannerapp.models.Task

class TaskDTO {
    var id: String? = null
    var name: String? = null
    var description: String? = null
    var status: String? = null
    var assignedTo: String? = null
    var dueDate: String? = null
    var createdAt: String? = null

    constructor() {}
    constructor(
        id: String?,
        name: String?,
        description: String?,
        status: String?,
        assignedTo: String?,
        dueDate: String?,
        createdAt: String?
    ) {
        this.id = id
        this.name = name
        this.description = description
        this.status = status
        this.assignedTo = assignedTo
        this.dueDate = dueDate
        this.createdAt = createdAt
    }

    fun toEntity(): Task {
        return Task(id, name, description, status, assignedTo, dueDate, createdAt)
    }
}