package edu.itvo.pets.core

import edu.itvo.pets.data.local.entities.PetEntity
import edu.itvo.pets.data.model.PetModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList

@kotlinx.coroutines.FlowPreview
suspend fun <T> Flow<List<T>>.convertToList() =
    flatMapConcat { it.asFlow() }.toList()

fun PetEntity.toPetModel() = PetModel(
    id=id,
    name = name,
    birthDate= birthDate,
    species= species,
    race = race,
    description = description,
    image = image,
)

fun PetModel.toEntity() = PetEntity(
    id=id,
    name = name,
    birthDate= birthDate,
    species= species,
    race = race,
    description = description,
    image = image,
)

fun List<PetModel>.toListPetEntity () =
    map {it.toEntity() }

fun List<PetEntity>.toListPetModel () =
    map {it.toPetModel() }