package com.kkatia.behancer.common;

import android.os.Bundle;

import com.kkatia.behancer.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * @author Azret Magometov
 */
public abstract class RefreshActivity extends SingleFragmentActivity implements SwipeRefreshLayout.OnRefreshListener, RefreshOwner {

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSwipeRefreshLayout = findViewById(R.id.refresher);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

//    @Override
//    protected int getLayout() {
//        return R.layout.ac_swipe_container;
//    }

    @Override
    public void onRefresh() {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment instanceof Refreshable) {
            ((Refreshable) fragment).onRefreshData();
        } else {
            setRefreshState(false);
        }
    }

    @Override
    public void setRefreshState(boolean refreshing) {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(refreshing));
    }

}
