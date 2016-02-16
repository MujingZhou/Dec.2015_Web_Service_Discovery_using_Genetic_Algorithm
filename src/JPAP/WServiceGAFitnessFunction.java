package JPAP;
import java.util.ArrayList;
import java.util.HashMap;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class WServiceGAFitnessFunction extends FitnessFunction{
    
    private static final long serialVersionUID = 1L;
    private final double WEIGHTCOST = 0.35;
    private final double WEIGHTRELABILITY = 0.2;
    private final double WEIGHTPERFORMANCE = 0.25;
    private final double WEIGHTAVAILABILITY = 0.2;
    
    private HashMap<String,ServiceCluster> scMap = new HashMap<>();
    private ArrayList<String> keywords = new ArrayList<>();
    
    public WServiceGAFitnessFunction(HashMap<String,ServiceCluster> scMap,ArrayList<String> keywords){
        this.scMap = scMap;
        this.keywords = keywords;
    }
    
    @Override
    protected double evaluate(IChromosome chromosome) {
        
        ArrayList<Integer> index = new ArrayList<>();
        for (int i=0;i<chromosome.size();i++){
            int chosenIndex = (Integer)chromosome.getGene(i).getAllele();
            index.add(chosenIndex);
        }

        ArrayList<Service> chosenServiceList = new ArrayList<>();
        for (int i=0;i<index.size();i++){
            Service serviceChosen = scMap.get(keywords.get(i)).getService(index.get(i));
            chosenServiceList.add(serviceChosen);
        }
        
        double totalPerformance=0.0;

        double bestCost = 1.0;
        double bestReliability = 1.0;
        double bestTimePer = 1.0;
        double bestAvailability = 1.0;
        
        for (int i=0;i<index.size();i++){
            bestCost *= chosenServiceList.get(i).cost;
            bestReliability *= chosenServiceList.get(i).reliability;
            bestTimePer *= chosenServiceList.get(i).time;
            bestAvailability *= chosenServiceList.get(i).availability;
        }
        
        totalPerformance = WEIGHTCOST*bestCost+WEIGHTRELABILITY*bestReliability+WEIGHTPERFORMANCE*
                bestTimePer+WEIGHTAVAILABILITY*bestAvailability;
        
        return totalPerformance;
    }
   
}
