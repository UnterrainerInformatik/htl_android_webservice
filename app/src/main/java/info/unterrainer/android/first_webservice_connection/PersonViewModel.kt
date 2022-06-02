package info.unterrainer.android.first_webservice_connection

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PersonViewModel : ViewModel() {
    private val _personList = mutableStateListOf<Person>()
    var errorMessage: String by mutableStateOf("")
    val personList: List<Person>
        get() = _personList

    fun getPersonList() {
        viewModelScope.launch {
            val service = PersonService.getInstance()
            try {
                _personList.clear()
                _personList.addAll(service.getPersons())

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}