package kosien.procon.application;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by Owner on 2017/10/15.
 */



public class travelFragmentPagerAdapter extends FragmentPagerAdapter {

    /*表示内容：１つ１つの表示はフラグメントが担当する
    ・次の予定
    ・周辺のお勧めの場所
    ・地図UI
    ・次訪れる観光地の情報
    */

    //表示内容の数によってページ数を決める。
    private static final int PAGE_NUM = 4;

    public travelFragmentPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        Fragment fragment = null;

        //ここでフラグメントの指定
        switch(position){
            case 0:
                fragment = new fragment_travel();
                break;
            case 1:
                fragment = new fragment_travel();
                break;
            case 2:
                fragment = new fragment_travel();
                break;
            default:
                fragment = new fragment_travel();

        }
        return fragment;
    }

    @Override
    public int getCount(){
        return PAGE_NUM;
    }

    @Override
    public CharSequence getPageTitle(int position){
        CharSequence sequence = null;

        //ここでページタイトルを返す。
        switch(position){
            case 0:
                sequence = "テスト１";
                break;
            case 1:
                sequence = "テスト２";
                break;
            case 2:
                sequence =  "テスト３";
                break;
            default:
                sequence = "テスト４";

        }

        return sequence;
    }


}
