package problems;

import java.util.*;

class VueltaAtras{

	public static Vector<String> camino = new Vector<String>();
	public static int mps[][];
	public static boolean hayCamino = false; //validar si hay camino

	//70% = 1 30% = 0;
	private boolean porcentaje(int uno){
		// uno = porcentaje de que salga uno sobre un
		// algoritmo aleatorio de 0 / 100, siendo el uno el 
		// numero maximo para que sea 1
		Random r = new Random();
		if(r.nextInt(101) <= uno) return true; // 1
		else return false; // 0
	}
	
	//funcion para crear matriz de 0 y 1
	protected int[][] crearMatriz(int t){
		int [][] matriz = new int [t][t];
		for(int f = 0; f < t; f++){
			for(int c = 0; c < t; c++){ //matriz cuadrada
				if(f == 0 && c == 0 || f == t-1 && c == t-1)
					matriz[f][c] = 1;
				else{
					if(porcentaje(70)) matriz[f][c] = 1;
					else matriz[f][c] = 0;
				}
			}
		}
		return matriz;
	}
	
	//esta matriz sera usada para encontrar casillas ya pisadas
	protected int[][] crearMatrizCero(int t){
		int [][] m = new int[t][t];
		for(int f = 0; f < t; f++)
			for(int c = 0; c < t; c++) m[f][c] = 0;
		return m;
	}
	
	//iniciar funcion encontrarCamino con valores por defecto
	protected void buscaCamino(int m[][]){
		Vector<String> v = new Vector<String>();
		int mp[][] = crearMatrizCero(m.length);
		encontrarCamino(m,0,0,v,mp);
	}
	
	private void copiarMatriz(int m1[][], int m2[][]){
		for(int f = 0; f < m1.length; f++){
			for(int c = 0; c < m1.length; c++){
				m1[f][c] = m2[f][c];
			}
		}
	}
	
	protected void encontrarCamino(int m[][],int f, int c, Vector<String> vc, int mp[][]){
		vc.add(f + "," + c + "|");
		/*System.out.println("Vector = " + vc);
		for(int i = 0; i < mp.length; i++){
			for(int j = 0; j < mp.length; j++){
				System.out.print(mp[i][j] + " ");
			}
			System.out.print("\n");
		}*/
		mp[f][c] = 1; //casilla pisada por cada buscador
		if(f == m.length-1 && f == c && !hayCamino){
			this.camino = vc;
			this.hayCamino = true;
		}
		else{
			//caminar abajo
			if(f < m.length-1){
				if(m[f+1][c] == 1 && mp[f+1][c] == 0){
					int t = mp.length;
					int mpn [][] = new int [t][t];
					copiarMatriz(mpn,mp);
					Vector<String> vcn = new Vector<String>();
					vcn.addAll(vc);
					encontrarCamino(m,f+1,c,vcn,mpn);
				}
			}
			//caminar derecha
			if(c < m.length-1){
				if(m[f][c+1] == 1 && mp[f][c+1] == 0){
					int t = mp.length;
					int mpn [][] = new int [t][t];
					copiarMatriz(mpn,mp);
					Vector<String> vcn = new Vector<String>();
					vcn.addAll(vc);
					encontrarCamino(m,f,c+1,vcn,mpn);
				}
			}
			//caminar arriba
			if(f > 0){
				if(m[f-1][c] == 1 && mp[f-1][c] == 0){
					int t = mp.length;
					int mpn [][] = new int [t][t];
					copiarMatriz(mpn,mp);
					Vector<String> vcn = new Vector<String>();
					vcn.addAll(vc);
					encontrarCamino(m,f-1,c,vcn,mpn);
				}
			}
			//caminar izquierda
			if(c > 0){
				if(m[f][c-1] == 1 && mp[f][c-1] == 0){
					int t = mp.length;
					int mpn [][] = new int [t][t];
					copiarMatriz(mpn,mp);
					Vector<String> vcn = new Vector<String>();
					vcn.addAll(vc);
					encontrarCamino(m,f,c-1,vcn,mpn);
				}
			}
		}
	}
	
	//---------------------------
	//Prueba con nodos
	//---------------------------
	
	private class Nodo{
		Nodo p; //previo
		Nodo s; //siguiente
		int f,c; //valores fila y columna respectivamente
		public Nodo(int f, int c){
			this.f = f;
			this.c = c;
		}
	}
	
	Nodo n; //Nodo inicial
	
	private boolean insertarN(Nodo n,int f,int c, int m[][]){
		/*Nodo nuevoN = new Nodo(f,c,);
		if(n == null){ //prueba de error
			n = nuevoN;
			n.s = null; //siguiente nodo == a null
		}*/
		//revisar en cada nodo todas las posibilidades
		//cada vez...
		//pared abajo derecha o retroceso
		Nodo nuevoN = null; //inicializacion por defecto
		boolean opValida = false;
		//abajo
		if(f < m.length-1){
			if(m[f+1][c] == 1 && this.mps[f+1][c] == 0){
				nuevoN = new Nodo(f+1,c);
				opValida = true;
			}
		}
		//derecha
		if(c < m.length-1 && !opValida){
			if(m[f][c+1] == 1 && this.mps[f][c+1] == 0){
				nuevoN = new Nodo(f,c+1);
				opValida = true;
			}
		}
		//arriba
		if(f > 0 && !opValida){
			if(m[f-1][c] == 1 && this.mps[f-1][c] == 0){
				nuevoN = new Nodo(f-1,c);
				opValida = true;
			}
		}
		//izquierda
		if(c > 0 && !opValida){
			if(m[f][c-1] == 1 && this.mps[f][c-1] == 0){
				nuevoN = new Nodo(f,c-1);
				opValida = true;
			}
		}
		if(opValida){
			this.mps[nuevoN.f][nuevoN.c] = 1;
			n.s = nuevoN;
			nuevoN.p = n;
			return true;
		}
		else return false; //no hubo movimiento
	}
	
	private void retroceder(Nodo n){ //no funcional? -> porque?
		n = n.p; //retroceso
		n.s = null; //cerrar nodo
	}
	
	protected void establecerCamino2(){
		Nodo nn = this.n; //copia de nodo uno
		while(nn != null){
			this.camino.add(nn.f + "," + nn.c + "|");
			nn = nn.s;
		}
	}
	
	protected void encontrarCamino2(int m[][]){
		this.mps = crearMatrizCero(m.length);
		this.n = new Nodo(0,0); //el nodo primero
		n.p = null; //revision inutil?
		boolean posib = true; //booleano para cerrar ciclo
		boolean inserP;
		Nodo nn = this.n; //copia de nodo para mover
		while(posib){
			inserP = insertarN(nn,nn.f,nn.c,m);
			if(inserP) nn = nn.s; //avanzar al nodo siguiente
			else{
				if(nn.p != null){ 
					//retroceder
					nn = nn.p;
					nn.s = null;
				}
				else return;
			}
			if(nn.f == m.length-1 && nn.c == nn.f){
				hayCamino = true;
				nn.s = null; // no hay mas nodos
				posib = false; //necesario?
				return;
			}
		}
	}

}
