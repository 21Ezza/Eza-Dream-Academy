package com.example.mvp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.example.mvp.databinding.ActivityMainBinding
import com.example.mvp.features.LoginPresenter
import com.example.mvp.features.LoginView

class MainActivity : AppCompatActivity(), LoginView {
    private lateinit var binding: ActivityMainBinding
    private val presenter = LoginPresenter()
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
        binding.textInput.editText?.doOnTextChanged() { text, start, before, count ->
            validateInput()
        }

        binding.textPassword.editText?.doOnTextChanged { text, start, before, count ->
            validateInput()
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

    override fun onError(message: String) {
            binding.textPassword.error = message


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
        binding.textPassword.isErrorEnabled = false
        Toast.makeText(this, "Success Login", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}