package fr.isen.choquell.androidcontactds

import activity.Contact.Api.Results
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.squareup.picasso.Picasso
import fr.isen.choquell.androidcontactds.databinding.ActivityDetailsContactBinding


@Suppress("DEPRECATION")
class DetailsContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsContactBinding
    private lateinit var item: Results
    private lateinit var name: String


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_contact)
        item = intent.getSerializableExtra("detail") as Results
        binding = ActivityDetailsContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        name = item.name?.first +" "+ item.name?.last



        val actionBar = supportActionBar
        actionBar?.title = name


        val textView = findViewById<TextView>(R.id.nomDetailView)
        val text = name
        textView.text = text

        if (item.picture?.large!!.isNotEmpty()) {
            Picasso.get().load(item.picture?.large).into(binding.detailsImageContact)
        }


        val mailView = findViewById<TextView>(R.id.mailDetailView)
        val mail = item.email
        mailView.text = mail

        val addresseView = findViewById<TextView>(R.id.adresseDetailView)
        val adresse = item.location?.street?.number.toString() + " " + item.location?.street?.name + " " + item.location?.state + " " + item.location?.city
        addresseView.text = adresse


        val telephoneView = findViewById<TextView>(R.id.telephoneDetailView)
        val telephone = item.phone
        telephoneView.text = telephone

        val dateView = findViewById<TextView>(R.id.dateDetailView)
        val date = item.registered?.date +" "+ item.registered?.age
        dateView.text = date
    }

}




