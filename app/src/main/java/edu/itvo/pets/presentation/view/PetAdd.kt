package edu.itvo.pets.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import edu.itvo.pets.data.model.PetModel
import edu.itvo.pets.databinding.ActivityPetAddBinding
import edu.itvo.pets.presentation.viewmodel.PetAddViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.util.*
@AndroidEntryPoint
class PetAdd : AppCompatActivity() {
    private  lateinit var binding:ActivityPetAddBinding
    private val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val petViewModel: PetAddViewModel by viewModels()

    private  val  formatter =  DateTimeFormatterBuilder()
        .appendPattern("dd/MM/uuuu")
        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
        .toFormatter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etBirthDate.setOnClickListener {
            val builder = MaterialDatePicker.Builder.datePicker()
            val picker = builder.build()
            picker.show(supportFragmentManager, "DATE_PICKER")
            picker.addOnPositiveButtonClickListener {
                val date = sdf.format(it)
                binding.etBirthDate.setText(date)
            }
        }
        binding.btnSave.setOnClickListener {
            with(binding){
                val date=etBirthDate.text.toString()
                val pet = PetModel(
                    name=etName.text.toString(),
                    race = etRace.text.toString(),
                    species = etSpecies.text.toString(),
                    description = etDescription.text.toString(),
                    image = etImage.text.toString(),
                    birthDate = LocalDateTime.parse(date, formatter)

                )

                lifecycleScope.launch(Dispatchers.IO) {
                  petViewModel.addPet(pet)
               }
               val alert = CustomAlert()
               alert.showDialog(this@PetAdd, "El Dato ha sido guardado ...")
            }
        }
       observer()
    }

    private fun observer(){
        lifecycleScope.launch {
            val response= petViewModel.petResponse
            response.collect()
        }
    }
}
