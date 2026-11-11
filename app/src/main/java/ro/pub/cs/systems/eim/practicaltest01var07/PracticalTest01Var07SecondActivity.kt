package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var07SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var07_second)
        val i1 = findViewById<EditText>(R.id.i1)
        val i2 = findViewById<EditText>(R.id.i2)
        val i3 = findViewById<EditText>(R.id.i3)
        val i4 = findViewById<EditText>(R.id.i4)

        val bsum = findViewById<Button>(R.id.sum)
        val bprod = findViewById<Button>(R.id.product)

        val n1 = intent.getStringExtra(Constants.KEY_NUMBER_1) ?: "0"
        val n2 = intent.getStringExtra(Constants.KEY_NUMBER_2) ?: "0"
        val n3 = intent.getStringExtra(Constants.KEY_NUMBER_3) ?: "0"
        val n4 = intent.getStringExtra(Constants.KEY_NUMBER_4) ?: "0"

        i1.setText(n1)
        i2.setText(n2)
        i3.setText(n3)
        i4.setText(n4)
        var t = n1.toInt() * n2.toInt() * n3.toInt() * n4.toInt();
        var t2 = n1.toInt() + n2.toInt() + n3.toInt() + n4.toInt();


        bprod.setOnClickListener {
            val returnIntent = Intent().apply {
                putExtra(Constants.KEY_RESULT, t.toString())
            }
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }

        bsum.setOnClickListener {
            val returnIntent = Intent().apply {
                putExtra(Constants.KEY_RESULT, t2.toString())
            }
            setResult(Activity.RESULT_CANCELED, returnIntent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}