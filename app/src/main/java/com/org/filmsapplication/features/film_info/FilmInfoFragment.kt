package com.org.filmsapplication.features.film_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.org.filmsapplication.core.ui.view_model.film_catalog_view_model.FilmsCatalogViewModel
import com.org.filmsapplication.features.film_info.compose.FilmInfoScreen
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class FilmInfoFragment : Fragment() {

    private val viewModel: FilmsCatalogViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                FilmInfoScreen(
                    viewModel = viewModel,
                    onBackClick = {
                        findNavController().popBackStack()
                    }
                )
            }
        }
    }
}