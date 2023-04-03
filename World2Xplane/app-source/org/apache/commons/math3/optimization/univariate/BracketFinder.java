/*     */ package org.apache.commons.math3.optimization.univariate;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.TooManyEvaluationsException;
/*     */ import org.apache.commons.math3.optimization.GoalType;
/*     */ import org.apache.commons.math3.util.Incrementor;
/*     */ 
/*     */ public class BracketFinder {
/*     */   private static final double EPS_MIN = 1.0E-21D;
/*     */   
/*     */   private static final double GOLD = 1.618034D;
/*     */   
/*     */   private final double growLimit;
/*     */   
/*  48 */   private final Incrementor evaluations = new Incrementor();
/*     */   
/*     */   private double lo;
/*     */   
/*     */   private double hi;
/*     */   
/*     */   private double mid;
/*     */   
/*     */   private double fLo;
/*     */   
/*     */   private double fHi;
/*     */   
/*     */   private double fMid;
/*     */   
/*     */   public BracketFinder() {
/*  79 */     this(100.0D, 50);
/*     */   }
/*     */   
/*     */   public BracketFinder(double growLimit, int maxEvaluations) {
/*  91 */     if (growLimit <= 0.0D)
/*  92 */       throw new NotStrictlyPositiveException(Double.valueOf(growLimit)); 
/*  94 */     if (maxEvaluations <= 0)
/*  95 */       throw new NotStrictlyPositiveException(Integer.valueOf(maxEvaluations)); 
/*  98 */     this.growLimit = growLimit;
/*  99 */     this.evaluations.setMaximalCount(maxEvaluations);
/*     */   }
/*     */   
/*     */   public void search(UnivariateFunction func, GoalType goal, double xA, double xB) {
/* 113 */     this.evaluations.resetCount();
/* 114 */     boolean isMinim = (goal == GoalType.MINIMIZE);
/* 116 */     double fA = eval(func, xA);
/* 117 */     double fB = eval(func, xB);
/* 118 */     if (isMinim ? (fA < fB) : (fA > fB)) {
/* 122 */       double tmp = xA;
/* 123 */       xA = xB;
/* 124 */       xB = tmp;
/* 126 */       tmp = fA;
/* 127 */       fA = fB;
/* 128 */       fB = tmp;
/*     */     } 
/* 131 */     double xC = xB + 1.618034D * (xB - xA);
/* 132 */     double fC = eval(func, xC);
/* 134 */     while (isMinim ? (fC < fB) : (fC > fB)) {
/* 135 */       double fW, tmp1 = (xB - xA) * (fB - fC);
/* 136 */       double tmp2 = (xB - xC) * (fB - fA);
/* 138 */       double val = tmp2 - tmp1;
/* 139 */       double denom = (Math.abs(val) < 1.0E-21D) ? 2.0E-21D : (2.0D * val);
/* 141 */       double w = xB - ((xB - xC) * tmp2 - (xB - xA) * tmp1) / denom;
/* 142 */       double wLim = xB + this.growLimit * (xC - xB);
/* 145 */       if ((w - xC) * (xB - w) > 0.0D) {
/* 146 */         fW = eval(func, w);
/* 147 */         if (isMinim ? (fW < fC) : (fW > fC)) {
/* 150 */           xA = xB;
/* 151 */           xB = w;
/* 152 */           fA = fB;
/* 153 */           fB = fW;
/*     */           break;
/*     */         } 
/* 155 */         if (isMinim ? (fW > fB) : (fW < fB)) {
/* 158 */           xC = w;
/* 159 */           fC = fW;
/*     */           break;
/*     */         } 
/* 162 */         w = xC + 1.618034D * (xC - xB);
/* 163 */         fW = eval(func, w);
/* 164 */       } else if ((w - wLim) * (wLim - xC) >= 0.0D) {
/* 165 */         w = wLim;
/* 166 */         fW = eval(func, w);
/* 167 */       } else if ((w - wLim) * (xC - w) > 0.0D) {
/* 168 */         fW = eval(func, w);
/* 169 */         if (isMinim ? (fW < fC) : (fW > fC)) {
/* 172 */           xB = xC;
/* 173 */           xC = w;
/* 174 */           w = xC + 1.618034D * (xC - xB);
/* 175 */           fB = fC;
/* 176 */           fC = fW;
/* 177 */           fW = eval(func, w);
/*     */         } 
/*     */       } else {
/* 180 */         w = xC + 1.618034D * (xC - xB);
/* 181 */         fW = eval(func, w);
/*     */       } 
/* 184 */       xA = xB;
/* 185 */       fA = fB;
/* 186 */       xB = xC;
/* 187 */       fB = fC;
/* 188 */       xC = w;
/* 189 */       fC = fW;
/*     */     } 
/* 192 */     this.lo = xA;
/* 193 */     this.fLo = fA;
/* 194 */     this.mid = xB;
/* 195 */     this.fMid = fB;
/* 196 */     this.hi = xC;
/* 197 */     this.fHi = fC;
/* 199 */     if (this.lo > this.hi) {
/* 200 */       double tmp = this.lo;
/* 201 */       this.lo = this.hi;
/* 202 */       this.hi = tmp;
/* 204 */       tmp = this.fLo;
/* 205 */       this.fLo = this.fHi;
/* 206 */       this.fHi = tmp;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getMaxEvaluations() {
/* 214 */     return this.evaluations.getMaximalCount();
/*     */   }
/*     */   
/*     */   public int getEvaluations() {
/* 221 */     return this.evaluations.getCount();
/*     */   }
/*     */   
/*     */   public double getLo() {
/* 229 */     return this.lo;
/*     */   }
/*     */   
/*     */   public double getFLo() {
/* 237 */     return this.fLo;
/*     */   }
/*     */   
/*     */   public double getHi() {
/* 245 */     return this.hi;
/*     */   }
/*     */   
/*     */   public double getFHi() {
/* 253 */     return this.fHi;
/*     */   }
/*     */   
/*     */   public double getMid() {
/* 261 */     return this.mid;
/*     */   }
/*     */   
/*     */   public double getFMid() {
/* 269 */     return this.fMid;
/*     */   }
/*     */   
/*     */   private double eval(UnivariateFunction f, double x) {
/*     */     try {
/* 281 */       this.evaluations.incrementCount();
/* 282 */     } catch (MaxCountExceededException e) {
/* 283 */       throw new TooManyEvaluationsException(e.getMax());
/*     */     } 
/* 285 */     return f.value(x);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimizatio\\univariate\BracketFinder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */