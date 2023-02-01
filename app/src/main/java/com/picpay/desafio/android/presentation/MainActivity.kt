package com.picpay.desafio.android.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.presentation.user.UserListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private val usersAdapter = UserListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUsersAdapter()
        observeStates()
        viewModel.getUsers()
    }

    private fun initUsersAdapter() {
        with(binding.recyclerView) {
            adapter = usersAdapter
            setHasFixedSize(true)
        }
    }

    private fun observeStates() {
        viewModel.users.observe(this) { value ->
            value?.let {
                usersAdapter.users = value
            }
        }

        viewModel.progressBar.observe(this) { value ->
            value.let { show ->
                binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
            }
        }

        viewModel.error.observe(this) { value ->
            value.let { show ->
                if (show) {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.error),
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.onErrorShown()
                }
            }
        }
    }
}
