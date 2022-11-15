package utils;

public enum Genre {
    MALE("Masculino"),
    FEMALE("Femenino");
    
    private String name;
    
    Genre(String s){
    	name = s;
    }
    
    public String getName(){
    	return name;
    }
}
