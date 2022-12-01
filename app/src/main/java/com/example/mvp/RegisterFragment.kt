package com.example.mvp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.mvp.databinding.LayoutRegisterBinding

class RegisterFragment:Fragment() {
    companion object {
        const val TAG = "RegisterFragment"
    }
    private lateinit var binding: LayoutRegisterBinding
    private var registerInterface: FragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentListener) {
            registerInterface = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = LayoutRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSelanjutnya.setOnClickListener {
            registerInterface?.onClickConfirm()
            val editText: Editable? = binding.iKataSandi.editText?.text
            val input = editText.toString()
            val bundle = Bundle()
            bundle.putString("data", input)

            val fragment = LoginFragment()
            fragment.arguments = bundle
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentConstainer, LoginFragment(), LoginFragment.TAG)
            requireActivity().supportFragmentManager.popBackStack()

        }
    }

    interface FragmentListener {
        fun onClickConfirm()
    }
}