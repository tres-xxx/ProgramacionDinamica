package problems;
import java.util.*;

class DividirVencer{
	
	protected static int matriz1[][];
	protected static int matriz2[][];
	
	//crear matriz de tamano t y devolverla
	//maxal => maximo numero aleatorio
	protected int [][]crearMatriz(int t, int maxal){ 
		int matriz[][] = new int [t][t];
		Random al = new Random();
		/* // No ceros
		for(int f = 0; f < t; f++)
			for(int c = 0; c < t; c++)
				matriz[f][c] = al.nextInt(maxal);
		*/
		for(int f = 0; f < t; f++){
			for(int c = 0; c < f; c++){ //revision llenado de ceros
				matriz[f][c] = 0;
			}
			for(int c = f; c < t; c++){
				matriz[f][c] = al.nextInt(maxal);
			}
		}
		return matriz;
	}
	
	//constructor por defecto => no utilizado en realidad...
	DividirVencer(){
		matriz1 = crearMatriz(4,10);
		matriz2 = crearMatriz(4,10);
	}
	
	//revisar que se pueda multiplicar
	boolean posibleMult(int m1[][], int m2[][]){
		//tomar en cuenta que son matrices cuadradas
		//por lo tanto no es necesario evaluar las columnas
		if(m1.length != m2.length) return false;
		return true;
	}
	
	private int [][]llenarCero(int t){
		int matriz[][] = new int[t][t]; //matriz cuadrada
		for(int i = 0; i < t; i++){
			for(int j = 0; j < t; j++){
				matriz[i][j] = 0;
			}
		}
		return matriz;
	}
	
	protected int [][]multMatriz(int m1[][], int m2[][]){
		int tam = m1.length; //considerar que son cuadradas f = c
		//int matriz [][] = new int [tam][tam]; // matriz a retornar
		int matriz[][] = llenarCero(tam); //llenar matriz de ceros
		//revisar posibilidad de multiplicacion
		if (!posibleMult(m1,m2)) return matriz; // retornar matriz vacia, no operacion
		int suma;
		
		int f1,f2,c1,c2;
		for(f1 = 0; f1 < tam; f1++){
			c2 = 0;
			for(int i = 0; i < tam; i++){ //cantidad de veces multiplicar por la otra matriz
				suma = 0;
				// f2 == c1 se puede remover por una sola variable
				// las dejo para tener claridad en la operacion
				for(c1 = f1, f2 = f1; c1 <= c2; c1++){
					suma += (m1[f1][c1] * m2[f2][c2]);
					f2++; // f2 cambia con limite c2 => matriz cuadrada
				}
				matriz[f1][i] = suma; // asignar valor de la suma a la matriz
				c2++;
			}			
		}
		return matriz;
	}

	protected int [][]multMatrizII(int m1[][], int m2[][]){
		//posible agrupar por submatrices de tamano dos
		if(m1.length%2 == 0){
			int tam = m1.length;
			int matriz[][] = llenarCero(tam); //matriz para realizar las sumas
			if(!posibleMult(m1,m2)) return matriz; //matriz vacia
			//for(int f1 = 0; f1 < tam/2; f1++){ //tam = numero de "submatrices" || 4 => 
			System.out.println("Ciclos de sumas");
			for(int f2=0; f2<tam; f2+=2){ //las matrices son cuadradas, movimientos ordenados
				//establecer matriz 1 y multiplicar tam/2 veces
				for(int c2 = 0; c2<tam; c2+=2){
					for(int f3=0; f3<tam; f3+=2){
						matriz[f2][f3] += ((m1[f2][c2]*m2[c2][f3])+(m1[f2][c2+1]*m2[c2+1][f3])); //primera fila
						matriz[f2][f3+1] += ((m1[f2][c2]*m2[c2][f3+1])+(m1[f2][c2+1]*m2[c2+1][f3+1])); //primera fila
						matriz[f2+1][f3] += ((m1[f2+1][c2]*m2[c2][f3])+(m1[f2+1][c2+1]*m2[c2+1][f3])); //segunda fila
						matriz[f2+1][f3+1] += ((m1[f2+1][c2]*m2[c2][f3+1])+(m1[f2+1][c2+1]*m2[c2+1][f3+1])); //segunda fila
						
						//impresion de prueba
						System.out.println("-------------------");
						for(int i = 0; i < tam; i++){
							for(int j = 0; j < tam; j++){
								System.out.print(matriz[i][j] + " ");
							}
							System.out.print("\n");
						}
						System.out.println("-------------------");
					}
				}
			}
			return matriz;		
		}
		else return multMatriz(m1,m2);
	}
	
}
