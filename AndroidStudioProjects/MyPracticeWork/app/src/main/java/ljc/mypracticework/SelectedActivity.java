package ljc.mypracticework;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import ljc.mypracticework.fragment.FlowLayoutFragment;
import ljc.mypracticework.fragment.RecyclerviewSwipeFragment;

/**
 * Created by lijianchang on 16/8/16.
 *
 */
public class SelectedActivity extends AppCompatActivity {

    @Bind(R.id.activity_selected_container)
    FrameLayout mContainer;
    public  Fragment mFragment;

    public static String KEY_FRAGMENT = "key_fragment";

    private int i;

    public static void mSelectedActivityStart(Context mContext,int position){
        Intent intent = new Intent();
        intent.setClass(mContext,SelectedActivity.class);
        intent.putExtra(KEY_FRAGMENT,position);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected);
        ButterKnife.bind(this);
        i = getIntent().getIntExtra(KEY_FRAGMENT,0);
        switch (i){
            case 0:
                mFragment = RecyclerviewSwipeFragment.newInstance();
                break;
            case 1:
                mFragment = FlowLayoutFragment.newInstance();
                break;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_selected_container, mFragment)
                .commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
