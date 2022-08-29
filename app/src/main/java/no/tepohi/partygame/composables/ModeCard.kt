package no.tepohi.partygame.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import no.tepohi.partygame.BottomNavItem
import no.tepohi.partygame.cornerRadius

@Composable
fun ModeCard(
    text: String,
    icon: ImageVector,
    navController: NavController,
    buttonSize: Dp = 140.dp,
    fontSize: TextUnit = 14.sp,
    color: Color,
    modifier: Modifier,
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(buttonSize)
            .shadow(20.dp, RoundedCornerShape(cornerRadius))
            .border(
                width = 2.dp,
                color = Color(0,0,0, 50),
                shape = RoundedCornerShape(15.dp)
            )
            .background(
                color = color,
                shape = RoundedCornerShape(cornerRadius)
            )
            .clickable {
                navController.navigate(BottomNavItem.RoundsMode.screen_route) {

                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
            .then(modifier)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Color(0,0,0, 50),
            modifier = Modifier.size(90.dp)
        )
//                Text(text = BottomNavItem.RoundGame.title)
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black.copy(0.5f),
        )
    }

}