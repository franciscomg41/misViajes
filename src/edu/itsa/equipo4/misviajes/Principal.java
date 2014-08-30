package edu.itsa.equipo4.misviajes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Principal extends Activity {
	//Creacion del objeto auxiliar de SQLite
	public SQLiteHelper helper = new SQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        
        //Creacion de variables en onCreate()
        final TextView txtAdmin = (TextView)findViewById(R.id.textViewAdminUser);
        final TextView txtGuest = (TextView)findViewById(R.id.textViewGuestUser);
        final TextView txtCredits = (TextView)findViewById(R.id.textViewCredits);
        
        //Listeners
        txtAdmin.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent newIntent = new Intent(v.getContext(), Login.class);
				startActivityForResult(newIntent,0);
			}
		});
        
        txtGuest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				helper.open();
				Intent newIntent = new Intent(v.getContext(), MyTravels.class);
				newIntent.putExtra("rol", helper.getRol("guest").toString());
				helper.close();
				startActivity(newIntent);
			}
		});
        
        txtCredits.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent newIntent = new Intent(v.getContext(), Credits.class);
				startActivity(newIntent);
			}
		});
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}