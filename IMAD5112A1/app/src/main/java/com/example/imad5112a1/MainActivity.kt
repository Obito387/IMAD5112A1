package com.example.imad5112a1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    private val TAG = "SocialSparkLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContent replaces setContentView(R.layout.activity_main)
        setContent {
            SocialSparkApp()
        }
    }

    @Composable
    fun SocialSparkApp() {
        // "State" variables replace the need for findViewById
        var inputText by remember { mutableStateOf("") }
        var resultText by remember { mutableStateOf("Enter time to see suggestion") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // This is your TextView
            Text(text = resultText, style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(20.dp))

            // TextField
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text("Time of Day (e.g. Morning)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                // Generate Button
                Button(onClick = {
                    val input = inputText.trim().lowercase()
                    Log.d(TAG, "Input: $input")

                    resultText = when (input) {
                        "morning" -> "Send a 'Good morning' text to a family member."
                        "mid-morning" -> "Reach out to a colleague with a quick 'Thank you.'"
                        "afternoon" -> "Share a funny meme or interesting link with a friend."
                        "afternoon snack time" -> "Send a quick 'thinking of you' message."
                        "dinner" -> "Call a friend or relative for a 5-minute catch-up."
                        "after dinner", "night" -> "Leave a thoughtful comment on a friend's post."
                        else -> "That time doesn't look right. Try 'Morning' or 'Dinner'!"
                    }
                }) {
                    Text("Generate")
                }

                Spacer(modifier = Modifier.width(10.dp))

                // Reset Button
                Button(onClick = {
                    inputText = ""
                    resultText = "Enter time to see suggestion"
                }) {
                    Text("Reset")
                }
            }
        }
    }
}
