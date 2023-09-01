package com.example.bancodedados.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class Herois(val id : Int, var nomeHeroi : String, var poderHeroi : String, var planetaOrigem : String) {

    companion object{
        fun SalvarPreferencias(context: Context, herois: Herois){
            // Salvar as informações em 'cache'
            val preferencias = context.getSharedPreferences("chaveGeral", AppCompatActivity.MODE_PRIVATE)

            val editorPreferencia = preferencias.edit()
            editorPreferencia.putInt("idHeroi", herois.id)
            editorPreferencia.putString("nomeHeroi", herois.nomeHeroi)
            editorPreferencia.putString("poderHeroi", herois.poderHeroi)
            editorPreferencia.putString("planetaHeroi", herois.planetaOrigem)
            editorPreferencia.commit()
        }

        fun LimparPreferencias(context: Context){
            // Salvar as informações em 'cache'
            val preferencias = context.getSharedPreferences("chaveGeral", AppCompatActivity.MODE_PRIVATE)

            val editorPreferencia = preferencias.edit()
            editorPreferencia.remove("idHeroi")
            editorPreferencia.remove("nomeHeroi")
            editorPreferencia.remove("poderHeroi")
            editorPreferencia.remove("planetaHeroi")
            editorPreferencia.apply()
        }

        fun CapturarPreferencias(context: Context):Herois{
            // Resgatar as informações em 'cache'
            val preferencias = context.getSharedPreferences("chaveGeral", AppCompatActivity.MODE_PRIVATE)

            // Criando o heroi com as informações salvas
            val heroi = Herois(preferencias.getInt("idHeroi", 0),
                               preferencias.getString("nomeHeroi", "").toString(),
                               preferencias.getString("poderHeroi", "").toString(),
                               preferencias.getString("planetaHeroi", "").toString()
            )

            return heroi
        }
    }

    override fun toString(): String {
        return "id: ${id}, ${nomeHeroi}: seu poder é ${poderHeroi}, e seu planeta de origem é: ${planetaOrigem}"
    }
}