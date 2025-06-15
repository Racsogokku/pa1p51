package rank;

public class WebExtended extends Web{
    public WebExtended(){
        super();
    }
    protected void addSiteWithName(String name){
        addSite(new SiteExtended(name));
    }

    protected void distribute(Site site, double prize){
        SiteExtended siteEX=(SiteExtended) site;
        if(siteEX.isValid()){
            super.distribute(site,prize);
        }
    }
    protected void switchSiteWithName(String name){
        SiteExtended site=(SiteExtended) getSite(name);
        site.setValid(!site.isValid());
    }
}
