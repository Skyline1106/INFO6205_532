import java.util.Random;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        GeneticOperators geneOps = new GeneticOperators();
        int popSize = 1000;
        int its = 0;
        int maxIts = 10000;

//        Sudoku.setInitialGene(new int[]{1, 0, 0, 4, 0, 0, 0, 0, 0, 3, 2, 0, 0, 0, 0, 0});
        //Sudoku.setInitialGene(new int[]{5,2,4,0,6,0,1,9,3,
        Sudoku.setInitialGene(new int[]{
                5,2,4,0,6,0,1,9,3,
                8,0,0,0,1,5,0,0,7,
                7,0,1,3,9,2,5,0,4,
                9,0,0,8,3,7,0,0,6,
                6,3,0,2,4,9,0,1,5,
                2,0,0,1,5,6,0,0,8,
                3,0,2,9,7,4,8,0,1,
                1,0,0,6,8,0,0,0,2,
                4,8,6,0,2,0,3,7,9});
        Sudoku currentSu = new Sudoku(geneOps.initialize(Sudoku.getInitialGene().clone()));

        while (currentSu.getFitnessValue() != 0 && its < maxIts) {
            System.out.println("Current " + currentSu + ", iteration: " + its + "\n");

            Vector<Sudoku> pop = new Vector<>();

            for (int i = 0; i < popSize; i++) pop.add(new Sudoku(currentSu.getGene().clone()));

            for (int i = 0; i < pop.size(); i++) {
                double probM = Math.random();
                double probC = Math.random();

                if (probM>0.1)
                    pop.get(i).mutation();

                if (probC<0.5)
                    pop.get(i).crossover(pop.get(rand.nextInt(pop.size())));
            }
            //if (rand.nextBoolean())
            double probS = Math.random();
            if (probS>0.5)
                currentSu = Sudoku.bestSelection(pop);
            else
                currentSu = Sudoku.rouletteSelection(pop);
            its++;
        }


        System.out.println("Solution " + currentSu + ", iteration: " + its);

    }


}