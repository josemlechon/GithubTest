package com.jml.github.challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.jml.github.challenge.databinding.ActivityMainBinding
import com.jml.github.challenge.ui.fragments.RepoListFragment
import com.jml.github.challenge.ui.fragments.utils.viewBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {

            supportFragmentManager.commit {
                add<RepoListFragment>(R.id.fragment_container_view)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            true
        } else {
            super.onSupportNavigateUp()
        }
    }
}