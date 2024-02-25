package com.example.roomstarter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomstarter.room.User
import com.example.roomstarter.room.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomStarterViewModel @Inject constructor(
    private val userDao: UserDao
): ViewModel() {
    private val logTag = "RoomStarterViewModel"
    private var curUserIndex = DEFAULT_INDEX
    private var userDataJob: Job? = null
    private var usersDataJob: Job? = null

    private val _selectedUserData = MutableStateFlow<User?>(User())
    val selectedUserData: Flow<User?>
        get() = _selectedUserData

    private val _selectedUsersData = MutableStateFlow<List<User>>(listOf())
    val selectedUsersData: Flow<List<User>>
        get() = _selectedUsersData

    val selectedData = userDao.queryByUserId(3)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    fun initSelectedUserData(userId: Int) {
        Log.d(logTag, "start subscribe userId: $userId")
        userDataJob?.cancel()
        userDataJob = viewModelScope.launch {
            userDao.queryByUserId(userId)
                .flowOn(Dispatchers.IO)
                .distinctUntilChanged()
                .collect {
                    Log.d(logTag, "collect in ViewModel: $it")
                    _selectedUserData.emit(it)
                }
        }
    }

    fun initMultiSelectedUserData(vararg userIds: Int) {
        Log.d(logTag, "start subscribe userIds: ${userIds.toList()}")
        usersDataJob?.cancel()
        usersDataJob = viewModelScope.launch {
            userDao.queryByUserIds(userIds.toList())
                .flowOn(Dispatchers.IO)
                .collect {
                    Log.d(logTag, "collect in ViewModel: $it")
                    _selectedUsersData.emit(it)
                }
        }
    }

    fun insertData() {
        viewModelScope.launch(Dispatchers.IO) {
            curUserIndex++
            val insertUser = User(curUserIndex, curUserIndex.toString(), curUserIndex.toString())
            Log.d(logTag, "insertData: $insertUser")
            userDao.insert(insertUser)
        }
    }

    fun updateUser(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.update(User(userId, "$curUserIndex", "$curUserIndex"))
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
        private fun Flow<List<User>>.distinctUntilChangedUsersData() =
            distinctUntilChanged(areEquivalent = { old, new ->
                if (old.size != new.size) {
                    return@distinctUntilChanged false
                }

                for (i in old.indices) {
                    if (old[i] != new[i]) {
                        return@distinctUntilChanged false
                    }
                }

                return@distinctUntilChanged true
            })
    }
}