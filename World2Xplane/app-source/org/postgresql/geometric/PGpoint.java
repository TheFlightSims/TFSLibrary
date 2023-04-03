/*     */ package org.postgresql.geometric;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.io.Serializable;
/*     */ import java.sql.SQLException;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PGobject;
/*     */ import org.postgresql.util.PGtokenizer;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public class PGpoint extends PGobject implements Serializable, Cloneable {
/*     */   public double x;
/*     */   
/*     */   public double y;
/*     */   
/*     */   public PGpoint(double x, double y) {
/*  46 */     this();
/*  47 */     this.x = x;
/*  48 */     this.y = y;
/*     */   }
/*     */   
/*     */   public PGpoint(String value) throws SQLException {
/*  59 */     this();
/*  60 */     setValue(value);
/*     */   }
/*     */   
/*     */   public PGpoint() {
/*  68 */     setType("point");
/*     */   }
/*     */   
/*     */   public void setValue(String s) throws SQLException {
/*  77 */     PGtokenizer t = new PGtokenizer(PGtokenizer.removePara(s), ',');
/*     */     try {
/*  80 */       this.x = Double.valueOf(t.getToken(0)).doubleValue();
/*  81 */       this.y = Double.valueOf(t.getToken(1)).doubleValue();
/*  83 */     } catch (NumberFormatException e) {
/*  85 */       throw new PSQLException(GT.tr("Conversion to type {0} failed: {1}.", new Object[] { this.type, s }), PSQLState.DATA_TYPE_MISMATCH, e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  95 */     if (obj instanceof PGpoint) {
/*  97 */       PGpoint p = (PGpoint)obj;
/*  98 */       return (this.x == p.x && this.y == p.y);
/*     */     } 
/* 100 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 105 */     long v1 = Double.doubleToLongBits(this.x);
/* 106 */     long v2 = Double.doubleToLongBits(this.y);
/* 107 */     return (int)(v1 ^ v2 ^ v1 >>> 32L ^ v2 >>> 32L);
/*     */   }
/*     */   
/*     */   public String getValue() {
/* 115 */     return "(" + this.x + "," + this.y + ")";
/*     */   }
/*     */   
/*     */   public void translate(int x, int y) {
/* 125 */     translate(x, y);
/*     */   }
/*     */   
/*     */   public void translate(double x, double y) {
/* 135 */     this.x += x;
/* 136 */     this.y += y;
/*     */   }
/*     */   
/*     */   public void move(int x, int y) {
/* 146 */     setLocation(x, y);
/*     */   }
/*     */   
/*     */   public void move(double x, double y) {
/* 156 */     this.x = x;
/* 157 */     this.y = y;
/*     */   }
/*     */   
/*     */   public void setLocation(int x, int y) {
/* 169 */     move(x, y);
/*     */   }
/*     */   
/*     */   public void setLocation(Point p) {
/* 180 */     setLocation(p.x, p.y);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\geometric\PGpoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */