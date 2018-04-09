
package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class FieldWeb {
    private ArrayList<Field> fields;
    
    public FieldWeb() {
        this.fields = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();
        Arrays.asList(2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12).stream().forEach(i->{
            values.add(i);
        });
        ArrayList<Resource> resources = new ArrayList<>();
        Arrays.asList(Resource.Clay, Resource.Clay, Resource.Clay, Resource.Corp, Resource.Corp, Resource.Corp, Resource.Corp, Resource.Sheep, Resource.Sheep, 
                Resource.Sheep, Resource.Sheep, Resource.Stone, Resource.Stone, Resource.Stone, Resource.Wood, Resource.Wood, Resource.Wood, Resource.Wood, 
                Resource.Clay).stream().forEach(i->{
            resources.add(i);
        });
        
        for (int i = 0; i < 19; i++) {
            int randomValue = new Random().nextInt(values.size());
            int randomResource = new Random().nextInt(resources.size());
            
            this.fields.add(new Field(resources.get(randomResource), values.get(randomValue),null, null));
            
            values.remove(randomValue);
            resources.remove(randomResource);
        }
        
        this.fields.get(0).setLocationAndNode(new Location(6,2), Arrays.asList("N1","N2","N4","N5","N9","N10"));
        this.fields.get(1).setLocationAndNode(new Location(4,3), Arrays.asList("N3","N4","N8","N9","N14","N15"));
        this.fields.get(2).setLocationAndNode(new Location(8,3), Arrays.asList("N5","N6","N10","N11","N16","N17"));
        this.fields.get(3).setLocationAndNode(new Location(2,4), Arrays.asList("N7","N8","N13","N14","N19","N20"));
        this.fields.get(4).setLocationAndNode(new Location(6,4), Arrays.asList("N9","N10","N15","N16","N21","N22"));
        this.fields.get(5).setLocationAndNode(new Location(10,4), Arrays.asList("N11","N12","N17","N18","N23","N24"));
        this.fields.get(6).setLocationAndNode(new Location(4,5), Arrays.asList("N14","N15","N20","N21","N26","N27"));
        this.fields.get(7).setLocationAndNode(new Location(8,5), Arrays.asList("N16","N17","N22","N23","N28","N29"));
        this.fields.get(8).setLocationAndNode(new Location(2,6), Arrays.asList("N19","N20","N25","N26","N31","N32"));
        this.fields.get(9).setLocationAndNode(new Location(6,6), Arrays.asList("N21","N22","N27","N28","N33","N34"));
        this.fields.get(10).setLocationAndNode(new Location(10,6), Arrays.asList("N23","N24","N29","N30","N35","N36"));
        this.fields.get(11).setLocationAndNode(new Location(4,7), Arrays.asList("N26","N27","N32","N33","N38","N39"));
        this.fields.get(12).setLocationAndNode(new Location(8,7), Arrays.asList("N28","N29","N34","N35","N40","N41"));
        this.fields.get(13).setLocationAndNode(new Location(2,8), Arrays.asList("N31","N32","N37","N38","N43","N44"));
        this.fields.get(14).setLocationAndNode(new Location(6,8), Arrays.asList("N33","N34","N39","N40","N45","N46"));
        this.fields.get(15).setLocationAndNode(new Location(10,8), Arrays.asList("N35","N36","N41","N42","N47","N48"));
        this.fields.get(16).setLocationAndNode(new Location(4,9), Arrays.asList("N38","N39","N44","N45","N49","N50"));
        this.fields.get(17).setLocationAndNode(new Location(8,9), Arrays.asList("N40","N41","N46","N47","N51","N52"));
        this.fields.get(18).setLocationAndNode(new Location(6,10), Arrays.asList("N45","N46","N50","N51","N53","N54"));
        
    }

    public ArrayList<Field> getFields() {
        return fields;
    }
    
}
