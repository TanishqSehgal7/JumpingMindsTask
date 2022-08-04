package com.example.jumpingmindstask.Adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.util.Linkify
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.get
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jumpingmindstask.Model.TvShowsModelClassItem
import com.example.jumpingmindstask.R
import com.example.jumpingmindstask.databinding.TvShowItemLayoutBinding

class TvShowAdapter(private val context: Context) : RecyclerView.Adapter<TvShowAdapter.MyViewHolderClass>() {

    private var issummaryButtonClicked:Boolean=false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderClass {
       return MyViewHolderClass(TvShowItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolderClass, position: Int) {
        val currentTvShowItem = tvShows.get(position)
        holder.binding.apply {
            showName.text =currentTvShowItem.name
            val urlOfImage: String? =  currentTvShowItem.image?.original
            Glide.with(context).load(urlOfImage).into(tvShowImg)
            genre.text = "Genre: " + currentTvShowItem.genres.toString()
            premiereDate.text = "Premiered On: " + currentTvShowItem.premiered
            language.text = "Language: "+ currentTvShowItem.language
            showType.text = "Show Type: " + currentTvShowItem.type
            officialSite.text = currentTvShowItem.officialSite
            Linkify.addLinks(officialSite, Linkify.WEB_URLS)
            summaryText.text = "Summary: "+currentTvShowItem.summary
        }
                if (!issummaryButtonClicked) {
                    holder.binding.summarybtn.setOnClickListener {
                        TransitionManager.beginDelayedTransition(holder.binding.root, AutoTransition())
                        holder.binding.summaryText.visibility = View.VISIBLE
                        issummaryButtonClicked = true
                        notifyDataSetChanged();
                    }
                } else if (holder.binding.summarybtn.visibility==View.VISIBLE && issummaryButtonClicked) {
                        holder.binding.summarybtn.setOnClickListener {
                            issummaryButtonClicked=false
                            TransitionManager.beginDelayedTransition(holder.binding.root, AutoTransition())
                            holder.binding.summaryText.visibility = View.GONE
                            notifyDataSetChanged();
                        }
                }
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }


    class MyViewHolderClass(val binding: TvShowItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    }

        private val COMPARATOR= object : DiffUtil.ItemCallback<TvShowsModelClassItem>() {
            override fun areItemsTheSame(oldItem: TvShowsModelClassItem, newItem: TvShowsModelClassItem): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TvShowsModelClassItem, newItem: TvShowsModelClassItem): Boolean {
                return oldItem==newItem
            }
        }

        private val asyncDiffer = AsyncListDiffer(this, COMPARATOR)
        var tvShows:List<TvShowsModelClassItem>
        get() = asyncDiffer.currentList
        set(value) {
            asyncDiffer.submitList(value)
        }
}