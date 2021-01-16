package com.example.anchorbookskvh.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.anchorbookskvh.R
import com.example.anchorbookskvh.databinding.BookListFragmentBinding
import com.example.anchorbookskvh.viewmodel.BookVM

class BookListFragment : Fragment(), ListAdapter.IListAdapter {

    private lateinit var binding: BookListFragmentBinding
    private lateinit var mAdapter: ListAdapter
    private val vm: BookVM by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BookListFragmentBinding.inflate(layoutInflater)
        vm.setBooksWebIntoDB()
        mAdapter = ListAdapter(this)

        vm.getBookList().observe(viewLifecycleOwner, {
            mAdapter.updateList(it)
        })

        binding.recyclerBookList.apply {
            //setear grid layout y adapter, tengo que hacer adapter
            layoutManager = GridLayoutManager(activity, 3)

            adapter = mAdapter

        }






        return binding.root
    }

    override fun idFromBook(id: Int) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.frame_main, BookDetailFragment.newInstance(id))
            ?.addToBackStack("details")?.commit()
    }
}