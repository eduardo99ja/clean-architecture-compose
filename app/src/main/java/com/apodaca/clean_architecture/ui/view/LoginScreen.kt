package com.apodaca.clean_architecture.ui.view


import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.apodaca.clean_architecture.R
import com.apodaca.clean_architecture.ui.view.components.LoadingDialog
import com.apodaca.clean_architecture.ui.view.components.RoundedButton
import com.apodaca.clean_architecture.ui.view.components.TransparentTextField
import com.apodaca.clean_architecture.ui.viewmodel.LoginViewModel



@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun LoginScreen(
    navController: NavController = rememberNavController(),
    viewModel: LoginViewModel = hiltViewModel()
) {
//    val user by viewModel.user.observeAsState()
//    if (user != null) LaunchedEffect(user) {
//        Token.token = user?.apiToken ?: ""
//        Prefs.userId = user!!.id!!
//        navController.navigate(Screens.SCREEN_HOME) {
//            this.launchSingleTop = true
//        }
//
//    }
    val owner= LocalLifecycleOwner.current
    val context = LocalContext.current

    val isLoading by viewModel.isLoading.observeAsState()
    val user by viewModel.user.observeAsState()
    val error by viewModel.error.observeAsState()

    if (isLoading == true) {
        LoadingDialog()
    }
    if (user != null) {
        LaunchedEffect(user) {
            Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
        }
    }
    if(error != null){
        LaunchedEffect(error) {
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            viewModel.error.value = null
        }
    }

    LoginScreenContent(navController = navController, viewModel = viewModel)


}

@Composable
fun LoginScreenContent(navController: NavController, viewModel: LoginViewModel) {

    val emailValue = rememberSaveable { viewModel.email }
    val passwordValue = rememberSaveable { viewModel.password }
    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_login),
            contentDescription = "Login Image",
            contentScale = ContentScale.Inside
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            ConstraintLayout {

                val (surface, fab) = createRefs()

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .constrainAs(surface) {
                            bottom.linkTo(parent.bottom)
                        },
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStartPercent = 8,
                        topEndPercent = 8
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "Inicio de sesión",
                            style = MaterialTheme.typography.h4.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            TransparentTextField(
                                textFieldValue = emailValue.value,
                                textLabel = "Correo electrónico",
                                keyboardType = KeyboardType.Email,
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }
                                ),
                                imeAction = ImeAction.Next
                            ) {
                                viewModel.setEmail(it)
                            }

                            TransparentTextField(
                                textFieldValue = passwordValue.value,
                                textLabel = "Contraseña",
                                keyboardType = KeyboardType.Password,
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()

                                        viewModel.loginClicked(email = emailValue.value, password = passwordValue.value)
                                    }
                                ),
                                imeAction = ImeAction.Done,
                                trailingIcon = {
                                    IconButton(
                                        onClick = {
                                            passwordVisibility = !passwordVisibility
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (passwordVisibility) {
                                                Icons.Default.Visibility
                                            } else {
                                                Icons.Default.VisibilityOff
                                            },
                                            contentDescription = "Toggle Password Icon"
                                        )
                                    }
                                },
                                visualTransformation = if (passwordVisibility) {
                                    VisualTransformation.None
                                } else {
                                    PasswordVisualTransformation()
                                }
                            ) {
                                viewModel.setPassword(it)
                            }

                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "¿Olvidaste tu contraseña?",
                                style = MaterialTheme.typography.body1,
                                textAlign = TextAlign.End
                            )
                        }

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            RoundedButton(
                                text = "Iniciar sesión",
                                displayProgressBar = false,
                                onClick = {
                                    viewModel.loginClicked(email = emailValue.value, password = passwordValue.value)
                                }
                            )

                            ClickableText(
                                text = buildAnnotatedString {
                                    append("¿Aun no tienes cuenta? ")

                                    withStyle(
                                        style = SpanStyle(
                                            color = MaterialTheme.colors.primary,
                                            fontWeight = FontWeight.Bold
                                        )
                                    ) {
                                        append("Registrate aquí")
                                    }
                                }
                            ) {
//                                navController.navigate(Screens.SCREEN_REGISTER) {
//                                    this.launchSingleTop = true
//                                }
                            }
                        }
                        OnLoggedUser(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                }


            }
        }
    }
}


@Composable
fun OnLoggedUser(
    navController: NavController,
    viewModel: LoginViewModel
) {
//    val result by remember { viewModel.loggedUser }
//    val context = LocalContext.current
//    when (result) {
//        is NetworkResult.Success -> {
//            if (result?.data?.success == true) LaunchedEffect(result) {
//
//                navController.navigate(Screens.SCREEN_HOME) {
//                    this.launchSingleTop = true
//                }
//            } else LaunchedEffect(result) {
//                Toast.makeText(context, result?.data?.message ?: "", Toast.LENGTH_SHORT).show()
//            }
//        }
//        is NetworkResult.Error -> {
//
//            Toast.makeText(context, stringResource(id = R.string.err), Toast.LENGTH_LONG).show()
//
//
//        }
//        is NetworkResult.Loading -> {
//            LoadingDialog()
//        }
//    }
}





