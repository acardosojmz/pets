package edu.itvo.pets.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.itvo.pets.data.model.PetModel
import edu.itvo.pets.data.model.PetResponse
import edu.itvo.pets.domain.usecase.GetPetsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject


@HiltViewModel
class PetListViewModel @Inject constructor (
    private val getPetsUseCase: GetPetsUseCase,
): ViewModel() {
    private  val pet=PetModel(id=0,name="Pet","race",
        species = "species", description = "description",
        birthDate = LocalDateTime.now(),
        image = "https://i.pinimg.com/originals/39/7f/83/397f83a3e11de0bf50318f50b8070f7b.jpg"
        )
    private var _pets = MutableStateFlow(PetResponse(false,"",listOf(
        pet)))
    val pets : StateFlow<PetResponse> = _pets

    private val _stateLoading = MutableStateFlow<Boolean>(false)
    val isLoading : StateFlow<Boolean> = _stateLoading

    suspend fun   getPets() {
        viewModelScope.launch(Dispatchers.IO) {
            val petResponse = getPetsUseCase.getPets().first()
            if (petResponse!=null){
                _pets.value =petResponse
            }
        }
    }
}