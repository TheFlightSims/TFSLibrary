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
/*     */ public class PGbox extends PGobject implements Serializable, Cloneable {
/*  29 */   public PGpoint[] point = new PGpoint[2];
/*     */   
/*     */   public PGbox(double x1, double y1, double x2, double y2) {
/*  39 */     this();
/*  40 */     this.point[0] = new PGpoint(x1, y1);
/*  41 */     this.point[1] = new PGpoint(x2, y2);
/*     */   }
/*     */   
/*     */   public PGbox(PGpoint p1, PGpoint p2) {
/*  50 */     this();
/*  51 */     this.point[0] = p1;
/*  52 */     this.point[1] = p2;
/*     */   }
/*     */   
/*     */   public PGbox(String s) throws SQLException {
/*  61 */     this();
/*  62 */     setValue(s);
/*     */   }
/*     */   
/*     */   public PGbox() {
/*  70 */     setType("box");
/*     */   }
/*     */   
/*     */   public void setValue(String value) throws SQLException {
/*  82 */     PGtokenizer t = new PGtokenizer(value, ',');
/*  83 */     if (t.getSize() != 2)
/*  84 */       throw new PSQLException(GT.tr("Conversion to type {0} failed: {1}.", new Object[] { this.type, value }), PSQLState.DATA_TYPE_MISMATCH); 
/*  86 */     this.point[0] = new PGpoint(t.getToken(0));
/*  87 */     this.point[1] = new PGpoint(t.getToken(1));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  96 */     if (obj instanceof PGbox) {
/*  98 */       PGbox p = (PGbox)obj;
/* 101 */       if (p.point[0].equals(this.point[0]) && p.point[1].equals(this.point[1]))
/* 102 */         return true; 
/* 105 */       if (p.point[0].equals(this.point[1]) && p.point[1].equals(this.point[0]))
/* 106 */         return true; 
/* 110 */       if ((p.point[0]).x == (this.point[0]).x && (p.point[0]).y == (this.point[1]).y && (p.point[1]).x == (this.point[1]).x && (p.point[1]).y == (this.point[0]).y)
/* 112 */         return true; 
/* 116 */       if ((p.point[0]).x == (this.point[1]).x && (p.point[0]).y == (this.point[0]).y && (p.point[1]).x == (this.point[0]).x && (p.point[1]).y == (this.point[1]).y)
/* 118 */         return true; 
/*     */     } 
/* 121 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 130 */     return this.point[0].hashCode() ^ this.point[1].hashCode();
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 135 */     PGbox newPGbox = (PGbox)super.clone();
/* 136 */     if (newPGbox.point != null) {
/* 138 */       newPGbox.point = (PGpoint[])newPGbox.point.clone();
/* 139 */       for (int i = 0; i < newPGbox.point.length; i++) {
/* 140 */         if (newPGbox.point[i] != null)
/* 141 */           newPGbox.point[i] = (PGpoint)newPGbox.point[i].clone(); 
/*     */       } 
/*     */     } 
/* 143 */     return newPGbox;
/*     */   }
/*     */   
/*     */   public String getValue() {
/* 151 */     return this.point[0].toString() + "," + this.point[1].toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\geometric\PGbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */