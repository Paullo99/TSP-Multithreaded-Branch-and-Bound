package pl.zssk;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // Menu g³ówne programu
    public static void main(String[] args) throws IOException, InterruptedException {
        long nanosActualTime;
        long executionTime;
        ArrayList<Thread> threadArrayList = new ArrayList<>();

        //Liczba w¹tków
        ArrayList<Integer> numberOfThreads = new ArrayList<>(Arrays.asList(10, 25, 50, 100));

        /*
        MA£Y PLIK - TSP_22.TXT
        (10 POWTÓRZEÑ)
         */
        System.out.println("\n\ntsp.22.txt\n");
        ArrayGraph graph22 = getArrayGraph("tsp_20.txt");


        for (Integer numberOfProblems : numberOfThreads) {
            //File file = new File(".\\results\\sequence_"+numberOfProblems + "_problems_size_22.csv");
            String resultSequenceFilename = ".\\results\\sequence_"+numberOfProblems + "_problems_size_17.csv";
            String resultParallelFilename = ".\\results\\parallel_"+numberOfProblems + "_problems_size_17.csv";
            CSVWriter sequenceWriter = new CSVWriter(new FileWriter(resultSequenceFilename, true), ',', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            CSVWriter parallelWriter = new CSVWriter(new FileWriter(resultParallelFilename, true), ',', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

            for (int i = 0; i < 10; i++) {
                System.out.println("Liczba problemów: "+numberOfProblems + " Powtórzenie: " + i);
                //sekwencyjnie
                Thread sequence = new Thread(() -> {
                    for (int j = 0; j < numberOfProblems; j++) {
                        Graph.ATSPBranchAndBound(graph22);
                    }
                });
                nanosActualTime = System.nanoTime();
                sequence.start();
                sequence.join();
                executionTime = (System.nanoTime() - nanosActualTime) / 1000000;
                System.out.println("Szeregowo: " + executionTime);
                sequenceWriter.writeNext(new String[]{String.valueOf(executionTime)});

                //równolegle
                threadArrayList = new ArrayList<>();
                for (int j = 0; j < numberOfProblems; j++) {
                    threadArrayList.add(new Thread(() -> Graph.ATSPBranchAndBound(graph22)));
                }


                for (Thread t : threadArrayList) {
                    t.start();
                }

                nanosActualTime = System.nanoTime();
                for (Thread t : threadArrayList) {
                    t.join();
                }
                executionTime = (System.nanoTime() - nanosActualTime) / 1000000;
                System.out.println("Równolegle: " + executionTime);
                parallelWriter.writeNext(new String[]{String.valueOf(executionTime)});
            }
            sequenceWriter.close();
            parallelWriter.close();
        }

        numberOfThreads = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 8, 10, 25, 50, 100));
        /*
        ŒREDNI PLIK - TSP_23.TXT
        (5 POWTÓRZEÑ)
         */
        System.out.println("\n\ntsp.23.txt\n");
        ArrayGraph graph23 = getArrayGraph("tsp_23.txt");


        for (Integer numberOfProblems : numberOfThreads) {
            //File file = new File(".\\results\\sequence_"+numberOfProblems + "_problems_size_22.csv");
            String resultSequenceFilename = ".\\results\\sequence_"+numberOfProblems + "_problems_size_23.csv";
            String resultParallelFilename = ".\\results\\parallel_"+numberOfProblems + "_problems_size_23.csv";
            CSVWriter sequenceWriter = new CSVWriter(new FileWriter(resultSequenceFilename, true), ',', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            CSVWriter parallelWriter = new CSVWriter(new FileWriter(resultParallelFilename, true), ',', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

            for (int i = 0; i < 5; i++) {
                System.out.println("Liczba problemów: "+numberOfProblems + " Powtórzenie: " + i);
                //sekwencyjnie
                Thread sequence = new Thread(() -> {
                    for (int j = 0; j < numberOfProblems; j++) {
                        Graph.ATSPBranchAndBound(graph23);
                    }
                });
                nanosActualTime = System.nanoTime();
                sequence.start();
                sequence.join();
                executionTime = (System.nanoTime() - nanosActualTime) / 1000000;
                sequenceWriter.writeNext(new String[]{String.valueOf(executionTime)});

                //równolegle
                threadArrayList = new ArrayList<>();
                for (int j = 0; j < numberOfProblems; j++) {
                    threadArrayList.add(new Thread(() -> Graph.ATSPBranchAndBound(graph23)));
                }

                nanosActualTime = System.nanoTime();
                for (Thread t : threadArrayList) {
                    t.start();
                }


                for (Thread t : threadArrayList) {
                    t.join();
                }
                executionTime = (System.nanoTime() - nanosActualTime) / 1000000;
                parallelWriter.writeNext(new String[]{String.valueOf(executionTime)});
            }
            sequenceWriter.close();
            parallelWriter.close();


        }



        /*
        DU¯Y PLIK - TSP_24.TXT
        (10 POWTÓRZEÑ)
         */
        ArrayGraph graph24 = getArrayGraph("tsp_24.txt");


        for (Integer numberOfProblems : numberOfThreads) {
            //File file = new File(".\\results\\sequence_"+numberOfProblems + "_problems_size_22.csv");
            String resultSequenceFilename = ".\\results\\sequence_"+numberOfProblems + "_problems_size_24.csv";
            String resultParallelFilename = ".\\results\\parallel_"+numberOfProblems + "_problems_size_24.csv";
            CSVWriter sequenceWriter = new CSVWriter(new FileWriter(resultSequenceFilename, true), ',', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            CSVWriter parallelWriter = new CSVWriter(new FileWriter(resultParallelFilename, true), ',', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

            for (int i = 0; i < 10; i++) {
                System.out.println("Liczba problemów: "+numberOfProblems + " Powtórzenie: " + i);
                //sekwencyjnie
                Thread sequence = new Thread(() -> {
                    for (int j = 0; j < numberOfProblems; j++) {
                        Graph.ATSPBranchAndBound(graph24);
                    }
                });
                nanosActualTime = System.nanoTime();
                sequence.start();
                sequence.join();
                executionTime = (System.nanoTime() - nanosActualTime) / 1000000;
                sequenceWriter.writeNext(new String[]{String.valueOf(executionTime)});

                //równolegle
                threadArrayList = new ArrayList<>();
                for (int j = 0; j < numberOfProblems; j++) {
                    threadArrayList.add(new Thread(() -> Graph.ATSPBranchAndBound(graph24)));
                }

                nanosActualTime = System.nanoTime();
                for (Thread t : threadArrayList) {
                    t.start();
                }


                for (Thread t : threadArrayList) {
                    t.join();
                }
                executionTime = (System.nanoTime() - nanosActualTime) / 1000000;
                parallelWriter.writeNext(new String[]{String.valueOf(executionTime)});
            }
            sequenceWriter.close();
            parallelWriter.close();


        }
    }

    private static ArrayGraph getArrayGraph(String filename) throws FileNotFoundException {
        Scanner scanner;
        File file;
        ArrayGraph graph;
        int firstLine;
        file = new File(filename);
        scanner = new Scanner(file);
        firstLine = scanner.nextInt();
        graph = new ArrayGraph(firstLine);
        for (int i = 0; i < firstLine; i++) {
            for (int j = 0; j < firstLine; j++) {
                graph.addEdge(i, j, scanner.nextInt());
            }
        }
        return graph;
    }


    public void oldMenu() throws FileNotFoundException {
        int selection;
        ArrayGraph graph = null;
        final int maxSalesmanDistance = 300;
        do {
            System.out.println("1. Wczytanie danych z pliku");
            System.out.println("2. Wygenerowanie danych losowych");
            System.out.println("3. Wyœwietlenie ostatnio wczytanych lub wygenerowanych danych");
            System.out.println("4. Podzial i ograniczenia");
            System.out.println("5. Pomiary");
            System.out.println("Aby zakonczyc - 0");
            System.out.println("Wprowadz liczbê: ");
            Scanner sc = new Scanner(System.in);
            selection = sc.nextInt();
            switch (selection) {
                case 1: {
                    System.out.println("Wprowadz nazwê pliku: ");
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
                        System.out.println("Brak danych do wyœwietlenia");
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
                            if (i != route.size() - 1) {
                                System.out.print(" -> ");
                            }
                        }
                        System.out.println();
                        System.out.println("Dlugosc trasy: " + distFromStart);

                        System.out.println("Czas wykonania algorytmu [ms]: " + executionTime / 1000000);
                    } else
                        System.out.println(" Brak zaladowanych danych ");
                }
                break;

                case 5: {

                    int average;
                    int max_iteration = 100; // iloœæ pomiarów dla danego N
                    System.out.println();
                    System.out.println("----- BB -----");
                    for (int nIterator = 9; nIterator < 15; nIterator++) {
                        int sum = 0;
                        for (int actualIterator = 0; actualIterator < max_iteration; actualIterator++) {
                            ArrayGraph graph2 = new ArrayGraph(nIterator);
                            Graph.generateRandomFullGraph(graph2, maxSalesmanDistance);
                            long nanosActualTime = System.nanoTime();
                            Graph.ATSPBranchAndBound(graph2);
                            long executionTime = (System.nanoTime() - nanosActualTime) / 1000000;
                            sum += executionTime;
                            System.out.print(" \r" + (100 / max_iteration) * (actualIterator + 1) + "%");
                        }
                        System.out.println();
                        average = sum / max_iteration;
                        System.out.println("N = " + nIterator + ", sredni czas = " + average + "[ms]");
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
