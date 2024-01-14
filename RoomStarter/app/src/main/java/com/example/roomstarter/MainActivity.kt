package com.example.roomstarter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.roomstarter.room.User
import com.example.roomstarter.room.UserDao
import com.example.roomstarter.ui.theme.RoomStarterTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RoomStarterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel by viewModels<RoomStarterViewModel>()
                    val allUserData by viewModel.userData.collectAsState()
                    val filteredUserData by viewModel.selectedData.collectAsState()
                    val filteredMultiUserData by viewModel.selectedMultiData.collectAsState()
                    Column {
                        Row {
                            EditUserRoomTable("Delete last User") { viewModel.deleteLastData() }
                            Spacer(modifier = Modifier.width(5.dp))
                            EditUserRoomTable("Delete All") { viewModel.deleteAll() }
                        }
                        EditUserRoomTable("Insert User") { viewModel.insertData() }
                        Row {
                            EditUserRoomTable("Edit User(0)") { viewModel.updateUser0() }
                            Spacer(modifier = Modifier.width(5.dp))
                            EditUserRoomTable("Edit User(3)") { viewModel.updateUser3() }
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        ShowUserInfo(allUserData)
                        Spacer(modifier = Modifier.height(20.dp))
                        ShowLatestUserInfo(filteredUserData)
                        Spacer(modifier = Modifier.height(20.dp))
                        ShowUserInfo(filteredMultiUserData, doLog = true)
                    }
                }
            }
        }
        testUserDao(userDao)
    }

    private fun testUserDao(userDao: UserDao) {
        lifecycleScope.launch(Dispatchers.IO) {
            val all = userDao.queryAll() // return type: List<User>
            Log.d(TAG, "$all, size: ${all.size}")

            val sd = userDao.findByName("", "") // return type: User
//            Log.d(TAG, "${sd.firstName}")
        }
    }

}

private const val TAG = "MainActivity"

@Composable
fun ShowUserInfo(userInfo: List<User>, doLog: Boolean = false) {
    if (doLog) {
        Log.d(TAG, "userInfo: $userInfo")
    }
    LazyColumn {
        items(userInfo) {
            Text(text = "uid: ${it.uid}, firstName: ${it.firstName}, lastName: ${it.lastName}")
        }
    }
}

@Composable
fun ShowLatestUserInfo(userInfo: User?) {
    Log.d(TAG, "$userInfo")
    userInfo?.let {
        Text(text = "uid: ${userInfo.uid}, firstName: ${it.firstName}, lastName: ${it.lastName}")
    }
}

@Composable
fun EditUserRoomTable(
    text: String,
    onClick: () -> Unit
) {
    Button(modifier = Modifier.wrapContentSize(), onClick = onClick) {
        Text(text = text)
    }
}