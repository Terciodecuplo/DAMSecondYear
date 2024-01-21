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
import com.example.listados.model.User

class UsersAdapter(var userList: ArrayList<Contact>, var context: Context) :
    RecyclerView.Adapter<UsersAdapter.MyHolder>() {

    class MyHolder(item: View) : ViewHolder(item) {
        /* estructura base del recycler view, template de la fila
         * la vista (view) hace referencia al item_recycler.xml
         * cada variable hace referencia a cada uno de los elementos que mostrarán datos en item_recycler
         */
        var imageArea: ImageView
        var nameArea: TextView

        // init es un método que se ejecuta siempre, sí o sí
        init {
            imageArea = item.findViewById(R.id.row_image)
            nameArea = item.findViewById(R.id.row_name)
        }
    }

    // estos métodos de abajo definen cómo se pintan cada una de las filas

    // crea el template para cada fila y lo almacena en caché
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder   {
        /*
        * LayoutInflater -> Objeto de tipo vista, se usa para transformar el objeto R(int) en View
        * .from(context) -> qué cogemos. En este caso se pasa la pantalla donde se ponen los datos
        * .inflate(arg1 -> ruta al xml donde está el template
        * ,arg2 -> dónde quieres ponerlo. en este caso en el parent, que representa el layoutinflater
        * ,arg3 -> si lo quieres attached. Si es true, los elementos se mantienen inmutables (en
        *           nuestro caso no interesa porque es una lista actualizable)
        * */
        var vista: View = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
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
    }

    // permite añadir un
    fun addContact(contact:Contact){
        this.userList.add(contact)
        notifyItemInserted(userList.size-1)
    }

    fun removeAt(position: Int){
        this.userList.removeAt(position)
        notifyItemRemoved(position)
    }
}