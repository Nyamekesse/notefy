package com.example.notefy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notefy.screens.NoteScreen
import com.example.notefy.screens.NoteViewModel
import com.example.notefy.ui.theme.NoteFyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                val noteViewModel = viewModel<NoteViewModel>()
                NoteApp(noteViewModel)
            }
        }
    }
}

@Composable
fun NoteApp(noteViewModel: NoteViewModel) {
    val notesList = noteViewModel.noteList.collectAsState().value
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