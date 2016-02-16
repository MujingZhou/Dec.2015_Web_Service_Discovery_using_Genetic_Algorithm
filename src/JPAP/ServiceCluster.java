package JPAP;

import java.util.ArrayList;

public class ServiceCluster {
    ArrayList<Service> serviceList = new ArrayList<>();
    
    
    public ServiceCluster(){
        
    }
    
    public int numberOfServices(){
        return serviceList.size();
    }
    
    public void addServiceToList(Service service){
        serviceList.add(service);
    }
    
    public ServiceCluster(ArrayList<Service> serviceList){
        this.serviceList = serviceList;
    }
    
    public Service getService(int index){
        return serviceList.get(index);
    } 
}
