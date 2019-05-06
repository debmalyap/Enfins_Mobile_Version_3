package in.qbent.com.qbentrecclick;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.R.layout;
import android.widget.Toast;
//import android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserFormActivity extends AppCompatActivity
{
    Spinner spinner;
    DatabaseHelper databaseHelper;
    //API of country lists//
    //public String URL = "http://148.72.209.27:8888/api/external/countries";
    private List<Country> countryLists;
    String data = " ";
    String countryToken = " ";

//    public UserFormActivity(String s)
//    {
//        countryToken = s;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);
//        Bundle bundle = getIntent().getExtras();
//        String message = bundle.getString("message");

        databaseHelper = new DatabaseHelper(UserFormActivity.this);
        String message =  databaseHelper.getDetails();

        spinner = (Spinner) findViewById(R.id.mySpinner);
        countryLists = new ArrayList<Country>();
       // new FetchCountry().execute();
        //Create an ArrayAdapter using the string array and a default spinner layout//
//        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.districts_lists, android.R.layout.simple_spinner_item);
//        //Specify the layout to use ehtn the list of dictricts appears//
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //Apply the adapter to the spinner//
//        spinner.setAdapter(arrayAdapter);
        //A method to load the elements of a spinner//
        //loadSpinnerValues(URL);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                //String country = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();

                //spinner.setOnItemSelectedListener(this);
                //Toast.makeText(getApplicationContext(),country, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                //Yet to be completed//
            }
        });

        new FetchCountry(message).execute();

    }

//    private void loadSpinnerValues(String url) {
//        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
//        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response)
//            {
//                try
//                {
//                    URL url = new URL("http://148.72.209.27:8888/api/external/countries");
//                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                    InputStream inputStream = httpURLConnection.getInputStream();//read the data//
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));//read the data from input stream//
//                    String line = " ";
//                    //countryLists = new ArrayList<String>();
//                    while (line!=null)
//                    {
//                        line = bufferedReader.readLine();
//                        data = data + line;
//                    }
//                    JSONArray jsonArray = new JSONArray(data);
//                    for(int i=0;i<jsonArray.length()-1;i++)
//                    {
//                        JSONObject jsonObject = (JSONObject)jsonArray.get(i);
//                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                        Country countryModel = new Country(
//                                jsonObject.getString("name")
//
//                        );
//                        countryLists.add(countryModel);
//                    }
////                    JSONObject jsonObject = new JSONObject(response);
////                    String countries = jsonObject.getString("name");
////                    countryLists.add(countries);
//                    ArrayAdapter<Country> arrayAdapter = new ArrayAdapter<Country>(UserFormActivity.this,android.R.layout.simple_spinner_dropdown_item,countryLists);
//                    spinner.setAdapter(arrayAdapter);
//
//                }
//
//                catch (JSONException e)
//                {
//                    e.printStackTrace();
//                }
//                catch (NullPointerException e)
//                {
//                    e.printStackTrace();
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            //end of onResponse//
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        int socketTimeout = 30000;
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        stringRequest.setRetryPolicy(policy);
//        requestQueue.add(stringRequest);
//    }

//    public void loadSpinnerValues(String url)
//    {
//        try
//        {
//            URL url1 = new URL("http://148.72.209.27:8888/api/external/countries");
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
//            InputStream inputStream = httpURLConnection.getInputStream();//read the data//
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));//read the data from input stream//
//            String line = " ";
//            //countryLists = new ArrayList<String>();
//            while (line!=null)
//            {
//                line = bufferedReader.readLine();
//                data = data + line;
//            }
//            JSONArray jsonArray = new JSONArray(data);
//            for(int i=0;i<jsonArray.length()-1;i++)
//            {
//                JSONObject jsonObject = (JSONObject)jsonArray.get(i);
//                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                Country countryModel = new Country(
//                        jsonObject.getString("name")
//
//                );
//                countryLists.add(countryModel);
//            }
////                    JSONObject jsonObject = new JSONObject(response);
////                    String countries = jsonObject.getString("name");
////                    countryLists.add(countries);
//            ArrayAdapter<Country> arrayAdapter = new ArrayAdapter<Country>(UserFormActivity.this,android.R.layout.simple_spinner_dropdown_item,countryLists);
//            spinner.setAdapter(arrayAdapter);
//
//        }
//
//        catch (JSONException e)
//        {
//            e.printStackTrace();
//        }
//        catch (NullPointerException e)
//        {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public class FetchCountry extends AsyncTask<Void, Void, Void>
    {
        public FetchCountry(String token)
        {
            countryToken = token;
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            try
            {

                URL url = new URL("http://148.72.209.27:8888/api/external/countries");
                HttpURLConnection httpURLConnection1 = (HttpURLConnection) url.openConnection();//established a connection//
                httpURLConnection1.setRequestProperty( "Authorization","bearer "+countryToken );

                InputStream inputStream = httpURLConnection1.getInputStream();//read the data//
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));//read the data from input stream//
                String line = " ";
                countryLists = new ArrayList<Country>();
                while(line != null)
                {
                    line=bufferedReader.readLine();//read each lines of the JSON file//
                    data =data + line;//All JSON file will be in data//
                }
                JSONArray jsonArray = new JSONArray(data);
                for(int i=0;i<jsonArray.length();i++)
                {
                    //JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                    JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                    Country country = new Country(
                            jsonObject.getString("name")
                    );
                    countryLists.add(country);

//
                }
//
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            //ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(UserFormActivity.this, R.array.districts_lists, android.R.layout.simple_spinner_item);
            //ArrayAdapter<Country> arrayAdapter = new ArrayAdapter<Country>(getApplicationContext(),android.R.layout.simple_spinner_item, countryLists);
            ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(UserFormActivity.this, android.R.layout.simple_spinner_item,countryLists);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }
    }
}
