package com.rkm.weatherapp

import android.net.http.HttpException
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.RequiresExtension
import com.rkm.weatherapp.databinding.ActivityMainBinding
import com.rkm.weatherapp.utils.retrofitinstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.splashscreen)
        Handler(Looper.getMainLooper()).postDelayed(object : Runnable{
            override fun run() {
                setContentView(binding.root)
            }
        },2000)

        getdata()

    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getdata() {
        GlobalScope.launch (Dispatchers.IO){

            val response = try {

                retrofitinstance.api.getweatherdata("gorakhpur","metric",applicationContext.getString(R.string.key))

            }catch (e:IOException){
                Toast.makeText(applicationContext,"IO ERROR",Toast.LENGTH_LONG).show()
                return@launch

            }catch (e: HttpException){

                Toast.makeText(applicationContext,"HTTP ERROR",Toast.LENGTH_LONG).show()
                return@launch

            }


            if(response.isSuccessful && response.body()!=null){

                withContext(Dispatchers.Main){

                    binding.temp.text = "T:-- ${response.body()!!.main.temp}"

                }
            }


        }
    }
}