package ljc.mypracticework;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ljc.mypracticework.adapter.MainActivityRecyclerViewAdapter;
import ljc.mypracticework.model.PracticeInfo;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.activity_main_recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.activity_main_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeLayout;
    private List<PracticeInfo> mList;
    private int mDrawableId = R.mipmap.ic_launcher;
    MainActivityRecyclerViewAdapter mAdapter;
    private int mPage = 1;
    private LinearLayoutManager mLayoutManager;
    private boolean isLoading;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mList = new ArrayList<>();
//        getActionBar().setTitle("实践列表");
        mHandler = new Handler();
        mSwipeLayout.setRefreshing(true);
        mSwipeLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPage = 1;
                        getData();
                    }
                }, 2000);

            }
        });
        mList = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MainActivityRecyclerViewAdapter(this,mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(mSwipeLayout.isRefreshing()){
                    return true;
                }
                return false;
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
                if(lastVisibleItemPosition == mAdapter.getItemCount() -1){
                    if(mPage >= 5){
                        mAdapter.setmNoMore(true);
                        return;
                    }else{
                        mAdapter.setmNoMore(false);
                    }
                    if(mSwipeLayout.isRefreshing()){
                        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
                        return;
                    }
                    if(!isLoading){
                        isLoading = true;
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mPage++;
                                getData();
                                isLoading = false;
                            }
                        },2000);
                    }
                }


            }
        });
        getData();
    }

    private void getData() {
        if(mPage == 1){
            /**
             * 添加已知的实践
             */
            addPracticedPracticeInfo();
            /**
             * 添加未知的实践
             */
            for(int i = 0;i < 10;i++){
                String mTitle = "未知标题";
                String mDes = "未知描述";
                String mTimeTag = "01/01";
                PracticeInfo mPracticeInfo = new PracticeInfo(mTitle,mDes,mDrawableId,mTimeTag);
                mList.add(mPracticeInfo);
            }
        }else{
            for(int i = 0;i < 10;i++){
                String mTitle = "未知标题";
                String mDes = "未知描述";
                String mTimeTag = "01/01";
                PracticeInfo mPracticeInfo = new PracticeInfo(mTitle,mDes,mDrawableId,mTimeTag);
                mList.add(mPracticeInfo);
            }
        }
    mAdapter.setmList(mList);
    mSwipeLayout.setRefreshing(false);
    }

    private void addPracticedPracticeInfo() {
        /**
         *第一个Demo
         * title：RecyclerView的加载与刷新
         * DES：这是一个结合Recyclerview 与SwipeRefreshLayout一起的刷新系统，实现的功能有加载，刷新，没有更多的提示。
         */
        String mPracticeInfo_15_AUG_title = "RecyclerView的加载与刷新";
        String mPracticeInfo_15_AUG_DES = "这是一个结合Recyclerview 与SwipeRefreshLayout一起的刷新系统，实现的功能有加载，刷新，没有更多的提示的功能。";
        String mTimeTag = "08/15";
        PracticeInfo mPracticeInfo_15_AUG = new PracticeInfo(mPracticeInfo_15_AUG_title,mPracticeInfo_15_AUG_DES, R.mipmap.ic_launcher,mTimeTag);
        mList.add(mPracticeInfo_15_AUG);
        /**
         * 第二个Demo
         * title:流式布局下的标签的添加和删除
         * des:增加对自定义Viewgroup的认识，更深一步的了解ViewGroup中onmeasure和onlayout函数。
         */
        String mPracticeInfo_21_AUG_title = "流式布局下的标签的添加和删除";
        String mPracticeInfo_21_AUG_des   ="增加对自定义Viewgroup的认识，更深一步的了解ViewGroup中onmeasure和onlayout函数";
        String mPracticeInfo_21_AUG_mTimeTag = "08/21";
        PracticeInfo mPracticeInfo_21_AUG = new PracticeInfo(mPracticeInfo_21_AUG_title,mPracticeInfo_21_AUG_des, R.mipmap.ic_launcher,mPracticeInfo_21_AUG_mTimeTag);
        mList.add(mPracticeInfo_21_AUG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
