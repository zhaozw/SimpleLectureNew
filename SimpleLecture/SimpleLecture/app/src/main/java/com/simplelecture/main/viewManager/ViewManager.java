package com.simplelecture.main.viewManager;

import android.app.Activity;
import android.content.Intent;

import com.simplelecture.main.activities.ChangePasswordActivity;
import com.simplelecture.main.activities.ComboCourseActivity;
import com.simplelecture.main.activities.CreateAccountActivity;
import com.simplelecture.main.activities.DashboardActivity;
import com.simplelecture.main.activities.ForgotPasswordActivity;
import com.simplelecture.main.activities.HomeActivity;
import com.simplelecture.main.activities.LoginActivity;
import com.simplelecture.main.activities.SingleCourseActivity;

/**
 * Created by karthik.rao on 03-02-2016.
 */
public class ViewManager {

    private Intent intent;

    public ViewManager() {
    }

    /**
     * @param activity
     * @param intent
     */
    public void setDisplay(Activity activity, Intent intent) {
        activity.startActivity(intent);
    }


    /**
     * Description: go to Login View
     *
     * @return
     */
    public Intent gotoLoginView(Activity activity) {
        intent = new Intent(activity, LoginActivity.class);
        setDisplay(activity, intent);
        //activity.finish();
        return intent;
    }

    /**
     * Description: go to Create Account View
     *
     * @return
     */
    public Intent gotoCreateAccountView(Activity activity) {
        intent = new Intent(activity, CreateAccountActivity.class);
        setDisplay(activity, intent);
        return intent;
    }

    /**
     * Description: go to Forgot Password View
     *
     * @return
     */
    public Intent gotoForgotPasswordView(Activity activity) {
        intent = new Intent(activity, ForgotPasswordActivity.class);
        setDisplay(activity, intent);
        return intent;
    }

    /**
     * Description: go to Change Password View
     *
     * @return
     */
    public Intent gotoChangePasswordView(Activity activity) {
        intent = new Intent(activity, ChangePasswordActivity.class);
        setDisplay(activity, intent);
        return intent;
    }

    /**
     * Description: go to Home View
     *
     * @return
     */
    public Intent gotoHomeView(Activity activity) {
        intent = new Intent(activity, HomeActivity.class);
        setDisplay(activity, intent);
        return intent;
    }

    /**
     * Description: go to Combo Course View
     *
     * @return
     */
    public Intent gotoComboCourseView(Activity activity) {
        intent = new Intent(activity, ComboCourseActivity.class);
        setDisplay(activity, intent);
        return intent;
    }

    public Intent gotoDashboardView(Activity activity) {
        intent = new Intent(activity, DashboardActivity.class);
        setDisplay(activity, intent);
        return intent;
    }

    /**
     * Description: go to Single Course View
     *
     * @return
     */
    public Intent gotoSingleCourseView(Activity activity) {
        intent = new Intent(activity, SingleCourseActivity.class);
        setDisplay(activity, intent);
        return intent;
    }
}
