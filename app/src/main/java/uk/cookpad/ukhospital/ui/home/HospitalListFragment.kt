package uk.cookpad.ukhospital.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_hospital_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import uk.cookpad.ukhospital.R
import uk.cookpad.ukhospital.data.dto.HospitalDetail
import uk.cookpad.ukhospital.ui.HomeActivity
import uk.cookpad.ukhospital.ui.detail.HospitalDetailFragment
import uk.cookpad.ukhospital.ui.filter.FilterDialogFragment


class HospitalListFragment : Fragment(), HospitalActionListener {
    private val hospitalListingAdapter = HospitalListingAdapter(this)
    private val homeViewModel by sharedViewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            homeViewModel.refreshHospitalData()
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_filter -> {
                FilterDialogFragment.showDialog(parentFragmentManager)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_hospital_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        homeViewModel.hospitalList.observe(
            viewLifecycleOwner,
            Observer { hospitalListingAdapter.submitList(it) })
        homeViewModel.uiSate.observe(
            viewLifecycleOwner,
            Observer { handleUiState(it) })
    }

    private fun initUi() {
        rvHospitalList.apply {
            adapter = hospitalListingAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
        swipeRefreshLayout.setOnRefreshListener {
            homeViewModel.refreshHospitalData()
        }

        (requireActivity() as HomeActivity).supportActionBar?.apply {
            setTitle(R.string.app_name)
            setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun handleUiState(uiState: UiState) {
        when (uiState) {
            is UiState.Success -> {
                swipeRefreshLayout.isRefreshing = false
                Snackbar.make(
                    swipeRefreshLayout,
                    getString(R.string.message_data_success, uiState.insertedRows),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            UiState.Loading -> {
                swipeRefreshLayout.isRefreshing = true
            }
            is UiState.Error -> {
                swipeRefreshLayout.isRefreshing = false
                Snackbar.make(
                    swipeRefreshLayout,
                    R.string.message_data_error,
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setAction(R.string.message_data_error_action) { homeViewModel.refreshHospitalData() }
                    .show()
            }
        }
    }

    override fun onItemClick(hospitalDetail: HospitalDetail) {
        (activity as? HomeActivity)?.openScreen(
            fragment = HospitalDetailFragment.newInstance(hospitalDetail),
            addToBackStack = true
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = HospitalListFragment()
    }
}
