package edu.itvo.pets.domain.usecase

import edu.itvo.pets.data.model.PetResponse
import edu.itvo.pets.domain.repositories.PetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetPetsUseCase @Inject constructor(private val petRepository: PetRepository) {

    suspend fun getPets(): Flow<PetResponse?> = petRepository.getPets()

}