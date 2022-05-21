package edu.itvo.pets.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import edu.itvo.pets.data.model.PetModel
import edu.itvo.pets.databinding.ActivityPetListBinding
import edu.itvo.pets.presentation.viewmodel.PetListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PetList : AppCompatActivity() {
    private lateinit var binding: ActivityPetListBinding
    private val petListViewModel: PetListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observer()

        lifecycleScope.launch (Dispatchers.IO) {
            Log.e("CALL", "from PetList Activity")
            petListViewModel.getPets()
        }

    }
    private fun observer(){

        lifecycleScope.launch {
           petListViewModel.pets.collect{
               val adapter = PetAdapter(it.data as List<PetModel>)
               binding.recyclerView.layoutManager= LinearLayoutManager(baseContext)
               binding.recyclerView.adapter = adapter

            }
        }
        lifecycleScope.launch {
            petListViewModel.isLoading.collect {
                binding.loading.isVisible = it
            }
        }
    }
}