/*     */ package org.apache.commons.math3.genetics;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public class TournamentSelection implements SelectionPolicy {
/*     */   private int arity;
/*     */   
/*     */   public TournamentSelection(int arity) {
/*  45 */     this.arity = arity;
/*     */   }
/*     */   
/*     */   public ChromosomePair select(Population population) {
/*  58 */     return new ChromosomePair(tournament((ListPopulation)population), tournament((ListPopulation)population));
/*     */   }
/*     */   
/*     */   private Chromosome tournament(ListPopulation population) {
/*  73 */     if (population.getPopulationSize() < this.arity)
/*  74 */       throw new MathIllegalArgumentException(LocalizedFormats.TOO_LARGE_TOURNAMENT_ARITY, new Object[] { Integer.valueOf(this.arity), Integer.valueOf(population.getPopulationSize()) }); 
/*  78 */     ListPopulation tournamentPopulation = new ListPopulation(this.arity) {
/*     */         public Population nextGeneration() {
/*  81 */           return null;
/*     */         }
/*     */       };
/*  86 */     List<Chromosome> chromosomes = new ArrayList<Chromosome>(population.getChromosomes());
/*  87 */     for (int i = 0; i < this.arity; i++) {
/*  89 */       int rind = GeneticAlgorithm.getRandomGenerator().nextInt(chromosomes.size());
/*  90 */       tournamentPopulation.addChromosome(chromosomes.get(rind));
/*  92 */       chromosomes.remove(rind);
/*     */     } 
/*  95 */     return tournamentPopulation.getFittestChromosome();
/*     */   }
/*     */   
/*     */   public int getArity() {
/* 104 */     return this.arity;
/*     */   }
/*     */   
/*     */   public void setArity(int arity) {
/* 113 */     this.arity = arity;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\genetics\TournamentSelection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */