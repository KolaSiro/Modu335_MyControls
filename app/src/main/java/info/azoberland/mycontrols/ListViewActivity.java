package info.azoberland.mycontrols;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;

/**
 * Listview ist eine Liste von einfachen strings die auf Click reagieren
 */
public class ListViewActivity extends Activity
{
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_activity);

        // Get ListView object from xml
        listView = findViewById(R.id.list);


        // Defined Array values to show in ListView
        String[] laender = new String[]
                {
                "Ägypten", "Argentinien", "Australien",
                "Belgien", "Brasilien", "Costa Rica",
                "Dänemark", "Deutschland", "England",
                "Frankreich", "Iran", "Island",
                "Japan", "Kolumbien", "Kroatien",
                "Marokko", "Mexiko", "Nigeria",
                "Panama", "Peru",
                "Polen", "Portugal",
                "Russland", "Saudi-Arabien", "Schweden","Schweiz","Senegal","Serbien","Spanien","Südkorea","Tunesien", "Uruguay"
                };
        Arrays.sort(laender);

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, laender);
//        {
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent)
//            {
//                View view = super.getView(position, convertView, parent);
//                TextView text1 = view.findViewById(android.R.id.text1);
//                TextView text2 = view.findViewById(android.R.id.text2);
////                text1.setText(spieler.get(position).toString());
////                text2.setText("Jahresverdienst: " + spieler.get(position).getJahresVerdienst().toString() + " Euro pro Monat");
//                return view;
//            }
//        };

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int iPos, long id)
            {
                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(iPos);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + iPos + "  ListItem : " + itemValue + " id= " + id, Toast.LENGTH_LONG)
                        .show();
            }
        });


    }
}