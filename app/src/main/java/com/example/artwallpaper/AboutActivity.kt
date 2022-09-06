package com.example.artwallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.artwallpaper.Adapter.animeAdapter
import com.example.artwallpaper.Model.AboutModel
import com.example.artwallpaper.databinding.ActivityAboutBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.core.DatabaseInfo

class AboutActivity : AppCompatActivity() {
    lateinit var binding: ActivityAboutBinding
    private lateinit var locationTextView: TextView
    private lateinit var reference: DatabaseInfo


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityAboutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()
        val uid = intent.getStringExtra("uid")
        val name = intent.getStringExtra("name")

        db.collection("Animelist").document(uid!!).collection("about")
            .addSnapshotListener { value, error ->
                val listOfaboutAnime = arrayListOf<AboutModel>()
                val data=value?.toObjects(AboutModel::class.java)
                listOfaboutAnime.addAll(data!!)

                binding.animeTitle.text = name.toString()


                binding.rcvAnime.layoutManager = StaggeredGridLayoutManager(1,
                    StaggeredGridLayoutManager.VERTICAL)
                binding.rcvAnime.adapter = animeAdapter(this,listOfaboutAnime)

    }
}
}