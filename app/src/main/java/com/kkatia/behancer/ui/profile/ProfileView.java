package com.kkatia.behancer.ui.profile;

import com.kkatia.behancer.common.BaseView;
import com.kkatia.behancer.data.model.user.User;

interface ProfileView extends BaseView {

    void showProfile(User user);

    void showRefresh();

    void hideRefresh();
}
