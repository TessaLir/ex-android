package ru.vetukov.spec.java.level01.menu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class KotlinMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.main, menu)

//        val item: MenuItem = menu.add(Menu.NONE, 4444, Menu.NONE, "Battary")
//        item.setIcon(R.drawable.battary)
//        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_ALWAYS)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.main_add -> showMessage("Kotlin Add")
            R.id.main_battary -> showMessage("Kotlin Battary")
            R.id.main_train -> showMessage("Kotlin Train")
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showMessage(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
