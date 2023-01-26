package info.azoberland.mycontrols;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;


public class GpsActivity extends Activity
{
    // Acquire a reference to the system Location Manager

    private LocationManager locationManager;
    private LocationListener locationListenerGPS;


    private static final int REQUEST_GPS = 100;

    @Override
    protected void onResume()
    {
        super.onResume();
        setProviderState();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        //showGpsPosition();

        locationListenerGPS = new LocationListener()
        {
            @Override
            public void onLocationChanged(Location location)
            {
                makeUseOfNewLocation(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle bundle)
            {
                Toast.makeText(getApplicationContext(), "GPS_PROVIDER Status changed: " + provider + " : " + status, Toast.LENGTH_LONG).show();
                setProviderState();
            }

            // Wenn die Standort-Freigabe ein-ausgeschaltet wird
            @Override
            public void onProviderEnabled(String provider)
            {
                Toast.makeText(getApplicationContext(), "GPS_PROVIDER enabled: " + provider, Toast.LENGTH_LONG).show();
            }

            // Wenn die Standort-Freigabe ein-ausgeschaltet wird
            @Override
            public void onProviderDisabled(String provider)
            {
                Toast.makeText(getApplicationContext(), "GPS_PROVIDER disabled: " + provider, Toast.LENGTH_LONG).show();
            }
        };


        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            // Register the listener with the Location Manager to receive location updates
            locationManager = (LocationManager) this.getSystemService(getApplicationContext().LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListenerGPS);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "ACCESS_FINE_LOCATION not granted", Toast.LENGTH_LONG).show();

            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION))
            {
                Toast.makeText(getApplicationContext(), "ACCESS_FINE_LOCATION wird verlangt", Toast.LENGTH_LONG).show();
            }

            // was nun?
            requestPermissions(new String[]
                    {
                            Manifest.permission.ACCESS_FINE_LOCATION
                    }, REQUEST_GPS);
        }

        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            LocationManager locMngr = (LocationManager) this.getSystemService(getApplicationContext().LOCATION_SERVICE);
            locMngr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListenerGPS);
        }
    }



    /** Schreibt die GPS-Position in die Activity
     * @param loc GPS-Position
     */
    private void makeUseOfNewLocation(Location loc)
    {
        TextView tvBreitengrad = findViewById(R.id.tvBreitengrad);
        TextView tvLaengengrad = findViewById(R.id.tvLaengengrad);
        TextView tvHoehe = findViewById(R.id.tvHoehe);
        TextView tvGenauikeit = findViewById(R.id.tvGenauigkeit);

        tvBreitengrad.setText(Double.toString( loc.getLongitude()));
        tvLaengengrad.setText(Double.toString(loc.getLatitude()));
        tvHoehe.setText(Double.toString(loc.getAltitude()));
        tvGenauikeit.setText(Double.toString(loc.getAccuracy()));
    }


    // Handle the permissions request response
    //
    // When the user responds to your app's permission request, the system invokes your app's
    // onRequestPermissionsResult() method, passing it the user response.
    // Your app has to override that method to find out whether the permission was granted.
    // The callback is passed the same request code you passed to requestPermissions().
    // For example, if an app requests READ_CONTACTS access it might have the following callback method:
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        switch (requestCode)
        {
            // MY_PERMISSIONS_REQUEST_READ_CONTACTS
            case REQUEST_GPS:
            {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
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
    private String getNetworkType()
    {
        TelephonyManager teleMan = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = teleMan.getNetworkType();

        switch (networkType)
        {
            case TelephonyManager.NETWORK_TYPE_GPRS: return "GPRS General Packet Radio Service";
            case TelephonyManager.NETWORK_TYPE_EDGE: return "EDGE Enhanced Data Rates for GSM Evolution.";
            case TelephonyManager.NETWORK_TYPE_UMTS: return "UMTS Universal Mobile Telecommunications System, 3G";
            case TelephonyManager.NETWORK_TYPE_CDMA: return "CDMA Code Division Multiple Access";
            case TelephonyManager.NETWORK_TYPE_EVDO_0: return "EVDO rev. 0 CDMA2000";
            case TelephonyManager.NETWORK_TYPE_EVDO_A: return "EVDO rev. A CDMA2000";
            case TelephonyManager.NETWORK_TYPE_1xRTT: return "1xRTT";
            case TelephonyManager.NETWORK_TYPE_HSDPA: return "HSDPA High Speed Packet Access (3.5G, 3G+ oder UMTS-Broadband)";
            case TelephonyManager.NETWORK_TYPE_HSUPA: return "HSUPA High Speed Uplink Packet Access (HSUPA)";
            case TelephonyManager.NETWORK_TYPE_HSPA: return "HSPA High Speed Packet Access (3.5G, 3G+ oder UMTS-Broadband)";
            case TelephonyManager.NETWORK_TYPE_IDEN: return "iDen Integrated Digital Enhanced Network (Motorola)";
            case TelephonyManager.NETWORK_TYPE_EVDO_B: return "EVDO rev. B CDMA2000";
            case TelephonyManager.NETWORK_TYPE_LTE: return "LTE (long term evolution, LTE-A, LTE+, 4G)";
            case TelephonyManager.NETWORK_TYPE_EHRPD: return "eHRPD";
            case TelephonyManager.NETWORK_TYPE_HSPAP: return "HSPA+ High Speed Packet Access (3.5G, 3G+ oder UMTS-Broadband)";
            case TelephonyManager.NETWORK_TYPE_GSM: return "GSM";
            case TelephonyManager.NETWORK_TYPE_TD_SCDMA: return "TE_SCDMA";
            case TelephonyManager.NETWORK_TYPE_IWLAN: return "IWLAN";
            // case TelephonyManager.NETWORK_TYPE_LTE_CA: return "LTE_CA"; // 19



            case TelephonyManager.NETWORK_TYPE_UNKNOWN: return "Unbekannter Netzwerktyp.";
            default: return "Unbekannter neuer Netzwerktyp.";
        }
    }

    private void setProviderState()
    {
        TextView tvProvider = findViewById(R.id.tvProvider);
        tvProvider.setText( getNetworkType());
    }
}
