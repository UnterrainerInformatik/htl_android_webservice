package info.unterrainer.android.first_webservice_connection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = PersonViewModel()
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PersonView(vm)
            }
        }
    }
}

@Composable
fun PersonView(vm: PersonViewModel) {

    LaunchedEffect(Unit, block = {
        vm.getPersonList()
    })

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text("Persons")
                    }
                })
        },
        content = {
            if (vm.errorMessage.isEmpty()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    LazyColumn(modifier = Modifier.fillMaxHeight()) {
                        items(vm.personList) { person ->
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(0.dp, 0.dp, 16.dp, 0.dp)
                                    ) {
                                        Text(
                                            person.name,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Text(
                                        person.birth.format(DateTimeFormatter.ISO_DATE),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                Divider()
                            }
                        }
                    }
                }
            } else {
                Text(vm.errorMessage)
            }
        }
    )
}

@Composable
fun ToDoView(vm: ToDoViewModel) {

    LaunchedEffect(Unit, block = {
        vm.getTodoList()
    })

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text("Persons")
                    }
                })
        },
        content = {
            if (vm.errorMessage.isEmpty()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    LazyColumn(modifier = Modifier.fillMaxHeight()) {
                        items(vm.todoList) { todo ->
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(0.dp, 0.dp, 16.dp, 0.dp)
                                    ) {
                                        Text(
                                            todo.title,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Checkbox(checked = todo.completed, onCheckedChange = null)
                                }
                                Divider()
                            }
                        }
                    }
                }
            } else {
                Text(vm.errorMessage)
            }
        }
    )
}