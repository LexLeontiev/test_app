package com.lexleontiev.tapyou.test.app.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.github.terrakok.cicerone.Router
import com.lexleontiev.tapyou.test.app.R
import com.lexleontiev.tapyou.test.app.databinding.FragmentInputBinding
import com.lexleontiev.tapyou.test.app.di.Injector


class InputFragment : BindingFragment<FragmentInputBinding>() {

    private val router: Router = Injector.inject(Router::class)

    override fun getViewBinding() = FragmentInputBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            if (binding.pointsAmount.text.isNotEmpty()) {
                try {
                    val input = binding.pointsAmount.text.toString().toInt()
                    router.navigateTo(Screens.result(input))
                } catch (ex: NumberFormatException) {
                    Toast.makeText(requireActivity(), getString(R.string.please_enter_a_number), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireActivity(), getString(R.string.please_enter_a_number), Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {

        fun newInstance(): InputFragment = InputFragment()
    }
}