package edu.itvo.pets.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import edu.itvo.pets.R
import edu.itvo.pets.data.model.PetModel
import edu.itvo.pets.databinding.PetitemBinding

class PetAdapter(private val listImageSound: List<PetModel>) :
    RecyclerView.Adapter<PetAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val binding = PetitemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)

        return ViewHolder(binding)
    }
    inner class ViewHolder(val binding: PetitemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        with(holder){
            with(listImageSound[position]){


                binding.description.text="DESCRIPCION: \n\r" + this.description
                binding.name.text = "NOMBRE: " + this.name
                binding.race.text =  "RAZA: "+  this.race
                binding.species.text = "ESPECIE: " + this.species
                if (this.image.trim()!="") {
                    binding.image.load(this.image) {
                        crossfade(true)
                        placeholder(R.drawable.not_pets)
                        transformations(CircleCropTransformation())
                    }
                }else {
                    binding.image.load(R.drawable.not_pets) {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }
                }
            }
        }

    }


    override fun getItemCount(): Int {
        return listImageSound.size
    }

}