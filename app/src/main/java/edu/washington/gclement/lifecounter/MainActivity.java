package edu.washington.gclement.lifecounter;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private ArrayList<Magician> magicians = new ArrayList<Magician>();
    private ArrayList<Button> buttons = new ArrayList<Button>();
    private ArrayList<TextView> scores = new ArrayList<TextView>();
    private int maxPlayers = 4;
    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
//        Android level greater than level 13
//        Point p = new Point();
//        display.getSize(p);
//        width = p.x;
//        height = p.y;
        width = display.getWidth();
        height = display.getHeight();
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            magicians = savedInstanceState.getParcelableArrayList("magicians");
        } else {
            for(int i = 0; i < maxPlayers; i++){
                magicians.add(new Magician());
            }
        }

        buttons.add((Button) findViewById(R.id.play1plus1));
        buttons.add((Button) findViewById(R.id.play1plus5));
        buttons.add((Button) findViewById(R.id.play1minus1));
        buttons.add((Button) findViewById(R.id.play1minus5));
        buttons.add((Button) findViewById(R.id.play2plus1));
        buttons.add((Button) findViewById(R.id.play2plus5));
        buttons.add((Button) findViewById(R.id.play2minus1));
        buttons.add((Button) findViewById(R.id.play2minus5));
        buttons.add((Button) findViewById(R.id.play3plus1));
        buttons.add((Button) findViewById(R.id.play3plus5));
        buttons.add((Button) findViewById(R.id.play3minus1));
        buttons.add((Button) findViewById(R.id.play3minus5));
        buttons.add((Button) findViewById(R.id.play4plus1));
        buttons.add((Button) findViewById(R.id.play4plus5));
        buttons.add((Button) findViewById(R.id.play4minus1));
        buttons.add((Button) findViewById(R.id.play4minus5));

        scores.add((TextView) findViewById(R.id.play1health));
        scores.add((TextView) findViewById(R.id.play2health));
        scores.add((TextView) findViewById(R.id.play3health));
        scores.add((TextView) findViewById(R.id.play4health));

    }

    @Override
    public void onClick(View v) {
        int health = 20;
        int id = 0;
        switch (v.getId()) {
            case R.id.play1plus1:
                id = R.id.play1health;
                health = magicians.get(0).changeHealth(1, id, "1");
                break;
            case R.id.play2plus1:
                id = R.id.play2health;
                health = magicians.get(1).changeHealth(1, id, "2");
                break;
            case R.id.play3plus1:
                id = R.id.play3health;
                health = magicians.get(2).changeHealth(1, id, "3");
                break;
            case R.id.play4plus1:
                id = R.id.play4health;
                health = magicians.get(3).changeHealth(1, id, "4");
                break;
            case R.id.play1plus5:
                id = R.id.play1health;
                health = magicians.get(0).changeHealth(5, id, "1");
                break;
            case R.id.play2plus5:
                id = R.id.play2health;
                health = magicians.get(1).changeHealth(5, id, "2");
                break;
            case R.id.play3plus5:
                id = R.id.play3health;
                health = magicians.get(2).changeHealth(5, id, "3");
                break;
            case R.id.play4plus5:
                id = R.id.play4health;
                health = magicians.get(3).changeHealth(5, id, "4");
                break;
            case R.id.play1minus1:
                id = R.id.play1health;
                health = magicians.get(0).changeHealth(-1, id, "1");
                break;
            case R.id.play2minus1:
                id = R.id.play2health;
                health = magicians.get(1).changeHealth(-1, id, "2");
                break;
            case R.id.play3minus1:
                id = R.id.play3health;
                health = magicians.get(2).changeHealth(-1, id, "3");
                break;
            case R.id.play4minus1:
                id = R.id.play4health;
                health = magicians.get(3).changeHealth(-1, id, "4");
                break;
            case R.id.play1minus5:
                id = R.id.play1health;
                health = magicians.get(0).changeHealth(-5, id, "1");
                break;
            case R.id.play2minus5:
                id = R.id.play2health;
                health = magicians.get(1).changeHealth(-5, id, "2");
                break;
            case R.id.play3minus5:
                id = R.id.play3health;
                health = magicians.get(2).changeHealth(-5, id, "3");
                break;
            case R.id.play4minus5:
                id = R.id.play4health;
                health = magicians.get(3).changeHealth(-5, id, "4");
                break;
        }
        TextView txtHealth = (TextView) findViewById(id);
        txtHealth.setText("" + health);
    }

    @Override
    protected void onStart(){
        super.onStart();

        for(Button btn : buttons){
            btn.setOnClickListener(this);
            btn.setHeight(height / 10);
            btn.setWidth(width / 10);
        }

        for(int i = 0; i < magicians.size(); i++){
            scores.get(i).setText("" + magicians.get(i).getHealth());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle state){
           state.putParcelableArrayList("magicians", magicians);
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


    private class Magician implements Parcelable{
        private int health;
        private boolean isAlive;

        public Magician(){
            this.health = 20;
            this.isAlive = true;
        }

        public int changeHealth(int change, int id, String player){
            if(isAlive) {
                this.health += change;
                if (this.health <= 0) {
                    this.isAlive = false;
                    TextView txtDeath = (TextView) findViewById(R.id.death);
                    txtDeath.setText(getString(R.string.death, player));
                }
            }
            return this.health;
        }

        public int getHealth(){
            return this.health;
        }

        public boolean isAlive(){
            return this.isAlive;
        }

        @Override
        public int describeContents() {
            return this.hashCode();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(this.health);
        }
    }
}
