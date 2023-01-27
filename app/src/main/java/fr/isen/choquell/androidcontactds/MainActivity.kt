package fr.isen.choquell.androidcontactds

import ContactAdapter
import activity.Contact.Api.Model
import activity.Contact.Api.Results
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.choquell.androidcontactds.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)
        actionBar?.title = "AndroidContactDS"
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#000000")))

        val layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerViewList.layoutManager = layoutManager
        binding.recyclerViewList.adapter = ContactAdapter(arrayListOf()) {
            val intent = Intent(this, DetailsContactActivity::class.java)
            intent.putExtra("detail", it)
            startActivity(intent)
        }
        loadContactFromAPI()
    }

    private fun loadContactFromAPI() {
        val url = "https://randomuser.me/api/?results=10&nat=fr"
        val jsonObject = JSONObject()
        jsonObject.put("","")
        val jsonRequest = JsonObjectRequest(Request.Method.GET, url, jsonObject , {
            Log.w("MainActivity", "reponse : $it")
            handleAPIData(it.toString())
        }, {
            Log.w("MainActivity", "erreur : $it")
        })
        Volley.newRequestQueue(this).add(jsonRequest)
    }

    private fun handleAPIData(data: String){
        val contactResult = Gson().fromJson(data, Model::class.java)
        val contactCategory =contactResult.results
        val adapter = binding.recyclerViewList.adapter as ContactAdapter
        adapter.refreshList(contactCategory as ArrayList<Results>)
    }
}
