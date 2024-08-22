package com.example.contact

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contact.databinding.ActivityMainBinding
import com.example.contact.databinding.DialogAddEditBinding

class MainActivity : AppCompatActivity(), OnContactClickListener {

    private lateinit var contactAdapter: ContactAdapter
    private val contactList = mutableListOf<Contact>()
    private var contactId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contactAdapter = ContactAdapter(contactList, this)
        binding.recycleView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = contactAdapter
        }

        binding.addButton.setOnClickListener {
            openAddEditDialog(null)
        }
    }

    private fun openAddEditDialog(contact: Contact?) {
        val dialogView = DialogAddEditBinding.inflate(layoutInflater)
        val editTextName = dialogView.editTextName
        val editTextPhone = dialogView.editTextPhone

        contact?.let {
            editTextName.setText(it.name)
            editTextPhone.setText(it.phone)
        }

        AlertDialog.Builder(this)
            .setTitle(if (contact == null) "연락처 추가" else "연락처 수정")
            .setView(dialogView.root)
            .setPositiveButton("저장") { dialog, _ ->
                val name = editTextName.text.toString()
                val phone = editTextPhone.text.toString()

                if (contact == null) {
                    val newContact = Contact(contactId++, name, phone)
                    contactAdapter.addContact(newContact)
                } else {
                    contact.name = name
                    contact.phone = phone
                    contactAdapter.updateContact(contact)
                }
                dialog.dismiss()
            }
            .setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onContactClick(contact: Contact) {
        openAddEditDialog(contact)
    }

    override fun onContactDelete(contact: Contact) {
        contactAdapter.deleteContact(contact)
    }

    override fun onContactSend(contact: Contact) {
        val intent = Intent(this, ContactDetailActivity2::class.java).apply {
            putExtra("contact", contact)
        }
        startActivity(intent)
    }


}