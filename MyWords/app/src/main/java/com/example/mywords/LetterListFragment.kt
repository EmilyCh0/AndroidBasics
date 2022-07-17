package com.example.mywords

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mywords.data.SettingsDataStore
import com.example.mywords.databinding.FragmentLetterListBinding
import kotlinx.coroutines.launch


class LetterListFragment : Fragment() {

    private var _binding: FragmentLetterListBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private var isLinearLayout = true

    private lateinit var settingsDataStore: SettingsDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView

        //chooseLayout()

        settingsDataStore = SettingsDataStore(requireContext())
        settingsDataStore.prefenceFlow.asLiveData().observe(viewLifecycleOwner) { value ->
            isLinearLayout = value
            chooseLayout()
            activity?.invalidateOptionsMenu()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)
        val layoutBtn = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutBtn)
    }

    private fun chooseLayout() {
        if(isLinearLayout){
            recyclerView.layoutManager = LinearLayoutManager(context)
        }else{
            recyclerView.layoutManager = GridLayoutManager(context, 4)
        }
        recyclerView.adapter = LetterAdapter()
    }

    private fun setIcon(menuItem: MenuItem?) {
        menuItem?.icon = if(isLinearLayout){
            ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
        }else{
            ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_switch_layout -> {
                isLinearLayout = !isLinearLayout
                chooseLayout()
                setIcon(item)
                lifecycleScope.launch {
                    settingsDataStore.saveLayoutToPreferencesStore(isLinearLayout, requireContext())
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}