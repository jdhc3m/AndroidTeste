package com.example.prigui.androidtest.ui.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.example.prigui.androidtest.R
import com.example.prigui.androidtest.ui.favorite.FavoriteFragment
import kotlinx.android.synthetic.main.activity_main.*

const val INITIAL_PAGE = 1

class MainMoviesActivity : AppCompatActivity(){

    private var fragmentHome: HomeFragment? = null
    private var fragmentfavorite: FavoriteFragment? = null
    private var mCurrentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.title = null
        setupBottomNavigation()

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_home -> {
                openHome()
                topTitleTv.text = getString(R.string.movie_top_title)
                //setNavigationTitle("Home", showToolbarHelpIcon = false)
                return@OnNavigationItemSelectedListener true
            }

            R.id.action_favorite -> {
                openFavorite()
                topTitleTv.text = getString(R.string.favorite_top_title)
                //setNavigationTitle("Favoritos", showToolbarHelpIcon = true)
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    private fun setupBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        //BottomNavigationViewHelper.removeShiftMode(bottom_navigation) // Coloca nome em todas as tabs
        bottom_navigation.selectedItemId = R.id.action_home
    }

    private fun openFavorite() {
        fragmentfavorite = FavoriteFragment(this)
        addFragment(fragmentfavorite!!)
    }

    private fun openHome() {
        fragmentHome = HomeFragment(this)
        addFragment(fragmentHome!!)
    }


    /**
     * add/replace fragment in container [framelayout]
     */
    private fun addFragment(fragment: Fragment) {
        //avoid add more than once the same fragment on top of back stack
        if (mCurrentFragment?.javaClass != fragment.javaClass) {
            mCurrentFragment = fragment
            supportFragmentManager
                    .beginTransaction()
//                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                    .replace(R.id.content, fragment, fragment.javaClass.simpleName)
                    .addToBackStack(fragment.javaClass.simpleName)
                    .commit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()

    }


}