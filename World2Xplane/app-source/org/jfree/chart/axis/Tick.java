/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public abstract class Tick implements Serializable, Cloneable {
/*     */   private static final long serialVersionUID = 6668230383875149773L;
/*     */   
/*     */   private String text;
/*     */   
/*     */   private TextAnchor textAnchor;
/*     */   
/*     */   private TextAnchor rotationAnchor;
/*     */   
/*     */   private double angle;
/*     */   
/*     */   public Tick(String text, TextAnchor textAnchor, TextAnchor rotationAnchor, double angle) {
/*  86 */     if (textAnchor == null)
/*  87 */       throw new IllegalArgumentException("Null 'textAnchor' argument."); 
/*  89 */     if (rotationAnchor == null)
/*  90 */       throw new IllegalArgumentException("Null 'rotationAnchor' argument."); 
/*  94 */     this.text = text;
/*  95 */     this.textAnchor = textAnchor;
/*  96 */     this.rotationAnchor = rotationAnchor;
/*  97 */     this.angle = angle;
/*     */   }
/*     */   
/*     */   public String getText() {
/* 106 */     return this.text;
/*     */   }
/*     */   
/*     */   public TextAnchor getTextAnchor() {
/* 115 */     return this.textAnchor;
/*     */   }
/*     */   
/*     */   public TextAnchor getRotationAnchor() {
/* 125 */     return this.rotationAnchor;
/*     */   }
/*     */   
/*     */   public double getAngle() {
/* 134 */     return this.angle;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 145 */     if (this == obj)
/* 146 */       return true; 
/* 148 */     if (obj instanceof Tick) {
/* 149 */       Tick t = (Tick)obj;
/* 150 */       if (!ObjectUtilities.equal(this.text, t.text))
/* 151 */         return false; 
/* 153 */       if (!ObjectUtilities.equal(this.textAnchor, t.textAnchor))
/* 154 */         return false; 
/* 156 */       if (!ObjectUtilities.equal(this.rotationAnchor, t.rotationAnchor))
/* 157 */         return false; 
/* 159 */       if (this.angle != t.angle)
/* 160 */         return false; 
/* 162 */       return true;
/*     */     } 
/* 164 */     return false;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 175 */     Tick clone = (Tick)super.clone();
/* 176 */     return clone;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 185 */     return this.text;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\Tick.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */