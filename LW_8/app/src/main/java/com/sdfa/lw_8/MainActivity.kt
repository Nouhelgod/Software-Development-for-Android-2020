package com.sdfa.lw_8

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), LeftFragment.OnDataListener {

    private var isTwoPane = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isTwoPane = findViewById<View>(R.id.frame_left) != null

        if (isTwoPane) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_left, LeftFragment())
                .add(R.id.frame_right, RightFragment(0))
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, LeftFragment())
                .commit()
        }
    }


    override fun onData(Data: Int) {
        supportFragmentManager.beginTransaction()
            .replace(
                if (isTwoPane)
                    R.id.frame_right
                else
                    R.id.container,
                RightFragment(Data)
            )
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }

}