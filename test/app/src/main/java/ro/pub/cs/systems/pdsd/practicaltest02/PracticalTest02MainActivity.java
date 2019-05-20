package ro.pub.cs.systems.pdsd.practicaltest02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PracticalTest02MainActivity extends AppCompatActivity {

    TextView defin;
    EditText serverPort, clientPort, address, city;
    Button connect, get;

    ServerThread serverThread = null;
    ClientThread clientThread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test02_main);

        serverPort = (EditText) findViewById(R.id.serverPort);
        clientPort = findViewById(R.id.clientPort);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        connect = findViewById(R.id.connect);
        get = findViewById(R.id.weather);
        defin = findViewById(R.id.weatherView);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String svPort = serverPort.getText().toString();
                if (svPort != null && !svPort.isEmpty()) {
                    serverThread = new ServerThread(Integer.parseInt(svPort));
                    serverThread.start();
                }
            }
        });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = city.getText().toString();

                String clPort = clientPort.getText().toString();
                String clAddress = address.getText().toString();

                clientThread = new ClientThread(clAddress, Integer.parseInt(clPort), query, defin);
                clientThread.start();
            }
        });

    }

    @Override
    protected void onDestroy() {

        if (serverThread != null)
            serverThread.stopThread();
        super.onDestroy();


    }
}
