package com.example.telefonrehberi2.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.telefonrehberi2.model.RehberModel
import com.example.telefonrehberi2.viewmodel.KisiEkleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KisiEkleScreen(navController: NavController, id: Int? = null, viewModel: KisiEkleViewModel = hiltViewModel()) {

    if (id != null){

        LaunchedEffect(key1 = Unit) {
            viewModel.getSelectedData(id)
        }

        val kisi = viewModel.selectedData

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SpecialTextField(
                value = kisi.name,
                label = "Kişi Adı",
                keyboardType = KeyboardType.Text
            ) {
                viewModel.selectedData = kisi.copy(name = it)
                //.copy() -> nesnenin bazı alanlarını değiştirerek yeni nesne oluşturur
            }

            Spacer(Modifier.height(12.dp))

            SpecialTextField(
                value = kisi.phoneNumber,
                label = "Telefon Numarası",
                keyboardType = KeyboardType.Phone
            ) {
                viewModel.selectedData = kisi.copy(phoneNumber = it)
            }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    viewModel.updateData(viewModel.selectedData)
                    navController.navigate("anasayfa"){ popUpTo("kisi_ekle/{kisiId}"){ inclusive = true }}
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2), contentColor = Color.White)
            ) {
                Text(text = "Kaydet", fontSize = 18.sp)
            }
        }

    } else {
        val context = LocalContext.current

        var name by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SpecialTextField(
                value = name,
                label = "Kişi Adı",
                keyboardType = KeyboardType.Text
            ) {
                name = it
            }

            Spacer(Modifier.height(12.dp))

            SpecialTextField(
                value = phoneNumber,
                label = "Telefon Numarası",
                keyboardType = KeyboardType.Phone
            ) {
                phoneNumber = it
            }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {

                    if (name.isBlank() || phoneNumber.isBlank()){
                        Toast.makeText(context, "Boş bırakmayın!", Toast.LENGTH_SHORT).show()
                    }else {
                        val kisi = RehberModel(name, phoneNumber)
                        viewModel.saveData(kisi)
                        navController.navigate("anasayfa"){ popUpTo("kisi_ekle"){ inclusive = true }}
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2), contentColor = Color.White)
            ) {
                Text(text = "Kaydet", fontSize = 18.sp)
            }
        }

    }
}

@Composable
fun SpecialTextField(
    value: String,
    label: String,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp)
    )
}