package com.kikuma.kikumaapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.databinding.ActivitySignUpBinding
import com.kikuma.kikumaapp.databinding.FragmentHomeBinding
import com.kikuma.kikumaapp.utils.DataDummy
import com.kikuma.kikumaapp.viewmodel.ViewModelFactory
import com.kikuma.kikumaapp.vo.Status

class HomeFragment : Fragment() {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        //fragmentHomeBinding.tvName.text = resources.getString(R.string.hello, signUpBinding.edName)

        val homeAdapter = HomeAdapter()
        homeViewModel.getAllArticle().observe(viewLifecycleOwner, { articles ->
            if (articles != null) {
                when (articles.status) {
                    Status.LOADING -> fragmentHomeBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        fragmentHomeBinding.progressBar.visibility = View.GONE
                        homeAdapter.submitList(articles.data)
                    }
                    Status.ERROR -> {
                        fragmentHomeBinding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "An Error Occured", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(fragmentHomeBinding.rvArticle) {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(false)
            this.adapter = homeAdapter
        }
    }
}