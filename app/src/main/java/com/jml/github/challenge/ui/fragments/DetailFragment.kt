package com.jml.github.challenge.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.jml.github.challenge.R
import com.jml.github.challenge.databinding.FragmentDetailBinding
import com.jml.github.challenge.ui.extensions.gone
import com.jml.github.challenge.ui.extensions.observe
import com.jml.github.challenge.ui.extensions.visible
import com.jml.github.challenge.ui.fragments.utils.viewBinding
import com.jml.github.challenge.ui.models.RepoDetailUI
import com.jml.github.challenge.ui.vm.DetailViewModel
import com.jml.github.challenge.ui.vm.state.DetailState
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment(R.layout.fragment_detail) {

    companion object {
        const val KEY_ID_REPO = "key_id_repo"
    }

    private val binding by viewBinding(FragmentDetailBinding::bind)

    private val viewModel: DetailViewModel by viewModel()

    private fun getIdRepo() : Long? =  arguments?.getLong(KEY_ID_REPO)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTransitions()
        (activity as? AppCompatActivity)?.apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = ""
        }

        requestData()

        binding.retryButton.setOnClickListener {
            requestData()
        }
        observe()
    }

    private fun setTransitions() {
        ViewCompat.setTransitionName(
            binding.titleTextView,getIdRepo()?.toString()
        )

        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)

    }

    private fun requestData() {
        viewModel.getRepoDetails(getIdRepo())
    }

    private fun observe() {
        viewModel.viewState
            .observe(this) { viewState ->
                when (viewState) {
                    is DetailState.Data -> showData(viewState.item)
                    is DetailState.Error -> showError()
                    DetailState.Loading -> showProgress()
                }
            }
    }

    private fun showData(data: RepoDetailUI) {
        binding.titleTextView.text = data.name
        binding.descriptionTextView.text = data.description

        binding.starsTextView.text = data.stars
        binding.forksTextView.text = data.forks

        binding.languageTextView.text = data.language

        with(binding.openWebViewButton) {
            tag = data.urlWebRepo
            setOnClickListener {
                val url = it.tag as String
                WebViewBottomDialogFragment(url).show(childFragmentManager, "webViewDialog");
            }
            visible()
        }
        binding.rootContent.visible()
        showProgress(false)
    }

    private fun showError() {
        binding.groupError.visible()
        binding.rootContent.gone()
        showProgress(false)
    }

    private fun showProgress(show: Boolean = true) {
        if (show) binding.progress.visible()
        else binding.progress.gone()
    }
}