/*    */ package org.apache.commons.math3.genetics;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class BinaryMutation implements MutationPolicy {
/*    */   public Chromosome mutate(Chromosome original) {
/* 41 */     if (!(original instanceof BinaryChromosome))
/* 42 */       throw new MathIllegalArgumentException(LocalizedFormats.INVALID_BINARY_CHROMOSOME, new Object[0]); 
/* 45 */     BinaryChromosome origChrom = (BinaryChromosome)original;
/* 46 */     List<Integer> newRepr = new ArrayList<Integer>(origChrom.getRepresentation());
/* 49 */     int geneIndex = GeneticAlgorithm.getRandomGenerator().nextInt(origChrom.getLength());
/* 51 */     newRepr.set(geneIndex, Integer.valueOf((((Integer)origChrom.getRepresentation().get(geneIndex)).intValue() == 0) ? 1 : 0));
/* 53 */     Chromosome<Integer> newChrom = origChrom.newFixedLengthChromosome(newRepr);
/* 54 */     return newChrom;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\genetics\BinaryMutation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */