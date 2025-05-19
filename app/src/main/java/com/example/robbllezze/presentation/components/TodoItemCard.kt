package com.example.robbllezze.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.robbllezze.R
import com.example.robbllezze.data.model.TodoItem

// IN THIS FILE WE WILL DEFINE A TODOITEMCARD COMPOSABLE THAT WILL BE REUSABLE
// IN DIFFERENT SCREENS
// presentation/components/TodoItemCard.kt


@Composable
fun TodoItemCard(
    todo: TodoItem,
    onCompleteChange: (Boolean) -> Unit,
    onEditClick: (TodoItem) -> Unit,
    onDeleteClick: (TodoItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {},
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                modifier = Modifier.padding(end = 16.dp),
                checked = todo.isCompleted,
                onCheckedChange = onCompleteChange
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = todo.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = todo.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Assignee",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = todo.tasker,
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 4.dp)
                ) {
                    Button(onClick = { onEditClick(todo) }) {
                        Text(text = "Edit", color = MaterialTheme.colorScheme.onPrimary)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = { onDeleteClick(todo) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text(text = "Delete", color = MaterialTheme.colorScheme.onPrimary)
                    }

                }
            }

            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

        }
    }
}

@Preview
@Composable
fun TodoItemCardPreview() {
    TodoItemCard(
        todo = TodoItem(
            id = 1, title = "sample todo", description = "sample text",
            imageUri = null, tasker = "joseph", isCompleted = false
        ),
        onCompleteChange = { isChecked ->
            println("Checked: $isChecked")
        },
        onEditClick = { todo ->
            println("Edit: ${todo.title}")
        },
        onDeleteClick = { todo ->
            println("Delete: ${todo.title}")}
    )
}



















