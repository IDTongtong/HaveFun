package com.lanou.tong.fun.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lanou.tong.fun.R;
import com.lanou.tong.fun.adapter.HistoryAdapter;

/**
 * Created by zt on 16/3/24.
 */
public class HistoryFragment extends Fragment {
    private ListView listView;
    private HistoryAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) getView().findViewById(R.id.history_fragment_listView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new HistoryAdapter(getContext());
        listView.setAdapter(adapter);
    }
}
