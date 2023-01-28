package com.codingwithme.meowbottomnavigationbar.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.codingwithme.meowbottomnavigationbar.R
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {
    lateinit var txtTime: TextView
    lateinit var txtDienAp: TextView
    lateinit var txtDongDien: TextView
    lateinit var txtTanSo: TextView
    lateinit var txtCsTacDung: TextView
    lateinit var txtCsPhanKhang: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        txtTime = view.findViewById(R.id.txt_time)
        txtDienAp = view.findViewById(R.id.dien_ap)
        txtDongDien = view.findViewById(R.id.dong_dien)
        txtTanSo = view.findViewById(R.id.tan_so)
        txtCsTacDung = view.findViewById(R.id.cs_tacdung)
        txtCsPhanKhang = view.findViewById(R.id.cs_phankhang)

        showData()
        return view
    }

    private fun showData(){
        val rootRef = FirebaseDatabase.getInstance().reference
        val testRef = rootRef.child("test").orderByChild("TIME").limitToLast(1)
        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val time = ds.child("TIME").getValue(String::class.java)
                    val dienAp = ds.child("Three-phase Equivalent Voltage").getValue(String::class.java)
                    val dongDien = ds.child("Three-phase Equivalent Current").getValue(String::class.java)
                    val tanSo = ds.child("Frequency").getValue(String::class.java)
                    val csTacDung = ds.child("ActivePowe").getValue(String::class.java)
                    val csPhanKhang = ds.child("ReactivePower").getValue(String::class.java)

                    // Set data
                    txtTime.text = time
                    txtDienAp.text = dienAp
                    txtDongDien.text = dongDien
                    txtTanSo.text = tanSo
                    txtCsTacDung.text = csTacDung
                    txtCsPhanKhang.text = csPhanKhang
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("error", databaseError.message)
            }
        }
        testRef.addValueEventListener(valueEventListener)
    }

    companion object {
       @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}
