package com.example.localeatsdiscovery.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localeatsdiscovery.MainActivity
import com.example.localeatsdiscovery.R
import com.example.localeatsdiscovery.auth.SessionManager

class LoginActivity : ComponentActivity() {

    private val purple = Color(0xFF7446D9)
    private val lightPurple = Color(0xFFF0E9FF)
    private val darkText = Color(0xFF272238)
    private val greyText = Color(0xFF77727F)
    private val borderColor = Color(0xFFE4DFEA)

    /*
     * SharedPreferences
     *
     * This is suitable for this academic prototype.
     * It stores the demo username and password locally.
     */
    private val prefs by lazy {
        getSharedPreferences("local_eats_account", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // If the user is already logged in, go directly to MainActivity.
        if (SessionManager.isLoggedIn(this)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        setContent {
            LoginApp()
        }
    }

    @Composable
    private fun LoginApp() {

        var currentScreen by remember {
            mutableStateOf("login")
        }

        when (currentScreen) {

            "login" -> {
                LoginScreen(
                    onCreateAccount = {
                        currentScreen = "create"
                    },
                    onForgotPassword = {
                        currentScreen = "forgot"
                    }
                )
            }

            "create" -> {
                CreateAccountScreen(
                    onBack = {
                        currentScreen = "login"
                    },
                    onAccountCreated = {
                        currentScreen = "login"
                    }
                )
            }

            "forgot" -> {
                ForgotPasswordScreen(
                    onBack = {
                        currentScreen = "login"
                    }
                )
            }
        }
    }

    // =========================================================
    // LOGIN SCREEN
    // =========================================================

    @Composable
    private fun LoginScreen(
        onCreateAccount: () -> Unit,
        onForgotPassword: () -> Unit
    ) {

        // Default demo login details
        val defaultUsername = "s8193819"
        val defaultPassword = "Faysal123"

        /*
         * If the user has already created another account,
         * use that saved account.
         *
         * Otherwise use:
         * Username = s8193819
         * Password = Faysal123
         */
        var username by remember {
            mutableStateOf(
                prefs.getString("username", defaultUsername)
                    ?: defaultUsername
            )
        }

        var password by remember {
            mutableStateOf(
                prefs.getString("password", defaultPassword)
                    ?: defaultPassword
            )
        }

        var passwordVisible by remember {
            mutableStateOf(false)
        }

        var errorMessage by remember {
            mutableStateOf("")
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFFAF9FC)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(42.dp))

                Image(
                    painter = painterResource(
                        id = R.drawable.local_eats_logo
                    ),
                    contentDescription = "Local Eats Discovery Logo",
                    modifier = Modifier.size(118.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Welcome Back",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = darkText
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Sign in to continue to Local Eats Discovery",
                    fontSize = 13.sp,
                    color = greyText,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(26.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(22.dp)
                        )
                        .padding(22.dp)
                ) {

                    Text(
                        text = "Sign In",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = darkText
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    OutlinedTextField(
                        value = username,
                        onValueChange = {
                            username = it
                            errorMessage = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = {
                            Text("Username")
                        },
                        placeholder = {
                            Text("Enter your username")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Username"
                            )
                        },
                        shape = RoundedCornerShape(14.dp)
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            errorMessage = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = {
                            Text("Password")
                        },
                        placeholder = {
                            Text("Enter your password")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Password"
                            )
                        },
                        trailingIcon = {

                            IconButton(
                                onClick = {
                                    passwordVisible = !passwordVisible
                                }
                            ) {

                                Icon(
                                    imageVector = if (passwordVisible) {
                                        Icons.Default.VisibilityOff
                                    } else {
                                        Icons.Default.Visibility
                                    },
                                    contentDescription =
                                        if (passwordVisible) {
                                            "Hide password"
                                        } else {
                                            "Show password"
                                        }
                                )
                            }
                        },
                        visualTransformation =
                            if (passwordVisible) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        shape = RoundedCornerShape(14.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {

                        TextButton(
                            onClick = onForgotPassword
                        ) {

                            Text(
                                text = "Forgot password?",
                                color = purple,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    if (errorMessage.isNotEmpty()) {

                        Text(
                            text = errorMessage,
                            color = Color(0xFFD32F2F),
                            fontSize = 13.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                    Button(
                        onClick = {

                            val savedUsername =
                                prefs.getString(
                                    "username",
                                    defaultUsername
                                ) ?: defaultUsername

                            val savedPassword =
                                prefs.getString(
                                    "password",
                                    defaultPassword
                                ) ?: defaultPassword

                            if (
                                username.trim() == savedUsername &&
                                password == savedPassword
                            ) {

                                // Save login session
                                SessionManager.setLoggedIn(
                                    this@LoginActivity,
                                    true
                                )

                                Toast.makeText(
                                    this@LoginActivity,
                                    "Login successful",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent = Intent(
                                    this@LoginActivity,
                                    MainActivity::class.java
                                )

                                startActivity(intent)
                                finish()

                            } else {

                                errorMessage =
                                    "Invalid username or password."
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = purple,
                            contentColor = Color.White
                        )
                    ) {

                        Text(
                            text = "SIGN IN",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        androidx.compose.material3.HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = borderColor
                        )

                        Text(
                            text = "  OR  ",
                            fontSize = 11.sp,
                            color = greyText
                        )

                        androidx.compose.material3.HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = borderColor
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    /*
                     * Google button intentionally has no action.
                     */
                    OutlinedButton(
                        onClick = {
                            // Google Sign-In button is display-only
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White,
                            contentColor = darkText
                        )
                    ) {

                        Text(
                            text = "G",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Sign in with Google",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "Don't have an account?",
                            fontSize = 13.sp,
                            color = greyText
                        )

                        TextButton(
                            onClick = onCreateAccount
                        ) {

                            Text(
                                text = "Create Account",
                                color = purple,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Local Eats Discovery",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = purple
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "App Version 1.0.0",
                    fontSize = 10.sp,
                    color = greyText
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "Developed by Md Faysal Hossain",
                    fontSize = 10.sp,
                    color = greyText
                )

                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }

    // =========================================================
    // CREATE ACCOUNT SCREEN
    // =========================================================

    @Composable
    private fun CreateAccountScreen(
        onBack: () -> Unit,
        onAccountCreated: () -> Unit
    ) {

        var username by remember {
            mutableStateOf("")
        }

        var email by remember {
            mutableStateOf("")
        }

        var password by remember {
            mutableStateOf("")
        }

        var confirmPassword by remember {
            mutableStateOf("")
        }

        var passwordVisible by remember {
            mutableStateOf(false)
        }

        var confirmPasswordVisible by remember {
            mutableStateOf(false)
        }

        var message by remember {
            mutableStateOf("")
        }

        var error by remember {
            mutableStateOf(false)
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFFAF9FC)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        onClick = onBack
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = darkText
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = painterResource(
                        id = R.drawable.local_eats_logo
                    ),
                    contentDescription = "Local Eats Discovery Logo",
                    modifier = Modifier.size(88.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Create Account",
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    color = darkText
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Create your Local Eats Discovery account",
                    fontSize = 13.sp,
                    color = greyText,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(22.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.White,
                            RoundedCornerShape(22.dp)
                        )
                        .padding(22.dp)
                ) {

                    OutlinedTextField(
                        value = username,
                        onValueChange = {
                            username = it
                            message = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = {
                            Text("Username")
                        },
                        placeholder = {
                            Text("Choose a username")
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Email,
                                contentDescription = "Username"
                            )
                        },
                        shape = RoundedCornerShape(14.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            message = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = {
                            Text("Email")
                        },
                        placeholder = {
                            Text("Enter your email")
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Email,
                                contentDescription = "Email"
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        shape = RoundedCornerShape(14.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            message = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = {
                            Text("Password")
                        },
                        placeholder = {
                            Text("Create a password")
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Lock,
                                contentDescription = "Password"
                            )
                        },
                        trailingIcon = {

                            IconButton(
                                onClick = {
                                    passwordVisible = !passwordVisible
                                }
                            ) {

                                Icon(
                                    if (passwordVisible) {
                                        Icons.Default.VisibilityOff
                                    } else {
                                        Icons.Default.Visibility
                                    },
                                    contentDescription = "Show or hide password"
                                )
                            }
                        },
                        visualTransformation =
                            if (passwordVisible) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        shape = RoundedCornerShape(14.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                            message = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = {
                            Text("Confirm Password")
                        },
                        placeholder = {
                            Text("Enter password again")
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Lock,
                                contentDescription = "Confirm password"
                            )
                        },
                        trailingIcon = {

                            IconButton(
                                onClick = {
                                    confirmPasswordVisible =
                                        !confirmPasswordVisible
                                }
                            ) {

                                Icon(
                                    if (confirmPasswordVisible) {
                                        Icons.Default.VisibilityOff
                                    } else {
                                        Icons.Default.Visibility
                                    },
                                    contentDescription = "Show or hide password"
                                )
                            }
                        },
                        visualTransformation =
                            if (confirmPasswordVisible) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        shape = RoundedCornerShape(14.dp)
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    if (message.isNotEmpty()) {

                        Text(
                            text = message,
                            color = if (error) {
                                Color(0xFFD32F2F)
                            } else {
                                Color(0xFF2E7D32)
                            },
                            fontSize = 13.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                    Button(
                        onClick = {

                            when {

                                username.trim().isEmpty() -> {
                                    error = true
                                    message = "Please enter a username."
                                }

                                email.trim().isEmpty() -> {
                                    error = true
                                    message = "Please enter your email."
                                }

                                !email.contains("@") -> {
                                    error = true
                                    message = "Please enter a valid email."
                                }

                                password.length < 6 -> {
                                    error = true
                                    message =
                                        "Password must be at least 6 characters."
                                }

                                password != confirmPassword -> {
                                    error = true
                                    message =
                                        "Passwords do not match."
                                }

                                else -> {

                                    prefs.edit()
                                        .putString(
                                            "username",
                                            username.trim()
                                        )
                                        .putString(
                                            "email",
                                            email.trim()
                                        )
                                        .putString(
                                            "password",
                                            password
                                        )
                                        .apply()

                                    // Make sure newly created account
                                    // starts in logged-out state.
                                    SessionManager.setLoggedIn(
                                        this@LoginActivity,
                                        false
                                    )

                                    error = false

                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Account created successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    onAccountCreated()
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = purple,
                            contentColor = Color.White
                        )
                    ) {

                        Text(
                            text = "CREATE ACCOUNT",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "App Version 1.0.0",
                    fontSize = 10.sp,
                    color = greyText
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "Developed by Md Faysal Hossain",
                    fontSize = 10.sp,
                    color = greyText
                )

                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }

    // =========================================================
    // FORGOT PASSWORD SCREEN
    // =========================================================

    @Composable
    private fun ForgotPasswordScreen(
        onBack: () -> Unit
    ) {

        var username by remember {
            mutableStateOf("")
        }

        var email by remember {
            mutableStateOf("")
        }

        var newPassword by remember {
            mutableStateOf("")
        }

        var confirmPassword by remember {
            mutableStateOf("")
        }

        var passwordVisible by remember {
            mutableStateOf(false)
        }

        var confirmPasswordVisible by remember {
            mutableStateOf(false)
        }

        var message by remember {
            mutableStateOf("")
        }

        var error by remember {
            mutableStateOf(false)
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFFAF9FC)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        onClick = onBack
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = darkText
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Image(
                    painter = painterResource(
                        id = R.drawable.local_eats_logo
                    ),
                    contentDescription = "Local Eats Discovery Logo",
                    modifier = Modifier.size(88.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Reset Password",
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    color = darkText
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Set a new password for your account",
                    fontSize = 13.sp,
                    color = greyText,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.White,
                            RoundedCornerShape(22.dp)
                        )
                        .padding(22.dp)
                ) {

                    OutlinedTextField(
                        value = username,
                        onValueChange = {
                            username = it
                            message = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = {
                            Text("Username")
                        },
                        placeholder = {
                            Text("Enter your username")
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Email,
                                contentDescription = "Username"
                            )
                        },
                        shape = RoundedCornerShape(14.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            message = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = {
                            Text("Email")
                        },
                        placeholder = {
                            Text("Enter your registered email")
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Email,
                                contentDescription = "Email"
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        shape = RoundedCornerShape(14.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedTextField(
                        value = newPassword,
                        onValueChange = {
                            newPassword = it
                            message = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = {
                            Text("New Password")
                        },
                        placeholder = {
                            Text("Enter new password")
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Lock,
                                contentDescription = "New password"
                            )
                        },
                        trailingIcon = {

                            IconButton(
                                onClick = {
                                    passwordVisible =
                                        !passwordVisible
                                }
                            ) {

                                Icon(
                                    if (passwordVisible) {
                                        Icons.Default.VisibilityOff
                                    } else {
                                        Icons.Default.Visibility
                                    },
                                    contentDescription =
                                        "Show or hide password"
                                )
                            }
                        },
                        visualTransformation =
                            if (passwordVisible) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        shape = RoundedCornerShape(14.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                            message = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = {
                            Text("Confirm Password")
                        },
                        placeholder = {
                            Text("Enter password again")
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Lock,
                                contentDescription = "Confirm password"
                            )
                        },
                        trailingIcon = {

                            IconButton(
                                onClick = {
                                    confirmPasswordVisible =
                                        !confirmPasswordVisible
                                }
                            ) {

                                Icon(
                                    if (confirmPasswordVisible) {
                                        Icons.Default.VisibilityOff
                                    } else {
                                        Icons.Default.Visibility
                                    },
                                    contentDescription =
                                        "Show or hide password"
                                )
                            }
                        },
                        visualTransformation =
                            if (confirmPasswordVisible) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        shape = RoundedCornerShape(14.dp)
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    if (message.isNotEmpty()) {

                        Text(
                            text = message,
                            color = if (error) {
                                Color(0xFFD32F2F)
                            } else {
                                Color(0xFF2E7D32)
                            },
                            fontSize = 13.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                    Button(
                        onClick = {

                            val savedUsername =
                                prefs.getString("username", null)

                            val savedEmail =
                                prefs.getString("email", null)

                            when {

                                savedUsername == null -> {
                                    error = true
                                    message =
                                        "No account found. Please create an account first."
                                }

                                username.trim() != savedUsername -> {
                                    error = true
                                    message =
                                        "Username does not match our records."
                                }

                                savedEmail != null &&
                                        email.trim() != savedEmail -> {
                                    error = true
                                    message =
                                        "Email does not match our records."
                                }

                                newPassword.length < 6 -> {
                                    error = true
                                    message =
                                        "New password must be at least 6 characters."
                                }

                                newPassword != confirmPassword -> {
                                    error = true
                                    message =
                                        "Passwords do not match."
                                }

                                else -> {

                                    prefs.edit()
                                        .putString(
                                            "password",
                                            newPassword
                                        )
                                        .apply()

                                    // Password reset means the user
                                    // must sign in again.
                                    SessionManager.setLoggedIn(
                                        this@LoginActivity,
                                        false
                                    )

                                    error = false
                                    message =
                                        "Password reset successfully."

                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Password updated successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = purple,
                            contentColor = Color.White
                        )
                    ) {

                        Text(
                            text = "RESET PASSWORD",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    TextButton(
                        onClick = onBack,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(
                            text = "Back to Sign In",
                            color = purple,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "App Version 1.0.0",
                    fontSize = 10.sp,
                    color = greyText
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "Developed by Md Faysal Hossain",
                    fontSize = 10.sp,
                    color = greyText
                )

                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}