package com.gl.tmdb.app.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gl.tmdb.R;
import com.gl.tmdb.content.model.Account;

/**
 * Custom NavigationView header implementation.
 */
public class NavigationHeader extends LinearLayout {

    public NavigationHeader(Context context) {
        this(context, null, 0);
    }

    public NavigationHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            init(context, attrs);
        }
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.navigation_header_layout, this, true);
    }

    public void showSignIn(View.OnClickListener clickListener) {
        findViewById(R.id.account_layout).setVisibility(View.GONE);
        final View signInLayout = findViewById(R.id.sign_layout);
        signInLayout.setVisibility(View.VISIBLE);
        signInLayout.setOnClickListener(clickListener);
    }

    public void showAccount(Account account, View.OnClickListener clickListener) {
        findViewById(R.id.sign_layout).setVisibility(View.GONE);
        final View accountLayout = findViewById(R.id.account_layout);
        accountLayout.setVisibility(View.VISIBLE);
        accountLayout.setOnClickListener(clickListener);
        ImageView avatar = (ImageView) accountLayout.findViewById(R.id.avatar);
        TextView userName = (TextView) accountLayout.findViewById(R.id.username);
        TextView name = (TextView) accountLayout.findViewById(R.id.name);
        Glide.with(getContext())
                .load(account.getAvatar().getGravatar().getGravatarUrl(80))
                .fitCenter()
                .into(avatar);
        userName.setText(account.getUsername());
        name.setText(account.getName());
    }
}
