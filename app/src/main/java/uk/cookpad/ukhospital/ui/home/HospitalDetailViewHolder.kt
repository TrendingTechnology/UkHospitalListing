package uk.cookpad.ukhospital.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_hospital.*
import uk.cookpad.ukhospital.R
import uk.cookpad.ukhospital.data.dto.HospitalDetail

class HospitalDetailViewHolder(
    override val containerView: View,
    private val hospitalActionListener: HospitalActionListener
) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    @SuppressLint("SetTextI18n")
    fun bind(hospitalDetail: HospitalDetail) {
        hospitalDetail.apply {
            textName.text = organisationName
            textSubType.text = "Subtype: $subType \nSector: $sector"
            textAddress.text = "Address: $address1 $address2 $address3, $city, $county".trim()
        }

        containerView.setOnClickListener { hospitalActionListener.onItemClick(hospitalDetail) }
    }

    companion object {
        fun create(parent: ViewGroup, hospitalActionListener: HospitalActionListener) =
            HospitalDetailViewHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(
                        R.layout.list_item_hospital,
                        parent,
                        false
                    ),
                hospitalActionListener
            )
    }
}
