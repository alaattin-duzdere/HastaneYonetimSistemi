import java.io.IOException;

class Hastane {

    public static void main(String[] args) throws IOException{
        System.out.println("Teststtt");
        Menu menu = new AnaMenu();
        try {
            menu.goster(); // Ana menüyü başlat
        } catch (Exception e) {
            System.out.println("exception");
        }
    }
}