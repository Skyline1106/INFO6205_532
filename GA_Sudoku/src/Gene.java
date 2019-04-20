import java.util.Random;

public class Gene {
    public int length;
    public int[] gene;



    public int[] phenotype;
    public double score;

    public int[] getPhenotype() {
        return phenotype;
    }

    public void setPhenotype(int[] phenotype) {
        this.phenotype = phenotype;
    }

    public int getlength() {
        return length;
    }
    public void setlength(int length) {
        this.length=length;
    }
    public int[] getgene() {
        return gene;
    }
    public void setgene(int gene[]) {
        this.gene=gene;
    }
    public double getscore() {
        return score;
    }
    public void setscore(double score) {
        this.score=score;
    }
    public Gene(int length){
        this.length = length;
        gene = new int[length];
        phenotype = new int[length];
        Random rand = new Random();
        for(int i=0;i<length;i++){
            gene[i] = rand.nextInt(2);
        }
        for(int i=0; i<gene.length; i++) {
            phenotype[i] = gene[i];
        }
        this.score=0;
    }
}
