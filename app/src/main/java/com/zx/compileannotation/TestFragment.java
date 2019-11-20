package com.zx.compileannotation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zx.annotation.BindView;
import com.zx.inject_api.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {

    @BindView(R.id.fm_tv)
    TextView textView;


    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_test, container, false);
        ButterKnife.bind(this,inflate);
        textView.setText("This is fragment annotation");
        return inflate;
    }

}
