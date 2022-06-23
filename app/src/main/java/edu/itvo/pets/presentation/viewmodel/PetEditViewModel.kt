package edu.itvo.pets.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.itvo.pets.data.model.PetModel
import edu.itvo.pets.domain.usecase.UpdatePetUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetEditViewModel @Inject constructor (
    private val updatePetUseCase: UpdatePetUseCase,
): ViewModel() {
    private  val _status = MutableStateFlow(String())
    val status : StateFlow<String> = _status

    suspend fun   updatePet(pet: PetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.e("EDIT", "Send data for editing from PetEditModel")
            updatePetUseCase.updatePet(pet)
        }
    }
}