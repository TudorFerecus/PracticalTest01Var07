package ro.pub.cs.systems.eim.practicaltest01var07

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var07MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_practical_test_01_var07_main)

        var bset = findViewById<Button>(R.id.set)
        var brandom = findViewById<Button>(R.id.random)

        var i1 = findViewById<EditText>(R.id.i1)
        var i2 = findViewById<EditText>(R.id.i2)
        var i3 = findViewById<EditText>(R.id.i3)
        var i4 = findViewById<EditText>(R.id.i4)

        brandom.setOnClickListener {
            var v1 = (1..10).random()
            var v2 = (1..10).random()
            var v3 = (1..10).random()
            var v4 = (1..10).random()

            if(!isNumericToX(i1.text.toString()))
                i1.setText(v1.toString())
            if(!isNumericToX(i2.text.toString()))
                i2.setText(v2.toString())
            if(!isNumericToX(i3.text.toString()))
                i3.setText(v3.toString())
            if(!isNumericToX(i4.text.toString()))
                i4.setText(v4.toString())

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun isNumericToX(toCheck: String): Boolean {
        return toCheck.toDoubleOrNull() != null
    }
}