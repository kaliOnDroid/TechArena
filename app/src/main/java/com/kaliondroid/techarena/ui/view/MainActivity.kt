package com.kaliondroid.techarena.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kaliondroid.techarena.databinding.ActivityMainBinding
import com.kaliondroid.techarena.ui.adapter.NewsAdapter
import com.kaliondroid.techarena.ui.view.viewmodel.MainViewModel
import com.kaliondroid.techarena.utils.VerticalStackTransformer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var pagerAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initObservers()
    }

    private fun initView() {
        binding.apply {
            pager.apply {
                adapter = pagerAdapter
                offscreenPageLimit = 3
                setPageTransformer(VerticalStackTransformer(3))
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.data.collectLatest {
                pagerAdapter.submitData(lifecycle, it)
            }
        }
    }
}