package com.cpadridev.carmonaadrian_examen1ev

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.cpadridev.carmonaadrian_examen1ev.databinding.SummaryBinding

class Summary : AppCompatActivity() {
    private lateinit var binding: SummaryBinding

    private var computer: Computer? = null
    private var screen: Screen? = null
    private var printer: Printer? = null
    private var inventory: ArrayList<Device> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Detect data entry
        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            val bundle = intent.getBundleExtra(Intent.EXTRA_TEXT)

            computer = bundle?.getParcelable("Computer")
            screen = bundle?.getParcelable("Screen")
            printer = bundle?.getParcelable("Printer")

            inventory = bundle?.getParcelableArrayList("Inventory")!!
        }

        val bundle = Bundle()

        if (computer != null) {
            binding.computerLayout.isVisible = true
            binding.screenLayout.isVisible = false
            binding.printerLayout.isVisible = false

            binding.txvName.text = computer?.name
            binding.txvPlace.text = computer?.place
            binding.txvDevice.text = computer?.device
            binding.txvYear.text = computer?.year.toString()
            binding.txvProcessor.text = computer?.processor

            bundle.putParcelable("Computer", computer)
        }
        if (screen != null) {
            binding.computerLayout.isVisible = false
            binding.screenLayout.isVisible = true
            binding.printerLayout.isVisible = false

            binding.txvName.text = screen?.name
            binding.txvPlace.text = screen?.place
            binding.txvDevice.text = screen?.device
            binding.txvInches.text = screen?.inches.toString()

            bundle.putParcelable("Screen", screen)
        }
        if (printer != null) {
            binding.computerLayout.isVisible = false
            binding.screenLayout.isVisible = false
            binding.printerLayout.isVisible = true

            binding.txvName.text = printer?.name
            binding.txvPlace.text = printer?.place
            binding.txvDevice.text = printer?.device
            binding.txvPhotocopies.text =
                if (printer?.photocopies == true) {
                    getString(R.string.yes)
                } else {
                    getString(R.string.no)
                }

            bundle.putParcelable("Printer", printer)
        }

        binding.btnAccept.setOnClickListener {
            bundle.putParcelableArrayList("Inventory", inventory)

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(Intent.EXTRA_TEXT, bundle)
            }

            startActivity(intent)
        }
    }
}