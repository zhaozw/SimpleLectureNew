package com.simplelecture.main.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.simplelecture.main.R;
import com.simplelecture.main.adapters.ViewPagerAdapter;
import com.simplelecture.main.fragments.ComboCoursesFragment;
import com.simplelecture.main.fragments.CourseBenifitsFragment;
import com.simplelecture.main.fragments.CourseDescriptionFragment;
import com.simplelecture.main.fragments.CourseFeatureFragment;
import com.simplelecture.main.fragments.FAQFragment;
import com.simplelecture.main.fragments.ReviewFragment;
import com.simplelecture.main.fragments.interfaces.OnFragmentInteractionListener;
import com.simplelecture.main.model.viewmodel.CourseDetailsResponseModel;
import com.simplelecture.main.model.viewmodel.CourseMonths;
import com.simplelecture.main.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ComboCourseActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private EditText searchEditText;
    private CourseDetailsResponseModel courseDetailsResponseModelObj;

    Intent intent;

    Spinner spinnerMonths;
    TextView textViewCourseAmount;
    CustomSpinnerAdapter customSpinnerAdapter;
    ArrayList<CharSequence> courseMaterials = new ArrayList<>();
    CheckBox chekInclude;
    TextView textViewLabelMaterial;

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.secureScreenShot(ComboCourseActivity.this);
        setContentView(R.layout.activity_combo_course);

        intent = getIntent();
        if (intent.hasExtra("courseDetails")) {
            courseDetailsResponseModelObj = (CourseDetailsResponseModel) intent.getSerializableExtra("courseDetails");
        }

        courseMaterials = Util.convertToStringArray(courseDetailsResponseModelObj.getCourseMaterials());

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        searchEditText = (EditText) toolbar.findViewById(R.id.searchEditText);
        searchEditText.setVisibility(View.GONE);
        getSupportActionBar().setTitle(courseDetailsResponseModelObj.getcName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Changing the action bar color
        getSupportActionBar().setTitle(Util.setActionBarText(getSupportActionBar().getTitle().toString()));
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        spinnerMonths = (Spinner) findViewById(R.id.spinner_months);
        customSpinnerAdapter = new CustomSpinnerAdapter(this, courseDetailsResponseModelObj.getCourseMonths());
        spinnerMonths.setAdapter(customSpinnerAdapter);
        textViewCourseAmount = (TextView) findViewById(R.id.textViewCourseAmount);
        textViewCourseAmount.setText(courseDetailsResponseModelObj.getCoursePrice() + " X ");
        chekInclude = (CheckBox) findViewById(R.id.checkBox);
        chekInclude.setOnCheckedChangeListener(onCheckedChangeListener);
        textViewLabelMaterial = (TextView) findViewById(R.id.textView_labelMaterial);

        viewPager.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });
    }

    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (isChecked) {
                showMaterialsDialog();
            }
        }
    };

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ComboCoursesFragment().newInstance(courseDetailsResponseModelObj), getResources().getString(R.string.comboCourse));
        adapter.addFrag(new CourseFeatureFragment().newInstance(courseDetailsResponseModelObj), getResources().getString(R.string.courseFeature));
        adapter.addFrag(new CourseDescriptionFragment().newInstance(courseDetailsResponseModelObj), getResources().getString(R.string.courseDescription));
        adapter.addFrag(new CourseBenifitsFragment().newInstance(courseDetailsResponseModelObj), getResources().getString(R.string.courseBenifits));
        adapter.addFrag(new FAQFragment().newInstance(courseDetailsResponseModelObj), getResources().getString(R.string.fAQ));
        adapter.addFrag(new ReviewFragment(), getResources().getString(R.string.review));

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


   /* class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }*/

    private void showMaterialsDialog() {
        final CharSequence[] dialogList = courseMaterials.toArray(new CharSequence[courseMaterials.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Item");
        int count = dialogList.length;
        boolean[] is_checked = new boolean[count];
        builder.setMultiChoiceItems(dialogList, is_checked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        });

        // Set the positive/yes button click listener
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when click positive button

                ListView list = ((AlertDialog) dialog).getListView();
                // make selected item in the comma seprated string
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < list.getCount(); i++) {
                    boolean checked = list.isItemChecked(i);

                    if (checked) {
                        if (stringBuilder.length() > 0) stringBuilder.append(",");
                        stringBuilder.append(list.getItemAtPosition(i));
                        textViewLabelMaterial.setVisibility(View.VISIBLE);
                        textViewLabelMaterial.setText(stringBuilder.toString());

                    }
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when click the negative button
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();

    }

    private class CustomSpinnerAdapter extends BaseAdapter {

        private List<CourseMonths> data;
        private Context mContext;
        private LayoutInflater layoutInflater;

        public CustomSpinnerAdapter(Context context, List<CourseMonths> courseMonths) {
            data = courseMonths;
            mContext = context;
            layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = layoutInflater.inflate(R.layout.spinner_layout, parent, false);
            TextView listItem = (TextView) row.findViewById(R.id.textview);
            listItem.setText(data.get(position).getName());
            return row;
        }
    }
}
