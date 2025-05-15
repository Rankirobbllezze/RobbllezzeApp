package com.example.robbllezze.presentation.screens.addtodo

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.robbllezze.data.repository.MockToDoRepository
import com.example.robbllezze.data.repository.TodoRepository
import com.example.robbllezze.presentation.screens.dashboard.DashboardViewModel
//1. ADD THE VIEWMODEL WITH THE FUNCTION TO OPERATE
//2. ONDISMISS AS OUR FORM WILL BE SHOW CASED ON A POP UP

@Composable
fun AddToDoForm(
    viewModel: DashboardViewModel,
    onDismiss: () -> Unit
){
    //Variable to save inputs
    val context = LocalContext.current
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    //define a reference from activity launcher
    //this allows capture of multimedia files from other apps
    val imagePickerLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()) { uri: Uri? -> imageUri = uri }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var tasker by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add To Do",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = title , onValueChange = {title = it},
            label = { Text("Title")},
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = description , onValueChange = {description = it},
            label = { Text("Description")},
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = tasker , onValueChange = {tasker = it},
            label = { Text("Tasker Name")},
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        //button to pick image
        Button(onClick = {
//            imagePickerLauncher.launch("*")
            imagePickerLauncher.launch("image/*")
        }) { Text(text = "Select Image")}
        //image container
        //rememberAsyncImagePainter : this is from the coil lib. allowing us to load images to
        //image containers. it - imageUri selected
        imageUri?.let{
            Image(painter = rememberAsyncImagePainter(it),
                contentDescription = null)
        }

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            Button(onClick = onDismiss,
                colors = ButtonDefaults.buttonColors()) {
                Text("Cancel")
            }
            Button(onClick = {
                if(title.isNotBlank()){
                    viewModel.addToDO(title,description,tasker,imageUri)
                    onDismiss()
                }
            }, enabled = title.isNotBlank()) {
                Text("Add To Do")
            }
        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun AddToDoFormPreview(){
    val viewModel = DashboardViewModel(MockToDoRepository())
    AddToDoForm(viewModel = viewModel, onDismiss = {})
}