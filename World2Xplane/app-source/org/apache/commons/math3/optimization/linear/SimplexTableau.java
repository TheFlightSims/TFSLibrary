/*     */ package org.apache.commons.math3.optimization.linear;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.math3.linear.Array2DRowRealMatrix;
/*     */ import org.apache.commons.math3.linear.MatrixUtils;
/*     */ import org.apache.commons.math3.linear.RealMatrix;
/*     */ import org.apache.commons.math3.linear.RealVector;
/*     */ import org.apache.commons.math3.optimization.GoalType;
/*     */ import org.apache.commons.math3.optimization.PointValuePair;
/*     */ import org.apache.commons.math3.util.Precision;
/*     */ 
/*     */ class SimplexTableau implements Serializable {
/*     */   private static final String NEGATIVE_VAR_COLUMN_LABEL = "x-";
/*     */   
/*     */   private static final int DEFAULT_ULPS = 10;
/*     */   
/*     */   private static final long serialVersionUID = -1369660067587938365L;
/*     */   
/*     */   private final LinearObjectiveFunction f;
/*     */   
/*     */   private final List<LinearConstraint> constraints;
/*     */   
/*     */   private final boolean restrictToNonNegative;
/*     */   
/*  84 */   private final List<String> columnLabels = new ArrayList<String>();
/*     */   
/*     */   private transient RealMatrix tableau;
/*     */   
/*     */   private final int numDecisionVariables;
/*     */   
/*     */   private final int numSlackVariables;
/*     */   
/*     */   private int numArtificialVariables;
/*     */   
/*     */   private final double epsilon;
/*     */   
/*     */   private final int maxUlps;
/*     */   
/*     */   SimplexTableau(LinearObjectiveFunction f, Collection<LinearConstraint> constraints, GoalType goalType, boolean restrictToNonNegative, double epsilon) {
/* 117 */     this(f, constraints, goalType, restrictToNonNegative, epsilon, 10);
/*     */   }
/*     */   
/*     */   SimplexTableau(LinearObjectiveFunction f, Collection<LinearConstraint> constraints, GoalType goalType, boolean restrictToNonNegative, double epsilon, int maxUlps) {
/* 135 */     this.f = f;
/* 136 */     this.constraints = normalizeConstraints(constraints);
/* 137 */     this.restrictToNonNegative = restrictToNonNegative;
/* 138 */     this.epsilon = epsilon;
/* 139 */     this.maxUlps = maxUlps;
/* 140 */     this.numDecisionVariables = f.getCoefficients().getDimension() + (restrictToNonNegative ? 0 : 1);
/* 142 */     this.numSlackVariables = getConstraintTypeCounts(Relationship.LEQ) + getConstraintTypeCounts(Relationship.GEQ);
/* 144 */     this.numArtificialVariables = getConstraintTypeCounts(Relationship.EQ) + getConstraintTypeCounts(Relationship.GEQ);
/* 146 */     this.tableau = createTableau((goalType == GoalType.MAXIMIZE));
/* 147 */     initializeColumnLabels();
/*     */   }
/*     */   
/*     */   protected void initializeColumnLabels() {
/* 154 */     if (getNumObjectiveFunctions() == 2)
/* 155 */       this.columnLabels.add("W"); 
/* 157 */     this.columnLabels.add("Z");
/*     */     int i;
/* 158 */     for (i = 0; i < getOriginalNumDecisionVariables(); i++)
/* 159 */       this.columnLabels.add("x" + i); 
/* 161 */     if (!this.restrictToNonNegative)
/* 162 */       this.columnLabels.add("x-"); 
/* 164 */     for (i = 0; i < getNumSlackVariables(); i++)
/* 165 */       this.columnLabels.add("s" + i); 
/* 167 */     for (i = 0; i < getNumArtificialVariables(); i++)
/* 168 */       this.columnLabels.add("a" + i); 
/* 170 */     this.columnLabels.add("RHS");
/*     */   }
/*     */   
/*     */   protected RealMatrix createTableau(boolean maximize) {
/* 181 */     int width = this.numDecisionVariables + this.numSlackVariables + this.numArtificialVariables + getNumObjectiveFunctions() + 1;
/* 183 */     int height = this.constraints.size() + getNumObjectiveFunctions();
/* 184 */     Array2DRowRealMatrix matrix = new Array2DRowRealMatrix(height, width);
/* 187 */     if (getNumObjectiveFunctions() == 2)
/* 188 */       matrix.setEntry(0, 0, -1.0D); 
/* 190 */     int zIndex = (getNumObjectiveFunctions() == 1) ? 0 : 1;
/* 191 */     matrix.setEntry(zIndex, zIndex, maximize ? 1.0D : -1.0D);
/* 192 */     RealVector objectiveCoefficients = maximize ? this.f.getCoefficients().mapMultiply(-1.0D) : this.f.getCoefficients();
/* 194 */     copyArray(objectiveCoefficients.toArray(), matrix.getDataRef()[zIndex]);
/* 195 */     matrix.setEntry(zIndex, width - 1, maximize ? this.f.getConstantTerm() : (-1.0D * this.f.getConstantTerm()));
/* 198 */     if (!this.restrictToNonNegative)
/* 199 */       matrix.setEntry(zIndex, getSlackVariableOffset() - 1, getInvertedCoefficientSum(objectiveCoefficients)); 
/* 204 */     int slackVar = 0;
/* 205 */     int artificialVar = 0;
/* 206 */     for (int i = 0; i < this.constraints.size(); i++) {
/* 207 */       LinearConstraint constraint = this.constraints.get(i);
/* 208 */       int row = getNumObjectiveFunctions() + i;
/* 211 */       copyArray(constraint.getCoefficients().toArray(), matrix.getDataRef()[row]);
/* 214 */       if (!this.restrictToNonNegative)
/* 215 */         matrix.setEntry(row, getSlackVariableOffset() - 1, getInvertedCoefficientSum(constraint.getCoefficients())); 
/* 220 */       matrix.setEntry(row, width - 1, constraint.getValue());
/* 223 */       if (constraint.getRelationship() == Relationship.LEQ) {
/* 224 */         matrix.setEntry(row, getSlackVariableOffset() + slackVar++, 1.0D);
/* 225 */       } else if (constraint.getRelationship() == Relationship.GEQ) {
/* 226 */         matrix.setEntry(row, getSlackVariableOffset() + slackVar++, -1.0D);
/*     */       } 
/* 230 */       if (constraint.getRelationship() == Relationship.EQ || constraint.getRelationship() == Relationship.GEQ) {
/* 232 */         matrix.setEntry(0, getArtificialVariableOffset() + artificialVar, 1.0D);
/* 233 */         matrix.setEntry(row, getArtificialVariableOffset() + artificialVar++, 1.0D);
/* 234 */         matrix.setRowVector(0, matrix.getRowVector(0).subtract(matrix.getRowVector(row)));
/*     */       } 
/*     */     } 
/* 238 */     return (RealMatrix)matrix;
/*     */   }
/*     */   
/*     */   public List<LinearConstraint> normalizeConstraints(Collection<LinearConstraint> originalConstraints) {
/* 247 */     List<LinearConstraint> normalized = new ArrayList<LinearConstraint>();
/* 248 */     for (LinearConstraint constraint : originalConstraints)
/* 249 */       normalized.add(normalize(constraint)); 
/* 251 */     return normalized;
/*     */   }
/*     */   
/*     */   private LinearConstraint normalize(LinearConstraint constraint) {
/* 260 */     if (constraint.getValue() < 0.0D)
/* 261 */       return new LinearConstraint(constraint.getCoefficients().mapMultiply(-1.0D), constraint.getRelationship().oppositeRelationship(), -1.0D * constraint.getValue()); 
/* 265 */     return new LinearConstraint(constraint.getCoefficients(), constraint.getRelationship(), constraint.getValue());
/*     */   }
/*     */   
/*     */   protected final int getNumObjectiveFunctions() {
/* 274 */     return (this.numArtificialVariables > 0) ? 2 : 1;
/*     */   }
/*     */   
/*     */   private int getConstraintTypeCounts(Relationship relationship) {
/* 283 */     int count = 0;
/* 284 */     for (LinearConstraint constraint : this.constraints) {
/* 285 */       if (constraint.getRelationship() == relationship)
/* 286 */         count++; 
/*     */     } 
/* 289 */     return count;
/*     */   }
/*     */   
/*     */   protected static double getInvertedCoefficientSum(RealVector coefficients) {
/* 298 */     double sum = 0.0D;
/* 299 */     for (double coefficient : coefficients.toArray())
/* 300 */       sum -= coefficient; 
/* 302 */     return sum;
/*     */   }
/*     */   
/*     */   protected Integer getBasicRow(int col) {
/* 311 */     Integer row = null;
/* 312 */     for (int i = 0; i < getHeight(); i++) {
/* 313 */       double entry = getEntry(i, col);
/* 314 */       if (Precision.equals(entry, 1.0D, this.maxUlps) && row == null) {
/* 315 */         row = Integer.valueOf(i);
/* 316 */       } else if (!Precision.equals(entry, 0.0D, this.maxUlps)) {
/* 317 */         return null;
/*     */       } 
/*     */     } 
/* 320 */     return row;
/*     */   }
/*     */   
/*     */   protected void dropPhase1Objective() {
/* 328 */     if (getNumObjectiveFunctions() == 1)
/*     */       return; 
/* 332 */     List<Integer> columnsToDrop = new ArrayList<Integer>();
/* 333 */     columnsToDrop.add(Integer.valueOf(0));
/*     */     int i;
/* 336 */     for (i = getNumObjectiveFunctions(); i < getArtificialVariableOffset(); i++) {
/* 337 */       double entry = this.tableau.getEntry(0, i);
/* 338 */       if (Precision.compareTo(entry, 0.0D, this.maxUlps) > 0)
/* 339 */         columnsToDrop.add(Integer.valueOf(i)); 
/*     */     } 
/* 344 */     for (i = 0; i < getNumArtificialVariables(); i++) {
/* 345 */       int col = i + getArtificialVariableOffset();
/* 346 */       if (getBasicRow(col) == null)
/* 347 */         columnsToDrop.add(Integer.valueOf(col)); 
/*     */     } 
/* 351 */     double[][] matrix = new double[getHeight() - 1][getWidth() - columnsToDrop.size()];
/*     */     int j;
/* 352 */     for (j = 1; j < getHeight(); j++) {
/* 353 */       int col = 0;
/* 354 */       for (int k = 0; k < getWidth(); k++) {
/* 355 */         if (!columnsToDrop.contains(Integer.valueOf(k)))
/* 356 */           matrix[j - 1][col++] = this.tableau.getEntry(j, k); 
/*     */       } 
/*     */     } 
/* 361 */     for (j = columnsToDrop.size() - 1; j >= 0; j--)
/* 362 */       this.columnLabels.remove(((Integer)columnsToDrop.get(j)).intValue()); 
/* 365 */     this.tableau = (RealMatrix)new Array2DRowRealMatrix(matrix);
/* 366 */     this.numArtificialVariables = 0;
/*     */   }
/*     */   
/*     */   private void copyArray(double[] src, double[] dest) {
/* 374 */     System.arraycopy(src, 0, dest, getNumObjectiveFunctions(), src.length);
/*     */   }
/*     */   
/*     */   boolean isOptimal() {
/* 382 */     for (int i = getNumObjectiveFunctions(); i < getWidth() - 1; i++) {
/* 383 */       double entry = this.tableau.getEntry(0, i);
/* 384 */       if (Precision.compareTo(entry, 0.0D, this.epsilon) < 0)
/* 385 */         return false; 
/*     */     } 
/* 388 */     return true;
/*     */   }
/*     */   
/*     */   protected PointValuePair getSolution() {
/* 397 */     int negativeVarColumn = this.columnLabels.indexOf("x-");
/* 398 */     Integer negativeVarBasicRow = (negativeVarColumn > 0) ? getBasicRow(negativeVarColumn) : null;
/* 399 */     double mostNegative = (negativeVarBasicRow == null) ? 0.0D : getEntry(negativeVarBasicRow.intValue(), getRhsOffset());
/* 401 */     Set<Integer> basicRows = new HashSet<Integer>();
/* 402 */     double[] coefficients = new double[getOriginalNumDecisionVariables()];
/* 403 */     for (int i = 0; i < coefficients.length; i++) {
/* 404 */       int colIndex = this.columnLabels.indexOf("x" + i);
/* 405 */       if (colIndex < 0) {
/* 406 */         coefficients[i] = 0.0D;
/*     */       } else {
/* 409 */         Integer basicRow = getBasicRow(colIndex);
/* 410 */         if (basicRow != null && basicRow.intValue() == 0) {
/* 414 */           coefficients[i] = 0.0D;
/* 415 */         } else if (basicRows.contains(basicRow)) {
/* 418 */           coefficients[i] = 0.0D - (this.restrictToNonNegative ? 0.0D : mostNegative);
/*     */         } else {
/* 420 */           basicRows.add(basicRow);
/* 421 */           coefficients[i] = ((basicRow == null) ? 0.0D : getEntry(basicRow.intValue(), getRhsOffset())) - (this.restrictToNonNegative ? 0.0D : mostNegative);
/*     */         } 
/*     */       } 
/*     */     } 
/* 426 */     return new PointValuePair(coefficients, this.f.getValue(coefficients));
/*     */   }
/*     */   
/*     */   protected void divideRow(int dividendRow, double divisor) {
/* 439 */     for (int j = 0; j < getWidth(); j++)
/* 440 */       this.tableau.setEntry(dividendRow, j, this.tableau.getEntry(dividendRow, j) / divisor); 
/*     */   }
/*     */   
/*     */   protected void subtractRow(int minuendRow, int subtrahendRow, double multiple) {
/* 456 */     this.tableau.setRowVector(minuendRow, this.tableau.getRowVector(minuendRow).subtract(this.tableau.getRowVector(subtrahendRow).mapMultiply(multiple)));
/*     */   }
/*     */   
/*     */   protected final int getWidth() {
/* 465 */     return this.tableau.getColumnDimension();
/*     */   }
/*     */   
/*     */   protected final int getHeight() {
/* 473 */     return this.tableau.getRowDimension();
/*     */   }
/*     */   
/*     */   protected final double getEntry(int row, int column) {
/* 482 */     return this.tableau.getEntry(row, column);
/*     */   }
/*     */   
/*     */   protected final void setEntry(int row, int column, double value) {
/* 492 */     this.tableau.setEntry(row, column, value);
/*     */   }
/*     */   
/*     */   protected final int getSlackVariableOffset() {
/* 500 */     return getNumObjectiveFunctions() + this.numDecisionVariables;
/*     */   }
/*     */   
/*     */   protected final int getArtificialVariableOffset() {
/* 508 */     return getNumObjectiveFunctions() + this.numDecisionVariables + this.numSlackVariables;
/*     */   }
/*     */   
/*     */   protected final int getRhsOffset() {
/* 516 */     return getWidth() - 1;
/*     */   }
/*     */   
/*     */   protected final int getNumDecisionVariables() {
/* 530 */     return this.numDecisionVariables;
/*     */   }
/*     */   
/*     */   protected final int getOriginalNumDecisionVariables() {
/* 539 */     return this.f.getCoefficients().getDimension();
/*     */   }
/*     */   
/*     */   protected final int getNumSlackVariables() {
/* 547 */     return this.numSlackVariables;
/*     */   }
/*     */   
/*     */   protected final int getNumArtificialVariables() {
/* 555 */     return this.numArtificialVariables;
/*     */   }
/*     */   
/*     */   protected final double[][] getData() {
/* 563 */     return this.tableau.getData();
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 570 */     if (this == other)
/* 571 */       return true; 
/* 574 */     if (other instanceof SimplexTableau) {
/* 575 */       SimplexTableau rhs = (SimplexTableau)other;
/* 576 */       return (this.restrictToNonNegative == rhs.restrictToNonNegative && this.numDecisionVariables == rhs.numDecisionVariables && this.numSlackVariables == rhs.numSlackVariables && this.numArtificialVariables == rhs.numArtificialVariables && this.epsilon == rhs.epsilon && this.maxUlps == rhs.maxUlps && this.f.equals(rhs.f) && this.constraints.equals(rhs.constraints) && this.tableau.equals(rhs.tableau));
/*     */     } 
/* 586 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 592 */     return Boolean.valueOf(this.restrictToNonNegative).hashCode() ^ this.numDecisionVariables ^ this.numSlackVariables ^ this.numArtificialVariables ^ Double.valueOf(this.epsilon).hashCode() ^ this.maxUlps ^ this.f.hashCode() ^ this.constraints.hashCode() ^ this.tableau.hashCode();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream oos) throws IOException {
/* 609 */     oos.defaultWriteObject();
/* 610 */     MatrixUtils.serializeRealMatrix(this.tableau, oos);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
/* 620 */     ois.defaultReadObject();
/* 621 */     MatrixUtils.deserializeRealMatrix(this, "tableau", ois);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\linear\SimplexTableau.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */