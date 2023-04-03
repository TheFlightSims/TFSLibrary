/*     */ package org.apache.commons.math3.genetics;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math3.exception.NotPositiveException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public abstract class ListPopulation implements Population {
/*     */   private List<Chromosome> chromosomes;
/*     */   
/*     */   private int populationLimit;
/*     */   
/*     */   public ListPopulation(List<Chromosome> chromosomes, int populationLimit) {
/*  51 */     if (chromosomes.size() > populationLimit)
/*  52 */       throw new NumberIsTooLargeException(LocalizedFormats.LIST_OF_CHROMOSOMES_BIGGER_THAN_POPULATION_SIZE, Integer.valueOf(chromosomes.size()), Integer.valueOf(populationLimit), false); 
/*  55 */     if (populationLimit <= 0)
/*  56 */       throw new NotPositiveException(LocalizedFormats.POPULATION_LIMIT_NOT_POSITIVE, Integer.valueOf(populationLimit)); 
/*  59 */     this.chromosomes = chromosomes;
/*  60 */     this.populationLimit = populationLimit;
/*     */   }
/*     */   
/*     */   public ListPopulation(int populationLimit) {
/*  70 */     if (populationLimit <= 0)
/*  71 */       throw new NotPositiveException(LocalizedFormats.POPULATION_LIMIT_NOT_POSITIVE, Integer.valueOf(populationLimit)); 
/*  73 */     this.populationLimit = populationLimit;
/*  74 */     this.chromosomes = new ArrayList<Chromosome>(populationLimit);
/*     */   }
/*     */   
/*     */   public void setChromosomes(List<Chromosome> chromosomes) {
/*  82 */     this.chromosomes = chromosomes;
/*     */   }
/*     */   
/*     */   public List<Chromosome> getChromosomes() {
/*  90 */     return this.chromosomes;
/*     */   }
/*     */   
/*     */   public void addChromosome(Chromosome chromosome) {
/*  98 */     this.chromosomes.add(chromosome);
/*     */   }
/*     */   
/*     */   public Chromosome getFittestChromosome() {
/* 107 */     Chromosome bestChromosome = this.chromosomes.get(0);
/* 108 */     for (Chromosome chromosome : this.chromosomes) {
/* 109 */       if (chromosome.compareTo(bestChromosome) > 0)
/* 111 */         bestChromosome = chromosome; 
/*     */     } 
/* 114 */     return bestChromosome;
/*     */   }
/*     */   
/*     */   public int getPopulationLimit() {
/* 122 */     return this.populationLimit;
/*     */   }
/*     */   
/*     */   public void setPopulationLimit(int populationLimit) {
/* 130 */     this.populationLimit = populationLimit;
/*     */   }
/*     */   
/*     */   public int getPopulationSize() {
/* 138 */     return this.chromosomes.size();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 146 */     return this.chromosomes.toString();
/*     */   }
/*     */   
/*     */   public Iterator<Chromosome> iterator() {
/* 155 */     return this.chromosomes.iterator();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\genetics\ListPopulation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */