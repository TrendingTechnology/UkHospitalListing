package uk.cookpad.ukhospital.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_hospital_detail.*
import uk.cookpad.ukhospital.R
import uk.cookpad.ukhospital.data.dto.HospitalDetail
import uk.cookpad.ukhospital.ui.HomeActivity

class HospitalDetailFragment : Fragment() {

    private val hospitalDetailObject by lazy {
        arguments?.getParcelable(PARAM_HOSPITAL) as? HospitalDetail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_hospital_detail, container, false)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hospitalDetailObject?.apply {
            textName.text = organisationName
            textSubType.text = "Subtype: $subType \nSector: $sector"
            textAddress.text =
                "Address: $address1 $address2 $address3, $city, $county $postcode".trim()
            restDetails.text = "\nOrganisationID: $organisationID" +
                    "\nOrganisationName: $organisationName" +
                    "Website: $website " +
                    "\nEmail: $email " +
                    setToolbarTitle(organisationName)
        }
    }

    private fun setToolbarTitle(text: String) {
        (requireActivity() as HomeActivity).supportActionBar?.apply {
            title = text
            setDisplayHomeAsUpEnabled(true)
        }
    }

    companion object {
        const val PARAM_HOSPITAL = "PARAM_HOSPITAL"
        @JvmStatic
        fun newInstance(hospitalDetail: HospitalDetail) =
            HospitalDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PARAM_HOSPITAL, hospitalDetail)
                }
            }
    }
}
