package info.azoberland.mycontrols;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ListView die aufklappt und zusätzliche Items anzeigt.
 */
public class ExpandableListViewActivity extends Activity
{
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandable_list_view_activity);

        // get the listview
        expListView = findViewById(R.id.lvExp);

        // Click handler
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
            {
                Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition)
                        + " : " + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Expand handler
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener()
        {

            @Override
            public void onGroupExpand(int groupPosition)
            {
                Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " Expanded", Toast.LENGTH_SHORT).show();
            }
        });

        // Collapse handler
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener()
        {

            @Override
            public void onGroupCollapse(int groupPosition)
            {
                Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " Collapsed", Toast.LENGTH_SHORT).show();
            }
        });


        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData()
    {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding child data
        listDataHeader.add("Schweiz");
        listDataHeader.add("England");
        listDataHeader.add("Frankreich");

        // Adding child data
        List<String> ch = new ArrayList<>();
        ch.add("Thomas Torlos");
        ch.add("Karl Kicker");
        ch.add("Ferdi Flankengott");
        ch.add("Bertil Blutschere");
        ch.add("Conrelius Cornerschuss");
        ch.add("Stefan Stürmer");
        ch.add("Sebastian Schwalber");

        List<String> en = new ArrayList<>();
        en.add("Gerarld Graveyard ");
        en.add("Peter Powerless");
        en.add("Jack Toolate");
        en.add("Paul Peddigree");
        en.add("Steven Snail");
        en.add("Dave Deadman");

        List<String> fr = new ArrayList<>();
        fr.add("Pierre Tresfort");
        fr.add("Antoine Absolute");
        fr.add("Francois Frejus");
        fr.add("Paul Pedicure");
        fr.add("Henri LaVache");

        listDataChild.put(listDataHeader.get(0), ch); // Header, Child data
        listDataChild.put(listDataHeader.get(1), en);
        listDataChild.put(listDataHeader.get(2), fr);
    }
}

