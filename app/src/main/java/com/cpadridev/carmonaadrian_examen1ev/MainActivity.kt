package com.cpadridev.carmonaadrian_examen1ev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.cpadridev.carmonaadrian_examen1ev.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var device: Device? = null
    private var inventory: ArrayList<Device> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Detect data entry
        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            val bundle = intent.getBundleExtra(Intent.EXTRA_TEXT)

            device = bundle?.getParcelable("Device")

            inventory = bundle?.getParcelableArrayList("Inventory")!!
        }

        if (device is Computer) {
            inventory.add(device!!)
        }
        if (device is Screen) {
            inventory.add(device!!)
        }
        if (device is Printer) {
            inventory.add(device!!)
        }

        for (item in inventory) {
            binding.txvList.append("$item \n")
        }

        binding.fabAccept.setOnClickListener {
            val bundle = Bundle()

            bundle.putParcelableArrayList("Inventory", inventory)

            val intent = Intent(this, Form::class.java).apply {
                putExtra(Intent.EXTRA_TEXT, bundle)
            }

            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // Delete the list.
            R.id.deleteMenu -> {
                if (inventory.size != 0) {
                    inventory.clear()
                    binding.txvList.text = ""
                    Toast.makeText(
                        this,
                        getString(R.string.show_delete_information),
                        Toast.LENGTH_LONG
                    )
                        .show()
                } else {
                    Toast.makeText(this, getString(R.string.error_delete_list), Toast.LENGTH_LONG)
                        .show()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // In the main interface, it exits the application if you hit the back button.
        finishAffinity()
    }
}
