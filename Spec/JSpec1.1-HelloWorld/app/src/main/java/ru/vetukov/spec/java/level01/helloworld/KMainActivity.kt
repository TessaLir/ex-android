package ru.vetukov.spec.java.level01.helloworld

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast

class KMainActivity : AppCompatActivity(), View.OnClickListener {

    var LOG_TAG: String = "##%\$#HelloWorld.LOG_TAG"

    lateinit var mTVText: TextView
    lateinit var mCBCheck: CheckBox
    lateinit var mBTNCheck: Button
    lateinit var mBTNUncheck: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTVText = findViewById(R.id.main_tv_text)
        mCBCheck = findViewById(R.id.main_cb_check)
        mBTNCheck = findViewById(R.id.main_btn_check)
        mBTNUncheck = findViewById(R.id.main_btn_uncheck)

        mBTNCheck.setOnClickListener(this)
        mBTNUncheck.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.main_btn_check -> setChecked(true)
            R.id.main_btn_uncheck -> setChecked(false)
        }
    }

    fun setChecked(boolean: Boolean) {
        mCBCheck.isChecked = boolean
        Log.d(LOG_TAG, "checkBox.isChecked " + boolean.toString())
        Toast.makeText(this, "isChecked", Toast.LENGTH_SHORT).show()
    }
}
