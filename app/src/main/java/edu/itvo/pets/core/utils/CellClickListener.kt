package edu.itvo.pets.core.utils

import edu.itvo.pets.data.model.PetModel


interface CellClickListener {
    fun onCellClickListener(petModel: PetModel)
}