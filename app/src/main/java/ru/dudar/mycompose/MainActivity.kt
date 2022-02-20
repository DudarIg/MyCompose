package ru.dudar.mycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import kotlinx.coroutines.awaitAll
import ru.dudar.mycompose.ui.theme.MyComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                    Greeting(Message("Игорь","привет!!!" ))
                }

    }
}

data class Message(val autor: String, val body: String)

@Composable
fun Greeting(message: Message) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = 3.dp) {
        Row(Modifier.padding(all = 10.dp)) {
            Image(painter = painterResource(id = R.drawable.profile_picture),
                contentDescription ="Contact Profile",
                Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape))
            Spacer(modifier = Modifier.width(10.dp))
            Column(Modifier.padding(horizontal = 10.dp)) {
                Text(text = message.autor,
                    color= Color(0xFF09329B)
                )
                Spacer(Modifier.height(3.dp))
                Text(text = message.body)
            }
        }
    }



}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeTheme {
        Greeting(Message("Игорь","привет!!!" ))
    }
}