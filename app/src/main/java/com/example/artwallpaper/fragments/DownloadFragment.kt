package com.example.artwallpaper.fragments

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artwallpaper.Adapter.BomAdapter
import com.example.artwallpaper.Adapter.animeAdapter
import com.example.artwallpaper.Adapter.categoryAdapter
import com.example.artwallpaper.Model.BomModel
import com.example.artwallpaper.Model.categoryModel
import com.example.artwallpaper.databinding.FragmentDownloadBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.io.File


class DownloadFragment : Fragment() {

    lateinit var binding: FragmentDownloadBinding
    lateinit var db: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val allFiles: Array<File>
        val imageList = arrayListOf<String>()
        val targetPath =
            Environment.getExternalStorageDirectory().absolutePath + "/Pictures/Amoled Wallpaper"
        val targetFile = File(targetPath)
        allFiles =  targetFile.listFiles()!!
        for (data in allFiles) {
            imageList.add(data.absolutePath)
        }
        binding = FragmentDownloadBinding.inflate(layoutInflater, container, false)

        db = FirebaseFirestore.getInstance()
        db.collection("Animelist").addSnapshotListener { value, error ->
            val listOfCategory = arrayListOf<categoryModel>()
            val data=value?.toObjects(categoryModel::class.java)
            listOfCategory.addAll(data!!)

            binding.rcvAnime.layoutManager = GridLayoutManager(requireContext(),2 )
            binding.rcvAnime.adapter = animeAdapter(requireContext(),listOfCategory)
        }


        return binding.root


    }







}