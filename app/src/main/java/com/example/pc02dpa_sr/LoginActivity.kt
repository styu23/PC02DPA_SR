package com.example.pc02dpa_sr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val txtIDN: TextView = findViewById(R.id.txtIDNI)
        val txtICLAVE: TextView = findViewById(R.id.txtICLAVE)
        val btnIngresar: Button = findViewById(R.id.btnIngresar)
        val btnCrear: Button = findViewById(R.id.btnCrear)
        val db = FirebaseFirestore.getInstance()
        var clave: String=""

        btnCrear.setOnClickListener {
            val intent = Intent(this,RegistroActivity::class.java)
            startActivity(intent)
        }

        btnIngresar.setOnClickListener {
            if(isValidarForm(txtIDN,txtICLAVE)){
                db.collection("Users").document(txtIDN.text.toString()).get()
                    .addOnSuccessListener { document ->
                        clave = document.get("Clave") as String
                        if(clave.equals(txtICLAVE.text.toString())){
                            Toast.makeText(
                                this, "Acceso Permitido",
                                Toast.LENGTH_LONG
                            ).show()
                        }else{Toast.makeText(
                            this,"El usuario y/o clave\n" + "No esta registrado",
                            Toast.LENGTH_LONG

                                ).show()
                        }

                    }



                }

            }


        }


    private fun isValidarForm(DNI:TextView,Clave:TextView):Boolean
    {
        var response = true
        if(TextUtils.isEmpty(DNI.text.toString()))
        {
            DNI.error = "Ingrese DNI"
            response = false
        }

        if(TextUtils.isEmpty(Clave.text.toString()))
        {
            Clave.error = "Ingrese clave"
            response = false
        }
        return response


    }

}