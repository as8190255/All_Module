package org.jc.allmoduledemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.jc.allmoduledemo.widget.recyclerview.RecyclerviewActivity;
import org.jc.allmoduledemo.widget.vlayout.VLayoutActivity;

/**
 * 首页,选择要测试的模块对应的界面
 */
public class SelectModuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_module);

        startActivity(new Intent(this, VLayoutActivity.class));
    }
}
