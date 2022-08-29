package no.tepohi.partygame.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Looks3
import androidx.compose.material.icons.filled.LooksOne
import androidx.compose.material.icons.filled.LooksTwo
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import no.tepohi.partygame.*
import no.tepohi.partygame.R
import no.tepohi.partygame.composables.ChallengeCard
import no.tepohi.partygame.composables.DifficultyCard
import no.tepohi.partygame.dares
import kotlin.random.Random


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ClassicModeScreen3() {

    val difficulties = mapOf(
        1 to listOf(
            listOf("A","2","3"),
            listOf("4","5","6"),
            listOf("7","8","9"),
            listOf("10"),
        ),
        2 to listOf(
            listOf("A","2"),
            listOf("3","4","5"),
            listOf("6","7","8"),
            listOf("9","10"),
        ),
        3 to listOf(
            listOf("A"),
            listOf("2","3","4"),
            listOf("5","6","7"),
            listOf("8","9","10"),
        ),
    )

    var difficulty by remember { mutableStateOf(0) }
    val localDares by remember { mutableStateOf(dares) }

    var timerReady by remember { mutableStateOf(false) }

    val chanceSize by animateFloatAsState(
        targetValue = if (timerReady) 240f else 70f,
        animationSpec = repeatable(
            iterations = 1,
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    var cint by remember { mutableStateOf(0) }

//    val angle2 by animateFloatAsState(
//        targetValue = if (timerReady) 0f else 360f,
//        animationSpec = repeatable(
//            iterations = 1,
//            animation = tween(1000 * 15 * 1, easing = LinearEasing),
//            repeatMode = RepeatMode.Restart
//        )
//    )

    val infTransition = rememberInfiniteTransition()
    val angle1 by infTransition.animateFloat(
        initialValue = 0F,
        targetValue = 0F,
        animationSpec = infiniteRepeatable(
            animation = tween(1000 * 15 * 1, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    var dare1Card by remember { mutableStateOf(false) }
    var dare2Card by remember { mutableStateOf(false) }
    var dare3Card by remember { mutableStateOf(false) }
    var dare4Card by remember { mutableStateOf(false) }

    var rint1 by remember { mutableStateOf(0)}
    var rint2 by remember { mutableStateOf(0)}
    var rint3 by remember { mutableStateOf(0)}
    var rint4 by remember { mutableStateOf(0)}

    val selectedSize = 3f
    val unselectedSize = 2f
    val selectedAnimDuration = 500 * 1

    val topRowHeight by animateFloatAsState(
        targetValue = if (dare1Card || dare2Card) selectedSize else unselectedSize,
        animationSpec = repeatable(
            iterations = 1,
            animation = tween(selectedAnimDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    val bottomRowHeight  by animateFloatAsState(
        targetValue = if (dare3Card || dare4Card) selectedSize else unselectedSize,
        animationSpec = repeatable(
            iterations = 1,
            animation = tween(selectedAnimDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    val startColumnWidth  by animateFloatAsState(
        targetValue = if (dare1Card || dare3Card) selectedSize else unselectedSize,
        animationSpec = repeatable(
            iterations = 1,
            animation = tween(selectedAnimDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    val endColumnWidth  by animateFloatAsState(
        targetValue = if (dare2Card || dare4Card) selectedSize else unselectedSize,
        animationSpec = repeatable(
            iterations = 1,
            animation = tween(selectedAnimDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {

        AnimatedVisibility(
            visible = difficulty == 0,
            enter = fadeIn(animationSpec = tween(durationMillis = 1200)),
            exit = fadeOut(animationSpec = tween(durationMillis = 1200)),
            modifier = Modifier
                .zIndex(100f)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0, 0, 0, 150))
                    .zIndex(100f)
            ) { }
        }
        AnimatedVisibility(
            visible = difficulty == 0,
            enter = slideInVertically(animationSpec = tween(durationMillis = 1200), initialOffsetY = { io -> -io }),
            exit = slideOutVertically(animationSpec = tween(durationMillis = 1200), targetOffsetY = { io -> -io }),
            modifier = Modifier
                .zIndex(100f)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(100f)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(40.dp)
                        .shadow(20.dp, RoundedCornerShape(cornerRadius))
                        .border(width = 2.dp, color = Color(0,0,0, 150), shape = RoundedCornerShape(15.dp))
                        .fillMaxSize()
                        .background(Color.White, RoundedCornerShape(cornerRadius))
                ) {
                    Text(text = "Choose a difficulty:")
                    Row(
                        modifier = Modifier
                            .padding(15.dp)
                            .border(2.dp, testColor, RoundedCornerShape(cornerRadius))
                    ) {
                        DifficultyCard(
                            text = "Easy",
                            color = Color(109, 224, 134, 255),
                            icon = Icons.Filled.LooksOne,
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    difficulty = 1
                                }
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        DifficultyCard(
                            text = "Medium",
                            color = Color(224, 182, 109, 255),
                            icon = Icons.Filled.LooksTwo,
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    difficulty = 2
                                }
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        DifficultyCard(
                            text = "Hard",
                            color = Color(224, 122, 109, 255),
                            icon = Icons.Filled.Looks3,
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    difficulty = 3
                                }
                        )
                    }



                }
            }
        }

        Column(
            modifier = Modifier
                //            .background(Color.Gray)
                .fillMaxSize()
                .padding(15.dp)
                .border(2.dp, testColor, RoundedCornerShape(cornerRadius))

        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(topRowHeight)
                    .border(2.dp, testColor, RoundedCornerShape(cornerRadius))
            ) {

                ChallengeCard(
                    show = dare1Card,
                    preText = if (difficulty != 0) difficulties[difficulty]!![0].joinToString( "   ") else "",
                    text = localDares[1]?.get(rint1) ?: "",
                    background = Color(109, 224, 134, 255),
                    bottom1Text = "1",
                    bottom2Text = "${localDares[1]?.size}",
                    modifier = Modifier
                        .weight(startColumnWidth)
                        .clickable(
                            enabled = difficulty != 0
                        ) {
                            dare1Card = true
                            dare2Card = false
                            dare3Card = false
                            dare4Card = false

                            if (localDares[1]?.size!! != 1) {
                                val temp = rint1
                                rint1 = Random.nextInt(localDares[1]?.size!! - 1)

                                localDares[1]!!.also {
                                    it.removeAt(temp)
                                    localDares[1] = it
                                }
                            }

                        }
                )

                Spacer(modifier = Modifier.width(15.dp))

                ChallengeCard(
                    show = dare2Card,
                    preText = if (difficulty != 0) difficulties[difficulty]!![1].joinToString( "   ") else "",
                    text = localDares[2]?.get(rint2) ?: "",
                    background = Color(199, 224, 109, 255),
                    bottom1Text = "2",
                    bottom2Text = "${localDares[2]?.size}",
                    modifier = Modifier
                        .weight(endColumnWidth)
                        .clickable(
                            enabled = difficulty != 0
                        ) {
                            dare1Card = false
                            dare2Card = true
                            dare3Card = false
                            dare4Card = false

//                            topRowHeight = selectedSize
//                            bottomRowHeight = unselectedSize
//                            startColumnWidth = unselectedSize
//                            endColumnWidth = selectedSize

                            if (localDares[2]?.size!! != 1) {
                                val temp = rint2
                                rint2 = Random.nextInt(localDares[2]?.size!! - 1)

                                localDares[2]!!.also {
                                    it.removeAt(temp)
                                    localDares[2] = it
                                }
                            }

                        }
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(bottomRowHeight)
                    .border(2.dp, testColor, RoundedCornerShape(cornerRadius))
            ) {
                ChallengeCard(
                    show = dare3Card,
                    preText = if (difficulty != 0) difficulties[difficulty]!![2].joinToString( "   ") else "",
                    text = localDares[3]?.get(rint3) ?: "",
                    background = Color(224, 182, 109, 255),
                    bottom1Text = "3",
                    bottom2Text = "${localDares[3]?.size}",
                    modifier = Modifier
                        .weight(startColumnWidth)
                        .clickable(
                            enabled = difficulty != 0
                        ) {
                            dare1Card = false
                            dare2Card = false
                            dare3Card = true
                            dare4Card = false

//                            topRowHeight = unselectedSize
//                            bottomRowHeight = selectedSize
//                            startColumnWidth = selectedSize
//                            endColumnWidth = unselectedSize

                            if (localDares[3]?.size!! != 1) {
                                val temp = rint3
                                rint3 = Random.nextInt(localDares[3]?.size!! - 1)

                                localDares[3]!!.also {
                                    it.removeAt(temp)
                                    localDares[3] = it
                                }
                            }

                        }
                )

                Spacer(modifier = Modifier.width(15.dp))

                ChallengeCard(
                    show = dare4Card,
                    preText = if (difficulty != 0) difficulties[difficulty]!![3].joinToString( "   ") else "",
                    text = localDares[4]?.get(rint4) ?: "",
                    background = Color(224, 122, 109, 255),
                    bottom1Text = "4",
                    bottom2Text = "${localDares[4]?.size}",
                    modifier = Modifier
                        .weight(endColumnWidth)
                        .clickable(
                            enabled = difficulty != 0
                        ) {
                            dare1Card = false
                            dare2Card = false
                            dare3Card = false
                            dare4Card = true

//                            topRowHeight = unselectedSize
//                            bottomRowHeight = selectedSize
//                            startColumnWidth = unselectedSize
//                            endColumnWidth = selectedSize

                            if (localDares[4]?.size!! != 1) {
                                val temp = rint4
                                rint4 = Random.nextInt(localDares[4]?.size!! - 1)

                                localDares[4]!!.also {
                                    it.removeAt(temp)
                                    localDares[4] = it
                                }
                            }

                        }
                )
            }


        }

        Column {
            Spacer(modifier = Modifier.weight(topRowHeight))
            Row {
                Spacer(modifier = Modifier.weight(startColumnWidth))

                IconButton(
                    onClick = {
                        timerReady = !timerReady

                        cint = Random.nextInt(chances.size-1)
                    },
                    modifier = Modifier
                        .size(chanceSize.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .shadow(20.dp, RoundedCornerShape(cornerRadius))
                        .background(Color(109, 170, 224, 255))
                        .border(
                            width = 2.dp,
                            color = Color(0, 0, 0, 50),
                            shape = RoundedCornerShape(50.dp)
                        )
                ) {

                    val progress = -((angle1 / 360) % 1)

                    CircularProgressIndicator(
                        progress = if (!timerReady) progress else 0f,
                        strokeWidth = 35.dp,
                        color = Color(0, 0, 0, 100),
                        modifier = Modifier
                            .size(70.dp)
                    )

//                    val resource = if (!timerReady) painterResource(id = R.drawable.questionmark2) else painterResource(id = R.drawable.exclamationmark1)
                    AnimatedVisibility(
                        visible = !timerReady,
                        enter = slideInVertically(animationSpec = tween(durationMillis = selectedAnimDuration, delayMillis = 1000), initialOffsetY = { io -> -io * 3 }),
                        exit = slideOutVertically(animationSpec = tween(durationMillis = selectedAnimDuration, delayMillis = 1000), targetOffsetY = { io -> -io * 3 }),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.questionmark1),
                            contentDescription = "?",
                            colorFilter = ColorFilter.tint(Color(109, 170, 224, 255), blendMode = BlendMode.Difference),
                            modifier = Modifier
                                .rotate(if (!timerReady) angle1 else 0f)
                        )
                    }
                    AnimatedVisibility(
                        visible = timerReady,
                        enter = slideInVertically(animationSpec = tween(durationMillis = selectedAnimDuration, delayMillis = 1000), initialOffsetY = { io -> io * 3 }),
                        exit = slideOutVertically(animationSpec = tween(durationMillis = selectedAnimDuration, delayMillis = 1000), targetOffsetY = { io -> io * 3 }),
                    ) {
                        Text(
                            text = chances[cint],
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(15.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(endColumnWidth))
            }
            Spacer(modifier = Modifier.weight(bottomRowHeight))
        }


    }
}