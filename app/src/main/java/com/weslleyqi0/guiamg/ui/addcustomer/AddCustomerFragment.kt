package com.weslleyqi0.guiamg.ui.addcustomer

import android.net.Uri
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsMultiChoice
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.google.android.material.textfield.TextInputLayout
import com.weslleyqi0.guiamg.R
import com.weslleyqi0.guiamg.databinding.FragmentAddCustomerBinding
import com.weslleyqi0.guiamg.ui.dashboard.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCustomerFragment : Fragment() {

    private var _binding: FragmentAddCustomerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddCustomerViewModel by viewModels()

    private var imageCustomerUri: Uri? = null

    private var categories = listOf("")

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

            edtAddCustomerCategory.setOnClickListener {
                chooseCategories()
            }

            buttonSaveCustomer.setOnClickListener {
                val name = binding.edtAddCustomerName.text.toString()
                val description = binding.edtAddCustomerDescription.text.toString()
                val address = binding.edtAddCustomerAddress.text.toString()
                val phone = binding.edtAddCustomerPhone.text.toString()
                //val category = binding.edtAddCustomerCategory.text.toString()
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
                    categories
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

    private fun chooseCategories(){
        val myItems = listOf("Artesanato", "Padaria", "Pizzaria", "Sorveteria", "Lamchonete", "Pintura")

        MaterialDialog(requireContext()).show {
            //title(text = "Categorias")
            title(text = getString(R.string.title_choose_three_categories))
            listItemsMultiChoice(items = myItems){ _, index, text ->
                categories = text.map { it.toString() }
                binding.edtAddCustomerCategory.text =
                    Editable.Factory.getInstance().newEditable(text.toString())
            }

            positiveButton(R.string.select)
        }
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