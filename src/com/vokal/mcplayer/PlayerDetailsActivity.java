package com.vokal.mcplayer;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.vokal.network.*;
import com.vokal.async_image_cache.ImageFetcher;

import com.vokal.mcplayer.models.Player;

public class PlayerDetailsActivity extends SherlockFragmentActivity {

    private ImageFetcher mFetcher;

    @Override
    public void onCreate(Bundle aSavedState) {
        super.onCreate(aSavedState);
        setContentView(R.layout.details);
        mFetcher = new ImageFetcher(this);
        mFetcher.addImageCache(this);

        Player p = Player.withName(this, getIntent().getStringExtra(Player.NAME));

        ImageView avatar = (ImageView) findViewById(R.id.avatar);
        mFetcher.loadImage(getString(R.string.avatar_url, p.mName), avatar);

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(p.mName);
        TextView loc = (TextView) findViewById(R.id.location);
        loc.setText(getString(R.string.location, p.mX, p.mY, p.mZ));
    }
}
