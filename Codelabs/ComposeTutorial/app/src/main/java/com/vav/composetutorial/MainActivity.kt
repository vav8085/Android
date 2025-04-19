package com.vav.composetutorial

import android.os.Bundle
import android.provider.Telephony.Sms.Conversations
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vav.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTutorialTheme {
                Conversations(SampleData.conversationSample)
            }
        }
    }
}

data class Message(val author: String, val body: String)


@Composable
fun MessageCard(msg: Message) {
    Row {
        Image(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(
                    1.5.dp, MaterialTheme.colorScheme.primary,
                    CircleShape
                ),
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Contact profile picture",
        )
        Spacer(modifier = Modifier.size(8.dp))

        var isExpanded by remember { mutableStateOf(false) }
//        val surfaceColor by animateColorAsState(
//            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
//        )
        Column {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp, onClick = { isExpanded = !isExpanded }, modifier = Modifier.animateContentSize()) {
                Text(
                    modifier = Modifier.padding(all = 4.dp),
                    text = msg.body,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTutorialTheme {
        Surface {
            MessageCard(Message("Android", "Jetpack Compose"))
        }
    }
}

@Composable
fun Conversations(messages: List<Message>) {
    LazyColumn {
        items(messages.size, itemContent = {
            MessageCard(messages[it])
        })
    }
}

@Preview(showBackground = true)
@Composable
fun ConversationsPreview() {
    ComposeTutorialTheme {
        Conversations(SampleData.conversationSample)
    }
}