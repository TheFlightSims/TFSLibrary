/*     */ package org.apache.commons.math3.genetics;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public class OnePointCrossover<T> implements CrossoverPolicy {
/*     */   public ChromosomePair crossover(Chromosome first, Chromosome second) {
/*  78 */     if (!(first instanceof AbstractListChromosome) || !(second instanceof AbstractListChromosome))
/*  79 */       throw new MathIllegalArgumentException(LocalizedFormats.INVALID_FIXED_LENGTH_CHROMOSOME, new Object[0]); 
/*  81 */     return crossover((AbstractListChromosome<T>)first, (AbstractListChromosome<T>)second);
/*     */   }
/*     */   
/*     */   private ChromosomePair crossover(AbstractListChromosome<T> first, AbstractListChromosome<T> second) {
/*  95 */     int length = first.getLength();
/*  96 */     if (length != second.getLength())
/*  97 */       throw new DimensionMismatchException(second.getLength(), length); 
/* 101 */     List<T> parent1Rep = first.getRepresentation();
/* 102 */     List<T> parent2Rep = second.getRepresentation();
/* 104 */     ArrayList<T> child1Rep = new ArrayList<T>(first.getLength());
/* 105 */     ArrayList<T> child2Rep = new ArrayList<T>(second.getLength());
/* 108 */     int crossoverIndex = 1 + GeneticAlgorithm.getRandomGenerator().nextInt(length - 2);
/*     */     int i;
/* 111 */     for (i = 0; i < crossoverIndex; i++) {
/* 112 */       child1Rep.add(parent1Rep.get(i));
/* 113 */       child2Rep.add(parent2Rep.get(i));
/*     */     } 
/* 116 */     for (i = crossoverIndex; i < length; i++) {
/* 117 */       child1Rep.add(parent2Rep.get(i));
/* 118 */       child2Rep.add(parent1Rep.get(i));
/*     */     } 
/* 121 */     return new ChromosomePair(first.newFixedLengthChromosome(child1Rep), second.newFixedLengthChromosome(child2Rep));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\genetics\OnePointCrossover.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */