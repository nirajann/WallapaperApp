package com.example.artwallpaper.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artwallpaper.Adapter.BomAdapter
import com.example.artwallpaper.Adapter.categoryAdapter
import com.example.artwallpaper.Adapter.colortoneAdapter
import com.example.artwallpaper.Model.BomModel
import com.example.artwallpaper.Model.categoryModel
import com.example.artwallpaper.Model.colortoneModel
import com.example.artwallpaper.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var db: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(layoutInflater,container,false)
        db = FirebaseFirestore.getInstance()
        db.collection("bestofthemonth").addSnapshotListener { value, error ->
            val listBestOfMonth = arrayListOf<BomModel>()
            val data=value?.toObjects(BomModel::class.java)
            listBestOfMonth.addAll(data!!)

            binding.rcvBom.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)
            binding.rcvBom.adapter = BomAdapter(requireContext(),listBestOfMonth)
        }

        db.collection("thecolortone").addSnapshotListener { value, error ->
            val ListTheColorTone = arrayListOf<colortoneModel>()
            val data=value?.toObjects(colortoneModel::class.java)
            ListTheColorTone.addAll(data!!)

            binding.rcvTct.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)
            binding.rcvTct.adapter = colortoneAdapter(requireContext(),ListTheColorTone)
        }

        db.collection("Categories").addSnapshotListener { value, error ->
            val listOfCategory = arrayListOf<categoryModel>()
            val data=value?.toObjects(categoryModel::class.java)
            listOfCategory.addAll(data!!)

            binding.rcvCat.layoutManager = GridLayoutManager(requireContext(),2 )
            binding.rcvCat.adapter = categoryAdapter(requireContext(),listOfCategory)
        }


        return binding.root

    }


}