package com.example.anchorbookskvh.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.anchorbookskvh.databinding.BookDetailFragmentBinding

private const val ARG_PARAM1 = "param1"
class BookDetailFragment:Fragment() {

    private var param1: Int = 0
    private lateinit var binding:BookDetailFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //el id que viene desde BookListFragment, se guarda en la variable param1
        arguments?.let { param1 = it.getInt(ARG_PARAM1) }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= BookDetailFragmentBinding.inflate(layoutInflater)




        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            BookDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, id)
                }
            }
    }
}