package com.example.togglebutton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Yellow),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                TriStateToggle()
            }

        }
    }
}

@Composable
fun TriStateToggle() {

    val states = listOf(
        "Google",
        "Dummy",
    )

    val GoogleList = listOf(
        "Search",
        "Text",
        "Translate",
    )

    val DummyList = listOf(
        "Search",
        "Text",
        "Translate",
        "Q/A"
    )

    var selectedOption by remember {
        mutableStateOf(states[0])
    }

    selectedOption = ToggleButton(states)

    Spacer(modifier = Modifier.size(30.dp))

    if(selectedOption == states[0]) {
        ToggleButton(states = GoogleList)
    }
    else{
        ToggleButton(states = DummyList)
    }
}

@Composable
fun ToggleButton(states: List<String>) : String {

    var selectedOption by remember {
        mutableStateOf(states[0])
    }

    val onSelectionChange = { text: String ->
        selectedOption = text
    }

    Surface(
        shape = RoundedCornerShape(24.dp),
        shadowElevation = 4.dp,
        modifier = Modifier
            .wrapContentSize()
    ) {
        Row(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(24.dp))
                .background(Color.DarkGray)
        ) {
            states.forEach { text->
                Text(
                    text = text,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(24.dp))
                        .clickable {
                            onSelectionChange(text)
                        }
                        .background(
                            if (text == selectedOption) {
                                Color.White
                            } else {
                                Color.DarkGray
                            }
                        )
                        .padding(
                            vertical = 12.dp,
                            horizontal = 16.dp,
                        ),
                    color = if(text == selectedOption){
                        Color.Black
                    }else{
                        Color.White
                    }
                )
            }
        }
    }

    return selectedOption
}