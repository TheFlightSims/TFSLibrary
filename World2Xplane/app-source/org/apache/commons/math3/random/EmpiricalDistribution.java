/*     */ package org.apache.commons.math3.random;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Serializable;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.ZeroException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
/*     */ import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class EmpiricalDistribution implements Serializable {
/*     */   public static final int DEFAULT_BIN_COUNT = 1000;
/*     */   
/*     */   private static final long serialVersionUID = 5729073523949762654L;
/*     */   
/*     */   private final List<SummaryStatistics> binStats;
/*     */   
/*  96 */   private SummaryStatistics sampleStats = null;
/*     */   
/*  99 */   private double max = Double.NEGATIVE_INFINITY;
/*     */   
/* 102 */   private double min = Double.POSITIVE_INFINITY;
/*     */   
/* 105 */   private double delta = 0.0D;
/*     */   
/*     */   private final int binCount;
/*     */   
/*     */   private boolean loaded = false;
/*     */   
/* 114 */   private double[] upperBounds = null;
/*     */   
/*     */   private final RandomDataImpl randomData;
/*     */   
/*     */   public EmpiricalDistribution() {
/* 123 */     this(1000, new RandomDataImpl());
/*     */   }
/*     */   
/*     */   public EmpiricalDistribution(int binCount) {
/* 132 */     this(binCount, new RandomDataImpl());
/*     */   }
/*     */   
/*     */   public EmpiricalDistribution(int binCount, RandomGenerator generator) {
/* 144 */     this.binCount = binCount;
/* 145 */     this.randomData = new RandomDataImpl(generator);
/* 146 */     this.binStats = new ArrayList<SummaryStatistics>();
/*     */   }
/*     */   
/*     */   public EmpiricalDistribution(RandomGenerator generator) {
/* 157 */     this(1000, generator);
/*     */   }
/*     */   
/*     */   public EmpiricalDistribution(int binCount, RandomDataImpl randomData) {
/* 169 */     this.binCount = binCount;
/* 170 */     this.randomData = randomData;
/* 171 */     this.binStats = new ArrayList<SummaryStatistics>();
/*     */   }
/*     */   
/*     */   public EmpiricalDistribution(RandomDataImpl randomData) {
/* 182 */     this(1000, randomData);
/*     */   }
/*     */   
/*     */   public void load(double[] in) throws NullArgumentException {
/* 193 */     DataAdapter da = new ArrayDataAdapter(in);
/*     */     try {
/* 195 */       da.computeStats();
/* 196 */       fillBinStats(in);
/* 197 */     } catch (IOException e) {
/* 198 */       throw new MathIllegalStateException(e, LocalizedFormats.SIMPLE_MESSAGE, new Object[] { e.getLocalizedMessage() });
/*     */     } 
/* 200 */     this.loaded = true;
/*     */   }
/*     */   
/*     */   public void load(URL url) throws IOException, NullArgumentException {
/* 212 */     MathUtils.checkNotNull(url);
/* 213 */     BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
/*     */     try {
/* 216 */       DataAdapter da = new StreamDataAdapter(in);
/* 217 */       da.computeStats();
/* 218 */       if (this.sampleStats.getN() == 0L)
/* 219 */         throw new ZeroException(LocalizedFormats.URL_CONTAINS_NO_DATA, new Object[] { url }); 
/* 221 */       in = new BufferedReader(new InputStreamReader(url.openStream()));
/* 222 */       fillBinStats(in);
/* 223 */       this.loaded = true;
/*     */     } finally {
/*     */       try {
/* 226 */         in.close();
/* 227 */       } catch (IOException ex) {}
/*     */     } 
/*     */   }
/*     */   
/*     */   public void load(File file) throws IOException, NullArgumentException {
/* 241 */     MathUtils.checkNotNull(file);
/* 242 */     BufferedReader in = new BufferedReader(new FileReader(file));
/*     */     try {
/* 244 */       DataAdapter da = new StreamDataAdapter(in);
/* 245 */       da.computeStats();
/* 246 */       in = new BufferedReader(new FileReader(file));
/* 247 */       fillBinStats(in);
/* 248 */       this.loaded = true;
/*     */     } finally {
/*     */       try {
/* 251 */         in.close();
/* 252 */       } catch (IOException ex) {}
/*     */     } 
/*     */   }
/*     */   
/*     */   private abstract class DataAdapter {
/*     */     private DataAdapter() {}
/*     */     
/*     */     public abstract void computeBinStats() throws IOException;
/*     */     
/*     */     public abstract void computeStats() throws IOException;
/*     */   }
/*     */   
/*     */   private class DataAdapterFactory {
/*     */     private DataAdapterFactory() {}
/*     */     
/*     */     public EmpiricalDistribution.DataAdapter getAdapter(Object in) {
/* 293 */       if (in instanceof BufferedReader) {
/* 294 */         BufferedReader inputStream = (BufferedReader)in;
/* 295 */         return new EmpiricalDistribution.StreamDataAdapter(inputStream);
/*     */       } 
/* 296 */       if (in instanceof double[]) {
/* 297 */         double[] inputArray = (double[])in;
/* 298 */         return new EmpiricalDistribution.ArrayDataAdapter(inputArray);
/*     */       } 
/* 300 */       throw new MathIllegalArgumentException(LocalizedFormats.INPUT_DATA_FROM_UNSUPPORTED_DATASOURCE, new Object[] { in.getClass().getName(), BufferedReader.class.getName(), double[].class.getName() });
/*     */     }
/*     */   }
/*     */   
/*     */   private class StreamDataAdapter extends DataAdapter {
/*     */     private BufferedReader inputStream;
/*     */     
/*     */     public StreamDataAdapter(BufferedReader in) {
/* 322 */       this.inputStream = in;
/*     */     }
/*     */     
/*     */     public void computeBinStats() throws IOException {
/* 328 */       String str = null;
/* 329 */       double val = 0.0D;
/* 330 */       while ((str = this.inputStream.readLine()) != null) {
/* 331 */         val = Double.parseDouble(str);
/* 332 */         SummaryStatistics stats = EmpiricalDistribution.this.binStats.get(EmpiricalDistribution.this.findBin(val));
/* 333 */         stats.addValue(val);
/*     */       } 
/* 336 */       this.inputStream.close();
/* 337 */       this.inputStream = null;
/*     */     }
/*     */     
/*     */     public void computeStats() throws IOException {
/* 343 */       String str = null;
/* 344 */       double val = 0.0D;
/* 345 */       EmpiricalDistribution.this.sampleStats = new SummaryStatistics();
/* 346 */       while ((str = this.inputStream.readLine()) != null) {
/* 347 */         val = Double.valueOf(str).doubleValue();
/* 348 */         EmpiricalDistribution.this.sampleStats.addValue(val);
/*     */       } 
/* 350 */       this.inputStream.close();
/* 351 */       this.inputStream = null;
/*     */     }
/*     */   }
/*     */   
/*     */   private class ArrayDataAdapter extends DataAdapter {
/*     */     private double[] inputArray;
/*     */     
/*     */     public ArrayDataAdapter(double[] in) throws NullArgumentException {
/* 371 */       MathUtils.checkNotNull(in);
/* 372 */       this.inputArray = in;
/*     */     }
/*     */     
/*     */     public void computeStats() throws IOException {
/* 378 */       EmpiricalDistribution.this.sampleStats = new SummaryStatistics();
/* 379 */       for (int i = 0; i < this.inputArray.length; i++)
/* 380 */         EmpiricalDistribution.this.sampleStats.addValue(this.inputArray[i]); 
/*     */     }
/*     */     
/*     */     public void computeBinStats() throws IOException {
/* 387 */       for (int i = 0; i < this.inputArray.length; i++) {
/* 388 */         SummaryStatistics stats = EmpiricalDistribution.this.binStats.get(EmpiricalDistribution.this.findBin(this.inputArray[i]));
/* 390 */         stats.addValue(this.inputArray[i]);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void fillBinStats(Object in) throws IOException {
/* 403 */     this.min = this.sampleStats.getMin();
/* 404 */     this.max = this.sampleStats.getMax();
/* 405 */     this.delta = (this.max - this.min) / Double.valueOf(this.binCount).doubleValue();
/* 408 */     if (!this.binStats.isEmpty())
/* 409 */       this.binStats.clear(); 
/* 411 */     for (int i = 0; i < this.binCount; i++) {
/* 412 */       SummaryStatistics stats = new SummaryStatistics();
/* 413 */       this.binStats.add(i, stats);
/*     */     } 
/* 417 */     DataAdapterFactory aFactory = new DataAdapterFactory();
/* 418 */     DataAdapter da = aFactory.getAdapter(in);
/* 419 */     da.computeBinStats();
/* 422 */     this.upperBounds = new double[this.binCount];
/* 423 */     this.upperBounds[0] = ((SummaryStatistics)this.binStats.get(0)).getN() / this.sampleStats.getN();
/* 425 */     for (int j = 1; j < this.binCount - 1; j++)
/* 426 */       this.upperBounds[j] = this.upperBounds[j - 1] + ((SummaryStatistics)this.binStats.get(j)).getN() / this.sampleStats.getN(); 
/* 429 */     this.upperBounds[this.binCount - 1] = 1.0D;
/*     */   }
/*     */   
/*     */   private int findBin(double value) {
/* 439 */     return FastMath.min(FastMath.max((int)FastMath.ceil((value - this.min) / this.delta) - 1, 0), this.binCount - 1);
/*     */   }
/*     */   
/*     */   public double getNextValue() throws MathIllegalStateException {
/* 453 */     if (!this.loaded)
/* 454 */       throw new MathIllegalStateException(LocalizedFormats.DISTRIBUTION_NOT_LOADED, new Object[0]); 
/* 458 */     double x = this.randomData.nextUniform(0.0D, 1.0D);
/* 461 */     for (int i = 0; i < this.binCount; i++) {
/* 462 */       if (x <= this.upperBounds[i]) {
/* 463 */         SummaryStatistics stats = this.binStats.get(i);
/* 464 */         if (stats.getN() > 0L) {
/* 465 */           if (stats.getStandardDeviation() > 0.0D)
/* 466 */             return this.randomData.nextGaussian(stats.getMean(), stats.getStandardDeviation()); 
/* 469 */           return stats.getMean();
/*     */         } 
/*     */       } 
/*     */     } 
/* 474 */     throw new MathIllegalStateException(LocalizedFormats.NO_BIN_SELECTED, new Object[0]);
/*     */   }
/*     */   
/*     */   public StatisticalSummary getSampleStats() {
/* 486 */     return (StatisticalSummary)this.sampleStats;
/*     */   }
/*     */   
/*     */   public int getBinCount() {
/* 495 */     return this.binCount;
/*     */   }
/*     */   
/*     */   public List<SummaryStatistics> getBinStats() {
/* 506 */     return this.binStats;
/*     */   }
/*     */   
/*     */   public double[] getUpperBounds() {
/* 523 */     double[] binUpperBounds = new double[this.binCount];
/* 524 */     binUpperBounds[0] = this.min + this.delta;
/* 525 */     for (int i = 1; i < this.binCount - 1; i++)
/* 526 */       binUpperBounds[i] = binUpperBounds[i - 1] + this.delta; 
/* 528 */     binUpperBounds[this.binCount - 1] = this.max;
/* 529 */     return binUpperBounds;
/*     */   }
/*     */   
/*     */   public double[] getGeneratorUpperBounds() {
/* 544 */     int len = this.upperBounds.length;
/* 545 */     double[] out = new double[len];
/* 546 */     System.arraycopy(this.upperBounds, 0, out, 0, len);
/* 547 */     return out;
/*     */   }
/*     */   
/*     */   public boolean isLoaded() {
/* 556 */     return this.loaded;
/*     */   }
/*     */   
/*     */   public void reSeed(long seed) {
/* 566 */     this.randomData.reSeed(seed);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\EmpiricalDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */