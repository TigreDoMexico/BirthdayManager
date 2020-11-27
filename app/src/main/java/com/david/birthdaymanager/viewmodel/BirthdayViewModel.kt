package com.david.birthdaymanager.viewmodel

import androidx.lifecycle.*
import com.david.birthdaymanager.data.Birthday
import com.david.birthdaymanager.repository.BirthdayRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class BirthdayViewModel(private val repository: BirthdayRepository) : ViewModel() {
    val allBirthdays: LiveData<List<Birthday>> = repository.allBirthdays.asLiveData()

    fun insert(birthday: Birthday) = viewModelScope.launch {
        repository.insert(birthday)
    }
}

class BirthdayModelFactory(private val repository: BirthdayRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BirthdayViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return BirthdayViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}