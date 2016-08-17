package ljc.mypracticework.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ljc.mypracticework.R;
import ljc.mypracticework.adapter.MainActivityRecyclerViewAdapter;
import ljc.mypracticework.adapter.RecyclerviewSwipeFragmentAdapter;

/**
 * Created by lijianchang on 16/8/16.
 *
 */
public class RecyclerviewSwipeFragment extends Fragment {

    @Bind(R.id.activity_main_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeLayout;
    @Bind(R.id.activity_main_recyclerview)
    RecyclerView mRecyclerView;
    private List<String> mList;
    private RecyclerviewSwipeFragmentAdapter mAdapter;
    private int mPage = 1;
    private LinearLayoutManager mLayoutManager;
    private boolean isLoading;
    private Handler mHandler;
    public static RecyclerviewSwipeFragment newInstance(){

        return new RecyclerviewSwipeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList = new ArrayList<>();
        mAdapter = new RecyclerviewSwipeFragmentAdapter(getActivity(),mList);
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
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(mSwipeLayout.isRefreshing()){
                    return  true;
                }
                return false;
            }
        });
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

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
        for(int i = 0;i<10;i++){
            mList.add("");
        }
        mAdapter.setmList(mList);
        mSwipeLayout.setRefreshing(false);
    }
}
