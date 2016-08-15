package ljc.mypracticework.model;

/**
 * Created by lijianchang on 16/8/15.
 *
 */
public class PracticeInfo {

    public PracticeInfo(){

    }

    public PracticeInfo(String mInfoTitle,String mInfoDes,int id,String mTimeTag){
        this.mDrawableId = id;
        this.mInfoTitle = mInfoTitle;
        this.mInfoDes   = mInfoDes;
        this.mTimeTag = mTimeTag;
    }

    public String mInfoTitle;

    public String mInfoDes;

    public  int mDrawableId;

    public String mTimeTag;
}
