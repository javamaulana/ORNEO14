package com.example.katalogfakultas2.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.katalogfakultas2.model.Fakultas
import com.example.katalogfakultas2.viewmodel.FakultasViewModel
import com.example.katalogfakultas2.ui.theme.KatalogFakultas2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    fakultasViewModel: FakultasViewModel = viewModel()
) {
    val fakultasList by fakultasViewModel.fakultasList.collectAsState()
    val isLoading by fakultasViewModel.isLoadingList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Katalog Fakultas UNAND") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /* Sudah di Home */ }) {
                        Icon(Icons.Filled.Home, contentDescription = "Halaman Utama")
                    }
                    IconButton(onClick = { navController.navigate("about") }) {
                        Icon(Icons.Filled.AccountCircle, contentDescription = "Tentang Programmer")
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                if (fakultasList.isEmpty()) {
                    Text(
                        text = "Tidak ada data fakultas.",
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(fakultasList, key = { it.id }) { fakultas ->
                            FakultasCard(fakultas = fakultas, onItemClick = {
                                navController.navigate("detail/${fakultas.id}")
                            })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FakultasCard(
    fakultas: Fakultas,
    onItemClick: (Fakultas) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(fakultas) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column {
            Image(
                painter = painterResource(id = fakultas.gambarResId),
                contentDescription = "Gambar ${fakultas.nama}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = fakultas.nama,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    KatalogFakultas2Theme {
        HomeScreen(navController = NavController(LocalContext.current))
    }
}