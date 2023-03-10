package com.example.app_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val users = listOf(User("pippo@gmail.com", "pippo841!"), User("mario@gmail.com", "itsmemario4$"), User("luigi@gmail.com", "iambored1!"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Write code under this line, if you write something that manipulate the layout, at example a button, above the ContentView method the application crashes

        val button = findViewById<Button>(R.id.button)
        val email = findViewById<EditText>(R.id.fieldEmail)
        val password = findViewById<EditText>(R.id.fieldPassword)

        button.setOnClickListener{

            var logged = false

            try{
                if((email.text.isNotEmpty() || password.text.isNotEmpty())){
                    if(password.text.length < 6){
                        password.error = "controllare che la password sia lunga più di 6 caratteri"
                    } else if(!Patterns.EMAIL_ADDRESS.matcher(email.text).matches()){
                        email.error = "controllare che l'email contenga la @ e il dominio"
                    } else {
                        for (user in users){
                            if(user.email == email.text.toString() && user.password == password.text.toString()){
                                Toast.makeText(this, "Login effettuato", Toast.LENGTH_SHORT).show()
                                logged = true
                            }
                        }
                        if(!logged){
                            Toast.makeText(this, "Login fallito, controllare password o username", Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    password.error = "compilare il campo"
                    email.error = "compilare il campo"
                    // Toast.makeText(this, "Controllare che tutti i campi siano validi", Toast.LENGTH_SHORT).show()
                }
            }catch (error: java.lang.Error){
                Toast.makeText(this, "Errore", Toast.LENGTH_SHORT).show()
            }
        }

    }
}