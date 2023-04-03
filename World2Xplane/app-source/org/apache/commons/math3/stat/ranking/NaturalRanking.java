/*     */ package org.apache.commons.math3.stat.ranking;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math3.exception.MathInternalError;
/*     */ import org.apache.commons.math3.random.RandomData;
/*     */ import org.apache.commons.math3.random.RandomDataImpl;
/*     */ import org.apache.commons.math3.random.RandomGenerator;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class NaturalRanking implements RankingAlgorithm {
/*  74 */   public static final NaNStrategy DEFAULT_NAN_STRATEGY = NaNStrategy.MAXIMAL;
/*     */   
/*  77 */   public static final TiesStrategy DEFAULT_TIES_STRATEGY = TiesStrategy.AVERAGE;
/*     */   
/*     */   private final NaNStrategy nanStrategy;
/*     */   
/*     */   private final TiesStrategy tiesStrategy;
/*     */   
/*     */   private final RandomData randomData;
/*     */   
/*     */   public NaturalRanking() {
/*  93 */     this.tiesStrategy = DEFAULT_TIES_STRATEGY;
/*  94 */     this.nanStrategy = DEFAULT_NAN_STRATEGY;
/*  95 */     this.randomData = null;
/*     */   }
/*     */   
/*     */   public NaturalRanking(TiesStrategy tiesStrategy) {
/* 105 */     this.tiesStrategy = tiesStrategy;
/* 106 */     this.nanStrategy = DEFAULT_NAN_STRATEGY;
/* 107 */     this.randomData = (RandomData)new RandomDataImpl();
/*     */   }
/*     */   
/*     */   public NaturalRanking(NaNStrategy nanStrategy) {
/* 117 */     this.nanStrategy = nanStrategy;
/* 118 */     this.tiesStrategy = DEFAULT_TIES_STRATEGY;
/* 119 */     this.randomData = null;
/*     */   }
/*     */   
/*     */   public NaturalRanking(NaNStrategy nanStrategy, TiesStrategy tiesStrategy) {
/* 130 */     this.nanStrategy = nanStrategy;
/* 131 */     this.tiesStrategy = tiesStrategy;
/* 132 */     this.randomData = (RandomData)new RandomDataImpl();
/*     */   }
/*     */   
/*     */   public NaturalRanking(RandomGenerator randomGenerator) {
/* 143 */     this.tiesStrategy = TiesStrategy.RANDOM;
/* 144 */     this.nanStrategy = DEFAULT_NAN_STRATEGY;
/* 145 */     this.randomData = (RandomData)new RandomDataImpl(randomGenerator);
/*     */   }
/*     */   
/*     */   public NaturalRanking(NaNStrategy nanStrategy, RandomGenerator randomGenerator) {
/* 159 */     this.nanStrategy = nanStrategy;
/* 160 */     this.tiesStrategy = TiesStrategy.RANDOM;
/* 161 */     this.randomData = (RandomData)new RandomDataImpl(randomGenerator);
/*     */   }
/*     */   
/*     */   public NaNStrategy getNanStrategy() {
/* 170 */     return this.nanStrategy;
/*     */   }
/*     */   
/*     */   public TiesStrategy getTiesStrategy() {
/* 179 */     return this.tiesStrategy;
/*     */   }
/*     */   
/*     */   public double[] rank(double[] data) {
/* 193 */     IntDoublePair[] ranks = new IntDoublePair[data.length];
/* 194 */     for (int i = 0; i < data.length; i++)
/* 195 */       ranks[i] = new IntDoublePair(data[i], i); 
/* 199 */     List<Integer> nanPositions = null;
/* 200 */     switch (this.nanStrategy) {
/*     */       case AVERAGE:
/* 202 */         recodeNaNs(ranks, Double.POSITIVE_INFINITY);
/*     */         break;
/*     */       case MAXIMUM:
/* 205 */         recodeNaNs(ranks, Double.NEGATIVE_INFINITY);
/*     */         break;
/*     */       case MINIMUM:
/* 208 */         ranks = removeNaNs(ranks);
/*     */         break;
/*     */       case RANDOM:
/* 211 */         nanPositions = getNanPositions(ranks);
/*     */         break;
/*     */       default:
/* 214 */         throw new MathInternalError();
/*     */     } 
/* 218 */     Arrays.sort((Object[])ranks);
/* 222 */     double[] out = new double[ranks.length];
/* 223 */     int pos = 1;
/* 224 */     out[ranks[0].getPosition()] = pos;
/* 225 */     List<Integer> tiesTrace = new ArrayList<Integer>();
/* 226 */     tiesTrace.add(Integer.valueOf(ranks[0].getPosition()));
/* 227 */     for (int j = 1; j < ranks.length; j++) {
/* 228 */       if (Double.compare(ranks[j].getValue(), ranks[j - 1].getValue()) > 0) {
/* 230 */         pos = j + 1;
/* 231 */         if (tiesTrace.size() > 1)
/* 232 */           resolveTie(out, tiesTrace); 
/* 234 */         tiesTrace = new ArrayList<Integer>();
/* 235 */         tiesTrace.add(Integer.valueOf(ranks[j].getPosition()));
/*     */       } else {
/* 238 */         tiesTrace.add(Integer.valueOf(ranks[j].getPosition()));
/*     */       } 
/* 240 */       out[ranks[j].getPosition()] = pos;
/*     */     } 
/* 242 */     if (tiesTrace.size() > 1)
/* 243 */       resolveTie(out, tiesTrace); 
/* 245 */     if (this.nanStrategy == NaNStrategy.FIXED)
/* 246 */       restoreNaNs(out, nanPositions); 
/* 248 */     return out;
/*     */   }
/*     */   
/*     */   private IntDoublePair[] removeNaNs(IntDoublePair[] ranks) {
/* 259 */     if (!containsNaNs(ranks))
/* 260 */       return ranks; 
/* 262 */     IntDoublePair[] outRanks = new IntDoublePair[ranks.length];
/* 263 */     int j = 0;
/* 264 */     for (int i = 0; i < ranks.length; i++) {
/* 265 */       if (Double.isNaN(ranks[i].getValue())) {
/* 267 */         for (int k = i + 1; k < ranks.length; k++)
/* 268 */           ranks[k] = new IntDoublePair(ranks[k].getValue(), ranks[k].getPosition() - 1); 
/*     */       } else {
/* 272 */         outRanks[j] = new IntDoublePair(ranks[i].getValue(), ranks[i].getPosition());
/* 274 */         j++;
/*     */       } 
/*     */     } 
/* 277 */     IntDoublePair[] returnRanks = new IntDoublePair[j];
/* 278 */     System.arraycopy(outRanks, 0, returnRanks, 0, j);
/* 279 */     return returnRanks;
/*     */   }
/*     */   
/*     */   private void recodeNaNs(IntDoublePair[] ranks, double value) {
/* 289 */     for (int i = 0; i < ranks.length; i++) {
/* 290 */       if (Double.isNaN(ranks[i].getValue()))
/* 291 */         ranks[i] = new IntDoublePair(value, ranks[i].getPosition()); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean containsNaNs(IntDoublePair[] ranks) {
/* 304 */     for (int i = 0; i < ranks.length; i++) {
/* 305 */       if (Double.isNaN(ranks[i].getValue()))
/* 306 */         return true; 
/*     */     } 
/* 309 */     return false;
/*     */   }
/*     */   
/*     */   private void resolveTie(double[] ranks, List<Integer> tiesTrace) {
/*     */     Iterator<Integer> iterator;
/*     */     long f;
/*     */     int i;
/* 329 */     double c = ranks[((Integer)tiesTrace.get(0)).intValue()];
/* 332 */     int length = tiesTrace.size();
/* 334 */     switch (this.tiesStrategy) {
/*     */       case AVERAGE:
/* 336 */         fill(ranks, tiesTrace, (2.0D * c + length - 1.0D) / 2.0D);
/*     */         return;
/*     */       case MAXIMUM:
/* 339 */         fill(ranks, tiesTrace, c + length - 1.0D);
/*     */         return;
/*     */       case MINIMUM:
/* 342 */         fill(ranks, tiesTrace, c);
/*     */         return;
/*     */       case RANDOM:
/* 345 */         iterator = tiesTrace.iterator();
/* 346 */         f = FastMath.round(c);
/* 347 */         while (iterator.hasNext())
/* 348 */           ranks[((Integer)iterator.next()).intValue()] = this.randomData.nextLong(f, f + length - 1L); 
/*     */         return;
/*     */       case SEQUENTIAL:
/* 354 */         iterator = tiesTrace.iterator();
/* 355 */         f = FastMath.round(c);
/* 356 */         i = 0;
/* 357 */         while (iterator.hasNext())
/* 358 */           ranks[((Integer)iterator.next()).intValue()] = (f + i++); 
/*     */         return;
/*     */     } 
/* 362 */     throw new MathInternalError();
/*     */   }
/*     */   
/*     */   private void fill(double[] data, List<Integer> tiesTrace, double value) {
/* 374 */     Iterator<Integer> iterator = tiesTrace.iterator();
/* 375 */     while (iterator.hasNext())
/* 376 */       data[((Integer)iterator.next()).intValue()] = value; 
/*     */   }
/*     */   
/*     */   private void restoreNaNs(double[] ranks, List<Integer> nanPositions) {
/* 387 */     if (nanPositions.size() == 0)
/*     */       return; 
/* 390 */     Iterator<Integer> iterator = nanPositions.iterator();
/* 391 */     while (iterator.hasNext())
/* 392 */       ranks[((Integer)iterator.next()).intValue()] = Double.NaN; 
/*     */   }
/*     */   
/*     */   private List<Integer> getNanPositions(IntDoublePair[] ranks) {
/* 404 */     ArrayList<Integer> out = new ArrayList<Integer>();
/* 405 */     for (int i = 0; i < ranks.length; i++) {
/* 406 */       if (Double.isNaN(ranks[i].getValue()))
/* 407 */         out.add(Integer.valueOf(i)); 
/*     */     } 
/* 410 */     return out;
/*     */   }
/*     */   
/*     */   private static class IntDoublePair implements Comparable<IntDoublePair> {
/*     */     private final double value;
/*     */     
/*     */     private final int position;
/*     */     
/*     */     public IntDoublePair(double value, int position) {
/* 433 */       this.value = value;
/* 434 */       this.position = position;
/*     */     }
/*     */     
/*     */     public int compareTo(IntDoublePair other) {
/* 445 */       return Double.compare(this.value, other.value);
/*     */     }
/*     */     
/*     */     public double getValue() {
/* 453 */       return this.value;
/*     */     }
/*     */     
/*     */     public int getPosition() {
/* 461 */       return this.position;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\ranking\NaturalRanking.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */