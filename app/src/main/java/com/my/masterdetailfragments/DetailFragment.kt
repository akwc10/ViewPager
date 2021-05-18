package com.my.masterdetailfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.my.viewpager.R

class DetailFragment : Fragment() {
    private val position by lazy { arguments?.getInt(KEY_POSITION_ARG) ?: -1 }
    private val detailTextView by lazy { requireView().findViewById<TextView>(R.id.detail_text_view) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.detail_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("@@@ $position DetailFragment().onViewCreated()")
        detailTextView.text = String.format("%s %d", getString(R.string.detail_text_view), position)
    }

    override fun onDestroy() {
        println("@@@ $position DetailFragment().onDestroy()")
        super.onDestroy()
    }

    companion object {
        private const val KEY_POSITION_ARG = "position"

        fun newInstance(position: Int) = DetailFragment().apply {
            arguments = Bundle().apply { putInt(KEY_POSITION_ARG, position) }
        }
    }
}