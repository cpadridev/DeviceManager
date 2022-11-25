package com.cpadridev.carmonaadrian_examen1ev

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.cpadridev.carmonaadrian_examen1ev.databinding.FormBinding

class Form : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FormBinding
    private lateinit var device: Device

    private var inventory: ArrayList<Device> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            val bundle = intent.getBundleExtra(Intent.EXTRA_TEXT)

            inventory = bundle?.getParcelableArrayList("Inventory")!!
        }

        // Creation of the vehicle spinner.
        ArrayAdapter.createFromResource(
            this,
            R.array.type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spnDevice.adapter = adapter
        }

        binding.spnDevice.onItemSelectedListener = this


        binding.btnAdd.setOnClickListener {
            var check = false
            val typeArray = resources.getStringArray(R.array.type_array)
            if (binding.edtName.text.isNotEmpty() && binding.edtPlace.text.isNotEmpty()) {
                val bundle = Bundle()

                when (binding.spnDevice.selectedItem) {
                    typeArray[0] -> {
                        if (binding.edtYear.text?.length == 4) {
                            device = Computer(
                                binding.edtName.text.toString(),
                                binding.edtPlace.text.toString(),
                                binding.spnDevice.selectedItem.toString(),
                                binding.edtYear.text.toString().toInt(),
                                if (binding.rbtIntel.isChecked) {
                                    "Intel"
                                } else {
                                    "AMD"
                                }
                            )

                            check = true
                        } else {
                            Toast.makeText(
                                this,
                                getString(R.string.error_field_year),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    typeArray[1] -> {
                        if (binding.edtInches.text?.length == 2) {
                            device = Screen(
                                binding.edtName.text.toString(),
                                binding.edtPlace.text.toString(),
                                binding.spnDevice.selectedItem.toString(),
                                binding.edtInches.text.toString().toInt()
                            )

                            check = true
                        } else {
                            Toast.makeText(
                                this,
                                getString(R.string.error_field_inches),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    else -> {
                        device = Printer(
                            binding.edtName.text.toString(),
                            binding.edtPlace.text.toString(),
                            binding.spnDevice.selectedItem.toString(),
                            binding.chkPhotocopies.isChecked
                        )

                        check = true
                    }
                }

                if (check) {
                    bundle.putParcelable("Device", device)
                    bundle.putParcelableArrayList("Inventory", inventory)

                    val intent = Intent(this, IdentificationCode::class.java).apply {
                        putExtra(Intent.EXTRA_TEXT, bundle)
                    }

                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, getString(R.string.error_fields_form), Toast.LENGTH_LONG)
                    .show()
            }
        }

        binding.btnCancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // Item selected
        val item = parent?.getItemAtPosition(position).toString()
        // Get an array from string resources
        val typeArray = resources.getStringArray(R.array.type_array)

        when (item) {
            typeArray[0] -> {
                binding.computerLayout.isVisible = true
                binding.screenLayout.isVisible = false
                binding.printerLayout.isVisible = false
            }
            typeArray[1] -> {
                binding.computerLayout.isVisible = false
                binding.screenLayout.isVisible = true
                binding.printerLayout.isVisible = false
            }
            else -> {
                binding.computerLayout.isVisible = false
                binding.screenLayout.isVisible = false
                binding.printerLayout.isVisible = true
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}