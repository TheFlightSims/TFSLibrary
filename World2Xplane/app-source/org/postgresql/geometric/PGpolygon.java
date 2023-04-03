/*     */ package org.postgresql.geometric;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.SQLException;
/*     */ import org.postgresql.util.PGobject;
/*     */ import org.postgresql.util.PGtokenizer;
/*     */ 
/*     */ public class PGpolygon extends PGobject implements Serializable, Cloneable {
/*     */   public PGpoint[] points;
/*     */   
/*     */   public PGpolygon(PGpoint[] points) {
/*  34 */     this();
/*  35 */     this.points = points;
/*     */   }
/*     */   
/*     */   public PGpolygon(String s) throws SQLException {
/*  44 */     this();
/*  45 */     setValue(s);
/*     */   }
/*     */   
/*     */   public PGpolygon() {
/*  53 */     setType("polygon");
/*     */   }
/*     */   
/*     */   public void setValue(String s) throws SQLException {
/*  62 */     PGtokenizer t = new PGtokenizer(PGtokenizer.removePara(s), ',');
/*  63 */     int npoints = t.getSize();
/*  64 */     this.points = new PGpoint[npoints];
/*  65 */     for (int p = 0; p < npoints; p++)
/*  66 */       this.points[p] = new PGpoint(t.getToken(p)); 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  75 */     if (obj instanceof PGpolygon) {
/*  77 */       PGpolygon p = (PGpolygon)obj;
/*  79 */       if (p.points.length != this.points.length)
/*  80 */         return false; 
/*  82 */       for (int i = 0; i < this.points.length; i++) {
/*  83 */         if (!this.points[i].equals(p.points[i]))
/*  84 */           return false; 
/*     */       } 
/*  86 */       return true;
/*     */     } 
/*  88 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  93 */     int hash = 0;
/*  94 */     for (int i = 0; i < this.points.length && i < 5; i++)
/*  96 */       hash ^= this.points[i].hashCode(); 
/*  98 */     return hash;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 103 */     PGpolygon newPGpolygon = (PGpolygon)super.clone();
/* 104 */     if (newPGpolygon.points != null) {
/* 106 */       newPGpolygon.points = (PGpoint[])newPGpolygon.points.clone();
/* 107 */       for (int i = 0; i < newPGpolygon.points.length; i++) {
/* 108 */         if (newPGpolygon.points[i] != null)
/* 109 */           newPGpolygon.points[i] = (PGpoint)newPGpolygon.points[i].clone(); 
/*     */       } 
/*     */     } 
/* 111 */     return newPGpolygon;
/*     */   }
/*     */   
/*     */   public String getValue() {
/* 119 */     StringBuffer b = new StringBuffer();
/* 120 */     b.append("(");
/* 121 */     for (int p = 0; p < this.points.length; p++) {
/* 123 */       if (p > 0)
/* 124 */         b.append(","); 
/* 125 */       b.append(this.points[p].toString());
/*     */     } 
/* 127 */     b.append(")");
/* 128 */     return b.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\geometric\PGpolygon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */