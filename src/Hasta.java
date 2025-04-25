public class Hasta extends Kullanici{
    private String hastalik;
    private int yas;
    private String cinsiyet;

    //Constructor metodlar
    Hasta(String isim,int sifre,String hastalik,int yas,String cinsiyet){
        this.isim = isim;
        this.id= id;
        this.hastalik= hastalik;
        this.yas=yas;
        this.cinsiyet=cinsiyet;
    }

    //getter
    public String getIsim() {
        return isim;
    }

    public int getId() {
        return id;
    }

    public String getHastalik() {
        return hastalik;
    }
    public int getYas() {
        return yas;
    }
    public String getCinsiyet() {
        return cinsiyet;
    }

    //Setter
    public void setIsim(String isim) {
        this.isim = isim;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setHastalik(String hastalik) {
        this.hastalik = hastalik;
    }
    public void setYas(int yas) {
        this.yas = yas;
    }
    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }
}