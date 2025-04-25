
public class HastaMenu implements Menu{

    @Override
    public void goster() {
        while(true){
            System.out.println("\nGiris tipi seciniz: ");  
            String[] options1= {"Randevu Al","Randevu Kontrol","Geri Don"};
            int secenek=girdiAl(options1);
            System.out.println("secenek: "+secenek);
            switch (secenek) {
                case 1:
                    System.out.println("Randevu Alma menusu");
                    new RandevuAlmaMenusu().goster();
                    break;
                case 2:
                    System.out.println("Randevu Kontrol");
                    break;
                case 3:
                    System.out.println("Ana menuye donuldu");
                    new AnaMenu().goster();              //Geri Don secenegi ile AnaMenu'ye yonlendirme
                    break;
            }
        }
    }
    
}
