package com.example.anchorbookskvh.views

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.anchorbookskvh.R
import com.example.anchorbookskvh.databinding.BookDetailFragmentBinding
import com.example.anchorbookskvh.model.dataclass.BookDetail
import com.example.anchorbookskvh.model.isOnline
import com.example.anchorbookskvh.viewmodel.BookVM
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

private const val ARG_PARAM1 = "param1"

class BookDetailFragment : Fragment() {

    private var param1: Int = 0
    private val vm: BookVM by activityViewModels()
    private lateinit var binding: BookDetailFragmentBinding

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
        binding = BookDetailFragmentBinding.inflate(layoutInflater)
        checkConnectivity(param1)
        vm.getBookDetail(param1).observe(viewLifecycleOwner, { book ->
            if (book != null) {
                binding.apply {
                    setViewDetails(binding,book)
                    detailButton.intentToMail(book)
                    wikiButton.intentToBrowser(book)
                }
            }

        })

        return binding.root
    }
    fun checkConnectivity(id:Int){
        if(!isOnline(requireContext())){
            vm.getBookDetail(id).observe(viewLifecycleOwner, {
                if(it!=null){
                    showFailWidget(false)

                }else{
                    showFailWidget(true)
                    binding.buttonRetryConnection.setOnClickListener {
                        if(isOnline(requireContext())){
                            showFailWidget(false)
                            vm.setBookDetailIntoDB(id)
                        } else{
                            Snackbar.make(requireActivity().findViewById(android.R.id.content),"No hay internet", Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            })

        } else {
            vm.setBookDetailIntoDB(id)
        }
    }
    private fun showFailWidget(show :Boolean){
        if(show){
            binding.textRetryConnection.visibility=View.VISIBLE
            binding.buttonRetryConnection.visibility=View.VISIBLE
        } else{
            binding.textRetryConnection.visibility=View.GONE
            binding.buttonRetryConnection.visibility=View.GONE
        }
    }

    fun setViewDetails(binding:BookDetailFragmentBinding, book:BookDetail){
        binding.detailTxtBookName.text = book.title
        binding.detailTxtBookAuthor.text = book.author
        Picasso.get().load(book.imageLink)
            .placeholder(R.drawable.ic_launcher_foreground).into(
                binding.detailImgImage
            )
        binding.detailTxtLastPrice.text = book.lastPrice?.formatChileanPeso()
        binding.detailTxtLastPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        binding.detailTxtPrice.text = book.price?.formatChileanPeso()
        binding.detailTxtYear.text = book.year.toString()
        binding.detailTxtCountry.text = book.country
        binding.detailTxtPages.text = book.pages.toString()
        binding.detailTxtLanguage.text = book.language

        if (book.delivery == true) {
            binding.delivery.visibility = View.VISIBLE
        } else {
            binding.noDelivery.visibility = View.VISIBLE
        }
    }

    fun Button.intentToMail(book:BookDetail){
        this.setOnClickListener {
            //intent a gmail
            val intent = Intent(Intent.ACTION_SEND)
            val to = "ventas@anchorBooks.cl"
            val addressees = arrayOf(to)
            val subject = "Consulta por libro ${book.title} id ${book.id}"
            val message =
                "Hola \nVi el libro ${book.title} de codigo ${book.id} y me gustaría que me contactaran a este correo o al siguiente número _________ \nQuedo atento."
            intent.putExtra(Intent.EXTRA_EMAIL, addressees)
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Contactar Area de Ventas:"))
        }
    }

    fun Button.intentToBrowser(book: BookDetail){
        this.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(book.link))
            startActivity(browserIntent) }
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

fun Int.formatChileanPeso(): String {
    val formating = String.format("%,d", this).replace(',', '.')
    val formatedPrice = "$${formating}"
    return formatedPrice
}