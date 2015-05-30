package com.example.busapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class PickArea extends ListActivity {

    final Context context=this;
    static final String[] pickArea = new String[] {  "Abbigere", "Aecs Layout", "Airport Area", "Airport Road", "Akshaya Nagar", "Anekal",
            "Anjanapura", "Attibele", "Bagalur", "Banashankari", "Banaswadi", "Bannerghatta Road", "Basavanagar", "Basavanagudi",
            "Basaveshwaranagar", "Begur Road", "Belandur", "Bellary Road", "Benson Town", "Bilekahalli", "Bommanahalli", "Bommasandra",
            "Brooke Field", "Btm Layout", "C V Raman Nagar", "Central Silk Board", "Chamarajpet", "Chambal River", "Chandapur", "Chikkaballapur",
            "Chikkajala", "Cookes Town", "Cox Town", "Defence Colony", "Devanahalli", "Dodballapur Road", "Doddaballapur", "Doddaballapur Road",
            "Dollars Colony", "Domlur", "Electronic City", "Fraser Town", "G M Palya", "Ganganagar","Gottigere", "Hanumanth Nagar",
            "Hbr Layout", "Hebbal", "Hebbal Kempapura", "Hegde Nagar", "Hennur", "Hennur Road",
            "Hesaraghatta Main Road", "Hmt Layout", "Hoodi Village", "Horamavu", "Hoskote", "Hoskote", "Hosur Road", "Hrbr Layout",
            "Hsr Layout", "Hulimavu", "Huskur", "Indira Nagar", "Indraprastha", "Isro Layout", "Itpl", "Jakkur", "Jalahalli", "Jayanagar",
            "Jeevan Bima Nagar", "Jigani Industrial Area", "Jp Nagar", "Kaggadaspura", "Kalyan Nagar", "Kanaka Nagar", "Kanakapura Road",
            "Kasturi Nagar", "Kengeri", "Kodigehalli", "Koramangala", "Kr Puram", "Kudlu Gate", "Kumaraswamy Layout", "Kundalahalli",
            "Lavelle Road", "Madiwala", "Magadi Road", "Mahadevapura", "Majestic", "Mallesh Palaya", "Malleshwaram", "Manek Chowk",
            "Marathahalli", "Mathikere", "Mg Road", "Millers Road", "Mysore Road", "Naganathapura", "Nagarbhavi", "Nagawara", "Nagwar",
            "Nandi Hills", "Nelamangala", "Old Airport Road", "Old Madras Road", "Ombr Layout", "Outer Ring Road", "Padmanabhanagar",
            "Pai Layout", "Palace Road", "Peenya", "Prashanth Nagar", "Raj Bhavan", "Rajajinagar", "Rajanukunte", "Rajarajeshwari Nagar",
            "Ramamurthy Nagar", "Rbi Layout", "Rest House Road", "Richards Tow", "Richmond Road", "Rmv Extension Stage", "Rt Nagar",
            "Sadaramangala", "Sadaramangala", "Sahakar Nagar", "Sanjay Nagar", "Sarjapur", "Sarjapur Road", "Shanti Nagar", "Silkboard",
            "Thanisandra", "Thippasandra", "Thyagaraj Nagar", "Tippasandra", "Tumkur Road", "Ulsoor", "Uttarahalli", "Vasanth Nagar",
            "Vidyanagar", "Vidyaranyapura", "Vigyan Nagar", "Vijaya Bank Layout", "Vijayanagar", "Whitefield", "Wilson Garden", "Yelahanka",
            "Yeshwantpur" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_pick_area);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_area, pickArea));

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Intent intent = new Intent(context,MainActivity.class);
                intent.putExtra("area", ((TextView) view).getText());
                // Use Bundle
                startActivity(intent);
             /*   Toast.makeText(getApplicationContext(),
                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();*/
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pick_area, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
