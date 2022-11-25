package com.cpadridev.carmonaadrian_examen1ev

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cpadridev.carmonaadrian_examen1ev.databinding.IdentificationCodeBinding

class IdentificationCode : AppCompatActivity() {
    private lateinit var binding: IdentificationCodeBinding

    private var device: Device? = null
    private var inventory: ArrayList<Device> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = IdentificationCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Detect data entry
        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            val bundle = intent.getBundleExtra(Intent.EXTRA_TEXT)

            device = bundle?.getParcelable("Device")
            inventory = bundle?.getParcelableArrayList("Inventory")!!
        }

        val bundle = Bundle()

        if (device is Computer) {
            binding.txvCode.text = device?.code
        }
        if (device is Screen) {
            binding.txvCode.text = device?.code
        }
        if (device is Printer) {
            binding.txvCode.text = device?.code
        }

        bundle.putParcelable("Device", device)
        bundle.putParcelableArrayList("Inventory", inventory)

        binding.btnAccept.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(Intent.EXTRA_TEXT, bundle)
            }

            startActivity(intent)
        }

        binding.btnSummary.setOnClickListener {
            val intent = Intent(this, Summary::class.java).apply {
                putExtra(Intent.EXTRA_TEXT, bundle)
            }

            startActivity(intent)
        }
    }
}