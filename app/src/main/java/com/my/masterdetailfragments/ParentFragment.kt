package com.my.masterdetailfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.my.viewpager.R

class ParentFragment : Fragment() {
    private val position by lazy { arguments?.getInt(POSITION) ?: -1 }
    private val detail_container: FragmentContainerView? by lazy { requireActivity().findViewById(R.id.detail_container) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.parent_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("@@@ $position ParentFragment().onViewCreated()")
        childFragmentManager.commit {
            replace(R.id.master_container, MasterFragment.newInstance(position))
        }
        detail_container?.let {
            childFragmentManager.commit {
                replace(R.id.detail_container, DetailFragment.newInstance(position))
            }
        }
    }

    override fun onDestroy() {
        println("@@@ $position ParentFragment().onDestroy()")
        super.onDestroy()
    }

    companion object {
        private const val POSITION = "position"

        fun newInstance(position: Int) = ParentFragment().apply {
            arguments = Bundle().apply { putInt(POSITION, position) }
        }
    }
}