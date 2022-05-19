package edu.itvo.pets.domain.usecase

import edu.itvo.pets.data.model.PetResponse
import edu.itvo.pets.domain.repositories.PetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetPetUseCase @Inject constructor(private val petRepository: PetRepository) {

    suspend fun getPet(petId:Int): Flow<PetResponse?> = petRepository.getPet(petId)

}