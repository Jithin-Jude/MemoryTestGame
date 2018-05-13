package com.inc.game.memoryTestGame;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inc.game.memoryTestGame.R;

/**
 * Created by JithinJude on 30-01-2018.
 */

public class Tab1 extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1_layout, container, false);
        return rootView;
    }
}
