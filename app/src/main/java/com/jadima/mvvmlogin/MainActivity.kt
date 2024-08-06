package com.jadima.mvvmlogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jadima.mvvmlogin.ui.login.ui.LoginScreen
import com.jadima.mvvmlogin.ui.login.ui.LoginViewModel
import com.jadima.mvvmlogin.ui.theme.MVVMLoginTheme


class MainActivity : ComponentActivity() {
    private val loginViewModel = LoginViewModel() //se puede injectar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVVMLoginTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(loginViewModel)
                }
            }
        }
    }
}