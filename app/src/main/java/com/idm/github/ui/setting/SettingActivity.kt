package com.idm.github.ui.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.CompoundButton
import com.idm.github.databinding.ActivitySettingBinding
import com.idm.github.ui.about.AboutActivity
import com.idm.github.utils.alarm.AlarmReceiver

class SettingActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySettingBinding
    private val switchPreference = "switch preference"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val alarmReceiver = AlarmReceiver()
        val time = "09:00"
        val message = "Open your App Today"
        val preferences = getSharedPreferences(switchPreference, MODE_PRIVATE)
        val editPref = preferences.edit()
        binding.switchAlarm.setChecked(preferences.getBoolean("isChecked", false))
        with(binding){

            btnBack.setOnClickListener {
                finish()
            }
            btnChangeLanguage.setOnClickListener {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            }
            btnAbout.setOnClickListener {
                startActivity(Intent(this@SettingActivity,AboutActivity::class.java))
            }
            switchAlarm.setOnCheckedChangeListener (CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    editPref.putBoolean("isChecked",true)
                    alarmReceiver.setRepeatingAlarm(this@SettingActivity,time,message)
                }else{
                    editPref.putBoolean("isChecked",false)
                    alarmReceiver.cancelAlarm(this@SettingActivity)
                }
                editPref.apply()
            }
            )}

    }
}