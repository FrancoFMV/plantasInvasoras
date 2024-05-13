package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Manzana {
	
	double x;
	double y;
	Image imagen;
	double ancho;
	double alto;
	double escala;

	
	public Manzana(double x, double y) {
		
		this.escala = 0.3;
		this.x = x;
		this.y = y;
		
		imagen = Herramientas.cargarImagen("manzana.jpg");
		this.ancho=imagen.getWidth(null)*this.escala;
		this.alto=imagen.getHeight(null)*this.escala;
	}
	
	public void dibujarse(Entorno entorno) {
		
		entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.7);
	}
	
}
