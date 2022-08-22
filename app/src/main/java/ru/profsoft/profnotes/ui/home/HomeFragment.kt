package ru.profsoft.profnotes.ui.home

import android.os.Bundle
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.profsoft.profnotes.core.spanStringUnderline
import ru.profsoft.profnotes.data.model.MyTasks
import ru.profsoft.profnotes.databinding.FragmentHomeBinding
import ru.profsoft.profnotes.ui.home.adapter.NewNotesAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val myNotesAdapter by lazy { NewNotesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvAllTasks.apply {
            text = text.spanStringUnderline(
                startIndex = 0,
                endIndex = text.length
            )
        }
    }

    override fun onStart() {
        super.onStart()
        setupMyTasks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupMyTasks() {
        with(binding) {
            rvMyTasks.adapter = myNotesAdapter
            rvMyTasks.layoutManager = object : LinearLayoutManager(requireContext()) {
                override fun canScrollVertically() = false
            }

            myNotesAdapter.setItems(listOf(
                MyTasks(
                    id = 1,
                    title = "Test",
                    date = "Today",
                    status = "Новое",
                    description = "Test"
                ), MyTasks(
                    id = 2,
                    title = "Test",
                    date = "Today",
                    status = "В работе",
                    description = "Test"
                ), MyTasks(
                    id = 3,
                    title = "Test",
                    date = "Today",
                    status = "Отложено",
                    description = "Test"
                ), MyTasks(
                    id = 4,
                    title = "Test",
                    date = "Today",
                    status = "Выполнено",
                    description = "Test"
                )
            ))
        }
    }
}