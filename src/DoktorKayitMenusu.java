import java.util.Scanner;


public class DoktorKayitMenusu implements Menu{

    @Override
    public void goster() {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("İsim-Soyisim Giriniz;");
        String isim_soyisim = scanner.nextLine();  // Read user input
        System.out.println("Id Giriniz;");
        Integer id = scanner.nextInt();  // Read user input
        scanner.nextLine();
        System.out.println("Uzmanlik Giriniz;");
        String uzmanlik = scanner.nextLine();  // Read user input
        System.out.println("Aktif Olma Durumu Giriniz;");
        String[] secenek={"Aktif","Aktif Degil"};
        boolean enable;
        if(girdiAl(secenek)==1){enable =true;}
        else{enable = false;}  // Read user input
        Doktor doktor=new Doktor(isim_soyisim,id,uzmanlik,enable);

        //Girilen verilere ait bir doktor olup olmadıgını kontrol ediyoruz
        if(readDoctor(isim_soyisim)!=null){
            System.out.println("Bu kullanici zaten var. Lutfen giris yapiniz");
        }
        else{
            addDoctor(isim_soyisim, id, uzmanlik,enable);
        }
    }
    
}
