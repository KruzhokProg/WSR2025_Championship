package presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import database.DataBaseUtils.getAllDepartments
import database.DataBaseUtils.getAllDivisions
import database.DataBaseUtils.getAllUnits
import database.DataBaseUtils.getEmployeeByDepartment
import database.DataBaseUtils.getDepartmentIdByName
import database.DataBaseUtils.getDivisionIdByName
import database.DataBaseUtils.getEmployeeByUnits
import database.DataBaseUtils.getUnitIdByName


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val scrollStateForDepartmentRow = rememberScrollState()
    val scrollStateForDivisionRow = rememberScrollState()
    val scrollStateForUnitRow = rememberScrollState()
    val scrollStateForEmployeeColumn = rememberScrollState()

    val chosenDepartmentId = remember { mutableStateOf(-1) }
    val chosenDepartmentName = remember { mutableStateOf("") }

    val chosenDivisionId = remember { mutableStateOf<Int>(-1) }
    val chosenDivisionName = remember { mutableStateOf("") }

    val chosenUnitId = remember { mutableStateOf<Int>(-1) }
    val chosenUnitName = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xff78B24B))
                .height(65.dp)
                .align(alignment = Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically)
        {
            Spacer(modifier = Modifier.width(10.dp))

            Image(
                painter = painterResource("/drawable/Logo.png"),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(55.dp)
            )

            Spacer(
                modifier = Modifier
                    .width(15.dp)
            )

            Text(
                text = " Организационная структура ",
                modifier = Modifier
                    .background(color = Color.White, RoundedCornerShape(10.dp))
                    .padding(vertical = 8.dp, horizontal = 30.dp)
                    .align(alignment = Alignment.CenterVertically), overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            modifier = Modifier.weight(1f).background(color = Color(0xffaaaaaa)),
        ) {
            Column(modifier = Modifier.fillMaxHeight().weight(1f)) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(5.dp),
                        content = { Text(text = "Дороги россии") },
                        colors = ButtonDefaults.buttonColors(Color(0xff78B24B))
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(modifier = Modifier.horizontalScroll(scrollStateForDepartmentRow)) {
                    getAllDepartments().forEach {
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(
                            onClick = {
                                chosenDepartmentId.value = getDepartmentIdByName(it)
                                chosenDepartmentName.value = it

                                chosenDivisionId.value = -1
                                chosenDivisionName.value = " "

                                chosenUnitId.value = -1
                                chosenUnitName.value = " "
                            },
                            shape = RoundedCornerShape(5.dp),
                            content = { Text(text = it) },
                            colors = ButtonDefaults.buttonColors(Color(0xff78B24B))
                        )
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(modifier = Modifier.horizontalScroll(scrollStateForDivisionRow)) {
                    getAllDivisions().forEach {
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(
                            onClick = {
                                chosenDivisionId.value = getDivisionIdByName(it)
                                chosenDivisionName.value = it

                                chosenDepartmentId.value = -1
                                chosenDepartmentName.value = " "

                                chosenUnitId.value = -1
                                chosenUnitName.value = " "
                            },
                            shape = RoundedCornerShape(5.dp),
                            content = { Text(text = it) },
                            colors = ButtonDefaults.buttonColors(Color(0xff78B24B))
                        )
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(modifier = Modifier.horizontalScroll(scrollStateForUnitRow)) {
                    getAllUnits().forEach {
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(
                            onClick = {
                                chosenUnitId.value = getUnitIdByName(it)
                                chosenUnitName.value = it

                                chosenDepartmentId.value = -1
                                chosenDepartmentName.value = " "

                                chosenDivisionId.value = -1
                                chosenDivisionName.value = " "
                            },
                            shape = RoundedCornerShape(5.dp),
                            content = { Text(text = it) },
                            colors = ButtonDefaults.buttonColors(Color(0xff78B24B))
                        )
                    }
                }
            }
            VerticalDivider(modifier = Modifier.fillMaxHeight())
            Column(modifier = Modifier.fillMaxHeight().weight(1f).verticalScroll(scrollStateForEmployeeColumn)) {
                getEmployeeByDepartment(chosenDepartmentId.value).forEach {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Box(modifier = Modifier.background(color = Color(0xff78B24B))) {
                            Column(modifier = Modifier) {
                                Row(
                                    modifier = Modifier
                                ) {
                                    Text(text = chosenDepartmentName.value)
                                    Text(" - ")
                                    Text(text = it.position)
                                }
                                Text(text = it.fullName)
                            }
                        }
                    }
                }

                getEmployeeByUnits(chosenUnitId.value).forEach {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Box(modifier = Modifier.background(color = Color(0xff78B24B))) {
                            Column(modifier = Modifier) {
                                Row(modifier = Modifier) {
                                    Text(text = chosenUnitName.value)
                                    Text(" - ")
                                    Text(text = it.position)
                                }
                                Text(text = it.fullName)
                            }
                        }
                    }
                }
            }
        }
    }
}