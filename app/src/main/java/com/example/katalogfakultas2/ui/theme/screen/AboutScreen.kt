package com.example.katalogfakultas2.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.katalogfakultas2.R
import com.example.katalogfakultas2.ui.theme.KatalogFakultas2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    val programmerPhotoResId = R.drawable.profil
    val programmerName = "Java Maulana"
    val programmerNim = "2410431021"
    val programmerDepartment = "Matematika dan Sains Data"
    val programmerFaculty = "Fakultas Matematika dan Ilmu Pengetahuan Alam"
    val programmerOrigin = "Kabupaten Simalungun, Provinsi Sumatera Utara"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tentang Programmer") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = programmerPhotoResId),
                contentDescription = "Foto Programmer",
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(60))
                    .padding(bottom = 0.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(20.dp))

            InfoRow(label = "Nama Lengkap", value = programmerName)
            InfoRow(label = "NIM", value = programmerNim)
            InfoRow(label = "Departemen", value = programmerDepartment)
            InfoRow(label = "Fakultas", value = programmerFaculty)
            InfoRow(label = "Asal Daerah", value = programmerOrigin)
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
        Divider(modifier = Modifier.padding(top = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    KatalogFakultas2Theme {
        AboutScreen(navController = NavController(LocalContext.current))
    }
}