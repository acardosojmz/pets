package edu.itvo.pets.data

import edu.itvo.pets.core.convertToList
import edu.itvo.pets.core.toEntity
import edu.itvo.pets.core.toListPetModel
import edu.itvo.pets.core.toPetModel
import edu.itvo.pets.data.local.PetLocalDataSource
import edu.itvo.pets.data.local.daos.PetDao
import edu.itvo.pets.data.model.PetModel
import edu.itvo.pets.data.model.PetResponse
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PetLocalDataSourceImpl  @Inject constructor(private val petDao: PetDao): PetLocalDataSource {
    override suspend fun getPets(): Flow<PetResponse?> {
        val pets = petDao.getPets()
        val data = pets.map { it.toListPetModel() }.convertToList()
        return flow { emit(PetResponse(true, "list pets", data)) }
    }

    override suspend fun getPet(petId: Int): Flow<PetResponse> {
        val pet= petDao.getPet(petId).map { it.toPetModel() }
        val data = flow { emit(listOf(pet.first()))}.convertToList()
        return  flow { emit(PetResponse(true, "list pets", data)) }

    }

    override suspend fun getPetRandom(): Flow<PetResponse> {
        val pet= petDao.getPetRandom().map { it.toPetModel() }
        val data = flow { emit(listOf(pet.first()))}.convertToList()
        return  flow { emit(PetResponse(true, "list pets", data)) }
    }


    override suspend  fun insertAll(pets: List<PetModel>) {
        petDao.insertAll(pets.map { it.toEntity()})
    }


    override suspend  fun insert(pet: PetModel) {
        petDao.insert(pet.toEntity())
    }


    override suspend fun update(pet: PetModel) {
        petDao.update (pet.toEntity())
    }

}
