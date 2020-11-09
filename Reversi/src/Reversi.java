import java.util.HashSet;
import java.util.Scanner;

public class Reversi {
    public static void Zaidejai(Lenta b) {
        Scanner scan = new Scanner(System.in);
        Lenta.Taskas eiti = new Lenta.Taskas(-1, -1);
        System.out.println("Pirmas zaidejas pradeda");

        int rezultatas;
        boolean praleisti;
        String ivestis;

        while (true) {
            praleisti = false;

            HashSet<Lenta.Taskas> pirmoGalimosLokacijos = b.galimosLokacijos('1', '2');
            HashSet<Lenta.Taskas> antroGalimosLokacijos = b.galimosLokacijos('1', '2');

            b.rodytiGalimasLokacijas(pirmoGalimosLokacijos);
            rezultatas = b.zaidimoRezultatas(antroGalimosLokacijos, pirmoGalimosLokacijos);

            if (rezultatas == 0) {
                System.out.println("Lygiosios ");
                break;
            } else if (rezultatas == 1) {
                System.out.println("Antras zaidejas laimejo: " + b.antroTaskai + ":" + b.pirmoTaskai);
                break;
            } else if (rezultatas == -1) {
                System.out.println("Pirmas zaidejas laimejo: " + b.pirmoTaskai + ":" + b.antroTaskai);
                break;
            }

            if (pirmoGalimosLokacijos.isEmpty()) {
                System.out.println("Pirmas zaidejas praleidzia... Perduodama antram zaidejui");
                praleisti = true;
            }

            if (!praleisti) {
                System.out.println("Eina pirmas zaidejas: ");
                ivestis = scan.next();
                eiti.y = b.Koordinates(ivestis.charAt(0));
                eiti.x = (Integer.parseInt(ivestis.charAt(1) + "") - 1);

                while (!pirmoGalimosLokacijos.contains(eiti)) {
                    System.out.println("Negalimas ejimas!\nEina pirmas zaidejas: ");
                    ivestis = scan.next();
                    eiti.y = b.Koordinates(ivestis.charAt(0));
                    eiti.x = Integer.parseInt((ivestis.charAt(1) + "")) - 1;
                }
                b.Ejimas(eiti, '1', '2');
                b.taskuSkaiciavimas();
                System.out.println("\nPirmas zaidejas: " + b.pirmoTaskai + " Antras zaidejas: " + b.antroTaskai);
            }
            praleisti = false;

            antroGalimosLokacijos = b.galimosLokacijos('2', '1');
            pirmoGalimosLokacijos = b.galimosLokacijos('1', '2');

            b.rodytiGalimasLokacijas(antroGalimosLokacijos);
            rezultatas = b.zaidimoRezultatas(antroGalimosLokacijos, pirmoGalimosLokacijos);

            if (rezultatas == 0) {
                System.out.println("Lygiosios ");
                break;
            } else if (rezultatas == 1) {
                System.out.println("Antras zaidejas laimejo " + b.antroTaskai + ":" + b.pirmoTaskai);
                break;
            } else if (rezultatas == -1) {
                System.out.println("Pirmas zaidejas laimejo: " + b.pirmoTaskai + ":" + b.antroTaskai);
                break;
            }

            if (antroGalimosLokacijos.isEmpty()) {
                System.out.println("Antras zaidejas praleidzia... Perduodama pirmam zaidejui");
                praleisti = true;
            }

            if (!praleisti) {
                System.out.println("Eina antras zaidejas: ");
                ivestis = scan.next();
                eiti.y = b.Koordinates(ivestis.charAt(0));
                eiti.x = (Integer.parseInt(ivestis.charAt(1) + "") - 1);

                while (!antroGalimosLokacijos.contains(eiti)) {
                    System.out.println("Negalimas ejimas!\n\nEina antras zaidejas: ");
                    ivestis = scan.next();
                    eiti.y = b.Koordinates(ivestis.charAt(0));
                    eiti.x = (Integer.parseInt(ivestis.charAt(1) + "") - 1);
                }
                b.Ejimas(eiti, '2', '1');
                b.taskuSkaiciavimas();
                System.out.println("\nAntras zaidejas: " + b.antroTaskai + " Pirmas zaidejas: " + b.pirmoTaskai);
            }
        }
    }

    public static void main(String[] args) {
        Lenta b = new Lenta();
        Zaidejai(b);
    }
}
