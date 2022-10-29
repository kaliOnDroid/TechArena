package com.kaliondroid.techarena.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaliondroid.techarena.databinding.ActivityMainBinding
import com.kaliondroid.techarena.ui.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var pagerAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}