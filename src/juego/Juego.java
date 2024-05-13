package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	Jugador layka;
	Manzana[] manzanitas;
	Auto[] autitos;
	EnemigoPlanta[] flowey;
	Image fondo;
	Image vida;
	ArrayList<Proyectil> proyectilesJugador = new ArrayList<Proyectil>();
	ArrayList<Proyectil> proyectilesPlanta = new ArrayList<Proyectil>();
	
	int vidasJugador = 3;
	int puntaje = 0;
	int enemigosDerrotados = 0;
	
	int posXPlanta0 = 10;
	int posYPlanta0 = 320;
	int posXPlanta1 = 1065;
	int posYPlanta1 = 350;
	int posXPlanta2 = 20;
	int posYPlanta2 = 915;
	int posXPlanta3 = 382;
	int posYPlanta3 = 400;
	
	int posXAuto0 = 550;
	int posYAuto0 = 45;
	int posXAuto1 = 705;
	int posYAuto1 = 300;
	int posXAuto2 = 550;
	int posYAuto2 = 640;
	
	long cooldownJugador = 0L;
	long cooldownPlanta0 = 0L;
	long cooldownPlanta1 = 0L;
	long cooldownPlanta2 = 0L;
	long cooldownPlanta3 = 0L;
	
	int respawnPlanta0 = 100;
	int respawnPlanta1 = 100;
	int respawnPlanta2 = 100;
	int respawnPlanta3 = 100;
	
	int respawnAuto0 = 100;
	int respawnAuto1 = 100;
	int respawnAuto2 = 100;
	
	int respawnJugador = 200;
	long currentTime;
	
	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Plantas Invasoras", 1100, 950);
		
		// Inicializar lo que haga falta para el juego
		// ...
		
		fondo = Herramientas.cargarImagen("fondo.jpg");
		vida = Herramientas.cargarImagen("corazon.png");
		layka = new Jugador(550,920);
		manzanitas = new Manzana[9];
		int posXManzana= 210;
		int posYManzana= 175;
		for(int i = 0; i<manzanitas.length; i++) {
			manzanitas[i] = new Manzana(posXManzana,posYManzana);
			if(posXManzana <= entorno.ancho()-100) {
				posXManzana += 343;
			}
			if(posXManzana > entorno.ancho()-100) {
				posXManzana = 210;
				posYManzana += 300;
			}
			
		}
		
		autitos = new Auto[3];
			
		autitos[0] = new Auto(posXAuto0, posYAuto0, 0);
		autitos[1] = new Auto(posXAuto1, posYAuto1, 1);
		autitos[2] = new Auto(posXAuto2, posYAuto2, 2);
		
		
		
		flowey = new EnemigoPlanta[4];
		
		flowey[0] = new EnemigoPlanta(posXPlanta0, posYPlanta0, 0);
		flowey[1] = new EnemigoPlanta(posXPlanta1, posYPlanta1, 1);
		flowey[2] = new EnemigoPlanta(posXPlanta2, posYPlanta2, 2);
		flowey[3] = new EnemigoPlanta(posXPlanta3, posYPlanta3, 3);
			
		

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		currentTime = System.currentTimeMillis();
		
		
		for(Auto a : this.autitos) {
			if(a != null) {
				autitos[a.direccion].moverse();
			}
		}
		
		for(Auto a : this.autitos) {
			if((a != null) && (a.direccion==2 || a.direccion==0) && casiColisionAuto(a)) {
				a.debeFrenar=true;
			} else if((a != null) && a.debeFrenar==true) {
				a.debeFrenar=false;
			}
				
		}
		
		for(EnemigoPlanta p : this.flowey) {
			if(p != null) {
				flowey[p.direccion].moverse();
			}
		}
		
		for(EnemigoPlanta p : this.flowey) {
			if((p != null) && (p.direccion==2 || p.direccion==0) && casiColisionPlanta(p)) {
				p.debeFrenar=true;
			} else if((p != null) && p.debeFrenar==true) {
				p.debeFrenar=false;
			}
				
		}
		
		
		
		
		if((layka == null) && respawnJugador > 0) {
			respawnJugador--;
		}else {
			if(layka == null && vidasJugador > 0) {
				layka = new Jugador(550, 920);
				respawnJugador = 200;
			}
		}
		
		
		
		
		if((autitos[0] == null) && respawnAuto0 > 0) {
			respawnAuto0--;
		}else {
			if(autitos[0] == null) {
				autitos[0] = new Auto(posXAuto0+450, posYAuto0, 0);
				respawnAuto0 = 100;
			}
		}
		
		if((autitos[1] == null) && respawnAuto1 > 0) {
			respawnAuto1--;
		}else {
			if(autitos[1] == null) {
				autitos[1] = new Auto(posXAuto1, posYAuto1-400, 1);
				respawnAuto1 = 100;
			}
		}
		
		if((autitos[2] == null) && respawnAuto2 > 0) {
			respawnAuto2--;
		}else {
			if(autitos[2] == null) {
				autitos[2] = new Auto(posXAuto2-600, posYAuto2, 2);
				respawnAuto2 = 100;
			}
		}
		
		
		
			
		if((flowey[0] == null) && respawnPlanta0 > 0) {
			respawnPlanta0--;
		}else {
			if(flowey[0] == null) {
				flowey[0] = new EnemigoPlanta(posXPlanta0-50, posYPlanta0, 0);
				respawnPlanta0 = 100;
			}
		}
		
		if((flowey[1] == null) && respawnPlanta1 > 0) {
			respawnPlanta1--;
		}else {
			if(flowey[1] == null) {
				flowey[1] = new EnemigoPlanta(posXPlanta1, posYPlanta1-400, 1);
				respawnPlanta1 = 100;
			}
		}
		
		if((flowey[2] == null) && respawnPlanta2 > 0) {
			respawnPlanta2--;
		}else {
			if(flowey[2] == null) {
				flowey[2] = new EnemigoPlanta(posXPlanta2-55, posYPlanta2, 2);
				respawnPlanta2 = 100;
			}
		}
		
		if((flowey[3] == null) && respawnPlanta3 > 0) {
			respawnPlanta3--;
		}else {
			if(flowey[3] == null) {
				flowey[3] = new EnemigoPlanta(posXPlanta3, posYPlanta3+600, 3);
				respawnPlanta3 = 100;
			}
		}
		
		
		
		if(currentTime - cooldownPlanta0 >= 1500) {
			dispararPlanta(flowey[0]);
			cooldownPlanta0 = currentTime;
		}
		if(currentTime - cooldownPlanta1 >= 1500) {
			dispararPlanta(flowey[1]);
			cooldownPlanta1 = currentTime;
		}
		if(currentTime - cooldownPlanta2 >= 1500) {
			dispararPlanta(flowey[2]);
			cooldownPlanta2 = currentTime;
		}
		if(currentTime - cooldownPlanta3 >= 1500) {
			dispararPlanta(flowey[3]);
			cooldownPlanta3 = currentTime;
		}
		
		
		if ((layka != null) && entorno.estaPresionada(entorno.TECLA_DERECHA) && (!entorno.estaPresionada(entorno.TECLA_ABAJO) && !entorno.estaPresionada(entorno.TECLA_ARRIBA)) && colisionMultipleManzana(manzanitas, layka)!=1) {
			layka.mover(2);

		}
		
		if ((layka != null) && entorno.estaPresionada(entorno.TECLA_ARRIBA) && (!entorno.estaPresionada(entorno.TECLA_DERECHA) && !entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) && colisionMultipleManzana(manzanitas, layka)!=0) {
			layka.mover(3);

		}	
		
		if ((layka != null) && entorno.estaPresionada(entorno.TECLA_ABAJO) && (!entorno.estaPresionada(entorno.TECLA_DERECHA) && !entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) && colisionMultipleManzana(manzanitas, layka)!=2) {
			layka.mover(1);

		}
		
		if ((layka != null) && entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && (!entorno.estaPresionada(entorno.TECLA_ABAJO) && !entorno.estaPresionada(entorno.TECLA_ARRIBA)) && colisionMultipleManzana(manzanitas, layka)!=3) {
			layka.mover(0);

		}
		
		if((layka != null) && entorno.estaPresionada(entorno.TECLA_ESPACIO) && currentTime - cooldownJugador >= 500) {
			   dispararJugador();
			   cooldownJugador = currentTime;
		}
		
		entorno.dibujarImagen(fondo, 550, 465, 0, 1);
		
		int posicion = 520;
		for(int i = 0; i < vidasJugador; i++) {
			entorno.dibujarImagen(vida, posicion, 11, 0, 0.07);
			posicion+= 30;
			if(i == 3) {
				posicion = 520;
			}
		}
		
		
		entorno.cambiarFont("Arial black", 16, Color.black);
		entorno.escribirTexto("Vidas: ", 450, 15);
		entorno.escribirTexto("Tu puntaje es: " + puntaje, 870, 15);
		entorno.escribirTexto("Enemigos derrotados: " + enemigosDerrotados, 80, 15);
		
		
		for(int i = 0; i < proyectilesJugador.size(); i++) {
			if(!proyectilFueraPantalla(proyectilesJugador.get(i)) && !proyectilChocaConOtro(proyectilesJugador.get(i)) && !proyectilChocaAuto(proyectilesJugador.get(i)) && !proyectilChocaPlanta(proyectilesJugador.get(i)) && !proyectilChocaManzana(proyectilesJugador.get(i))) {
				proyectilesJugador.get(i).dibujarseJugador(this.entorno);
				proyectilesJugador.get(i).mover();
			}
			else {
				proyectilesJugador.remove(i);
			}
			
		}
		
		dibujarManzanas(manzanitas);
		
		for(int i = 0; i < autitos.length; i++) {
			if(destruirAuto(autitos[i])) {
				autitos[i] = null;
			}
		}
		
		if(jugadorChocaProyectil() || colisionJugadorAuto() || colisionJugadorEnemigo()) {
			vidasJugador--;
			layka = null;
		}
		
		if(layka != null) {
			layka.dibujarse(this.entorno);
		}
		
		
		for(int j = 0; j < proyectilesPlanta.size(); j++) {
			if(!proyectilFueraPantalla(proyectilesPlanta.get(j))) {
				proyectilesPlanta.get(j).dibujarsePlanta(this.entorno);
				proyectilesPlanta.get(j).mover();
			}
			else {
				proyectilesPlanta.remove(j);
			}
		}
		
		dibujarPlantas(flowey);
		dibujarAutos(autitos);
	}
		

	public void dibujarPlantas(EnemigoPlanta[] flowey) {
		for(int i = 0; i < flowey.length; i++) {
			if(flowey[i] != null) {
				flowey[i].dibujarse(this.entorno);
			}
		}
	}

	public void dibujarAutos(Auto[] autitos) {
		for(int i = 0; i < autitos.length; i++) {
			if(autitos[i] != null) {
				autitos[i].dibujarse(this.entorno);
			}
		}
	}
	
	public void dibujarManzanas(Manzana[] manzanitas) {
		for(int i=0; i<manzanitas.length; i++) {
			manzanitas[i].dibujarse(this.entorno);
		}
	}
	
	public void dispararJugador() {
		if(layka != null) {
			Proyectil estrella = new Proyectil(layka.x, layka.y,5, layka.direccion, 0);
			proyectilesJugador.add(estrella);
		}
	}
	
	public void dispararPlanta(EnemigoPlanta p) {
		if(p!= null) {
			Proyectil misil = new Proyectil(p.x, p.y, 6, p.direccion, 1);
			proyectilesPlanta.add(misil);
		}
	}
	
	public int colisionManzana (Manzana m, Jugador l) {
		double zona1 = m.x-(m.ancho/2);
		double zona3 = m.x+(m.ancho/2);
		double zona2 = m.y-(m.alto/2);
		double zona0 = m.y+(m.alto/2);
		
		
		if((l != null) && l.y > zona2-75 && l.y < zona0+75 && l.x>zona1-90 && l.x<zona3+75) {
			return 1;
		}
		
		if((l != null) && l.x > zona1-70 && l.x < zona3+70 && l.y>zona2-80 && l.y<zona0+70) {
			return 2;
		}
		if((l != null) && l.x > zona1-70 && l.x < zona3+70 && l.y>zona2-70 && l.y<zona0+80) {
			return 0;
		}
		if((l != null) && l.x > zona1-75 && l.x < zona3+90 && l.y>zona2-75 && l.y<zona0+75) {
			return 3;
		}
		return 5;
		
	}
	
	public int colisionMultipleManzana(Manzana[] m, Jugador l) {
		for(int i= 0; i<m.length; i++) {
			if((l != null) && colisionManzana(m[i],l) != 5) {
				return colisionManzana(m[i],l);
			}
		}
		return 5;
	}
	
	public boolean casiColisionAuto(Auto auto) {
		for(Auto autoComparado : autitos) {
			if(autoComparado!=auto && autoComparado != null) {
				if(auto.x>=autoComparado.x-60 && auto.x<=autoComparado.x+60 && auto.y<=autoComparado.y + 120 && auto.y>=autoComparado.y -80) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean casiColisionPlanta(EnemigoPlanta planta) {
		for(EnemigoPlanta plantaComparada : flowey) {
			if(plantaComparada!=planta && plantaComparada != null) {
				if(planta.x>=plantaComparada.x-60 && planta.x<=plantaComparada.x+60 && planta.y<=plantaComparada.y + 120 && planta.y>=plantaComparada.y -80) {
					return true;
				}
			}
		}
		return false;
	}
	
    public boolean colisionJugadorAuto() {
        for (Auto a : autitos) {
            double radioColision = 25.0;
            if ((layka != null && a != null) && Math.abs(layka.x - a.x) < radioColision && Math.abs(layka.y - a.y) < radioColision) {
                return true;
            }
        }
        return false;
    }

    public boolean colisionJugadorEnemigo() {
        for (EnemigoPlanta e : flowey) {
            double radioColision = 25.0;
            if ((layka != null && e != null) && Math.abs(layka.x - e.x) < radioColision && Math.abs(layka.y - e.y) < radioColision) {
                return true;
            }
        }
        return false;
    }
	
	
	public boolean proyectilFueraPantalla(Proyectil p) {
    	if (p.x>1150) {
			return true;
		}
		if (p.x<-50) {
			return true;
		}
		if(p.y>1000) {
			return true;
		}
		if(p.y<-50) {
			return true;
		}
		return false;
    }
	
	public boolean proyectilChocaConOtro(Proyectil j) {
		for(Proyectil p : this.proyectilesPlanta) {
			double radioColision = 25.0;
			if(Math.abs(j.x - p.x) < radioColision && Math.abs(j.y - p.y) < radioColision) {
				proyectilesPlanta.remove(p);
				return true;
			}
		}
		return false;
	}
	
	public boolean proyectilChocaAuto(Proyectil j) {
		for(Auto a : this.autitos) {
			double radioColision = 25.0;
			if((a != null) && Math.abs(j.x - a.x) < radioColision && Math.abs(j.y - a.y) < radioColision) {
				return true;
			}
		}
		return false;
	}
	
	public boolean proyectilChocaPlanta(Proyectil j) {
		for(int i = 0; i < flowey.length; i++) {
			double radioColision = 25.0;
			if((flowey[i] != null) && Math.abs(j.x - flowey[i].x) < radioColision && Math.abs(j.y-flowey[i].y) < radioColision) {
				puntaje += 5;
				enemigosDerrotados++;
				flowey[i] = null;
				return true;
			}
		}
		return false;
	}
	
	public boolean proyectilChocaManzana(Proyectil j) {
		for(Manzana m : this.manzanitas) {
			double radioColision = 125.0;
			if(Math.abs(j.x - m.x) < radioColision && Math.abs(j.y - m.y) < radioColision) {
				return true;
			}
		}
		return false;
	}
	
	public boolean jugadorChocaProyectil() {
		for(Proyectil p : this.proyectilesPlanta) {
			double radioColision = 25.0;
			if((layka != null) && Math.abs(layka.x - p.x) < radioColision && Math.abs(layka.y - p.y) < radioColision) {
				proyectilesPlanta.remove(p);
				return true;
			}
		}
		return false;
	}
	
	public boolean destruirAuto(Auto a) {
		double radioColision = 25.0;
		for(Proyectil p : this.proyectilesPlanta) {
			if((a != null) && Math.abs(a.x - p.x) < radioColision && Math.abs(a.y - p.y) < radioColision) {
				return true;
			}
		}
		for(EnemigoPlanta e : this.flowey) {
			if((a != null && e != null) && Math.abs(a.x - e.x) < radioColision && Math.abs(a.y - e.y) < radioColision) {
				return true;
			}
		}
		return false;
	}
	
	

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
