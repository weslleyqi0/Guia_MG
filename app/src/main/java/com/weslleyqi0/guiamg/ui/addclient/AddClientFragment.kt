package com.weslleyqi0.guiamg.ui.addclient

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.weslleyqi0.guiamg.R
import com.weslleyqi0.guiamg.databinding.FragmentAddClientBinding
import com.weslleyqi0.guiamg.databinding.FragmentDashboardBinding

class AddClientFragment : Fragment() {

    private var _binding: FragmentAddClientBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AddClientViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(AddClientViewModel::class.java)

        _binding = FragmentAddClientBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}