import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public interface  Menu {
    void goster();


    //Parametre olarak aldıgı dizideki elemanları secenekler olarak sunar ve kullanıcının girdigi secenegi dondurur
    default Integer girdiAl(String[] secenek_listesi){
        int size=secenek_listesi.length;
        while(true){
            //Secenekleri yazdir
            for(int i=0;i<size;i++){                
                System.out.println((i+1)+"- " +secenek_listesi[i]);
            }
            //Girdi al ve dogru tipte ve aralikta oldugunu kontrol
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                int girdi = scanner.nextInt();
                if(girdi>0 && girdi<=size){
                    //scanner.close();
                    return girdi;
                }
                else{System.err.println("\nLutfen belirtilen sayilardan birini giriniz");}
            } else {
                System.err.println("\nBu bir tamsayi değil! Lutfen bir sayi giriniz");
            }
        }
    }

    //dosyaya yazma işlemi
    default void addDoctor(String isim,int id,String uzmanlik,boolean enable){
        Path path = Paths.get(System.getProperty("user.dir"), "doktor_listesi.txt");
        String dosyaYolu = path.toString();
        String eklenecekMetin = "\n"+isim+","+id+","+uzmanlik+","+enable;  

        try (FileWriter dosyaYazici = new FileWriter(dosyaYolu, true)) {
            dosyaYazici.write(eklenecekMetin); 
            System.out.println("Metin dosyaya başariyla eklendi.");
        } catch (IOException e) {
            System.err.println("Dosyaya yazma hatasi: " + e.getMessage());
        }
    }

    //isim-soyismi verilen kisiyi dondurur. eger bulunamazsa null cevirir
    default Doktor readDoctor(String isim_soyisim){
        Path path = Paths.get(System.getProperty("user.dir"), "doktor_listesi.txt");
        try(BufferedReader reader = new BufferedReader(new FileReader(path.toString()))){
            String currentline;
            String currentisim_soyisim;
            Integer currentyas;
            String currentuzmanlik;
            boolean currentenable;
        
            while((currentline=reader.readLine()) != null){
                try {
                    String[] parts= currentline.split(",");
                    if(parts[0].equalsIgnoreCase(isim_soyisim)){
                        currentisim_soyisim=parts[0];
                        currentyas=Integer.parseInt(parts[1]);
                        currentuzmanlik=parts[2];
                        if(parts[3].equals("false")){currentenable=false;}
                        else{currentenable=true;}
                        Doktor doktor = new Doktor(currentisim_soyisim,currentyas, currentuzmanlik,currentenable);
                        return doktor;
                    }

                } catch (NumberFormatException e) {
                    // Sayıya çevirme hatası olursa bu satırı atla ve hatayı bildir
                    System.err.println("Hata: Geçersiz sayı formatı algılandı. Satır: "+"Hata: " + e.getMessage());
                    // Bu satırı işlemeye devam etme, sonraki satıra geç
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Bu aslında yukarıdaki 'if (parts.length >= 3)' kontrolü ile önleniyor,
                    // ancak yine de bir güvenlik katmanı olarak eklenebilir veya if kontrolüne güvenilebilir.
                    System.err.println("Hata: Satırda beklenenden az bölüm var. Satır: " + " Hata: " + e.getMessage());
                }
            }
        }




        ////Dosyanın acılması ve okunması sırasındaki hatalar
        catch(FileNotFoundException e){
            // Dosya bulunamadığında verilecek hata mesajı
            System.err.println("Hata: 'doktor_listesi.txt' dosyası bulunamadı. " + e.getMessage());
            // Burada programın akışına göre null dönebilir veya başka bir işlem yapabilirsiniz.
        }
        catch(IOException e){
            // Dosya okuma sırasında başka bir G/Ç hatası olursa verilecek mesaj
            System.err.println("Hata: Dosya okunurken bir G/Ç hatası oluştu. " + e.getMessage());
        }
        catch (Exception e) {
            // Beklenmedik diğer tüm hatalar için genel bir yakalama bloğu (isteğe bağlı)
            System.err.println("Beklenmedik bir hata oluştu: " + e.getMessage());
            e.printStackTrace(); // Hatanın detaylı dökümünü verir, hata ayıklama için faydalıdır.
        }

        return null;     //belirtilen doktorun bulunamaması durumu
    }
}
