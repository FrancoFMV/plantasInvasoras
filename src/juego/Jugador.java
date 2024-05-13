package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Jugador {
	
	double x;
	double y;
	int direccion;
	Image[] imagen;
	double velocidad = 4;
	
	
	public Jugador(double x, double y) {
		
		this.x = x;
		this.y = y;
		this.direccion = 0;
		this.imagen = new Image[4];
		
		for(int i = 0; i < imagen.length; i++) {
			
			imagen[i] = Herramientas.cargarImagen("dog" + i + ".png");
		}
	}
	
	public void dibujarse(Entorno entorno) {
		
		entorno.dibujarImagen(imagen[this.direccion], this.x, this.y, 0, 0.05);
	}
	
	public void mover(int d) {
		
		this.direccion = d;
		
		if(direccion == 0) {
			x-= velocidad;
		}
		if(direccion == 1) {
			y+= velocidad;
		}
		if(direccion == 2) {
			x+= velocidad;
		}
		if(direccion == 3) {
			y-= velocidad;
		}
		
		if (this.x>1080) {
			x=1080;
		}
		if (this.x<20) {
			x=20;
		}
		if(this.y>935) {
			y=935;
		}
		if(this.y<20) {
			y=20;
		}
	}
}
