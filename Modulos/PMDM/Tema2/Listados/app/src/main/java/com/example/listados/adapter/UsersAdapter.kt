package com.example.listados.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.listados.R
import com.example.listados.model.Contact

class UsersAdapter(var userList: ArrayList<Contact>, var context: Context) :
    RecyclerView.Adapter<UsersAdapter.MyHolder>() {

    //TODO: PASO 4 -> creo un objeto de la interfaz de callback y la inicializo en el bloque init
    private var listener: OnUserRecyclerListener
    init {
        listener = context as OnUserRecyclerListener
    }
    class MyHolder(item: View) : ViewHolder(item) {
        /* estructura base del recycler view, template de la fila
         * la vista (view) hace referencia al item_recycler.xml
         * cada variable hace referencia a cada uno de los elementos que mostrarán datos en item_recycler
         */
        var imageArea: ImageView
        var nameArea: TextView
        init {
            imageArea = item.findViewById(R.id.row_image)
            nameArea = item.findViewById(R.id.row_name)
        }
    }

    // estos métodos de abajo definen cómo se pintan cada una de las filas

    // crea el template para cada fila y lo almacena en caché
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        /*
        * LayoutInflater -> Objeto de tipo vista, aquí se usa para transformar el objeto R(int) en View
        * .from(context) -> qué cogemos. En este caso se pasa la pantalla donde se ponen los datos
        * .inflate(arg1 -> ruta al xml donde está el template
        * ,arg2 -> dónde quieres ponerlo. en este caso en el parent, que representa el layoutinflater
        * ,arg3 -> si lo quieres attached. Si es true, los elementos se mantienen inmutables (en
        *           nuestro caso no interesa porque es una lista actualizable)
        * */
        var vista: View =
            LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return MyHolder(vista) // devuelve el template para cada fila
    }

    // obtiene número de elementos que estarán en el objeto (una lista en este caso)
    override fun getItemCount(): Int {
        return userList.size
    }

    // monta la lista utilizando el número de elementos y el template antes generado
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var user = userList[position]
        holder.imageArea.setImageResource(user.image)
        holder.nameArea.text = user.name
        holder.nameArea.setOnClickListener {
            // TODO: PASO 3 -> llamo al método de la interfaz y le paso el usuario de la lista
            listener.onUserSelected(user)
        }
    }

    // permite añadir un elemento nuevo
    fun addContact(contact: Contact) {
        this.userList.add(contact)
        notifyItemInserted(userList.size - 1)
    }

    fun removeAt(position: Int) {
        this.userList.removeAt(position)
        notifyItemRemoved(position)
    }

    // TODO: PASO 1 -> crear interfaz en el origen de los datos (adapter en este caso)
    interface OnUserRecyclerListener {
        // TODO: PASO 2 -> crear un método con el dato a comunicar como parámetro
        fun onUserSelected(contact: Contact)
    }
}