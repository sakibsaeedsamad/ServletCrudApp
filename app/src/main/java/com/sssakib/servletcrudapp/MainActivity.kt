package com.sssakib.servletcrudapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.sssakib.servletcrudapp.model.LoginModel
import com.sssakib.servletcrudapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        btnLogin.setOnClickListener {
            doLogin()
        }
        observeViewModel()


    }

    fun observeViewModel() {
        userViewModel.login_response_error.observe(this, androidx.lifecycle.Observer {
            it?.let {


                Log.e("Login-->", "Error Found")

            }
        })
    }

    private fun doLogin() {

        var model = LoginModel()
        model.requestCode = ("1")
        model.phone = etPhoneNoLogin.text.toString()
        model.password = etPasswordLogin.text.toString()
        this.let { it1 -> userViewModel.doLogin(model) }
    }
}