package com.sayaandreas.baikanandroid.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.navigation.NavHostController
import com.sayaandreas.baikanandroid.ui.theme.fab
import kotlinx.coroutines.launch
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.model.Counselor
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.ui.main.MainViewModel

@Composable
fun HomeScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    val scaffoldState = rememberScaffoldState()
    val (selectedTab, setSelectedTab) = rememberSaveable { mutableStateOf(NavHomeTab.HOME.title) }
    val (showDialog, setShowDialog) = rememberSaveable { mutableStateOf(false) }
    val (showLoginDialog, setShowLoginDialog) = rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val toggleDrawer = {
        scope.launch {
            scaffoldState.drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerContent(navController = navController, logoutUser = {
                mainViewModel.logoutUser()
            }, isLoggedIn = mainViewModel.currentUser.value != null)
        },
        drawerShape = RectangleShape,
        floatingActionButton = {
            FloatingActionButton(
                contentColor = Color.White,
                backgroundColor = MaterialTheme.colors.primary,
                onClick = {
                    if (mainViewModel.currentUser.value != null) setShowDialog(true) else setShowLoginDialog(
                        true
                    )
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable._4_hours_svgrepo_com),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }

        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = {
            HomeBottomNavBar(
                selectedTab = selectedTab,
                onTabChange = {
                    if (mainViewModel.currentUser.value != null) setSelectedTab(it) else setShowLoginDialog(
                        true
                    )
                }
            )
        }
    ) { innerPadding ->
        Crossfade(selectedTab, modifier = Modifier.padding(innerPadding)) { destination ->
            when (destination) {
                NavHomeTab.HOME.title -> HomeTab(
                    toggleDrawer = toggleDrawer,
                    goToCounseling = {
                        mainViewModel.setQuick(false)
                        setSelectedTab(NavHomeTab.TOPICS.title)
                    },
                    counselorList = mainViewModel.counselorList,
                    currentUser = mainViewModel.currentUser.value,
                    goToCounselorDetail = { c: Counselor ->
                        mainViewModel.selectCounselor(c)
                        navController.navigate(BaikanScreen.CounselorDetail.route)
                    },
                    setShowLoginDialog = setShowLoginDialog
                )
                NavHomeTab.TOPICS.title -> TopicsTab(
                    mainViewModel,
                    navController,
                    mainViewModel.quick.value,
                    mainViewModel.counselorList
                )
                NavHomeTab.PROFILE.title -> ProfileTab(
                    navController = navController,
                    mainViewModel = mainViewModel
                )
                NavHomeTab.NOTIFICATIONS.title -> NotificationsTab()
            }
        }
        if (showDialog) {
            QuickCounselingDialog(
                onDismissRequest = { setShowDialog(false) },
                onSuccess = {
                    mainViewModel.setQuick(true)
                    setShowDialog(false)
                    setSelectedTab(NavHomeTab.TOPICS.title)
                })
        }

        if (showLoginDialog) {
            AlertDialog(
                onDismissRequest = { setShowLoginDialog(false) },
                title = { Text(text = "Mohon Maaf") },
                text = { Text(text = "Kamu harus login terlebih dahulu untuk dapat menggunakan fitur ini") },
                confirmButton = {
                    Button(onClick = { navController.navigate(BaikanScreen.Login.route) }) {
                        Text(text = "Lanjut login")
                    }
                },
                dismissButton = {
                    OutlinedButton(onClick = { setShowLoginDialog(false) }) {
                        Text(text = "Batal")
                    }
                },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
                )
            )
        }
    }
}

@Composable
fun HomeBottomNavBar(
    selectedTab: String,
    onTabChange: (tab: String) -> Unit,
) {
    val tabs = NavHomeTab.values()
    BottomAppBar(
        cutoutShape = MaterialTheme.shapes.fab,
    ) {
        tabs.forEach { tab ->
            BottomNavigationItem(
                selected = selectedTab === tab.title,
                onClick = { onTabChange(tab.title) },
                icon = {
                    Icon(
                        painter = painterResource(id = tab.icon),
                        contentDescription = null,
                    )
                },
                label = { Text(text = tab.title, fontSize = 10.sp) },
            )
            if (tab.title == "Konseling") {
                Spacer(Modifier.weight(1f, true))
            }
        }
    }
}

@Composable
fun DrawerContent(navController: NavHostController, logoutUser: () -> Unit, isLoggedIn: Boolean) {
    val menuList = listOf(
        "Home" to R.drawable.ic_home_outline,
        "Riwayat" to R.drawable.ic_time_outline,
        "Instagram" to R.drawable.ic_logo_instagram,
        "Facebook" to R.drawable.ic_logo_facebook,
        "Twitter" to R.drawable.ic_logo_twitter,
        "Youtube" to R.drawable.ic_logo_youtube,
        "Bantuan" to R.drawable.ic_call_outline,
        "FAQ" to R.drawable.ic_information_circle_outline,
        "Tentang" to R.drawable.ic_help_circle_outline
    )
    Column(
        Modifier
            .fillMaxWidth()
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            menuList.forEach {
                val withDivider = it.first == "Riwayat" || it.first == "Youtube"
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = it.second),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black
                    )
                    Text(text = it.first)
                }
                if (withDivider) {
                    Divider(modifier = Modifier.padding(vertical = 10.dp))
                }
            }
        }
        if (isLoggedIn) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp), horizontalArrangement = Arrangement.Center
            ) {
                OutlinedButton(onClick = {
                    navController.navigate(BaikanScreen.Login.route) {
                        popUpTo(BaikanScreen.Login.route) {
                            inclusive = true
                        }
                    }
                    logoutUser()
                }) {
                    Text(text = "Logout")
                }
            }
        }
    }

}

@Composable
fun QuickCounselingDialog(
    onDismissRequest: () -> Unit,
    onSuccess: () -> Unit
) {

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            securePolicy = SecureFlagPolicy.Inherit
        )
    ) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Konseling 24 Jam", style = MaterialTheme.typography.h6)
                Text(
                    text = "Hai, kamu bisa konseling kapan pun karena layanan kami tersedia selama 24 JAM.",
                    modifier = Modifier.padding(top = 4.dp)
                )
                Button(
                    onClick = onSuccess, modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                ) {
                    Text(text = "Pilih Topik")
                }
                OutlinedButton(
                    onClick = onDismissRequest, modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Batal")
                }

            }
        }
    }
}

enum class NavHomeTab(
    val title: String,
    @DrawableRes val icon: Int
) {
    HOME("Home", R.drawable.ic_baseline_home_24),
    TOPICS(
        "Konseling",
        R.drawable.ic_baseline_chat_bubble_24
    ),
    PROFILE("Profil", R.drawable.ic_baseline_account_circle_24),
    NOTIFICATIONS("Notifikasi", R.drawable.ic_baseline_notifications_24);
}