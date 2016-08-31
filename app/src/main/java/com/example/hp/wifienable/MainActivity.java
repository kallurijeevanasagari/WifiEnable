package com.example.hp.wifienable;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
  Switch aSwitch;
    WifiManager wifiManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wifiManager=(WifiManager)getSystemService(Context.WIFI_SERVICE);
        aSwitch=(Switch)findViewById(R.id.switch1);
         int i=wifiManager.getWifiState();
         if(i==0){
             aSwitch.setChecked(false);
         }else {
             aSwitch.setChecked(true);
         }
       aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if(isChecked){
                  wifiManager.setWifiEnabled(true);
              }else {
                   wifiManager.setWifiEnabled(false);
              }
           }
       });

    }
    public void getscanwifidevices(View v){
        List<ScanResult> results= wifiManager.getScanResults();
        for(ScanResult result:results){
            Toast.makeText(getApplicationContext(),result.SSID+"\n"+result.frequency,Toast.LENGTH_SHORT).show();
        }

    }
    public void getpairedwifidevices(View v){
         List<WifiConfiguration> configurations= wifiManager.getConfiguredNetworks();
           for(WifiConfiguration configuration:configurations){
               Toast.makeText(getApplicationContext(),configuration.SSID+"\n"+configuration.status,Toast.LENGTH_SHORT).show();

        }
    }
}
