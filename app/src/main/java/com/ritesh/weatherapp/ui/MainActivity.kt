package com.ritesh.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ritesh.weatherapp.R
import com.ritesh.weatherapp.ui.dashboard.DashboardFragment
import com.ritesh.weatherapp.ui.weather.CurrentWeatherFragment
import com.ritesh.weatherapp.ui.location.MapsFragment

class MainActivity : AppCompatActivity(),
    IFragmentNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            loadDashboardFragment()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitNow()
    }

    override fun loadDashboardFragment() {
        loadFragment(DashboardFragment.newInstance())
    }

    override fun loadMapFragment() {
        loadFragment(MapsFragment.newInstance())
    }

    override fun loadCurrentWeatherFragment() {
        loadFragment(CurrentWeatherFragment.newInstance())
    }
}