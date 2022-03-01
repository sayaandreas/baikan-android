package com.sayaandreas.baikanandroid.ui.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sayaandreas.baikanandroid.ui.theme.fab
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    val (selectedTab, setSelectedTab) = rememberSaveable { mutableStateOf(NavHomeTab.HOME.title) }
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
            Text("Drawer title", modifier = Modifier.padding(16.dp))
        },
        drawerShape = RectangleShape,
        floatingActionButton = {
            FloatingActionButton(
                contentColor = Color.White,
                backgroundColor = MaterialTheme.colors.primary,
                onClick = {}
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                )
            }

        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = {
            HomeBottomNavBar(
                selectedTab = selectedTab,
                onTabChange = setSelectedTab
            )
        }
    ) { innerPadding ->
        Crossfade(selectedTab, modifier = Modifier.padding(innerPadding)) { destination ->
            when (destination) {
                NavHomeTab.HOME.title -> HomeScreen(toggleDrawer)
                NavHomeTab.TOPICS.title -> TopicsTab(navController)
                NavHomeTab.PROFILE.title -> ProfileTab()
                NavHomeTab.NOTIFICATIONS.title -> NotificationsTab()
            }
        }
    }
}

@Composable
fun HomeBottomNavBar(
    selectedTab: String,
    onTabChange: (tab: String) -> Unit
) {
    val tabs = NavHomeTab.values()
    BottomAppBar(
        cutoutShape = MaterialTheme.shapes.fab
    ) {
        tabs.forEach { tab ->
            BottomNavigationItem(
                selected = selectedTab === tab.title,
                onClick = { onTabChange(tab.title) },
                icon = {
                    Icon(
                        tab.icon,
                        contentDescription = null,
                    )
                },
            )
            if (tab.title == "Counseling") {
                Spacer(Modifier.weight(1f, true))
            }
        }
    }
}

enum class NavHomeTab(
    val title: String,
    val icon: ImageVector
) {
    HOME("Home", Icons.Filled.Home),
    TOPICS("Counseling", Icons.Filled.Email),
    PROFILE("Profile", Icons.Filled.AccountCircle),
    NOTIFICATIONS("Notifications", Icons.Filled.Notifications);
}