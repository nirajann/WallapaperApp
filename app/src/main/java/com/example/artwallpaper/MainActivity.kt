package com.example.artwallpaper


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.artwallpaper.databinding.ActivityMainBinding
import com.example.artwallpaper.fragments.DownloadFragment
import com.example.artwallpaper.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding:  ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.coolPinkNav)

        binding = ActivityMainBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.root)


//        for nav drawer
        toggle = ActionBarDrawerToggle(this, binding.root,R.string.open,R.string.close)
        binding.root.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        replaceFragment(HomeFragment())

        binding.icHome.setOnClickListener{
            replaceFragment(HomeFragment())
        }

        binding.navview.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.navFeedback -> Toast.makeText(baseContext, "Feedback", Toast.LENGTH_SHORT).show()
                R.id.navSetting -> Toast.makeText(baseContext, "Setting", Toast.LENGTH_SHORT).show()
                R.id.navAbout -> Toast.makeText(baseContext, "About", Toast.LENGTH_SHORT).show()
                R.id.navExit -> finishAffinity()
            }
            true
        }


        binding.icDownload.setOnClickListener{
            replaceFragment(DownloadFragment())
        }



    }
    private fun replaceFragment(fragment:Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragementReplace, fragment)
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }



}