package edu.itvo.pets.presentation.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import edu.itvo.pets.data.model.PetModel
import edu.itvo.pets.databinding.ActivityPetEditBinding
import edu.itvo.pets.presentation.viewmodel.PetEditViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.util.*
@AndroidEntryPoint
class PetEdit : AppCompatActivity() {
    private  lateinit var binding: ActivityPetEditBinding
    private val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val editViewModel: PetEditViewModel by viewModels()
    private  val  formatter =  DateTimeFormatterBuilder()
        .appendPattern("dd/MM/uuuu")
        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
        .toFormatter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrieveData()
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
            with(binding) {
                val date = etBirthDate.text.toString()
                val pet = PetModel(
                    id= etId.text.toString().toInt(),
                    name = etName.text.toString(),
                    race = etRace.text.toString(),
                    species = etSpecies.text.toString(),
                    description = etDescription.text.toString(),
                    image = etImage.text.toString(),
                    birthDate = LocalDateTime.parse(date, formatter)

                )
                lifecycleScope.launch(Dispatchers.IO) {
                    editViewModel.updatePet(pet)
                }
                val alert = CustomAlert()
                alert.showDialog(this@PetEdit, "Cambios guardados ...")
            }
        }
        observer()
    }
    private  fun retrieveData(){
        binding.etId.setText(intent.getStringExtra("id"))
        binding.etName.setText(intent.getStringExtra("name"))
        binding.etRace.setText(intent.getStringExtra("race"))
        binding.etSpecies.setText(intent.getStringExtra("species"))
        binding.etDescription.setText(intent.getStringExtra("description"))
        binding.etImage.setText(intent.getStringExtra("image"))
        val date = intent.getStringExtra( "birthDate")!!.substring(0,10)
        val birthDate = date.split("-").reversed().joinToString("/")
        binding.etBirthDate.setText(birthDate)

    }
    private fun observer(){
        lifecycleScope.launch {
            val status= editViewModel.status
            status.collect()
        }
    }
}