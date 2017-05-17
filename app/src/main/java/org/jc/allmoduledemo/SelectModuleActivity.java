package org.jc.allmoduledemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.jc.allmoduledemo.framework.view.ButterknifeActivity;
import org.jc.allmoduledemo.widget.recyclerview.RecyclerviewActivity;
import org.jc.allmoduledemo.widget.vlayout.TmallIndexActivity;
import org.jc.allmoduledemo.widget.vlayout.VLayoutActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页,选择要测试的模块对应的界面
 */
public class SelectModuleActivity extends AppCompatActivity {

    ListView lv_activity;
    Map<String, Class> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_module);

        lv_activity = (ListView) findViewById(R.id.lv_activity);

        data = new HashMap<>();
        data.put(RecyclerviewActivity.class.getSimpleName(), RecyclerviewActivity.class);
        data.put(VLayoutActivity.class.getSimpleName(), VLayoutActivity.class);
        data.put(TmallIndexActivity.class.getSimpleName(), TmallIndexActivity.class);
        data.put(ButterknifeActivity.class.getSimpleName(), ButterknifeActivity.class);

        lv_activity.setAdapter(new ActiveAdapter(this, data));
        lv_activity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(SelectModuleActivity.this, (Class<?>) data.values().toArray()[i]));
            }
        });
    }

    private class ActiveAdapter extends BaseAdapter{
        Context context;
        Map<String, Class> data;

        public ActiveAdapter(Context context, Map<String, Class> data) {
            this.context =context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.values().toArray()[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(context);
            textView.setText((String)data.keySet().toArray()[i]);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setHeight(80);
            return textView;
        }
    }
}
