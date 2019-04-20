
import java.util.Random;

public class GeneticOperators {

    public int[] initialize(int[] gene) {
        int dim = (int) Math.sqrt(gene.length);

        for (int i = 0; i < gene.length; i++) {
            if (gene[i] == 0) gene[i] = new Random().nextInt(dim) + 1;
        }

        return gene;
    }

    public static int[] randomMutation(int[] gene) {
        int dim = (int) Math.sqrt(gene.length);
        int[] muta = gene;
        int rand = new Random().nextInt(gene.length);
        for (int i = 0; i < rand; i++)
            muta = mutation(gene, new Random().nextInt(gene.length), new Random().nextInt(dim) + 1);


        return muta;
    }

    public static int[] mutation(int[] gene) {
        int dim = (int) Math.sqrt(gene.length);
        int[] muta = mutation(gene, new Random().nextInt(gene.length), new Random().nextInt(dim) + 1);
            return muta;
        }

    public static int[] mutation(int[] gene, int index, int value) {
        gene[index] = value;

        return gene;
    }

    public static int[] mutatePheno(int[] gene) {
        int dim = (int) Math.sqrt(gene.length);
        int[] muta = mutatePheno(gene, new Random().nextInt(gene.length), new Random().nextInt(dim) + 1);
        return muta;
    }

    public static int[] mutatePheno(int[] gene, int index, int value) {
        gene[index] = value;

        return gene;
    }

    public static int[][] crossover(int[] g1, int[] g2) {
        int start = new Random().nextInt(g1.length);
        int end = new Random().nextInt(g1.length - start) + start;

        return crossover(g1, g2, start, end);
    }

    public static int[][] crossover(int[] g1, int[] g2, boolean sp) {
        int start = (sp) ? 0 : new Random().nextInt(g1.length);
        int end = new Random().nextInt(g1.length - start) + start;

        return crossover(g1, g2, start, end);
    }

    public static int[][] crossover(int[] g1, int[] g2, int start, int end) {
        int[][] newG = new int[2][g1.length];
        for (int i = 0; i < g1.length; i++) {
            newG[0][i] = (i >= start && i <= end) ? g2[i] : g1[i];
            newG[1][i] = (i >= start && i <= end) ? g1[i] : g2[i];
        }
        return newG;
    }

}

