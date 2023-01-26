package info.azoberland.mycontrols;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class ListViewActivity2Row extends AppCompatActivity
{

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_activity2_row);

        // Get ListView object from xml
        listView = findViewById(R.id.list2Row);


        // Defined Array values to show in ListView
        final ArrayList<Spieler> spieler = new ArrayList<Spieler>();
        spieler.add(new Spieler("Toni", "Terror", "Stürmer", 1000000));
        spieler.add(new Spieler("Fritz", "Flankengott", "rechter Flügel", 550000));
        spieler.add(new Spieler("Beat", "Blutgrätsche", "linker Flügel", 480000));
        spieler.add(new Spieler("Thomas", "Torlos", "Mittelstürmer", 30000));
        spieler.add(new Spieler("Sebastian", "Simulant", "Libero", 445000));
        spieler.add(new Spieler("Karl", "Klugmann", "Kapitän", 1200000));
        spieler.add(new Spieler("Henning", "Humpler", "Verteidiger", 4500));
        spieler.add(new Spieler("Walter", "Warmduscher", "Verteidiger", 9500));
        spieler.add(new Spieler("Covid", "Coroner", "Mittelfeld", 1));
        spieler.add(new Spieler("Günther", "Netzer", "Verteidiger", 123456));


        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        ArrayAdapter<Spieler> adapter = new ArrayAdapter<Spieler>(this,
                android.R.layout.simple_list_item_2, android.R.id.text2, spieler)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);
                text1.setText(spieler.get(position).toString());
                text2.setText("Jahresverdienst: " + spieler.get(position).getJahresVerdienst().toString() + " Euro pro Monat");
                return view;
            }
        };

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int iPos, long id)
            {

                // ListView Clicked item value
                Spieler itemValue = (Spieler) listView.getItemAtPosition(iPos);



                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Spieler: " + itemValue.toString() , Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
