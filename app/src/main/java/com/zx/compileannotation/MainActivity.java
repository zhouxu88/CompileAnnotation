package com.zx.compileannotation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zx.annotation.BindView;
import com.zx.compileannotation.rv.MyAdapter;
import com.zx.inject_api.ButterKnife;

import java.util.ArrayList;
import java.util.List;

/**
 * 编译期注解
 * 仿ButterKnife
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv1)
    TextView textView;
    @BindView(R.id.tv2)
    TextView textView2;

    @BindView(R.id.rv)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        textView2.setText("this is Activity  compile annotation");

        addFragment();
        initRv();
    }

    /**
     * 添加一个fragment
     */
    private void addFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new TestFragment())
                .commit();
    }


    /**
     * 初始化rv
     */
    private void initRv() {
        List<String> mList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mList.add("Title" + i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter adapter = new MyAdapter(this, mList);
        recyclerView.setAdapter(adapter);
    }
}
