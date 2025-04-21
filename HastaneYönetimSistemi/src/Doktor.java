public class Doktor extends Kullanici{
    private String uzmanlik;
    private boolean enable;

    //Constructorlar
    Doktor(String isim, int id,String uzmanlik,boolean enable){
        this.isim = isim;
        this.id= id;
        this.uzmanlik = uzmanlik;
        this.enable= enable;
    }

    //Getter Metodlari
    public String getIsim() {
        return isim;
    }

    public int getSifre() {
        return id;
    }
    public String getUzmanlik() {
        return uzmanlik;
    }
    public boolean getEnable() {
        return enable;
    }
    

    //Setter Metodlari
    public void setIsim(String isim) {
        this.isim = isim;
    }

    public void setSifre(int sifre) {
        this.id = id;
    }
    public void setUzmanlik(String uzmanlik) {
        this.uzmanlik = uzmanlik;
    }
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

}