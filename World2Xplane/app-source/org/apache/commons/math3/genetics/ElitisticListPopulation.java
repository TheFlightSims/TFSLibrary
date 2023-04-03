/*     */ package org.apache.commons.math3.genetics;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class ElitisticListPopulation extends ListPopulation {
/*  36 */   private double elitismRate = 0.9D;
/*     */   
/*     */   public ElitisticListPopulation(List<Chromosome> chromosomes, int populationLimit, double elitismRate) {
/*  49 */     super(chromosomes, populationLimit);
/*  50 */     this.elitismRate = elitismRate;
/*     */   }
/*     */   
/*     */   public ElitisticListPopulation(int populationLimit, double elitismRate) {
/*  62 */     super(populationLimit);
/*  63 */     this.elitismRate = elitismRate;
/*     */   }
/*     */   
/*     */   public Population nextGeneration() {
/*  74 */     ElitisticListPopulation nextGeneration = new ElitisticListPopulation(getPopulationLimit(), getElitismRate());
/*  76 */     List<Chromosome> oldChromosomes = getChromosomes();
/*  77 */     Collections.sort(oldChromosomes);
/*  80 */     int boundIndex = (int)FastMath.ceil((1.0D - getElitismRate()) * oldChromosomes.size());
/*  81 */     for (int i = boundIndex; i < oldChromosomes.size(); i++)
/*  82 */       nextGeneration.addChromosome(oldChromosomes.get(i)); 
/*  84 */     return nextGeneration;
/*     */   }
/*     */   
/*     */   public void setElitismRate(double elitismRate) {
/*  96 */     if (elitismRate < 0.0D || elitismRate > 1.0D)
/*  97 */       throw new OutOfRangeException(LocalizedFormats.ELITISM_RATE, Double.valueOf(elitismRate), Integer.valueOf(0), Integer.valueOf(1)); 
/*  99 */     this.elitismRate = elitismRate;
/*     */   }
/*     */   
/*     */   public double getElitismRate() {
/* 107 */     return this.elitismRate;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\genetics\ElitisticListPopulation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */