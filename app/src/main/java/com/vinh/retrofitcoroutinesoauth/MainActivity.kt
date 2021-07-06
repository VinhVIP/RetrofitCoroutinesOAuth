package com.vinh.retrofitcoroutinesoauth

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vinh.retrofitcoroutinesoauth.data.models.User
import com.vinh.retrofitcoroutinesoauth.data.requests.LoginRequest
import com.vinh.retrofitcoroutinesoauth.data.responses.LoginResponse
import com.vinh.retrofitcoroutinesoauth.databinding.ActivityMainBinding
import com.vinh.retrofitcoroutinesoauth.repository.Repository
import com.vinh.retrofitcoroutinesoauth.utils.SessionManager
import com.vinh.retrofitcoroutinesoauth.viewmodels.MainViewModel
import com.vinh.retrofitcoroutinesoauth.viewmodels.MainViewModelFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var sessionManager: SessionManager

    // KTX
    // ViewModel using Factory
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(Repository(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        viewModel.loginResponse.observe(this, { response ->
            if (response == null) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("DMM", response.code().toString())

                if (response.isSuccessful) {
                    val loginResponse: LoginResponse = response.body()!!.data
                    binding.textView.text = loginResponse.accessToken
                    sessionManager.saveAuthToken(loginResponse.accessToken)

                    viewModel.getProfile()
                } else {
                    binding.textView.text = response.code().toString()
                }
            }
        })

        viewModel.profile.observe(this, { response ->
            Log.d("DMM", response.code().toString())

            if (response.isSuccessful) {
                val user: User = response.body()!!.data
                binding.textView.text = "${user.firstName} ${user.lastName}"
            } else {
                binding.textView.text = response.code().toString()
            }
        })

        binding.btnLogin.setOnClickListener {
//            val email = binding.edEmail.text.toString()
//            val pass = binding.edPass.text.toString()
            val email = "vinhabc@gmail.com"
            val pass = "123456"

            binding.textView.text = "Logging..."
            viewModel.login(LoginRequest(email, pass));

        }

        binding.btnRandom.setOnClickListener {
            val random = Random()
            binding.textView.text = "Random: ${random.nextInt() % 100}"
        }

    }
}