package com.example.artwallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.artwallpaper.Adapter.catImageAdapter
import com.example.artwallpaper.Model.BomModel
import com.example.artwallpaper.databinding.ActivityCategoryBinding
import com.google.firebase.firestore.FirebaseFirestore

class CategoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()
        val uid = intent.getStringExtra("uid")
        val name = intent.getStringExtra("name")


        db.collection("Categories").document(uid!!).collection("OnePiecewallpapaper")
            .addSnapshotListener { value, error ->
                val listOfCategorywallpaper = arrayListOf<BomModel>()
                val data=value?.toObjects(BomModel::class.java)
                listOfCategorywallpaper.addAll(data!!)

                binding.catTitle.text = name.toString()
                binding.catCount.text = "${listOfCategorywallpaper.size.toString()} wallpaper Availabel"

                binding.rcvCategory.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                binding.rcvCategory.adapter = catImageAdapter(this,listOfCategorywallpaper)
        }
    }
}