package info.azoberland.mycontrols;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

public class MeldungenActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meldungen);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        LinearLayout vLayout = findViewById(R.id.meldungen);
        Snackbar snackbar = Snackbar
                .make(vLayout, "Meldung von der 'Snackbar'", Snackbar.LENGTH_INDEFINITE)
                .setAction("Drück mich", new View.OnClickListener()

                {
                    @Override
                    public void onClick(View view)
                    {
                        Toast.makeText(getApplicationContext(), "Ich bin eine Toast-Meldung", Toast.LENGTH_LONG).show();
                    }
                });

        snackbar.setActionTextColor(Color.RED);
        snackbar.setTextColor(Color.YELLOW);
        snackbar.show();
    }
}
