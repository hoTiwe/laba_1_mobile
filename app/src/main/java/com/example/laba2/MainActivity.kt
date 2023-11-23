package com.example.laba2

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.example.laba2.database.DataBase
import com.example.laba2.database.entities.User
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {

    lateinit var loginField: EditText;
    lateinit var passField: EditText;
    lateinit var sign_button: Button;
    private lateinit var db: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginField = findViewById(R.id.email)
        passField = findViewById(R.id.password)
        sign_button = findViewById(R.id.button)

        val array1 = applicationContext.resources.getStringArray(R.array.Emails)
        val array2 = applicationContext.resources.getStringArray(R.array.Passwords)

        db = DataBase.getDatabase(this)
        thread {
            db.getDao().createUser(User(login = "test", password = "test"))
            db.getDao().createUser(User(login = "hello@mail.ru", password = "qwerty"))
            db.getDao().createUser(User(login = "end@mail.ru", password = "1234"))
            db.getDao().createUser(User(login = "pop@gmail.com", password = "123456"))
            db.getDao().createUser(User(login = "bass@m.c", password = "777"))
        }

        sign_button?.setOnClickListener{
            login()
        }

        passField.setOnKeyListener { v, keyCode, event ->
            loginField.setTextColor(Color.BLACK)
            passField.setTextColor(Color.BLACK)
            if(event.action == KeyEvent.ACTION_DOWN &&
                (keyCode == KeyEvent.KEYCODE_ENTER))
            {
                login()
            }
            true
        }

        // При обновлении значения логина цвет текста логина и пароля должен быть черным
        loginField.setOnKeyListener { v, keyCode, event ->
            loginField.setTextColor(Color.BLACK)
            passField.setTextColor(Color.BLACK)
            true
        }
    }

    private fun login(){
        thread {
            if (db.getDao().checkUserExists(loginField.text.toString())) {
                if (db.getDao().getUserByLogin(loginField.text.toString()).password == passField.text.toString()
                ) {
                    val id = db.getDao().getUserByLogin(loginField.text.toString()).idUser!!
                    getSharedPreferences("user", Context.MODE_PRIVATE).edit().putInt("id", id).apply() // записываем id текущего пользователя в sp
                    runOnUiThread {
                        Log.d("Success", "Success login")
                        val intent = Intent(this, BottomNavigationActivity::class.java)
                        startActivity(intent)
                    }
                } else {
                    runOnUiThread {
                        passField.setTextColor(Color.RED) // Неверный пароль
                    }
                }
            } else {
                runOnUiThread {
                    loginField.setTextColor(Color.RED) // Неверный логин
                }
            }
        }
    }

    //Сохранение состояния activity при перезаписи(повороте)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("email", loginField.text.toString())
        outState.putString("password", passField.text.toString())
    }

    //Восстановление состояния активити
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val Email = savedInstanceState.getString("email")
        val Password = savedInstanceState.getString("password")
        loginField.setText(Email)
        passField.setText(Password)
    }
}

