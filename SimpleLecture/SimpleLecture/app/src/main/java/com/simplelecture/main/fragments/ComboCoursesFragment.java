package com.simplelecture.main.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.simplelecture.main.R;
import com.simplelecture.main.adapters.ComboCoursesAdapter;
import com.simplelecture.main.fragments.interfaces.OnFragmentInteractionListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ComboCoursesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ComboCoursesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComboCoursesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerView;

    ComboCoursesAdapter comboCoursesAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComboCoursesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ComboCoursesFragment newInstance(String param1, String param2) {
        ComboCoursesFragment fragment = new ComboCoursesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ComboCoursesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View convertView = inflater.inflate(R.layout.fragment_combo_courses, container, false);


        ArrayList<String> data = new ArrayList<>();
        data.add("test1");
        data.add("test2");
        data.add("test3");
        data.add("test4");
        data.add("test5");
        data.add("test6");
        data.add("test7");
        data.add("test8");
        data.add("test9");
        data.add("test7");
        data.add("test8");
        data.add("test9");
        data.add("test7");
        data.add("test8");
        data.add("test9");

        recyclerView = (RecyclerView) convertView.findViewById(R.id.my_recycler_view);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        comboCoursesAdapter = new ComboCoursesAdapter(data);
        recyclerView.setAdapter(comboCoursesAdapter);


        return convertView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
