package edu.itvo.pets.data

import edu.itvo.pets.data.local.PetLocalDataSource
import edu.itvo.pets.data.model.PetModel
import edu.itvo.pets.data.model.PetResponse
import edu.itvo.pets.domain.repositories.PetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PetRepositoryImpl @Inject constructor (private val localDataSource: PetLocalDataSource):
    PetRepository {
    override suspend  fun getPets(): Flow<PetResponse?> {
        val pets = localDataSource.getPets()
        return (pets)
    }

    override suspend fun getPetRandom(): Flow<PetResponse?> {
        return  localDataSource.getPetRandom()
    }

    override suspend fun getPet(petId: Int): Flow<PetResponse> {
        return localDataSource.getPet(petId)
    }

    override suspend  fun updatePet(petModel: PetModel) {
        localDataSource.update(petModel)
    }

    override suspend fun addPet (petModel: PetModel) {
        localDataSource.insert(petModel)
    }

    override suspend fun addPets(pets: List<PetModel>) {
         localDataSource.insertAll(pets)
    }
}