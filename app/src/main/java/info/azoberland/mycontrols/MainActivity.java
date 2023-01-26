package info.azoberland.mycontrols;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

// Haupteinstiegsklasse fuer MyControls - App
public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickButtons(View button)
    {
        final Intent intent = new Intent(this, ButtonActivity.class);
        startActivity(intent);
    }

    public void onClickListView(View button)
    {
        final Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);
    }

    public void onClickListViewExpandable(View button)
    {
        final Intent intent = new Intent(this, ExpandableListViewActivity.class);
        startActivity(intent);
    }

    public void onClickHardware(View button)
    {
        final Intent intent = new Intent(this, HardwareActivity.class);
        startActivity(intent);
    }

    public void onClickGridLayout(View button)
    {
        final Intent intent = new Intent(this, GridViewActivity.class);
        startActivity(intent);
    }

    public void onClickDynamicView(View button)
    {
        final Intent intent = new Intent(this, DynamicActivity.class);
        startActivity(intent);
    }

    public  void onClickList2Row(View button)
    {
        final Intent intent = new Intent(this, ListViewActivity2Row.class);
        startActivity(intent);;
    }

    public void onClickRecyclerView(View button)
    {
        final Intent intent = new Intent(this, RecyclerViewActivity.class);
        startActivity(intent);
    }

    public void onClickMeldungenView(View button)
    {
        final Intent intent = new Intent(this, MeldungenActivity.class);
        startActivity(intent);
    }

    public void onClickTableLayoutView(View button)
    {
        final Intent intent = new Intent(this, TableActivity.class);
        startActivity(intent);;
    }
}
