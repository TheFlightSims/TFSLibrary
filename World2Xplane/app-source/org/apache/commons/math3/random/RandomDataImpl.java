/*      */ package org.apache.commons.math3.random;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.security.MessageDigest;
/*      */ import java.security.NoSuchAlgorithmException;
/*      */ import java.security.NoSuchProviderException;
/*      */ import java.security.SecureRandom;
/*      */ import java.util.Collection;
/*      */ import org.apache.commons.math3.distribution.BetaDistribution;
/*      */ import org.apache.commons.math3.distribution.BinomialDistribution;
/*      */ import org.apache.commons.math3.distribution.CauchyDistribution;
/*      */ import org.apache.commons.math3.distribution.ChiSquaredDistribution;
/*      */ import org.apache.commons.math3.distribution.FDistribution;
/*      */ import org.apache.commons.math3.distribution.HypergeometricDistribution;
/*      */ import org.apache.commons.math3.distribution.IntegerDistribution;
/*      */ import org.apache.commons.math3.distribution.PascalDistribution;
/*      */ import org.apache.commons.math3.distribution.RealDistribution;
/*      */ import org.apache.commons.math3.distribution.TDistribution;
/*      */ import org.apache.commons.math3.distribution.WeibullDistribution;
/*      */ import org.apache.commons.math3.distribution.ZipfDistribution;
/*      */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*      */ import org.apache.commons.math3.exception.MathInternalError;
/*      */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*      */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*      */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*      */ import org.apache.commons.math3.util.ArithmeticUtils;
/*      */ import org.apache.commons.math3.util.FastMath;
/*      */ import org.apache.commons.math3.util.ResizableDoubleArray;
/*      */ 
/*      */ public class RandomDataImpl implements RandomData, Serializable {
/*      */   private static final long serialVersionUID = -626730818244969716L;
/*      */   
/*      */   private static final double[] EXPONENTIAL_SA_QI;
/*      */   
/*  126 */   private RandomGenerator rand = null;
/*      */   
/*  129 */   private SecureRandom secRand = null;
/*      */   
/*      */   static {
/*  139 */     double LN2 = FastMath.log(2.0D);
/*  140 */     double qi = 0.0D;
/*  141 */     int i = 1;
/*  149 */     ResizableDoubleArray ra = new ResizableDoubleArray(20);
/*  151 */     while (qi < 1.0D) {
/*  152 */       qi += FastMath.pow(LN2, i) / ArithmeticUtils.factorial(i);
/*  153 */       ra.addElement(qi);
/*  154 */       i++;
/*      */     } 
/*  157 */     EXPONENTIAL_SA_QI = ra.getElements();
/*      */   }
/*      */   
/*      */   public RandomDataImpl(RandomGenerator rand) {
/*  181 */     this.rand = rand;
/*      */   }
/*      */   
/*      */   public String nextHexString(int len) {
/*  201 */     if (len <= 0)
/*  202 */       throw new NotStrictlyPositiveException(LocalizedFormats.LENGTH, Integer.valueOf(len)); 
/*  206 */     RandomGenerator ran = getRan();
/*  209 */     StringBuilder outBuffer = new StringBuilder();
/*  212 */     byte[] randomBytes = new byte[len / 2 + 1];
/*  213 */     ran.nextBytes(randomBytes);
/*  216 */     for (int i = 0; i < randomBytes.length; i++) {
/*  217 */       Integer c = Integer.valueOf(randomBytes[i]);
/*  224 */       String hex = Integer.toHexString(c.intValue() + 128);
/*  227 */       if (hex.length() == 1)
/*  228 */         hex = "0" + hex; 
/*  230 */       outBuffer.append(hex);
/*      */     } 
/*  232 */     return outBuffer.toString().substring(0, len);
/*      */   }
/*      */   
/*      */   public int nextInt(int lower, int upper) {
/*  237 */     if (lower >= upper)
/*  238 */       throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Integer.valueOf(lower), Integer.valueOf(upper), false); 
/*  241 */     double r = getRan().nextDouble();
/*  242 */     double scaled = r * upper + (1.0D - r) * lower + r;
/*  243 */     return (int)FastMath.floor(scaled);
/*      */   }
/*      */   
/*      */   public long nextLong(long lower, long upper) {
/*  248 */     if (lower >= upper)
/*  249 */       throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Long.valueOf(lower), Long.valueOf(upper), false); 
/*  252 */     double r = getRan().nextDouble();
/*  253 */     double scaled = r * upper + (1.0D - r) * lower + r;
/*  254 */     return (long)FastMath.floor(scaled);
/*      */   }
/*      */   
/*      */   public String nextSecureHexString(int len) {
/*  274 */     if (len <= 0)
/*  275 */       throw new NotStrictlyPositiveException(LocalizedFormats.LENGTH, Integer.valueOf(len)); 
/*  279 */     SecureRandom secRan = getSecRan();
/*  280 */     MessageDigest alg = null;
/*      */     try {
/*  282 */       alg = MessageDigest.getInstance("SHA-1");
/*  283 */     } catch (NoSuchAlgorithmException ex) {
/*  285 */       throw new MathInternalError(ex);
/*      */     } 
/*  287 */     alg.reset();
/*  290 */     int numIter = len / 40 + 1;
/*  292 */     StringBuilder outBuffer = new StringBuilder();
/*  293 */     for (int iter = 1; iter < numIter + 1; iter++) {
/*  294 */       byte[] randomBytes = new byte[40];
/*  295 */       secRan.nextBytes(randomBytes);
/*  296 */       alg.update(randomBytes);
/*  299 */       byte[] hash = alg.digest();
/*  302 */       for (int i = 0; i < hash.length; i++) {
/*  303 */         Integer c = Integer.valueOf(hash[i]);
/*  310 */         String hex = Integer.toHexString(c.intValue() + 128);
/*  313 */         if (hex.length() == 1)
/*  314 */           hex = "0" + hex; 
/*  316 */         outBuffer.append(hex);
/*      */       } 
/*      */     } 
/*  319 */     return outBuffer.toString().substring(0, len);
/*      */   }
/*      */   
/*      */   public int nextSecureInt(int lower, int upper) {
/*  324 */     if (lower >= upper)
/*  325 */       throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Integer.valueOf(lower), Integer.valueOf(upper), false); 
/*  328 */     SecureRandom sec = getSecRan();
/*  329 */     double r = sec.nextDouble();
/*  330 */     double scaled = r * upper + (1.0D - r) * lower + r;
/*  331 */     return (int)FastMath.floor(scaled);
/*      */   }
/*      */   
/*      */   public long nextSecureLong(long lower, long upper) {
/*  337 */     if (lower >= upper)
/*  338 */       throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Long.valueOf(lower), Long.valueOf(upper), false); 
/*  341 */     SecureRandom sec = getSecRan();
/*  342 */     double r = sec.nextDouble();
/*  343 */     double scaled = r * upper + (1.0D - r) * lower + r;
/*  344 */     return (long)FastMath.floor(scaled);
/*      */   }
/*      */   
/*      */   public long nextPoisson(double mean) {
/*  361 */     if (mean <= 0.0D)
/*  362 */       throw new NotStrictlyPositiveException(LocalizedFormats.MEAN, Double.valueOf(mean)); 
/*  365 */     double pivot = 40.0D;
/*  366 */     if (mean < 40.0D) {
/*  367 */       RandomGenerator generator = getRan();
/*  368 */       double p = FastMath.exp(-mean);
/*  369 */       long n = 0L;
/*  370 */       double r = 1.0D;
/*  371 */       double rnd = 1.0D;
/*  373 */       while (n < 1000.0D * mean) {
/*  374 */         rnd = generator.nextDouble();
/*  375 */         r *= rnd;
/*  376 */         if (r >= p) {
/*  377 */           n++;
/*      */           continue;
/*      */         } 
/*  379 */         return n;
/*      */       } 
/*  382 */       return n;
/*      */     } 
/*  384 */     double lambda = FastMath.floor(mean);
/*  385 */     double lambdaFractional = mean - lambda;
/*  386 */     double logLambda = FastMath.log(lambda);
/*  387 */     double logLambdaFactorial = ArithmeticUtils.factorialLog((int)lambda);
/*  388 */     long y2 = (lambdaFractional < Double.MIN_VALUE) ? 0L : nextPoisson(lambdaFractional);
/*  389 */     double delta = FastMath.sqrt(lambda * FastMath.log(32.0D * lambda / Math.PI + 1.0D));
/*  390 */     double halfDelta = delta / 2.0D;
/*  391 */     double twolpd = 2.0D * lambda + delta;
/*  392 */     double a1 = FastMath.sqrt(Math.PI * twolpd) * FastMath.exp(0.0D * lambda);
/*  393 */     double a2 = twolpd / delta * FastMath.exp(-delta * (1.0D + delta) / twolpd);
/*  394 */     double aSum = a1 + a2 + 1.0D;
/*  395 */     double p1 = a1 / aSum;
/*  396 */     double p2 = a2 / aSum;
/*  397 */     double c1 = 1.0D / 8.0D * lambda;
/*  399 */     double x = 0.0D;
/*  400 */     double y = 0.0D;
/*  401 */     double v = 0.0D;
/*  402 */     int a = 0;
/*  403 */     double t = 0.0D;
/*  404 */     double qr = 0.0D;
/*  405 */     double qa = 0.0D;
/*      */     while (true) {
/*  407 */       double u = nextUniform(0.0D, 1.0D);
/*  408 */       if (u <= p1) {
/*  409 */         double n = nextGaussian(0.0D, 1.0D);
/*  410 */         x = n * FastMath.sqrt(lambda + halfDelta) - 0.5D;
/*  411 */         if (x > delta || x < -lambda)
/*      */           continue; 
/*  414 */         y = (x < 0.0D) ? FastMath.floor(x) : FastMath.ceil(x);
/*  415 */         double e = nextExponential(1.0D);
/*  416 */         v = -e - n * n / 2.0D + c1;
/*      */       } else {
/*  418 */         if (u > p1 + p2) {
/*  419 */           y = lambda;
/*      */           break;
/*      */         } 
/*  422 */         x = delta + twolpd / delta * nextExponential(1.0D);
/*  423 */         y = FastMath.ceil(x);
/*  424 */         v = -nextExponential(1.0D) - delta * (x + 1.0D) / twolpd;
/*      */       } 
/*  427 */       a = (x < 0.0D) ? 1 : 0;
/*  428 */       t = y * (y + 1.0D) / 2.0D * lambda;
/*  429 */       if (v < -t && a == 0) {
/*  430 */         y = lambda + y;
/*      */         break;
/*      */       } 
/*  433 */       qr = t * ((2.0D * y + 1.0D) / 6.0D * lambda - 1.0D);
/*  434 */       qa = qr - t * t / 3.0D * (lambda + a * (y + 1.0D));
/*  435 */       if (v < qa) {
/*  436 */         y = lambda + y;
/*      */         break;
/*      */       } 
/*  439 */       if (v > qr)
/*      */         continue; 
/*  442 */       if (v < y * logLambda - ArithmeticUtils.factorialLog((int)(y + lambda)) + logLambdaFactorial) {
/*  443 */         y = lambda + y;
/*      */         break;
/*      */       } 
/*      */     } 
/*  447 */     return y2 + (long)y;
/*      */   }
/*      */   
/*      */   public double nextGaussian(double mu, double sigma) {
/*  454 */     if (sigma <= 0.0D)
/*  455 */       throw new NotStrictlyPositiveException(LocalizedFormats.STANDARD_DEVIATION, Double.valueOf(sigma)); 
/*  457 */     return sigma * getRan().nextGaussian() + mu;
/*      */   }
/*      */   
/*      */   public double nextExponential(double mean) {
/*  473 */     if (mean <= 0.0D)
/*  474 */       throw new NotStrictlyPositiveException(LocalizedFormats.MEAN, Double.valueOf(mean)); 
/*  478 */     double a = 0.0D;
/*  479 */     double u = nextUniform(0.0D, 1.0D);
/*  482 */     while (u < 0.5D) {
/*  483 */       a += EXPONENTIAL_SA_QI[0];
/*  484 */       u *= 2.0D;
/*      */     } 
/*  488 */     u += u - 1.0D;
/*  491 */     if (u <= EXPONENTIAL_SA_QI[0])
/*  492 */       return mean * (a + u); 
/*  496 */     int i = 0;
/*  497 */     double u2 = nextUniform(0.0D, 1.0D);
/*  498 */     double umin = u2;
/*      */     do {
/*  502 */       i++;
/*  503 */       u2 = nextUniform(0.0D, 1.0D);
/*  505 */       if (u2 >= umin)
/*      */         continue; 
/*  506 */       umin = u2;
/*  510 */     } while (u > EXPONENTIAL_SA_QI[i]);
/*  512 */     return mean * (a + umin * EXPONENTIAL_SA_QI[0]);
/*      */   }
/*      */   
/*      */   public double nextUniform(double lower, double upper) {
/*  530 */     return nextUniform(lower, upper, false);
/*      */   }
/*      */   
/*      */   public double nextUniform(double lower, double upper, boolean lowerInclusive) {
/*  551 */     if (lower >= upper)
/*  552 */       throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Double.valueOf(lower), Double.valueOf(upper), false); 
/*  556 */     if (Double.isInfinite(lower) || Double.isInfinite(upper))
/*  557 */       throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_BOUND, new Object[0]); 
/*  560 */     if (Double.isNaN(lower) || Double.isNaN(upper))
/*  561 */       throw new MathIllegalArgumentException(LocalizedFormats.NAN_NOT_ALLOWED, new Object[0]); 
/*  564 */     RandomGenerator generator = getRan();
/*  567 */     double u = generator.nextDouble();
/*  568 */     while (!lowerInclusive && u <= 0.0D)
/*  569 */       u = generator.nextDouble(); 
/*  572 */     return u * upper + (1.0D - u) * lower;
/*      */   }
/*      */   
/*      */   public double nextBeta(double alpha, double beta) {
/*  586 */     return nextInversionDeviate((RealDistribution)new BetaDistribution(alpha, beta));
/*      */   }
/*      */   
/*      */   public int nextBinomial(int numberOfTrials, double probabilityOfSuccess) {
/*  600 */     return nextInversionDeviate((IntegerDistribution)new BinomialDistribution(numberOfTrials, probabilityOfSuccess));
/*      */   }
/*      */   
/*      */   public double nextCauchy(double median, double scale) {
/*  614 */     return nextInversionDeviate((RealDistribution)new CauchyDistribution(median, scale));
/*      */   }
/*      */   
/*      */   public double nextChiSquare(double df) {
/*  627 */     return nextInversionDeviate((RealDistribution)new ChiSquaredDistribution(df));
/*      */   }
/*      */   
/*      */   public double nextF(double numeratorDf, double denominatorDf) {
/*  641 */     return nextInversionDeviate((RealDistribution)new FDistribution(numeratorDf, denominatorDf));
/*      */   }
/*      */   
/*      */   public double nextGamma(double shape, double scale) {
/*  666 */     if (shape < 1.0D) {
/*      */       double x;
/*      */       while (true) {
/*  671 */         double u = nextUniform(0.0D, 1.0D);
/*  672 */         double bGS = 1.0D + shape / Math.E;
/*  673 */         double p = bGS * u;
/*  675 */         if (p <= 1.0D) {
/*  678 */           double d1 = FastMath.pow(p, 1.0D / shape);
/*  679 */           double d2 = nextUniform(0.0D, 1.0D);
/*  681 */           if (d2 > FastMath.exp(-d1))
/*      */             continue; 
/*  685 */           return scale * d1;
/*      */         } 
/*  690 */         x = -1.0D * FastMath.log((bGS - p) / shape);
/*  691 */         double u2 = nextUniform(0.0D, 1.0D);
/*  693 */         if (u2 > FastMath.pow(x, shape - 1.0D))
/*      */           continue; 
/*      */         break;
/*      */       } 
/*  697 */       return scale * x;
/*      */     } 
/*  705 */     RandomGenerator generator = getRan();
/*  706 */     double d = shape - 0.3333333333333333D;
/*  707 */     double c = 1.0D / 3.0D * FastMath.sqrt(d);
/*      */     while (true) {
/*  710 */       double x = generator.nextGaussian();
/*  711 */       double v = (1.0D + c * x) * (1.0D + c * x) * (1.0D + c * x);
/*  713 */       if (v <= 0.0D)
/*      */         continue; 
/*  717 */       double xx = x * x;
/*  718 */       double u = nextUniform(0.0D, 1.0D);
/*  721 */       if (u < 1.0D - 0.0331D * xx * xx)
/*  722 */         return scale * d * v; 
/*  725 */       if (FastMath.log(u) < 0.5D * xx + d * (1.0D - v + FastMath.log(v)))
/*  726 */         return scale * d * v; 
/*      */     } 
/*      */   }
/*      */   
/*      */   public int nextHypergeometric(int populationSize, int numberOfSuccesses, int sampleSize) {
/*  743 */     return nextInversionDeviate((IntegerDistribution)new HypergeometricDistribution(populationSize, numberOfSuccesses, sampleSize));
/*      */   }
/*      */   
/*      */   public int nextPascal(int r, double p) {
/*  757 */     return nextInversionDeviate((IntegerDistribution)new PascalDistribution(r, p));
/*      */   }
/*      */   
/*      */   public double nextT(double df) {
/*  770 */     return nextInversionDeviate((RealDistribution)new TDistribution(df));
/*      */   }
/*      */   
/*      */   public double nextWeibull(double shape, double scale) {
/*  784 */     return nextInversionDeviate((RealDistribution)new WeibullDistribution(shape, scale));
/*      */   }
/*      */   
/*      */   public int nextZipf(int numberOfElements, double exponent) {
/*  798 */     return nextInversionDeviate((IntegerDistribution)new ZipfDistribution(numberOfElements, exponent));
/*      */   }
/*      */   
/*      */   private RandomGenerator getRan() {
/*  812 */     if (this.rand == null)
/*  813 */       initRan(); 
/*  815 */     return this.rand;
/*      */   }
/*      */   
/*      */   private void initRan() {
/*  823 */     this.rand = new Well19937c(System.currentTimeMillis() + System.identityHashCode(this));
/*      */   }
/*      */   
/*      */   private SecureRandom getSecRan() {
/*  836 */     if (this.secRand == null) {
/*  837 */       this.secRand = new SecureRandom();
/*  838 */       this.secRand.setSeed(System.currentTimeMillis() + System.identityHashCode(this));
/*      */     } 
/*  840 */     return this.secRand;
/*      */   }
/*      */   
/*      */   public void reSeed(long seed) {
/*  853 */     if (this.rand == null)
/*  854 */       initRan(); 
/*  856 */     this.rand.setSeed(seed);
/*      */   }
/*      */   
/*      */   public void reSeedSecure() {
/*  867 */     if (this.secRand == null)
/*  868 */       this.secRand = new SecureRandom(); 
/*  870 */     this.secRand.setSeed(System.currentTimeMillis());
/*      */   }
/*      */   
/*      */   public void reSeedSecure(long seed) {
/*  883 */     if (this.secRand == null)
/*  884 */       this.secRand = new SecureRandom(); 
/*  886 */     this.secRand.setSeed(seed);
/*      */   }
/*      */   
/*      */   public void reSeed() {
/*  894 */     if (this.rand == null)
/*  895 */       initRan(); 
/*  897 */     this.rand.setSeed(System.currentTimeMillis() + System.identityHashCode(this));
/*      */   }
/*      */   
/*      */   public void setSecureAlgorithm(String algorithm, String provider) throws NoSuchAlgorithmException, NoSuchProviderException {
/*  922 */     this.secRand = SecureRandom.getInstance(algorithm, provider);
/*      */   }
/*      */   
/*      */   public int[] nextPermutation(int n, int k) {
/*  935 */     if (k > n)
/*  936 */       throw new NumberIsTooLargeException(LocalizedFormats.PERMUTATION_EXCEEDS_N, Integer.valueOf(k), Integer.valueOf(n), true); 
/*  939 */     if (k <= 0)
/*  940 */       throw new NotStrictlyPositiveException(LocalizedFormats.PERMUTATION_SIZE, Integer.valueOf(k)); 
/*  944 */     int[] index = getNatural(n);
/*  945 */     shuffle(index, n - k);
/*  946 */     int[] result = new int[k];
/*  947 */     for (int i = 0; i < k; i++)
/*  948 */       result[i] = index[n - i - 1]; 
/*  951 */     return result;
/*      */   }
/*      */   
/*      */   public Object[] nextSample(Collection<?> c, int k) {
/*  969 */     int len = c.size();
/*  970 */     if (k > len)
/*  971 */       throw new NumberIsTooLargeException(LocalizedFormats.SAMPLE_SIZE_EXCEEDS_COLLECTION_SIZE, Integer.valueOf(k), Integer.valueOf(len), true); 
/*  974 */     if (k <= 0)
/*  975 */       throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, Integer.valueOf(k)); 
/*  978 */     Object[] objects = c.toArray();
/*  979 */     int[] index = nextPermutation(len, k);
/*  980 */     Object[] result = new Object[k];
/*  981 */     for (int i = 0; i < k; i++)
/*  982 */       result[i] = objects[index[i]]; 
/*  984 */     return result;
/*      */   }
/*      */   
/*      */   public double nextInversionDeviate(RealDistribution distribution) {
/*  996 */     return distribution.inverseCumulativeProbability(nextUniform(0.0D, 1.0D));
/*      */   }
/*      */   
/*      */   public int nextInversionDeviate(IntegerDistribution distribution) {
/* 1009 */     return distribution.inverseCumulativeProbability(nextUniform(0.0D, 1.0D));
/*      */   }
/*      */   
/*      */   private void shuffle(int[] list, int end) {
/* 1024 */     int target = 0;
/* 1025 */     for (int i = list.length - 1; i >= end; i--) {
/* 1026 */       if (i == 0) {
/* 1027 */         target = 0;
/*      */       } else {
/* 1029 */         target = nextInt(0, i);
/*      */       } 
/* 1031 */       int temp = list[target];
/* 1032 */       list[target] = list[i];
/* 1033 */       list[i] = temp;
/*      */     } 
/*      */   }
/*      */   
/*      */   private int[] getNatural(int n) {
/* 1045 */     int[] natural = new int[n];
/* 1046 */     for (int i = 0; i < n; i++)
/* 1047 */       natural[i] = i; 
/* 1049 */     return natural;
/*      */   }
/*      */   
/*      */   public RandomDataImpl() {}
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\RandomDataImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */