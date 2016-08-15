package ljc.mypracticework;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ljc.mypracticework.model.PracticeInfo;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.activity_main_recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.activity_main_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeLayout;
    private List<PracticeInfo> mList;
    private int mDrawableId = R.mipmap.ic_launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mList = new ArrayList<>();
        mSwipeLayout.setRefreshing(true);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }

    private void getData() {
        /**
         * 添加已知的知识点
         */
        addPracticedPracticeInfo();
        /**
         * 添加未知的知识点
         */
        for(int i = 0;i < 10;i++){
            String mTitle = "未知标题";
            String mDes = "未知描述";
            String mTimeTag = "01/01";
            PracticeInfo mPracticeInfo = new PracticeInfo(mTitle,mDes,mDrawableId,mTimeTag);
            mList.add(mPracticeInfo);
        }

    }

    private void addPracticedPracticeInfo() {
        /**
         *第一个Demo
         * title：RecyclerView的加载与刷新
         * DES：这是一个结合Recyclerview 与SwipeRefreshLayout一起的刷新系统，实现的功能有加载，刷新，没有更多的提示。
         */
        String mPracticeInfo_15_AUG_title = "RecyclerView的加载与刷新";
        String mPracticeInfo_15_AUG_DES = "这是一个结合Recyclerview 与SwipeRefreshLayout一起的刷新系统，实现的功能有加载，刷新，没有更多的提示";
        String mTimeTag = "08/15";
        PracticeInfo mPracticeInfo_15_AUG = new PracticeInfo(mPracticeInfo_15_AUG_title,mPracticeInfo_15_AUG_DES, R.mipmap.ic_launcher,mTimeTag);
        mList.add(mPracticeInfo_15_AUG);
    }
}
