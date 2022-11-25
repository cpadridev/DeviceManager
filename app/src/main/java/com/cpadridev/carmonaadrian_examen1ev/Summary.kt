package com.cpadridev.carmonaadrian_examen1ev

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.cpadridev.carmonaadrian_examen1ev.databinding.SummaryBinding

class Summary : AppCompatActivity() {
    private lateinit var binding: SummaryBinding

    private var device: Device? = null
    private var inventory: ArrayList<Device> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Detect data entry
        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            val bundle = intent.getBundleExtra(Intent.EXTRA_TEXT)

            device = bundle?.getParcelable("Device")

            inventory = bundle?.getParcelableArrayList("Inventory")!!
        }

        val bundle = Bundle()

        binding.txvName.text = device?.name
        binding.txvPlace.text = device?.place
        binding.txvDevice.text = device?.device

        if (device is Computer) {
            binding.computerLayout.isVisible = true

            binding.txvYear.text = (device as Computer).year.toString()
            binding.txvProcessor.text = (device as Computer).processor
        }
        if (device is Screen) {
            binding.screenLayout.isVisible = true

            binding.txvInches.text = (device as Screen).inches.toString()
        }
        if (device is Printer) {
            binding.printerLayout.isVisible = true

            binding.txvPhotocopies.text =
                if ((device as Printer).photocopies) {
                    getString(R.string.yes)
                } else {
                    getString(R.string.no)
                }
        }

        binding.btnAccept.setOnClickListener {
            bundle.putParcelable("Device", device)
            bundle.putParcelableArrayList("Inventory", inventory)

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(Intent.EXTRA_TEXT, bundle)
            }

            startActivity(intent)
        }
    }
}