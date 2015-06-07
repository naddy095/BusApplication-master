package com.example.busapp;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    View view_user;
    Intent intent_user;
    Button SignUp;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view_user=  inflater.inflate(R.layout.fragment_login, container, false);

        SignUp = (Button)view_user.findViewById(R.id.signUpBtnInSignUp);
        SignUp.setOnClickListener(this);

        return view_user;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View v)
    {

        FragmentManager fm = getFragmentManager();
        SignUpFragment user = new SignUpFragment();
        FragmentTransaction FT = fm.beginTransaction();
        FT.replace(R.id.frameLayout,user);
        FT.commit();


    }
}
