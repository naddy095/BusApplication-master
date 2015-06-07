package com.example.busapp;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment implements View.OnClickListener {


    View view_detail;
    EditText username,name,password,phone;
    Button SignIn;


    public SignUpFragment() {
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
        view_detail= inflater.inflate(R.layout.fragment_sign_up, container, false);
        username = (EditText)view_detail.findViewById(R.id.edUserName);
        password = (EditText)view_detail.findViewById(R.id.edPassword);
        name = (EditText)view_detail.findViewById(R.id.edName);
        phone = (EditText)view_detail.findViewById(R.id.edPhoneNumber);
        SignIn = (Button)view_detail.findViewById(R.id.signUpBtnInSignUp);

        SignIn.setOnClickListener(this);


        return view_detail;
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
    public void onClick(View v) {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        String name1 = name.getText().toString();
        String number = phone.getText().toString();

        new ExecuteTask().execute(user,pass,name1,number);
    }


    class ExecuteTask extends AsyncTask<String,Integer,String>
    {
        @Override
        protected String doInBackground(String... params) {

            PostData(params);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getActivity(),"register successfully",Toast.LENGTH_SHORT).show();

            FragmentManager fm = getFragmentManager();
            LoginFragment login = new LoginFragment();
            FragmentTransaction FT = fm.beginTransaction();
            FT.replace(R.id.frameLayout,login);
            FT.commit();
        }

    }


    public void PostData(String[] values )
    {

        Gson gson=new Gson();
        NewUser newUser = new NewUser();
        newUser.setUsername(values[0]);
        newUser.setPassword(values[1]);
        newUser.setName(values[2]);
        newUser.setPhone(values[3]);
        gson.toJson(newUser);
        String s="";
        try{
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://www.comparetokar.com/api/user/register");
            StringEntity input = new StringEntity(gson.toJson(newUser).toString());
            input.setContentType("application/json");
            httpPost.setEntity(input);
            HttpResponse httpResponse=  httpClient.execute(httpPost);
            s= readResponse(httpResponse);
            System.out.println(s);
            Log.i("PP", "try");


        }
        catch (Exception e)
        {
        Log.i("pp", e+"");
        }




    }

    private String readResponse(HttpResponse httpResponse) {

        InputStream is=null;
        String return_text="";
        try {
            is=httpResponse.getEntity().getContent();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));
            String line="";
            StringBuffer sb=new StringBuffer();
            while ((line=bufferedReader.readLine())!=null)
            {
                sb.append(line);
            }
            return_text=sb.toString();
        } catch (Exception e)
        {

        }
        return return_text;
    }



}
