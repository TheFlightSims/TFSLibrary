/*     */ package org.apache.commons.math3.ode;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public class JacobianMatrices {
/*     */   private ExpandableStatefulODE efode;
/*     */   
/*     */   private int index;
/*     */   
/*     */   private MainStateJacobianProvider jode;
/*     */   
/*     */   private ParameterizedODE pode;
/*     */   
/*     */   private int stateDim;
/*     */   
/*     */   private ParameterConfiguration[] selectedParameters;
/*     */   
/*     */   private List<ParameterJacobianProvider> jacobianProviders;
/*     */   
/*     */   private int paramDim;
/*     */   
/*     */   private boolean dirtyParameter;
/*     */   
/*     */   private double[] matricesData;
/*     */   
/*     */   public JacobianMatrices(FirstOrderDifferentialEquations fode, double[] hY, String... parameters) throws MathIllegalArgumentException {
/* 105 */     this(new MainStateJacobianWrapper(fode, hY), parameters);
/*     */   }
/*     */   
/*     */   public JacobianMatrices(MainStateJacobianProvider jode, String... parameters) throws MathIllegalArgumentException {
/* 125 */     this.efode = null;
/* 126 */     this.index = -1;
/* 128 */     this.jode = jode;
/* 129 */     this.pode = null;
/* 131 */     this.stateDim = jode.getDimension();
/* 133 */     if (parameters == null) {
/* 134 */       this.selectedParameters = null;
/* 135 */       this.paramDim = 0;
/*     */     } else {
/* 137 */       this.selectedParameters = new ParameterConfiguration[parameters.length];
/* 138 */       for (int j = 0; j < parameters.length; j++)
/* 139 */         this.selectedParameters[j] = new ParameterConfiguration(parameters[j], Double.NaN); 
/* 141 */       this.paramDim = parameters.length;
/*     */     } 
/* 143 */     this.dirtyParameter = false;
/* 145 */     this.jacobianProviders = new ArrayList<ParameterJacobianProvider>();
/* 149 */     this.matricesData = new double[(this.stateDim + this.paramDim) * this.stateDim];
/* 150 */     for (int i = 0; i < this.stateDim; i++)
/* 151 */       this.matricesData[i * (this.stateDim + 1)] = 1.0D; 
/*     */   }
/*     */   
/*     */   public void registerVariationalEquations(ExpandableStatefulODE expandable) throws MathIllegalArgumentException {
/* 166 */     FirstOrderDifferentialEquations ode = (this.jode instanceof MainStateJacobianWrapper) ? ((MainStateJacobianWrapper)this.jode).ode : this.jode;
/* 169 */     if (expandable.getPrimary() != ode)
/* 170 */       throw new MathIllegalArgumentException(LocalizedFormats.UNMATCHED_ODE_IN_EXPANDED_SET, new Object[0]); 
/* 173 */     this.efode = expandable;
/* 174 */     this.index = this.efode.addSecondaryEquations(new JacobiansSecondaryEquations());
/* 175 */     this.efode.setSecondaryState(this.index, this.matricesData);
/*     */   }
/*     */   
/*     */   public void addParameterJacobianProvider(ParameterJacobianProvider provider) {
/* 183 */     this.jacobianProviders.add(provider);
/*     */   }
/*     */   
/*     */   public void setParameterizedODE(ParameterizedODE parameterizedOde) {
/* 190 */     this.pode = parameterizedOde;
/* 191 */     this.dirtyParameter = true;
/*     */   }
/*     */   
/*     */   public void setParameterStep(String parameter, double hP) {
/* 213 */     for (ParameterConfiguration param : this.selectedParameters) {
/* 214 */       if (parameter.equals(param.getParameterName())) {
/* 215 */         param.setHP(hP);
/* 216 */         this.dirtyParameter = true;
/*     */         return;
/*     */       } 
/*     */     } 
/* 221 */     throw new MathIllegalArgumentException(LocalizedFormats.UNKNOWN_PARAMETER, new Object[] { parameter });
/*     */   }
/*     */   
/*     */   public void setInitialMainStateJacobian(double[][] dYdY0) throws DimensionMismatchException {
/* 237 */     checkDimension(this.stateDim, dYdY0);
/* 238 */     checkDimension(this.stateDim, dYdY0[0]);
/* 241 */     int i = 0;
/* 242 */     for (double[] row : dYdY0) {
/* 243 */       System.arraycopy(row, 0, this.matricesData, i, this.stateDim);
/* 244 */       i += this.stateDim;
/*     */     } 
/* 247 */     if (this.efode != null)
/* 248 */       this.efode.setSecondaryState(this.index, this.matricesData); 
/*     */   }
/*     */   
/*     */   public void setInitialParameterJacobian(String pName, double[] dYdP) throws MathIllegalArgumentException {
/* 266 */     checkDimension(this.stateDim, dYdP);
/* 269 */     int i = this.stateDim * this.stateDim;
/* 270 */     for (ParameterConfiguration param : this.selectedParameters) {
/* 271 */       if (pName.equals(param.getParameterName())) {
/* 272 */         System.arraycopy(dYdP, 0, this.matricesData, i, this.stateDim);
/* 273 */         if (this.efode != null)
/* 274 */           this.efode.setSecondaryState(this.index, this.matricesData); 
/*     */         return;
/*     */       } 
/* 278 */       i += this.stateDim;
/*     */     } 
/* 281 */     throw new MathIllegalArgumentException(LocalizedFormats.UNKNOWN_PARAMETER, new Object[] { pName });
/*     */   }
/*     */   
/*     */   public void getCurrentMainSetJacobian(double[][] dYdY0) {
/* 291 */     double[] p = this.efode.getSecondaryState(this.index);
/* 293 */     int j = 0;
/* 294 */     for (int i = 0; i < this.stateDim; i++) {
/* 295 */       System.arraycopy(p, j, dYdY0[i], 0, this.stateDim);
/* 296 */       j += this.stateDim;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void getCurrentParameterJacobian(String pName, double[] dYdP) {
/* 308 */     double[] p = this.efode.getSecondaryState(this.index);
/* 310 */     int i = this.stateDim * this.stateDim;
/* 311 */     for (ParameterConfiguration param : this.selectedParameters) {
/* 312 */       if (param.getParameterName().equals(pName)) {
/* 313 */         System.arraycopy(p, i, dYdP, 0, this.stateDim);
/*     */         return;
/*     */       } 
/* 316 */       i += this.stateDim;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkDimension(int expected, Object array) throws DimensionMismatchException {
/* 328 */     int arrayDimension = (array == null) ? 0 : Array.getLength(array);
/* 329 */     if (arrayDimension != expected)
/* 330 */       throw new DimensionMismatchException(arrayDimension, expected); 
/*     */   }
/*     */   
/*     */   private class JacobiansSecondaryEquations implements SecondaryEquations {
/*     */     private JacobiansSecondaryEquations() {}
/*     */     
/*     */     public int getDimension() {
/* 344 */       return JacobianMatrices.this.stateDim * (JacobianMatrices.this.stateDim + JacobianMatrices.this.paramDim);
/*     */     }
/*     */     
/*     */     public void computeDerivatives(double t, double[] y, double[] yDot, double[] z, double[] zDot) {
/* 352 */       if (JacobianMatrices.this.dirtyParameter && JacobianMatrices.this.paramDim != 0) {
/* 353 */         JacobianMatrices.this.jacobianProviders.add(new ParameterJacobianWrapper(JacobianMatrices.this.jode, JacobianMatrices.this.pode, JacobianMatrices.this.selectedParameters));
/* 354 */         JacobianMatrices.this.dirtyParameter = false;
/*     */       } 
/* 361 */       double[][] dFdY = new double[JacobianMatrices.this.stateDim][JacobianMatrices.this.stateDim];
/* 362 */       JacobianMatrices.this.jode.computeMainStateJacobian(t, y, yDot, dFdY);
/* 365 */       for (int i = 0; i < JacobianMatrices.this.stateDim; i++) {
/* 366 */         double[] dFdYi = dFdY[i];
/* 367 */         for (int j = 0; j < JacobianMatrices.this.stateDim; j++) {
/* 368 */           double s = 0.0D;
/* 369 */           int startIndex = j;
/* 370 */           int zIndex = startIndex;
/* 371 */           for (int l = 0; l < JacobianMatrices.this.stateDim; l++) {
/* 372 */             s += dFdYi[l] * z[zIndex];
/* 373 */             zIndex += JacobianMatrices.this.stateDim;
/*     */           } 
/* 375 */           zDot[startIndex + i * JacobianMatrices.this.stateDim] = s;
/*     */         } 
/*     */       } 
/* 379 */       if (JacobianMatrices.this.paramDim != 0) {
/* 381 */         double[] dFdP = new double[JacobianMatrices.this.stateDim];
/* 382 */         int startIndex = JacobianMatrices.this.stateDim * JacobianMatrices.this.stateDim;
/* 383 */         for (ParameterConfiguration param : JacobianMatrices.this.selectedParameters) {
/* 384 */           boolean found = false;
/* 385 */           for (ParameterJacobianProvider provider : JacobianMatrices.this.jacobianProviders) {
/* 386 */             if (provider.isSupported(param.getParameterName()))
/*     */               try {
/* 388 */                 provider.computeParameterJacobian(t, y, yDot, param.getParameterName(), dFdP);
/* 389 */                 for (int j = 0; j < JacobianMatrices.this.stateDim; j++) {
/* 390 */                   double[] dFdYi = dFdY[j];
/* 391 */                   int zIndex = startIndex;
/* 392 */                   double s = dFdP[j];
/* 393 */                   for (int l = 0; l < JacobianMatrices.this.stateDim; l++) {
/* 394 */                     s += dFdYi[l] * z[zIndex];
/* 395 */                     zIndex++;
/*     */                   } 
/* 397 */                   zDot[startIndex + j] = s;
/*     */                 } 
/* 399 */                 startIndex += JacobianMatrices.this.stateDim;
/* 400 */                 found = true;
/*     */                 break;
/* 402 */               } catch (IllegalArgumentException iae) {} 
/*     */           } 
/* 406 */           if (!found)
/* 407 */             throw new MathIllegalArgumentException(LocalizedFormats.UNKNOWN_PARAMETER, new Object[] { param }); 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static class MainStateJacobianWrapper implements MainStateJacobianProvider {
/*     */     private final FirstOrderDifferentialEquations ode;
/*     */     
/*     */     private final double[] hY;
/*     */     
/*     */     public MainStateJacobianWrapper(FirstOrderDifferentialEquations ode, double[] hY) {
/* 434 */       this.ode = ode;
/* 435 */       this.hY = (double[])hY.clone();
/*     */     }
/*     */     
/*     */     public int getDimension() {
/* 440 */       return this.ode.getDimension();
/*     */     }
/*     */     
/*     */     public void computeDerivatives(double t, double[] y, double[] yDot) {
/* 445 */       this.ode.computeDerivatives(t, y, yDot);
/*     */     }
/*     */     
/*     */     public void computeMainStateJacobian(double t, double[] y, double[] yDot, double[][] dFdY) {
/* 452 */       int n = this.ode.getDimension();
/* 453 */       double[] tmpDot = new double[n];
/* 455 */       for (int j = 0; j < n; j++) {
/* 456 */         double savedYj = y[j];
/* 457 */         y[j] = y[j] + this.hY[j];
/* 458 */         this.ode.computeDerivatives(t, y, tmpDot);
/* 459 */         for (int i = 0; i < n; i++)
/* 460 */           dFdY[i][j] = (tmpDot[i] - yDot[i]) / this.hY[j]; 
/* 462 */         y[j] = savedYj;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\JacobianMatrices.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */