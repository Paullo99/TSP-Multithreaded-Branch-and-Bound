package pl.zssk;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public abstract class Graph {
	protected int vertexAmount;
	private static int INFINITY = Integer.MAX_VALUE;

	public int getVertexAmount() {
		return vertexAmount;
	}

	public void setVertexAmount(int vertexAmount) {
		this.vertexAmount = vertexAmount;
	}

	static void generateRandomFullGraph(ArrayGraph graph, int maxWeight) {
		Random random = new Random();
		for (int i = 0; i < graph.vertexAmount; i++) {
			for (int j = 0; j < graph.vertexAmount; j++) {
				if (i != j) {
					// Bez warunku na krawedzie juz wygenerowane...
					// ...z tym radzi sobie juz metoda addEdge
					int randomWeight = ((random.nextInt() % maxWeight) + maxWeight + 1) % maxWeight;
					graph.addEdge(i, j, randomWeight);
				}
			}
		}

	}

	static ArrayList<Integer> ATSPBranchAndBound(ArrayGraph graph) {
		PriorityQueue<ArrayList<Integer>> priorityQueue = new PriorityQueue<ArrayList<Integer>>(new ArrayListComparator());;
		
		//W tej li�cie b�dzie zapisywane optymalne w danej chwili rozwi�zanie
		ArrayList<Integer> optimalRoute = new ArrayList<Integer>();
		int optimalRouteLength = INFINITY; // optymalna d�ugo�c tras - na starcie jest to niesko�czono��

		// Pierwszy element listy to granica
		// liczba b�d�ca ograniczeniem warto�ci rozwi�zania jakie mo�na uzyska�
		//dzi�ki rozwini�ciu (przejrzeniu potomk�w) danego w�z�a
		
		// Kolejne to wierzcholki trasy

		ArrayList<Integer> currentRoute = new ArrayList<Integer>(); // Niejawne tworzenie drzewa, tu bedzie korzen
		currentRoute.add(0); // Poczatkowe oszacowanie nie ma znaczenia
		currentRoute.add(0); // Wierzcholek startowy (korzen drzewa rozwiazan)
		priorityQueue.add(currentRoute); // Dodanie do kolejki korzenia
		
		//deklaracja nast�pnej trasy
		ArrayList<Integer> nextRoute;

		while (!priorityQueue.isEmpty()) {
			
			// Przypisanie korzenia oraz jego usuni�cie z kolejki
			currentRoute = priorityQueue.poll();
	
			// Sprawdzenie, czy rozwiazanie jest warte rozwijania, czy odrzucic
			if (optimalRouteLength == INFINITY || currentRoute.get(0) < optimalRouteLength) {
				for (int i = 0; i < graph.vertexAmount; i++) {
					// Petla wykonywana dla kazdego potomka rozpatrywanego wlasnie rozwiazania w
					// drzewie
					// Ustalenie, czy dany wierzcholek mozna jeszcze wykorzystac, czy juz zostal
					// uzyty
					boolean vertexUsed = false;
					for (int j = 1; j < currentRoute.size(); j++) {
						if (currentRoute.get(j) == i) {
							vertexUsed = true;
							break;
						}
					}
					if (vertexUsed)
						continue;

					// Utworzenie nowego wezla reprezuntujacego rozpatrywane rozwiazanie...
					nextRoute = new ArrayList<Integer>(currentRoute);
					nextRoute.add(i);

					// Dalej bedziemy postepowac roznie...
					if (nextRoute.size() > graph.vertexAmount) {
						// Dotarli�my do li�cia
						// Dodajemy drog� do wierzcho�ka startowego
						nextRoute.add(0);

						nextRoute.set(0, 0);

						for (int j = 1; j < nextRoute.size() - 1; j++) {
							// Liczymy dystans od poczatku do konca

							nextRoute.set(0,
									nextRoute.get(0) + graph.getWeight(nextRoute.get(j), nextRoute.get(j + 1)));
						}
						if (optimalRouteLength == INFINITY || nextRoute.get(0) < optimalRouteLength) {
							optimalRouteLength = nextRoute.get(0);
							nextRoute.remove(0);
							optimalRoute = nextRoute;
						}
					} else {
						// Liczenie tego, co juz wiemy, od nowa...
						// (dystans od poczatku)
						nextRoute.set(0, 0);
						for (int j = 1; j < nextRoute.size() - 1; j++) {
							nextRoute.set(0,
									nextRoute.get(0) + graph.getWeight(nextRoute.get(j), nextRoute.get(j + 1)));
						}

						// Reszte szacujemy...
						// Pomijamy od razu wierzcholek startowy
						for (int j = 1; j < graph.vertexAmount; j++) {
							// Odrzucenie wierzcholkow juz umieszczonych na trasie
							vertexUsed = false;
							for (int k = 1; k < currentRoute.size(); k++) {
								if (j == currentRoute.get(k)) {
									vertexUsed = true;
									break;
								}
							}
							if (vertexUsed)
								continue;

							int minEdge = -1;
							for (int k = 0; k < graph.vertexAmount; k++) {
								// Odrzucenie krawedzi do wierzcholka 0 przy ostatnim wierzcholku w czesciowym
								// rozwiazaniu
								// Wyjatkiem jest ostatnia mozliwa krawedz
								if (j == i && k == 0)
									continue;

								// Odrzucenie krawedzi do wierzcholka umieszczonego juz na rozwazanej trasie
								vertexUsed = false;
								for (int l = 2; l < nextRoute.size(); l++) {
									if (k == nextRoute.get(l)) {
										vertexUsed = true;
										break;
									}
								}
								if (vertexUsed)
									continue;

								// Odrzucenie samego siebie
								if (k == j)
									continue;

								// Znalezienie najkrotszej mozliwej jeszcze do uzycia krawedzi
								int consideredLength = graph.getWeight(j, k);

								if (minEdge == -1)
									minEdge = consideredLength;
								else if (minEdge > consideredLength)
									minEdge = consideredLength;
							}
							nextRoute.set(0, nextRoute.get(0) + minEdge);
						}

						// 
						if (optimalRouteLength == INFINITY || nextRoute.get(0) < optimalRouteLength) {
							priorityQueue.add(nextRoute);
						}
					}
				}
			} else {
				// Jezeli jedno rozwiazanie odrzucilismy, to wszystkie inne tez mozemy
				// (kolejka priorytetowa, inne nie moga byc lepsze)
				break;
			}
		}

		return optimalRoute;

	}

}
