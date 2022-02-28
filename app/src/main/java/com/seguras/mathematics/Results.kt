package com.seguras.mathematics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seguras.mathematics.databinding.ActivityResultsBinding
import java.math.BigDecimal
import java.math.RoundingMode

class Results : AppCompatActivity() {

    private lateinit var binding: ActivityResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        var resultado = bundle?.getDouble(getString(R.string.resKey),0.0)
        var opcion = bundle?.getInt(getString(R.string.opcion),0)

        when(opcion){
            0 -> {
                binding.tvLeyenda.text = getString(R.string.leyendaHipo)
                binding.tvResultado.text =
                    BigDecimal(resultado!!).setScale(3, RoundingMode.HALF_EVEN).toString()
            }
            1 -> {
                binding.tvLeyenda.text = getString(R.string.leyendaCono)
                binding.tvResultado.text =
                    BigDecimal(resultado!!).setScale(3, RoundingMode.HALF_EVEN).toString()
            }
            2 -> {
                binding.tvLeyenda.text = getString(R.string.leyendaVolt)
                binding.tvResultado.text =
                    BigDecimal(resultado!!).setScale(3, RoundingMode.HALF_EVEN).toString()
            }
            3 -> {
                binding.tvLeyenda.text = getString(R.string.leyendaGrav)
                binding.tvResultado.text = resultado.toString()
            }
        }
    }
}