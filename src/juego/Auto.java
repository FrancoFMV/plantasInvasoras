package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Auto {
	
	double x;
	double y;
	double ancho;
	double alto;
	double escala;
	Image[] imagen;
	int direccion;
	double velocidad = 7;
	boolean debeFrenar;
	
	
	public Auto(double x, double y, int direccion) {
		
		this.escala = 0.3;
		this.x = x;
		this.y = y;
		this.direccion = direccion;
		this.imagen = new Image[3];
		boolean debeFrenar = false;
		
		for(int i = 0; i < imagen.length; i++) {
			
			imagen[i] = Herramientas.cargarImagen("auto" + i + ".png");
			this.ancho=imagen[i].getWidth(null)*this.escala;
			this.alto=imagen[i].getHeight(null)*this.escala;
		}
	}

		
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(imagen[this.direccion], this.x, this.y, 0, 0.10);
	}
	
	public void moverse() {
		if(direccion == 0 && !debeFrenar) {
			x-= velocidad;
		}
		if(direccion == 1 && !debeFrenar) {
			y+= velocidad;
		}
		if(direccion == 2 && !debeFrenar) {
			x+= velocidad;
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
