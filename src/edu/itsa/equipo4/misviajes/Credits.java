package edu.itsa.equipo4.misviajes;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Credits extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_credits);
    	ListView listViewDevelopers = (ListView) findViewById(R.id.listDevelopers);    	
    	String[] developers = {
				"Andrea Yoshira Carreón Domínguez",
				"Faviola Ortíz Morales",
				"Gladys Salomón Martínez",
				"Josue Pérez Nolasco",
				"Juan Francisco Martínez García",
				"Julio Cesar Villafuerte Toledo",
				"Selene Lizette Rodríguez Trujillo"};
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, developers);
        listViewDevelopers.setAdapter(adapter);
	}
}