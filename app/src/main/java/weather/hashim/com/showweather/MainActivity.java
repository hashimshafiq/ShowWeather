package weather.hashim.com.showweather;

import android.app.Activity;
import android.content.Context;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


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

        Model m = new Model();
        m.setTempMain("20");
        m.setDate("19");
        m.setDay("Wednesday");
        m.setDescription("Rain");
        m.setLocation("Lahore, PK");
        m.setTempSecondry("5");


        weatherData.add(m);
        m.setDay("THursday");
        weatherData.add(m);
        m.setDay("Friday");
        weatherData.add(m);



        arrayAdapter = new weatherDataArrayAdapter(MainActivity.this,0,weatherData);

        customList.setAdapter(arrayAdapter);



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





}
