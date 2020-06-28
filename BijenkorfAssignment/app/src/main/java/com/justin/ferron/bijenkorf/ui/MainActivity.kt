package com.justin.ferron.bijenkorf.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.justin.ferron.bijenkorf.R
import com.justin.ferron.bijenkorf.ui.home.HomeScreenFragment
import com.justin.ferron.bijenkorf.ui.product.list.ProductListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = HomeScreenFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainLayout, fragment)
        transaction.commit()
    }
}