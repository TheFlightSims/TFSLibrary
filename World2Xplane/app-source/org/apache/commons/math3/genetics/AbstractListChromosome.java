/*     */ package org.apache.commons.math3.genetics;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class AbstractListChromosome<T> extends Chromosome {
/*     */   private final List<T> representation;
/*     */   
/*     */   public AbstractListChromosome(List<T> representation) {
/*  43 */     checkValidity(representation);
/*  44 */     this.representation = Collections.unmodifiableList(new ArrayList<T>(representation));
/*     */   }
/*     */   
/*     */   public AbstractListChromosome(T[] representation) {
/*  52 */     this(Arrays.asList(representation));
/*     */   }
/*     */   
/*     */   protected abstract void checkValidity(List<T> paramList) throws InvalidRepresentationException;
/*     */   
/*     */   protected List<T> getRepresentation() {
/*  69 */     return this.representation;
/*     */   }
/*     */   
/*     */   public int getLength() {
/*  77 */     return getRepresentation().size();
/*     */   }
/*     */   
/*     */   public abstract AbstractListChromosome<T> newFixedLengthChromosome(List<T> paramList);
/*     */   
/*     */   public String toString() {
/* 100 */     return String.format("(f=%s %s)", new Object[] { Double.valueOf(getFitness()), getRepresentation() });
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\genetics\AbstractListChromosome.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */