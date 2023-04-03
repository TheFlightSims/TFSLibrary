/*     */ package org.apache.commons.math3.ode;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ 
/*     */ public class ExpandableStatefulODE {
/*     */   private final FirstOrderDifferentialEquations primary;
/*     */   
/*     */   private final EquationsMapper primaryMapper;
/*     */   
/*     */   private double time;
/*     */   
/*     */   private final double[] primaryState;
/*     */   
/*     */   private final double[] primaryStateDot;
/*     */   
/*     */   private List<SecondaryComponent> components;
/*     */   
/*     */   public ExpandableStatefulODE(FirstOrderDifferentialEquations primary) {
/*  73 */     int n = primary.getDimension();
/*  74 */     this.primary = primary;
/*  75 */     this.primaryMapper = new EquationsMapper(0, n);
/*  76 */     this.time = Double.NaN;
/*  77 */     this.primaryState = new double[n];
/*  78 */     this.primaryStateDot = new double[n];
/*  79 */     this.components = new ArrayList<SecondaryComponent>();
/*     */   }
/*     */   
/*     */   public FirstOrderDifferentialEquations getPrimary() {
/*  86 */     return this.primary;
/*     */   }
/*     */   
/*     */   public int getTotalDimension() {
/*  96 */     if (this.components.isEmpty())
/*  98 */       return this.primaryMapper.getDimension(); 
/* 101 */     EquationsMapper lastMapper = (this.components.get(this.components.size() - 1)).mapper;
/* 102 */     return lastMapper.getFirstIndex() + lastMapper.getDimension();
/*     */   }
/*     */   
/*     */   public void computeDerivatives(double t, double[] y, double[] yDot) {
/* 114 */     this.primaryMapper.extractEquationData(y, this.primaryState);
/* 115 */     this.primary.computeDerivatives(t, this.primaryState, this.primaryStateDot);
/* 116 */     this.primaryMapper.insertEquationData(this.primaryStateDot, yDot);
/* 119 */     for (SecondaryComponent component : this.components) {
/* 120 */       component.mapper.extractEquationData(y, component.state);
/* 121 */       component.equation.computeDerivatives(t, this.primaryState, this.primaryStateDot, component.state, component.stateDot);
/* 123 */       component.mapper.insertEquationData(component.stateDot, yDot);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int addSecondaryEquations(SecondaryEquations secondary) {
/*     */     int firstIndex;
/* 135 */     if (this.components.isEmpty()) {
/* 137 */       this.components = new ArrayList<SecondaryComponent>();
/* 138 */       firstIndex = this.primary.getDimension();
/*     */     } else {
/* 140 */       SecondaryComponent last = this.components.get(this.components.size() - 1);
/* 141 */       firstIndex = last.mapper.getFirstIndex() + last.mapper.getDimension();
/*     */     } 
/* 144 */     this.components.add(new SecondaryComponent(secondary, firstIndex));
/* 146 */     return this.components.size() - 1;
/*     */   }
/*     */   
/*     */   public EquationsMapper getPrimaryMapper() {
/* 155 */     return this.primaryMapper;
/*     */   }
/*     */   
/*     */   public EquationsMapper[] getSecondaryMappers() {
/* 163 */     EquationsMapper[] mappers = new EquationsMapper[this.components.size()];
/* 164 */     for (int i = 0; i < mappers.length; i++)
/* 165 */       mappers[i] = (this.components.get(i)).mapper; 
/* 167 */     return mappers;
/*     */   }
/*     */   
/*     */   public void setTime(double time) {
/* 174 */     this.time = time;
/*     */   }
/*     */   
/*     */   public double getTime() {
/* 181 */     return this.time;
/*     */   }
/*     */   
/*     */   public void setPrimaryState(double[] primaryState) throws DimensionMismatchException {
/* 192 */     if (primaryState.length != this.primaryState.length)
/* 193 */       throw new DimensionMismatchException(primaryState.length, this.primaryState.length); 
/* 197 */     System.arraycopy(primaryState, 0, this.primaryState, 0, primaryState.length);
/*     */   }
/*     */   
/*     */   public double[] getPrimaryState() {
/* 205 */     return (double[])this.primaryState.clone();
/*     */   }
/*     */   
/*     */   public double[] getPrimaryStateDot() {
/* 212 */     return (double[])this.primaryStateDot.clone();
/*     */   }
/*     */   
/*     */   public void setSecondaryState(int index, double[] secondaryState) throws DimensionMismatchException {
/* 226 */     double[] localArray = (this.components.get(index)).state;
/* 229 */     if (secondaryState.length != localArray.length)
/* 230 */       throw new DimensionMismatchException(secondaryState.length, localArray.length); 
/* 234 */     System.arraycopy(secondaryState, 0, localArray, 0, secondaryState.length);
/*     */   }
/*     */   
/*     */   public double[] getSecondaryState(int index) {
/* 244 */     return (double[])(this.components.get(index)).state.clone();
/*     */   }
/*     */   
/*     */   public double[] getSecondaryStateDot(int index) {
/* 253 */     return (double[])(this.components.get(index)).stateDot.clone();
/*     */   }
/*     */   
/*     */   public void setCompleteState(double[] completeState) throws DimensionMismatchException {
/* 265 */     if (completeState.length != getTotalDimension())
/* 266 */       throw new DimensionMismatchException(completeState.length, getTotalDimension()); 
/* 270 */     this.primaryMapper.extractEquationData(completeState, this.primaryState);
/* 271 */     for (SecondaryComponent component : this.components)
/* 272 */       component.mapper.extractEquationData(completeState, component.state); 
/*     */   }
/*     */   
/*     */   public double[] getCompleteState() {
/* 285 */     double[] completeState = new double[getTotalDimension()];
/* 288 */     this.primaryMapper.insertEquationData(this.primaryState, completeState);
/* 289 */     for (SecondaryComponent component : this.components)
/* 290 */       component.mapper.insertEquationData(component.state, completeState); 
/* 293 */     return completeState;
/*     */   }
/*     */   
/*     */   private static class SecondaryComponent {
/*     */     private final SecondaryEquations equation;
/*     */     
/*     */     private final EquationsMapper mapper;
/*     */     
/*     */     private final double[] state;
/*     */     
/*     */     private final double[] stateDot;
/*     */     
/*     */     public SecondaryComponent(SecondaryEquations equation, int firstIndex) {
/* 317 */       int n = equation.getDimension();
/* 318 */       this.equation = equation;
/* 319 */       this.mapper = new EquationsMapper(firstIndex, n);
/* 320 */       this.state = new double[n];
/* 321 */       this.stateDot = new double[n];
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\ExpandableStatefulODE.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */