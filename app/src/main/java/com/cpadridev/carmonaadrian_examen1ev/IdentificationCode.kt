package com.cpadridev.carmonaadrian_examen1ev

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cpadridev.carmonaadrian_examen1ev.databinding.IdentificationCodeBinding

class IdentificationCode : AppCompatActivity() {
    private lateinit var binding: IdentificationCodeBinding

    private var computer: Computer? = null
    private var screen: Screen? = null
    private var printer: Printer? = null
    private var inventory: ArrayList<Device> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = IdentificationCodeBinding.inflate(layoutInflater)
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
            binding.txvCode.text = computer?.code
            bundle.putParcelable("Computer", computer)
        }
        if (screen != null) {
            binding.txvCode.text = screen?.code
            bundle.putParcelable("Screen", screen)
        }
        if (printer != null) {
            binding.txvCode.text = printer?.code
            bundle.putParcelable("Printer", printer)
        }

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