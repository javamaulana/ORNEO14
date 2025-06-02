package com.example.katalogfakultas2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.katalogfakultas2.R
import com.example.katalogfakultas2.model.Fakultas
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FakultasViewModel : ViewModel() {

    private val _fakultasList = MutableStateFlow<List<Fakultas>>(emptyList())
    val fakultasList: StateFlow<List<Fakultas>> = _fakultasList.asStateFlow()

    private val _isLoadingList = MutableStateFlow(false)
    val isLoadingList: StateFlow<Boolean> = _isLoadingList.asStateFlow()

    private val _selectedFakultas = MutableStateFlow<Fakultas?>(null)
    val selectedFakultas: StateFlow<Fakultas?> = _selectedFakultas.asStateFlow()

    private val _isLoadingDetail = MutableStateFlow(false)
    val isLoadingDetail: StateFlow<Boolean> = _isLoadingDetail.asStateFlow()

    private val daftarFakultasFull = listOf(
        Fakultas(1, "Fakultas Matematika dan Ilmu Pengetahuan Alam", "Berpusat pada pengembangan ilmu dasar seperti matematika, fisika, kimia, dan biologi serta terapannya.", 11, R.drawable.fmipa),
        Fakultas(2, "Fakultas Hukum", "Fokus pada pendidikan dan pengembangan ilmu hukum, mencetak praktisi dan akademisi hukum yang profesional dan berintegritas.", 4, R.drawable.hukum),
        Fakultas(3, "Fakultas Pertanian", "Mengembangkan ilmu dan teknologi di bidang pertanian untuk mendukung ketahanan pangan dan agribisnis yang berkelanjutan.", 10, R.drawable.pertanian),
        Fakultas(4, "Fakultas Kedokteran", "Menyelenggarakan pendidikan dokter dan tenaga kesehatan lainnya, serta penelitian untuk meningkatkan kualitas kesehatan masyarakat.", 19, R.drawable.kedokteran),
        Fakultas(5, "Fakultas Ekonomi dan Bisnis", "Mempersiapkan tenaga ahli di bidang ekonomi, manajemen, akuntansi, dan bisnis yang mampu bersaing secara global.", 18, R.drawable.feb),
        Fakultas(6, "Fakultas Peternakan", "Mengembangkan ilmu pengetahuan dan teknologi di bidang peternakan untuk meningkatkan produktivitas dan kesejahteraan ternak serta industri terkait.", 5, R.drawable.peternakan),
        Fakultas(7, "Fakultas Ilmu Budaya", "Mengkaji dan mengembangkan ilmu-ilmu terkait bahasa, sastra, sejarah, dan aspek budaya lainnya.", 9, R.drawable.fib),
        Fakultas(8, "Fakultas Teknik", "Menyelenggarakan pendidikan dan riset dalam berbagai disiplin ilmu keteknikan untuk menghasilkan insinyur yang kompeten.", 10, R.drawable.teknik),
        Fakultas(9, "Fakultas Ilmu Sosial dan Ilmu Politik", "Fokus pada studi tentang masyarakat, negara, politik, dan hubungan internasional.", 13, R.drawable.fisip),
        Fakultas(10, "Fakultas Farmasi", "Menyelenggarakan pendidikan tinggi di bidang farmasi, menghasilkan apoteker dan peneliti obat-obatan.", 4, R.drawable.farmasi),
        Fakultas(11, "Fakultas Teknologi Pertanian", "Mengaplikasikan prinsip-prinsip keteknikan dalam bidang pertanian, termasuk teknologi pangan dan hasil pertanian.", 5, R.drawable.fateta),
        Fakultas(12, "Fakultas Kesehatan Masyarakat", "Fokus pada pencegahan penyakit dan promosi kesehatan di tingkat populasi.", 3, R.drawable.fkm),
        Fakultas(13, "Fakultas Teknologi Informasi", "Mengembangkan ilmu pengetahuan dan teknologi di bidang informatika, sistem informasi, dan teknologi komputer.", 4, R.drawable.fti),
        Fakultas(14, "Fakultas Keperawatan", "Menghasilkan tenaga perawat profesional yang kompeten dalam memberikan asuhan keperawatan.", 3, R.drawable.keperawatan),
        Fakultas(15, "Fakultas Kedokteran Gigi", "Menyelenggarakan pendidikan dokter gigi dan penelitian di bidang kesehatan gigi dan mulut.", 3, R.drawable.fkg),
    )

    init {
        loadFakultasList()
    }

    fun loadFakultasList() {
        viewModelScope.launch {
            _isLoadingList.value = true
            delay(1500)
            _fakultasList.value = daftarFakultasFull
            _isLoadingList.value = false
        }
    }

    fun loadFakultasDetail(fakultasId: Int) {
        viewModelScope.launch {
            _isLoadingDetail.value = true
            _selectedFakultas.value = null
            delay(1000)
            _selectedFakultas.value = daftarFakultasFull.find { it.id == fakultasId }
            _isLoadingDetail.value = false
        }
    }
}