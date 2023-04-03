/*     */ package org.apache.commons.math3.genetics;
/*     */ 
/*     */ public abstract class Chromosome implements Comparable<Chromosome>, Fitness {
/*     */   private static final double NO_FITNESS = -InfinityD;
/*     */   
/*  33 */   private double fitness = Double.NEGATIVE_INFINITY;
/*     */   
/*     */   public double getFitness() {
/*  45 */     if (this.fitness == Double.NEGATIVE_INFINITY)
/*  47 */       this.fitness = fitness(); 
/*  49 */     return this.fitness;
/*     */   }
/*     */   
/*     */   public int compareTo(Chromosome another) {
/*  65 */     return Double.valueOf(getFitness()).compareTo(Double.valueOf(another.getFitness()));
/*     */   }
/*     */   
/*     */   protected boolean isSame(Chromosome another) {
/*  76 */     return false;
/*     */   }
/*     */   
/*     */   protected Chromosome findSameChromosome(Population population) {
/*  89 */     for (Chromosome anotherChr : population) {
/*  90 */       if (isSame(anotherChr))
/*  91 */         return anotherChr; 
/*     */     } 
/*  94 */     return null;
/*     */   }
/*     */   
/*     */   public void searchForFitnessUpdate(Population population) {
/* 104 */     Chromosome sameChromosome = findSameChromosome(population);
/* 105 */     if (sameChromosome != null)
/* 106 */       this.fitness = sameChromosome.getFitness(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\genetics\Chromosome.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */