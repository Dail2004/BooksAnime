package com.example.home1.ui.fragment.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.home1.adapter.BooksAdapter;
import com.example.home1.databinding.FragmentDetailBinding;
import com.example.home1.model.Book;
import com.example.home1.ui.fragment.BooksViewModel;

import java.io.Serializable;

public class DetailFragment extends Fragment {
    FragmentDetailBinding binding;
    ViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAlysetion();
        initTitle();
    }

    public void initAlysetion() {
        viewModel = new ViewModelProvider(requireActivity()).get(BooksViewModel.class);
    }

    private void initTitle() {
        getParentFragmentManager().setFragmentResultListener("key2", getViewLifecycleOwner(), (requestKey, result) -> {
            Book model = (Book) result.getSerializable("key");
            binding.image.setImageResource(model.getImage());
            binding.itemTitle.setText(model.getTitle());
            binding.description.setText(model.getDescription());
        });
    }
}