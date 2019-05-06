package in.qbent.com.qbentrecclick;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

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

import static in.qbent.com.qbentrecclick.R.id.floatAdd;

public class RecycleClickActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<MyData> listItems;
    String data = " ";
    String myToken = " ";

    Button backButton;
    FloatingActionButton floatingActionButton;
    //Button myShow;

//    public RecycleClickActivity(String s)
//    {
//        myToken = s;
//
//
//        //new FetchInfo().execute();
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_click);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");

        listItems = new ArrayList<>();

        backButton = (Button) findViewById(R.id.goBack);
        recyclerView = (RecyclerView) findViewById(R.id.myRecView);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatAdd);

        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(RecycleClickActivity.this,UserFormActivity.class));
            }
        });

        new FetchInfo(message).execute();
    }



    public class FetchInfo extends AsyncTask<Void,Void,Void>
    {
        public FetchInfo(String token)
        {
            myToken = token;
        }

        @Override
        protected Void doInBackground(Void...voids)
        {
            try
            {

                URL url = new URL("http://148.72.209.27:8888/api/external/collection-points");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();//established a connection//
                httpURLConnection.setRequestProperty( "Authorization","bearer "+myToken );


                InputStream inputStream = httpURLConnection.getInputStream();//read the data//
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));//read the data from input stream//
                String line = " ";
                listItems = new ArrayList<MyData>();
                while(line != null)
                {
                    line=bufferedReader.readLine();//read each lines of the JSON file//
                    data =data + line;//All JSON file will be in data//
                }
                JSONArray jsonArray = new JSONArray(data);
                for(int i=0;i<jsonArray.length()-1;i++)
                {
                    JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    MyData myList = new MyData(
                            jsonObject.getString("name"),
                            jsonObject.getString("mobileNo")
                    );
                    listItems.add(myList);


                }
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
        protected void onPostExecute(Void voids)
        {
            super.onPostExecute(voids);
//            recyclerView = new RecyclerView(R.id.myRecView);
            recyclerView = (RecyclerView) findViewById(R.id.myRecView);
            adapter = new MyAdapter(listItems, RecycleClickActivity.this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(RecycleClickActivity.this));
        }
    }

//    public void showLists(View view)
//    {
//        new FetchInfo(this.myToken).execute();
//    }

    public void goHome(View view)
    {
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }

}
