package edu.itvo.pets.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import edu.itvo.pets.data.model.PetModel
import edu.itvo.pets.data.model.PetResponse
import edu.itvo.pets.domain.usecase.AddPetUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


import javax.inject.Inject

@HiltViewModel
class PetAddViewModel @Inject constructor (
    private val addPetUseCase: AddPetUseCase,
): ViewModel() {
    private val petResponseMutableStateFlow = MutableStateFlow(
        PetResponse(false, message = "", data= listOf())
    )
    val petResponse: StateFlow<PetResponse> = petResponseMutableStateFlow


    suspend fun   addPet(pet:PetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            addPetUseCase.addPet(pet)
        }

    }

}