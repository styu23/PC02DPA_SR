package com.example.pc02dpa_sr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val btnRegistrar: Button = findViewById(R.id.btnRegistrar)
        val txtRDNI: TextView =  findViewById(R.id.txtRDNI)
        val txtRNOMBRE: TextView =  findViewById(R.id.txtRNOMBRE)
        val txtRPASSWORD: TextView =  findViewById(R.id.txtRPASSWORD)
        val txtRCLAVEREPITE: TextView =  findViewById(R.id.txtRCLAVEREPITE)
        val db= FirebaseFirestore.getInstance()

        btnRegistrar.setOnClickListener {
            if(validateClave(txtRPASSWORD.text.toString(),
                txtRPASSWORD.text.toString()))

            {

                db.collection("Users").document(txtRDNI.text.toString())
                    .set(hashMapOf("NombresC" to  txtRNOMBRE.text.toString(),
                        "Clave" to txtRPASSWORD.text.toString()
                        ))

                val intent = Intent(    this, LoginActivity::class.java)
                startActivity(intent)

            }else{
                txtRCLAVEREPITE.error = "Las claves no coinciden.. volver ingresar"


            }

        }

    }

    private fun validateClave(clave: String, claveR:String):Boolean
    {

        return clave.equals(claveR)
    }
}