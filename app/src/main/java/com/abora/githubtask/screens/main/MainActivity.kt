package com.abora.githubtask.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.abora.githubtask.R
import com.abora.githubtask.base.BaseActivity
import com.abora.githubtask.databinding.ActivityMainBinding
import com.abora.githubtask.utils.MyUtils.myToastAction2Button
import kotlin.reflect.KClass

class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {

    lateinit var navController: NavController

    override fun resourceId(): Int = R.layout.activity_main

    override fun viewModelClass(): KClass<MainViewModel> = MainViewModel::class

    override fun setUI(savedInstanceState: Bundle?) {
        navController = findNavController(R.id.hostFragment)


    }

    override fun observer() {

    }

    override fun clicks() {

    }

    override fun callApis() {

    }

    override fun onBackPressed() {
       if(navController.graph.startDestinationId == navController.currentDestination?.id) {
            myToastAction2Button("Do you want to exit the application?") { alertDialog ->
                alertDialog.dismiss()
                finishAffinity()
            }
        }else{
         super.onBackPressed()
           overridePendingTransition(R.anim.no_change, R.anim.slide_down)
        }
    }

}