package edu.itsa.equipo4.misviajes;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyTravels extends Activity{
	ListView listViewMyTravels;
	//Creacion del objeto auxiliar de SQLite
	private SQLiteHelper helper = new SQLiteHelper(this);
	private String rol = "X";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytravels);
        
        //Creacion de variables en onCreate()
    	final Button btnnewtravel = (Button)findViewById(R.id.buttonNewTravel);
    	final TextView txtmytravels = (TextView)findViewById(R.id.textViewNumberOfTravels);
    	
    	Bundle extras = getIntent().getExtras();
    	if (extras != null){
    		rol = extras.getString("rol").toString();
    	}
    	
    	//Condicion para permisos de usuario
    	if (!rol.equals("0")){
    		RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)txtmytravels.getLayoutParams();
    		params.addRule(RelativeLayout.RIGHT_OF,0);
    		btnnewtravel.setVisibility(4);
    	}
    	
    	//Listeners
    	btnnewtravel.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			Intent newIntent = new Intent(v.getContext(), NewTravel.class);
    			newIntent.putExtra("travelId","0");
    			newIntent.putExtra("action","new");
    			startActivityForResult(newIntent,0);
    		}
    	});
    }
    
    @Override
    protected void onStart(){
    	super.onStart();
    	final TextView txtmytravels = (TextView)findViewById(R.id.textViewNumberOfTravels);
    	listViewMyTravels = (ListView) findViewById(R.id.listMyTravels);
    	
    	helper.open();
    	int numviajes = helper.getNumOfTravels();
    	helper.close();
    	
    	//Muestra N cantidad de viajes
    	if (numviajes!=1){
    		txtmytravels.setText("Tengo "+numviajes+" viajes");
    	}else{
    		txtmytravels.setText("Tengo "+numviajes+" viaje");
    	}
    	
    	//Condicion para mostrar viajes si es que hay
    	if (numviajes!=0){
    		//Cargo la lista de los viajes
    		final ArrayList<String> travels = new ArrayList<String>();
    		final ArrayList<String> ids = new ArrayList<String>();
    		helper.open();
    		Cursor ctrl = helper.getTravels();
    		helper.close();
    		if (ctrl.isFirst()) {
                //Loop de lectura de cada viaje 
                do {
                     String id = ctrl.getString(0);
                     String nombre = ctrl.getString(1);
                     String origen = ctrl.getString(2);
                     String destino = ctrl.getString(3);
                     String costo = ctrl.getString(7);
                     travels.add(nombre+"\n"+origen+" - "+destino+"\n$"+costo);
                     ids.add(id);
                } while (ctrl.moveToNext());
    		}
    		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, travels);
            listViewMyTravels.setAdapter(adapter); 
            
            //Listeners
            listViewMyTravels.setOnItemClickListener(new OnItemClickListener() {
            	@Override
            	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            		Intent newIntent = new Intent(view.getContext(), EachTravel.class);
            		newIntent.putExtra("rol",rol);
            		newIntent.putExtra("travelId",ids.get(position));
            		startActivityForResult(newIntent,0);
            	}
        	});
    	}
    }
}