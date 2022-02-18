package com.example.parstagram

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PostAdapter(val context: Context, val posts: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        // Specify the layout file to use for this item

        val view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        val post = posts.get(position)
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    // Clean all elements of the recycler
    fun clear() {
        posts.clear()
        notifyDataSetChanged()
    }

    // Add a list of items
    fun addAll(allPosts: List<Post>) {
        posts.addAll(allPosts)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUserName: TextView
        val ivImage: ImageView
        val tvDescription: TextView
        val tvTimeLeft: TextView

        init {
            tvUserName = itemView.findViewById(R.id.tvUserName)
            ivImage = itemView.findViewById(R.id.ivImage)
            tvDescription = itemView.findViewById(R.id.tvDescription)
            tvTimeLeft = itemView.findViewById(R.id.tvTimeLeft)
        }

        fun bind(post: Post) {
            tvDescription.text = post.getDescription()
            tvUserName.text = post.getUser()?.username
            tvTimeLeft.text = getRelativeTimeAgo(post.createdAt.toString())

            // Populate image
            Glide.with(itemView.context).load(post.getImage()?.url).into(ivImage)
        }

        fun getRelativeTimeAgo(rawJsonDate: String): String {
            val twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy"
            val sf = SimpleDateFormat(twitterFormat, Locale.ENGLISH)
            sf.setLenient(true)
            var relativeDate = ""
            try {
                val dateMillis: Long = sf.parse(rawJsonDate).getTime()
                relativeDate = DateUtils.getRelativeTimeSpanString(
                    dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS
                ).toString()
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return relativeDate
        }
    }
}