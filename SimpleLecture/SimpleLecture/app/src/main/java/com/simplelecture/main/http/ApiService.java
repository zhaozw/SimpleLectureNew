package com.simplelecture.main.http;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.simplelecture.main.model.LoginModel;
import com.simplelecture.main.model.SignInModel;
import com.simplelecture.main.transactions.CartAddTransaction;
import com.simplelecture.main.transactions.CartDetailsTransaction;
import com.simplelecture.main.transactions.CartRemoveTransaction;
import com.simplelecture.main.transactions.ChangePasswordTransaction;
import com.simplelecture.main.transactions.ChaptersTransaction;
import com.simplelecture.main.transactions.CourseCategoriesTransaction;
import com.simplelecture.main.transactions.CoursePostReviewTransaction;
import com.simplelecture.main.transactions.CoursesDetailsTransaction;
import com.simplelecture.main.transactions.DemoTutorialTransaction;
import com.simplelecture.main.transactions.ForgotPasswordTransaction;
import com.simplelecture.main.transactions.HomePageDataTransaction;
import com.simplelecture.main.transactions.LoginTransaction;
import com.simplelecture.main.transactions.MyCoursesTransaction;
import com.simplelecture.main.transactions.SelectMyCourseTransaction;
import com.simplelecture.main.transactions.SignInTransaction;
import com.simplelecture.main.transactions.VimeoVideoTransaction;
import com.simplelecture.main.util.JsonFactory;
import com.simplelecture.main.util.Util;

import org.json.JSONObject;

/**
 * Created by M1032185 on 2/16/2016.
 */
public class ApiService {

    public static ApiService apiService = new ApiService();

    private ApiService() {

    }

    public static  String headerToken = "";

    public static ApiService getApiService() {
        return apiService;
    }

