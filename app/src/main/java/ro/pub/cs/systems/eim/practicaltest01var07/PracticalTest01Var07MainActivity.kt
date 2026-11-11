package ro.pub.cs.systems.eim.practicaltest01var07

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var07MainActivity : AppCompatActivity() {
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private var serviceStatus = Constants.SERVICE_STOPPED

    private lateinit var broadcastReceiver: PracticalTest01BroadcastReceiver
    private lateinit var intentFilter: IntentFilter
    companion object {
        private var RETURNED_TOAST = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_practical_test_01_var07_main)

        var bset = findViewById<Button>(R.id.set)
        var brandom = findViewById<Button>(R.id.sum)

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

        broadcastReceiver = PracticalTest01BroadcastReceiver()
        intentFilter = IntentFilter().apply {
            addAction(Constants.ACTION_1)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                Constants.PERMISSION_REQUEST_POST_NOTIFICATIONS
            )
        }

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val returnMessage = result.data?.getStringExtra(Constants.KEY_RESULT) ?: "Niciun cod"
            if (result.resultCode == Activity.RESULT_OK) {
                RETURNED_TOAST = returnMessage.toInt()
                Toast.makeText(this, "Rezultat primit prod $returnMessage", Toast.LENGTH_SHORT).show()
            } else {
                RETURNED_TOAST = returnMessage.toInt()
                Toast.makeText(this, "Rezultat primit sum $returnMessage", Toast.LENGTH_SHORT).show()
            }
        }

        bset.setOnClickListener {
            val num1 = i1.text.toString().toIntOrNull() ?: 0
            val num2 = i2.text.toString().toIntOrNull() ?: 0
            val num3 = i3.text.toString().toIntOrNull() ?: 0
            val num4 = i4.text.toString().toIntOrNull() ?: 0
            Log.d("logggg", "started first activity ${num1} ${num2}")

            val intent = Intent(this, PracticalTest01Var07SecondActivity::class.java).apply {
                putExtra(Constants.KEY_NUMBER_1, num1.toString())
                putExtra(Constants.KEY_NUMBER_2, num2.toString())
                putExtra(Constants.KEY_NUMBER_3, num3.toString())
                putExtra(Constants.KEY_NUMBER_4, num4.toString())
            }

            activityResultLauncher.launch(intent)
        }

        val serviceIntent = Intent(this, PracticalTest01Var07Service::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }

        serviceStatus = Constants.SERVICE_STARTED



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun isNumericToX(toCheck: String): Boolean {
        return toCheck.toDoubleOrNull() != null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("toastres", RETURNED_TOAST.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedNumber1 = savedInstanceState.getString("RETURNED_TOAST")
        RETURNED_TOAST = savedNumber1?.toInt() ?: -1
        Log.d("pctC", "ultima val primita $RETURNED_TOAST")

    }

    override fun onResume() {
        super.onResume()
        Log.d("pctC", "ultima val primita $RETURNED_TOAST")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(broadcastReceiver, intentFilter, Context.RECEIVER_EXPORTED)
        } else {
            @Suppress("DEPRECATION")
            registerReceiver(broadcastReceiver, intentFilter)
        }

    }
}