/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class Rotation implements Serializable {
/*     */   private static final long serialVersionUID = -4662815260201591676L;
/*     */   
/*  60 */   public static final Rotation CLOCKWISE = new Rotation("Rotation.CLOCKWISE", -1.0D);
/*     */   
/*  64 */   public static final Rotation ANTICLOCKWISE = new Rotation("Rotation.ANTICLOCKWISE", 1.0D);
/*     */   
/*     */   private String name;
/*     */   
/*     */   private double factor;
/*     */   
/*     */   private Rotation(String name, double factor) {
/*  83 */     this.name = name;
/*  84 */     this.factor = factor;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  93 */     return this.name;
/*     */   }
/*     */   
/*     */   public double getFactor() {
/* 103 */     return this.factor;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 115 */     if (this == o)
/* 116 */       return true; 
/* 118 */     if (!(o instanceof Rotation))
/* 119 */       return false; 
/* 122 */     Rotation rotation = (Rotation)o;
/* 124 */     if (this.factor != rotation.factor)
/* 125 */       return false; 
/* 128 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 137 */     long temp = Double.doubleToLongBits(this.factor);
/* 138 */     return (int)(temp ^ temp >>> 32L);
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 149 */     if (equals(CLOCKWISE))
/* 150 */       return CLOCKWISE; 
/* 152 */     if (equals(ANTICLOCKWISE))
/* 153 */       return ANTICLOCKWISE; 
/* 155 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\Rotation.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */