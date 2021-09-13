package com.example.home1.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.home1.R;
import com.example.home1.adapter.BooksAdapter;
import com.example.home1.databinding.FragmentBooksBinding;
import com.example.home1.model.Book;
import com.example.home1.utils.OnItemClick;

import java.util.ArrayList;

public class BooksFragment extends Fragment {

    FragmentBooksBinding binding;
    BooksAdapter adapter = new BooksAdapter();
    BooksViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBooksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAlysetion();
        initRV();
        onClick();
        setUpObserve();
        detail();
    }


    private void setUpObserve() {
        viewModel.data.observe(getViewLifecycleOwner(), v -> {
            binding.rV.setVisibility(View.VISIBLE);
            adapter.setBooks(v);
            binding.button.setVisibility(View.GONE);
            Log.e("anime", "onChanged: "  );

        });
    }

    private void onClick() {
        binding.button.setOnClickListener(view -> {
            viewModel.getBooks();
            binding.rV.setVisibility(View.VISIBLE);
            binding.button.setVisibility(View.GONE);
        });
    }

    public void initAlysetion() {
        viewModel = new ViewModelProvider(requireActivity()).get(BooksViewModel.class);
    }

    private void initRV() {
        set();
    }

    private void set() {
        binding.rV.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rV.setAdapter(adapter);
    }


    private void detail() {
        adapter.setOnItemClick(position -> {
            Bundle bundle = new Bundle();
            Book model = new Book(
                    adapter.list.get(position).getImage(),
                    adapter.list.get(position).getTitle(),
                    adapter.list.get(position).getDescription()
            );
            bundle.putSerializable("key", model);
            getParentFragmentManager().setFragmentResult("key2", bundle);
            close();
        });
    }

    private void close(){
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_booksFragment_to_detailFragment);
    }
}