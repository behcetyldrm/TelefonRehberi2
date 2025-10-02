package com.example.telefonrehberi2.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.telefonrehberi2.model.RehberModel
import com.example.telefonrehberi2.viewmodel.AnasayfaViewModel

@Composable
fun AnasayfaScreen(navController: NavController, viewModel: AnasayfaViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = Unit) {
        viewModel.gelAllData()
    }
    val kisi_listesi = viewModel.kisi_listesi

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 10.dp)
    ) {
        items(kisi_listesi) { kisi ->
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = kisi.name, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(3.dp))
                    Text(text = kisi.phoneNumber, fontSize = 18.sp, modifier = Modifier.padding(3.dp))
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(10.dp)
                ) {
                    IconButton(
                        onClick = { navController.navigate("kisi_detay/${kisi.id}") },
                        colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White)
                    ) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "edit")
                    }
                    IconButton(
                        onClick = { viewModel.deleteData(kisi) },
                        colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White)
                    ) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                    }
                }
            }
            HorizontalDivider(thickness = 1.dp, color = Color.Black) //Çizgi koymak için
        }
    }
}


