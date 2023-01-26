package info.azoberland.mycontrols;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HardwareActivity extends Activity
{
    private static final int REQUEST_NETWORK_STATE = 100;
    private ArrayList<String> sensorenListe = new ArrayList<>();
    private ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hardware_activity);
        listView = findViewById(R.id.listView_activity_hardware_sensoren);
    }

    private void showSensoren()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, sensorenListe);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        showNetworkState();
    }

    private void showNetworkState()
    {
        if (checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED)
        {
            // Netzwerk erlaubnis ist bereits gewährt
            // d.h. Zugriff auf das Netzwerk (WLAN oder Mobil) ist ohne Einschränkungen immer möglich
            // das muss nicht explizit gewährt werden...
            // siehe https://stackoverflow.com/questions/43464282/android-does-runtime-permission-required-for-internet-and-access-network-state-i
            checkWLANAndMobilFunk();

            sensorenListe.clear();
            checkSensoren();

        }
        else
        {
            // Erlaubnis noch nicht erteilt
            if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_NETWORK_STATE))
            {
                Toast.makeText(this, "Erlaubnis Netzwerkstatus anzuzeigen wird benötigt.", Toast.LENGTH_LONG).show();
            }

            // Erlaubnis einholen vom Benutzer
            requestPermissions(new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, REQUEST_NETWORK_STATE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

             switch (requestCode)
              {
                  // MY_PERMISSIONS_REQUEST_READ_CONTACTS
                  case REQUEST_NETWORK_STATE:
                  {
                      // If request is cancelled, the result arrays are empty.
                      if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                      {
                          // permission was granted, yay! Do the
                      }
                      else
                      {
                          // permission denied, boo! Disable the
                          // functionality that depends on this permission.
                      }
                      return;
                  }

                  default:
                      break;
                  // other 'case' lines to check for other
                  // permissions this app might request.
              }
    }

    private void checkSensoren()
    {
        sensorenListe.clear();

        SensorManager sensorenManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

//        if(sensorenManager.getDefaultSensor(Sensor.TYPE_POSE_6DOF) != null)
//        {
//            sensorenListe.add("POSE_6DOF vorhanden");
//        }
//        if (sensorenManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
//        {
//            sensorenListe.add("ACCELEROMETER vorhanden");
//        }
//
//        if(sensorenManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null)
//        {
//            sensorenListe.add("GYROSCOPE vorhanden");
//        }
//
//        if(sensorenManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null)
//        {
//            sensorenListe.add("MAGNETOMETER vorhanden");
//        }

        List<Sensor> sList = sensorenManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s: sList)
        {
            sensorenListe.add(s.getName() ); //+ " " + s.getStringType() + " " + s.getVendor() + " Version: " + s.getVersion());
        }


        showSensoren();
    }

   private void checkWLANAndMobilFunk()
   {
       ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
       NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
       TextView tvWifi =  findViewById(R.id.tvWifiStatus);

       FrameLayout layoutGPS = findViewById(R.id.layoutGPS);
       layoutGPS.setClickable(false);

       if (activeNetwork != null)
       {
           switch (activeNetwork.getType())
           {
               case ConnectivityManager.TYPE_WIFI:
                   // Mit WLAN verbunden -> billig
                   tvWifi.setText("WLAN ON: " + activeNetwork.getTypeName());
                   layoutGPS.setClickable(true);
                   break;
               case ConnectivityManager.TYPE_MOBILE:
                   // Mit Mobilfunk-Betreiber in Internet -> teuer
                   tvWifi.setText("MobilFunk ON: " + activeNetwork.getTypeName());
                   layoutGPS.setClickable(true);
                   break;
                   default:
                       tvWifi.setText("Connectivity Type ??: " + activeNetwork.getTypeName());
                       break;
           }
       }
       else
       {
           // WIFI nicht eingeschaltet
           tvWifi.setText("Kein aktives Netzwerk gefunden.");
       }
   }
    public void onClickGPS(View view)
    {
        final Intent intent = new Intent(this, GpsActivity.class);
        startActivity(intent);
    }
}
