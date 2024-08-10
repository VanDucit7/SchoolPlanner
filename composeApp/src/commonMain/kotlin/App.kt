import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import schoolplanner.composeapp.generated.resources.Res
import schoolplanner.composeapp.generated.resources.title_configuration
import schoolplanner.composeapp.generated.resources.title_welcome
import screen.ConfigurationScreen
import screen.WelcomeScreen
import ui.presentation.configterm.ConfigTermScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        SchoolPlannerApp()
    }
}


/**
 * enum values that represent the screens in the app
 */
enum class SchoolPlannerScreen(val title: StringResource) {
    Welcome(title = Res.string.title_welcome),
    Configuration(title = Res.string.title_configuration),
    ConfigTerm(title = Res.string.title_configuration)
}

@Composable
fun SchoolPlannerApp(
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = SchoolPlannerScreen.valueOf(
        backStackEntry?.destination?.route ?: SchoolPlannerScreen.Welcome.name
    )

    KoinContext {
        NavHost(
            navController = navController,
            startDestination = SchoolPlannerScreen.ConfigTerm.name,
        ) {
            composable(route = SchoolPlannerScreen.Welcome.name) {
                WelcomeScreen(onStartClick = {
                    navController.navigate(SchoolPlannerScreen.Configuration.name)
                })
            }
            composable(route = SchoolPlannerScreen.Configuration.name) {
                ConfigurationScreen(
                    onConfigNowClick = {
                        navController.navigate(SchoolPlannerScreen.ConfigTerm.name)
                    },
                    onConfigLateClick = {

                    }
                )
            }
            composable(route = SchoolPlannerScreen.ConfigTerm.name) {
                ConfigTermScreen()
            }
        }
    }
}