package com.example.telefonrehberi2.viewmodel

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
}