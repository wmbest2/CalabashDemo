package com.vokal.mcplayer;

import android.content.*;
import android.database.*;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import android.support.v4.app.*;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import com.actionbarsherlock.app.SherlockListFragment;

import com.vokal.mcplayer.models.Player;

public class PlayerListFragment extends SherlockListFragment {

    public static final int LOADER_PLAYERS = 0;

    private LoaderCallbacks<Cursor> mManager = new LoaderCallbacks<Cursor>() {
        public Loader<Cursor> onCreateLoader(int aId, Bundle aArgs) {
            return new CursorLoader(getActivity(), Player.CONTENT_URI, Player.ALL,
                null, null, null);
        }

        public void onLoadFinished(Loader<Cursor> aLoader, Cursor aData) {
            if (getListAdapter() == null) {
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(),
		    android.R.layout.simple_list_item_1, aData,
		    new String[] { Player.NAME },
		    new int[] { android.R.id.text1 });
                setListAdapter(adapter);
            } else {
                ((CursorAdapter) getListAdapter()).swapCursor(aData);
            }
        }

        public void onLoaderReset(Loader<Cursor> aLoader) {
            ((CursorAdapter) getListAdapter()).swapCursor(null);
        }
    };

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

    public void onListItemClick(ListView aList, View aView, int position, long id) {
	TextView nameView = (TextView) aView.findViewById(android.R.id.text1);

	Intent i = new Intent(getActivity(), PlayerDetailsActivity.class);
	i.putExtra(Player.NAME, nameView.getText().toString());
	startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();

        getLoaderManager().initLoader(LOADER_PLAYERS, null, mManager);
    }
}
