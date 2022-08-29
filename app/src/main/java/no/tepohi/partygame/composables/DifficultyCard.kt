package no.tepohi.partygame.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import no.tepohi.partygame.cornerRadius

@Composable
fun DifficultyCard(
    text: String,
    color: Color,
    icon: ImageVector,
    cardSize: Dp = 150.dp,
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(cardSize)
            .shadow(20.dp, RoundedCornerShape(cornerRadius))
            .border(width = 1.dp, color = Color(0,0,0, 50), shape = RoundedCornerShape(15.dp))
//            .border(2.dp, testColor, RoundedCornerShape(cornerRadius))
            .background(
                color = color,
                shape = RoundedCornerShape(cornerRadius)
            )
            .clip(RoundedCornerShape(cornerRadius))
            .then(modifier)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Color(0, 0, 0, 50),
            modifier = Modifier.size(90.dp)
        )
        Text(
            text = text,
            color = Color(0, 0, 0, 100),
            fontWeight = FontWeight.SemiBold
        )
    }

}