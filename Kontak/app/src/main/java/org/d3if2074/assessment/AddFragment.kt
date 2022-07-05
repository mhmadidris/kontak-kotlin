package org.d3if2074.assessment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.d3if2074.assessment.databinding.FragmentAddBinding
import org.d3if2074.assessment.model.Kontak

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var db: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        (activity as AppCompatActivity).onBackPressed()
        db = FirebaseDatabase.getInstance().getReference("Kontak")
        binding.buttonSave.setOnClickListener {
            val namaDepan: String = binding.firstName.text.toString()
            val namaBelakang: String = binding.lastName.text.toString()
            val nomorTelepon: String = binding.phoneNumber.text.toString()

            val kontakId: String? = db.push().key

            val kontak = kontakId?.let { Kontak(it, namaDepan, namaBelakang, nomorTelepon) }

            if (kontakId != null) {
                db.child(kontakId).setValue(kontak).addOnCompleteListener {
                    Toast.makeText(
                        (activity as AppCompatActivity).applicationContext,
                        "Data berhasil disimpan",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}