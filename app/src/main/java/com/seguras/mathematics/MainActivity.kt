package com.seguras.mathematics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.seguras.mathematics.databinding.ActivityMainBinding
import kotlin.math.PI
import kotlin.math.hypot
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var opc: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val formulas = resources.getStringArray(R.array.formulas)

        val spinner = binding.spOpciones
        if(spinner != null){
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, formulas)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when(position){
                        0 -> {
                            opc = position
                            binding.etUno.error = null
                            binding.etDos.error = null
                            binding.ivFormula.setImageResource(R.drawable.hipotenusa)
                            binding.tvUno.text = getString(R.string.la)
                            binding.tvDos.text = getString(R.string.lb)
                            binding.etUno.setText(R.string.vacio)
                            binding.etDos.setText(R.string.vacio)
                            binding.etTres.visibility = View.INVISIBLE
                            binding.tvTres.visibility = View.INVISIBLE
                        }
                        1 -> {
                            opc = position
                            binding.etUno.error = null
                            binding.etDos.error = null
                            binding.ivFormula.setImageResource(R.drawable.volumen)
                            binding.tvUno.text = getString(R.string.radio)
                            binding.tvDos.text = getString(R.string.altura)
                            binding.etUno.setText(R.string.vacio)
                            binding.etDos.setText(R.string.vacio)
                            binding.etTres.visibility = View.INVISIBLE
                            binding.tvTres.visibility = View.INVISIBLE
                        }
                        2 -> {
                            opc = position
                            binding.etUno.error = null
                            binding.etDos.error = null
                            binding.ivFormula.setImageResource(R.drawable.ohm)
                            binding.tvUno.text = getString(R.string.corriente)
                            binding.tvDos.text = getString(R.string.resistencia)
                            binding.etUno.setText(R.string.vacio)
                            binding.etDos.setText(R.string.vacio)
                            binding.etTres.visibility = View.INVISIBLE
                            binding.tvTres.visibility = View.INVISIBLE
                        }
                        3 -> {
                            opc = position
                            binding.etUno.error = null
                            binding.etDos.error = null
                            binding.etTres.error = null
                            binding.ivFormula.setImageResource(R.drawable.gravedad)
                            binding.tvUno.text = getString(R.string.m1)
                            binding.tvDos.text = getString(R.string.m2)
                            binding.tvTres.text = getString(R.string.dist)
                            binding.etUno.setText(R.string.vacio)
                            binding.etDos.setText(R.string.vacio)
                            binding.etTres.visibility = View.VISIBLE
                            binding.tvTres.visibility = View.VISIBLE
                        }
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        }
    }

    fun click(view: View) {

        val intent = Intent(this, Results::class.java)

        val parametros = Bundle()

            when(opc) {
                0 -> {
                    if(validaCampos(2)){
                        var resultado = hypot(binding.etUno.text.toString().toDouble(),binding.etDos.text.toString().toDouble())
                        parametros.putDouble(getString(R.string.resKey),resultado)
                        parametros.putInt(getString(R.string.opcion), opc)
                        intent.putExtras(parametros)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, getString(R.string.ingresa_valor), Toast.LENGTH_SHORT).show()
                    }
                }
                1 -> {
                    if(validaCampos(2)){
                        var resultado = (PI * binding.etUno.text.toString().toDouble().pow(2.0) * binding.etDos.text.toString().toDouble())/3
                        parametros.putDouble(getString(R.string.resKey),resultado)
                        parametros.putInt(getString(R.string.opcion), opc)
                        intent.putExtras(parametros)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, getString(R.string.ingresa_valor), Toast.LENGTH_SHORT).show()
                    }
                }
                2 -> {
                    if(validaCampos(2)){
                        var resultado = binding.etUno.text.toString().toDouble() * binding.etDos.text.toString().toDouble()
                        parametros.putDouble(getString(R.string.resKey),resultado)
                        parametros.putInt(getString(R.string.opcion), opc)
                        intent.putExtras(parametros)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, getString(R.string.ingresa_valor), Toast.LENGTH_SHORT).show()
                    }
                }
                3 -> {
                    if(validaCampos(3)){
                        var resultado = 6.67e-11 * (binding.etUno.text.toString().toDouble()*binding.etDos.text.toString().toDouble()
                                / Math.pow(binding.etTres.text.toString().toDouble(), 2.0))
                        parametros.putDouble(getString(R.string.resKey),resultado)
                        parametros.putInt(getString(R.string.opcion), opc)
                        intent.putExtras(parametros)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, getString(R.string.ingresa_valor), Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    private fun validaCampos(numPar: Int): Boolean{
        var esValido = true
        when(numPar){
            2 -> {
                if (binding.etUno.text.toString() == getString(R.string.vacio)) {
                    binding.etUno.error = getString(R.string.valReq)
                    esValido = false
                } else binding.etUno.error = null
                if (binding.etDos.text.toString() == getString(R.string.vacio)) {
                    binding.etDos.error = getString(R.string.valReq)
                    esValido = false
                } else binding.etDos.error = null
            }
            3 -> {
                if(binding.etUno.text.toString() == getString(R.string.vacio)){
                    binding.etUno.error = getString(R.string.valReq)
                    esValido = false
                } else binding.etUno.error = null
                if(binding.etDos.text.toString() == getString(R.string.vacio)){
                    binding.etDos.error = getString(R.string.valReq)
                    esValido = false
                } else binding.etDos.error = null
                if(binding.etTres.text.toString() == getString(R.string.vacio)){
                    binding.etTres.error = getString(R.string.valReq)
                    esValido = false
                } else binding.etTres.error = null
            }
            else -> return false
        }
        return esValido
    }
}