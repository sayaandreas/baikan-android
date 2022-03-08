package com.sayaandreas.baikanandroid.ui.counseling

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.model.Package
import com.sayaandreas.baikanandroid.model.PackageType
import com.sayaandreas.baikanandroid.model.Tag
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme

@Composable
fun ChoosePackageScreen(navController: NavHostController) {
    val packageTypes =
        listOf(PackageType.Text.name, PackageType.VoiceCall.name, PackageType.VideoCall.name)
    val packages = Package.getAll()
    val textPackages = packages.subList(0, 3)
    val voicePackages = packages.subList(3, 5)
    val videoPackages = packages.subList(5, 6)
    var (selectedTab, setSelectedTab) = rememberSaveable { mutableStateOf(PackageType.Text.name) }
    var (selectedPackage, setSelectedPackage) = rememberSaveable { mutableStateOf(0) }

    Column(
        Modifier
            .fillMaxSize()
    ) {
        TopAppBar(title = { Text(text = "Paket Konseling") }, navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        })
        Column(
            Modifier
                .weight(1f)
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                packageTypes.forEach {
                    val selected = selectedTab == it
                    OutlinedButton(
                        onClick = {
                            if (!selected) {
                                setSelectedTab(it)
                                setSelectedPackage(0)
                            }
                        }, Modifier.weight(1f), colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = if (selected) MaterialTheme.colors.primary else Color.White,
                            contentColor = if (selected) Color.White else MaterialTheme.colors.primary,
                        ), shape = CircleShape
                    ) {
                        Text(text = it, style = MaterialTheme.typography.subtitle2)
                    }
                }
            }
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Icon(Icons.Default.Info, contentDescription = null)
                    Text(
                        text = "Durasi konseling $selectedTab per sesi = 60 menit",
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
                Column(
                    Modifier.padding(top = 16.dp, bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    when (selectedTab) {
                        PackageType.Text.name -> {
                            PackageTab(selectedPackage, setSelectedPackage, textPackages)
                        }
                        PackageType.VoiceCall.name -> {
                            PackageTab(selectedPackage, setSelectedPackage, voicePackages)
                        }
                        PackageType.VideoCall.name -> {
                            PackageTab(selectedPackage, setSelectedPackage, videoPackages)
                        }
                    }
                }
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                onClick = {
                    if (selectedPackage != 0) {
                        navController.navigate(BaikanScreen.ChooseCounselor.route)
                    }
                },
                enabled = selectedPackage != 0
            ) {
                Text(text = "Pilih Paket")
            }
        }
    }
}

@Composable
fun PackageTab(selectedCard: Int, onClick: (v: Int) -> Unit, list: List<Package>) {
    list.forEach {
        ProductCard(
            selectedCard, it, onClick
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductCard(
    selectedCard: Int,
    product: Package,
    onClick: (v: Int) -> Unit
) {
    val selected = selectedCard == product.id
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        border = BorderStroke(
            1.dp,
            color = if (selected) MaterialTheme.colors.primary else Color.LightGray
        ),
        backgroundColor = Color.White,
        onClick = { onClick(product.id) }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(Modifier.weight(1f)) {
                Text(text = product.name)
                Spacer(Modifier.size(4.dp))
                Text(
                    text = "Rp ${product.finalTotalPrice} / ${product.sessionQty}x sesi",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (selected) MaterialTheme.colors.primary else Color.Black
                )
                Spacer(Modifier.size(4.dp))
                Text(text = product.description1, fontSize = 11.sp)
                Text(text = product.description2, fontSize = 11.sp)
            }
            product.tag?.let { TagText(it) }
        }

    }
}

@Composable
fun TagText(tag: Tag) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(50))
            .background(color = tag.color)
            .padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        Text(
            text = tag.text,
            color = Color.White,
            fontSize = 11.sp
        )
    }
}

@Preview
@Composable
fun ChoosePackagePreview() {
    val navController = rememberNavController()
    BaikanAndroidTheme() {
        Surface() {
            ChoosePackageScreen(navController)
        }
    }
}

