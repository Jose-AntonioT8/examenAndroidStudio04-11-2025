package com.turingalan.examen.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.turingalan.examen.ui.detail.BookDetailScreen
import com.turingalan.examen.ui.results.BookResult
import com.turingalan.examen.ui.results.BookResultScreen
import com.turingalan.examen.ui.search.BookSearch
import kotlinx.serialization.Serializable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val startDestination = Route.Search.route
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        Modifier.padding(innerPadding)

        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable(Route.Search.route) {
                BookSearch(
                    onNavegationList = { result ->
                        navController.navigate("result/$result")
                    }
                )
            }
            composable("detail/{result}/{id}",
                arguments = listOf(navArgument("id"){type = NavType.LongType})
            ){ backStackEntry ->
                val id = backStackEntry.arguments?.getLong("id")?:0L
                val result = backStackEntry.arguments?.getString("result")?:""
                BookDetailScreen(bookId = id,
                    onNavegationBack = {
                        navController.navigate("result/$result")
                    })

            }

            composable("result/{result}",
                arguments = listOf(navArgument("result"){type = NavType.StringType})
            ){backStackEntry ->
                    val resultado = backStackEntry.arguments?.getString("result")?:""
                BookResult(
                    onShowDetail = { id->
                        navController.navigate("detail/$resultado/$id")
                    },
                    onNavigateBack = {
                        navController.navigate(Route.Search.route)
                    }
                )
            }
        }
    }
}
@Serializable
sealed class Route(val route: String) {
    @Serializable
    object Search: Route(route = "search")

    @Serializable
    object Form: Route(route = "PokemonForm")


    @Serializable
    data class Detail(val id: Int): Route(route = "PokemonDetail/{$id}")
}
