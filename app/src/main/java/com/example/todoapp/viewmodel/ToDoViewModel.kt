package com.example.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ToDoViewModel : ViewModel() {

    private val _toDoUiState = MutableStateFlow(ToDoUiState())
    val toDoUiState = _toDoUiState.asStateFlow()
    var id = 0

    fun onEvent(event: ToDoEvent) {
        when (event) {
            is ToDoEvent.AddToDo -> {
                _toDoUiState.update { currentToDos ->
                    val newToDo = event.toDo.copy(id = id++)
                    currentToDos.copy(
                        toDo = currentToDos.toDo + newToDo
                    )
                }
            }
            is ToDoEvent.DeleteToDo -> {
                _toDoUiState.update { currentToDos ->
                    currentToDos.copy(
                        toDo = currentToDos.toDo - event.toDo
                    )
                }
            }
            is ToDoEvent.UpdateToDo -> {
                val updateToDoReq = event.toDo
                _toDoUiState.update {
                    it.copy(
                        toDo = it.toDo.map { currentToDo ->
                            if (currentToDo.id == updateToDoReq.id) updateToDoReq else currentToDo
                        }
                    )
                }
            }
            is ToDoEvent.MarkAsDone -> {
                _toDoUiState.update {
                    it.copy(
                        toDo = it.toDo.map { currentToDo ->
                            val toDoIsUnDone = currentToDo.id == event.toDo.id && currentToDo.status != event.toDo.status
                            if (toDoIsUnDone) event.toDo else currentToDo
                        }
                    )
                }

            }

            is ToDoEvent.Edit -> {

            }
        }
    }
}