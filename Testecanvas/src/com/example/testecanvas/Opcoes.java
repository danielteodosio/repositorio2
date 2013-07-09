package com.example.testecanvas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Opcoes extends Activity {

	protected Desenho des;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opcoes);
	
	    des = new Desenho(this);
	 Button jogar = (Button)findViewById(R.id.button1);
	
	
	
	  
	 
	 jogar.setOnClickListener(
			 
			 new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
		
					setContentView(des);
					
			/*Opcoes.this.startActivity(new Intent(Opcoes.this, MainActivity.class));
		Opcoes.this.finish();*/
					
				}
			}
			 
			 
			 
			 
			 
			 );
	
	 
	  
	 
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.opcoes, menu);
		return true;
	}

	
	
	protected void onDestroy(){
		
		super.onDestroy();
		
		des.finalizar();
		
	}
	
	
}
