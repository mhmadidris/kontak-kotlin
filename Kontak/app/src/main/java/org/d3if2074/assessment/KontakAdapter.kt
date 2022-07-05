package org.d3if2074.assessment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import org.d3if2074.assessment.model.Kontak

class KontakAdapter(
    val kontakCtx: Context,
    val layoutResId: Int,
    val kontakList: MutableList<Kontak>
) :
    ArrayAdapter<Kontak>(kontakCtx, layoutResId, kontakList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(kontakCtx)

        val view: View = layoutInflater.inflate(layoutResId, null)

        val nameText: TextView = view.findViewById(R.id.nameText)
        val phoneText: TextView = view.findViewById(R.id.nomorText)

        val kontak = kontakList[position]

        val nameGet = kontak.namaDepan + " " + kontak.namaBelakang
        nameText.text = nameGet
        phoneText.text = kontak.nomor

        return view
    }
}