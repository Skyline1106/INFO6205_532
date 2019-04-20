import java.util.Random;
import java.util.Vector;

public class Sudoku {
    private static int[] initialGene;
    private int[] gene;
    private int[] phenotype;
    private int fitnessValue;
    private double probability;

    public Sudoku(int[] gene) {
        this.gene = gene;
        this.phenotype = gene;
        fitnessValue = fitness();

    }


    public static Sudoku bestSelection(Vector<Sudoku> sudokus) {
        Sudoku min = sudokus.firstElement();
        for (Sudoku sudoku : sudokus)
            if (sudoku.fitnessValue < min.fitnessValue)
                min = sudoku;

        return min;
    }
//    public void setgene(int gene[]) {
//        this.gene=gene;
//    }
//    GeneticOperators geneticOperators = new GeneticOperators();

    public static Sudoku rouletteSelection(Vector<Sudoku> sudokus) {
        int max = 0;
        for (Sudoku sudoku : sudokus)
            if (sudoku.fitnessValue > max) max = sudoku.fitnessValue;
//        int sum = 0;
//        for (Sudoku sudoku : sudokus)
//            sum += sudoku.getFitnessValue();
//        for (Sudoku sudoku : sudokus)
//            sudoku.setProbability(sudoku.getFitnessValue() / (sum * 1.0));

        int sum = 0;
        for (Sudoku sudoku : sudokus)
            sum += max - sudoku.fitnessValue;
        for (Sudoku sudoku : sudokus)
            sudoku.setProbability((max - sudoku.fitnessValue) / (sum * 1.0));

        double random = Math.random() * sum;
        int i;
        for (i = 0; i < sudokus.size() && random > 0; i++) {
            random -= max - sudokus.get(i).fitnessValue;
        }
        return sudokus.get(i - 1);
    }

    public void randomMutation() {
        int[] muta = GeneticOperators.randomMutation(this.getGene());
        this.setGene(muta);
    }

    public void mutation() {
        int[] muta = GeneticOperators.mutation(this.getGene());
        this.setGene(muta);
    }

    public void mutation(int index, int value) {
        int[] muta = GeneticOperators.mutation(this.getGene(), index, value);
        this.setGene(muta);
    }

    public static void randomMutation(Sudoku sudoku) {
        int[] muta = GeneticOperators.randomMutation(sudoku.getGene());
        sudoku.setGene(muta);
    }

    public static void mutation(Sudoku sudoku) {
        int[] muta = GeneticOperators.mutation(sudoku.getGene());
        sudoku.setGene(muta);
    }

    public static void mutation(Sudoku sudoku, int index, int value) {
        int[] muta= GeneticOperators.mutation(sudoku.getGene(), index, value);
        sudoku.setGene(muta);
    }

    public void mutatePheno() {
        int[] muta = GeneticOperators.mutatePheno(this.getPheno());
        this.setPheno(muta);
    }

    public void crossover(Sudoku sudoku1, boolean singlePoint) {
        int[][] co = GeneticOperators.crossover(this.getGene(), sudoku1.getGene(), singlePoint);
        this.setGene(co[0]);
        sudoku1.setGene(co[1]);
    }

    public void crossover(Sudoku sudoku1) {
        int[][] co = GeneticOperators.crossover(this.getGene(), sudoku1.getGene());
        this.setGene(co[0]);
        sudoku1.setGene(co[1]);
    }

    public void crossover(Sudoku sudoku1, int start, int end) {
        int[][] co = GeneticOperators.crossover(this.getGene(), sudoku1.getGene(), start, end);
        this.setGene(co[0]);
        sudoku1.setGene(co[1]);
    }

    public static void crossover(Sudoku sudoku, Sudoku sudoku1, boolean singlePoint) {
        int[][] co = GeneticOperators.crossover(sudoku.getGene(), sudoku1.getGene(), singlePoint);
        sudoku.setGene(co[0]);
        sudoku1.setGene(co[1]);
    }

    public static void crossover(Sudoku sudoku, Sudoku sudoku1) {
        int[][] co = GeneticOperators.crossover(sudoku.getGene(), sudoku1.getGene());
        sudoku.setGene(co[0]);
        sudoku1.setGene(co[1]);
    }

