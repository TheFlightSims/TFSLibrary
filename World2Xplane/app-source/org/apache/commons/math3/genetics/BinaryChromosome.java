/*     */ package org.apache.commons.math3.genetics;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public abstract class BinaryChromosome extends AbstractListChromosome<Integer> {
/*     */   public BinaryChromosome(List<Integer> representation) {
/*  39 */     super(representation);
/*     */   }
/*     */   
/*     */   public BinaryChromosome(Integer[] representation) {
/*  49 */     super(representation);
/*     */   }
/*     */   
/*     */   protected void checkValidity(List<Integer> chromosomeRepresentation) throws InvalidRepresentationException {
/*  58 */     for (Iterator<Integer> i$ = chromosomeRepresentation.iterator(); i$.hasNext(); ) {
/*  58 */       int i = ((Integer)i$.next()).intValue();
/*  59 */       if (i < 0 || i > 1)
/*  60 */         throw new InvalidRepresentationException(LocalizedFormats.INVALID_BINARY_DIGIT, new Object[] { Integer.valueOf(i) }); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static List<Integer> randomBinaryRepresentation(int length) {
/*  73 */     List<Integer> rList = new ArrayList<Integer>(length);
/*  74 */     for (int j = 0; j < length; j++)
/*  75 */       rList.add(Integer.valueOf(GeneticAlgorithm.getRandomGenerator().nextInt(2))); 
/*  77 */     return rList;
/*     */   }
/*     */   
/*     */   protected boolean isSame(Chromosome another) {
/*  86 */     if (!(another instanceof BinaryChromosome))
/*  87 */       return false; 
/*  89 */     BinaryChromosome anotherBc = (BinaryChromosome)another;
/*  91 */     if (getLength() != anotherBc.getLength())
/*  92 */       return false; 
/*  95 */     for (int i = 0; i < getRepresentation().size(); i++) {
/*  96 */       if (!((Integer)getRepresentation().get(i)).equals(anotherBc.getRepresentation().get(i)))
/*  97 */         return false; 
/*     */     } 
/* 101 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\genetics\BinaryChromosome.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */