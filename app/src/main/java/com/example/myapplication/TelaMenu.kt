package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TelaMenu(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        CaixaSimples(cor = Color(0xFFE0E0E0), altura = 60.dp, texto = "Cabeçalho: FOTO E MENSAGEM DE BOAS VINDAS AO USUARIO")
        CaixaSimples(cor = Color(0xFFF5F5F5), altura = 50.dp, texto = "Barra de Busca")
        Text("Ofertas da Semana", fontSize = 18.sp)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CaixaSimples(cor = Color(0xFFB3E5FC), altura = 150.dp, texto = "Carro 1", modifier = Modifier.weight(1f))
            CaixaSimples(cor = Color(0xFFB3E5FC), altura = 150.dp, texto = "Carro 2", modifier = Modifier.weight(1f))
        }
        Text("Categorias", fontSize = 18.sp)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CaixaSimples(cor = Color(0xFFC5CAE9), altura = 80.dp, texto = "CATEGORIA_1", modifier = Modifier.weight(1f))
            CaixaSimples(cor = Color(0xFFC5CAE9), altura = 80.dp, texto = "CATEGORIA_2", modifier = Modifier.weight(1f))
            CaixaSimples(cor = Color(0xFFC5CAE9), altura = 80.dp, texto = "CATEGORIA_3", modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.weight(1f))
        CaixaSimples(cor = Color.LightGray, altura = 60.dp, texto = "Menu Inferior: Início | Carros | Perfil")
    }
}

@Composable
fun CaixaSimples(cor: Color, altura: Dp, texto: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(altura)
            .background(cor)
    ) {
        Text(text = texto, color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEsboco() {
    TelaMenu(PaddingValues(0.dp))
}