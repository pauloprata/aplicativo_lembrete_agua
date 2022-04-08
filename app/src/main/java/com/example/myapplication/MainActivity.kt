package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var edit_peso: EditText
    private lateinit var edit_idade: EditText
    private lateinit var btn_calcular: Button
    private lateinit var txt_resultado_ml: EditText
    private lateinit var refresh_result: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
        InciciaComponentes()

  btn_calcular.setOnClickListener {
       if (edit_peso.text.toString().isEmpty()){
           Toast.makeText(this,R.string.toast_informe_peso,Toast.LENGTH_SHORT).show()
       }else if (edit_idade.text.toString().isEmpty()){
           Toast.makeText(this,R.string.toast_informe_idade,Toast.LENGTH_SHORT).show()
       }
  }

    }
    private fun InciciaComponentes(){
        edit_peso = findViewById(R.id.edit_peso)
        edit_idade = findViewById(R.id.edit_idade)
        btn_calcular = findViewById(R.id.btn_calcular)
        txt_resultado_ml = findViewById(R.id.txt_resultado_ml)
        refresh_result = findViewById(R.id.refresh_result)
    }
}