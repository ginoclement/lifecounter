package edu.washington.gclement.lifecounter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private ArrayList<Magician> magicians = new ArrayList<Magician>();
    private int maxPlayers = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i = 0; i < maxPlayers; i++){
            magicians.add(new Magician());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play1plus1:
                magicians.get(0).changeHealth(1, R.id.play1health);
                break;
            case R.id.play2plus1:
                magicians.get(1).changeHealth(1, R.id.play2health);
                break;
            case R.id.play3plus1:
                magicians.get(2).changeHealth(1, R.id.play3health);
                break;
            case R.id.play4plus1:
                magicians.get(3).changeHealth(1, R.id.play4health);
                break;
            case R.id.play1plus5:
                magicians.get(0).changeHealth(5, R.id.play1health);
                break;
            case R.id.play2plus5:
                magicians.get(1).changeHealth(5, R.id.play2health);
                break;
            case R.id.play3plus5:
                magicians.get(2).changeHealth(5, R.id.play3health);
                break;
            case R.id.play4plus5:
                magicians.get(3).changeHealth(5, R.id.play4health);
                break;
            case R.id.play1minus1:
                magicians.get(0).changeHealth(-1, R.id.play1health);
                break;
            case R.id.play2minus1:
                magicians.get(1).changeHealth(-1, R.id.play2health);
                break;
            case R.id.play3minus1:
                magicians.get(2).changeHealth(-1, R.id.play3health);
                break;
            case R.id.play4minus1:
                magicians.get(3).changeHealth(-1, R.id.play4health);
                break;
            case R.id.play1minus5:
                magicians.get(0).changeHealth(-5, R.id.play1health);
                break;
            case R.id.play2minus5:
                magicians.get(1).changeHealth(-5, R.id.play2health);
                break;
            case R.id.play3minus5:
                magicians.get(2).changeHealth(-5, R.id.play3health);
                break;
            case R.id.play4minus5:
                magicians.get(3).changeHealth(-5, R.id.play4health);
                break;
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Button btnp1p1 = (Button) findViewById(R.id.play1plus1);
        Button btnp1p5 = (Button) findViewById(R.id.play1plus5);
        Button btnp1m1 = (Button) findViewById(R.id.play1minus1);
        Button btnp1m5 = (Button) findViewById(R.id.play1minus5);
        Button btnp2p1 = (Button) findViewById(R.id.play2plus1);
        Button btnp2p5 = (Button) findViewById(R.id.play2plus5);
        Button btnp2m1 = (Button) findViewById(R.id.play2minus1);
        Button btnp2m5 = (Button) findViewById(R.id.play2minus5);
        Button btnp3p1 = (Button) findViewById(R.id.play3plus1);
        Button btnp3p5 = (Button) findViewById(R.id.play3plus5);
        Button btnp3m1 = (Button) findViewById(R.id.play3minus1);
        Button btnp3m5 = (Button) findViewById(R.id.play3minus5);
        Button btnp4p1 = (Button) findViewById(R.id.play4plus1);
        Button btnp4p5 = (Button) findViewById(R.id.play4plus5);
        Button btnp4m1 = (Button) findViewById(R.id.play4minus1);
        Button btnp4m5 = (Button) findViewById(R.id.play4minus5);

        btnp1p1.setOnClickListener(this);
        btnp2p1.setOnClickListener(this);
        btnp3p1.setOnClickListener(this);
        btnp4p1.setOnClickListener(this);

        btnp1p5.setOnClickListener(this);
        btnp2p5.setOnClickListener(this);
        btnp3p5.setOnClickListener(this);
        btnp4p5.setOnClickListener(this);

        btnp1m1.setOnClickListener(this);
        btnp2m1.setOnClickListener(this);
        btnp3m1.setOnClickListener(this);
        btnp4m1.setOnClickListener(this);

        btnp1m5.setOnClickListener(this);
        btnp2m5.setOnClickListener(this);
        btnp3m5.setOnClickListener(this);
        btnp4m5.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class Magician {
        int health;
        boolean isAlive;

        public Magician(){
            this.health = 20;
            this.isAlive = true;
        }

        public int changeHealth(int change, int id){
            Log.i("info", "" + id);
            this.health += change;
            TextView txtHealth = (TextView) findViewById(id);
            txtHealth.setText("" + this.health);
            if(this.health <= 0){
                this.isAlive = false;
            }
            return this.health;
        }

        public boolean isAlive(){
            return this.isAlive;
        }
    }
}
