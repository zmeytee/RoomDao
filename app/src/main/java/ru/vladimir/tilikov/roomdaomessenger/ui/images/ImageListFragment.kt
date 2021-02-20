package ru.vladimir.tilikov.roomdaomessenger.ui.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import ru.vladimir.tilikov.roomdaomessenger.adapters.ImageAdapter
import ru.vladimir.tilikov.roomdaomessenger.databinding.FragmentImageListBinding
import ru.vladimir.tilikov.roomdaomessenger.ui.main.BaseFragment

class ImageListFragment: BaseFragment() {

    private val viewModel by viewModels<ImageListFragmentViewModel>()
    private var _binding: FragmentImageListBinding? = null
    private val binding: FragmentImageListBinding get() = _binding!!
    private val args by navArgs<ImageListFragmentArgs>()
    private var imageAdapter: ImageAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentImageListBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentImageListBinding.bind(view)

        initList()
        bindViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        imageAdapter = null
    }

    private fun initList() {
        imageAdapter = ImageAdapter {
            viewModel.addImageMessage(args.contact.id, it.uri)
        }

        with(binding.rvImages) {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
        }

        viewModel.getAllImages()
    }

    private fun bindViewModel() {
        viewModel.images.observe(viewLifecycleOwner) { imageAdapter?.submitList(it) }
        viewModel.addMessageComplete.observe(viewLifecycleOwner) { findNavController().popBackStack() }
    }
}