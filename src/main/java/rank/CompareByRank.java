package rank;

import java.util.Comparator;

public class CompareByRank implements Comparator<Site> {
    @Override
    public int compare(Site site1, Site site2){
        int result=Double.compare(site2.getRank(), site1.getRank());
        if(result==0){
            result=site1.compareTo(site2);
        }
        return result;
    }
}

