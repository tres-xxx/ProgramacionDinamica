package problems;
import java.util.*;

class Probando{

	public static void pruebaDividirVencer(){
		DividirVencer d = new DividirVencer();
		
		
		System.out.println("Divide y venceras");
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingresar el tamano de las dos matrices");
		int tamM = scan.nextInt();
		
		//crear matrices
		int m1[][] = d.crearMatriz(tamM,10);
		int m2[][] = d.crearMatriz(tamM,10);
		
		//imprimir matriz 1
		System.out.println("Matriz 1");
		for(int i = 0; i < tamM; i++){
			for(int j = 0; j<tamM; j++){
				System.out.print(m1[i][j] + " ");
			}
			System.out.print("\n");
		}
		//imprimir matriz 2
		System.out.println("Matriz 2");
		for(int i = 0; i < tamM; i++){
			for(int j = 0; j<tamM; j++){
				System.out.print(m2[i][j] + " ");
			}
			System.out.print("\n");
		}
		//imprimir resultado
		int resultado[][];
		resultado = d.multMatriz(m1,m2);
		System.out.println("Resultado");
		for(int i = 0; i < tamM; i++){
			for(int j = 0; j < tamM; j++){
				System.out.print(resultado[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.println("////////////////////////");
		resultado = d.multMatrizII(m1,m2);
		System.out.println("Resultado");
		for(int i = 0; i < tamM; i++){
			for(int j = 0; j < tamM; j++){
				System.out.print(resultado[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	public static void pruebaVueltaAtras(){
		VueltaAtras va = new VueltaAtras();
		Scanner s = new Scanner(System.in);
		System.out.println("Ingresar tamano de la matriz");
		int t = s.nextInt();
		int m[][] = va.crearMatriz(t);
		//imprimir laberinto
		for(int f = 0; f < t; f++){
			for(int c = 0; c < t; c++){
				System.out.print(m[f][c] + " ");
			}
			System.out.print("\n");
		}
		//va.buscaCamino(m);
		va.encontrarCamino2(m);
		if(va.hayCamino == true){
			System.out.println("Ruta seguida");
			va.establecerCamino2();
			System.out.println(va.camino);
		}
		else 
			System.out.println("No hay camino");
	}
	
	public static void pruebaProgramacionDinamica(){
		ProgramacionDinamica pd = new ProgramacionDinamica();
		Scanner s = new Scanner(System.in);
		System.out.println("Ingresar numero de embarcaderos");
		int num = s.nextInt();
		
		//crear lista
		pd.crearTarifaAleatoria(num,100);
		pd.imprimirTarifa(num);
		
		System.out.println("Ingresar embarcadero inicial");
		int ei = s.nextInt();
		while(ei < 0 || ei > num){
			System.out.println("Embarcadero erroneo, vuelva a ingresar embarcadero inicial");
			ei = s.nextInt();
		}
		
		System.out.println("Ingresar embarcadero final");
		int ef = s.nextInt();
		while(ei < 0 || ei > num){
			System.out.println("Embarcadero erroneo, vuelva a ingresar embarcadero final");
			ef = s.nextInt();
		}
		
		pd.iniciarGenerarCaminos(ei,ef);
		pd.imprimirPrecioDinamico();
		pd.imprimirCaminoE();
	}
	
	public static void main(String [] args){
		//pruebaDividirVencer();
		//pruebaVueltaAtras();
		pruebaProgramacionDinamica();
	}
	
}
