/*     */ package org.apache.commons.math3.genetics;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public abstract class RandomKey<T> extends AbstractListChromosome<Double> implements PermutationChromosome<T> {
/*     */   private final List<Double> sortedRepresentation;
/*     */   
/*     */   private final List<Integer> baseSeqPermutation;
/*     */   
/*     */   public RandomKey(List<Double> representation) {
/*  86 */     super(representation);
/*  88 */     List<Double> sortedRepr = new ArrayList<Double>(getRepresentation());
/*  89 */     Collections.sort(sortedRepr);
/*  90 */     this.sortedRepresentation = Collections.unmodifiableList(sortedRepr);
/*  92 */     this.baseSeqPermutation = Collections.unmodifiableList(decodeGeneric(baseSequence(getLength()), getRepresentation(), this.sortedRepresentation));
/*     */   }
/*     */   
/*     */   public RandomKey(Double[] representation) {
/* 103 */     this(Arrays.asList(representation));
/*     */   }
/*     */   
/*     */   public List<T> decode(List<T> sequence) {
/* 110 */     return decodeGeneric(sequence, getRepresentation(), this.sortedRepresentation);
/*     */   }
/*     */   
/*     */   private static <S> List<S> decodeGeneric(List<S> sequence, List<Double> representation, List<Double> sortedRepr) {
/* 127 */     int l = sequence.size();
/* 130 */     if (representation.size() != l)
/* 131 */       throw new DimensionMismatchException(representation.size(), l); 
/* 133 */     if (sortedRepr.size() != l)
/* 134 */       throw new DimensionMismatchException(sortedRepr.size(), l); 
/* 138 */     List<Double> reprCopy = new ArrayList<Double>(representation);
/* 141 */     List<S> res = new ArrayList<S>(l);
/* 142 */     for (int i = 0; i < l; i++) {
/* 143 */       int index = reprCopy.indexOf(sortedRepr.get(i));
/* 144 */       res.add(sequence.get(index));
/* 145 */       reprCopy.set(index, null);
/*     */     } 
/* 147 */     return res;
/*     */   }
/*     */   
/*     */   protected boolean isSame(Chromosome another) {
/* 160 */     if (!(another instanceof RandomKey))
/* 161 */       return false; 
/* 163 */     RandomKey<?> anotherRk = (RandomKey)another;
/* 165 */     if (getLength() != anotherRk.getLength())
/* 166 */       return false; 
/* 171 */     List<Integer> thisPerm = this.baseSeqPermutation;
/* 172 */     List<Integer> anotherPerm = anotherRk.baseSeqPermutation;
/* 174 */     for (int i = 0; i < getLength(); i++) {
/* 175 */       if (thisPerm.get(i) != anotherPerm.get(i))
/* 176 */         return false; 
/*     */     } 
/* 180 */     return true;
/*     */   }
/*     */   
/*     */   protected void checkValidity(List<Double> chromosomeRepresentation) throws InvalidRepresentationException {
/* 190 */     for (Iterator<Double> i$ = chromosomeRepresentation.iterator(); i$.hasNext(); ) {
/* 190 */       double val = ((Double)i$.next()).doubleValue();
/* 191 */       if (val < 0.0D || val > 1.0D)
/* 192 */         throw new InvalidRepresentationException(LocalizedFormats.OUT_OF_RANGE_SIMPLE, new Object[] { Double.valueOf(val), Integer.valueOf(0), Integer.valueOf(1) }); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final List<Double> randomPermutation(int l) {
/* 207 */     List<Double> repr = new ArrayList<Double>(l);
/* 208 */     for (int i = 0; i < l; i++)
/* 209 */       repr.add(Double.valueOf(GeneticAlgorithm.getRandomGenerator().nextDouble())); 
/* 211 */     return repr;
/*     */   }
/*     */   
/*     */   public static final List<Double> identityPermutation(int l) {
/* 222 */     List<Double> repr = new ArrayList<Double>(l);
/* 223 */     for (int i = 0; i < l; i++)
/* 224 */       repr.add(Double.valueOf(i / l)); 
/* 226 */     return repr;
/*     */   }
/*     */   
/*     */   public static <S> List<Double> comparatorPermutation(List<S> data, Comparator<S> comparator) {
/* 244 */     List<S> sortedData = new ArrayList<S>(data);
/* 245 */     Collections.sort(sortedData, comparator);
/* 247 */     return inducedPermutation(data, sortedData);
/*     */   }
/*     */   
/*     */   public static <S> List<Double> inducedPermutation(List<S> originalData, List<S> permutedData) {
/* 270 */     if (originalData.size() != permutedData.size())
/* 271 */       throw new DimensionMismatchException(permutedData.size(), originalData.size()); 
/* 273 */     int l = originalData.size();
/* 275 */     List<S> origDataCopy = new ArrayList<S>(originalData);
/* 277 */     Double[] res = new Double[l];
/* 278 */     for (int i = 0; i < l; i++) {
/* 279 */       int index = origDataCopy.indexOf(permutedData.get(i));
/* 280 */       if (index == -1)
/* 281 */         throw new MathIllegalArgumentException(LocalizedFormats.DIFFERENT_ORIG_AND_PERMUTED_DATA, new Object[0]); 
/* 283 */       res[index] = Double.valueOf(i / l);
/* 284 */       origDataCopy.set(index, null);
/*     */     } 
/* 286 */     return Arrays.asList(res);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 294 */     return String.format("(f=%s pi=(%s))", new Object[] { Double.valueOf(getFitness()), this.baseSeqPermutation });
/*     */   }
/*     */   
/*     */   private static List<Integer> baseSequence(int l) {
/* 304 */     List<Integer> baseSequence = new ArrayList<Integer>(l);
/* 305 */     for (int i = 0; i < l; i++)
/* 306 */       baseSequence.add(Integer.valueOf(i)); 
/* 308 */     return baseSequence;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\genetics\RandomKey.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */