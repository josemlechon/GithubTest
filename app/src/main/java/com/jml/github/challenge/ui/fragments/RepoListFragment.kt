package com.jml.github.challenge.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.google.android.material.snackbar.Snackbar
import com.jml.github.challenge.R
import com.jml.github.challenge.databinding.FragmentListReposBinding
import com.jml.github.challenge.ui.extensions.gone
import com.jml.github.challenge.ui.extensions.isTablet
import com.jml.github.challenge.ui.extensions.moveScroll
import com.jml.github.challenge.ui.extensions.observe
import com.jml.github.challenge.ui.extensions.visible
import com.jml.github.challenge.ui.fragments.adapter.RepoAdapter
import com.jml.github.challenge.ui.fragments.utils.viewBinding
import com.jml.github.challenge.ui.models.RepositoryUI
import com.jml.github.challenge.ui.vm.ReposViewModel
import com.jml.github.challenge.ui.vm.state.ReposState
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoListFragment : Fragment(R.layout.fragment_list_repos) {

    private val binding by viewBinding(FragmentListReposBinding::bind)
    private val viewModel by viewModel<ReposViewModel>()

    private var repoAdapter: RepoAdapter = RepoAdapter { id, viewShared ->
        openDetail(id, viewShared)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observe()

        binding.retryButton.setOnClickListener {
            viewModel.requestData()
            binding.groupError.gone()
        }
    }

    private fun observe() {
        viewModel.viewState
            .observe(this) { viewState ->

                when (viewState) {
                    ReposState.None -> {}
                    is ReposState.Data -> setData(viewState.listItems)
                    ReposState.Error -> showError()
                    ReposState.Loading -> showProgress()
                }
            }
    }

    private fun setData(items: List<RepositoryUI>) {

        val currentEmpty = repoAdapter.isEmpty()
        repoAdapter.submitList(items)
        if (currentEmpty.not()) {
            binding.recycler.moveScroll()
        }
        showProgress(false)
    }

    private fun showProgress(show: Boolean = true) {
        if (show) binding.progress.visible()
        else binding.progress.gone()
    }

    private fun showError() {
        showProgress(false)

        if (repoAdapter.isEmpty()) {
            binding.groupError.visible()
        } else {
            Snackbar.make(requireView(), getString(R.string.error_generic), Snackbar.LENGTH_LONG)
                .setAction(R.string.error_retry) {
                    viewModel.requestData()
                }.show()
        }
    }

    private fun setupRecyclerView() {

        with(binding.recycler) {
            adapter = repoAdapter
            layoutManager = if (context.isTablet()) GridLayoutManager(context, 2)
            else LinearLayoutManager(context)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!binding.recycler.canScrollVertically(1)) {
                        viewModel.requestData()
                    }
                }
            })
        }
    }

    private fun openDetail(id: Long, sharedView: View) {
        parentFragmentManager.commit {
            addSharedElement(sharedView, sharedView.transitionName)
            replace<DetailFragment>(
                containerViewId = R.id.fragment_container_view,
                args = bundleOf(DetailFragment.KEY_ID_REPO to id)
            )
            addToBackStack("detail $id")
        }
    }
}