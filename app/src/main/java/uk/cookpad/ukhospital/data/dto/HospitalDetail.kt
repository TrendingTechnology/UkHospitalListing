package uk.cookpad.ukhospital.data.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class HospitalDetail(
    @PrimaryKey val organisationID: Int,
    val organisationCode: String,
    val organisationType: String,
    val subType: String,
    val sector: String,
    val organisationStatus: String,
    val isPimsManaged: Boolean,
    val organisationName: String,
    val address1: String,
    val address2: String,
    val address3: String,
    val city: String,
    val county: String,
    val postcode: String,
    val latitude: String,
    val longitude: String,
    val parentODSCode: String,
    val parentName: String,
    val phone: String,
    val email: String,
    val website: String,
    val fax: String
) : Parcelable {
    companion object {
        fun fromMap(map: Map<String, String>): HospitalDetail {
            return HospitalDetail(
                organisationID = map.getOrElse("OrganisationID", { "0" }).toInt(),
                organisationCode = map["OrganisationCode"].orEmpty(),
                organisationType = map["OrganisationType"].orEmpty(),
                subType = map["SubType"].orEmpty(),
                sector = map["Sector"].orEmpty(),
                organisationStatus = map["OrganisationStatus"].orEmpty(),
                isPimsManaged = map.getOrElse("IsPimsManaged", { "False" }).toBoolean(),
                organisationName = map["OrganisationName"].orEmpty(),
                address1 = map["Address1"].orEmpty(),
                address2 = map["Address2"].orEmpty(),
                address3 = map["Address3"].orEmpty(),
                city = map["City"].orEmpty(),
                county = map["County"].orEmpty(),
                postcode = map["Postcode"].orEmpty(),
                latitude = map["Latitude"].orEmpty(),
                longitude = map["Longitude"].orEmpty(),
                parentODSCode = map["ParentODSCode"].orEmpty(),
                parentName = map["ParentName"].orEmpty(),
                phone = map["Phone"].orEmpty(),
                email = map["Email"].orEmpty(),
                website = map["Website"].orEmpty(),
                fax = map["Fax"].orEmpty()
            )
        }
    }
}