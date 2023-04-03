/*     */ package org.apache.commons.math3.random;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public class ValueServer {
/*     */   public static final int DIGEST_MODE = 0;
/*     */   
/*     */   public static final int REPLAY_MODE = 1;
/*     */   
/*     */   public static final int UNIFORM_MODE = 2;
/*     */   
/*     */   public static final int EXPONENTIAL_MODE = 3;
/*     */   
/*     */   public static final int GAUSSIAN_MODE = 4;
/*     */   
/*     */   public static final int CONSTANT_MODE = 5;
/*     */   
/*  70 */   private int mode = 5;
/*     */   
/*  73 */   private URL valuesFileURL = null;
/*     */   
/*  76 */   private double mu = 0.0D;
/*     */   
/*  79 */   private double sigma = 0.0D;
/*     */   
/*  82 */   private EmpiricalDistribution empiricalDistribution = null;
/*     */   
/*  85 */   private BufferedReader filePointer = null;
/*     */   
/*     */   private final RandomDataImpl randomData;
/*     */   
/*     */   public ValueServer() {
/*  94 */     this.randomData = new RandomDataImpl();
/*     */   }
/*     */   
/*     */   public ValueServer(RandomDataImpl randomData) {
/* 105 */     this.randomData = randomData;
/*     */   }
/*     */   
/*     */   public double getNext() throws IOException {
/* 116 */     switch (this.mode) {
/*     */       case 0:
/* 117 */         return getNextDigest();
/*     */       case 1:
/* 118 */         return getNextReplay();
/*     */       case 2:
/* 119 */         return getNextUniform();
/*     */       case 3:
/* 120 */         return getNextExponential();
/*     */       case 4:
/* 121 */         return getNextGaussian();
/*     */       case 5:
/* 122 */         return this.mu;
/*     */     } 
/* 123 */     throw new MathIllegalStateException(LocalizedFormats.UNKNOWN_MODE, new Object[] { 
/* 123 */           Integer.valueOf(this.mode), "DIGEST_MODE", Integer.valueOf(0), "REPLAY_MODE", Integer.valueOf(1), "UNIFORM_MODE", Integer.valueOf(2), "EXPONENTIAL_MODE", Integer.valueOf(3), "GAUSSIAN_MODE", 
/* 123 */           Integer.valueOf(4), "CONSTANT_MODE", Integer.valueOf(5) });
/*     */   }
/*     */   
/*     */   public void fill(double[] values) throws IOException {
/* 139 */     for (int i = 0; i < values.length; i++)
/* 140 */       values[i] = getNext(); 
/*     */   }
/*     */   
/*     */   public double[] fill(int length) throws IOException {
/* 153 */     double[] out = new double[length];
/* 154 */     for (int i = 0; i < length; i++)
/* 155 */       out[i] = getNext(); 
/* 157 */     return out;
/*     */   }
/*     */   
/*     */   public void computeDistribution() throws IOException {
/* 173 */     computeDistribution(1000);
/*     */   }
/*     */   
/*     */   public void computeDistribution(int binCount) throws IOException {
/* 192 */     this.empiricalDistribution = new EmpiricalDistribution(binCount, this.randomData);
/* 193 */     this.empiricalDistribution.load(this.valuesFileURL);
/* 194 */     this.mu = this.empiricalDistribution.getSampleStats().getMean();
/* 195 */     this.sigma = this.empiricalDistribution.getSampleStats().getStandardDeviation();
/*     */   }
/*     */   
/*     */   public int getMode() {
/* 205 */     return this.mode;
/*     */   }
/*     */   
/*     */   public void setMode(int mode) {
/* 214 */     this.mode = mode;
/*     */   }
/*     */   
/*     */   public URL getValuesFileURL() {
/* 224 */     return this.valuesFileURL;
/*     */   }
/*     */   
/*     */   public void setValuesFileURL(String url) throws MalformedURLException {
/* 235 */     this.valuesFileURL = new URL(url);
/*     */   }
/*     */   
/*     */   public void setValuesFileURL(URL url) {
/* 244 */     this.valuesFileURL = url;
/*     */   }
/*     */   
/*     */   public EmpiricalDistribution getEmpiricalDistribution() {
/* 253 */     return this.empiricalDistribution;
/*     */   }
/*     */   
/*     */   public void resetReplayFile() throws IOException {
/* 262 */     if (this.filePointer != null)
/*     */       try {
/* 264 */         this.filePointer.close();
/* 265 */         this.filePointer = null;
/* 266 */       } catch (IOException ex) {} 
/* 270 */     this.filePointer = new BufferedReader(new InputStreamReader(this.valuesFileURL.openStream()));
/*     */   }
/*     */   
/*     */   public void closeReplayFile() throws IOException {
/* 279 */     if (this.filePointer != null) {
/* 280 */       this.filePointer.close();
/* 281 */       this.filePointer = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getMu() {
/* 294 */     return this.mu;
/*     */   }
/*     */   
/*     */   public void setMu(double mu) {
/* 305 */     this.mu = mu;
/*     */   }
/*     */   
/*     */   public double getSigma() {
/* 318 */     return this.sigma;
/*     */   }
/*     */   
/*     */   public void setSigma(double sigma) {
/* 327 */     this.sigma = sigma;
/*     */   }
/*     */   
/*     */   public void reSeed(long seed) {
/* 337 */     this.randomData.reSeed(seed);
/*     */   }
/*     */   
/*     */   private double getNextDigest() {
/* 353 */     if (this.empiricalDistribution == null || this.empiricalDistribution.getBinStats().size() == 0)
/* 355 */       throw new MathIllegalStateException(LocalizedFormats.DIGEST_NOT_INITIALIZED, new Object[0]); 
/* 357 */     return this.empiricalDistribution.getNextValue();
/*     */   }
/*     */   
/*     */   private double getNextReplay() throws IOException {
/* 379 */     String str = null;
/* 380 */     if (this.filePointer == null)
/* 381 */       resetReplayFile(); 
/* 383 */     if ((str = this.filePointer.readLine()) == null) {
/* 385 */       closeReplayFile();
/* 386 */       resetReplayFile();
/* 387 */       if ((str = this.filePointer.readLine()) == null)
/* 388 */         throw new MathIllegalStateException(LocalizedFormats.URL_CONTAINS_NO_DATA, new Object[] { this.valuesFileURL }); 
/*     */     } 
/* 392 */     return Double.valueOf(str).doubleValue();
/*     */   }
/*     */   
/*     */   private double getNextUniform() {
/* 401 */     return this.randomData.nextUniform(0.0D, 2.0D * this.mu);
/*     */   }
/*     */   
/*     */   private double getNextExponential() {
/* 410 */     return this.randomData.nextExponential(this.mu);
/*     */   }
/*     */   
/*     */   private double getNextGaussian() {
/* 420 */     return this.randomData.nextGaussian(this.mu, this.sigma);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\ValueServer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */