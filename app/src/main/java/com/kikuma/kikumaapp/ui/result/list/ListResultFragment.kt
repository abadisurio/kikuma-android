package com.kikuma.kikumaapp.ui.result.list

import android.R.attr.*
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kikuma.kikumaapp.R
import com.kikuma.kikumaapp.data.source.local.entity.ModelResultEntity
import com.kikuma.kikumaapp.databinding.FragmentListResultBinding
import com.kikuma.kikumaapp.ui.result.DiseaseResultFragment
import com.kikuma.kikumaapp.viewmodel.ViewModelFactory
import com.kikuma.kikumaapp.vo.Status


class ListResultFragment : Fragment(){


    companion object {
        const val EXTRA_RESULT = "extra_result"
        const val EXTRA_DISEASE = "extra_disease"
        const val EXTRA_HISTORY_ID = "extra_history_id"
    }

    private lateinit var fragmentListResultBinding: FragmentListResultBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentListResultBinding = FragmentListResultBinding.inflate(layoutInflater, container, false)
        return fragmentListResultBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[ListResultViewModel::class.java]

        val bundle = this.arguments
        if (bundle != null){
            val historyId = bundle.getString(EXTRA_HISTORY_ID)
            val listAdapter = ListResultAdapter()
            fragmentListResultBinding.progressBar.visibility = View.VISIBLE

            if(historyId != null){
                Log.d("ini historyId", historyId)

                viewModel.getOneHistory(historyId).observe(viewLifecycleOwner, { result ->
                    if (result != null) {
                        when (result.status) {
                            Status.LOADING -> fragmentListResultBinding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {

                                val imageView = fragmentListResultBinding.imageView
                                val imageData = result.data?.imageData

                                val decodedString: ByteArray = Base64.decode(imageData, Base64.DEFAULT)
                                val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

                                imageView.setImageBitmap(decodedByte)
                                imageView.rotation = 90f

                            }
                            Status.ERROR -> {
                                fragmentListResultBinding.progressBar.visibility = View.GONE
                                Toast.makeText(context, "An Error Occured", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })

                viewModel.refreshListModelResult(historyId).observe(viewLifecycleOwner, { listResult ->
                    if (listResult != null) {
                        when (listResult.status) {
                            Status.LOADING -> fragmentListResultBinding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                Log.d("ini listResult", listResult.data.toString())
                                fragmentListResultBinding.progressBar.visibility = View.GONE
                                listAdapter.setResult(listResult.data)
                                listAdapter.notifyDataSetChanged()

                                with(fragmentListResultBinding.rvListResult) {
                                    layoutManager = LinearLayoutManager(context)
                                    setHasFixedSize(true)
                                    adapter = listAdapter

                                    listAdapter.setOnItemClickCallback(object : ListResultAdapter.OnItemClickCallback {
                                        override fun onItemClicked(result: ModelResultEntity) {
                                            val mDiseaseResultFragment = DiseaseResultFragment()

                                            val mBundle = Bundle()
                                            mBundle.putString(DiseaseResultFragment.EXTRA_DISEASE, result.disease)
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
                            Status.ERROR -> {
                                fragmentListResultBinding.progressBar.visibility = View.GONE
                                Toast.makeText(context, "An Error Occured", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }
    }
}

//fragmentListResultBinding.progressBar.visibility = View.GONE
//listAdapter.setResult(listResult.data)
//listAdapter.notifyDataSetChanged()
//
//with(fragmentListResultBinding.rvListResult) {
//    layoutManager = LinearLayoutManager(context)
//    setHasFixedSize(true)
//    adapter = listAdapter
//
//    listAdapter.setOnItemClickCallback(object : ListResultAdapter.OnItemClickCallback {
//        override fun onItemClicked(result: ModelResultEntity) {
//            val mDiseaseResultFragment = DiseaseResultFragment()
//
//            val mBundle = Bundle()
//            mBundle.putString(DiseaseResultFragment.EXTRA_RESULT, result.toString())
//            mBundle.putString(DiseaseResultFragment.EXTRA_DISEASE, result.toString())
//            mDiseaseResultFragment.arguments = mBundle
//
//            val mFragmentManager = fragmentManager
//            mFragmentManager?.beginTransaction()?.apply {
//                replace(
//                        R.id.frame_container,
//                        mDiseaseResultFragment,
//                        DiseaseResultFragment::class.java.simpleName
//                )
//                addToBackStack(null)
//                commit()
//            }
//        }
//    })
//}