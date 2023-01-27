package activity.Contact.Api

import com.google.gson.annotations.SerializedName


data class Timezone (

  @SerializedName("offset"      ) var offset      : String? = null,
  @SerializedName("description" ) var description : String? = null

):java.io.Serializable