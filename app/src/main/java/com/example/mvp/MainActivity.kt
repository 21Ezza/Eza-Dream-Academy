package com.example.mvp

import android.content.Intent
import android.hardware.usb.UsbEndpoint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp.data.model.User
import com.example.mvp.data.network.NetworkClient
import com.example.mvp.data.network.api.ReqresApi
import com.example.mvpapplication.data.network.ResponseStatus
import com.example.mvp.databinding.ActivityMainBinding
import com.example.mvp.network.api.UserApi
import com.example.mvp.network.model.UserPagination
import com.example.okhttp.UserAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val networkClient: NetworkClient = NetworkClient()
    private val api: ReqresApi = ReqresApi()
    private val adapter: UserAdapter by lazy { UserAdapter() }
    private val recipeLiveData = MutableLiveData<List<User>>(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSyncCall.setOnClickListener {
            UserApi().getUsers { UserPagination, error ->
                CoroutineScope(Dispatchers.Main).launch {
                    if (error == null){
                        binding.tvHasil.text = UserPagination?.data?.joinToString("\n") { it.email }
                    } else {
                        AlertDialog
                            .Builder(this@MainActivity)
                            .setMessage(error.message)
                            .create().show()
                    }
                }
            }
        }

        binding.rvUser.apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        recipeLiveData.observe(this) {
            adapter.submitList(it)
        }

        binding.btnAsyncCall.setOnClickListener {
            showProgress(true)
            api.getUserPagination {
                when (it) {
                    is ResponseStatus.SuccessPagination -> {
                        recipeLiveData.postValue(it.data)
                    }
                    is ResponseStatus.Failed -> {
                        Log.e("MainActivity", it.message)
                    }
                    else -> {}
                }
                showProgress(false)
            }
        }

        adapter.setOnCardClickListener(rvListener)
    }

    private val rvListener: (firstName: String, lastName: String, email: String, avatar: String) -> Unit =
        { firstName, lastName, email, avatar ->
            startActivity(Intent(this, Detail::class.java).apply {
                putExtra("firstName", firstName)
                putExtra("lastName", lastName)
                putExtra("email", email)
                putExtra("avatar", avatar)
            })
        }

    private fun showProgress(isShown: Boolean) {
        CoroutineScope(Dispatchers.Main).launch {
            binding.progressIndicator.isVisible = isShown
        }
    }
}