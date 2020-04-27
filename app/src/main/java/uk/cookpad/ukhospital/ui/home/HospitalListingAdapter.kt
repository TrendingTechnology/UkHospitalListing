package uk.cookpad.ukhospital.ui.home

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import uk.cookpad.ukhospital.data.dto.HospitalDetail

class HospitalListingAdapter(private val hospitalActionListener: HospitalActionListener) :
    PagedListAdapter<HospitalDetail, HospitalDetailViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalDetailViewHolder {
        return HospitalDetailViewHolder.create(parent, hospitalActionListener)
    }

    override fun onBindViewHolder(holder: HospitalDetailViewHolder, position: Int) {
        getItem(position)?.apply {
            holder.bind(this)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HospitalDetail>() {
            override fun areItemsTheSame(
                oldItem: HospitalDetail,
                newItem: HospitalDetail
            ): Boolean {
                return oldItem.organisationID == newItem.organisationID
            }

            override fun areContentsTheSame(
                oldItem: HospitalDetail,
                newItem: HospitalDetail
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
