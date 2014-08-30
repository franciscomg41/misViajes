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
				"Andrea Yoshira Carre�n Dom�nguez",
				"Faviola Ort�z Morales",
				"Gladys Salom�n Mart�nez",
				"Josue P�rez Nolasco",
				"Juan Francisco Mart�nez Garc�a",
				"Julio Cesar Villafuerte Toledo",
				"Selene Lizette Rodr�guez Trujillo"};
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, developers);
        listViewDevelopers.setAdapter(adapter);
	}
}