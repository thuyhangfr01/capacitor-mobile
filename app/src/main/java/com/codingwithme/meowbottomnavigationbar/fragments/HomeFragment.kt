package com.codingwithme.meowbottomnavigationbar.fragments

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.codingwithme.meowbottomnavigationbar.R
import com.google.firebase.database.*
import java.util.*


class HomeFragment : Fragment() {
    lateinit var txtTime: TextView
    lateinit var txtDienAp: TextView
    lateinit var txtDongDien: TextView
    lateinit var txtTanSo: TextView
    lateinit var txtCsTacDung: TextView
    lateinit var txtCsPhanKhang: TextView
    lateinit var txtHeSoCongSuat: TextView
    lateinit var imgStatus1: ImageView
    lateinit var imgStatus2: ImageView
    lateinit var imgStatus3: ImageView
    lateinit var imgStatus4: ImageView
    lateinit var imgStatus5: ImageView
    lateinit var imgStatus6: ImageView
    lateinit var imgStatus7: ImageView
    lateinit var imgStatus8: ImageView

    @SuppressLint("MissingInflatedId")
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
        txtHeSoCongSuat = view.findViewById(R.id.hs_congsuat)
        imgStatus1 = view.findViewById(R.id.ic_status1)
        imgStatus2 = view.findViewById(R.id.ic_status2)
        imgStatus3 = view.findViewById(R.id.ic_status3)
        imgStatus4 = view.findViewById(R.id.ic_status4)
        imgStatus5 = view.findViewById(R.id.ic_status5)
        imgStatus6 = view.findViewById(R.id.ic_status6)
        imgStatus7 = view.findViewById(R.id.ic_status7)
        imgStatus8 = view.findViewById(R.id.ic_status8)

        showData()

        return view
    }

    private fun showData(){
        val rootRef = FirebaseDatabase.getInstance().reference
        val testRef = rootRef.child("euphoria").orderByChild("TIME").limitToLast(1)
        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val time = ds.child("TIME").getValue(String::class.java)
                    val dienAp = ds.child("Three-phase Equivalent Voltage").getValue(String::class.java)
                    val dongDien = ds.child("Three-phase Equivalent Current").getValue(String::class.java)
                    val tanSo = ds.child("Frequency").getValue(String::class.java)
                    val csTacDung = ds.child("ActivePowe").getValue(String::class.java)
                    val csPhanKhang = ds.child("ReactivePower").getValue(String::class.java)
                    val hsCongSuat = ds.child("CosFi").getValue(String::class.java)
                    val stt1 = ds.child("Coil 1").getValue(String::class.java)
                    val stt2 = ds.child("Coil 2").getValue(String::class.java)
                    val stt3 = ds.child("Coil 3").getValue(String::class.java)
                    val stt4 = ds.child("Coil 4").getValue(String::class.java)
                    val stt5 = ds.child("Contactor1").getValue(String::class.java)
                    val stt6 = ds.child("Contactor2").getValue(String::class.java)
                    val stt7 = ds.child("Contactor3").getValue(String::class.java)
                    val stt8 = ds.child("Contactor4").getValue(String::class.java)

                    // Set data
                    txtTime.text = time
                    txtDienAp.text = dienAp
                    txtDongDien.text = dongDien
                    txtTanSo.text = tanSo
                    txtCsTacDung.text = csTacDung
                    txtCsPhanKhang.text = csPhanKhang
                    txtHeSoCongSuat.text = hsCongSuat
                    if(stt1 == "0"){
                        imgStatus1.setImageResource(R.drawable.ic_status_green)
                    }
                    else {
                        imgStatus1.setImageResource(R.drawable.ic_status_red)
                    }
                    if(stt2 == "0"){
                        imgStatus2.setImageResource(R.drawable.ic_status_green)
                    }
                    else {
                        imgStatus2.setImageResource(R.drawable.ic_status_red)
                    }
                    if(stt3 == "0"){
                        imgStatus3.setImageResource(R.drawable.ic_status_green)
                    }
                    else {
                        imgStatus3.setImageResource(R.drawable.ic_status_red)
                    }
                    if(stt4 == "0"){
                        imgStatus4.setImageResource(R.drawable.ic_status_green)
                    }
                    else {
                        imgStatus4.setImageResource(R.drawable.ic_status_red)
                    }
                    if(stt5 == "0"){
                        imgStatus5.setImageResource(R.drawable.ic_status_green)
                    }
                    else {
                        imgStatus5.setImageResource(R.drawable.ic_status_red)
                    }
                    if(stt6 == "0"){
                        imgStatus6.setImageResource(R.drawable.ic_status_green)
                    }
                    else {
                        imgStatus6.setImageResource(R.drawable.ic_status_red)
                    }
                    if(stt7 == "0"){
                        imgStatus7.setImageResource(R.drawable.ic_status_green)
                    }
                    else {
                        imgStatus7.setImageResource(R.drawable.ic_status_red)
                    }
                    if(stt8 == "0"){
                        imgStatus8.setImageResource(R.drawable.ic_status_green)
                    }
                    else {
                        imgStatus8.setImageResource(R.drawable.ic_status_red)
                    }
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
