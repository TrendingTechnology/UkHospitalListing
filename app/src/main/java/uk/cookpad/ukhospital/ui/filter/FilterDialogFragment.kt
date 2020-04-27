package uk.cookpad.ukhospital.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_filter.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import uk.cookpad.ukhospital.R
import uk.cookpad.ukhospital.ui.home.HomeViewModel

class FilterDialogFragment : BottomSheetDialogFragment() {

    private val homeViewModel by sharedViewModel<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.bottom_sheet_filter,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.filter.observe(viewLifecycleOwner, Observer { filter ->
            if (!filter.isNullOrEmpty()) {
                nhsCheckBox.isChecked = true
            }
        })
        applyFilter.setOnClickListener {
            if (nhsCheckBox.isChecked) {
                homeViewModel.setFilter(FILTER_NHS)
            } else {
                homeViewModel.setFilter(null)
            }
            dismiss()
        }
    }

    companion object {
        const val FILTER_NHS = "NHS Sector"
        fun showDialog(fm: FragmentManager) {
            FilterDialogFragment().show(fm, "FilterDialogFragment")
        }
    }
}
