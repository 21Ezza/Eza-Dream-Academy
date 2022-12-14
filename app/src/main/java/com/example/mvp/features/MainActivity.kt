package com.example.mvp.features

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.example.mvp.databinding.ActivityMainBinding
import com.example.mvp.features.homepage.Home
import com.example.mvp.features.loginUi.LoginPresenter
import com.example.mvp.features.loginUi.LoginView
import com.example.mvp.features.regis.MainRegis

class MainActivity : AppCompatActivity(), LoginView {
    private lateinit var binding: ActivityMainBinding
    private val presenter = LoginPresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.onAttach(this)

        binding.button.setOnClickListener {
            presenter.validateCredential()


        }

        binding.lupa.setOnClickListener {
            startActivity(Intent(this, MainRegis::class.java))
        }

        binding.textInput.editText?.doOnTextChanged() { text, start, before, count ->
            validateInput()
            presenter.validateUser(
                binding.textInput.editText?.text.toString()
            )


        }

        binding.textPassword.editText?.doOnTextChanged { text, start, before, count ->
            validateInput()
            presenter.validatePassword(
                binding.textPassword.editText?.text.toString()
            )

        }

    }

    private fun validateInput() {
        binding.button.isEnabled =
            binding.textInput.editText?.text.toString().isNotBlank() && binding.textPassword.editText?.text.toString()
                .isNotBlank()
    }


    override fun onLoading() {
        binding.progressIndicator.isVisible = true
    }

    override fun onFinishedLoading() {
        binding.progressIndicator.isVisible = false
    }

    override fun onError(code: Int, message: String) {
        when (code) {
            1 -> binding.textInput.error = message
            2 -> binding.textPassword.error = message
            else -> binding.textInput.isErrorEnabled = false
        }

        /*AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("Ok", this::dialogClickListener)
            .setNegativeButton("Cancel", this::dialogClickListener)
            .create()
            .show()*/
//        binding.textPassword.error = "Password must contain Uppercase, Lowercase, Numbers, Special Character & At least 8 Character"
    }
    private fun dialogClickListener(dialogInterface: DialogInterface, button: Int) {
        when (button) {
            DialogInterface.BUTTON_NEGATIVE -> {}
            DialogInterface.BUTTON_POSITIVE -> {}
            DialogInterface.BUTTON_NEUTRAL -> {}
        }
    }

    override fun onSuccessLogin() {
        Toast.makeText(this, "Success Login", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this,Home::class.java))
    }

    override fun onErrorUser() {
        binding.textInput.isErrorEnabled = false
    }

    override fun onErrorPassword() {
        binding.textPassword.isErrorEnabled = false
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}