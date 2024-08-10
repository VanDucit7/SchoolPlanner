package ui.presentation.configterm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import schoolplanner.composeapp.generated.resources.Res
import schoolplanner.composeapp.generated.resources.configure_terms_des
import schoolplanner.composeapp.generated.resources.ic_add
import schoolplanner.composeapp.generated.resources.ic_book_saved
import schoolplanner.composeapp.generated.resources.lbl_add_term
import schoolplanner.composeapp.generated.resources.lbl_configure
import schoolplanner.composeapp.generated.resources.lbl_end_date
import schoolplanner.composeapp.generated.resources.lbl_save
import schoolplanner.composeapp.generated.resources.lbl_start_date
import schoolplanner.composeapp.generated.resources.lbl_term
import schoolplanner.composeapp.generated.resources.lbl_title
import schoolplanner.composeapp.generated.resources.next
import ui.components.TermItemView

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterialApi::class)
@Composable
fun ConfigTermScreen() {
    val viewModel = koinViewModel<ConfigTermViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheetContent(
                editedName = uiState.editTerm.name,
                editedStartDate = uiState.editTerm.startDate,
                editedEndDate = uiState.editTerm.endDate,
                onSaveClick = { name, startDate, endDate ->
                    viewModel.updateTerm(
                        name = name,
                        startDate = startDate,
                        endDate = endDate
                    )
                    coroutineScope.launch {
                        scaffoldState.bottomSheetState.collapse()
                    }
                }
            )
        },
        sheetPeekHeight = 0.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize() // Padding to ensure space for the button
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 64.dp)
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                Row(modifier = Modifier.align(Alignment.Start)) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Image(
                        painter = painterResource(resource = Res.drawable.ic_book_saved),
                        contentDescription = "Icon",
                        modifier = Modifier
                            .size(64.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(Res.string.lbl_term),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start).padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(resource = Res.string.configure_terms_des),
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.Start).padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    itemsIndexed(uiState.terms) { index, term ->
                        TermItemView(term, onItemClick = {
                            viewModel.editTerm(index, term)
                            coroutineScope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        })
                        Divider(
                            modifier = Modifier.fillMaxWidth().padding(start = 56.dp),
                            thickness = 1.dp,
                            color = Color.LightGray
                        )
                    }
                    item {
                        val term = stringResource(Res.string.lbl_term);
                        AddNewTermItem(onAddNewTermClick = {
                            viewModel.addTerm(term)
                        })
                    }
                }
            }
            // Button always at the bottom
            Button(
                onClick = { /* Handle next action */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Text(text = stringResource(Res.string.next))
            }
        }

    }
}


@Composable
fun AddNewTermItem(onAddNewTermClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = onAddNewTermClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(resource = Res.drawable.ic_add),
            contentDescription = "Add Icon",
            modifier = Modifier.size(50.dp).padding(16.dp)
        )
        Text(
            text = stringResource(Res.string.lbl_add_term),
            fontSize = 16.sp
        )
    }
}

@Composable
fun BottomSheetContent(
    editedName: String,
    editedStartDate: String,
    editedEndDate: String,
    onSaveClick: (String, String, String) -> Unit
) {
    var name by remember { mutableStateOf(editedName) }
    val startDate by remember { mutableStateOf(editedStartDate) }
    val endDate by remember { mutableStateOf(editedEndDate) }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .heightIn(max = 400.dp, min = 250.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(Res.string.lbl_configure),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
        )
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(Res.string.lbl_title)) },
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .background(Color.LightGray.copy(0.7f))
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(Res.string.lbl_start_date),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = startDate,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .background(Color.LightGray.copy(0.7f))
                    .padding(start = 16.dp)
                    .clickable {

                    },
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(Res.string.lbl_end_date),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = endDate,
                    fontSize = 14.sp
                )
            }
        }
        TextButton(
            onClick = {
                onSaveClick(name, startDate, endDate)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(stringResource(Res.string.lbl_save))
        }
    }
}
