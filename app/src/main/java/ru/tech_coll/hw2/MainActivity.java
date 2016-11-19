package ru.tech_coll.hw2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView rv;
    List<Imgs> imgs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        MyTask mt = new MyTask();
        mt.execute();

    }


    class MyTask extends AsyncTask<Void, Void, Void> {


        RecyclerView rv_;

        public String download() {

            String result = "";

            try {
                URL url = new URL("http://dev.handh.ru/test.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setConnectTimeout(10000);
                connection.setRequestMethod("GET");
                connection.setReadTimeout(10000);

                connection.connect();

                InputStream is = connection.getInputStream();
                BufferedReader reader = new BufferedReader((new InputStreamReader(is)));
                StringBuilder strBuff = new StringBuilder();
                result = null;

                while ((result = reader.readLine()) != null) {
                    strBuff.append(result);
                }
                result = strBuff.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }


        @Override
        protected Void doInBackground(Void... params) {

            String strJson;
            JSONObject jso;

            strJson = download();
            imgs = new ArrayList<>();

            try {
                jso = new JSONObject(strJson);
                JSONArray Result = jso.getJSONArray("images");

                for (int i = 0; i < Result.length(); i++) {
                    JSONObject item = Result.getJSONObject(i);
                    String url = item.getString("ulr");
                    imgs.add(new Imgs(url));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            rv_ = (RecyclerView)findViewById(R.id.rv);
            RVadapter adapt = new RVadapter(imgs);
            rv_.setAdapter(adapt);
        }
    }

}
