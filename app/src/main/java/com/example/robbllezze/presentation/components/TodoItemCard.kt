package com.example.robbllezze.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


// In this file we will define a TodoItemCard COMPOSABLE that will be reusable in different screens
@Composable
fun TodoItemCard(

){
//    checkbox, text to display the task, image container
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = false,
                    onCheckedChange = {} // non-functional
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "To-Do Item",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
                )

            }
            Row {
                OutlinedButton(onClick = {}) {
                    Text("Delete")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {}) {
                    Text("Edit")
                }
            }
        }
    }
    
}
@Preview(showBackground = true)
@Composable
fun TodoItemCardPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            TodoItemCard()
        }
    }
}