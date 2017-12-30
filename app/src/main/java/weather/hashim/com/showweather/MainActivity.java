package weather.hashim.com.showweather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private final String URL = "https://api.openweathermap.org/data/2.5/weather?id=2172797&units=metric&APPID=7f4af2564fbd4a95779c2c1d55e83fcf";

    //Widgets
    TextView mainTemp;
    TextView secTemp;
    TextView description;
    TextView location;
    TextView date;
    ImageView weatherImage;
    ListView customList;

    //vars
    private ArrayList<Model> weatherData = new ArrayList<>();
    ArrayAdapter<Model> arrayAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTemp = findViewById(R.id.tempMain);
        secTemp = findViewById(R.id.tempSecondry);
        description = findViewById(R.id.weatherDescription);
        location = findViewById(R.id.location);
        date = findViewById(R.id.day);
        weatherImage = findViewById(R.id.weatherImage);
        customList = findViewById(R.id.customList);




        new GetWeatherData().execute();

        //main




    }


    private class weatherDataArrayAdapter extends ArrayAdapter<Model> {

        private List<Model> weatherData;
        private Context context;


        public weatherDataArrayAdapter(Context context, int resurce, ArrayList<Model> objects){
            super(context,resurce,objects);
            this.context = context;
            this.weatherData = objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Model weather = weatherData.get(position);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.custom_layout_listview,null);

            TextView mainTemp = view.findViewById(R.id.tempMain);
            TextView secTemp = view.findViewById(R.id.tempSecondry);
            TextView description = view.findViewById(R.id.weatherDescription);
            TextView day = view.findViewById(R.id.day);
            ImageView weatherImage = view.findViewById(R.id.weatherImage);


            mainTemp.setText(weather.getTempMain());
            secTemp.setText(weather.getTempSecondry());
            description.setText(weather.getDescription());
            day.setText(weather.getDay());
            //set image







            return view;
        }
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetWeatherData extends AsyncTask<Void, Void, Void> {

        ProgressDialog pDialog=null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(URL);



            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    //getting name
                    String city = jsonObj.getString("name");

                    // Getting JSON Array node of weather
                    JSONArray weatherObject = jsonObj.getJSONArray("weather");
                    // Getting JSON Objcts from node index=0 for getting description
                    JSONObject descriptionObject = weatherObject.getJSONObject(0);
                    String descriptions = descriptionObject.getString("main");
                    System.out.println("answer: "+descriptions);

                    // Getting JSON Object node of main
                    JSONObject mainObject = jsonObj.getJSONObject("main");
                    String maintemp = mainObject.getString("temp");
                    String minTemp = mainObject.getString("temp_min");

                    // Getting JSON Object node of sys
                    JSONObject sysObject = jsonObj.getJSONObject("sys");
                    String country = sysObject.getString("country");

                    Model m = new Model();
                    m.setDay("Today");
                    m.setLocation(city+", "+country);
                    m.setTempMain(maintemp);
                    m.setDescription(descriptions);
                    m.setTempSecondry(minTemp);

                    weatherData.add(m);






                } catch (final JSONException e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
//            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */

            arrayAdapter = new weatherDataArrayAdapter(MainActivity.this,0,weatherData);

            customList.setAdapter(arrayAdapter);

            mainTemp.setText(weatherData.get(0).getTempMain()+"");
            secTemp.setText(weatherData.get(0).getTempSecondry()+"");
            description.setText(weatherData.get(0).getDescription()+"");
            location.setText(weatherData.get(0).getLocation()+"");

        }

    }





}
