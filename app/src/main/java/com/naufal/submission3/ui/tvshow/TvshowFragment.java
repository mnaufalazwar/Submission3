package com.naufal.submission3.ui.tvshow;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.naufal.submission3.R;
import com.naufal.submission3.activity.DetailActivity;
import com.naufal.submission3.adapter.MovieAdapter;
import com.naufal.submission3.model.Movie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvshowFragment extends Fragment {

    private ProgressBar progressBar;

    public TvshowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.rv_tv_show);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final MovieAdapter adapter = new MovieAdapter();
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                Toast.makeText(getContext(), data.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_DATA, data);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);

        if(getActivity() != null){
            TvshowViewModel tvshowViewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(TvshowViewModel.class);

            tvshowViewModel.setListMovie();
            showLoading(true);

            tvshowViewModel.getListMovies().observe(getActivity(), new Observer<ArrayList<Movie>>() {
                @Override
                public void onChanged(ArrayList<Movie> movies) {
                    if(movies != null){
                        adapter.setData(movies);
                        showLoading(false);
                    }
                }
            });
        }

    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
