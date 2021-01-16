package com.example.anchorbookskvh.views

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.RecyclerView
import com.example.anchorbookskvh.R
import com.example.anchorbookskvh.databinding.ListItemViewholderBinding
import com.example.anchorbookskvh.model.dataclass.Book
import com.squareup.picasso.Picasso


class ListAdapter(var iAdapter: IListAdapter) : RecyclerView.Adapter<ListAdapter.BookVH>() {

    private var bookList = listOf<Book>()

    fun updateList(list: List<Book>) {
        bookList = list
        notifyDataSetChanged()
    }

    inner class BookVH(var binding: ListItemViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.bookName.text = book.title
            binding.bookAuthor.text = book.author
            Picasso.get().load(book.imageLink).placeholder(R.drawable.ic_launcher_foreground)
                .resize(
                    300,
                    450
                ).into(binding.bookImage) //quizas alla que aplicar un resize y centercrop
            itemView.setOnClickListener {

                iAdapter.idFromBook(book.id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookVH {
        return BookVH(ListItemViewholderBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BookVH, position: Int) {
        holder.bind(bookList[position])
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    interface IListAdapter {
        fun idFromBook(id: Int)
    }
}