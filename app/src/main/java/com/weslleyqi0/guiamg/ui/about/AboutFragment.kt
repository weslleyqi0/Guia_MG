package com.weslleyqi0.guiamg.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.weslleyqi0.guiamg.BuildConfig
import com.weslleyqi0.guiamg.databinding.FragmentAboutBinding
import com.weslleyqi0.guiamg.ui.BaseFragment
import com.weslleyqi0.guiamg.util.URL_GITHUB


class AboutFragment : BaseFragment<FragmentAboutBinding>() {

    override fun getViewBinding(): FragmentAboutBinding = FragmentAboutBinding.inflate(layoutInflater)

    override fun initializeUi() {
        binding.txtVersionName.text = getVersionName()

        binding.txtGithubLink.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(URL_GITHUB))
            startActivity(browserIntent)
        }
    }

    private fun getVersionName(): String {
        return "Versão ${BuildConfig.VERSION_NAME}"
    }
}