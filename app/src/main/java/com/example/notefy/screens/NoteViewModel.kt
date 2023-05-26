package com.example.notefy.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notefy.model.Note
import com.example.notefy.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    //    private var noteList = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    private val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect { listOfNotes ->
                if (listOfNotes.isEmpty()) {
                    Log.d("Empty", "The list is empty for now")
                } else {
                    _noteList.value = listOfNotes
                }
            }
        }
    }

    suspend fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }
    suspend fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
    suspend fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }

}