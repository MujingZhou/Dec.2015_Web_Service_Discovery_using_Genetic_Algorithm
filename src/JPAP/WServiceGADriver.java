package JPAP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

public class WServiceGADriver {

    private static final double WEIGHTCOST = 0.35;
    private static final double WEIGHTRELABILITY = 0.2;
    private static final double WEIGHTPERFORMANCE = 0.25;
    private static final double WEIGHTAVAILABILITY = 0.2;
    private static final int MAX_ALLOWED_EVOLUTIONS = 500;

    public static HashMap<String, ServiceCluster> scMap = new HashMap<>();
    public static ArrayList<String> keywords = new ArrayList<>();

    public static void main(String args[]) throws Exception {
//        String fileName = "input.txt";
//         String fileName = "input2(Adding Services).txt";
        String fileName = "input3(Adding ServiceClusters).txt";

        initializeFromText(fileName);
        Configuration conf = new DefaultConfiguration();

        FitnessFunction fun1 = new WServiceGAFitnessFunction(scMap, keywords);
        conf.setFitnessFunction(fun1);

        Gene[] sampleGenes = new Gene[scMap.size()];

        for (int i = 0; i < scMap.size(); i++) {
            sampleGenes[i] = new IntegerGene(conf, 0, scMap.get(keywords.get(i)).numberOfServices() - 1);
            System.out.println("Cluster " + keywords.get(i) + " has " + (scMap.get(keywords.get(i)).numberOfServices())
                    + " services");
        }

        Chromosome sampleChromosome = new Chromosome(conf, sampleGenes);
        conf.setSampleChromosome(sampleChromosome);
        conf.setPopulationSize(500);

        Genotype population = Genotype.randomInitialGenotype(conf);

        for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
            population.evolve();
        }

        IChromosome bestSolutionSoFar = population.getFittestChromosome();

        System.out.println("Optimal combinations of services are: ");
        ArrayList<Integer> chosenIndex = new ArrayList<>();
        for (int i = 0; i < scMap.size(); i++) {
            int index = (int) bestSolutionSoFar.getGene(i).getAllele();
            chosenIndex.add(index);
            System.out.println(keywords.get(i) + " : " + scMap.get(keywords.get(i)).getService(index).serviceName);
        }
        System.out.println("Best performance is " + bestPerformance(chosenIndex));
    }

    public static void initializeFromText(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            reader.readLine();
            String s = "";
            double minCost = (double)Integer.MAX_VALUE;
            
            while ((s = reader.readLine()) != null) {
                String[] inputArray = s.split(" ");
                double cost = Double.valueOf(inputArray[2]);
                if (cost<minCost) {
                    minCost = cost;
                }
            }
//            System.out.println(minCost);
            reader.close();
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine();
            while ((s = reader.readLine()) != null) {
                String[] inputArray = s.split(" ");
                String serviceClusterName = inputArray[0];
                if (!scMap.containsKey(serviceClusterName)) {
                    scMap.put(serviceClusterName, new ServiceCluster());
                    keywords.add(serviceClusterName);
                }
                String serviceName = inputArray[1];
                double cost = (minCost / Double.valueOf(inputArray[2]));
                double reliability = (Double.valueOf(inputArray[3]) / 100);
                double time = Double.valueOf(inputArray[4]);
                if (time <= 2)
                    time = 0.95;
                else if (time <= 5 && time > 2)
                    time = 0.8;
                else if (time <= 10 && time > 5)
                    time = 0.65;
                else if (time <= 20 && time > 10)
                    time = 0.5;
                else if (time > 20)
                    time = 0.35;
                double availability = (Double.valueOf(inputArray[5]) / 100);
                Service newService = new Service(serviceName, cost, reliability, time, availability);
                ServiceCluster tmp = scMap.get(serviceClusterName);
                tmp.addServiceToList(newService);
            }
            reader.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static double bestPerformance(ArrayList<Integer> index) {
        ArrayList<Service> chosenServiceList = new ArrayList<>();
        for (int i = 0; i < index.size(); i++) {
            Service serviceChosen = scMap.get(keywords.get(i)).getService(index.get(i));
            chosenServiceList.add(serviceChosen);
        }

        double bestPerformance = 0.0;

        double bestCost = 1.0;
        double bestReliability = 1.0;
        double bestTimePer = 1.0;
        double bestAvailability = 1.0;
        
        for (int i = 0; i < index.size(); i++) {
            bestCost *= chosenServiceList.get(i).cost;
            bestReliability *= chosenServiceList.get(i).reliability;
            bestTimePer *= chosenServiceList.get(i).time;
            bestAvailability *= chosenServiceList.get(i).availability;
        }

        bestPerformance = WEIGHTCOST * bestCost + WEIGHTRELABILITY * bestReliability + WEIGHTPERFORMANCE * bestTimePer
                + WEIGHTAVAILABILITY * bestAvailability;
        return bestPerformance;
    }
}
