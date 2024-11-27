package edu.itvo.pets.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.itvo.pets.data.models.PetModel
import edu.itvo.pets.data.models.PetResponse
import edu.itvo.pets.domain.usecases.PetUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPetViewModel@Inject constructor(private val petUseCase: PetUseCase):  ViewModel(){

    private val _petsState = MutableStateFlow<PetResponse?>(null)
    val petsState: StateFlow<PetResponse?> = _petsState


    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun loadPets() {
        viewModelScope.launch {
            try {
                petUseCase.getPets().collect { petResponse ->
                    if (petResponse != null) {
                        _petsState.value = petResponse
                    } else {
                        _petsState.value = null
                    }
                }
            } catch (e: Exception) {
                _errorState.value = e.message
            }
        }
    }
}