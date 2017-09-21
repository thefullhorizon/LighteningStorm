package com.nanshan.lighteningstorm.pages.index;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.nanshan.lighteningstorm.R;
import com.nanshan.lighteningstorm.pages.general.BaseActivity;
import com.nanshan.lighteningstorm.pages.index.fragments.HomeFragment;
import com.nanshan.lighteningstorm.pages.index.fragments.LifeFragment;
import com.nanshan.lighteningstorm.pages.index.fragments.MeFragment;
import com.nanshan.lighteningstorm.pages.index.fragments.WorkFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class Main extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationBar.OnTabSelectedListener {


    @BindView(R.id.navigation_drawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_left_view)
    NavigationView navigationViewLeft;

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    private ArrayList<Fragment> fragments;


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initializing(Bundle savedInstanceState) {

        initialNavigationLayout();
        initialBottomNavigationLayout();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void initialNavigationLayout(){
        /**
         * 使用ToolBar的方式让NavigationDrawer浮在ToolBar上面更加的符合Material Design
         * 使用ToolBar 注意Theme是NoActionBar 布局中使用ToolBar
         */
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //将drawerLayout, toolbar联动绑定
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        navigationViewLeft.setNavigationItemSelectedListener(this);
    }

    public void initialBottomNavigationLayout(){
        /**
         * 默认情况下
         * 1.主题的PrimaryColor将是active color
         * 2.Color.LTGRAY（灰色）是 in-active color
         * 3.白色是背景色
         */
        //设置导航栏背景模式
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //设置导航栏模式 setMode() Values:MODE_DEFAULT, MODE_FIXED, MODE_SHIFTING
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        //Tab左上角的红点
//        numberBadgeItem = new BadgeItem()
//                .setBorderWidth(4)
//                .setBackgroundColorResource(R.color.red)
//                .setText("5")
//                .setHideOnSelect(true);
        /**
         * 导航栏默认颜色的colorAccent
         * setActiveColorResource / setInActiveColorResource设置获得焦点状态/失去焦点状态下的字体的颜色
         */

        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.home_fill, R.string.tab_home).setInactiveIconResource(R.mipmap.home)
                        .setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.bottom_navigation_clicking_state))
                .addItem(new BottomNavigationItem(R.mipmap.location_fill, R.string.tab_work).setInactiveIconResource(R.mipmap.location)
                        .setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.bottom_navigation_clicking_state))
                .addItem(new BottomNavigationItem(R.mipmap.like_fill, R.string.tab_life).setInactiveIconResource(R.mipmap.like)
                        .setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.bottom_navigation_clicking_state))
                .addItem(new BottomNavigationItem(R.mipmap.person_fill, R.string.tab_person).setInactiveIconResource(R.mipmap.person)
                        .setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.bottom_navigation_clicking_state))
                .setFirstSelectedPosition(0)
                .initialise();

        getFragments();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_content, HomeFragment.newInstance("Home"));
        transaction.commit();

        mBottomNavigationBar.setTabSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onTabSelected(int position) {

        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.replace(R.id.fl_content, fragment);
                ft.commitAllowingStateLoss();
            }
        }

    }

    @Override
    public void onTabUnselected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.remove(fragments.get(position));
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int i) {

    }

    private ArrayList<Fragment> getFragments() {
        fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance(getString(R.string.tab_home)));
        fragments.add(WorkFragment.newInstance(getString(R.string.tab_work)));
        fragments.add(LifeFragment.newInstance(getString(R.string.tab_life)));
        fragments.add(MeFragment.newInstance(getString(R.string.tab_person)));
        return fragments;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_dts:
//                intent = new Intent(Main.this, DTSActivity.class);
//                startActivity(intent);

                break;
            case R.id.action_topics:
//                intent = new Intent(Main.this, TopicsActivity.class);
//                startActivity(intent);

                break;

            case R.id.action_right_menu:
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.closeDrawer(GravityCompat.END);
                } else {
                    drawerLayout.openDrawer(GravityCompat.END);
                }
                break;
        }

        return true;

    }


}
