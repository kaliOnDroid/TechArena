package com.kaiarcz.techarena.ui.view

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.kaiarcz.techarena.R
import com.kaiarcz.techarena.databinding.FragmentHomeBinding
import com.kaiarcz.techarena.ui.adapter.NewsAdapter
import com.kaiarcz.techarena.ui.view.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        private const val CHROME_PACKAGE_NAME = "com.android.chrome"
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var pagerAdapter: NewsAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAdapter()
        initView()
        initObservers()
    }

    private fun initAdapter() {
        pagerAdapter = NewsAdapter { url ->
            Log.d("TAG", "initAdapter: clicked ")
            newsDetailPage(url)
        }
    }

    private fun initView() {
        binding.apply {
            pager.adapter = pagerAdapter
        }
    }

    private fun newsDetailPage(url: String?) {
        val builder = CustomTabsIntent.Builder()

        // to set the toolbar color use CustomTabColorSchemeParams
        // since CustomTabsIntent.Builder().setToolBarColor() is deprecated

        val params = CustomTabColorSchemeParams.Builder()
        params.setToolbarColor(ContextCompat.getColor(requireContext(), R.color.white))
        builder.setDefaultColorSchemeParams(params.build())

        // shows the title of web-page in toolbar
        builder.setShowTitle(true)

        // setShareState(CustomTabsIntent.SHARE_STATE_ON) will add a menu to share the web-page
        builder.setShareState(CustomTabsIntent.SHARE_STATE_ON)

        // To modify the close button, use
        // builder.setCloseButtonIcon(bitmap)

        // to set weather instant apps is enabled for the custom tab or not, use
        builder.setInstantAppsEnabled(true)

        //  To use animations use -
        builder.setStartAnimations(requireContext(), R.anim.slide_up_enter, R.anim.slide_up_exit)
        builder.setExitAnimations(requireContext(), R.anim.slide_down_enter, R.anim.slide_down_exit)
        val customBuilder = builder.build()
        customBuilder.intent.setPackage(CHROME_PACKAGE_NAME)
        customBuilder.launchUrl(requireContext(), Uri.parse(url))
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.collectLatest {
                pagerAdapter.submitData(lifecycle, it)
            }
        }
    }
}