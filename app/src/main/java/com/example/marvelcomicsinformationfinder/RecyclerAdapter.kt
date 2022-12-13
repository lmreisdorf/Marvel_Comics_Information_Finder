package com.example.marvelcomicsinformationfinder

import android.media.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

public class RecyclerAdapter (
    private val comics: List<Comics>,
    private val mListener: OnListFragmentInteractionListener?
)
    : RecyclerView.Adapter<RecyclerAdapter.ComicViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler, parent, false)
        return ComicViewHolder(view)
    }

    inner class ComicViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Comics? = null
        val mTitle: TextView = mView.findViewById<View>(R.id.title) as TextView
        val mDescription: TextView = mView.findViewById<View>(R.id.description) as TextView
        val mPicture: ImageView = mView.findViewById<View>(R.id.picture) as ImageView

        override fun toString(): String {
            return mTitle.toString() + " '" + mDescription.text + " '"
        }
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = comics[position]

        holder.mItem = comic
        holder.mTitle.text = comic.title
        holder.mDescription.text = comic.description
        val url = comic.picture.toString()
        Glide.with(holder.mView)
            .load(url)
          //  .centerInside()
            .into(holder.mPicture)
    }

    override fun getItemCount(): Int {
        return comics.size
    }
}
