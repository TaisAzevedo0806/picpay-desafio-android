package com.picpay.desafio.android.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.picpay.desafio.android.R
import com.picpay.desafio.android.presentation.user.UserListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var rootLayout: NestedScrollView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private val adapter = UserListAdapter()

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        rootLayout = findViewById(R.id.rootLayout)
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

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
                progressBar.visibility = if (show) View.VISIBLE else View.GONE
            }
        }

        viewModel.snackBar.observe(this) { value ->
            value.let { show ->
                if (show) {
                    Snackbar.make(
                        rootLayout,
                        getString(R.string.error),
                        Snackbar.LENGTH_SHORT
                    ).show()
                    viewModel.onSnackBarShown()
                }
            }
        }
    }
}
