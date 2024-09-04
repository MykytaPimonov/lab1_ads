package org.umsf.labs.lab1

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun App() {
    val current = remember { mutableStateOf(Page.Form) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val arrLine = remember { mutableStateOf("") }
        when (current.value) {
            Page.Form -> Form(arrLine) { current.value = Page.Result }
            Page.Result -> Body(arrLine.value)
        }
    }
}

@Composable
private fun Form(
    arrLine: MutableState<String>,
    toResult: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = arrLine.value,
            onValueChange = { arrLine.value = it },
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        Spacer(Modifier.fillMaxSize(0.1f))
        Button(
            onClick = toResult

        ) {
            Text("Sort")
        }
    }
}

@Composable
private fun Body(arrLine: String) {
    val arr = arrLine
        .split(", ")
        .map { it.toInt() }
        .toMutableList()
    val result = sort(arr)
    Result(result)
}

@Composable
private fun Result(
    result: SortResult
) {
    val verticalState = rememberScrollState()
    val horizontalState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .horizontalScroll(horizontalState)
    ) {
        Arr(result.unsorted)
        Spacer(Modifier.height(30.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .verticalScroll(verticalState)
        ) {
            result.intermediate
                .forEach {
                    Arr(it)
                    Spacer(Modifier.height(10.dp))
                }
        }
        Spacer(Modifier.height(20.dp))
        Text("Count of swapping: ${result.comparisonsCount}")
        Spacer(Modifier.height(10.dp))
        Arr(result.sorted)
    }
}

@Composable
private fun Arr(arr: List<Int>) {
    Row {
        arr.forEach { Element(it) }
    }
}

@Composable
private fun Element(value: Int) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .border(BorderStroke(1.dp, Color.Black)),
        contentAlignment = Alignment.Center
    ) {
        Text(value.toString())
    }
}

private enum class Page {
    Form, Result
}
