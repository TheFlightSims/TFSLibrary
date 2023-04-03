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
/*     */ public class PGpath extends PGobject implements Serializable, Cloneable {
/*     */   public boolean open;
/*     */   
/*     */   public PGpoint[] points;
/*     */   
/*     */   public PGpath(PGpoint[] points, boolean open) {
/*  42 */     this();
/*  43 */     this.points = points;
/*  44 */     this.open = open;
/*     */   }
/*     */   
/*     */   public PGpath() {
/*  52 */     setType("path");
/*     */   }
/*     */   
/*     */   public PGpath(String s) throws SQLException {
/*  61 */     this();
/*  62 */     setValue(s);
/*     */   }
/*     */   
/*     */   public void setValue(String s) throws SQLException {
/*  72 */     if (s.startsWith("[") && s.endsWith("]")) {
/*  74 */       this.open = true;
/*  75 */       s = PGtokenizer.removeBox(s);
/*  77 */     } else if (s.startsWith("(") && s.endsWith(")")) {
/*  79 */       this.open = false;
/*  80 */       s = PGtokenizer.removePara(s);
/*     */     } else {
/*  83 */       throw new PSQLException(GT.tr("Cannot tell if path is open or closed: {0}.", s), PSQLState.DATA_TYPE_MISMATCH);
/*     */     } 
/*  85 */     PGtokenizer t = new PGtokenizer(s, ',');
/*  86 */     int npoints = t.getSize();
/*  87 */     this.points = new PGpoint[npoints];
/*  88 */     for (int p = 0; p < npoints; p++)
/*  89 */       this.points[p] = new PGpoint(t.getToken(p)); 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  98 */     if (obj instanceof PGpath) {
/* 100 */       PGpath p = (PGpath)obj;
/* 102 */       if (p.points.length != this.points.length)
/* 103 */         return false; 
/* 105 */       if (p.open != this.open)
/* 106 */         return false; 
/* 108 */       for (int i = 0; i < this.points.length; i++) {
/* 109 */         if (!this.points[i].equals(p.points[i]))
/* 110 */           return false; 
/*     */       } 
/* 112 */       return true;
/*     */     } 
/* 114 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 119 */     int hash = 0;
/* 120 */     for (int i = 0; i < this.points.length && i < 5; i++)
/* 122 */       hash ^= this.points[i].hashCode(); 
/* 124 */     return hash;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 129 */     PGpath newPGpath = (PGpath)super.clone();
/* 130 */     if (newPGpath.points != null) {
/* 132 */       newPGpath.points = (PGpoint[])newPGpath.points.clone();
/* 133 */       for (int i = 0; i < newPGpath.points.length; i++)
/* 134 */         newPGpath.points[i] = (PGpoint)newPGpath.points[i].clone(); 
/*     */     } 
/* 136 */     return newPGpath;
/*     */   }
/*     */   
/*     */   public String getValue() {
/* 144 */     StringBuffer b = new StringBuffer(this.open ? "[" : "(");
/* 146 */     for (int p = 0; p < this.points.length; p++) {
/* 148 */       if (p > 0)
/* 149 */         b.append(","); 
/* 150 */       b.append(this.points[p].toString());
/*     */     } 
/* 152 */     b.append(this.open ? "]" : ")");
/* 154 */     return b.toString();
/*     */   }
/*     */   
/*     */   public boolean isOpen() {
/* 159 */     return this.open;
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 164 */     return !this.open;
/*     */   }
/*     */   
/*     */   public void closePath() {
/* 169 */     this.open = false;
/*     */   }
/*     */   
/*     */   public void openPath() {
/* 174 */     this.open = true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\geometric\PGpath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */