package org.jc.allmoduledemo.widget.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.jc.allmoduledemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * RecyclerView 基本用法
 */
public class RecyclerviewActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ItemTouchHelper mItemTouchHelper;

    ThisAdaper mAdaper;

    List<String> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//线性布局
//        mDatas = Arrays.asList("1","2","3","123","123","123","123","123","123","123","123","123","123","121113","123","123","123","123","123","123","123","123","123","123");

//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));//瀑布流布局（需要设置动态的高度宽度后就会）
//        mDatas = Arrays.asList("1","2","3","123","111111111111123","123","111123","123","123","121111111113","123","123","123","121113","123","123","123","123","123","123","123","123","123","123");

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));//网格布局
        initData();
        //表格布局拖拽功能(其他布局也适用)
        mItemTouchHelper = new ItemTouchHelper(new ItemTouchCallback());
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        mAdaper = new ThisAdaper();
        mRecyclerView.setAdapter(mAdaper);
    }

    /** 拖拽,侧滑助手*/
    class ItemTouchCallback extends ItemTouchHelper.Callback{


        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            //可以在此过滤部分 不需要支持拖拽/滑动功能的item 返回0,0即可
//            if (viewHolder.getAdapterPosition() == 1)return makeMovementFlags(0, 0);

            int dragFlag,swipeFlags;
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager){
                dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT |
                        ItemTouchHelper.RIGHT;
                swipeFlags = ItemTouchHelper.LEFT;
            }else {
                dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                swipeFlags = 0;//不处理滑动操作
            }
            //当dragFlag不等于0的时候,长按item就会进入拖拽状态,在该状态下回不断回调onMove,就会得到
            //当前item和拖拽到的位置的item,最后交换位置使用Adapter.notifyItemMoved刷新
            return makeMovementFlags(dragFlag, swipeFlags);//（拖动标志,滑动标志）
        }
        //拖拽
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //获取发生变化的item
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(mDatas, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(mDatas, i, i - 1);
                }
            }
            mAdaper.notifyItemMoved(fromPosition, toPosition);
            return true;
        }
        //滑动
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            mDatas.remove(viewHolder.getAdapterPosition());
            mAdaper.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    }


    /** 适配器*/
    class ThisAdaper extends RecyclerView.Adapter<ThisHolder>{

        @Override
        public ThisHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //创建viewHolder对象
            ThisHolder holder = new ThisHolder(LayoutInflater.from(RecyclerviewActivity.this)
                    .inflate(R.layout.item_recyclerview, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final ThisHolder holder, final int position) {
            //设置数据
            holder.tv_title.setText(mDatas.get(position));
            //点击事件用 不建议使用position,对于有拖拽,侧滑删除这些功能后.可以使用holder.getAdapterPosition()
            holder.tv_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(RecyclerviewActivity.this, mDatas.get(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }


    /** ViewHolder*/
    class ThisHolder extends RecyclerView.ViewHolder{
        TextView tv_title;
        public ThisHolder(View itemView) {
            super(itemView);
            //只在创建的时候 执行一遍
            //实例化布局中的组件
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    public void initData(){
        mDatas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mDatas.add(""+i);
        }
    }
}
