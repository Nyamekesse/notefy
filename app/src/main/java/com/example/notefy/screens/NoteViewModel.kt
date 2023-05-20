package com.example.notefy.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.notefy.data.NotesDataSource
import com.example.notefy.model.Note

class NoteViewModel : ViewModel() {
    private var noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note: Note) {
        noteList.add(note)
    }

    fun removeNote(not: Note) {
        noteList.remove(not)
    }

    fun getAllNotes(): List<Note> {
        return noteList
    }

}