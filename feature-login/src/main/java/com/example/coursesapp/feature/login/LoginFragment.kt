package com.example.coursesapp.feature.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.coursesapp.feature.login.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        binding.emailEditText.addTextChangedListener { text ->
            viewModel.onEmailChanged(text.toString())
        }

        binding.passwordEditText.addTextChangedListener { text ->
            viewModel.onPasswordChanged(text.toString())
        }

        binding.loginButton.setOnClickListener {
            val navController = findNavController()
            val actionId = resources.getIdentifier(
                "action_login_to_main", "id", requireContext().packageName
            )
            if (actionId != 0) {
                navController.navigate(actionId)
            }
        }

        binding.vkButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/"))
            startActivity(intent)
        }

        binding.okButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ok.ru/"))
            startActivity(intent)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    binding.loginButton.isEnabled = state.isButtonEnabled

                    if (binding.emailEditText.text.toString() != state.email) {
                        binding.emailEditText.setText(state.email)
                        binding.emailEditText.setSelection(state.email.length)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
