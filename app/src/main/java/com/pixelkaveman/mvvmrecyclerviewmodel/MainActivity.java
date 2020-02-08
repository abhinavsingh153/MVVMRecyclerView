package com.pixelkaveman.mvvmrecyclerviewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pixelkaveman.mvvmrecyclerviewmodel.adapters.RecyclerAdapter;
import com.pixelkaveman.mvvmrecyclerviewmodel.models.NicePlace;
import com.pixelkaveman.mvvmrecyclerviewmodel.viewmodels.NicePlaceViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar mProgressBar;
    private NicePlaceViewModel mNicePlaceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.fab);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mProgressBar = findViewById(R.id.progress_Bar);

        mNicePlaceViewModel = ViewModelProviders.of(this).get(NicePlaceViewModel.class);

        //retrieve the data from the repository
        mNicePlaceViewModel.init();


        //observe changes done to our viewModel i.e NicePlaceViewModel

        mNicePlaceViewModel.getNicePlaces().observe(this, new Observer<List<NicePlace>>() {
            @Override
            public void onChanged(List<NicePlace> nicePlaces) {
                // if the data is changed
                // send the notifiation to the adapter
                mAdapter.notifyDataSetChanged();
            }
        });

       // List<NicePlace> nicePlaces = new ArrayList<>();

        initRecyclerView();
    }

    private void initRecyclerView(){

        // specify an adapter (see also next example)
        mAdapter = new RecyclerAdapter(this, mNicePlaceViewModel.getNicePlaces().getValue());
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void showProgressBar(){ mProgressBar.setVisibility(View.VISIBLE);}
    private void hideProgressBar(){ mProgressBar.setVisibility(View.GONE);}
}
