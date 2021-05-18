package com.my.masterdetailfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.adapter.MyListAdapter
import com.my.viewpager.R
import kotlinx.coroutines.*
import java.util.*

class MasterFragment : Fragment() {
    private val position by lazy { arguments?.getInt(KEY_POSITION_ARG) ?: -1 }
    private val list_container by lazy { requireView().findViewById<RecyclerView>(R.id.list_container) }
    private val myListAdapter by lazy { MyListAdapter() }
    private val myCoroutineScope get() = CoroutineScope(Job() + Dispatchers.Main)
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.master_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("@@@ $position MasterFragment().onViewCreated()")

        list_container.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = myListAdapter
            addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
        }
        myListAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        savedInstanceState?.getStringArrayList(KEY_DATA_ARG)?.let {
            myListAdapter.submitList(it)
        } ?: submitListDelayed(DATA)
    }

    private fun submitListDelayed(data: List<String>, delayMs: Long = 2000) {
        job = myCoroutineScope.launch {
            delay(delayMs)
            myListAdapter.submitList(data)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.apply {
            putStringArrayList(KEY_DATA_ARG, myListAdapter.currentList.toList() as ArrayList<String>)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        println("@@@ $position MasterFragment().onDestroy()")
        job?.cancel()
        super.onDestroy()
    }

    companion object {
        private const val KEY_POSITION_ARG = "position"
        private const val KEY_DATA_ARG = "data"
        private val DATA = List(100) { "Item ${it + 1}" }

        fun newInstance(position: Int) = MasterFragment().apply {
            arguments = Bundle().apply { putInt(KEY_POSITION_ARG, position) }
        }
    }
}