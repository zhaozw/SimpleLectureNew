package com.simplelecture.main.transactions;

import android.content.Context;

import com.simplelecture.main.BuildConfig;
import com.simplelecture.main.constants.Constants;
import com.simplelecture.main.http.GetTransaction;

import org.json.JSONObject;

import java.net.URI;

/**
 * Created by Raos on 8/28/2016.
 */
public class DashboardTestPaperChapterTransaction extends GetTransaction {

    private final String cId;

    public DashboardTestPaperChapterTransaction(JSONObject jsonObject, Context context, String cID) {
        super(jsonObject, context);
        this.cId = cID;
    }

    @Override
    protected JSONObject setupRequestBody() {
        return null;
    }

    @Override
    protected String getUri() {
        return BuildConfig.BASE_URL;
    }

    @Override
    protected URI getRequestUri() {
        return null;
    }

    @Override
    protected String getHeader() {
        return null;
    }

    @Override
    protected String getUrlPrefix() {
        return Constants.GET_USER_QUIZ_CHAPTERS + cId;
    }

}
