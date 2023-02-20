package com.example.cricketapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.MenuProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.cricketapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var homeBinding : ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)
        setSupportActionBar(homeBinding.homeToolbar)

        val view = homeBinding.mainDrawerMenu.getHeaderView(0)
        val profileName: TextView = view.findViewById(R.id.profile_name)
        val profileButton: ImageView = view.findViewById(R.id.profile_image)

        profileButton.setOnClickListener {
//            Toast.makeText(this, profileName.text.toString(), Toast.LENGTH_LONG).show()
            navController.navigate(R.id.profileFragment)
            homeBinding.homeActivityDrawerLayout.closeDrawer(GravityCompat.START, true)
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.myTeamsFragment)
            , homeBinding.homeActivityDrawerLayout
        )

        homeBinding.homeToolbar.setupWithNavController(navController, appBarConfiguration)
        homeBinding.mainDrawerMenu.setupWithNavController(navController)

        addMenuProvider(object: MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_menu, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return menuItem.onNavDestinationSelected(navController)
            }

        })
    }
}