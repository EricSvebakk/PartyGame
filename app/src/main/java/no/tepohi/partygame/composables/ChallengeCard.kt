package no.tepohi.partygame.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import no.tepohi.partygame.R
import no.tepohi.partygame.cornerRadius
import no.tepohi.partygame.testColor
import kotlin.random.Random

@Composable
fun ChallengeCard(
    show: Boolean,
    preText: String,
    text: String,
    bottom1Text: String,
    bottom2Text: String,
    background: Color,
    modifier: Modifier = Modifier
) {

    val enterAnimDuration = 1200
    val exitAnimDuration = 1200

    val enterAnimDelayDuration = 200
    val exitAnimDelayDuration = 1000

    val rotation by remember { mutableStateOf(Random.nextInt(-20, 20).toFloat()) }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxHeight()
            .shadow(30.dp, RoundedCornerShape(cornerRadius))
            .border(width = 2.dp, color = Color(0, 0, 0, 50), shape = RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .background(background)
            .then(modifier)
            .padding(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = preText,
                fontSize = 20.sp,
                color = Color.Black.copy(0.5f),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .border(2.dp, testColor, RoundedCornerShape(cornerRadius))
                    .zIndex(100f)
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.questionmark1),
                contentDescription = "?",
                colorFilter = ColorFilter.tint(background, blendMode = BlendMode.Difference),
                modifier = Modifier
                    .size(200.dp)
                    .rotate(rotation)
            )
        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "$bottom1Text sips",
                color = Color.Black.copy(0.5f)
            )
            Text(
                text = "$bottom2Text cards left",
                color = Color.Black.copy(0.5f)
            )
        }
        AnimatedVisibility(
            visible = show,
            enter = slideInVertically(animationSpec = tween(durationMillis = enterAnimDuration, delayMillis = enterAnimDelayDuration), initialOffsetY = { io -> io * 14 }),
            exit = slideOutVertically(animationSpec = tween(durationMillis = exitAnimDuration, delayMillis = exitAnimDelayDuration), targetOffsetY = { io -> io * 14 }),
            modifier = Modifier
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .border(2.dp, testColor, RoundedCornerShape(cornerRadius))
                    .padding(15.dp)
            ) {
                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .border(2.dp, testColor, RoundedCornerShape(cornerRadius))
//                        .padding(15.dp)
                )
            }
        }
    }
}