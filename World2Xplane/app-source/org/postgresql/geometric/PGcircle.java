/*     */ package org.postgresql.geometric;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.SQLException;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PGobject;
/*     */ import org.postgresql.util.PGtokenizer;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public class PGcircle extends PGobject implements Serializable, Cloneable {
/*     */   public PGpoint center;
/*     */   
/*     */   public double radius;
/*     */   
/*     */   public PGcircle(double x, double y, double r) {
/*  44 */     this(new PGpoint(x, y), r);
/*     */   }
/*     */   
/*     */   public PGcircle(PGpoint c, double r) {
/*  53 */     this();
/*  54 */     this.center = c;
/*  55 */     this.radius = r;
/*     */   }
/*     */   
/*     */   public PGcircle(String s) throws SQLException {
/*  64 */     this();
/*  65 */     setValue(s);
/*     */   }
/*     */   
/*     */   public PGcircle() {
/*  73 */     setType("circle");
/*     */   }
/*     */   
/*     */   public void setValue(String s) throws SQLException {
/*  82 */     PGtokenizer t = new PGtokenizer(PGtokenizer.removeAngle(s), ',');
/*  83 */     if (t.getSize() != 2)
/*  84 */       throw new PSQLException(GT.tr("Conversion to type {0} failed: {1}.", new Object[] { this.type, s }), PSQLState.DATA_TYPE_MISMATCH); 
/*     */     try {
/*  88 */       this.center = new PGpoint(t.getToken(0));
/*  89 */       this.radius = Double.valueOf(t.getToken(1)).doubleValue();
/*  91 */     } catch (NumberFormatException e) {
/*  93 */       throw new PSQLException(GT.tr("Conversion to type {0} failed: {1}.", new Object[] { this.type, s }), PSQLState.DATA_TYPE_MISMATCH, e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 103 */     if (obj instanceof PGcircle) {
/* 105 */       PGcircle p = (PGcircle)obj;
/* 106 */       return (p.center.equals(this.center) && p.radius == this.radius);
/*     */     } 
/* 108 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 113 */     long v = Double.doubleToLongBits(this.radius);
/* 114 */     return (int)(this.center.hashCode() ^ v ^ v >>> 32L);
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 119 */     PGcircle newPGcircle = (PGcircle)super.clone();
/* 120 */     if (newPGcircle.center != null)
/* 121 */       newPGcircle.center = (PGpoint)newPGcircle.center.clone(); 
/* 122 */     return newPGcircle;
/*     */   }
/*     */   
/*     */   public String getValue() {
/* 130 */     return "<" + this.center + "," + this.radius + ">";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\geometric\PGcircle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */