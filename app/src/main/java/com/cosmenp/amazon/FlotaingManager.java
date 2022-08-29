package com.cosmenp.amazon;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class FlotaingManager {

    public static void manageRecyclerView(FragmentActivity activity, RecyclerView recyclerView){
        final FloatingActionButton fab = activity.findViewById(R.id.fab);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0){
                    fab.hide();
                } else if (dy < 0){
                    fab.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

}
