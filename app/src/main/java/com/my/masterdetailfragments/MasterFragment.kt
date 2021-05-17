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
    private val data = List(100) { "Item ${it + 1}" }
    private val position by lazy { arguments?.getInt(POSITION) ?: -1 }
    private val list_container by lazy { requireView().findViewById<RecyclerView>(R.id.list_container) }
    private val myAdapter by lazy { MyListAdapter() }
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
            adapter = myAdapter
            addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
        }
        myAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        savedInstanceState?.getStringArrayList(DATA)?.let {
            myAdapter.submitList(it)
        } ?: submitListDelayed(data)
    }

    private fun submitListDelayed(data: List<String>, delayMs: Long = 2000) {
        job = myCoroutineScope.launch {
            delay(delayMs)
            myAdapter.submitList(data)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.apply {
            putStringArrayList(DATA, myAdapter.currentList.toList() as ArrayList<String>)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        println("@@@ $position MasterFragment().onDestroy()")
        job?.cancel()
        super.onDestroy()
    }

    companion object {
        private const val POSITION = "position"
        private const val DATA = "data"

        fun newInstance(position: Int) = MasterFragment().apply {
            arguments = Bundle().apply { putInt(POSITION, position) }
        }
    }
}