package com.example.laba2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    lateinit var email: EditText;
    lateinit var pass: EditText;
    lateinit var sign_button: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = findViewById(R.id.email)
        pass = findViewById(R.id.password)
        sign_button = findViewById(R.id.button)

        val array1 = applicationContext.resources.getStringArray(R.array.Emails)
        val array2 = applicationContext.resources.getStringArray(R.array.Passwords)

        sign_button?.setOnClickListener{
            if(array1.contains(email.text.toString())) {
                if(array2[array1.indexOf(email.text.toString())]==pass.text.toString()) {
                    val nextIntent = Intent(this, GifActivity::class.java);
                    startActivity(nextIntent)
                }
                else{
                    pass?.setTextColor(Color.RED);
                    email?.setTextColor(Color.RED);
                }
            }
            else{
                pass?.setTextColor(Color.RED);
                email?.setTextColor(Color.RED);
            }
        }

        email.setOnFocusChangeListener { view, hasFocus ->
            email.setTextColor(Color.BLACK)
        }

        pass.setOnFocusChangeListener { view, hasFocus ->
            pass.setTextColor(Color.BLACK)
        }

    }

    //Сохранение состояния activity при перезаписи(повороте)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("email", email.text.toString())
        outState.putString("password", pass.text.toString())
    }

    //Восстановление состояния активити
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val Email = savedInstanceState.getString("email")
        val Password = savedInstanceState.getString("password")
        email.setText(Email)
        pass.setText(Password)
    }
}

