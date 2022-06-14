package com.weslleyqi0.guiamg.ui.addcustomer

import android.net.Uri
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.google.android.material.textfield.TextInputLayout
import com.weslleyqi0.guiamg.databinding.FragmentAddCustomerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCustomerFragment : Fragment() {

    private var _binding: FragmentAddCustomerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddCustomerViewModel by viewModels()

    private var imageCustomerUri: Uri? = null

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            imageCustomerUri = uri
            binding.imageCustomer.setImageURI(uri)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCustomerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeVMEvents()
        setListeners()
    }

    private fun observeVMEvents() {

        viewModel.imageUriErrorResId.observe(viewLifecycleOwner) { drawableResId ->
            binding.imageCustomer.setBackgroundResource(drawableResId)
        }

        viewModel.nameFieldErrorResId.observe(viewLifecycleOwner) { stringResId ->
            binding.inputLayoutAddCustomerName.setError(stringResId)
        }

        viewModel.descriptionFieldErrorResId.observe(viewLifecycleOwner) { stringResId ->
            binding.inputLayoutAddCustomerDescription.setError(stringResId)
        }

        viewModel.addressFieldErrorResId.observe(viewLifecycleOwner) { stringResId ->
            binding.inputLayoutAddCustomerAddress.setError(stringResId)
        }

        viewModel.phoneFieldErrorResId.observe(viewLifecycleOwner) { stringResId ->
            binding.inputLayoutAddCustomerPhone.setError(stringResId)
        }

        viewModel.categoryFieldErrorResId.observe(viewLifecycleOwner) { stringResId ->
            binding.inputLayoutAddCustomerCategory.setError(stringResId)
        }
    }

    private fun setListeners() {

        with(binding){
            imageCustomer.setOnClickListener {
                chooseImage()
            }

            edtAddCustomerPhone.addTextChangedListener(PhoneNumberFormattingTextWatcher())

            buttonSaveCustomer.setOnClickListener {
                val name = binding.edtAddCustomerName.text.toString()
                val description = binding.edtAddCustomerDescription.text.toString()
                val address = binding.edtAddCustomerAddress.text.toString()
                val phone = binding.edtAddCustomerPhone.text.toString()
                val category = binding.edtAddCustomerCategory.text.toString()
                val instagramLink = binding.edtAddCustomerLinkInstagram.text.toString()
                val facebookLink = binding.edtAddCustomerLinkFacebook.text.toString()
                val mapsLink = binding.edtAddCustomerLinkMaps.text.toString()

                viewModel.createCustomer(
                    imageCustomerUri,
                    name,
                    address,
                    phone,
                    instagramLink,
                    facebookLink,
                    mapsLink,
                    description,
                    category
                )
            }
        }
    }

    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            imageCustomerUri = result.uriContent
            binding.imageCustomer.setImageURI(imageCustomerUri)
        } else {
            val exception = result.error
        }
    }

    private fun chooseImage() {
        cropImage.launch(
            options {
                setGuidelines(CropImageView.Guidelines.ON)
                setAspectRatio(16, 8)
            }
        )
    }

    private fun chooseImage0() {
        getContent.launch("image/*")
    }

    private fun TextInputLayout.setError(stringResId: Int?) {
        error = if (stringResId != null) {
            getString(stringResId)
        } else null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}