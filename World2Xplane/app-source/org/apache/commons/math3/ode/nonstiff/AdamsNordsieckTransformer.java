/*     */ package org.apache.commons.math3.ode.nonstiff;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.math3.FieldElement;
/*     */ import org.apache.commons.math3.fraction.BigFraction;
/*     */ import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
/*     */ import org.apache.commons.math3.linear.Array2DRowRealMatrix;
/*     */ import org.apache.commons.math3.linear.ArrayFieldVector;
/*     */ import org.apache.commons.math3.linear.FieldDecompositionSolver;
/*     */ import org.apache.commons.math3.linear.FieldLUDecomposition;
/*     */ import org.apache.commons.math3.linear.FieldMatrix;
/*     */ import org.apache.commons.math3.linear.FieldVector;
/*     */ import org.apache.commons.math3.linear.MatrixUtils;
/*     */ import org.apache.commons.math3.linear.QRDecomposition;
/*     */ import org.apache.commons.math3.linear.RealMatrix;
/*     */ 
/*     */ public class AdamsNordsieckTransformer {
/* 138 */   private static final Map<Integer, AdamsNordsieckTransformer> CACHE = new HashMap<Integer, AdamsNordsieckTransformer>();
/*     */   
/*     */   private final Array2DRowRealMatrix update;
/*     */   
/*     */   private final double[] c1;
/*     */   
/*     */   private AdamsNordsieckTransformer(int nSteps) {
/* 154 */     FieldMatrix<BigFraction> bigP = buildP(nSteps);
/* 155 */     FieldDecompositionSolver<BigFraction> pSolver = (new FieldLUDecomposition(bigP)).getSolver();
/* 158 */     BigFraction[] u = new BigFraction[nSteps];
/* 159 */     Arrays.fill((Object[])u, BigFraction.ONE);
/* 160 */     BigFraction[] bigC1 = (BigFraction[])pSolver.solve((FieldVector)new ArrayFieldVector((FieldElement[])u, false)).toArray();
/* 166 */     BigFraction[][] shiftedP = (BigFraction[][])bigP.getData();
/* 167 */     for (int i = shiftedP.length - 1; i > 0; i--)
/* 169 */       shiftedP[i] = shiftedP[i - 1]; 
/* 171 */     shiftedP[0] = new BigFraction[nSteps];
/* 172 */     Arrays.fill((Object[])shiftedP[0], BigFraction.ZERO);
/* 173 */     FieldMatrix<BigFraction> bigMSupdate = pSolver.solve((FieldMatrix)new Array2DRowFieldMatrix((FieldElement[][])shiftedP, false));
/* 177 */     this.update = MatrixUtils.bigFractionMatrixToRealMatrix(bigMSupdate);
/* 178 */     this.c1 = new double[nSteps];
/* 179 */     for (int j = 0; j < nSteps; j++)
/* 180 */       this.c1[j] = bigC1[j].doubleValue(); 
/*     */   }
/*     */   
/*     */   public static AdamsNordsieckTransformer getInstance(int nSteps) {
/* 191 */     synchronized (CACHE) {
/* 192 */       AdamsNordsieckTransformer t = CACHE.get(Integer.valueOf(nSteps));
/* 193 */       if (t == null) {
/* 194 */         t = new AdamsNordsieckTransformer(nSteps);
/* 195 */         CACHE.put(Integer.valueOf(nSteps), t);
/*     */       } 
/* 197 */       return t;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getNSteps() {
/* 207 */     return this.c1.length;
/*     */   }
/*     */   
/*     */   private FieldMatrix<BigFraction> buildP(int nSteps) {
/* 225 */     BigFraction[][] pData = new BigFraction[nSteps][nSteps];
/* 227 */     for (int i = 0; i < pData.length; i++) {
/* 229 */       BigFraction[] pI = pData[i];
/* 230 */       int factor = -(i + 1);
/* 231 */       int aj = factor;
/* 232 */       for (int j = 0; j < pI.length; j++) {
/* 233 */         pI[j] = new BigFraction(aj * (j + 2));
/* 234 */         aj *= factor;
/*     */       } 
/*     */     } 
/* 238 */     return (FieldMatrix<BigFraction>)new Array2DRowFieldMatrix((FieldElement[][])pData, false);
/*     */   }
/*     */   
/*     */   public Array2DRowRealMatrix initializeHighOrderDerivatives(double h, double[] t, double[][] y, double[][] yDot) {
/* 259 */     double[][] a = new double[2 * (y.length - 1)][this.c1.length];
/* 260 */     double[][] b = new double[2 * (y.length - 1)][(y[0]).length];
/* 261 */     double[] y0 = y[0];
/* 262 */     double[] yDot0 = yDot[0];
/* 263 */     for (int i = 1; i < y.length; i++) {
/* 265 */       double di = t[i] - t[0];
/* 266 */       double ratio = di / h;
/* 267 */       double dikM1Ohk = 1.0D / h;
/* 271 */       double[] aI = a[2 * i - 2];
/* 272 */       double[] aDotI = a[2 * i - 1];
/* 273 */       for (int j = 0; j < aI.length; j++) {
/* 274 */         dikM1Ohk *= ratio;
/* 275 */         aI[j] = di * dikM1Ohk;
/* 276 */         aDotI[j] = (j + 2) * dikM1Ohk;
/*     */       } 
/* 280 */       double[] yI = y[i];
/* 281 */       double[] yDotI = yDot[i];
/* 282 */       double[] bI = b[2 * i - 2];
/* 283 */       double[] bDotI = b[2 * i - 1];
/* 284 */       for (int k = 0; k < yI.length; k++) {
/* 285 */         bI[k] = yI[k] - y0[k] - di * yDot0[k];
/* 286 */         bDotI[k] = yDotI[k] - yDot0[k];
/*     */       } 
/*     */     } 
/* 294 */     QRDecomposition decomposition = new QRDecomposition((RealMatrix)new Array2DRowRealMatrix(a, false));
/* 295 */     RealMatrix x = decomposition.getSolver().solve((RealMatrix)new Array2DRowRealMatrix(b, false));
/* 296 */     return new Array2DRowRealMatrix(x.getData(), false);
/*     */   }
/*     */   
/*     */   public Array2DRowRealMatrix updateHighOrderDerivativesPhase1(Array2DRowRealMatrix highOrder) {
/* 311 */     return this.update.multiply(highOrder);
/*     */   }
/*     */   
/*     */   public void updateHighOrderDerivativesPhase2(double[] start, double[] end, Array2DRowRealMatrix highOrder) {
/* 330 */     double[][] data = highOrder.getDataRef();
/* 331 */     for (int i = 0; i < data.length; i++) {
/* 332 */       double[] dataI = data[i];
/* 333 */       double c1I = this.c1[i];
/* 334 */       for (int j = 0; j < dataI.length; j++)
/* 335 */         dataI[j] = dataI[j] + c1I * (start[j] - end[j]); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\AdamsNordsieckTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */