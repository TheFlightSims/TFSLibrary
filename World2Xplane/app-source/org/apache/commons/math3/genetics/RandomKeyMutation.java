/*    */ package org.apache.commons.math3.genetics;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class RandomKeyMutation implements MutationPolicy {
/*    */   public Chromosome mutate(Chromosome original) {
/* 41 */     if (!(original instanceof RandomKey))
/* 42 */       throw new MathIllegalArgumentException(LocalizedFormats.RANDOMKEY_MUTATION_WRONG_CLASS, new Object[] { original.getClass().getSimpleName() }); 
/* 46 */     RandomKey<?> originalRk = (RandomKey)original;
/* 47 */     List<Double> repr = originalRk.getRepresentation();
/* 48 */     int rInd = GeneticAlgorithm.getRandomGenerator().nextInt(repr.size());
/* 50 */     List<Double> newRepr = new ArrayList<Double>(repr);
/* 51 */     newRepr.set(rInd, Double.valueOf(GeneticAlgorithm.getRandomGenerator().nextDouble()));
/* 53 */     return originalRk.newFixedLengthChromosome(newRepr);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\genetics\RandomKeyMutation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */