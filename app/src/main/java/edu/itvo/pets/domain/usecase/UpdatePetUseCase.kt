package edu.itvo.pets.domain.usecase

import edu.itvo.pets.data.model.PetModel
import edu.itvo.pets.domain.repositories.PetRepository
import javax.inject.Inject

class UpdatePetUseCase  @Inject constructor(private val petRepository: PetRepository) {

    suspend fun updatePet(petModel: PetModel) {
        petRepository.updatePet(petModel)
    }
}