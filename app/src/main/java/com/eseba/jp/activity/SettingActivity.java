package com.eseba.jp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.eseba.jp.R;
import com.eseba.jp.bus.listener.OnSettingClickListener;
import com.eseba.jp.fragment.setting.SettingAllFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity implements OnSettingClickListener {
    private static final String TAG = SettingActivity.class.getSimpleName();

    @BindView(R.id.setting_toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        super.setSupportActionBar(this.toolbar);
        this.drawComponentViews();
    }

    @OnClick(R.id.toolbar_setting)
    public void settingButtonOnClick() {
        Intent intent = new Intent(this, SettingActivity.class);
        super.startActivity(intent);
        super.finish();
    }

    @Override
    public void notificationOnClick() {
//        this.drawContentWithFragment(SettingNotificationFragment.getInstance(this));
    }

    @Override
    public void onBackFragmentPressed() {
        super.getSupportFragmentManager().popBackStack();
    }

    private void drawComponentViews() {
        this.drawContentWithFragment(SettingAllFragment.getInstance(this));
    }

    private void drawContentWithFragment(Fragment fragment) {
        super.getSupportFragmentManager()
            .beginTransaction()
            .add(
                R.id.setting_content,
                fragment
            )
            .addToBackStack(null)
            .commit();
    }
}
