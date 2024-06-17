package com.lexleontiev.tapyou.test.app.ui

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lexleontiev.tapyou.test.app.R
import com.lexleontiev.tapyou.test.app.databinding.FragmentResultBinding
import com.lexleontiev.tapyou.test.app.ui.compose.ChartView
import com.lexleontiev.tapyou.test.app.ui.compose.ErrorStub
import com.lexleontiev.tapyou.test.app.ui.compose.LoadingCircle


class ResultFragment: BindingFragment<FragmentResultBinding>() {

    private lateinit var viewModel: ResultViewModel
    private val pointsAdapter = PointAdapter()

    override fun getViewBinding() = FragmentResultBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val count = arguments?.getInt(KEY_COUNT) ?: 0
        binding.recyclerView.adapter = pointsAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java).apply {
            screenState.observe(viewLifecycleOwner) {
                when (it) {
                    is ResultScreenState.Loading, is ResultScreenState.Error -> {
                        binding.stub?.isVisible = true
                        binding.recyclerView.isVisible = false
                        binding.headline.root.isVisible = false
                        binding.chart.isVisible = false
                        pointsAdapter.submitList(emptyList())
                    }
                    is ResultScreenState.Data -> {
                        binding.stub?.isVisible = false
                        binding.recyclerView.isVisible = true
                        binding.headline.root.isVisible = true
                        binding.chart.isVisible = true
                        pointsAdapter.submitList(it.data)
                    }
                }
            }
        }

        binding.chart.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    ChartView(
                        state = viewModel.screenState.observeAsState(
                            initial = ResultScreenState.Loading
                        )
                    )
                }
            }
        }

        binding.stub?.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    Box(contentAlignment = Alignment.Center) {
                        val state = viewModel.screenState.observeAsState(
                            initial = ResultScreenState.Loading
                        )
                        when (state.value) {
                            is ResultScreenState.Loading -> LoadingCircle(
                                colorInt = MaterialTheme.colorScheme.primary.toArgb(),
                                textResId = R.string.loading
                            )
                            is ResultScreenState.Error -> ErrorStub()
                            else -> {}
                        }
                    }
                }
            }
        }

        if (savedInstanceState == null) {
            viewModel.requestPoints(count)
        }
    }

    companion object {

        private const val KEY_COUNT = "key_count"

        fun newInstance(count: Int): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle().apply {
                putInt(KEY_COUNT, count)
            }
            fragment.arguments = args
            return fragment
        }
    }
}