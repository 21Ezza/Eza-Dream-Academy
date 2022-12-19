package com.example.mvp.features

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.example.mvp.data.api.CredentialApi
import com.example.mvp.data.api.CredentialLoginApi
import com.example.mvp.data.api.UserApi
import com.example.mvp.data.model.UserPagination
import com.example.mvp.databinding.ActivityMainBinding
import com.example.mvp.features.homepage.Home
import com.example.mvp.features.loginUi.LoginPresenter
import com.example.mvp.features.loginUi.LoginView
import com.example.mvp.features.regis.MainRegis

class MainActivity : AppCompatActivity(), LoginView {
    private lateinit var binding: ActivityMainBinding
    private val presenter = LoginPresenter(CredentialLoginApi(), UserApi())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.onAttach(this)

        binding.button.setOnClickListener {
            presenter.validateCredential(
                binding.textInput.editText?.text.toString(),
                binding.textPassword.editText?.text.toString()
            )


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
            else -> {
                AlertDialog.Builder(this)
                    .setMessage("code: $code, $message")
                    .setPositiveButton("Ok", this::dialogClickListener)
                    .setNegativeButton("Cancel", this::dialogClickListener)
                    .create()
                    .show()
            }
        }

    }
    private fun dialogClickListener(dialogInterface: DialogInterface, button: Int) {
        when (button) {
            DialogInterface.BUTTON_NEGATIVE -> {}
            DialogInterface.BUTTON_POSITIVE -> {}
            DialogInterface.BUTTON_NEUTRAL -> {}
        }
    }


    override fun onSuccessLogin(username: String, password: String) {
        presenter.register(username, password)
    }

    override fun onErrorUser() {
        binding.textInput.isErrorEnabled = false
    }

    override fun onErrorPassword() {
        binding.textPassword.isErrorEnabled = false
    }

    override fun onSuccessGetUser(user: UserPagination) {
        AlertDialog.Builder(this)
            .setMessage("user -> $user")
            .setPositiveButton("Ok", this::dialogClickListener)
            .setNegativeButton("Cancel", this::dialogClickListener)
            .create()
            .show()
    }

    override fun onSuccessRegister() {
        Toast.makeText(this, "Success Login", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this,Home::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}