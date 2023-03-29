package mutaciones;

import java.util.Random;

import Funciones.Individuo;
import main.AlgoritmoGenetico;

public class MetodoPropioMut {
    public static void metodopropiomut(AlgoritmoGenetico alg) {//shuffle
        Random rand = new Random();
        Individuo[] pob = alg.getPoblacion();
        for (int i = 0; i < alg.getTamPoblacion(); i++) {
            if (alg.getProbMutacion() >= rand.nextDouble()) {
                shuffleArray(pob[i].getCrom(),rand);
            }
        }

    }

    static void shuffleArray(int[] ar, Random rnd)//Durstenfeld shuffle O(n) complexity
      {
        for (int i = ar.length - 1; i > 0; i--)
        {
          int index = rnd.nextInt(i + 1);
          // Simple swap
          int a = ar[index];
          ar[index] = ar[i];
          ar[i] = a;
        }
      }
}