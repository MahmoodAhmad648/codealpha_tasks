package ma.developer.buynal2.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ma.developer.buynal2.R

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_drawer),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Buynal",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.weight(1f, fill = false)
                    )
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_cart),
                        contentDescription = "Cart Icon",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 321.dp, height = 209.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = 5.dp
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = R.drawable.banner),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = "Summer \nCollection\n2024",
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(16.dp),
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Featured Products",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Show all",
                        style = TextStyle(
                            color = Color(0xFF9B9B9B),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }

            item {
                LazyRowWithImagesAndText(
                    modifier = Modifier.fillMaxWidth(),
                    items = listOf(
                        R.drawable.image_1 to "Text for image 1",
                        R.drawable.image_2 to "Text for image 2",
                        R.drawable.image_3 to "Text for image 3",
                        R.drawable.image_4 to "Text for image 4",
                        R.drawable.image_5 to "Text for image 5",
                        R.drawable.image_6 to "Text for image 6",
                    )
                )
            }

            item {
                Image(
                    painter = painterResource(id = R.drawable.ic_banner),
                    contentDescription = "Banner Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 119.dp, height = 158.dp)
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Recommended",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Show all",
                        style = TextStyle(
                            color = Color(0xFF9B9B9B),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }

            item {
                LazyRowWithImagesAndText2(
                    modifier = Modifier.fillMaxWidth(),
                    items = listOf(
                        R.drawable.r_image1 to "Text for image 1",
                        R.drawable.image_3 to "Text for image 2",
                        R.drawable.image_3 to "Text for image 3",
                        R.drawable.r_image1 to "Text for image 4",
                        R.drawable.r_image2 to "Text for image 5",
                    )
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Top Collection",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Show all",
                        style = TextStyle(
                            color = Color(0xFF9B9B9B),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }

            item {
                Image(
                    painter = painterResource(id = R.drawable.banner_1),
                    contentDescription = "Banner Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 321.dp, height = 141.dp)
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.banner_3),
                        contentDescription = "Banner Image",
                        modifier = Modifier.size(width = 156.dp, height = 194.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.banner_4),
                        contentDescription = "Banner Image",
                        modifier = Modifier.size(width = 156.dp, height = 194.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun LazyRowWithImagesAndText2(
    modifier: Modifier,
    items: List<Pair<Int, String>>
) {
    LazyRow(modifier = modifier) {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(width = 213.dp, height = 66.dp),
                elevation = 5.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .clickable {

                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = item.first),
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 66.dp, height = 66.dp)
                            .clip(shape = RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = item.second,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
            }

        }
    }
}

@Composable
fun LazyRowWithImagesAndText(
    modifier: Modifier,
    items: List<Pair<Int, String>>
) {
    LazyRow(modifier = modifier) {
        items(items) { item ->
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable {

                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = item.first),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 126.dp, height = 172.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = item.second,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
        }
    }
}