    public void doLogin(LoginModel loginModel, Context context) {
        try {
            JsonFactory jsonFactory = new JsonFactory();
            JSONObject jsonObject = jsonFactory.getLoginParams(loginModel.getUe(), loginModel.getUp());
            LoginTransaction loginTransaction = new LoginTransaction(jsonObject, context);
            TransactionProcessor transactionProcessor = new TransactionProcessor(context);
            transactionProcessor.execute(loginTransaction);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void doGetMyCourses(Context mContext, String uId, Fragment fragmentContext) {

        try {
            //use this to get token stored in prefrences
            String token = Util.getFromPrefrences(mContext, "uToken");

            //String token = "alc3ZXpKOE1MSWl2aVlBV25tNHlpSHlRc3N3MkYvWGFLZGRsV3FLU0QzWT06REVFS1NIQU5BSURVMTlAR01BSUwuQ09NOjYzNTkxMjM1MjY4NzE0Njg0OQ==";

            MyCoursesTransaction myCoursesTransaction = new MyCoursesTransaction(null, mContext, uId, token);
            TransactionProcessor transactionProcessor = new TransactionProcessor(fragmentContext);
            transactionProcessor.execute(myCoursesTransaction);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void doGetCourseDetails(Context mContext, Fragment fragmentContext, String uId) {

        try {
            CoursesDetailsTransaction coursesDetailsTransaction = new CoursesDetailsTransaction(null, mContext, uId);
            TransactionProcessor transactionProcessor = new TransactionProcessor(fragmentContext);
            transactionProcessor.execute(coursesDetailsTransaction);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //HomeCoursesModel index
    public void doGetChapters(Context mContext, Fragment fragmentContext, String uId) {
        try {
            String token = Util.getFromPrefrences(mContext, "uToken");

            ChaptersTransaction chaptersTransaction = new ChaptersTransaction(null, mContext, uId, token);
            TransactionProcessor transactionProcessor = new TransactionProcessor(fragmentContext);
            transactionProcessor.execute(chaptersTransaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void doAddToCart(Context mContext,JSONObject jsonObject) {

        headerToken = Util.getFromPrefrences(mContext, "uToken");


        CartAddTransaction cartAddTransaction = new CartAddTransaction(jsonObject, mContext);
        TransactionProcessor transactionProcessor = new TransactionProcessor(mContext);
        transactionProcessor.execute(cartAddTransaction);

    }

    public void doGetCartDetails(Context mContext) {

        String userId = Util.getFromPrefrences(mContext, "uId");
        String token = Util.getFromPrefrences(mContext, "uToken");

        CartDetailsTransaction cartDetailsTransaction = new CartDetailsTransaction(null, mContext, userId, token);
        TransactionProcessor transactionProcessor = new TransactionProcessor(mContext);
        transactionProcessor.execute(cartDetailsTransaction);

    }

    public void doRemoveFromCart(Context mContext,String courseId){

        String token = Util.getFromPrefrences(mContext, "uToken");
        String userId = Util.getFromPrefrences(mContext, "uId");

        CartRemoveTransaction cartRemoveTransaction = new CartRemoveTransaction(null,mContext,userId +"/"+courseId,token);
        TransactionProcessor transactionProcessor = new TransactionProcessor(mContext);
        transactionProcessor.execute(cartRemoveTransaction);
    }

    //Vimeo Video
    public void doGetVimeoVideoURL(Context mContext, int videoId) {

        try {
            //use this to get token stored in prefrences
            String token = Util.getFromPrefrences(mContext, "uToken");

            VimeoVideoTransaction vimeoVideoTransaction = new VimeoVideoTransaction(null, mContext, videoId, token);
            TransactionProcessor transactionProcessor = new TransactionProcessor(mContext);
            transactionProcessor.execute(vimeoVideoTransaction);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void doGetHomeScreenData(Context mContext, Fragment fragmentContext, String uId) {

        try {
            HomePageDataTransaction homePageDataTransaction = new HomePageDataTransaction(null, mContext, uId);
            TransactionProcessor transactionProcessor = new TransactionProcessor(fragmentContext);
            transactionProcessor.execute(homePageDataTransaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGetVideoSampleTutorial(Context mContext, Fragment fragmentContext) {

        try {

            DemoTutorialTransaction demoTutorialTransaction = new DemoTutorialTransaction(null, mContext);
            TransactionProcessor transactionProcessor = new TransactionProcessor(fragmentContext);
            transactionProcessor.execute(demoTutorialTransaction);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void doGetSelectYourCourse(Context mContext, Fragment fragmentContext) {

        try {

            SelectMyCourseTransaction selectMyCourseTransaction = new SelectMyCourseTransaction(null, mContext);
            TransactionProcessor transactionProcessor = new TransactionProcessor(fragmentContext);
            transactionProcessor.execute(selectMyCourseTransaction);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void doGetCourseCategories(Context mContext, Fragment fragmentContext, String CId) {

        try {

            CourseCategoriesTransaction courseCategoriesTransaction = new CourseCategoriesTransaction(null, mContext, CId);
            TransactionProcessor transactionProcessor = new TransactionProcessor(fragmentContext);
            transactionProcessor.execute(courseCategoriesTransaction);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void doGetForgotPassword(Context mContext, String email) {

        try {

            JsonFactory jsonFactory = new JsonFactory();
            JSONObject jsonObject = jsonFactory.getForgotPwdParams(email);
            ForgotPasswordTransaction forgotPasswordTransaction = new ForgotPasswordTransaction(jsonObject, mContext);
            TransactionProcessor transactionProcessor = new TransactionProcessor(mContext);
            transactionProcessor.execute(forgotPasswordTransaction);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGetChangePassword(Context mContext, String oldPassword, String confirmPassword) {

        try {

            String token = Util.getFromPrefrences(mContext, "uToken");
            String uId = Util.getFromPrefrences(mContext, "uId");

            JsonFactory jsonFactory = new JsonFactory();
            JSONObject jsonObject = jsonFactory.getChangePwdParams(uId, oldPassword, confirmPassword);
            ChangePasswordTransaction changePasswordTransaction = new ChangePasswordTransaction(jsonObject, mContext, token);
            TransactionProcessor transactionProcessor = new TransactionProcessor(mContext);
            transactionProcessor.execute(changePasswordTransaction);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void doGetSignIn(Context mContext, SignInModel signInModel) {

        try {

            JsonFactory jsonFactory = new JsonFactory();
            JSONObject jsonObject = jsonFactory.getSignInParams(signInModel);
            SignInTransaction signInTransaction = new SignInTransaction(jsonObject, mContext);
            TransactionProcessor transactionProcessor = new TransactionProcessor(mContext);
            transactionProcessor.execute(signInTransaction);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void doGetCoursePostReview(Context mContext, Fragment fragmentContext, String cid, String reviewText) {

        try {

            String token = Util.getFromPrefrences(mContext, "uToken");
            String uId = Util.getFromPrefrences(mContext, "uId");

            JsonFactory jsonFactory = new JsonFactory();
            JSONObject jsonObject = jsonFactory.getCoursePostReview(uId, cid, reviewText);
            CoursePostReviewTransaction coursePostReviewTransaction = new CoursePostReviewTransaction(jsonObject, mContext, token);
            TransactionProcessor transactionProcessor = new TransactionProcessor(fragmentContext);
            transactionProcessor.execute(coursePostReviewTransaction);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
