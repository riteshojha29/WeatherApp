package com.ritesh.weatherapp.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ritesh.weatherapp.R
import com.ritesh.weatherapp.data.dao.DatabaseBuilder
import com.ritesh.weatherapp.data.dao.DatabaseHelperImpl
import com.ritesh.weatherapp.ui.MainActivity
import com.ritesh.weatherapp.ui.SharedViewModel

class DashboardFragment : Fragment() {
    companion object {
        fun newInstance() = DashboardFragment()
    }

    private lateinit var dbHelper: DatabaseHelperImpl

    private lateinit var textAddNewCity: TextView
    private lateinit var listLocations: RecyclerView
    private lateinit var fabAddCity: FloatingActionButton

    private lateinit var viewModel: DashboardViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.dashboard_fragment, container, false)

        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val context = activity as MainActivity
        dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(context))

        fetchLocationDataObserver(context)
        addLocationDataObserver(context)
        deleteLocationDataObserver(context)

        sharedViewModel.newLocationData.observe(viewLifecycleOwner, Observer {
            viewModel.insertLocation(dbHelper, it)
        })

        viewModel.fetchLocations(dbHelper)
    }

    private fun initViews(view: View) {
        textAddNewCity = view.findViewById(R.id.textAddNewCity)
        listLocations = view.findViewById(R.id.listLocations)
        fabAddCity = view.findViewById(R.id.fabAddCity)
    }

    private fun initListeners() {
        fabAddCity.setOnClickListener {
            (activity as MainActivity).loadMapFragment()
        }
    }

    private fun fetchLocationDataObserver(context: Context) {

        viewModel.locationData.observe(viewLifecycleOwner, Observer { locations ->
            listLocations.apply {
                visibility = View.VISIBLE
                layoutManager = GridLayoutManager(context, 2)
                adapter = DashboardListAdapter(locations, context)

                (adapter as DashboardListAdapter?)?.onItemClicked = {
                    sharedViewModel.weatherLocation.postValue(it)
                    (activity as MainActivity).loadCurrentWeatherFragment()
                }

                (adapter as DashboardListAdapter?)?.onItemDeleted = {
                    viewModel.deleteLocation(dbHelper, it)
                }
            }
        })

        viewModel.locationDataFailure.observe(viewLifecycleOwner, Observer { message ->
            textAddNewCity.visibility = View.VISIBLE
        })

    }

    private fun addLocationDataObserver(context: Context) {
        viewModel.locationInsertSuccess.observe(viewLifecycleOwner, Observer {
            viewModel.fetchLocations(dbHelper)
        })

        viewModel.locationDataFailure.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        })

    }

    private fun deleteLocationDataObserver(context: Context) {

        viewModel.locationDeleteSuccess.observe(viewLifecycleOwner, Observer {
            viewModel.fetchLocations(dbHelper)
        })

        viewModel.locationDataFailure.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        })

    }
}