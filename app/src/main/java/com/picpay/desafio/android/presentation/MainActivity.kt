package com.picpay.desafio.android.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.presentation.user.UserListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private val adapter = UserListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        observeStates()
        viewModel.getUsers()
    }
    private fun observeStates() {
        viewModel.users.observe(this) { value ->
            value?.let{
                adapter.users = value
            }
        }

        viewModel.progressBar.observe(this) { value ->
            value.let { show ->
                binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
            }
        }

        viewModel.snackBar.observe(this) { value ->
            value.let { show ->
                if (show) {
                    Snackbar.make(
                        binding.rootLayout,
                        getString(R.string.error),
                        Snackbar.LENGTH_SHORT
                    ).show()
                    viewModel.onSnackBarShown()
                }
            }
        }
    }
}
