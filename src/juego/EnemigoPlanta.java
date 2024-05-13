package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class EnemigoPlanta {
	
	double x;
	double y;
	Image[] imagen;
	int direccion;
	double velocidad = 2;
	boolean debeFrenar;

	 public EnemigoPlanta( double x, double y, int direccion) {
	  this.x= x;
	  this.y= y;
	  this.direccion= direccion;
	  this.imagen = new Image[4];
	  boolean debeFrenar = false;
	  
	  for(int i = 0; i < imagen.length; i++) {
		  imagen[i]= Herramientas.cargarImagen("flowey" + i + ".png");
	  }
	 }
	 
	 public void dibujarse(Entorno entorno) {
	  entorno.dibujarImagen(imagen[this.direccion], this.x, this.y, 0, 0.06);
	 }

	 public void moverse () {
		
		 if(direccion == 0 && !debeFrenar) {
			x-= velocidad;
		}
		if(direccion == 1 && !debeFrenar) {
			y+= velocidad;
		}
		if(direccion == 2 && !debeFrenar) {
			x+= velocidad;
		}
		if(direccion == 3 && !debeFrenar) {
			y-= velocidad;
		}
		  
		if (this.x>1150) {
			x=-50;
		}
		if (this.x<-50) {
			x=1150;
		}
		if(this.y>1000) {
			y=-50;
		}
		if(this.y<-50) {
			y=1000;
		}
	 }
}
