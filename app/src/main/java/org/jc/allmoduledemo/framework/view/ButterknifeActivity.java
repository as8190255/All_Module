package org.jc.allmoduledemo.framework.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jc.allmoduledemo.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 黄油刀 view注入框架
 * https://github.com/JakeWharton/butterknife
 */
public class ButterknifeActivity extends AppCompatActivity {

    @BindView(R.id.listview)ListView listview;
    @BindView(R.id.textView)TextView tv_text;
    @BindView(R.id.button)Button btn;

    @BindString(R.string.app_name)String appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);
        ButterKnife.bind(this);
        tv_text.setText("啊飒飒");
        btn.setText("点我");
        listview.setAdapter(new DataAdapter(this));
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.button) void submit(){
        Toast.makeText(this, "颠倒了 " + appName, Toast.LENGTH_SHORT).show();
    }

    private class DataAdapter extends BaseAdapter{
        Context mContext;

        public DataAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(mContext);
            textView.setText(i + " 11");
            return textView;
        }


    }
}
