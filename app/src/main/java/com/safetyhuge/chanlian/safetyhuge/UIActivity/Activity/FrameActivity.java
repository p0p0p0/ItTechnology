package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.fragment.BlankFragment;
import com.safetyhuge.chanlian.safetyhuge.fragment.ContFragment;
import com.safetyhuge.chanlian.safetyhuge.fragment.IncpFragment;
import com.safetyhuge.chanlian.safetyhuge.fragment.MineFragment;
import com.safetyhuge.chanlian.safetyhuge.fragment.ShaopingCatFragment;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页
 * 徐艳昭
 * 15326057231@163.com
 * 2017/3/6
 **/

public class FrameActivity extends FragmentActivity {
    @Bind(R.id.frame_iamge)
    ImageView mFrameIamge;
    @Bind(R.id.movie_rb)
    RadioButton mMovieRb;
    @Bind(R.id.article_rb)
    RadioButton mArticleRb;
    @Bind(R.id.pictrue_rb)
    RadioButton mPictrueRb;
    @Bind(R.id.critic_gwc)
    RadioButton mCriticGwc;
    @Bind(R.id.critic_rb)
    RadioButton mCriticRb;
    private FrameLayout container;
    private RadioGroup radioGroup;
    private RadioButton[] buttons;
    private List<Fragment> list;
    private Intent intent;
    private int current = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        ButterKnife.bind(this);
        initView();
        initFragment();
        initRadion();
    }

    private void initFragment() {
        list = new ArrayList<Fragment>();
        BlankFragment blankFragment = new BlankFragment();
        IncpFragment incpFragment = new IncpFragment();
        ContFragment contFragment = new ContFragment();
        ShaopingCatFragment shaopingCatFragment = new ShaopingCatFragment();
        MineFragment mineFragment = new MineFragment();
        list.add(blankFragment);
        list.add(incpFragment);
        list.add(contFragment);
        list.add(shaopingCatFragment);
        list.add(mineFragment);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, blankFragment).commit();
    }

    private void initRadion() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
       /* buttons = new RadioButton[radioGroup.getChildCount()];
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            buttons[i] = (RadioButton) radioGroup.getChildAt(i);
        }*/
        //  buttons[0].setChecked(true);
        mFrameIamge.setVisibility(View.VISIBLE);
        changeFragment(0);
       /* radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < buttons.length; i++) {
                    if (buttons[i].getId() == checkedId) {
                        changeFragment(i);
                    }
                }
            }
        });*/
    }

    protected void changeFragment(int i) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment tarFragment = list.get(i);
        if (list.get(i).isAdded()) {
            ft.show(tarFragment).hide(list.get(current)).commit();

        } else {
            ft.add(R.id.container, tarFragment).hide(list.get(current))
                    .commit();
        }
        current = i;
    }

    //实例化组件
    private void initView() {
        container = (FrameLayout) findViewById(R.id.container);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @OnClick({R.id.movie_rb, R.id.article_rb, R.id.pictrue_rb, R.id.critic_gwc, R.id.critic_rb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.movie_rb:
                changeFragment(0);
                break;
            case R.id.article_rb:
                changeFragment(1);
                break;
            case R.id.pictrue_rb:
                changeFragment(2);
                break;
            case R.id.critic_gwc:
                changeFragment(3);
                break;
            case R.id.critic_rb:
                changeFragment(4);
                break;
        }
    }
}

