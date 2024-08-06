package com.jadima.mvvmlogin.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jadima.mvvmlogin.R
import kotlinx.coroutines.launch

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginPreview(){
    LoginScreen(viewModel = LoginViewModel())
}

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            //.height(IntrinsicSize.Max)
            .padding(16.dp)
    ) {
        Login(viewModel)
    }
}

@Composable
fun Login(viewModel: LoginViewModel) {
    val email: String by viewModel.email.observeAsState("")
    val password: String by viewModel.password.observeAsState("")
    val btnLoginEnabled: Boolean by viewModel.loginEnabled.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()
    val loading: Boolean by viewModel.loading.observeAsState(initial=false)

    if (loading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }

        return
    }

    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HeaderIcon()
        Spacer(modifier = Modifier.padding(16.dp))
        EmailField(email) {
          viewModel.onLoginChanged(email = it, password = password)
        }
        Spacer(modifier = Modifier.padding(5.dp))
        PasswordField(password) {
            viewModel.onLoginChanged(email = email, password = it)
        }
        Spacer(Modifier.padding(5.dp))
        LoginButton(btnLoginEnabled) {
            coroutineScope.launch {
                viewModel.onLoginClicked()
            }
        }
    }
}

@Composable
fun LoginButton(loginEnabled: Boolean, onLoginClicked: () -> Unit) {
    Button(
        onClick = { onLoginClicked() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = MaterialTheme.shapes.small,
        enabled = loginEnabled
    ) {
        Text("Log in")
    }
}

@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = password, onValueChange = { onTextFieldChanged(it) },
        placeholder = { Text("Contraseña") },
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            capitalization = KeyboardCapitalization.Sentences),
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}

@Composable
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = email, onValueChange = { input -> onTextFieldChanged(input) },
        placeholder = { Text("Email") },
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}

@Composable
fun HeaderIcon() {
    Image(
        painter = painterResource(id = R.drawable.baseline_account_circle_24),
        contentDescription = "Logo",
        modifier = Modifier.size(120.dp)
    )
}
