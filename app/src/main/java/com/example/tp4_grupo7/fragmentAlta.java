package com.example.tp4_grupo7;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class fragmentAlta extends Fragment {
    View view;
    TextView txtTitle;
    String title;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_alta,container,false);
        txtTitle = (TextView)view.findViewById(R.id.txtTitle);

        if(getArguments() != null){
            title = getArguments().getString("title");
        }
        txtTitle.setText(title);

        return view;
    }
}
