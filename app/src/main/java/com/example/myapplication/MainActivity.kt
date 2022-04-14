package com.example.myapplication

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.*
import com.example.myapplication.model.CalcularIngestaoDiaria
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var edit_peso: EditText
    private lateinit var edit_idade: EditText
    private lateinit var btn_calcular: Button
    private lateinit var txt_resultado_ml: TextView
    private lateinit var refresh_result: ImageView
    private lateinit var btn_lembrete : Button
    private lateinit var bt_alarme : Button
    private lateinit var txt_hora: TextView
    private lateinit var txt_minutos: TextView

    private lateinit var calcularIngestaoDiaria: CalcularIngestaoDiaria
    private var resultadoMl = 0.0

    lateinit var timePickerDialog: TimePickerDialog
    lateinit var calendario: Calendar
    var horaAtual = 0
    var minutosAtuais = 0


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
           txt_resultado_ml.text = formatar.format(resultadoMl) + " " + "ml"
       }
  }
        refresh_result.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(R.string.dialog_titulo)
                .setMessage(R.string.dialog_desc)
                .setPositiveButton("Ok") { dialogInterface, i ->
                    edit_peso.setText("")
                    edit_idade.setText("")
                    txt_resultado_ml.text = " "
                }
            alertDialog.setNegativeButton("Cancelar") { dialogInterface, i ->

            }
            val dialog = alertDialog.create()
            dialog.show()
        }

        btn_lembrete.setOnClickListener {
            calendario = Calendar.getInstance()
            horaAtual = calendario.get(Calendar.HOUR_OF_DAY)
            minutosAtuais = calendario.get(Calendar.MINUTE)

            timePickerDialog = TimePickerDialog(  this ,{timePicker: TimePicker, hourOfDay: Int, minutes: Int ->
                  txt_hora.text =String.format("%02d",hourOfDay)
                  txt_minutos.text= String.format("%02d",minutes)
            }, horaAtual, minutosAtuais, true)
             timePickerDialog.show()
        }

         bt_alarme.setOnClickListener {
             if (!txt_hora.text.toString().isEmpty() && !txt_minutos.text.toString().isEmpty()){
                 val intent = Intent(AlarmClock.ACTION_SET_ALARM)
                 intent.putExtra(AlarmClock.EXTRA_HOUR, txt_hora.text.toString().toInt())
                 intent.putExtra(AlarmClock.EXTRA_MINUTES, txt_minutos.text.toString().toInt())
                 intent.putExtra(AlarmClock.EXTRA_MESSAGE, getString(R.string.time_alarme))
                 startActivity(intent)

                 if (intent.resolveActivity(packageManager) != null){
                     startActivity(intent)
                 }
             }
         }
    }
    private fun InciciaComponentes(){
        edit_peso = findViewById(R.id.edit_peso)
        edit_idade = findViewById(R.id.edit_idade)
        btn_calcular = findViewById(R.id.btn_calcular)
        txt_resultado_ml = findViewById(R.id.txt_resultado_ml)
        refresh_result = findViewById(R.id.refresh_result)
        btn_lembrete = findViewById(R.id.definir_lembrete)
        bt_alarme = findViewById(R.id.bt_alarme)
        txt_hora = findViewById(R.id.id_horas)
        txt_minutos = findViewById(R.id.id_minutos)
    }

}