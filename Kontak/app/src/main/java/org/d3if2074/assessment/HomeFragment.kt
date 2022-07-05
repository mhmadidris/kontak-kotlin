package org.d3if2074.assessment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.database.*
import org.d3if2074.assessment.databinding.FragmentHomeBinding
import org.d3if2074.assessment.model.Kontak

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var db: DatabaseReference
    private lateinit var listKotak: ListView
    private lateinit var kontakList: MutableList<Kontak>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.addFragment)
        }

        db = FirebaseDatabase.getInstance().getReference("Kontak")

        listKotak = binding.listKontak
        kontakList = mutableListOf()

        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    for (h in p0.children) {
                        val laundry = h.getValue(Kontak::class.java)
                        if (laundry != null) {
                            kontakList.add(laundry)
                        }
                    }

                    val adapter = activity?.let { KontakAdapter(it.applicationContext, R.layout.item_kontak, kontakList) }
                    listKotak.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}