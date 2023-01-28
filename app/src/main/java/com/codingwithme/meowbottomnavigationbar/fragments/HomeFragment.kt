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

    lateinit var  dbRef: DatabaseReference



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

        val rootRef = FirebaseDatabase.getInstance().reference
        val newsRef = rootRef.child("test")
        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val desc = ds.child("CosFi").getValue(String::class.java)
                    val title = ds.child("TIME").getValue(String::class.java)
                    Log.d("abc", "$desc / $title")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("abc", databaseError.message)
            }
        }
        newsRef.addValueEventListener(valueEventListener)

//        showData()
//        println("alo12345")

//        val reference = FirebaseDatabase.getInstance().getReference("test").child("-NKgivs6DhsL5T6IQsYV").child("TIME")

//        reference.addValueEventListener(object : ValueEventListener {
//            override fun onCancelled(error: DatabaseError) {
//                Log.e("Response", error.message)
//
//            }
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for (ds in snapshot.children) {
//                    txtTime.text = ds.toString();
//                    Log.d("Response", ds.toString())
//                }
//            }
//        })
        return view
    }

    fun showData(){
        println("database")







//        dbRef = FirebaseDatabase.getInstance().getReference("test")
//
////        val capList = dbRef.orderByChild("TIME").limitToLast(1);
//        val capList = dbRef.limitToLast(1);
//
//        capList.addValueEventListener(object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                Log.d("snapshot", snapshot.toString())
//                if(snapshot.exists()){
//
//                }
//                for (capSnapshot in snapshot.children){
//                    Log.d("dbRef", capSnapshot.child("TIME").getValue().toString())
//                    val timeDb : String = capSnapshot.child("TIME").getValue().toString();
//                    txtTime.text = timeDb;
//                    print("alo" + txtTime);
//                }
////
////                val test : String =
////                    CharCategory.valueOf(snapshot.child("3").child("CAPACITOR").child("TIME").getValue()
////                        .toString())
////                        .toString();
//                txtTime.text = "abc"
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//        })
////        txtTime.text = "abc"
//        println("sao khong chay")
    }

    companion object {
       @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}
