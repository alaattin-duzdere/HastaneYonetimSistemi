public class DoktorMenu implements Menu {
    @Override
    public void goster() {
        while(true){
            System.out.println("\nİslem Seciniz: ");  
            String[] options1= {"Giris Yap","Kayit Ol","Geri Don"};
            int secenek=girdiAl(options1);
            System.out.println("secenek: "+secenek);
            switch(secenek) {
                case 1:
                    new GirisYapMenusu().goster();       //GirisYapMenusu ne yonlendirme
                    break;
                case 2:
                    new DoktorKayitMenusu().goster();    //DoktorKayitMenusu ne yonlendirme
                    break;
                case 3:
                    new AnaMenu().goster();              //Geri Don secenegi ile AnaMenu'ye yonlendirme
                    break;
            }
        }
    }
}
