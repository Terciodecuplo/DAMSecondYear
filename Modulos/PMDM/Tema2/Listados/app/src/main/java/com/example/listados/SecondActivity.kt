package com.example.listados

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listados.adapter.UsersAdapter
import com.example.listados.databinding.ActivitySecondBinding
import com.example.listados.model.Contact
import com.example.listados.model.User
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var restoredFromPreviousScreen: User
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var userList: ArrayList<Contact>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Esta lista se generaría de otra forma, esto es una prueba. Si no se notifican los cambios
        // se tiene que crear la lista ANTES de instanciar el usersAdapter
        userList = ArrayList()
        userList.add(Contact("Jose", "Murcia", "628993830", R.drawable.user_image))
        userList.add(Contact("Javier", "Murcia", "602675331", R.drawable.user_image))
        userList.add(Contact("Carolina", "Muñoz", "626345734", R.drawable.user_image))
        userList.add(Contact("Ana", "Belmonte", "634578066", R.drawable.user_image))
        /* generamos una instancia del adaptador. this permite pasarle al constructor
            la clase SecondActivity. Podría usarse "applicationContext" pero dado que se usa una
            interfaz de callback, applicationContext no nos permitiría usarla.
         */
        usersAdapter = UsersAdapter(userList,this)

        // se usa el método getSerializable aunque esté deprecated
        restoredFromPreviousScreen = intent.extras?.getSerializable("usuario") as User
        binding.userName.text = restoredFromPreviousScreen.name
        binding.logoutBtn.setOnClickListener {
            finish()
        }
        binding.genderFilter.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selection = parent!!.adapter.getItem(position).toString()
                Snackbar.make(binding.root, selection, Snackbar.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }
        // parte gráfica -> XML
        binding.usersRecycler.adapter = usersAdapter
        // indica cómo se comporta el recyclerView, en este caso, se comporte linealmente vertical
        binding.usersRecycler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        // parte datos -> adapter RecyclerView.Adapter<ViewHolder> (el patrón de representación de los datos)


    }
}