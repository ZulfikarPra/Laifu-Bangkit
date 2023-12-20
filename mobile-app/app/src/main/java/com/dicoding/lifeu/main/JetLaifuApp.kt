//package com.dicoding.lifeu.main
//
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.AccountCircle
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material3.Icon
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.res.vectorResource
//import androidx.compose.ui.tooling.preview.Devices
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.navigation.NavGraph.Companion.findStartDestination
//import androidx.navigation.NavHostController
//import androidx.navigation.NavType
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.navArgument
//import com.dicoding.lifeu.R
//import com.dicoding.nav.Screen
//
////class JetLaifuApp {
////}
//@Composable
//fun JetLaifuApp(
//    mainViewModel: MainViewModel,
//    modifier: Modifier = Modifier,
//    navController: NavHostController = rememberNavController(),
//) {
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//
//    Scaffold(
//        bottomBar = {
//            if (currentRoute != Screen.Article.route) {
//                BottomBar(navController)
//            }
//        },
//        modifier = modifier
//    ) { innerPadding ->
//        NavHost(
//            navController = navController,
//            startDestination = Screen.Beranda.route,
//            modifier = Modifier.padding(innerPadding)
//        ) {
//            composable(Screen.Beranda.route) {
//                HomeScreen(mainViewModel, navigateToConsultation = { listId ->
//                    navController.navigate(Screen.Konsultasi.route)
//                }, navigateToArticle = { listId ->
//                    navController.navigate(Screen.Article.route)
//                }, navigateToDetail = { articleId ->
//                    navController.navigate(Screen.DetailArticle.createRoute(articleId))
//                })
//            }
//            composable(Screen.Konsultasi.route) {
//                ConsultationScreen()
//            }
//            composable(Screen.Riwayat.route) {
//                HistoryScreen()
//            }
//            composable(Screen.Profil.route) {
//                ProfileScreen(mainViewModel)
//            }
//            composable(Screen.Article.route) {
//                ArticleScreen(navigateToDetail = { articleId ->
//                    navController.navigate(Screen.DetailArticle.createRoute(articleId))
//                })
//            }
//            composable(
//                route = Screen.DetailArticle.route,
//                arguments = listOf(navArgument("articleId") { type = NavType.LongType }),
//            ) {
//                val id = it.arguments?.getLong("articleId") ?: -1L
//                DetailArticleScreen(
//                    articleId = id.toInt(),
//                    navigateBack = {
//                        navController.navigateUp()
//                    },
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun BottomBar(
//    navController: NavHostController,
//    modifier: Modifier = Modifier
//) {
//    NavigationBar(
//        modifier = modifier
//    ) {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
//
//        val navigationItems = listOf(
//            BottomBarItem(
//                title = stringResource(R.string.menu_beranda),
//                icon = Icons.Default.Home,
//                screen = Screen.Beranda
//            ),
//            BottomBarItem(
//                title = stringResource(R.string.menu_konsultasi),
//                icon = ImageVector.vectorResource(R.drawable.ic_baseline_consultation),
//                screen = Screen.Konsultasi
//            ),
//            BottomBarItem(
//                title = stringResource(R.string.menu_riwayat),
//                icon = ImageVector.vectorResource(R.drawable.ic_baseline_activity),
//                screen = Screen.Riwayat
//            ),
//            BottomBarItem(
//                title = stringResource(R.string.menu_profil),
//                icon = Icons.Default.AccountCircle,
//                screen = Screen.Profil
//            ),
//        )
//        navigationItems.map {
//            NavigationBarItem(
//                icon = {
//                    Icon(
//                        imageVector = it.icon,
//                        contentDescription = it.title
//                    )
//                },
//                label = {
//                    Text(it.title)
//                },
//                selected = currentRoute == it.screen.route,
//                onClick = {
//                    navController.navigate(it.screen.route) {
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                        }
//                        restoreState = true
//                        launchSingleTop = true
//                    }
//                }
//            )
//        }
//    }
//}
//
//@Preview(showBackground = true, device = Devices.PIXEL_4)
//@Composable
//fun JetNurturAppPreview() {
//    lateinit var mainViewMode: MainViewModel
//    JetLaifuApp(mainViewMode)
//}