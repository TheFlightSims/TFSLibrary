/*     */ package org.apache.commons.math3.optimization.linear;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.linear.ArrayRealVector;
/*     */ import org.apache.commons.math3.linear.MatrixUtils;
/*     */ import org.apache.commons.math3.linear.RealVector;
/*     */ 
/*     */ public class LinearConstraint implements Serializable {
/*     */   private static final long serialVersionUID = -764632794033034092L;
/*     */   
/*     */   private final transient RealVector coefficients;
/*     */   
/*     */   private final Relationship relationship;
/*     */   
/*     */   private final double value;
/*     */   
/*     */   public LinearConstraint(double[] coefficients, Relationship relationship, double value) {
/*  81 */     this((RealVector)new ArrayRealVector(coefficients), relationship, value);
/*     */   }
/*     */   
/*     */   public LinearConstraint(RealVector coefficients, Relationship relationship, double value) {
/* 100 */     this.coefficients = coefficients;
/* 101 */     this.relationship = relationship;
/* 102 */     this.value = value;
/*     */   }
/*     */   
/*     */   public LinearConstraint(double[] lhsCoefficients, double lhsConstant, Relationship relationship, double[] rhsCoefficients, double rhsConstant) {
/* 127 */     double[] sub = new double[lhsCoefficients.length];
/* 128 */     for (int i = 0; i < sub.length; i++)
/* 129 */       sub[i] = lhsCoefficients[i] - rhsCoefficients[i]; 
/* 131 */     this.coefficients = (RealVector)new ArrayRealVector(sub, false);
/* 132 */     this.relationship = relationship;
/* 133 */     this.value = rhsConstant - lhsConstant;
/*     */   }
/*     */   
/*     */   public LinearConstraint(RealVector lhsCoefficients, double lhsConstant, Relationship relationship, RealVector rhsCoefficients, double rhsConstant) {
/* 158 */     this.coefficients = lhsCoefficients.subtract(rhsCoefficients);
/* 159 */     this.relationship = relationship;
/* 160 */     this.value = rhsConstant - lhsConstant;
/*     */   }
/*     */   
/*     */   public RealVector getCoefficients() {
/* 168 */     return this.coefficients;
/*     */   }
/*     */   
/*     */   public Relationship getRelationship() {
/* 176 */     return this.relationship;
/*     */   }
/*     */   
/*     */   public double getValue() {
/* 184 */     return this.value;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 191 */     if (this == other)
/* 192 */       return true; 
/* 195 */     if (other instanceof LinearConstraint) {
/* 196 */       LinearConstraint rhs = (LinearConstraint)other;
/* 197 */       return (this.relationship == rhs.relationship && this.value == rhs.value && this.coefficients.equals(rhs.coefficients));
/*     */     } 
/* 201 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 207 */     return this.relationship.hashCode() ^ Double.valueOf(this.value).hashCode() ^ this.coefficients.hashCode();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream oos) throws IOException {
/* 218 */     oos.defaultWriteObject();
/* 219 */     MatrixUtils.serializeRealVector(this.coefficients, oos);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
/* 229 */     ois.defaultReadObject();
/* 230 */     MatrixUtils.deserializeRealVector(this, "coefficients", ois);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\linear\LinearConstraint.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */