package com.example.diploma_project

import android.os.Bundle
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.findNavController
import com.example.diploma_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        val navController = navHostFragment!!.findNavController()

        val popupMenu = PopupMenu(this,null)
        popupMenu.inflate(R.menu.bottom_nav)
        binding.bottomBar.setupWithNavController(popupMenu.menu, navController)

        binding.bottomBar.onItemSelected = {
            when(it){
                0 -> {
                    i = 0;
                    navController.navigate(R.id.homeFragment)

                }
                1 -> i = 1
                2 -> i = 2

                }
            }
        }

    override fun onBackPressed() {
        super.onBackPressed()
        if (i == 0){
            finish()
        }
    }
//        navController.addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener{
//            override fun onDestinationChanged(
//                controller: NavController,
//                destination: NavDestination,
//                arguments: Bundle?
//            ) {
//                title = when(destination.id){
//                    R.id.cartFragment -> "My Cart"
//                    R.id.moreFragment -> "My Dashboard"
//                    else -> "Diploma_Proj.."
//                }
//            }
//        })
//
//    }
}