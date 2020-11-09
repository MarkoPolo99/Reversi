import java.util.HashSet;
import java.util.Set;

class Lenta {

    public static class Taskas {
        int x, y;

        Taskas(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[" + x + ", " + y + "]";
        }

        @Override
        public boolean equals(Object o) {
            return o.hashCode() == this.hashCode();
        }

        @Override
        public int hashCode() {
            return Integer.parseInt(x + "" + y);
        }
    }

    private final char[][] lenta;
    int antroTaskai, pirmoTaskai, likutis;
    private final char[] lentaX = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

    public Lenta() {
        lenta = new char[][]{
                {'_', '_', '_', '_', '_', '_', '_', '_',},
                {'_', '_', '_', '_', '_', '_', '_', '_',},
                {'_', '_', '_', '_', '_', '_', '_', '_',},
                {'_', '_', '_', '2', '1', '_', '_', '_',},
                {'_', '_', '_', '1', '2', '_', '_', '_',},
                {'_', '_', '_', '_', '_', '_', '_', '_',},
                {'_', '_', '_', '_', '_', '_', '_', '_',},
                {'_', '_', '_', '_', '_', '_', '_', '_',},
        };
    }

    private void tinkamosLokacijosPaieska(char zaidejas, char priesininkas, HashSet<Taskas> tinkamaLokacija) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (lenta[i][j] == priesininkas) {
                    int I = i, J = j;
                    if (i - 1 >= 0 && j - 1 >= 0 && lenta[i - 1][j - 1] == '_') {
                        i = i + 1;
                        j = j + 1;
                        while (i < 7 && j < 7 && lenta[i][j] == priesininkas) {
                            i++;
                            j++;
                        }
                        if (i <= 7 && j <= 7 && lenta[i][j] == zaidejas) tinkamaLokacija.add(new Taskas(I - 1, J - 1));
                    }
                    i = I;
                    j = J;
                    if (i - 1 >= 0 && lenta[i - 1][j] == '_') {
                        i = i + 1;
                        while (i < 7 && lenta[i][j] == priesininkas) i++;
                        if (i <= 7 && lenta[i][j] == zaidejas) tinkamaLokacija.add(new Taskas(I - 1, J));
                    }
                    i = I;
                    if (i - 1 >= 0 && j + 1 <= 7 && lenta[i - 1][j + 1] == '_') {
                        i = i + 1;
                        j = j - 1;
                        while (i < 7 && j > 0 && lenta[i][j] == priesininkas) {
                            i++;
                            j--;
                        }
                        if (i <= 7 && j >= 0 && lenta[i][j] == zaidejas) tinkamaLokacija.add(new Taskas(I - 1, J + 1));
                    }
                    i = I;
                    j = J;
                    if (j - 1 >= 0 && lenta[i][j - 1] == '_') {
                        j = j + 1;
                        while (j < 7 && lenta[i][j] == priesininkas) j++;
                        if (j <= 7 && lenta[i][j] == zaidejas) tinkamaLokacija.add(new Taskas(I, J - 1));
                    }
                    j = J;
                    if (j + 1 <= 7 && lenta[i][j + 1] == '_') {
                        j = j - 1;
                        while (j > 0 && lenta[i][j] == priesininkas) j--;
                        if (j >= 0 && lenta[i][j] == zaidejas) tinkamaLokacija.add(new Taskas(I, J + 1));
                    }
                    j = J;
                    if (i + 1 <= 7 && j - 1 >= 0 && lenta[i + 1][j - 1] == '_') {
                        i = i - 1;
                        j = j + 1;
                        while (i > 0 && j < 7 && lenta[i][j] == priesininkas) {
                            i--;
                            j++;
                        }
                        if (i >= 0 && j <= 7 && lenta[i][j] == zaidejas) tinkamaLokacija.add(new Taskas(I + 1, J - 1));
                    }
                    i = I;
                    j = J;
                    if (i + 1 <= 7 && lenta[i + 1][j] == '_') {
                        i = i - 1;
                        while (i > 0 && lenta[i][j] == priesininkas) i--;
                        if (i >= 0 && lenta[i][j] == zaidejas) tinkamaLokacija.add(new Taskas(I + 1, J));
                    }
                    i = I;
                    if (i + 1 <= 7 && j + 1 <= 7 && lenta[i + 1][j + 1] == '_') {
                        i = i - 1;
                        j = j - 1;
                        while (i > 0 && j > 0 && lenta[i][j] == priesininkas) {
                            i--;
                            j--;
                        }
                        if (i >= 0 && j >= 0 && lenta[i][j] == zaidejas) tinkamaLokacija.add(new Taskas(I + 1, J + 1));
                    }
                    i = I;
                    j = J;
                }
            }
        }
    }

    public void lentosAtvaizdavimas(Lenta b) {
        System.out.print("\n  ");
        for (int i = 0; i < 8; ++i) System.out.print(lentaX[i] + " ");
        System.out.println();
        for (int i = 0; i < 8; ++i) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; ++j)
                System.out.print(b.lenta[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    public int zaidimoRezultatas(Set<Taskas> whitePlaceableLocations, Set<Taskas> blackPlaceableLocations) {
        taskuSkaiciavimas();
        if (likutis == 0) { //Lygiosios
            return Integer.compare(antroTaskai, pirmoTaskai);
        }
        if (antroTaskai == 0 || pirmoTaskai == 0) {
            if (antroTaskai > 0) return 1;
            else if (pirmoTaskai > 0) return -1;
        }
        if (whitePlaceableLocations.isEmpty() && blackPlaceableLocations.isEmpty()) { //Lygiosios
            return Integer.compare(antroTaskai, pirmoTaskai);
        }
        return -2;
    }

    public HashSet<Taskas> galimosLokacijos(char zaidejas, char priesininkas) {
        HashSet<Taskas> tinkamaLokacija = new HashSet<>();
        tinkamosLokacijosPaieska(zaidejas, priesininkas, tinkamaLokacija);
        return tinkamaLokacija;
    }

    public void rodytiGalimasLokacijas(HashSet<Taskas> locations) {
        for (Taskas p : locations)
            lenta[p.x][p.y] = '*';
        lentosAtvaizdavimas(this);
        for (Taskas p : locations)
            lenta[p.x][p.y] = '_';
    }
    
    public void Ejimas(Taskas p, char zaidejas, char priesininkas) {
        int i = p.x, j = p.y;
        lenta[i][j] = zaidejas;
        int I = i, J = j;

        if (i - 1 >= 0 && j - 1 >= 0 && lenta[i - 1][j - 1] == priesininkas) {
            i = i - 1;
            j = j - 1;
            while (i > 0 && j > 0 && lenta[i][j] == priesininkas) {
                i--;
                j--;
            }
            if (i >= 0 && j >= 0 && lenta[i][j] == zaidejas) {
                while (i != I - 1 && j != J - 1) lenta[++i][++j] = zaidejas;
            }
        }
        i = I;
        j = J;
        if (i - 1 >= 0 && lenta[i - 1][j] == priesininkas) {
            i = i - 1;
            while (i > 0 && lenta[i][j] == priesininkas) i--;
            if (i >= 0 && lenta[i][j] == zaidejas) {
                while (i != I - 1) lenta[++i][j] = zaidejas;
            }
        }
        i = I;
        if (i - 1 >= 0 && j + 1 <= 7 && lenta[i - 1][j + 1] == priesininkas) {
            i = i - 1;
            j = j + 1;
            while (i > 0 && j < 7 && lenta[i][j] == priesininkas) {
                i--;
                j++;
            }
            if (i >= 0 && j <= 7 && lenta[i][j] == zaidejas) {
                while (i != I - 1 && j != J + 1) lenta[++i][--j] = zaidejas;
            }
        }
        i = I;
        j = J;
        if (j - 1 >= 0 && lenta[i][j - 1] == priesininkas) {
            j = j - 1;
            while (j > 0 && lenta[i][j] == priesininkas) j--;
            if (j >= 0 && lenta[i][j] == zaidejas) {
                while (j != J - 1) lenta[i][++j] = zaidejas;
            }
        }
        j = J;
        if (j + 1 <= 7 && lenta[i][j + 1] == priesininkas) {
            j = j + 1;
            while (j < 7 && lenta[i][j] == priesininkas) j++;
            if (j <= 7 && lenta[i][j] == zaidejas) {
                while (j != J + 1) lenta[i][--j] = zaidejas;
            }
        }
        j = J;
        if (i + 1 <= 7 && j - 1 >= 0 && lenta[i + 1][j - 1] == priesininkas) {
            i = i + 1;
            j = j - 1;
            while (i < 7 && j > 0 && lenta[i][j] == priesininkas) {
                i++;
                j--;
            }
            if (i <= 7 && j >= 0 && lenta[i][j] == zaidejas) {
                while (i != I + 1 && j != J - 1) lenta[--i][++j] = zaidejas;
            }
        }
        i = I;
        j = J;
        if (i + 1 <= 7 && lenta[i + 1][j] == priesininkas) {
            i = i + 1;
            while (i < 7 && lenta[i][j] == priesininkas) i++;
            if (i <= 7 && lenta[i][j] == zaidejas) {
                while (i != I + 1) lenta[--i][j] = zaidejas;
            }
        }
        i = I;

        if (i + 1 <= 7 && j + 1 <= 7 && lenta[i + 1][j + 1] == priesininkas) {
            i = i + 1;
            j = j + 1;
            while (i < 7 && j < 7 && lenta[i][j] == priesininkas) {
                i++;
                j++;
            }
            if (i <= 7 && j <= 7 && lenta[i][j] == zaidejas) while (i != I + 1 && j != J + 1) lenta[--i][--j] = zaidejas;
        }
    }

    public void taskuSkaiciavimas() {
        antroTaskai = 0;
        pirmoTaskai = 0;
        likutis = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (lenta[i][j] == '2') antroTaskai++;
                else if (lenta[i][j] == '1') pirmoTaskai++;
                else likutis++;
            }
        }
    }

    public int Koordinates(char x) { //ejimas negalimas
        for (int i = 0; i < 8; ++i)
            if (lentaX[i] == Character.toLowerCase(x) || lentaX[i] == Character.toUpperCase(x)) return i;
        return -1;
    }
}

