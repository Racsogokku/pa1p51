package rank;

import java.lang.reflect.Array;
import java.util.*;

public class Web {
    private Set<Link>links;
    protected Set<Site>sites;
    private static final double THRESHOLD=1E-5;
    private Random alea;

    public Web(){
        links = new HashSet<>();
        sites = new HashSet<>();
        alea = new Random(1);
    }

    protected void addSite(Site site){
        sites.add(site);
    }

    protected void addSiteWithName(String name){
        addSite(new Site(name));
    }

    public void addLink(String dataLink){
        String[] pene = dataLink.split("->");
        if (pene.length != 2) {
            throw new IllegalArgumentException(dataLink);
        }
        Link link = new Link(pene[0], pene[1]);
        links.add(link);
        addSiteWithName(pene[0]);
        addSiteWithName(pene[1]);
    }

    public Site getSite(String name){
        Site site=null;
        boolean encontrado=false;
        Iterator<Site> iterator= sites.iterator();
        while (iterator.hasNext()&&!encontrado){
            site=iterator.next();
            if(site.getName().equalsIgnoreCase(name)){
                encontrado=true;
            }
        }
        if(!encontrado){
            throw new NoSuchElementException("No hay un site con ese nombre: "+name);
        }
        return  site;
    }

    public Set<String> getNames(){
        Set<String> names=new HashSet<>();
        for (Site site : sites) {
            names.add(site.getName());
        }
        return names;
    }

    public Set<Site> getSitesLinkedFrom(Site pagina){
        Set<Site> sites = new HashSet<>();
        for(Link link : links){
            if(link.getOrigin().equalsIgnoreCase(pagina.getName())){
                sites.add(getSite(link.getLinked()));
            }
        }
        return sites;
    }

    protected void distribute(Site site, double prize){
        Set<Site> sitesLinked;
        int n;
        if(prize>THRESHOLD){
            site.addRank(prize/2);
            sitesLinked=getSitesLinkedFrom(site);
            n=sitesLinked.size();
            if(n!=0) {
                for (Site site1 : sitesLinked) {
                    distribute(site1, prize / (2 * n));
                }
            }
        }
    }

    public void click(String name){
        try {
            Site site=getSite(name);
            distribute(site, 1);
        }catch (NoSuchElementException ignored ){}
    }

    public void simulateClick(int numClick){
        if(!sites.isEmpty()) {
            ArrayList<String> sites1 = new ArrayList<>();
            sites1.addAll(getNames());
            int numAleatorio;
            for (int i = 0; i < numClick; i++) {
                numAleatorio = alea.nextInt(sites1.size());
                click(sites1.get(numAleatorio));
            }
        }
    }

    public SortedSet<Site> getSitesByName(){
        SortedSet<Site> sites1=new TreeSet<>();
        sites1.addAll(sites);
        return sites1;

    }

    public SortedSet<Site> getSitesByRank(){
        SortedSet<Site> sites1=new TreeSet<>(new CompareByRank());
        sites1.addAll(sites);
        return sites1;
    }

    public String toString(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("Web(");
        StringJoiner stringJoiner=new StringJoiner(", ", "[", "]");
        for(Site site : sites){
            stringJoiner.add(site.toString());
        }
        stringBuilder.append(stringJoiner.toString()+", ");
        stringJoiner=new StringJoiner(", ", "[", "]");
        for(Link link:links){
            stringJoiner.add(link.toString());
        }
        stringBuilder.append(stringJoiner.toString()+")");
        return stringBuilder.toString();
    }
}
