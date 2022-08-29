package no.tepohi.partygame.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import no.tepohi.partygame.BottomNavItem
import no.tepohi.partygame.composables.ModeCard
import no.tepohi.partygame.cornerRadius
import no.tepohi.partygame.testColor

@Composable
fun OptionsScreen(navController: NavController) {

//    val buttonSize = 140.dp
//    val fontSize = 14.sp

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .border(2.dp, testColor, RoundedCornerShape(cornerRadius))
    ) {
        Text(text = "Choose a mode:")
        Row(
            modifier = Modifier
                .padding(15.dp)
                .border(2.dp, testColor, RoundedCornerShape(cornerRadius))
        ) {
            ModeCard(
                text = BottomNavItem.RoundsMode.title  + " (W.I.P)",
                icon = BottomNavItem.RoundsMode.icon,
                color = Color(150, 84, 197, 255),
                navController = navController,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(15.dp))
            ModeCard(
                text = BottomNavItem.TimerMode.title + " (W.I.P)",
                icon = BottomNavItem.TimerMode.icon,
                color = Color(193, 197, 84, 255),
                navController = navController,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier
                .padding(15.dp)
                .border(2.dp, testColor, RoundedCornerShape(cornerRadius))
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .height(140.dp)
                    .weight(1f)
                    .shadow(20.dp, RoundedCornerShape(cornerRadius))
                    .border(
                        width = 2.dp,
                        color = Color(0, 0, 0, 50),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .background(
                        color = Color(84, 197, 165, 255), shape = RoundedCornerShape(
                            cornerRadius
                        )
                    )
                    .clickable {
                        navController.navigate(BottomNavItem.ClassicMode.screen_route) {

                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }

            ) {
                Icon(
                    imageVector = BottomNavItem.ClassicMode.icon,
                    contentDescription = BottomNavItem.ClassicMode.title,
                    tint = Color(0,0,0, 50),
                    modifier = Modifier.size(90.dp)
                )
                Text(
                    text = BottomNavItem.ClassicMode.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black.copy(0.5f),
                )
            }
        }

    }

}