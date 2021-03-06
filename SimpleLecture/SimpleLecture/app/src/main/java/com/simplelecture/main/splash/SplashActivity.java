package com.simplelecture.main.splash;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.simplelecture.main.R;
import com.simplelecture.main.adapters.ScreenSlidePagerAdapter;
import com.simplelecture.main.fragments.SelectYourCoursesFragment;
import com.simplelecture.main.util.SessionManager;
import com.simplelecture.main.util.Util;
import com.simplelecture.main.util.ViewPagerIndicator;
import com.simplelecture.main.util.ZoomOutPageTransformer;
import com.simplelecture.main.viewManager.ViewManager;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    private ViewPagerIndicator pageIndicator;
    private Button buttonExplore, buttonSignin;

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.secureScreenShot(SplashActivity.this);

        setContentView(R.layout.activity_splash);

        buttonExplore = (Button) findViewById(R.id.buttonExplore);
        buttonSignin = (Button) findViewById(R.id.buttonSignin);
        pageIndicator = (ViewPagerIndicator) findViewById(R.id.page_indicator);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), null);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mPager.setAdapter(mPagerAdapter);
        pageIndicator.setViewPager(mPager);
        mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                pageIndicator.setViewPager(mPager);

                //  validateTheButton();
                //pageIndicator.notifyDataSetChanged();

            }
        });


        buttonExplore.setOnClickListener(this);
        buttonSignin.setOnClickListener(this);
    }

    private void validateTheButton() {
        /*if (mPager.getCurrentItem() == mPagerAdapter.getCount() - 1) {
            nextButton.setClickable(true);
            nextButton.setEnabled(true);
            nextButton.setFocusable(true);
        } else {
            nextButton.setClickable(false);
            nextButton.setEnabled(false);
            nextButton.setFocusable(false);
        }*/
    }

    @Override
    public void onClick(View v) {
        if (v == buttonExplore) {
            String displayName = "SplashActivity";

            SessionManager sessionManager = SessionManager.getInstance();

            sessionManager.setLoginStatus(Util.getFromPrefrencesBoolean(this, "loginStatus"));
            sessionManager.setLoginFBStatus(Util.getFromPrefrencesBoolean(this, "FBStatus"));
            sessionManager.setLoginGmailStatus(Util.getFromPrefrencesBoolean(this, "GmailStatus"));
            sessionManager.setLoginSLStatus(Util.getFromPrefrencesBoolean(this, "SLStatus"));

            SelectYourCoursesFragment selectYourCoursesFragment = new SelectYourCoursesFragment().newInstance(displayName, "");
            FragmentManager fragmentManager = getSupportFragmentManager();
            selectYourCoursesFragment.show(fragmentManager, "SplashActivity");

        } else if (v == buttonSignin) {
            new ViewManager().gotoSigninView(this);
        }
    }
}
