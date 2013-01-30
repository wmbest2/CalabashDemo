package com.vokal.mcplayer;

import android.support.v4.app.*;
import com.actionbarsherlock.app.SherlockListFragment;

public class PlayerListFragment extends SherlockListFragment {

    public static PlayerListFragment setup(FragmentActivity aActivity, final int aView) {
        FragmentManager manager = aActivity.getSupportFragmentManager();
        Fragment frag = manager.findFragmentById(aView);

        if (frag == null || !(frag instanceof PlayerListFragment)) {
            FragmentTransaction trans = manager.beginTransaction();
            frag = new PlayerListFragment();
            trans.replace(aView, frag);
            trans.commit();
        }

	return (PlayerListFragment) frag;
    }
}
