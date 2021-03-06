package kosien.procon.application;

import android.graphics.Bitmap;

public class makeScheduleListItem {
    private Bitmap mThumbnail = null;
    private String mTitle = null;
    private String mNakami = null;
    private String mTime = null;

    /**
     * 空のコンストラクタ
     */
    public makeScheduleListItem() {};

    /**
     * コンストラクタ
     * @param thumbnail サムネイル画像
     * @param title タイトル
     */
    public makeScheduleListItem(Bitmap thumbnail, String title, String nakami, String time) {
        mThumbnail = thumbnail;
        mTitle = title;
        mNakami = nakami;
        mTime = time;

    }

    /**
     * サムネイル画像を設定
     * @param thumbnail サムネイル画像
     */
    public void setThumbnail(Bitmap thumbnail) {
        mThumbnail = thumbnail;
    }

    /**
     * タイトルを設定
     * @param title タイトル
     */
    public void setmTitle(String title) {
        mTitle = title;
    }

    /**
     * ナカミを設定
     * @param nakami ナカミ
     */
    public void setmNakami(String nakami) {
        mNakami = nakami;
    }

    /**
     * 時間を設定
     * @param time 時間
     */
    public void setmTime(String time) {
        mTime = time;
    }

    /**
     * サムネイル画像を取得
     * @return サムネイル画像
     */
    public Bitmap getThumbnail() {
        return mThumbnail;
    }

    /**
     * タイトルを取得
     * @return タイトル
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * ナカミを取得
     * @return ナカミ
     */
    public String getNalami() {
        return mNakami;
    }

    /**
     * 時間を取得
     * @return 時間
     */
    public String getTime() {
        return mTime;
    }
}