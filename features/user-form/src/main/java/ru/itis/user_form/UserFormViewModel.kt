package ru.itis.user_form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.IDatastoreUseCase
import javax.inject.Inject

/**
 * Created by Iskandar on 27.05.2022.
 */

internal class UserFormViewModel() : ViewModel() {

    class UserFormViewModelFactory @Inject constructor(
        private val datastoreUseCase: IDatastoreUseCase,
        private val dispatcher: DispatchersProvider
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserFormViewModel() as T
        }
    }
}
