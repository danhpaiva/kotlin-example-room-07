package com.example.roomapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.roomapp.model.UsuarioModel
import com.example.roomapp.repository.UsuarioDataBase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Aqui estamos instanciando a classe do Room, ao mesmo tempo que estamos retornando a classe de usuarioDao
        val usuarioDatabase = UsuarioDataBase.getDataBase(this).usuarioDAO()

        //Observe como fazemos o insert, passando uma instância de UsuarioModel, modificando os atributos:
        val retornoInsert = usuarioDatabase.insertUser(UsuarioModel().apply {
            this.nome = "Daniel Paiva"
            this.idade = 32
        })

        val retornoSelectMultiplo = usuarioDatabase.getAll()//Retorna todos os registros

        for(item in retornoSelectMultiplo){
            Log.d("Retorno Múltiplo", "id_usuario: ${item.id_usuario}, nome: ${item.nome}, idade: ${item.idade}")
        }
    }
}