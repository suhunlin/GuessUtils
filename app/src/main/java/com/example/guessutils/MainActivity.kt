package com.example.guessutils

import android.content.DialogInterface
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.guessutils.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    val tag:String = MainActivity::class.java.simpleName
    val secretNumber:SecretNumber = SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.reset_game))
                .setMessage(getString(R.string.are_you_sure))
                .setPositiveButton(R.string.ok, {dialog, which->
                    onClickToResetGeame()
                })
                .setNeutralButton(getString(R.string.cancel), null)
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun guessVerify(view:View){
        var userInput:Int = binding.contentLayout.userInputEditText.text.toString().toInt()
        var message:String = secretNumber.verifyResult(resources, userInput)
        var bingo:Boolean = if(secretNumber.verify(userInput)==0) true else false

        binding.contentLayout.guessCounterView.text = secretNumber.guessCounter.toString() + "\t" +getString(
                    R.string.times)
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.guess_result)).setMessage(message)
            .setPositiveButton(getString(R.string.ok), {dialog, which->
                guessResultProcess(dialog, bingo)
            })
            .show()
    }

    fun guessResultProcess(dialog:DialogInterface, bingo:Boolean){
        binding.contentLayout.userInputEditText.text = null

    }

    fun onClickToResetGeame(){
        binding.contentLayout.userInputEditText.text = null
        binding.contentLayout.guessCounterView.text = "0"
        secretNumber.resetAll()
        Toast.makeText(this, getString(R.string.success), Toast.LENGTH_LONG)
    }
}