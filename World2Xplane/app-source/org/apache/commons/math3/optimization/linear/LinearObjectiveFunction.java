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
/*     */ public class LinearObjectiveFunction implements Serializable {
/*     */   private static final long serialVersionUID = -4531815507568396090L;
/*     */   
/*     */   private final transient RealVector coefficients;
/*     */   
/*     */   private final double constantTerm;
/*     */   
/*     */   public LinearObjectiveFunction(double[] coefficients, double constantTerm) {
/*  58 */     this((RealVector)new ArrayRealVector(coefficients), constantTerm);
/*     */   }
/*     */   
/*     */   public LinearObjectiveFunction(RealVector coefficients, double constantTerm) {
/*  66 */     this.coefficients = coefficients;
/*  67 */     this.constantTerm = constantTerm;
/*     */   }
/*     */   
/*     */   public RealVector getCoefficients() {
/*  75 */     return this.coefficients;
/*     */   }
/*     */   
/*     */   public double getConstantTerm() {
/*  83 */     return this.constantTerm;
/*     */   }
/*     */   
/*     */   public double getValue(double[] point) {
/*  92 */     return this.coefficients.dotProduct((RealVector)new ArrayRealVector(point, false)) + this.constantTerm;
/*     */   }
/*     */   
/*     */   public double getValue(RealVector point) {
/* 101 */     return this.coefficients.dotProduct(point) + this.constantTerm;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 108 */     if (this == other)
/* 109 */       return true; 
/* 112 */     if (other instanceof LinearObjectiveFunction) {
/* 113 */       LinearObjectiveFunction rhs = (LinearObjectiveFunction)other;
/* 114 */       return (this.constantTerm == rhs.constantTerm && this.coefficients.equals(rhs.coefficients));
/*     */     } 
/* 117 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 123 */     return Double.valueOf(this.constantTerm).hashCode() ^ this.coefficients.hashCode();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream oos) throws IOException {
/* 132 */     oos.defaultWriteObject();
/* 133 */     MatrixUtils.serializeRealVector(this.coefficients, oos);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
/* 143 */     ois.defaultReadObject();
/* 144 */     MatrixUtils.deserializeRealVector(this, "coefficients", ois);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\linear\LinearObjectiveFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */