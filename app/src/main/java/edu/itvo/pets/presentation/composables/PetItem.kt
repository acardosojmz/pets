package edu.itvo.pets.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.itvo.pets.data.models.PetModel



@Composable
fun PetItem(pet: PetModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { /* Acción al hacer click en una mascota */ },
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = pet.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Breed: ${pet.type}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Age: ${pet.description} years", style = MaterialTheme.typography.bodySmall)
        }
    }
}
