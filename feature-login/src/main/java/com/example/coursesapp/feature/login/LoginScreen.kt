package com.example.coursesapp.feature.login

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coursesapp.core.ui.theme.BackgroundDark
import com.example.coursesapp.core.ui.theme.DisabledGreen
import com.example.coursesapp.core.ui.theme.InputBackground
import com.example.coursesapp.core.ui.theme.OkOrange
import com.example.coursesapp.core.ui.theme.PrimaryGreen
import com.example.coursesapp.core.ui.theme.TextHint
import com.example.coursesapp.core.ui.theme.TextPrimary
import com.example.coursesapp.core.ui.theme.TextSecondary
import com.example.coursesapp.core.ui.theme.Transparent
import com.example.coursesapp.core.ui.theme.VkBlue

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.3f))

        Text(
            text = "Вход",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = state.email,
            onValueChange = { viewModel.onEmailChanged(it) },
            placeholder = { Text("Email", color = TextHint) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = InputBackground,
                unfocusedContainerColor = InputBackground,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                cursorColor = PrimaryGreen,
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent
            ),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = state.password,
            onValueChange = { viewModel.onPasswordChanged(it) },
            placeholder = { Text("Пароль", color = TextHint) },
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = InputBackground,
                unfocusedContainerColor = InputBackground,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                cursorColor = PrimaryGreen,
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent
            ),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onLoginSuccess,
            enabled = state.isButtonEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryGreen,
                disabledContainerColor = DisabledGreen
            ),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text(
                text = "Войти",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Нету аккаунта? ",
                fontSize = 14.sp,
                color = TextSecondary
            )
            Text(
                text = "Регистрация",
                fontSize = 14.sp,
                color = PrimaryGreen,
                modifier = Modifier.clickable { }
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .width(1.dp)
                    .height(16.dp)
                    .background(TextHint)
            )

            Text(
                text = "Забыли пароль?",
                fontSize = 14.sp,
                color = PrimaryGreen,
                modifier = Modifier.clickable { }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/")))
                },
                colors = ButtonDefaults.buttonColors(containerColor = VkBlue),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .width(120.dp)
                    .height(44.dp)
            ) {
                Text("VK", fontWeight = FontWeight.Bold, color = TextPrimary, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = {
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://ok.ru/")))
                },
                colors = ButtonDefaults.buttonColors(containerColor = OkOrange),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .width(120.dp)
                    .height(44.dp)
            ) {
                Text("OK", fontWeight = FontWeight.Bold, color = TextPrimary, fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.weight(0.7f))
    }
}
