package com.example.mvp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), LoginFragmentInterface {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentTransaction: FragmentTransaction =  supportFragmentManager.beginTransaction()

        fragmentTransaction
            .add(binding.fragmentConstainer.id,LoginFragment()).commit()

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentConstainer.id, LoginFragment(), LoginFragment.TAG)
            .commit()

    }

    override fun onClickLogin() {
        Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show()
        getLoginFragment()?.typeToUsername("Budi")
    }

    override fun onClickForgot() {
        supportFragmentManager.beginTransaction()
            .add(binding.fragmentConstainer.id, RegisterFragment(), RegisterFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    private fun getLoginFragment(): LoginFragment? {
        val fragment = supportFragmentManager.findFragmentByTag(LoginFragment.TAG)
        return if (fragment != null) {
            fragment as LoginFragment
        } else {
            null
        }
    }

}