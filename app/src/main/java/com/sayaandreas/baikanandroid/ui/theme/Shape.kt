package com.sayaandreas.baikanandroid.ui.theme

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp),
)

val Shapes.topAppBarLarge: Shape
    get() = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)

val Shapes.topAppBarNormal: Shape
    get() = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)

val Shapes.fab: Shape
    get() = Shapes.small.copy(CornerSize(percent = 50))