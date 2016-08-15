package ljc.mypracticework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import ljc.mypracticework.R;
import ljc.mypracticework.model.PracticeInfo;

/**
 * Created by lijianchang on 16/8/15.
 *
 */
public class MainActivityRecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<PracticeInfo> mList;

    private Context mContext;

    private boolean  mNoMore;

    public static int TYPE_NORMAL = 0;

    public static int TYPE_FOOTER = 1;

    public MainActivityRecyclerViewAdapter(Context mContext,List<PracticeInfo> mList){
        super();
        this.mContext = mContext;
        this.mList    = mList;
    }

    public void setmList(List<PracticeInfo> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void setmNoMore(Boolean mNoMore) {
        this.mNoMore = mNoMore;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView ;
        if(viewType == TYPE_FOOTER){
            itemView = View.inflate(mContext,R.layout.item_recyclerviewfooter,null);
            return  new FooterViewHolder(itemView);

        }else {
            itemView = View.inflate(mContext,R.layout.item_activitymain_recyclerview,null);
            return new NormalViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof NormalViewHolder){
            PracticeInfo mInfo = mList.get(position);
            ((NormalViewHolder) holder).mIvDes.setImageResource(mInfo.mDrawableId);
            ((NormalViewHolder) holder).mTvDes.setText(mInfo.mInfoDes);
            ((NormalViewHolder) holder).mTvTitle.setText(mInfo.mInfoTitle);
            ((NormalViewHolder) holder).mTvTime.setText(mInfo.mTimeTag);
        }else if(holder instanceof  FooterViewHolder){
            if(mNoMore){
                ((FooterViewHolder) holder).mProgressBar.setVisibility(View.GONE);
                ((FooterViewHolder) holder).mTvLoading.setText("---END---");
            }else{
                ((FooterViewHolder) holder).mProgressBar.setVisibility(View.VISIBLE);
                ((FooterViewHolder) holder).mTvLoading.setText("正在加载");

            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() != 0? mList.size()+1: 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position != getItemCount() -1? TYPE_NORMAL :TYPE_FOOTER;
    }

    static  class NormalViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.item_activitymain_recyclerview_iv)
        ImageView mIvDes;
        @Bind(R.id.item_activitymain_recyclerview_tv_title)
        TextView mTvTitle;
        @Bind(R.id.item_activitymain_recyclerview_tv_des)
        TextView mTvDes;
        @Bind(R.id.item_activitymain_recyclerview_tv_time)
        TextView mTvTime;

        public NormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    static class FooterViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.item_recyclerviewfooter_progressbar)
        ProgressBar  mProgressBar;
        @Bind(R.id.item_recyclerviewfooter_tv)
        TextView mTvLoading;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
