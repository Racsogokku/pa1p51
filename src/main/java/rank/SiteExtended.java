package rank;

public class SiteExtended extends Site{
    private boolean valid;
    public SiteExtended(String nombre){
        super(nombre);
        valid=true;
    }
    public void setValid(boolean b){
        valid = b;
    }

    public boolean isValid(){
        return valid;
    }

    public String toString(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(super.toString());
        if(!valid){
            stringBuilder.append("*");
        }
        return stringBuilder.toString();
    }

}
