package iuh.doancuoiki.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import iuh.doancuoiki.R
import iuh.doancuoiki.objects.Song
import iuh.doancuoiki.views.MusicDetailsActivity


class MusicAdapters(val context: Context, val layoutId: Int, val songs: ArrayList<Song>):
    RecyclerView.Adapter<MusicAdapters.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        var view : View = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)
        return ViewHolder(layoutId, view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Apply data into each view
        val song = songs[position]
        holder.name.text = song.name
        holder.singer.text = song.singer
        song.setPic(context, holder.image)
        // code nhan su kien click vao hinh trai tim
        holder.btnFavorite.setOnCheckedChangeListener { _, checked ->
            // hien thi thong bao
            Toast.makeText(context, "bai hat duoc yeu thich >> $checked", Toast.LENGTH_SHORT).show()
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MusicDetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putString("song", song.id)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    class ViewHolder(layoutId: Int, itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name : TextView
        var singer : TextView
        var image : ImageView
        var btnFavorite: CheckBox

        init {
            name = itemView.findViewById(R.id.name)
            singer = itemView.findViewById(R.id.singer)
            image = itemView.findViewById(R.id.image)
            btnFavorite = itemView.findViewById(R.id.cbHeart) // lien ket cai view ben xml vao code
//            button_loveSong = itemView.findViewById(R.id.button_loveSong)
        }
    }
}