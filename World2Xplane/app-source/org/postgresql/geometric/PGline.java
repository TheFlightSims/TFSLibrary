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
/*     */ public class PGline extends PGobject implements Serializable, Cloneable {
/*  32 */   public PGpoint[] point = new PGpoint[2];
/*     */   
/*     */   public PGline(double x1, double y1, double x2, double y2) {
/*  42 */     this(new PGpoint(x1, y1), new PGpoint(x2, y2));
/*     */   }
/*     */   
/*     */   public PGline(PGpoint p1, PGpoint p2) {
/*  51 */     this();
/*  52 */     this.point[0] = p1;
/*  53 */     this.point[1] = p2;
/*     */   }
/*     */   
/*     */   public PGline(String s) throws SQLException {
/*  62 */     this();
/*  63 */     setValue(s);
/*     */   }
/*     */   
/*     */   public PGline() {
/*  71 */     setType("line");
/*     */   }
/*     */   
/*     */   public void setValue(String s) throws SQLException {
/*  80 */     PGtokenizer t = new PGtokenizer(PGtokenizer.removeBox(s), ',');
/*  81 */     if (t.getSize() != 2)
/*  82 */       throw new PSQLException(GT.tr("Conversion to type {0} failed: {1}.", new Object[] { this.type, s }), PSQLState.DATA_TYPE_MISMATCH); 
/*  84 */     this.point[0] = new PGpoint(t.getToken(0));
/*  85 */     this.point[1] = new PGpoint(t.getToken(1));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  94 */     if (obj instanceof PGline) {
/*  96 */       PGline p = (PGline)obj;
/*  97 */       return ((p.point[0].equals(this.point[0]) && p.point[1].equals(this.point[1])) || (p.point[0].equals(this.point[1]) && p.point[1].equals(this.point[0])));
/*     */     } 
/* 100 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 104 */     return this.point[0].hashCode() ^ this.point[1].hashCode();
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 109 */     PGline newPGline = (PGline)super.clone();
/* 110 */     if (newPGline.point != null) {
/* 112 */       newPGline.point = (PGpoint[])newPGline.point.clone();
/* 113 */       for (int i = 0; i < newPGline.point.length; i++) {
/* 114 */         if (newPGline.point[i] != null)
/* 115 */           newPGline.point[i] = (PGpoint)newPGline.point[i].clone(); 
/*     */       } 
/*     */     } 
/* 117 */     return newPGline;
/*     */   }
/*     */   
/*     */   public String getValue() {
/* 125 */     return "[" + this.point[0] + "," + this.point[1] + "]";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\geometric\PGline.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */