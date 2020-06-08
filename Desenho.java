package com.example.testecanvas;

import java.text.AttributedCharacterIterator.Attribute;
import java.util.Random;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class Desenho extends View implements Runnable{

    float bola_posx =0;
 	float bola_posy = 0;
 	float bola_deslocamentox = 0;
 	float bola_deslocamentoy = 0;
 	
 	float jog1_posx =0;
 	float jog1_posy = 0;
 	float jog1_deslocamentox = 0;
 	float jog1_deslocamentoy = 0;
 	
 	
 	float jog2_posx =0;
 	float jog2_posy = 0;
 	float jog2_deslocamentox = 0;
 	float jog2_deslocamentoy = 0;
 	
 	static int contador_pontos_j1 = 0;
 	static int contador_pontos_j2 = 0;
 	
 	
 	float bola_centrox = 0;
 	float bola_centroy = 0;
 	float bola_raio = 0;
 	
 	
 	//variaveis de posição
 	
 	
   
   private Paint pincel;
   private Paint pincel2;
   private static int INTERVALO = 10;
   Random randomico = new Random();
   boolean variaveis_iniciadas = false;
   boolean clicado = false; 
   boolean rodando = true;
   Opcoes op = new Opcoes();

   public Desenho(Context context) {
		super(context);
        pincel = new Paint();
	    pincel.setColor(Color.WHITE);
	    setBackgroundColor(Color.BLACK);
	    pincel2 = new Paint();
	    pincel2.setColor(Color.RED);
	    Thread minhathread = new Thread(this);
	    minhathread.setPriority(Thread.MIN_PRIORITY);
	    minhathread.start();
	
	}
	
	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);

		if(variaveis_iniciadas == false){
			bola_deslocamentoy=getHeight()/15-(getHeight()/45);
			bola_deslocamentox = getHeight()/20;
			bola_posx= getWidth()/2;
			bola_posy=getHeight()-(3*getHeight()/20); 
		}
		
		//jogador2
		canvas.drawRect(jog2_posx+(getWidth()/2-getWidth()/8),    2*getHeight()/20,    jog2_posx+(getWidth()/2-getWidth()/8)+getWidth()/4,   getHeight()/20+getHeight()/40   ,   pincel);
		
		//jogador1
		canvas.drawRect(jog1_posx+(getWidth()/2-getWidth()/8)  ,   getHeight()-(2*getHeight()/20)    ,   jog1_posx+(getWidth()/2-getWidth()/8)+getWidth()/4,  getHeight()-(getHeight()/20)-getHeight()/40 , pincel   );
		
		//bola
		canvas.drawRect(bola_posx, bola_posy,bola_posx+getHeight()/20 , bola_posy+getHeight()/20 , pincel);
	
		//contador j1
		canvas.drawText(""+contador_pontos_j1, getHeight()/40, getHeight()-(4*getHeight()/20), pincel);
		
		//contador j2
		canvas.drawText(""+contador_pontos_j2, getHeight()/40, 4*getHeight()/20, pincel);

	
	//separador
	
		canvas.drawRect((getWidth()/11)*0, getHeight()/2-getHeight()/80, (getWidth()/11)*1, getHeight()/2+getHeight()/80, pincel);

		canvas.drawRect((getWidth()/11)*2, getHeight()/2-getHeight()/80, (getWidth()/11)*3, getHeight()/2+getHeight()/80, pincel);
		
		canvas.drawRect((getWidth()/11)*4, getHeight()/2-getHeight()/80, (getWidth()/11)*5, getHeight()/2+getHeight()/80, pincel);
		
		canvas.drawRect((getWidth()/11)*6, getHeight()/2-getHeight()/80, (getWidth()/11)*7, getHeight()/2+getHeight()/80, pincel);
		
		canvas.drawRect((getWidth()/11)*8, getHeight()/2-getHeight()/80, (getWidth()/11)*9, getHeight()/2+getHeight()/80, pincel);

		canvas.drawRect((getWidth()/11)*10, getHeight()/2-getHeight()/80, (getWidth()/11)*11, getHeight()/2+getHeight()/80, pincel);
	
		variaveis_iniciadas = true;
		
	}
	
 
    public boolean onTouchEvent(MotionEvent evento){
    	
    	switch(evento.getAction()){

    	case MotionEvent.ACTION_DOWN:
    	{
	    	if(evento.getX() >= jog1_posx+(getWidth()/2-getWidth()/8) & evento.getX() <= jog1_posx+(getWidth()/2-getWidth()/8)+getWidth()/4 ){
	    		clicado = true;
    		}	
    		break;
    	}
    	case MotionEvent.ACTION_MOVE:
    	{
    		if(clicado == true & evento.getX() >= getWidth()/16 & evento.getX() <= getWidth()-getWidth()/16){
    			jog1_posx = evento.getX()-(getWidth()/2-getWidth()/8)-getWidth()/8;
    		}	
    	 	break;
    	}
    	case MotionEvent.ACTION_UP:
    	{
         	clicado = false;
    	 	break; 
    	}
    	
    	} // fim do switch
    	
    	invalidate();
    	return true;	
    }

	@Override
	public void run() {

		while(rodando){
			try {
				Thread.sleep(INTERVALO);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			update_bola();
		    update_jogador2();
	  
		}
	}
	
	public void update_bola(){
	
	    if(bola_posx < getHeight()/20 & bola_deslocamentox < 0){
	    	bola_deslocamentox = bola_deslocamentox*-1;
	    	bola_posx = 1;
	    }
	
	    
	    if( bola_posx >getWidth()-(getHeight()/20)-(getHeight()/20) & bola_deslocamentox  > 0  ){
		    	bola_deslocamentox = bola_deslocamentox*-1;
		    	bola_posx = getWidth()-(getHeight()/20);
	    }
	    
	    if(bola_posy < getHeight()/20 & bola_deslocamentoy < 0){
	    	
	    	/*bola_deslocamentoy = 5;*/
	    	bola_deslocamentoy = bola_deslocamentoy*-1;
	    	
	    	bola_posy = 1;
	        contador_pontos_j1++;
	    	   
	    }
	    
	    if( bola_posy >getHeight()-(getHeight()/20)-(getHeight()/20) & bola_deslocamentoy > 0  ){
	    	
	    	bola_deslocamentoy = bola_deslocamentoy*-1;
	    	bola_posy = getHeight()-(getHeight()/20);
	    	contador_pontos_j2++;
	   
	    }
	
	    if(contador_pontos_j1 == 5){
	    	
	    	bola_posx= getWidth()/2;
			bola_posy=getHeight()-(3*getHeight()/20); 
			jog1_posx = 0;
			jog1_posy = 0;
			contador_pontos_j1 = 0;
			contador_pontos_j2 = 0;
	    }
			    
		if(contador_pontos_j2 == 5){
			    	
			bola_posx= getWidth()/2;
			bola_posy=getHeight()-(3*getHeight()/20); 
			jog1_posx = 0;
			jog1_posy = 0;
			contador_pontos_j1 = 0;
			contador_pontos_j2 = 0;
	    }
	    
 // colisão jogador1
 		if(bola_posx >= jog1_posx+(getWidth()/2-getWidth()/8) & bola_posx <= jog1_posx+(getWidth()/2-getWidth()/8)+getWidth()/4 & bola_posy >= getHeight()-(3*getHeight()/20) /*& bola_posy < getHeight()-(getHeight()/20)*/){
		 	if(bola_deslocamentoy >=0){
			 	bola_deslocamentoy = bola_deslocamentoy*-1;
				bola_posy = getHeight()-(3*getHeight()/20);
				bola_deslocamentox = randomico.nextInt(12)+(int)(getHeight()/40);
			  //mudança condicional da variavel x
			  if(randomico.nextInt(2)==0){
				  bola_deslocamentox = bola_deslocamentox*-1;
			  }
	 }
 }
 
 
//colisão jogador 2
 if(bola_posx >= jog2_posx+(getWidth()/2-getWidth()/8) & bola_posx <= jog2_posx+(getWidth()/2-getWidth()/8)+getWidth()/4 & bola_posy <= 2*getHeight()/20  /*& bola_posy >= getHeight()/20*/){

	

	 if(bola_deslocamentoy <=0){
	 
		
		 bola_deslocamentoy = bola_deslocamentoy*-1;
		  bola_posy = 2*getHeight()/20;
		 
		  
		  //mudança da velocidade x
		  
		  bola_deslocamentox = randomico.nextInt(12)+(int)(getHeight()/40);
		  
		  //mudança condicional da variavel x
		  if(randomico.nextInt(2)==0){
			  
			  bola_deslocamentox = bola_deslocamentox*-1;
			  
		  }
		 
	 
	 }

  }
 
 postInvalidate();

bola_posx += bola_deslocamentox;
bola_posy += bola_deslocamentoy;
}
	
	
public void update_jogador2(){
		
	if(jog2_posx+(getWidth()/2-getWidth()/8)+(2*getWidth()/20) > bola_posx  &   jog2_posx+(getWidth()/2-getWidth()/8) > 0 &  bola_deslocamentoy < 0){
		jog2_posx-= getHeight()/25;	
	}
	else if(jog2_posx+(getWidth()/2-getWidth()/8)+(2*getWidth()/20) < bola_posx   &  jog2_posx+(getWidth()/2-getWidth()/8)+getWidth()/4 < getWidth()   &  bola_deslocamentoy < 0 ){
			jog2_posx+= getHeight()/25;
		}
	}
	public void finalizar(){
		rodando =false;
	}
}
