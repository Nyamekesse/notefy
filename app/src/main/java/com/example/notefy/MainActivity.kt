package com.example.notefy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notefy.screens.NoteScreen
import com.example.notefy.screens.NoteViewModel
import com.example.notefy.ui.theme.NoteFyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                NoteApp()
            }
        }
    }
}

@Composable
fun NoteApp(noteViewModel: NoteViewModel = viewModel()) {
    val notesList = noteViewModel.getAllNotes()
    NoteScreen(
        notes = notesList,
        onRemoveNote = {
            noteViewModel.removeNote(it)
        },
        onAddNote = {
            noteViewModel.addNote(it)
        })
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    NoteFyTheme {
        content()

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApp {
        Text(text = "Hello there")
    }
}