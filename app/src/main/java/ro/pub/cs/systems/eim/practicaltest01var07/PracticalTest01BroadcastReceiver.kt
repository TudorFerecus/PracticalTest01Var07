package ro.pub.cs.systems.eim.practicaltest01var07

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.Toast

class PracticalTest01BroadcastReceiver(private val i1: EditText?,
                                       private val i2: EditText?,
                                       private val i3: EditText?,
                                       private val i4: EditText?) : BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        val v1 = intent.getStringExtra(Constants.KEY_NUMBER_1)
        val v2 = intent.getStringExtra(Constants.KEY_NUMBER_2)
        val v3 = intent.getStringExtra(Constants.KEY_NUMBER_3)
        val v4 = intent.getStringExtra(Constants.KEY_NUMBER_4)

        i1?.setText(v1)
        i2?.setText(v2)
        i3?.setText(v3)
        i4?.setText(v4)



        Toast.makeText(context, "$v1 $v2 $v3 $v4", Toast.LENGTH_LONG).show()
    }
}