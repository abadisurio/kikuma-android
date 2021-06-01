package com.kikuma.kikumaapp.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.databinding.FragmentProfileBinding
import com.kikuma.kikumaapp.ui.result.DiseaseResultViewModel
import com.kikuma.kikumaapp.ui.splashscreen.SplashScreenActivity
import com.kikuma.kikumaapp.utils.SignOut
import com.kikuma.kikumaapp.viewmodel.ViewModelFactory
import com.kikuma.kikumaapp.vo.Status

class ProfileFragment : Fragment() {

    private lateinit var fragmentProfileBinding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater)
        auth = Firebase.auth
        return fragmentProfileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val profileViewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

        val profileAdapter = ProfileAdapter()
        profileViewModel.refreshAllHistory().observe(viewLifecycleOwner, { history ->
            if (history != null) {
                when (history.status) {
                    Status.LOADING -> fragmentProfileBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        fragmentProfileBinding.progressBar.visibility = View.GONE
                        fragmentProfileBinding.buttonSignOut.setOnClickListener {
                            AlertDialog.Builder(requireContext())
                                .setMessage(getString(R.string.sign_out_confirmation))
                                .setPositiveButton(getString(R.string.ok)) { _, _ ->
                                    SignOut(requireActivity()).doSignOut()
                                    activity?.finish()
                                    startActivity(Intent(activity, SplashScreenActivity::class.java))

                                }
                                .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
                                .show()
                        }
                        profileAdapter.submitList(history.data)
                    }
                    Status.ERROR -> {
                        fragmentProfileBinding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "An Error Occured", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })


        Glide.with(this)
            .load(R.drawable.stock_avatar)
            .transform(RoundedCorners(40))
            .apply(
                RequestOptions
                    .placeholderOf(R.drawable.ic_loading)
                    .centerCrop()
                    .error(R.drawable.ic_error))
            .into(fragmentProfileBinding.imageProfile)

        with(fragmentProfileBinding.rvHistory) {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(false)
            this.adapter = profileAdapter
        }

        fragmentProfileBinding.tvName.text = auth.currentUser?.displayName

    }

}