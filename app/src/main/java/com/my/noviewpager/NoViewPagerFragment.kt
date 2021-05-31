package com.my.noviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.adapter.MyListAdapter
import com.my.behaviors.BehaviorFragment
import com.my.viewpager.R
import kotlinx.coroutines.*
import java.util.*

class NoViewPagerFragment : BehaviorFragment() {
    private val list_container by lazy { requireView().findViewById<RecyclerView>(R.id.list_container) }
    private val myListAdapter by lazy { MyListAdapter() }
    private val myCoroutineScope by lazy { CoroutineScope(Job() + Dispatchers.Main) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.no_view_pager_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = getString(R.string.fragment_with_list_adapter_title)
        list_container.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = myListAdapter
            addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
        }
        myListAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        savedInstanceState?.getStringArrayList(KEY_DATA_ARG)?.let {
//            myListAdapter.submitList(it)
            submitListDelayed(it)
        } ?: submitListDelayed(DATA)
    }

    private fun submitListDelayed(data: List<String>, delayMs: Long = 1500) {
        myCoroutineScope.launch {
            delay(delayMs)
            myListAdapter.submitList(data)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.apply {
            putStringArrayList(
                KEY_DATA_ARG,
                myListAdapter.currentList.toList() as ArrayList<String>
            )
        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        myCoroutineScope.cancel()
        super.onDestroyView()
    }

    companion object {
        private const val KEY_DATA_ARG = "data"
        private val DATA = List(100) { "Item ${it + 1}" }
    }
}