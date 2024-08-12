package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.domain.EmailValidType
import nextstep.signup.domain.EmailValidator
import nextstep.signup.domain.PasswordConfirmValidType
import nextstep.signup.domain.PasswordConfirmValidator
import nextstep.signup.domain.PasswordValidType
import nextstep.signup.domain.PasswordValidator
import nextstep.signup.domain.UsernameValidType
import nextstep.signup.domain.UsernameValidator
import nextstep.signup.ui.component.EmailTextField
import nextstep.signup.ui.component.PasswordConfirmTextField
import nextstep.signup.ui.component.PasswordTextField
import nextstep.signup.ui.component.UsernameTextField
import nextstep.signup.ui.theme.Blue50

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onShowSnackbar: (message: String) -> Unit,
) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }
    val enabled by remember(username, email, password, passwordConfirm) {
        val enabled = UsernameValidator.match(username) == UsernameValidType.VALID &&
                EmailValidator.match(email) == EmailValidType.VALID &&
                PasswordValidator.match(password) == PasswordValidType.VALID &&
                PasswordConfirmValidator.match(password, passwordConfirm) == PasswordConfirmValidType.VALID
        mutableStateOf(enabled)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            color = Color.Black,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 60.dp),
        )

        UsernameTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        )

        EmailTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        )

        PasswordTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        )

        PasswordConfirmTextField(
            value = passwordConfirm,
            passwordValue = password,
            onValueChange = { passwordConfirm = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        )

        Button(
            onClick = { onShowSnackbar(context.getString(R.string.success_sign_up)) },
            colors = ButtonDefaults.buttonColors(containerColor = Blue50),
            enabled = enabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .height(50.dp),
        ) {
            Text(text = stringResource(id = R.string.sign_up))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(onShowSnackbar = {})
}