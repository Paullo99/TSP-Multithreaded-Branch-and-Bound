package main.java.pl.zssk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	// Menu g��wne programu
	public static void main(String[] args) throws FileNotFoundException {
        /*System.out.println("Wprowadz nazw� pliku: ");
        Scanner sc = new Scanner(System.in);
        File file = new File(sc.nextLine());
        Scanner scanner = new Scanner(file);
        int firstLine = scanner.nextInt();
        ArrayGraph graph = new ArrayGraph(firstLine);
        for (int i = 0; i < firstLine; i++) {
            for (int j = 0; j < firstLine; j++) {
                graph.addEdge(i, j, scanner.nextInt());
            }
        }*/
        //
       ArrayGraph graph = new ArrayGraph(23);

        Graph.generateRandomFullGraph(graph, 100);
        graph.displayGraph();
        long nanosActualTime = System.nanoTime();
        ArrayList<Integer> route = Graph.ATSPBranchAndBound(graph);
        long executionTime = System.nanoTime() - nanosActualTime;
        System.out.println("Czas wykonania algorytmu [ms]: " + executionTime/1000000);

	}

	public void oldMenu() throws FileNotFoundException {
        int selection;
        ArrayGraph graph = null;
        final int maxSalesmanDistance = 300;
        do {
            System.out.println("1. Wczytanie danych z pliku");
            System.out.println("2. Wygenerowanie danych losowych");
            System.out.println("3. Wy�wietlenie ostatnio wczytanych lub wygenerowanych danych");
            System.out.println("4. Podzial i ograniczenia");
            System.out.println("5. Pomiary");
            System.out.println("Aby zakonczyc - 0");
            System.out.println("Wprowadz liczb�: ");
            Scanner sc = new Scanner(System.in);
            selection = sc.nextInt();
            switch (selection) {
                case 1: {
                    System.out.println("Wprowadz nazw� pliku: ");
                    sc = new Scanner(System.in);
                    File file = new File(sc.nextLine());
                    Scanner scanner = new Scanner(file);
                    int firstLine = scanner.nextInt();
                    graph = new ArrayGraph(firstLine);
                    for (int i = 0; i < firstLine; i++) {
                        for (int j = 0; j < firstLine; j++) {
                            graph.addEdge(i, j, scanner.nextInt());
                        }
                    }

                }
                break;

                case 2: {
                    int vertex;
                    System.out.println("Liczba miast: ");
                    vertex = sc.nextInt();

                    graph = new ArrayGraph(vertex);

                    Graph.generateRandomFullGraph(graph, maxSalesmanDistance);
                }
                break;
                case 3: {
                    if (graph != null)
                        graph.displayGraph();
                    else
                        System.out.println("Brak danych do wy�wietlenia");
                }
                break;
                case 4: {
                    if (graph != null) {
                        long nanosActualTime = System.nanoTime();
                        ArrayList<Integer> route = Graph.ATSPBranchAndBound(graph);
                        long executionTime = System.nanoTime() - nanosActualTime;

                        // Wyswietlenie trasy
                        int distFromStart = 0;
                        int length = 0;
                        System.out.print(route.get(0) + " -> ");
                        for (int i = 1; i < route.size(); i++) {
                            length = graph.getWeight(route.get(i - 1), route.get(i));
                            distFromStart += length;

                            System.out.print(route.get(i));
                            if(i!= route.size()-1) {
                                System.out.print(" -> ");
                            }
                        }
                        System.out.println();
                        System.out.println("Dlugosc trasy: " + distFromStart);

                        System.out.println("Czas wykonania algorytmu [ms]: " + executionTime/1000000);
                    } else
                        System.out.println(" Brak zaladowanych danych ");
                }
                break;

                case 5: {

                    int average;
                    int max_iteration = 100; // ilo�� pomiar�w dla danego N
                    System.out.println();
                    System.out.println("----- BB -----");
                    for (int nIterator = 9; nIterator < 15; nIterator++) {
                        int sum = 0;
                        for (int actualIterator = 0; actualIterator < max_iteration; actualIterator++) {
                            ArrayGraph graph2 = new ArrayGraph(nIterator);
                            Graph.generateRandomFullGraph(graph2, maxSalesmanDistance);
                            long nanosActualTime = System.nanoTime();
                            Graph.ATSPBranchAndBound(graph2);
                            long executionTime = (System.nanoTime() - nanosActualTime)/1000000;
                            sum += executionTime;
                            System.out.print(" \r" + (100 / max_iteration)* (actualIterator + 1) + "%");
                        }
                        System.out.println();
                        average = sum / max_iteration;
                        System.out.println("N = " + nIterator + ", sredni czas = " + average + "[ms]" );
                    }


                }
                break;
                case 0: {
                }
                break;
                default: {
                    System.out.println("Nieprawidlowy wybor");
                }
            }
        } while (selection != 0);
    }

}
