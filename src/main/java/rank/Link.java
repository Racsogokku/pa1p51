package rank;

import java.util.Objects;

public class Link {
    private String origin,linked;

    public Link (String origin, String linked){
        this.origin=origin;
        this.linked=linked;
    }

    @Override
    public boolean equals (Object obj) {
        boolean igual=false;
        if(obj instanceof Link otro){
            igual= otro.origin.equalsIgnoreCase(origin)&&otro.linked.equalsIgnoreCase(linked);
        }
        return igual;
    }

    public String getLinked () {
        return linked;
    }

    public String getOrigin () {
        return origin;
    }

    public int hashCode(){
        return Objects.hash(origin.toUpperCase(),linked.toUpperCase());
    }

    public String toString(){
        return origin+"->"+linked;
    }
}
