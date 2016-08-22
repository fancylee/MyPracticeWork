package ljc.mypracticework.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ljc.mypracticework.R;
import ljc.mypracticework.utils.LogUtils;
import ljc.mypracticework.view.FlowLayout;

/**
 * Created by lijianchang on 16/8/21.
 *
 */
public class FlowLayoutFragment extends Fragment {

    public static FlowLayoutFragment newInstance(){
        return new FlowLayoutFragment();
    }

    @Bind(R.id.fragment_flow_container)
    FlowLayout mContainer;
    @Bind(R.id.fragment_flow_et)
    EditText mEtAdd;
    List<String> mList = new ArrayList<>();
    PopupWindow mPopupWindow;
    public static final String TAG = "FlowLayoutFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view  = inflater.inflate(R.layout.fragment_flow,container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * 侦听键盘的完成按钮的点击事件
         */
        mEtAdd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    String text = mEtAdd.getEditableText().toString().trim();
                    if (!mList.contains(text)) {
                        LogUtils.d(text);
                        mList.add(text);
                        showTags();
                        mEtAdd.setText("");
                    } else {
                        Toast.makeText(getActivity(), R.string.add_repeat, Toast.LENGTH_SHORT).show();
                    }

                    return true;
                }
                return false;
            }
        });
    }

    private void showTags() {
        mContainer.removeAllViews();
        for(int i=0;i < mList.size();i++ ){
            final TextView mTvTags = (TextView)LayoutInflater.from(getActivity()).inflate(R.layout.tagview,mContainer,false);
            mTvTags.setText(mList.get(i));
            mContainer.addView(mTvTags);
            mTvTags.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((boolean)mTvTags.getTag()){
                        /**
                         * 被激活的edittext的点击事件
                         */
                        return;
                    }else{
                        /**
                         * 未被激活的edittext点击事件
                         */
                        mTvTags.setTag(true);
                        showPopWindow();
                    }

                }
            });
        }
        mContainer.addView(mEtAdd);
    }

    private void showPopWindow() {
        if(mPopupWindow != null){

        }
    }
}
