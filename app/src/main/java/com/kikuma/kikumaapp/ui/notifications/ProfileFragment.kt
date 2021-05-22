package com.kikuma.kikumaapp.ui.notifications

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
import com.kikuma.kikumaapp.databinding.FragmentProfileBinding
import com.kikuma.kikumaapp.ui.home.HomeAdapter
import com.kikuma.kikumaapp.utils.DataDummy
import com.kikuma.kikumaapp.viewmodel.ViewModelFactory
import com.kikuma.kikumaapp.vo.Status

class ProfileFragment : Fragment() {

    private lateinit var fragmentProfileBinding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater)
        return fragmentProfileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val profileViewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

        val profileAdapter = ProfileAdapter()
        profileViewModel.getAllHistory().observe(viewLifecycleOwner, { history ->
            if (history != null) {
                when (history.status) {
                    Status.LOADING -> fragmentProfileBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        fragmentProfileBinding.progressBar.visibility = View.GONE
                        profileAdapter.submitList(history.data)
                    }
                    Status.ERROR -> {
                        fragmentProfileBinding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "An Error Occured", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(fragmentProfileBinding.rvHistory) {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(false)
            this.adapter = profileAdapter
        }
    }
}