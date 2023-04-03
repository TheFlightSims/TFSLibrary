/*     */ package org.apache.commons.math3.optimization.linear;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.optimization.PointValuePair;
/*     */ import org.apache.commons.math3.util.Precision;
/*     */ 
/*     */ public class SimplexSolver extends AbstractLinearOptimizer {
/*     */   private static final double DEFAULT_EPSILON = 1.0E-6D;
/*     */   
/*     */   private static final int DEFAULT_ULPS = 10;
/*     */   
/*     */   private final double epsilon;
/*     */   
/*     */   private final int maxUlps;
/*     */   
/*     */   public SimplexSolver() {
/*  51 */     this(1.0E-6D, 10);
/*     */   }
/*     */   
/*     */   public SimplexSolver(double epsilon, int maxUlps) {
/*  60 */     this.epsilon = epsilon;
/*  61 */     this.maxUlps = maxUlps;
/*     */   }
/*     */   
/*     */   private Integer getPivotColumn(SimplexTableau tableau) {
/*  70 */     double minValue = 0.0D;
/*  71 */     Integer minPos = null;
/*  72 */     for (int i = tableau.getNumObjectiveFunctions(); i < tableau.getWidth() - 1; i++) {
/*  73 */       double entry = tableau.getEntry(0, i);
/*  74 */       if (Precision.compareTo(entry, minValue, this.maxUlps) < 0) {
/*  75 */         minValue = entry;
/*  76 */         minPos = Integer.valueOf(i);
/*     */       } 
/*     */     } 
/*  79 */     return minPos;
/*     */   }
/*     */   
/*     */   private Integer getPivotRow(SimplexTableau tableau, int col) {
/*  90 */     List<Integer> minRatioPositions = new ArrayList<Integer>();
/*  91 */     double minRatio = Double.MAX_VALUE;
/*  92 */     for (int i = tableau.getNumObjectiveFunctions(); i < tableau.getHeight(); i++) {
/*  93 */       double rhs = tableau.getEntry(i, tableau.getWidth() - 1);
/*  94 */       double entry = tableau.getEntry(i, col);
/*  96 */       if (Precision.compareTo(entry, 0.0D, this.maxUlps) > 0) {
/*  97 */         double ratio = rhs / entry;
/*  98 */         int cmp = Precision.compareTo(ratio, minRatio, this.maxUlps);
/*  99 */         if (cmp == 0) {
/* 100 */           minRatioPositions.add(Integer.valueOf(i));
/* 101 */         } else if (cmp < 0) {
/* 102 */           minRatio = ratio;
/* 103 */           minRatioPositions = new ArrayList<Integer>();
/* 104 */           minRatioPositions.add(Integer.valueOf(i));
/*     */         } 
/*     */       } 
/*     */     } 
/* 109 */     if (minRatioPositions.size() == 0)
/* 110 */       return null; 
/* 111 */     if (minRatioPositions.size() > 1)
/* 114 */       for (Integer row : minRatioPositions) {
/* 115 */         for (int j = 0; j < tableau.getNumArtificialVariables(); j++) {
/* 116 */           int column = j + tableau.getArtificialVariableOffset();
/* 117 */           double entry = tableau.getEntry(row.intValue(), column);
/* 118 */           if (Precision.equals(entry, 1.0D, this.maxUlps) && row.equals(tableau.getBasicRow(column)))
/* 120 */             return row; 
/*     */         } 
/*     */       }  
/* 125 */     return minRatioPositions.get(0);
/*     */   }
/*     */   
/*     */   protected void doIteration(SimplexTableau tableau) throws MaxCountExceededException, UnboundedSolutionException {
/* 137 */     incrementIterationsCounter();
/* 139 */     Integer pivotCol = getPivotColumn(tableau);
/* 140 */     Integer pivotRow = getPivotRow(tableau, pivotCol.intValue());
/* 141 */     if (pivotRow == null)
/* 142 */       throw new UnboundedSolutionException(); 
/* 146 */     double pivotVal = tableau.getEntry(pivotRow.intValue(), pivotCol.intValue());
/* 147 */     tableau.divideRow(pivotRow.intValue(), pivotVal);
/* 150 */     for (int i = 0; i < tableau.getHeight(); i++) {
/* 151 */       if (i != pivotRow.intValue()) {
/* 152 */         double multiplier = tableau.getEntry(i, pivotCol.intValue());
/* 153 */         tableau.subtractRow(i, pivotRow.intValue(), multiplier);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void solvePhase1(SimplexTableau tableau) throws MaxCountExceededException, UnboundedSolutionException, NoFeasibleSolutionException {
/* 169 */     if (tableau.getNumArtificialVariables() == 0)
/*     */       return; 
/* 173 */     while (!tableau.isOptimal())
/* 174 */       doIteration(tableau); 
/* 178 */     if (!Precision.equals(tableau.getEntry(0, tableau.getRhsOffset()), 0.0D, this.epsilon))
/* 179 */       throw new NoFeasibleSolutionException(); 
/*     */   }
/*     */   
/*     */   public PointValuePair doOptimize() throws MaxCountExceededException, UnboundedSolutionException, NoFeasibleSolutionException {
/* 187 */     SimplexTableau tableau = new SimplexTableau(getFunction(), getConstraints(), getGoalType(), restrictToNonNegative(), this.epsilon, this.maxUlps);
/* 195 */     solvePhase1(tableau);
/* 196 */     tableau.dropPhase1Objective();
/* 198 */     while (!tableau.isOptimal())
/* 199 */       doIteration(tableau); 
/* 201 */     return tableau.getSolution();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\linear\SimplexSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */