package ljc.mypracticework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ljc.mypracticework.R;
import ljc.mypracticework.model.PracticeInfo;

/**
 * Created by lijianchang on 16/8/16.
 *
 */
public class RecyclerviewSwipeFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mList;

    private Context mContext;

    public static int TYPE_NORMAL = 0;

    public static int TYPE_FOOTER = 1;

    private final LayoutInflater mLayoutInflater;

    private boolean  mNoMore;

    public RecyclerviewSwipeFragmentAdapter(Context mContext,List<String> mList){
        super();
        this.mContext = mContext;
        this.mList = mList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setmList(List<String> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void setmNoMore(boolean mNoMore) {
        this.mNoMore = mNoMore;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView ;
        if(viewType == TYPE_FOOTER){
            itemView = mLayoutInflater.inflate(R.layout.item_recyclerviewfooter,parent,false);
            return  new FooterViewHolder(itemView);

        }else {
            itemView = mLayoutInflater.inflate(R.layout.item_recyclerviewswipefragment_recyclerview,parent,false);
            return new NormalViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof NormalViewHolder){

            ((NormalViewHolder) holder).mTvDes.setText("第"+position+"个item");
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


    static class FooterViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.item_recyclerviewfooter_progressbar)
        ProgressBar mProgressBar;
        @Bind(R.id.item_recyclerviewfooter_tv)
        TextView mTvLoading;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    static class NormalViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.item_recyclerviewswipefragment_recyclerview_tv)
        TextView mTvDes;
        public NormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
