package rank;

import java.util.Objects;

public class Site implements Comparable<Site>{
    private String name;
    private double rank;

    public Site(String name){
        this.name = name;
        this.rank = 0;
    }

    public void addRank(double rank){
        this.rank+=rank;
    }


    public String getName(){
        return name;
    }

    public double getRank () {
        return rank;
    }

    @Override
    public boolean equals (Object obj) {
        boolean igual = false;
        if(obj instanceof Site pene){
            igual = name.equalsIgnoreCase(pene.name);
        }
        return igual;
    }

    @Override
    public int hashCode () {
        return Objects.hash(name.toUpperCase());
    }

    public String toString(){
        return name+"("+rank+")";
    }
    public int compareTo(Site site){
        return this.name.compareToIgnoreCase(site.name);
    }
}
