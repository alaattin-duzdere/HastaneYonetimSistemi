
public class AnaMenu implements Menu{


    @Override
    public void goster() {
        while (true) {
            //giris tipi secimi 
            System.out.println("\nGiris tipi seciniz: ");  
            String[] options1= {"Hasta","Doktor","Cikis"};
            int secenek=girdiAl(options1);
            System.out.println("secenek: "+secenek);

            //doktor ve hasta menusune yonlendirme
            if(secenek==1){
                new HastaMenu().goster();
                break;
            }
            if(secenek==2){
                new DoktorMenu().goster();
                break;
            }
            if(secenek==3){
                break;
            }
        }
    }
}
