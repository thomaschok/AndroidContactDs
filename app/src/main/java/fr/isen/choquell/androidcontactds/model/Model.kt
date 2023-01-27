package activity.Contact.Api

import com.google.gson.annotations.SerializedName


data class Model (

  @SerializedName("results" ) var results : ArrayList<Results> = arrayListOf(),
  @SerializedName("info"    ) var info    : Info?              = Info()

):java.io.Serializable