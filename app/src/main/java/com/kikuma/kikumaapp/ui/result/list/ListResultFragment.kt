package com.kikuma.kikumaapp.ui.result.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.data.source.local.entity.DiseaseEntity
import com.kikuma.kikumaapp.databinding.FragmentListResultBinding
import com.kikuma.kikumaapp.ui.result.DiseaseResultFragment
import com.kikuma.kikumaapp.utils.DataDummy
import com.kikuma.kikumaapp.viewmodel.ViewModelFactory

class ListResultFragment : Fragment(){

    private lateinit var fragmentListResultBinding: FragmentListResultBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentListResultBinding = FragmentListResultBinding.inflate(layoutInflater, container, false)
        return fragmentListResultBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[ListResultViewModel::class.java]

        val listAdapter = ListResultAdapter()
        fragmentListResultBinding.progressBar.visibility = View.VISIBLE
        viewModel.getListResult().observe(viewLifecycleOwner, { listResult ->
            fragmentListResultBinding.progressBar.visibility = View.GONE
            listAdapter.setResult(listResult.data)
            listAdapter.notifyDataSetChanged()

        })

        with(fragmentListResultBinding.rvListResult) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = listAdapter

            listAdapter.setOnItemClickCallback(object : ListResultAdapter.OnItemClickCallback {
                override fun onItemClicked(result: DiseaseEntity) {
                    val mDiseaseResultFragment = DiseaseResultFragment()

                    val mBundle = Bundle()
                    mBundle.putString(DiseaseResultFragment.EXTRA_RESULT, result.toString())
                    mDiseaseResultFragment.arguments = mBundle

                    val mFragmentManager = fragmentManager
                    mFragmentManager?.beginTransaction()?.apply {
                        replace(
                            R.id.frame_container,
                            mDiseaseResultFragment,
                            DiseaseResultFragment::class.java.simpleName
                        )
                        addToBackStack(null)
                        commit()
                    }
                }
            })
        }
    }
}