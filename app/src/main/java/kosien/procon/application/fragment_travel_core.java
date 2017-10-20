package kosien.procon.application;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import su.heartlove.matatabi.R;

/**
 * Created by Owner on 2017/10/15.
 */

public class fragment_travel_core extends Fragment {
    private TabLayout tabLayout;
    private ViewPager pager;
    private travelFragmentPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        super.onCreateView(inflater, container, saveInstanceState);

        //ホストのタブを使用する
        tabLayout = (TabLayout) getActivity().findViewById(R.id.tabLayout);

        return inflater.inflate(R.layout.fragment_travel_core, container, false);

    }


    @Override
    public void onViewCreated(final View view, Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
        //ViewPagerのセット
        pager = (ViewPager) view.findViewById(R.id.travel_pager);

        //アダプターセット

        adapter = new travelFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
    }


}
