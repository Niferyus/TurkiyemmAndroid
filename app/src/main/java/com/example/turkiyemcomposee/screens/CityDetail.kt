package com.example.turkiyemcomposee.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.turkiyemcomposee.R
import com.example.turkiyemcomposee.models.City
import com.example.turkiyemcomposee.ui.theme.TurkiyemcomposeeTheme
import okhttp3.internal.wait

@Composable
fun citydetailscreen(city: City?)
{
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF7C65A9), // Mor
                            Color(0xFF96D4CA)  // Yeşil
                        )
                    )
                )
                .padding(innerPadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                /*
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "City Background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .border(2.dp, Color.White, RoundedCornerShape(16.dp))
                        .width(150.dp)
                        .height(150.dp)
                )

                 */
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT8iq8srIMKYqPcAjx8_cZmAhPCpen-o9ZnjQ&s")
                        .crossfade(true)
                        .build(),
                    contentDescription = "Şehir resim",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .border(2.dp, Color.White, RoundedCornerShape(16.dp))
                        .width(300.dp)
                        .height(300.dp)
                )


                Spacer(modifier = Modifier.height(16.dp))

                // Çizgi
                HorizontalDivider(
                    color = Color.LightGray.copy(alpha = 0.5f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Şehir bilgileri
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = city?.name.orEmpty(),
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Nüfus: ${city?.population}",
                        color = Color.LightGray,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = city?.description.orEmpty(),
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Butonlar
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF503D88))
                        ) {
                            Text("Turistik mekanlar", color = Color.White)
                        }

                        Button(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF503D88))
                        ) {
                            Text("Oteller", color = Color.White)
                        }
                        Button(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF503D88))
                        ) {
                            Text("Restoranlar", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun citydetailPreview() {
    TurkiyemcomposeeTheme {
        citydetailscreen(city = City(1,"Sivas","20032421", description = "Adana, Türkiye'nin güneyinde yer alan, zengin tarihi ve kültürel mirasıyla bilinen bir şehirdir. Seyhan Nehri kıyısında bulunan Adana, ünlü Adana Kebabı ve lezzetli mutfağıyla dikkat çeker. Tarihi Taş Köprü, Sabancı Merkez Camii ve antik kentler gibi önemli turistik noktalara ev sahipliği yapar. Sıcak iklimi, geniş tarım arazileri ve kültürel etkinlikleriyle ziyaretçilerine eşsiz bir deneyim sunar.","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT8iq8srIMKYqPcAjx8_cZmAhPCpen-o9ZnjQ&s",0.00,0.0))
    }
}