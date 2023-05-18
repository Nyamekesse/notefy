package com.example.notefy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.notefy.screens.NoteScreen
import com.example.notefy.ui.theme.NoteFyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                NoteScreen()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit){
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