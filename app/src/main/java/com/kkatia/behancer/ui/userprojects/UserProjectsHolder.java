package com.kkatia.behancer.ui.userprojects;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kkatia.behancer.R;
import com.kkatia.behancer.data.model.project.Project;
import com.kkatia.behancer.utils.DateUtils;
import com.squareup.picasso.Picasso;

import androidx.recyclerview.widget.RecyclerView;

public class UserProjectsHolder  extends RecyclerView.ViewHolder {

    private static final int FIRST_OWNER_INDEX = 0;

    private ImageView mImage;
    private TextView mName;
    private TextView mUsername;
    private TextView mPublishedOn;

    public UserProjectsHolder(View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.image);
        mName = itemView.findViewById(R.id.tv_name);
        mUsername = itemView.findViewById(R.id.tv_username);
        mPublishedOn = itemView.findViewById(R.id.tv_published);
    }

    public void bind(Project item) {
        Picasso.get().load(item.getCover().getPhotoUrl())
                .fit()
                .into(mImage);

        mName.setText(item.getName());
        mUsername.setText(item.getOwners().get(FIRST_OWNER_INDEX).getUsername());
        mPublishedOn.setText(DateUtils.format(item.getPublishedOn()));
    }
}
