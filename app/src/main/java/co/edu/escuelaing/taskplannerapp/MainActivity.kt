package co.edu.escuelaing.taskplannerapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import co.edu.escuelaing.taskplannerapp.databinding.ActivityMainBinding
import co.edu.escuelaing.taskplannerapp.dto.TaskDTO
import co.edu.escuelaing.taskplannerapp.interfaces.TaskAPI
import co.edu.escuelaing.taskplannerapp.models.Task
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var edtCodigo: EditText? = null
    private var tvNombre: TextView? = null
    private var tvDescripcion: TextView? = null
    private var tvPrecio: TextView? = null
    private var btnBuscar: Button? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        edtCodigo = findViewById(R.id.edtCodigo)
        tvNombre = findViewById(R.id.tvNombre)
        tvDescripcion = findViewById(R.id.tvDescripcion)
        tvPrecio = findViewById(R.id.tvPrecio)
        btnBuscar = findViewById(R.id.btnBuscar)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun getCall(codigo:String){
        val taskApi: TaskAPI = RestEngine.getRestEngine().create(TaskAPI::class.java)
        val result: Call<Task> = taskApi.find(codigo)
        result.enqueue(object : Callback<Task> {
            override fun onFailure(call: Call<Task>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Task>, response: Response<Task>) {
                Toast.makeText(this@MainActivity, "OK",Toast.LENGTH_LONG).show()
            }
        })

    }
}