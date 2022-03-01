//package com.sayaandreas.baikanandroid.ui.main
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.RectangleShape
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.window.Dialog
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.rememberNavController
//import com.sayaandreas.baikanandroid.ui.counseling.*
//import com.sayaandreas.baikanandroid.ui.home.HomeTab
//import com.sayaandreas.baikanandroid.ui.notifications.NotificationsScreen
//import com.sayaandreas.baikanandroid.ui.onboarding.LoginScreen
//import com.sayaandreas.baikanandroid.ui.onboarding.OnboardingScreen
//import com.sayaandreas.baikanandroid.ui.onboarding.RegisterScreen
//import com.sayaandreas.baikanandroid.ui.onboarding.WelcomeScreen
//import com.sayaandreas.baikanandroid.ui.payment.*
//import com.sayaandreas.baikanandroid.ui.profile.ProfileScreen
//import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme
//import com.sayaandreas.baikanandroid.ui.theme.fab
//import com.sayaandreas.baikanandroid.R
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.launch
//
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            BaikanApp()
//        }
//    }
//}
//
//@Composable
//fun BaikanApp() {
//    BaikanAndroidTheme {
//        val allScreens = BaikanScreen.values().toList()
//        val navController = rememberNavController()
//        val backstackEntry = navController.currentBackStackEntryAsState()
//        val currentScreen = BaikanScreen.fromRoute(backstackEntry.value?.destination?.route)
//        val scaffoldState = rememberScaffoldState()
//        val scope = rememberCoroutineScope()
//        val topLevelNav = allScreens.filter { item -> item.icon != null }
//        val showBottomNavBar = topLevelNav.contains(currentScreen)
//        var showCustomDialogWithResult by remember { mutableStateOf(false) }
//
//        val toggleDrawer = {
//            scope.launch {
//                scaffoldState.drawerState.apply {
//                    if (isClosed) open() else close()
//                }
//            }
//        }
//        Scaffold(
//            scaffoldState = scaffoldState,
//            drawerContent = {
//                Text("Drawer title", modifier = Modifier.padding(16.dp))
//            },
//            drawerShape = RectangleShape,
//            floatingActionButton = {
//                if (showBottomNavBar) {
//                    FloatingActionButton(
//                        contentColor = Color.White,
//                        backgroundColor = MaterialTheme.colors.primary,
//                        onClick = {
//                            showCustomDialogWithResult = true
//                        }) {
//                        Icon(
//                            Icons.Default.Add,
//                            contentDescription = null,
//                        )
//                    }
//                }
//            },
//            floatingActionButtonPosition = FabPosition.Center,
//            isFloatingActionButtonDocked = true,
//            bottomBar = {
//                if (showBottomNavBar) {
//                    BaikanBottomNavBar(
//                        allScreens = allScreens,
//                        currentScreen = currentScreen,
//                        navController = navController
//                    )
//                }
//            }
//        ) { innerPadding ->
//            BaikanNavHost(
//                navController = navController,
//                toggleDrawer,
//                modifier = Modifier.padding(innerPadding)
//            )
//
//            if (showCustomDialogWithResult) {
//                CustomDialogWithResultExample(
//                    onDismiss = {
//                        showCustomDialogWithResult = !showCustomDialogWithResult
//
//                    },
//                    onNegativeClick = {
//                        showCustomDialogWithResult = !showCustomDialogWithResult
//
//
//                    },
//                    onPositiveClick = {
//                        showCustomDialogWithResult = !showCustomDialogWithResult
//
//                    }
//                )
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalFoundationApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
//@Composable
//private fun CustomDialogWithResultExample(
//    onDismiss: () -> Unit,
//    onNegativeClick: () -> Unit,
//    onPositiveClick: () -> Unit
//) {
//
//    val counselingTopics = listOf(
//        Topic(title = "Pekerjaan", icon = R.drawable.briefcase),
//        Topic(title = "Keluarga", icon = R.drawable.family),
//        Topic(title = "Pasangan", icon = R.drawable.heart),
//        Topic(title = "Emosi", icon = R.drawable.poker_face),
//        Topic(title = "Pendidikan", icon = R.drawable.mortarboard),
//        Topic(title = "Kesepian", icon = R.drawable.alone),
//        Topic(title = "Sosial", icon = R.drawable.social_media),
//        Topic(title = "Kecanduan", icon = R.drawable.brain),
//        Topic(title = "Lainnya", icon = R.drawable.more)
//    )
//    var step by remember { mutableStateOf(0) }
//
//    Dialog(onDismissRequest = onDismiss) {
//        Card(
//            elevation = 8.dp,
//            shape = RoundedCornerShape(12.dp),
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(500.dp)
//        ) {
//            Column(modifier = Modifier.padding(16.dp)) {
//                when (step) {
//                    0 -> {
//                        Text(
//                            text = "Konseling 24 JAM",
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 20.sp,
//                            modifier = Modifier.fillMaxWidth(),
//                            textAlign = TextAlign.Center
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Text(text = "Hai, kamu bisa konseling kapan pun karena layanan kami tersedia selama 24 JAM.")
//                        Spacer(modifier = Modifier.height(24.dp))
//                        Button(
//                            onClick = { step = 1 },
//                            modifier = Modifier.fillMaxWidth(),
//                            shape = CircleShape
//                        ) {
//                            Text(text = "Lanjut Pilih Topik")
//                        }
//                        TextButton(onClick = onNegativeClick, modifier = Modifier.fillMaxWidth()) {
//                            Text(text = "Batal")
//                        }
//                    }
//                    1 -> {
//                        Text(
//                            text = "Pilih Topik",
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 20.sp,
//                            modifier = Modifier.fillMaxWidth(),
//                            textAlign = TextAlign.Center
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Row(
//                            Modifier
//                                .fillMaxWidth()
//                                .padding(bottom = 8.dp),
//                            horizontalArrangement = Arrangement.spacedBy(8.dp)
//                        ) {
//                            for (i in 0..2) {
//                                val topicTitle = counselingTopics[i].title
//                                val image: Painter = painterResource(id = counselingTopics[i].icon)
//                                Column(
//                                    Modifier
//                                        .weight(1f)
//                                        .padding(16.dp),
//                                    horizontalAlignment = Alignment.CenterHorizontally
//                                ) {
//                                    Image(
//                                        painter = image,
//                                        contentDescription = "",
//                                        Modifier
//                                            .size(32.dp)
//                                            .padding(bottom = 8.dp)
//                                    )
//                                    Text(
//                                        text = topicTitle,
//                                        style = MaterialTheme.typography.caption
//                                    )
//                                }
//
//                            }
//                        }
//                        Row(
//                            Modifier
//                                .fillMaxWidth()
//                                .padding(bottom = 8.dp),
//                            horizontalArrangement = Arrangement.spacedBy(8.dp)
//                        ) {
//                            for (i in 3..5) {
//                                val topicTitle = counselingTopics[i].title
//                                val image: Painter = painterResource(id = counselingTopics[i].icon)
//                                Column(
//                                    Modifier
//                                        .weight(1f)
//                                        .padding(16.dp),
//                                    horizontalAlignment = Alignment.CenterHorizontally
//                                ) {
//                                    Image(
//                                        painter = image,
//                                        contentDescription = "",
//                                        Modifier
//                                            .size(32.dp)
//                                            .padding(bottom = 8.dp)
//                                    )
//                                    Text(
//                                        text = topicTitle,
//                                        style = MaterialTheme.typography.caption
//                                    )
//                                }
//
//                            }
//                        }
//                        Row(
//                            Modifier
//                                .fillMaxWidth()
//                                .padding(bottom = 8.dp),
//                            horizontalArrangement = Arrangement.spacedBy(8.dp)
//                        ) {
//                            for (i in 6..8) {
//                                val topicTitle = counselingTopics[i].title
//                                val image: Painter = painterResource(id = counselingTopics[i].icon)
//                                Column(
//                                    Modifier
//                                        .weight(1f)
//                                        .padding(16.dp),
//                                    horizontalAlignment = Alignment.CenterHorizontally
//                                ) {
//                                    Image(
//                                        painter = image,
//                                        contentDescription = "",
//                                        Modifier
//                                            .size(32.dp)
//                                            .padding(bottom = 8.dp)
//                                    )
//                                    Text(
//                                        text = topicTitle,
//                                        style = MaterialTheme.typography.caption
//                                    )
//                                }
//
//                            }
//                        }
//                        Spacer(modifier = Modifier.height(24.dp))
//                        Button(
//                            onClick = { step = 2 },
//                            modifier = Modifier.fillMaxWidth(),
//                            shape = CircleShape
//                        ) {
//                            Text(text = "Lanjut Pilih Topik")
//                        }
//                        TextButton(onClick = onNegativeClick, modifier = Modifier.fillMaxWidth()) {
//                            Text(text = "Batal")
//                        }
//                    }
//                    2 -> {
//                        Text(
//                            text = "Pilih Konselor",
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 20.sp,
//                            modifier = Modifier.fillMaxWidth(),
//                            textAlign = TextAlign.Center
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Text(text = "Hai, kamu bisa konseling kapan pun karena layanan kami tersedia selama 24 JAM.")
//                        Spacer(modifier = Modifier.height(24.dp))
//                        Button(
//                            onClick = { step = 3 },
//                            modifier = Modifier.fillMaxWidth(),
//                            shape = CircleShape
//                        ) {
//                            Text(text = "Lanjut Pilih Topik")
//                        }
//                        TextButton(onClick = onNegativeClick, modifier = Modifier.fillMaxWidth()) {
//                            Text(text = "Batal")
//                        }
//                    }
//                    3 -> {
//                        Text(
//                            text = "Mencari Konselor",
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 20.sp,
//                            modifier = Modifier.fillMaxWidth(),
//                            textAlign = TextAlign.Center
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Text(text = "Hai, kamu bisa konseling kapan pun karena layanan kami tersedia selama 24 JAM.")
//                        Spacer(modifier = Modifier.height(24.dp))
//                        Button(
//                            onClick = { step = 1 },
//                            modifier = Modifier.fillMaxWidth(),
//                            shape = CircleShape
//                        ) {
//                            Text(text = "Lanjut Pilih Topik")
//                        }
//                        TextButton(onClick = onNegativeClick, modifier = Modifier.fillMaxWidth()) {
//                            Text(text = "Batal")
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun BaikanNavHost(
//    navController: NavHostController,
//    toggleDrawer: () -> Job,
//    modifier: Modifier = Modifier
//) {
//    NavHost(
//        navController = navController,
//        startDestination = BaikanScreen.OnboardingScreen.name,
//        modifier = modifier
//    ) {
//        composable(BaikanScreen.OnboardingScreen.name) {
//            OnboardingScreen(navController)
//        }
//        composable(BaikanScreen.RegisterScreen.name) {
//            RegisterScreen(navController)
//        }
//        composable(BaikanScreen.LoginScreen.name) {
//            LoginScreen(navController)
//        }
//        composable(BaikanScreen.WelcomeScreen.name) {
//            WelcomeScreen(navController)
//        }
//        composable(BaikanScreen.HomeScreen.name) {
//            HomeTab(toggleDrawer)
//        }
//        composable(BaikanScreen.TopicsScreen.name) {
//            ChooseTopicScreen(navController)
//        }
//        composable(BaikanScreen.ProfileScreen.name) {
//            ProfileScreen()
//        }
//        composable(BaikanScreen.NotificationsScreen.name) {
//            NotificationsScreen()
//        }
//        composable(BaikanScreen.TopicIntroScreen.name) {
//            TopicIntroScreen(navController)
//        }
//        composable(BaikanScreen.ChooseServiceScreen.name) {
//            ChooseServiceScreen(navController)
//        }
//        composable(BaikanScreen.ChoosePackageScreen.name) {
//            ChoosePackageScreen(navController)
//        }
//        composable(BaikanScreen.ChooseCounselorScreen.name) {
//            ChooseCounselorScreen(navController)
//        }
//        composable(BaikanScreen.CounselingAgreementScreen.name) {
//            CounselingAgreementScreen(navController)
//        }
//        composable(BaikanScreen.OrderDetailScreen.name) {
//            OrderDetailScreen(navController)
//        }
//        composable(BaikanScreen.PaymentMethodScreen.name) {
//            PaymentMethodScreen(navController)
//        }
//        composable(BaikanScreen.ChooseBankScreen.name) {
//            ChooseBankScreen(navController)
//        }
//        composable(BaikanScreen.PaymentDetailScreen.name) {
//            PaymentDetailScreen(navController)
//        }
//        composable(BaikanScreen.PaymentSuccessScreen.name) {
//            PaymentSuccessScreen(navController)
//        }
//        composable(BaikanScreen.CallCounselorScreen.name) {
//            CallCounselorScreen(navController)
//        }
//    }
//}
//
//@Composable
//fun BaikanBottomNavBar(
//    allScreens: List<BaikanScreen>,
//    currentScreen: BaikanScreen,
//    navController: NavHostController
//) {
//    BottomAppBar(
//        cutoutShape = MaterialTheme.shapes.fab
//    ) {
//        allScreens.filter { item -> item.icon != null }.forEach { screen ->
//            BottomNavigationItem(
//                selected = currentScreen.name === screen.name,
//                onClick = { navController.navigate(screen.name) },
//                icon = {
//                    screen.icon?.let {
//                        Icon(
//                            it,
//                            contentDescription = null,
//                        )
//                    }
//                },
//            )
//            if (screen.name == BaikanScreen.TopicsScreen.name) {
//                Spacer(Modifier.weight(1f, true))
//            }
//        }
//    }
//}