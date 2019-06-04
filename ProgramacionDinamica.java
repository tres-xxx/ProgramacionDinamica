package problems;

import java.util.*;

class ProgramacionDinamica{
	
	private static Vector<Vector<Integer>> tarifa;
	private static Vector<Vector<Integer>> precioDinamico = new Vector<Vector<Integer>>();
	
	protected void iniciarGenerarCaminos(int i, int j){
		Vector<Integer> p = new Vector<Integer>();
		generarCaminos(i-1,j-1,p);
	}

	//int i => posicion inicial
	//int j => posicion destino
	protected void generarCaminos(int i, int j, Vector<Integer> p){
		//Al generar los precios todos los primeros numeros seran los embarques
		//El ultimo numero sera la suma total
		//Considerar que i minimo sera 1 por lo tanto
		// i minimo = i-1
		
		if(p.isEmpty())
			p.add(0); //primer valor sera el de el costo total
		p.add(i+1);
		if((j-i) > 0){
			//int ps = tarifa.get(i-1).size(); //posibilidad de salidas
			int suma;
			for(int b = 0;b < j-i; b++){
				Vector<Integer> pp = new Vector<Integer>();
				pp.addAll(p);
				//pp.add(tarifa.get(i).get(b));
				suma = pp.get(0) + tarifa.get(i).get(b);
				pp.setElementAt(suma,0);
				generarCaminos(b+i+1,j,pp);
			}
		}
		else precioDinamico.add(p); //guardar resultados
		//imprimirPrecioDinamico();
		/*
		for(int ps = i-1; ps < j-1; ps++){
			Vector<Integer> posPre = new Vector<Integer>();
			int suma = 0;
			for(int pl = ps+1; pl < j-1; pl++){
				posPre.add(pl);
				suma = tarifa.get(
			}
		}*/
	}
	
	protected void imprimirCaminoE(){
		// Burbuja!!!!??? noooo!!! no toca organizar :)
		int pos = 0;
		int costo = precioDinamico.get(pos).get(0);
		for(int i = 1; i < precioDinamico.size(); i++){
			if(costo > precioDinamico.get(i).get(0)){
				costo = precioDinamico.get(i).get(0);
				pos = i;
			}
		}
		System.out.println("Mejor camino a tomar: ");
		System.out.println("Costo: " + costo);
		System.out.print("Paradas: ");
		for(int i = 1; i < precioDinamico.get(pos).size(); i++){
			System.out.print("- " + precioDinamico.get(pos).get(i));
		}
		System.out.print("\n");
	}
	
	protected void imprimirPrecioDinamico(){
		System.out.println("Precio Dinamico");
		for(int ps = 0; ps < precioDinamico.size(); ps++){
			System.out.println("Costo " + (precioDinamico.get(ps).get(0)));
			System.out.print("Paradas ");
			for(int pl = 1; pl < precioDinamico.get(ps).size(); pl++){
				System.out.print("- " + precioDinamico.get(ps).get(pl));
			}
			System.out.print("\n");
		}
	}

	protected void imprimirTarifa(int t){
		for(int ps = 0; ps < t-1; ps++){
			System.out.println("Salida de puerto " + (ps+1));
			for(int pl = ps+1, c = 0; pl < t; pl++){
				System.out.println("--> Llegada a " + (pl+1) + ", precio = " + tarifa.get(ps).get(c));
				c++;
			}
		}
		//System.out.println(tarifa);
	}

	//t => tamano de la lista 
	//p => maximo valor a obtener aleatoriamente
	protected void crearTarifaAleatoria(int t,int p){
		Vector<Vector<Integer>> tar = new Vector<Vector<Integer>>();
		Random r = new Random();
		for(int f = 0; f < t; f++){
			Vector<Integer> temp = new Vector<Integer>();
			for(int c = f+1; c < t; c++){
				temp.add(r.nextInt(p+1));
			}
			tar.add(temp);
		}
		
		this.tarifa = tar;
	}
}
