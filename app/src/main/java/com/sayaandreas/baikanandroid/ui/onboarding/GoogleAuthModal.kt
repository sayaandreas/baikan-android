package com.sayaandreas.baikanandroid.ui.onboarding

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.model.User

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun GoogleAuthDialog(
    isLoading: Boolean,
    users: List<User>,
    onDismissRequest: () -> Unit,
    onSuccess: (user: User) -> Unit
) {

    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            securePolicy = SecureFlagPolicy.Inherit
        )
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(Color.White),
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            return@Dialog
        }
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 16.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google__g__logo),
                        contentDescription = null,
                        Modifier
                            .size(32.dp)
                            .padding(end = 8.dp)
                    )
                    Text(text = "Pilih Akun Google", style = MaterialTheme.typography.subtitle1)
                }
                Divider(Modifier.padding(top = 24.dp, bottom = 8.dp))
                users.forEach {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSuccess(it)
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Column() {
                            Text(text = it.name)
                            Text(text = it.email, fontSize = 12.sp, color = Color.Gray)
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Divider(Modifier.padding(top = 8.dp, bottom = 8.dp))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_person_add_24),
                        contentDescription = null
                    )
                    Text(text = "Tambahkan akun lain", modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}