    public static void crossover(Sudoku sudoku, Sudoku sudoku1, int start, int end) {
        int[][] co = GeneticOperators.crossover(sudoku.getGene(), sudoku1.getGene(), start, end);
        sudoku.setGene(co[0]);
        sudoku1.setGene(co[1]);
    }

    public int fitness() {
        //Random rand = new Random();

        double prob = Math.random();
        //System.out.println(prob);

        if (prob<0.1) {
            this.mutatePheno();
        }


        return fitness(this.phenotype);
        //return fitness(this.gene);
    }

    public static int fitness(int[] gene) {
        int ft = 0;

        int[][] newG = oneTotwo(gene);
        int[][] newInitialGene = oneTotwo(initialGene);
        for (int i = 0; i < newG.length; i++) {
            boolean[] rowFlag = new boolean[newG.length + 1];
            boolean[] colFlag = new boolean[newG.length + 1];
            for (int j = 0; j < newG.length; j++) {
                if (rowFlag[newG[i][j]])
                    ft++;
                if (colFlag[newG[j][i]])
                    ft++;
                if ((newInitialGene[i][j] != 0 && newInitialGene[i][j] != newG[i][j]) || newG[i][j] == 0)
                    ft += 10;

                rowFlag[newG[i][j]] = true;
                colFlag[newG[j][i]] = true;
            }
        }
        int blockSize = (int) Math.sqrt(newG.length);
        for (int i = 0; i < newG.length; i += blockSize) {
            for (int j = 0; j < newG.length; j += blockSize) {
                boolean[] blockFlag = new boolean[newG.length + 1];

                for (int k = 0; k < blockSize; k++) {
                    for (int l = 0; l < blockSize; l++) {
                        if (blockFlag[newG[i + k][j + l]])
                            ft++;
                        blockFlag[newG[i + k][j + l]] = true;
                    }
                }
            }
        }
        return ft;
    }

    //Transform from 2 dimension to 1 dimension list
    public static int[] twoToOne(int[][] twoD) {
        int[] oneD = new int[twoD.length * twoD.length];
        for (int i = 0; i < oneD.length; i++)
            oneD[i] = twoD[i / twoD.length][i % twoD.length];

        return oneD;
    }

    //Transform from 1 dimension to 2 dimension list
    public static int[][] oneTotwo(int[] oneD) {
   //     System.out.println(Math.sqrt(oneD.length));
        int[][] twoD = new int[(int) Math.sqrt(oneD.length)][(int) Math.sqrt(oneD.length)];
        for (int i = 0; i < oneD.length ; i++)
            twoD[i / twoD.length][i % twoD.length] = oneD[i];

        return twoD;
    }

    public static int[] getInitialGene() {
        return initialGene;
    }

    public static void setInitialGene(int[] initialGene) {
        Sudoku.initialGene = initialGene;
    }

    public int[] getGene() {
        return gene;
    }

    public void setGene(int[] gene) {
        this.gene = gene;
        //this.mutation();
        fitnessValue = fitness();
    }

    public int[] getPheno() {
        return phenotype;
    }

    public void setPheno(int[] pheno) {
        this.phenotype = pheno;
        //this.mutatePheno();
        //fitnessValue = fitness();
    }

    public int getFitnessValue() {
        return fitnessValue;
    }

    public void setFitnessValue(int fitnessValue) {
        this.fitnessValue = fitnessValue;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        String string = "Gene: ";
        int dimensions = (int) Math.sqrt(gene.length);
        for (int i : gene) {
            string += i;
        }
        for (int i = 0; i < gene.length; i++)
            string += ((i % dimensions == 0) ? "\n" : "") + gene[i] + " ";
        //string += "\nFitness: " + fitnessValue;

        string += "\nPhenoType:";
        for (int i : phenotype) {
            string += i;
        }
        for (int i = 0; i < phenotype.length; i++)
            string += ((i % dimensions == 0) ? "\n" : "") + phenotype[i] + " ";
        string += "\nFitness: " + fitnessValue;

        return string;
    }
}