import java.util.Random;

public class SimuladorEcosistema {
    public static void main(String[] args) {
        char[][] ecosistema = new char[20][20];
        Random random = new Random();
         final int NUMERO_DIAS=20;
         final double NUMERO1_PROBABILIDAD=0.1;
         final double NUMERO2_PROBABILIDAD=0.2;
         final int NUMERO1_VECINOS =2;
         final int NUMERO2_VECINOS=3;
         final double NUMERO3_VECINOS=0.01;
        // Inicializar ecosistema
        for (int i = 0; i < NUMERO_DIAS; i++) {
            for (int j = 0; j < NUMERO_DIAS; j++) {
                double prob = random.nextDouble();
                if (prob < NUMERO1_PROBABILIDAD) ecosistema[i][j] = 'A'; // Árbol
                else if (prob < NUMERO2_PROBABILIDAD) ecosistema[i][j] = 'H'; // Hierba
                else ecosistema[i][j] = ' '; // Vacío
            }
        }

        // Simulación por 20 días
        for (int dia = 1; dia <= NUMERO_DIAS; dia++) {
            System.out.println("Día " + dia);
            
            // Mostrar ecosistema
            for (int i = 0; i < NUMERO_DIAS; i++) {
                for (int j = 0; j < NUMERO_DIAS; j++) {
                    System.out.print(ecosistema[i][j] + " ");
                }
                System.out.println();
            }

            // Actualizar ecosistema
            char[][] nuevoEcosistema = new char[20][20];
            for (int i = 0; i < NUMERO_DIAS; i++) {
                for (int j = 0; j < NUMERO_DIAS; j++) {
                    // Contar vecinos (integrado directamente aquí)
                    int vecinos = 0;
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            if (di == 0 && dj == 0) continue;
                            int ni = (i + di + NUMERO_DIAS) % NUMERO_DIAS;
                            int nj = (j + dj + NUMERO_DIAS) % NUMERO_DIAS;
                            if (ecosistema[ni][nj] == 'A') vecinos++;
                        }
                    }

                    switch (ecosistema[i][j]) {
                        case 'A' -> {
                            if (vecinos < NUMERO1_VECINOS || vecinos > NUMERO2_VECINOS) nuevoEcosistema[i][j] = 'H';
                            else nuevoEcosistema[i][j] = 'A';
                        }
                        case 'H' -> {
                            if (vecinos == NUMERO2_VECINOS) nuevoEcosistema[i][j] = 'A';
                            else nuevoEcosistema[i][j] = 'H';
                        }
                        default -> {
                            if (random.nextDouble() < NUMERO3_VECINOS) nuevoEcosistema[i][j] = 'H';
                            else nuevoEcosistema[i][j] = ' ';
                        }
                    }
                }
            }
            ecosistema = nuevoEcosistema;
            final int millas=1000;

            // Pausa entre días
            try {
                Thread.sleep(millas);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
