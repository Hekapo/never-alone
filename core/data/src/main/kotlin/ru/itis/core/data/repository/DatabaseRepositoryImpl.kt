package ru.itis.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.itis.core.database_constants.DatabaseConstants.NODE_CURRENT_USER
import ru.itis.core.database_constants.DatabaseConstants.NODE_USERS
import ru.itis.core.domain.models.User
import ru.itis.core.domain.repository.DatabaseRepository
import ru.itis.core.domain.viewstates.ResultState
import javax.inject.Inject

/**
 * Created by Iskandar on 10.04.2022.
 */

class DatabaseRepositoryImpl @Inject constructor(
    private val databaseReference: DatabaseReference,
    private val firebaseAuth: FirebaseAuth
) : DatabaseRepository {

    private val _userFlowProcessState = MutableStateFlow<ResultState<User, Any>>(ResultState.None)

    override suspend fun addUser(user: User) {
        databaseReference.child(NODE_USERS).child(user.id!!).setValue(user.toMap())
        databaseReference.child(NODE_CURRENT_USER).child(user.id!!).setValue(user.toMap())
    }

    override suspend fun getUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUserId(): String? {
        return firebaseAuth.currentUser?.uid
    }

    override suspend fun fetchCurrentUser() {
        _userFlowProcessState.update { ResultState.InProcess }
        databaseReference.child(NODE_CURRENT_USER)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    _userFlowProcessState.update {
                        val user = snapshot.getValue<User>()
                        if (user != null) {
                            ResultState.Success(data = user)
                        } else {
                            ResultState.Error(message = "User not found")
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    _userFlowProcessState.update {
                        ResultState.Error(message = error.message)
                    }
                }
            })
    }

    override val userFlowProcess: Flow<ResultState<User, Any>>
        get() = _userFlowProcessState.asStateFlow()

}
