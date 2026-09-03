package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold { innerPadding ->
                    //TelaPesquisa(innerPadding)
                    TelaMenu(innerPadding)
                    //TelaPagamento(innerPadding)
                    // AQUI MUDA AS TELAS !!!!!
                }
            }
        }
    }
}

private val CorAzul = Color(0xFF2F5CE0)
private val CorFundoTela = Color(0xFFF4F5F7)
private val CorBranco = Color.White
private val CorTextoEscuro = Color(0xFF1C2340)
private val CorTextoCinza = Color(0xFF8A8F98)
private val CorChipBorda = Color(0xFFE0E0E0)
private val CorCirculoSeta = Color(0xFFE7ECFB)

data class Carro(
    val nome: String,
    val categoria: String,
    val avaliacao: String,
    val precoPorDia: String
)

private val listaDeCarros = listOf(
    Carro("Chevrolet Onix 2024", "Hatchback", "4.8", "R$ 110"),
    Carro("Jeep Compass 2024", "SUV", "4.9", "R$ 180"),
    Carro("Tesla Model 3 2023", "Elétrico", "5.0", "R$ 320")
)

private val listaDeFiltros = listOf("Sedã", "SUV", "Flex", "R$ 100 - R$ 300")

@Composable
fun TelaPesquisa(innerPadding: PaddingValues) {
    var localidade by remember { mutableStateOf("") }

    val filtrosSelecionados = remember { mutableStateListOf(false, false, false, false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        color = CorFundoTela
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            TopoPesquisa()

            LinhaLocalidadeEData(
                localidade = localidade,
                aoMudarLocalidade = { localidade = it }
            )

            LinhaDeFiltros(
                filtros = listaDeFiltros,
                selecionados = filtrosSelecionados,
                aoClicarFiltro = { indice ->
                    filtrosSelecionados[indice] = !filtrosSelecionados[indice]
                }
            )

            Text(
                text = "${listaDeCarros.size} veículos encontrados",
                fontSize = 13.sp,
                color = CorTextoCinza,
                modifier = Modifier.padding(start = 16.dp, top = 12.dp, bottom = 6.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                listaDeCarros.forEach { carro ->
                    CardDoCarro(carro)
                    Spacer(modifier = Modifier.height(14.dp))
                }
            }

            NavegacaoInferior()
        }
    }
}

@Composable
fun TopoPesquisa() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(CorBranco)
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Text("‹", fontSize = 22.sp, color = CorTextoEscuro)
        }

        Text(
            text = "Pesquisa",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = CorTextoEscuro,
            modifier = Modifier.weight(1f),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(CorBranco)
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Text("↻", fontSize = 20.sp, color = CorAzul)
        }
    }
}

@Composable
fun LinhaLocalidadeEData(localidade: String, aoMudarLocalidade: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = localidade,
            onValueChange = aoMudarLocalidade,
            placeholder = { Text("Digite a localidade", color = CorTextoCinza) },
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            shape = RoundedCornerShape(14.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.width(10.dp))

        Box(
            modifier = Modifier
                .width(150.dp)
                .height(56.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(CorBranco)
                .padding(horizontal = 14.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text("15 Out - 20 Out", color = CorTextoEscuro, fontSize = 14.sp)
        }
    }
}

@Composable
fun LinhaDeFiltros(
    filtros: List<String>,
    selecionados: List<Boolean>,
    aoClicarFiltro: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        filtros.forEachIndexed { indice, nome ->
            val estaSelecionado = selecionados[indice]

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(18.dp))
                    .background(if (estaSelecionado) CorAzul else CorBranco)
                    .then(
                        if (!estaSelecionado)
                            Modifier.border(1.dp, CorChipBorda, RoundedCornerShape(18.dp))
                        else Modifier
                    )
                    .clickable { aoClicarFiltro(indice) }
                    .padding(horizontal = 18.dp, vertical = 10.dp)
            ) {
                Text(
                    text = nome,
                    fontSize = 13.sp,
                    color = if (estaSelecionado) CorBranco else CorTextoEscuro
                )
            }

            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}
@Composable
fun CardDoCarro(carro: Carro) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CorBranco),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = carro.nome,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = CorTextoEscuro
                )
                Text(
                    text = carro.categoria,
                    fontSize = 12.sp,
                    color = CorTextoCinza
                )
                Row(
                    modifier = Modifier.padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("★", fontSize = 13.sp, color = CorAzul)
                    Text(" ${carro.avaliacao}", fontSize = 13.sp, color = CorTextoEscuro)
                }
                Text(
                    text = "${carro.precoPorDia} / dia",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = CorTextoEscuro
                )
            }

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(CorCirculoSeta)
                    .clickable {  },
                contentAlignment = Alignment.Center
            ) {
                Text("›", fontSize = 20.sp, color = CorAzul)
            }
        }
    }
}

@Composable
fun NavegacaoInferior() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(CorBranco),
    ) {
        ItemNav("Início", ativo = false, modifier = Modifier.weight(1f))
        ItemNav("Carros", ativo = false, modifier = Modifier.weight(1f))
        ItemNav("Agendamento", ativo = true, modifier = Modifier.weight(1f))
        ItemNav("Favoritos", ativo = false, modifier = Modifier.weight(1f))
        ItemNav("Perfil", ativo = false, modifier = Modifier.weight(1f))
    }
}

@Composable
fun ItemNav(nome: String, ativo: Boolean, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable {},
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = nome,
            fontSize = 11.sp,
            color = if (ativo) CorAzul else CorTextoCinza
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaPesquisa() {
    MyApplicationTheme {
        TelaPesquisa(PaddingValues(0.dp))
    }
}