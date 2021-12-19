package com.example.inmanage1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondFragment extends Fragment {

    TextView name;
    View view;
    public SecondFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_second, container, false);
        name=view.findViewById(R.id.second_name);
        String mName=getArguments().getString("name");
        name.setText(mName);
        return view;
    }
}