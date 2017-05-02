package org.jc.allmoduledemo.widget.vlayout;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.RecyclablePagerAdapter;
import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;

import org.jc.allmoduledemo.R;

import java.util.LinkedList;
import java.util.List;

/**
 * 模仿天猫首页布局(使用alibaba提供布局库)
 */
public class TmallIndexActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    VirtualLayoutManager layoutManager;
    DelegateAdapter delegateAdapter;//总适配器（也可以自定义适配器VirtualLayoutAdapter）
    List<DelegateAdapter.Adapter> adapters;//适配器列表（放置不同布局）


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmall_index);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        layoutManager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        //设置缓存
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        //创建总适配器
        delegateAdapter = new DelegateAdapter(layoutManager, true);
        mRecyclerView.setAdapter(delegateAdapter);
        adapters = new LinkedList<>();

        //开始创建各个布局↓

        //创建banner布局
        if (true){
            adapters.add(new BannerAdapter(this, viewPool));
        }
        //创建GridLayout 分类布局
        if (true){
            GridLayoutHelper layoutHelper = new GridLayoutHelper(4);
//            layoutHelper.setMargin(0, 0, 0, 0);//margin值
            layoutHelper.setHGap(3);//间隙
            layoutHelper.setAspectRatio(4f);
            adapters.add(new SubAdapter(this, layoutHelper, 8));
        }


        //最终设置
        delegateAdapter.setAdapters(adapters);
    }
    //Banner顶部数据
    static class BannerAdapter extends DelegateAdapter.Adapter<BannerViewHolder>{
        private Context mContext;
        private VirtualLayoutManager.LayoutParams mLayoutParams;
        private LayoutHelper mLayoutHelper;
        RecyclerView.RecycledViewPool viewPool;
        public BannerAdapter(Context mContext, RecyclerView.RecycledViewPool viewPool) {
            this.viewPool = viewPool;
            this.mContext = mContext;
            this.mLayoutParams = new VirtualLayoutManager.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 300);
            this.mLayoutHelper = new LinearLayoutHelper();
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return mLayoutHelper;
        }

        @Override
        public BannerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 1){
                return new BannerViewHolder(LayoutInflater.from(mContext).inflate(
                        R.layout.item_banner, parent, false));
            }else {
                return new BannerViewHolder(LayoutInflater.from(mContext).inflate(
                        R.layout.item_recyclerview, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(BannerViewHolder holder, int position) {
            holder.itemView.setLayoutParams(mLayoutParams);
            holder.mViewpager.setAdapter(new ThisPagerAdapter(this, viewPool));
        }

        @Override
        public int getItemViewType(int position) {
            return 1;
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }
    //Viewpager适配器
    static class ThisPagerAdapter extends RecyclablePagerAdapter<BannerViewHolder>{
        public ThisPagerAdapter(RecyclerView.Adapter<BannerViewHolder> adapter, RecyclerView.RecycledViewPool pool) {
            super(adapter, pool);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public void onBindViewHolder(BannerViewHolder viewHolder, int position) {
            viewHolder.itemView.setLayoutParams(new VirtualLayoutManager.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 300));
            viewHolder.tv_title.setText("1231");
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }
    }

    //表格 4*2 适配器
    static class SubAdapter extends DelegateAdapter.Adapter<ThisViewHolder>{
        private Context mContext;
        private LayoutHelper mLayoutHelper;
        private int mCount = 0;
        private VirtualLayoutManager.LayoutParams mLayoutParams;


        public SubAdapter(Context mContext, LayoutHelper mLayoutHelper, int mCount){
            this(mContext, mLayoutHelper, mCount,
                    new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        }
        public SubAdapter(Context mContext, LayoutHelper mLayoutHelper, int mCount,
                          VirtualLayoutManager.LayoutParams mLayoutParams) {
            this.mContext = mContext;
            this.mLayoutHelper = mLayoutHelper;
            this.mCount = mCount;
            this.mLayoutParams = mLayoutParams;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return mLayoutHelper;
        }

        @Override
        public ThisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ThisViewHolder(LayoutInflater.from(mContext).inflate(
                    R.layout.item_grid, parent, false));
        }

        @Override
        public void onBindViewHolder(ThisViewHolder holder, int position) {
            holder.itemView.setLayoutParams(new VirtualLayoutManager.LayoutParams(mLayoutParams));
            holder.tv_logo_name.setText("1");
            Glide.with(mContext).load("http://img.qq1234.org/uploads/allimg/161216/1639334126-5.jpg").into(holder.im_logo);

        }

        @Override
        public int getItemCount() {
            return mCount;
        }
    }

    //设置边框（非重要）
    RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration(){
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(4,4,4,4);
        }
    };

    static class BannerViewHolder extends RecyclerView.ViewHolder{
        ViewPager mViewpager;
        TextView tv_title;
        public BannerViewHolder(View itemView) {
            super(itemView);
            mViewpager = (ViewPager) itemView.findViewById(R.id.mViewpager);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    static class ThisViewHolder extends RecyclerView.ViewHolder{
        TextView tv_logo_name;
        ImageView im_logo;

        public ThisViewHolder(View itemView) {
            super(itemView);
            tv_logo_name = (TextView) itemView.findViewById(R.id.tv_logo_name);
            im_logo = (ImageView) itemView.findViewById(R.id.im_logo);
        }
    }
}
