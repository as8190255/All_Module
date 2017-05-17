package org.jc.allmoduledemo.widget.vlayout;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.RecyclablePagerAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;

import org.jc.allmoduledemo.R;

import java.util.Date;
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


    String [] ims = new String[]{
            "http://www.005.tv/uploads/allimg/160712/22-160G2102T3c7.jpg",
            "http://www.005.tv/uploads/allimg/160830/22-160S0111250139.jpg",
            "https://gss0.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/b151f8198618367a40893abc2d738bd4b31ce54b.jpg",
            "https://gss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/d000baa1cd11728b40cb0096cefcc3cec2fd2c9b.jpg"};


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
            adapters.add(new BannerAdapter(this, viewPool, ims));
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
        Handler mHandler = new Handler();
        Runnable runnable;
        String [] ims;

        public BannerAdapter(Context mContext, RecyclerView.RecycledViewPool viewPool, String [] ims) {
            this.viewPool = viewPool;
            this.mContext = mContext;
            this.mLayoutParams = new VirtualLayoutManager.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 300);
            this.mLayoutHelper = new LinearLayoutHelper();
            this.ims = ims;
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
                        R.layout.item_banner_item, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(final BannerViewHolder holder, int position) {
            holder.itemView.setLayoutParams(mLayoutParams);
            holder.mViewpager.setAdapter(new ThisPagerAdapter(mContext, this, viewPool, ims));
            holder.mViewpager.addOnPageChangeListener(new BannerPageChangeListener(holder.mViewpager, ims));
            holder.mViewpager.setCurrentItem(1, false);


            runnable = new Runnable() {
                @Override
                public void run() {
                    int num = holder.mViewpager.getCurrentItem();
                        num++;
                        holder.mViewpager.setCurrentItem(num, true);
                        Log.i("anan", "viewpager:"+ num);
                    mHandler.postDelayed(runnable, 5000);
                }
            };
//            mHandler.postDelayed(runnable, 5000);

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

    //Viewpager无限循环监听
    static class BannerPageChangeListener implements ViewPager.OnPageChangeListener {
        ViewPager mViewpager;
        String [] ims;
        int mCount;//实际轮播图数量
        public BannerPageChangeListener(ViewPager mViewpager, String [] ims) {
            this.mViewpager = mViewpager;
            this.ims = ims;
            this.mCount = ims.length;
        }
        @Override
        public void onPageSelected(int position) {
        }
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            if (positionOffset == 0){
//                if (position == 0){
//                    mViewpager.setCurrentItem(mCount, false);
//                }else if (position == (mCount + 1)){
//                    mViewpager.setCurrentItem(1, false);
//                }
//            }

        }
        @Override
        public void onPageScrollStateChanged(int state) {//0:结束滑动, 1:开始滑动, 2:松手

//该种方式可能会出现问题（占用过多主线程时间）
//            if (state != 0) return;//当state为0时说明已经结束滑动
//            long l=new Date().getTime();
//            Log.i("anan", mViewpager.getCurrentItem() + " ...0");
//            if (mViewpager.getCurrentItem() == 0){//到头了
//                Log.i("anan", mViewpager.getCurrentItem() + "...1");
//                mViewpager.setCurrentItem(mCount, false);
//                Log.i("anan2",""+(new Date().getTime() - l));
//            }else if (mViewpager.getCurrentItem() == (mCount + 1)){//到尾了
//                Log.i("anan", mViewpager.getCurrentItem() + "...2");
//                mViewpager.setCurrentItem(1, false);
//                Log.i("anan2",""+(new Date().getTime() - l));
//            }
        }
    }

    //Viewpager适配器
    static class ThisPagerAdapter extends RecyclablePagerAdapter<BannerViewHolder>{
        String [] ims;
        Context mContext;
        public ThisPagerAdapter(Context mContext, RecyclerView.Adapter<BannerViewHolder> adapter,
                                RecyclerView.RecycledViewPool pool, String [] ims) {
            super(adapter, pool);
            this.mContext = mContext;
            this.ims = ims;
        }

        @Override
        public int getCount() {
            return ims.length + 2;
        }

        @Override
        public void onBindViewHolder(BannerViewHolder viewHolder, int position) {
            viewHolder.itemView.setLayoutParams(new VirtualLayoutManager.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 300));
            if (position == 0){
                Glide.with(mContext).load(ims[ims.length - 1]).into(viewHolder.im_banner_item);
            }else if (position == (ims.length + 1)){
                Glide.with(mContext).load(ims[0]).into(viewHolder.im_banner_item);
            }else {
                Glide.with(mContext).load(ims[position - 1]).into(viewHolder.im_banner_item);
            }
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
//            Log.i("anan","onCreateViewHolder");
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
        ImageView im_banner_item;
        public BannerViewHolder(View itemView) {
            super(itemView);
            mViewpager = (ViewPager) itemView.findViewById(R.id.mViewpager);
            im_banner_item = (ImageView) itemView.findViewById(R.id.im_banner_item);
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
