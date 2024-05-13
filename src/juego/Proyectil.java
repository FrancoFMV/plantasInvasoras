package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {
	
	double x;
    double y;
    double velocidad;
    int direccion;
    Image imagenJ;
    Image imagenP;
    
    public Proyectil(double x, double y, double velocidad, int direccion, int tipo) {
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.direccion = direccion;
        
        if(tipo == 0) {
        	imagenJ = Herramientas.cargarImagen("proyectil" + tipo + ".png");
        }
        else {
        	imagenP = Herramientas.cargarImagen("proyectil" + tipo + ".png");
        }
    }
    
    public void mover() {
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
    }
    
    
    public void dibujarseJugador(Entorno entorno) {
		entorno.dibujarImagen(imagenJ, x, y, 0, 0.03);
    }
    
    public void dibujarsePlanta(Entorno entorno) {
    	entorno.dibujarImagen(imagenP, x, y, 0, 0.15);
    }
    
}

