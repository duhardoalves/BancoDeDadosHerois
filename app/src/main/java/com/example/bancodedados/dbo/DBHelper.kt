package com.example.bancodedados.dbo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.bancodedados.activity.Herois

class DBHelper(context : Context) : SQLiteOpenHelper(context, "database.db", null, 1){

    // Passar os comandos de criação da tabela
    val sql = "CREATE TABLE Herois (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, poder TEXT, planeta TEXT)"

    override fun onCreate(db: SQLiteDatabase) {

        // Banco vai realizar o comando de criação da tabela
        db.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    // Função de inserção de herois
    fun InserirHerois(nomeHeroi : String, poderHeroi : String, planetaOrigem : String){
        val db = this.writableDatabase

        val valoresObjeto = ContentValues()
        valoresObjeto.put("nome", nomeHeroi)
        valoresObjeto.put("poder", poderHeroi)
        valoresObjeto.put("planeta", planetaOrigem)

        db.insert("Herois", null, valoresObjeto)
        db.close()
    }

    fun listarHerois() : ArrayList<Herois>{
        // Lista de herois
        val listaHerois = ArrayList<Herois>()
        val db = this.readableDatabase
        val lerRegistros = db.rawQuery("SELECT * FROM Herois", null)

        if(lerRegistros.count > 0){
            lerRegistros.moveToFirst()
            do {
                // Procurando a referência da coluna
                val colunaId = lerRegistros.getColumnIndex("id")
                val colunaNome = lerRegistros.getColumnIndex("nome")
                val colunaPoder = lerRegistros.getColumnIndex("poder")
                val colunaPlaneta = lerRegistros.getColumnIndex("planeta")

                // Extrair o valor da coluna
                val valorId = lerRegistros.getInt(colunaId)
                val valorNome = lerRegistros.getString(colunaNome)
                val valorPoder = lerRegistros.getString(colunaPoder)
                val valorPlaneta = lerRegistros.getString(colunaPlaneta)

                // Criando nosso heroi
                var heroi = Herois(valorId, valorNome, valorPoder, valorPlaneta)
                listaHerois.add(heroi)
            }while (lerRegistros.moveToNext())
        }

        return listaHerois
    }

    fun ExcluirHeroi(idHeroi : Int){

        val db = this.writableDatabase
        db.delete("Herois", "id=?", arrayOf(idHeroi.toString()))
        db.close()

    }

    fun AlterarHeroi(idHeroi: Int, nomeHeroi: String, poderHeroi: String, planetaHeroi: String){

        val db = this.writableDatabase

        val valoresObjeto = ContentValues()
        valoresObjeto.put("nome", nomeHeroi)
        valoresObjeto.put("poder", poderHeroi)
        valoresObjeto.put("planeta", planetaHeroi)

        db.update("Herois", valoresObjeto, "id=?", arrayOf(idHeroi.toString()))
        db.close()
    }


}