package com.example.coursesapp.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coursesapp.core.R
import com.example.coursesapp.core.ui.theme.CardBackground
import com.example.coursesapp.core.ui.theme.PrimaryGreen
import com.example.coursesapp.core.ui.theme.RatingBadgeBackground
import com.example.coursesapp.core.ui.theme.TextHint
import com.example.coursesapp.core.ui.theme.TextPrimary
import com.example.coursesapp.core.ui.theme.TextSecondary

private val placeholders = mapOf(
    100 to R.drawable.course_placeholder_1,
    101 to R.drawable.course_placeholder_2,
    102 to R.drawable.course_placeholder_3,
    103 to R.drawable.course_placeholder_4,
    104 to R.drawable.course_placeholder_5
)

@Composable
fun CourseCard(
    id: Int,
    title: String,
    description: String,
    price: String,
    rating: String,
    date: String,
    hasLike: Boolean,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column {
            Box {
                Image(
                    painter = painterResource(placeholders[id] ?: R.drawable.course_placeholder_1),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = rating,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(RatingBadgeBackground)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .align(Alignment.TopStart)
                )

                Icon(
                    painter = painterResource(
                        if (hasLike) R.drawable.ic_bookmark_filled
                        else R.drawable.ic_bookmark
                    ),
                    contentDescription = null,
                    tint = if (hasLike) PrimaryGreen else TextHint,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(32.dp)
                        .clickable { onFavoriteClick() }
                        .padding(4.dp)
                        .align(Alignment.TopEnd)
                )
            }

            Text(
                text = date,
                fontSize = 12.sp,
                color = TextHint,
                modifier = Modifier.padding(start = 12.dp, top = 8.dp)
            )

            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )

            Text(
                text = description,
                fontSize = 13.sp,
                color = TextSecondary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$price ₽",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Подробнее →",
                    fontSize = 14.sp,
                    color = PrimaryGreen
                )
            }
        }
    }
}
