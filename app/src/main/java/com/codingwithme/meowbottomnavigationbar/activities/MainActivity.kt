package com.codingwithme.meowbottomnavigationbar.activities

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.codingwithme.meowbottomnavigationbar.R
import com.codingwithme.meowbottomnavigationbar.fragments.HomeFragment
import com.codingwithme.meowbottomnavigationbar.fragments.NotificationFragment
import com.codingwithme.meowbottomnavigationbar.fragments.UserFragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers.Main
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    public var count = 0;
    companion object{
        const val CHANNEL_ID = "channel01"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if(count == 0){
            displayData()
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        addFragment(HomeFragment.newInstance())
        bottomNavigation.show(1)
        bottomNavigation.add(MeowBottomNavigation.Model(0, R.drawable.ic_notification))
        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_home))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_user))

        bottomNavigation.setOnClickMenuListener {
            when(it.id){
                0 -> {
                    replaceFragment(NotificationFragment.newInstance())
                }
                1 -> {
                    replaceFragment(HomeFragment.newInstance())
                }
                2 -> {
                    replaceFragment(UserFragment.newInstance())
                }
                else -> {
                    replaceFragment(HomeFragment.newInstance())
                }
            }
        }


    }

    @SuppressLint("SuspiciousIndentation")
    private fun replaceFragment(fragment:Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
            fragmentTransition.replace(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun addFragment(fragment:Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun showNotification(){
        createNotificationChannel()

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.memory)

        val date = Date()
        val notificationId = SimpleDateFormat("ddHHmmss", Locale.US).format(date).toInt()

        val mainIntent = Intent(this, MainActivity::class.java)
        mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val mainPendingIntent = PendingIntent.getActivity(this, 1, mainIntent, PendingIntent.FLAG_ONE_SHOT)


        val notificationBuilder = NotificationCompat.Builder(this, "$CHANNEL_ID")
        notificationBuilder.setSmallIcon(R.drawable.ic_notification)
        notificationBuilder.setContentTitle("Có sự cố xảy ra")
        notificationBuilder.setContentText("Phát hiện sự cố đóng cắt của tụ bù. Kiểm tra ngay!")
        notificationBuilder.priority = NotificationCompat.PRIORITY_HIGH
        notificationBuilder.setAutoCancel(true)
        notificationBuilder.setContentIntent(mainPendingIntent)
        notificationBuilder.setLargeIcon(bitmap)
        //notificationBuilder.setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(bitmap))

        val importance = NotificationManager.IMPORTANCE_HIGH
        val notificationManagerCompat = NotificationManagerCompat.from(this)
        notificationManagerCompat.notify(notificationId, notificationBuilder.build())
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name: CharSequence = "MyNotification"
            val description = "My notification channel description"

            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val notificationChannel = NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.description = description
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel((notificationChannel))
        }
    }

    private fun displayData(){
        val rootRef = FirebaseDatabase.getInstance().reference
        val testRef = rootRef.child("euphoria").orderByChild("TIME").limitToLast(1)
        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val stt1 = ds.child("Coil 1").getValue(String::class.java)
                    val stt2 = ds.child("Coil 2").getValue(String::class.java)
                    val stt3 = ds.child("Coil 3").getValue(String::class.java)
                    val stt4 = ds.child("Coil 4").getValue(String::class.java)
                    val stt5 = ds.child("Contactor1").getValue(String::class.java)
                    val stt6 = ds.child("Contactor2").getValue(String::class.java)
                    val stt7 = ds.child("Contactor3").getValue(String::class.java)
                    val stt8 = ds.child("Contactor4").getValue(String::class.java)

                    if(stt1 != stt5 || stt2 != stt6 || stt3 != stt7 || stt4 != stt8){
                        showNotification()
                        count++
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("error", databaseError.message)
            }
        }
        testRef.addValueEventListener(valueEventListener)
    }


}