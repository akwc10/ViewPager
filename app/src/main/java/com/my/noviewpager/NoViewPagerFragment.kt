package com.my.noviewpager

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

class NoViewPagerFragment : Fragment() {
    private val data = List(100) { "Item ${it + 1}" }
    private val list_container by lazy { requireView().findViewById<RecyclerView>(R.id.list_container) }
    private val myAdapter by lazy { MyListAdapter() }
    private val myCoroutineScope by lazy { CoroutineScope(Job() + Dispatchers.Main) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.no_view_pager_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Fragment with ListAdapter"
        list_container.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = myAdapter
            addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
        }
        myAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        savedInstanceState?.getStringArrayList(DATA)?.let {
//            myAdapter.submitList(it)
            submitListDelayed(it)
        } ?: submitListDelayed(data)
    }

    private fun submitListDelayed(data: List<String>, delayMs: Long = 1500) {
        myCoroutineScope.launch {
            delay(delayMs)
            myAdapter.submitList(data)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.apply {
            putStringArrayList(
                DATA,
                myAdapter.currentList.toList() as ArrayList<String>
            )
        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        myCoroutineScope.cancel()
        super.onDestroyView()
    }

    companion object {
        private const val DATA = "data"
    }
}