package com.kikuma.kikumaapp.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.databinding.FragmentProfileBinding
import com.kikuma.kikumaapp.ui.home.HomeAdapter
import com.kikuma.kikumaapp.utils.DataDummy
import com.kikuma.kikumaapp.viewmodel.ViewModelFactory

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
        val viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]
        val history = viewModel.getAllHistory()

        val profileAdapter = ProfileAdapter()

        fragmentProfileBinding.progressBar.visibility = View.VISIBLE
        viewModel.getAllHistory().observe(viewLifecycleOwner, { history ->
            fragmentProfileBinding.progressBar.visibility = View.GONE
            profileAdapter.setHistory(history)
            profileAdapter.notifyDataSetChanged()
        })

        with(fragmentProfileBinding.rvHistory) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = profileAdapter
        }
    }
}