package com.example.roomstarter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomstarter.room.User
import com.example.roomstarter.room.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomStarterViewModel @Inject constructor(
    private val userDao: UserDao
): ViewModel() {
    private var curUserIndex = DEFAULT_INDEX

    val userData = userDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = listOf()
        )

    val selectedData = userDao.loadAllById(3)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val selectedMultiData = userDao.loadAllByIdsFlow(2, 3)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = listOf()
        )

    fun insertData() {
        viewModelScope.launch(Dispatchers.IO) {
            curUserIndex++
            userDao.insert(User(curUserIndex, curUserIndex.toString(), curUserIndex.toString()))
        }
    }

    fun updateUser0() {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.update(User(0, "$curUserIndex", "$curUserIndex"))
        }
    }

    fun updateUser3() {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.update(User(3, "$curUserIndex", "$curUserIndex"))
        }
    }

    fun deleteLastData() {
        viewModelScope.launch(Dispatchers.IO) {
            if (curUserIndex != DEFAULT_INDEX) {
                userDao.deleteByUid(curUserIndex)
                curUserIndex--
            }
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.deleteAll()
            curUserIndex = DEFAULT_INDEX
        }
    }

    companion object {
        private const val DEFAULT_INDEX = -1
    }
}