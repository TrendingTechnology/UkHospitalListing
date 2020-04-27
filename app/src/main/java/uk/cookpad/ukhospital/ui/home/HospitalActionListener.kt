package uk.cookpad.ukhospital.ui.home

import uk.cookpad.ukhospital.data.dto.HospitalDetail

interface HospitalActionListener {
    fun onItemClick(hospitalDetail: HospitalDetail)
}