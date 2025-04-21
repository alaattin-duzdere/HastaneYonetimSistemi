import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GirisYapMenusu implements Menu{

    @Override
    public void goster() {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("İsim-Soyisim Giriniz;");
        String isim_soyisim = scanner.nextLine();  // Read user input
        if(readDoctor(isim_soyisim)!=null){
            Doktor doktor = readDoctor(isim_soyisim);
            System.out.println("\n\n------Kullanici Bilgileri-----");
            System.out.println("Ad Soyad       :"+  doktor.isim);
            System.out.println("Id             :"+ doktor.id);
            System.out.println("Uzmanlik       :"+ doktor.getUzmanlik());
            System.out.println("Aktiflik Durumu:"+doktor.getEnable());
            System.out.println("\nLutfen islem seciniz:");
            String[] secenek_listesi={"Aktiflik Degistir","Geri Don"};
            switch (girdiAl(secenek_listesi)) {
                case 1:
                    //aktiflik degisecek
                    aktiflikDegis(doktor);
                    System.out.println("Aktiflik Basariyla Guncellendi");
                    break;
                case 2:
                    new DoktorMenu().goster();
                    break;
            }
        }
        else{System.out.println("Girdiginiz isme ait kayit bulunamadi");}
    }
    
    //parametre olarak verilen doktorun aktifligini degistirir.
    public void aktiflikDegis(Doktor doktor){
        List<String> satirlar = new ArrayList<>();
        int sayac=0;

        try(BufferedReader reader = new BufferedReader(new FileReader("doktor_listesi.txt"))){
            String currentline;
            String currentisim_soyisim;
            Integer currentid;
            String currentuzmanlik;
            boolean currentenable;

            String yenisatir=null;

            //girilen kisinin enable durumu tersine cevrilmis bir sekilde o kisi icin yeni bir satir olusturuldu
            //ayni zamanda metin dosyasındaki satirlar satirlar dizisine aktarildi
            while((currentline=reader.readLine()) != null){
                satirlar.add(currentline);
                try {
                    String[] parts= currentline.split(",");
                    if(parts[0].equals(doktor.isim)){
                        //ismi verilen kisinin bilgileri gecici olarak tutuluyor
                        currentisim_soyisim=parts[0];
                        currentid=Integer.parseInt(parts[1]);
                        currentuzmanlik=parts[2];
                        if(parts[3].equals("false")){currentenable=true;}      //enable durumları tam tersi olacak sekilde yazildi
                        else{currentenable=false;}
                        yenisatir=currentisim_soyisim+","+currentid+","+currentuzmanlik+","+currentenable;
                        break;
                    }
                    sayac++;

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

            // degismesi gereken satir diziye set edildi ve dizideki satirlar metin dosyasina bastirildi
            satirlar.set(sayac,yenisatir);
            String dosyaYolu = "doktor_listesi.txt";  

            BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaYolu,false));
            for (String s : satirlar) {
                writer.write(s);
                writer.newLine();
            }
            writer.close();
        }
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

    }
}