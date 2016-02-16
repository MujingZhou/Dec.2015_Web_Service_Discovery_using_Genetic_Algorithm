package JPAP;

public class Service {
    public String serviceName;
    public double cost;
    public double reliability;
    public double time;
    public double availability;
    
    public Service(){
        
    }
    
    public Service(String serviceName,double cost,double reliability,double time,double availability){
        this.serviceName = serviceName;
        this.cost = cost;
        this.reliability = reliability;
        this.time = time;
        this.availability = availability;
    }
    
    
}
