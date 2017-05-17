package org.jc.allmoduledemo.widget.vlayout;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.DefaultLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;

import org.jc.allmoduledemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础多布局嵌套(多用于首页)
 * https://github.com/alibaba/vlayout
 */
public class VLayoutActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    VirtualLayoutManager virtualLayoutManager;//总布局管理器
    List<LayoutHelper> helpers;//布局管理列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        virtualLayoutManager = new VirtualLayoutManager(this);
        helpers = new ArrayList<>();

        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration(){
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(10,10,10,10);
            }
        });

        //表格(子布局)
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
        gridLayoutHelper.setItemCount(5);

        //悬停(子布局) 无滑动
        ScrollFixLayoutHelper scrollFixLayoutHelper =
                new ScrollFixLayoutHelper(FixLayoutHelper.TOP_LEFT, 100, 100);

        //添加至总布局管理列表 并且设置
        helpers.add(DefaultLayoutHelper.newHelper(3));
        helpers.add(scrollFixLayoutHelper);
        helpers.add(DefaultLayoutHelper.newHelper(2));
        helpers.add(gridLayoutHelper);
        virtualLayoutManager.setLayoutHelpers(helpers);

        mRecyclerView.setLayoutManager(virtualLayoutManager);
        //设置适配器
        mRecyclerView.setAdapter(new MyAdapter(virtualLayoutManager));
    }

    //适配器
    class MyAdapter extends VirtualLayoutAdapter{

        public MyAdapter(@NonNull VirtualLayoutManager layoutManager) {
            super(layoutManager);
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ThisHolder(new TextView(VLayoutActivity.this));
        }
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            VirtualLayoutManager.LayoutParams l = new VirtualLayoutManager.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 300);// 宽 , 高
            holder.itemView.setLayoutParams(l);

            ((TextView) holder.itemView).setText(Integer.toString(position));
            if (position == 3){//悬浮正方形
                l.height = 60;
                l.width = 60;
                holder.itemView.setBackgroundColor(0xaaffff00);
            }else if (position > 3 && position <= 5){
                l.width = 200 + (position - 30) * 100;
                holder.itemView.setBackgroundColor(0xaa00ffff);
            }else if (position > 5){
                holder.itemView.setBackgroundColor(0xaa00ff00);
            }
        }
        @Override
        public int getItemCount() {
            List<LayoutHelper> helpers = getLayoutHelpers();
            int count = 0;
            for (int i = 0; helpers != null && i < helpers.size(); i++) {
                count += helpers.get(i).getItemCount();
            }
            return count;
        }
    }


    class ThisHolder extends RecyclerView.ViewHolder{
        public ThisHolder(View itemView) {
            super(itemView);
        }
    }
}
