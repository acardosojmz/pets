package edu.itvo.pets.data.model


import java.time.LocalDateTime

data class PetModel (val id: Int=0,
                     val name:String,
                     val race:String,
                     val species: String,
                     val description: String,
                     val birthDate: LocalDateTime,
                     val image: String,
)