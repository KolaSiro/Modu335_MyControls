package info.azoberland.mycontrols;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class DynamicActivity extends Activity
{
    /**
     *
     * @param sizeInDp
     * @return
     */
    public int  densityConverter(int sizeInDp)
    {
        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (sizeInDp*scale + 0.5f);
        float a = 1.1f;
        return dpAsPixels;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dynamic_activity);

        // LinearLayout definiert als XML-Datei
        final LinearLayout lm = (LinearLayout) findViewById(R.id.myViewGroup);
        lm.setBackgroundColor(Color.rgb(190,190,255));

        // Datum und Ort
        TextView gruppenSpiel = new TextView(this);
        gruppenSpiel.setGravity(Gravity.CENTER_HORIZONTAL);
        gruppenSpiel.setText("GRUPPENSPIEL");
        gruppenSpiel.setTextColor(Color.WHITE);
        //gruppenSpiel.setBackgroundColor(Color.BLACK);
        gruppenSpiel.setTextAppearance(this, android.R.style.TextAppearance_Large);
        lm.addView(gruppenSpiel);

        ArrayList<String> orte = new ArrayList<>();
        orte.add("Fr. 10.06. 21:00 - St. Denis / Paris");
        orte.add("Sa. 11.06. 15:00 - Lens");
        orte.add("Mi. 15.06. 18:00 - Paris");
        orte.add("Mi. 15.06. 21:00 - Marseille");
        orte.add("So. 19.06. 21:00 - Lille");
        orte.add("So. 19.06. 21:00 - Lyon");

        ArrayList<String> spiele = new ArrayList<>();
        spiele.add("Frankreich 0:0 Rumaenien");
        spiele.add("Albanien 0:0 Schweiz");
        spiele.add("Rumaenien 0:0 Schweiz");
        spiele.add("Frankreich 0:0 Albanien");
        spiele.add("Schweiz 0:0 Frankreich");
        spiele.add("Rumaenien 0:0 Albanien");

        ArrayList<String> abschluss = new ArrayList<>();
        abschluss.add("Frankreich: 3 Pkt.");
        abschluss.add("Schweiz   : 2 Pkt.");
        abschluss.add("Albanien  : 1 Pkt.");
        abschluss.add("Rumaenien : 0 Pkt.");

        // 6 Gruppenspiele anzeigen
        for(int j = 0; j < 6; j++)
        {
            LinearLayout horz = new LinearLayout(this);
            horz.setOrientation(LinearLayout.HORIZONTAL);
            horz.setGravity(Gravity.CENTER_HORIZONTAL);

            // Background Color abwechselnd aendern
            horz.setBackgroundColor(( (j % 2) == 0) ? Color.rgb(200,255,200) : Color.WHITE);
            horz.setPadding(0,densityConverter(10),0,densityConverter(10));
            horz.setDividerPadding(0);

            // Flagge Links
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.flag_france);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            horz.addView(imageView);

            LinearLayout vert = new LinearLayout(this);
            vert.setOrientation(LinearLayout.VERTICAL);
            vert.setPadding(densityConverter(10), 0, densityConverter(10), 0);

            // Datum und Ort
            TextView datumOrt = new TextView(this);
            datumOrt.setGravity(Gravity.CENTER_HORIZONTAL);
            datumOrt.setText(orte.get(j));
            datumOrt.setTextAppearance(this, android.R.style.TextAppearance_Small);

            vert.addView(datumOrt);

            // Spielstand 3:2
            TextView spielstand = new TextView(this);
            spielstand.setGravity(Gravity.CENTER_HORIZONTAL);
            spielstand.setText(spiele.get(j));
            spielstand.setAllCaps(true);
            spielstand.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
            spielstand.setTextAppearance(this, android.R.style.TextAppearance_Medium);

            vert.addView(spielstand);

            horz.addView(vert);

            // Flagge Rechts
            ImageView imageView2 = new ImageView(this);
            imageView2.setImageResource(R.drawable.flag_romania);
            imageView2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            horz.addView(imageView2);

            // Event-Handler registrieren
            final int index = j;
            horz.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Toast.makeText(getApplicationContext(), "Clicked Index :" + index, Toast.LENGTH_SHORT).show();
                }
            });

            // add dynamic view to a fixed XML-Layout
            lm.addView(horz);
        }

        TextView abschlussTabelle = new TextView(this);
        abschlussTabelle.setText("Abschlusstabelle");
        abschlussTabelle.setAllCaps(true);
        abschlussTabelle.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        abschlussTabelle.setGravity(Gravity.CENTER_HORIZONTAL);
        abschlussTabelle.setTextColor(Color.WHITE);
        abschlussTabelle.setBackgroundColor(Color.BLACK);
        abschlussTabelle.setPadding(0,densityConverter(5),0,densityConverter(5));
        abschlussTabelle.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
        lm.addView(abschlussTabelle);

        for(int i = 0; i < 4; i++)
        {
            int backColor = ((i % 2) == 0) ? Color.rgb(200,255,200) : Color.WHITE;

            TextView tv = new TextView(this);
            tv.setText(Integer.toString(i+1) + ". " + abschluss.get(i));


            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            tv.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
            tv.setPadding(0,densityConverter(10),0,densityConverter(10));
            tv.setBackgroundColor(backColor);

            // Flagge Links
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.flag_france);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setBackgroundColor(backColor);

            LinearLayout linSmall = new LinearLayout(this);
            linSmall.setOrientation(LinearLayout.HORIZONTAL);
            linSmall.setGravity(Gravity.CENTER_HORIZONTAL);
            linSmall.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            linSmall.addView(imageView);
            linSmall.addView(tv);

            // Event-Handler registrieren
            final int index = i;
            linSmall.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Toast.makeText(getApplicationContext(), "zu den Achtelfinals...", Toast.LENGTH_SHORT).show();
                }
            });
            lm.addView(linSmall);
        }
    }
}

