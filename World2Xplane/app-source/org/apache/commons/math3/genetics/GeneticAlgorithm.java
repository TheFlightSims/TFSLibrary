/*     */ package org.apache.commons.math3.genetics;
/*     */ 
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.random.JDKRandomGenerator;
/*     */ import org.apache.commons.math3.random.RandomGenerator;
/*     */ 
/*     */ public class GeneticAlgorithm {
/*  40 */   private static RandomGenerator randomGenerator = (RandomGenerator)new JDKRandomGenerator();
/*     */   
/*     */   private final CrossoverPolicy crossoverPolicy;
/*     */   
/*     */   private final double crossoverRate;
/*     */   
/*     */   private final MutationPolicy mutationPolicy;
/*     */   
/*     */   private final double mutationRate;
/*     */   
/*     */   private final SelectionPolicy selectionPolicy;
/*     */   
/*  58 */   private int generationsEvolved = 0;
/*     */   
/*     */   public GeneticAlgorithm(CrossoverPolicy crossoverPolicy, double crossoverRate, MutationPolicy mutationPolicy, double mutationRate, SelectionPolicy selectionPolicy) {
/*  74 */     if (crossoverRate < 0.0D || crossoverRate > 1.0D)
/*  75 */       throw new OutOfRangeException(LocalizedFormats.CROSSOVER_RATE, Double.valueOf(crossoverRate), Integer.valueOf(0), Integer.valueOf(1)); 
/*  78 */     if (mutationRate < 0.0D || mutationRate > 1.0D)
/*  79 */       throw new OutOfRangeException(LocalizedFormats.MUTATION_RATE, Double.valueOf(mutationRate), Integer.valueOf(0), Integer.valueOf(1)); 
/*  82 */     this.crossoverPolicy = crossoverPolicy;
/*  83 */     this.crossoverRate = crossoverRate;
/*  84 */     this.mutationPolicy = mutationPolicy;
/*  85 */     this.mutationRate = mutationRate;
/*  86 */     this.selectionPolicy = selectionPolicy;
/*     */   }
/*     */   
/*     */   public static synchronized void setRandomGenerator(RandomGenerator random) {
/*  95 */     randomGenerator = random;
/*     */   }
/*     */   
/*     */   public static synchronized RandomGenerator getRandomGenerator() {
/* 104 */     return randomGenerator;
/*     */   }
/*     */   
/*     */   public Population evolve(Population initial, StoppingCondition condition) {
/* 118 */     Population current = initial;
/* 119 */     this.generationsEvolved = 0;
/* 120 */     while (!condition.isSatisfied(current)) {
/* 121 */       current = nextGeneration(current);
/* 122 */       this.generationsEvolved++;
/*     */     } 
/* 124 */     return current;
/*     */   }
/*     */   
/*     */   public Population nextGeneration(Population current) {
/* 150 */     Population nextGeneration = current.nextGeneration();
/* 152 */     RandomGenerator randGen = getRandomGenerator();
/* 154 */     while (nextGeneration.getPopulationSize() < nextGeneration.getPopulationLimit()) {
/* 156 */       ChromosomePair pair = getSelectionPolicy().select(current);
/* 159 */       if (randGen.nextDouble() < getCrossoverRate())
/* 161 */         pair = getCrossoverPolicy().crossover(pair.getFirst(), pair.getSecond()); 
/* 165 */       if (randGen.nextDouble() < getMutationRate())
/* 167 */         pair = new ChromosomePair(getMutationPolicy().mutate(pair.getFirst()), getMutationPolicy().mutate(pair.getSecond())); 
/* 173 */       nextGeneration.addChromosome(pair.getFirst());
/* 175 */       if (nextGeneration.getPopulationSize() < nextGeneration.getPopulationLimit())
/* 177 */         nextGeneration.addChromosome(pair.getSecond()); 
/*     */     } 
/* 181 */     return nextGeneration;
/*     */   }
/*     */   
/*     */   public CrossoverPolicy getCrossoverPolicy() {
/* 189 */     return this.crossoverPolicy;
/*     */   }
/*     */   
/*     */   public double getCrossoverRate() {
/* 197 */     return this.crossoverRate;
/*     */   }
/*     */   
/*     */   public MutationPolicy getMutationPolicy() {
/* 205 */     return this.mutationPolicy;
/*     */   }
/*     */   
/*     */   public double getMutationRate() {
/* 213 */     return this.mutationRate;
/*     */   }
/*     */   
/*     */   public SelectionPolicy getSelectionPolicy() {
/* 221 */     return this.selectionPolicy;
/*     */   }
/*     */   
/*     */   public int getGenerationsEvolved() {
/* 232 */     return this.generationsEvolved;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\genetics\GeneticAlgorithm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */