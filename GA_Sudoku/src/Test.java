
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Vector;

class Test {
    Gene testgene = new Gene(6);
    int[] testarray= {0,0,1,1,0,1,2,0,1};
    int[] originalarray= {0,0,1,1,0,1,2,0,1};


    @org.junit.jupiter.api.Test
    void testgene() {


        testgene.setgene(testarray);
        assertEquals(testgene.getgene()[0], 0);
        assertEquals(testgene.getgene()[1], 0);
        assertEquals(testgene.getgene()[2], 1);
        assertEquals(testgene.getgene()[3], 1);
        assertEquals(testgene.getgene()[4], 0);
        assertEquals(testgene.getgene()[5], 1);
    }

    @org.junit.jupiter.api.Test
    void testinitialize() {
        GeneticOperators ge =new GeneticOperators();
        int [] curgene = ge.initialize(testarray);
        assertEquals(curgene.length, originalarray.length);
        Assertions.assertNotEquals(curgene[0],originalarray[0]);
        Assertions.assertNotEquals(curgene[1],originalarray[1]);
        Assertions.assertNotEquals(curgene[4],originalarray[4]);
        Assertions.assertNotEquals(curgene[7],originalarray[7]);

    }

    @org.junit.jupiter.api.Test
    void testcrossover() {
        GeneticOperators ge = new GeneticOperators();

        int [] curgene = ge.initialize(testarray);
        int[][] crossgene = GeneticOperators.crossover(originalarray,curgene,3,5);
        for(int i=3; i<5; i++){
            assertEquals(crossgene[0][i],curgene[i]);
            assertEquals(crossgene[1][i],originalarray[i]);
        }

    }

    @org.junit.jupiter.api.Test
    void testSelection() {
        GeneticOperators ge = new GeneticOperators();
        Vector<Sudoku> sudokus = new Vector<>();
        Sudoku.setInitialGene(testarray);
        int[] curgene = ge.initialize(testarray);
        Sudoku currenS = new Sudoku(curgene);
     //   Sudoku currentSudoku = new Sudoku(ge.initialize(Sudoku.getInitialGene().clone()));
        sudokus.add(new Sudoku(currenS.getGene()));
        int maxfv = Sudoku.fitness(curgene);
        assertEquals(currenS.bestSelection(sudokus).getFitnessValue(),maxfv);

    }

    @org.junit.jupiter.api.Test
    void mutation() {

        GeneticOperators ge = new GeneticOperators();
        int [] curgene = ge.initialize(testarray);
        for(int i=0; i<curgene.length; i++) {
            System.out.println(curgene[i]);
        }

        Sudoku.setInitialGene(testarray);
        Sudoku testmt =new Sudoku(curgene);
        testmt.mutation(2,3);
        Assertions.assertNotEquals(testarray[2],1);

    }

    @org.junit.jupiter.api.Test
    void mutation2() {

        GeneticOperators ge1 = new GeneticOperators();
        int [] curgene1 = ge1.initialize(testarray);


        Sudoku.setInitialGene(testarray);
        Sudoku testmt1 =new Sudoku(curgene1);
        testmt1.mutation(1,2);
        Assertions.assertNotEquals(testarray[1],1);

    }




}
