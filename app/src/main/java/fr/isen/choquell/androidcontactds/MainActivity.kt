package fr.isen.choquell.androidcontactds

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
        binding.recyclerViewList.layoutManager = LinearLayoutManager(this)

        loadContactFromAPI()
    }


    private fun loadContactFromAPI(){
        Volley.newRequestQueue(this)
        val url = "https://randomuser.me/api/?results=10&nat=fr"
        val jsonObject = JSONObject()
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, jsonObject, {
                Log.w("MainActivity", "response : $it")
            }, {
                Log.w("MainActivity", "erreur : $it")
            }
        )
        Volley.newRequestQueue(this).add(jsonRequest)
    }
}
