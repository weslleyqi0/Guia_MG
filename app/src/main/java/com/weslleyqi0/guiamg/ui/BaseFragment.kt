package com.weslleyqi0.guiamg.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment <viewBinding : ViewBinding> : Fragment(){

    private var _binding: viewBinding? = null

    protected val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUi()
        setHasOptionsMenu(showActionBarOptionMenu())
    }

    protected open fun showActionBarOptionMenu(): Boolean = false

    protected abstract fun getViewBinding(): viewBinding

    /** fragment method to initialize UI components. */
    protected abstract fun initializeUi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return _binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /**
     * Allow custom back pressed actions in fragments,
     * which get executed before the default behavior
     * of the activity´s onBackPressed
     */
    protected fun setOnBackPressedCallback(backPressAction: () -> Unit = {}) {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    backPressAction()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    //protected fun log(msg: String) = Timber.d(msg)
}