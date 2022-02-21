package ru.dudar.mycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
            ListMessage(SampleData.messageList)
                }

    }
}

data class Message(val autor: String, val body: String)

@Composable
fun MessageCard(message: Message) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = 3.dp) {
        Row(Modifier.padding(all = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.profile_picture),
                contentDescription ="Contact Profile",
                Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape))
            Spacer(modifier = Modifier.width(8.dp))

            // отслеживание состояния (развернутое или свернутое)
            var isExpanded by remember {mutableStateOf(false)}
            val surfaceColor: Color by animateColorAsState(
                if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,)

            Column(Modifier.clickable { isExpanded = !isExpanded } ) {
                Text(text = message.autor,
                    color= Color(0xFF09329B),
                    style = MaterialTheme.typography.subtitle2
                )
                Spacer(Modifier.height(3.dp))

                Surface(shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    color = surfaceColor,
                    modifier = Modifier.animateContentSize().padding(1.dp).fillMaxWidth()

                ) {
                    Text(text = message.body,
                    modifier = Modifier.padding(all = 4.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2
                        )
                }

            }
        }
    }
}

@Composable
fun ListMessage(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MyComposeTheme {
//        MessageCard(Message("Игорь","привет!!!" ))
//    }
//}