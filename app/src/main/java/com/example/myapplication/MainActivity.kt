package com.example.myapplication

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.myapplication.model.CalcularIngestaoDiaria
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var edit_peso: EditText
    private lateinit var edit_idade: EditText
    private lateinit var btn_calcular: Button
    private lateinit var txt_resultado_ml: EditText
    private lateinit var refresh_result: ImageView

    private lateinit var calcularIngestaoDiaria: CalcularIngestaoDiaria

    private var resultadoMl = 0.0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
        InciciaComponentes()
        calcularIngestaoDiaria = CalcularIngestaoDiaria()

  btn_calcular.setOnClickListener {
       if (edit_peso.text.toString().isEmpty()){
           Toast.makeText(this,R.string.toast_informe_peso,Toast.LENGTH_SHORT).show()
       }else if (edit_idade.text.toString().isEmpty()){
           Toast.makeText(this,R.string.toast_informe_idade,Toast.LENGTH_SHORT).show()
       }else{
           val peso = edit_peso.text.toString().toDouble()
           val idade = edit_idade.text.toString().toInt()
           calcularIngestaoDiaria.calcularTotalMl(peso,idade)
           resultadoMl = calcularIngestaoDiaria.ResultadoMl()
           val formatar = NumberFormat.getInstance(Locale("pt","BR"))
           formatar.isGroupingUsed = false
           txt_resultado_ml.setText( formatar.format(resultadoMl) + " " + " ml ")
       }
  }
        refresh_result.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(R.string.dialog_titulo)
                .setMessage(R.string.dialog_desc)
                .setPositiveButton("Ok") { dialogInterface, i ->
                    edit_peso.setText("")
                    edit_idade.setText("")
                    txt_resultado_ml.setText("")
                }
            alertDialog.setNegativeButton("Cancelar") { dialogInterface, i ->

            }
            val dialog = alertDialog.create()
            dialog.show()
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