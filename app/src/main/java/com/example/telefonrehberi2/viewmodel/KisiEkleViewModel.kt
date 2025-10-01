package com.example.telefonrehberi2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.telefonrehberi2.database.RehberDao
import com.example.telefonrehberi2.model.RehberModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KisiEkleViewModel @Inject constructor(private val dao: RehberDao) : ViewModel() {

    var selectedData by mutableStateOf(RehberModel(name = "", phoneNumber = ""))
    fun saveData(kisi: RehberModel) {
        viewModelScope.launch (Dispatchers.IO){
            dao.insertData(kisi)
        }
    }

    fun updateData(kisi: RehberModel) {
        viewModelScope.launch (Dispatchers.IO){
            dao.updateData(kisi)
        }
    }

    fun getSelectedData(id: Int) {
        viewModelScope.launch (Dispatchers.IO){
            selectedData = dao.getSelectedData(id)
        }
    }
}