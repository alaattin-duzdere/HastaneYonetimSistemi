import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RandevuAlmaMenusu implements Menu{
    @Override
    public void goster() {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("İsim-Soyisim Giriniz;");
        String isim_soyisim = scanner.nextLine();  // Read user input
        System.out.println("Uzmanlik Seciniz;");
        String uzmanlik = scanner.nextLine();  // Read user input

        if(AktifDoktorlar(uzmanlik)==null){
            System.out.println("Aktif doktor bulunamadi");
            return;
        }
        else{
            System.out.println("Aktif doktorlar:");
            int secenek=girdiAl(AktifDoktorlar(uzmanlik));
            System.out.println("secenek: "+secenek);
        }
    }

    public String[] AktifDoktorlar(String uzmanlik) {
        List<String> doktorlar = new ArrayList<>();

        Path path = Paths.get(System.getProperty("user.dir"), "doktor_listesi.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {
            String currentline;

            while ((currentline = reader.readLine()) != null) {
                try {
                    String[] parts = currentline.split(",");
                    if (parts[3].equals("true")&&parts[2].equals(uzmanlik)) {
                        String currentisim_soyisim = parts[0];
                        int currentyas = Integer.parseInt(parts[1]);
                        String currentuzmanlik = parts[2];
                        // Doktor bilgilerini String olarak listeye ekle
                        doktorlar.add(currentisim_soyisim + ", " + currentyas + ", " + currentuzmanlik);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Hata: Geçersiz sayı formatı algılandı. Satır: " + e.getMessage());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Hata: Satırda beklenenden az bölüm var. Hata: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Hata: 'doktor_listesi.txt' dosyası bulunamadı. " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Hata: Dosya okunurken bir G/Ç hatası oluştu. " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Beklenmedik bir hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }

        if (doktorlar.isEmpty()) {
            return null;
        } else {
            return doktorlar.toArray(new String[0]);
        }
    }
}
