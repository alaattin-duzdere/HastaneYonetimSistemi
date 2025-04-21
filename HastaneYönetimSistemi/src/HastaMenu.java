
public class HastaMenu implements Menu{

    @Override
    public void goster() {
        while(true){
            System.out.println("\nGiris tipi seciniz: ");  
            String[] options1= {"Hasta secenegi1","Hasta secenegi 2",};
            int secenek=girdiAl(options1);
            System.out.println("secenek: "+secenek);
            break;
        }
    }
    
}
