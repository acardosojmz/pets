package edu.itvo.pets.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.itvo.pets.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddPets.setOnClickListener {
            val intent= Intent(this,PetAdd::class.java)
            startActivity(intent)
        }
        binding.btnListPets.setOnClickListener {
            val intent= Intent(this,PetAdd::class.java)
            startActivity(intent)
        }
    }
}