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
/*     */ public class PGlseg extends PGobject implements Serializable, Cloneable {
/*  29 */   public PGpoint[] point = new PGpoint[2];
/*     */   
/*     */   public PGlseg(double x1, double y1, double x2, double y2) {
/*  39 */     this(new PGpoint(x1, y1), new PGpoint(x2, y2));
/*     */   }
/*     */   
/*     */   public PGlseg(PGpoint p1, PGpoint p2) {
/*  48 */     this();
/*  49 */     this.point[0] = p1;
/*  50 */     this.point[1] = p2;
/*     */   }
/*     */   
/*     */   public PGlseg(String s) throws SQLException {
/*  59 */     this();
/*  60 */     setValue(s);
/*     */   }
/*     */   
/*     */   public PGlseg() {
/*  68 */     setType("lseg");
/*     */   }
/*     */   
/*     */   public void setValue(String s) throws SQLException {
/*  77 */     PGtokenizer t = new PGtokenizer(PGtokenizer.removeBox(s), ',');
/*  78 */     if (t.getSize() != 2)
/*  79 */       throw new PSQLException(GT.tr("Conversion to type {0} failed: {1}.", new Object[] { this.type, s }), PSQLState.DATA_TYPE_MISMATCH); 
/*  81 */     this.point[0] = new PGpoint(t.getToken(0));
/*  82 */     this.point[1] = new PGpoint(t.getToken(1));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  91 */     if (obj instanceof PGlseg) {
/*  93 */       PGlseg p = (PGlseg)obj;
/*  94 */       return ((p.point[0].equals(this.point[0]) && p.point[1].equals(this.point[1])) || (p.point[0].equals(this.point[1]) && p.point[1].equals(this.point[0])));
/*     */     } 
/*  97 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 102 */     return this.point[0].hashCode() ^ this.point[1].hashCode();
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 107 */     PGlseg newPGlseg = (PGlseg)super.clone();
/* 108 */     if (newPGlseg.point != null) {
/* 110 */       newPGlseg.point = (PGpoint[])newPGlseg.point.clone();
/* 111 */       for (int i = 0; i < newPGlseg.point.length; i++) {
/* 112 */         if (newPGlseg.point[i] != null)
/* 113 */           newPGlseg.point[i] = (PGpoint)newPGlseg.point[i].clone(); 
/*     */       } 
/*     */     } 
/* 115 */     return newPGlseg;
/*     */   }
/*     */   
/*     */   public String getValue() {
/* 123 */     return "[" + this.point[0] + "," + this.point[1] + "]";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\geometric\PGlseg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */