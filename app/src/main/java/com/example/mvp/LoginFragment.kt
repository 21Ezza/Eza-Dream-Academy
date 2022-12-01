package com.example.mvp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fragments.databinding.LayoutLoginBinding

class LoginFragment:Fragment() {
    companion object {
        const val TAG = "LoginFragment"

        fun newInstance(data: String): LoginFragment {
            val fragment = LoginFragment()
            val bundle = Bundle().apply {
                putString("key", data)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
    private var inputText: String? = ""
    private lateinit var binding: LayoutLoginBinding
    private var loginInterface: LoginFragmentInterface? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LoginFragmentInterface) {
            loginInterface = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutLoginBinding.inflate(layoutInflater, container, false)
        return binding.root

        inputText = arguments?.getString("data")

        val outputTextView = binding.textPassword?.editText?.text.toString()
        outputTextView = inputText?.toString().toString()

       /* val textView: Editable? = binding.passwordID.text

        val args = this.arguments
        val inputData = args?.get("data")
        textView = inputData.toString()
        return view
*/
    }

    fun typeToUsername(string: String) {
        binding.textInput.editText?.setText(string)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            loginInterface?.onClickLogin()
        }

        binding.tvForgotPass.setOnClickListener {
            loginInterface?.onClickForgot()
        }

    }

}