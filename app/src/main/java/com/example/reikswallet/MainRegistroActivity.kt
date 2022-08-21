package com.example.reikswallet

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainRegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_email)
        val inputNombres = findViewById<EditText>(R.id.nombres)
        val inputApellidos = findViewById<EditText>(R.id.apellidos)
        val inputCorreo = findViewById<EditText>(R.id.email)
        val inpunCelular = findViewById<EditText>(R.id.phone)
        val inpuntContrasena1 = findViewById<EditText>(R.id.password1)
        val inpuntContrasena2 = findViewById<EditText>(R.id.password2)
        val buttonRegistrar = findViewById<Button>(R.id.nuevo_registro)
        val urlregistrar = "http://192.168.0.18/wallet/registroWithEmail.php"

        buttonRegistrar.setOnClickListener {
            val nombres = inputNombres.text.toString()
            val apellidos = inputApellidos.text.toString()
            val correo = inputCorreo.text.toString()
            val celular = inpunCelular.text.toString()
            val contrasena1 = inpuntContrasena1.text.toString()
            val contrasena2 =inpuntContrasena2.text.toString()

            if (nombres.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || celular.isEmpty() ||
                contrasena1.isEmpty() || contrasena2.isEmpty())
            {

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Campos vacios")
                builder.setMessage("Por favor complete todo el formulario")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                        android.R.string.yes
                }
                builder.show()

            }else {
                if (contrasena1 == contrasena2 ){
                    val stringRequest: StringRequest = object : StringRequest(
                        Method.POST,urlregistrar ,
                        Response.Listener { response ->
                            println(response)
                            try {
                                val jsonObject = JSONObject(response)
                                println(jsonObject)
                                val msg = jsonObject.getString("msg")
                                if(msg.equals("Ok")){
                                    Toast.makeText(this@MainRegistroActivity, msg, Toast.LENGTH_SHORT).show()
                                }else{
                                    Toast.makeText(this@MainRegistroActivity, "Its a toast!", Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        },
                        Response.ErrorListener { error ->
                            println(error.toString())
                        }) {
                        @Throws(AuthFailureError::class)
                        override fun getParams(): Map<String, String>? {
                            val params: MutableMap<String, String> = HashMap()
                            println(correo)
                            println(contrasena1)
                            println(apellidos)
                            println(celular)
                            println(nombres)
                            params["usuario"] = correo
                            params["password"]= contrasena1
                            params["nombre"] = nombres
                            params["apellido"] = apellidos
                            params["telefono"] = celular

                            return params
                        }
                    }

                    val requestQueue = Volley.newRequestQueue(this)
                    requestQueue.add(stringRequest)



                }else{
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Error")
                    builder.setMessage("Contraseñas no coinciden")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))
                    builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                        android.R.string.yes
                    }
                    builder.show()


                }

            }

        }

    }

}