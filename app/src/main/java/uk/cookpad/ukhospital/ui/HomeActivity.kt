package uk.cookpad.ukhospital.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import uk.cookpad.ukhospital.ui.home.HospitalListFragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            openScreen(HospitalListFragment.newInstance())
        }
    }

    fun openScreen(fragment: Fragment, addToBackStack: Boolean = false) {
        supportFragmentManager.beginTransaction().apply {
            replace(android.R.id.content, fragment)
            if (addToBackStack)
                addToBackStack(null)
            commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed();
                return true
            }
        }
        return super.onOptionsItemSelected(item);
    }
}


