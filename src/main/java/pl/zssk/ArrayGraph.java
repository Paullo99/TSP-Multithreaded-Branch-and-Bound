package pl.zssk;

public class ArrayGraph extends Graph{
	private int[][] neighborhoodMatrix;

	// Konstruktor Klasy pl.zssk.Graph
	public ArrayGraph(int vertexAmount) {
		this.vertexAmount = vertexAmount;
		this.neighborhoodMatrix = new int[vertexAmount][vertexAmount];

		for (int i = 0; i < vertexAmount; i++) {
			for (int j = 0; j < vertexAmount; j++) {
					this.neighborhoodMatrix[i][j] = 0;
			}
		}
	}

	// Metoda tworz�ca kraw�dz pomi�dzy danymi wierzcho�kami o podanej wadze
	public boolean addEdge(int v, int w, int weight) {
		if (weight >= 1000)
			// Waga krawedzi musi byc mniejsza od 1000
			weight = 999;

		if (this.neighborhoodMatrix[v][w] > 0)
			return false;
		else {
			this.neighborhoodMatrix[v][w] = weight;
			return true;
		}
	}

	// Zwraca wag� kraw�dzi
	public int getWeight(int v, int w) {
		return neighborhoodMatrix[v][w];
	}

	// Wypisanie grafu w postaci macierzy s�siedztwa
	public void displayGraph() {
		for (int i = 0; i < vertexAmount; i++) {
			for (int j = 0; j < vertexAmount; j++) {
				System.out.printf("%d ", this.neighborhoodMatrix[i][j]);
			}
			System.out.println();
		}
	}

}
