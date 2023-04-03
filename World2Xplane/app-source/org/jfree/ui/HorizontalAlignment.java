/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class HorizontalAlignment implements Serializable {
/*     */   private static final long serialVersionUID = -8249740987565309567L;
/*     */   
/*  60 */   public static final HorizontalAlignment LEFT = new HorizontalAlignment("HorizontalAlignment.LEFT");
/*     */   
/*  64 */   public static final HorizontalAlignment RIGHT = new HorizontalAlignment("HorizontalAlignment.RIGHT");
/*     */   
/*  68 */   public static final HorizontalAlignment CENTER = new HorizontalAlignment("HorizontalAlignment.CENTER");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private HorizontalAlignment(String name) {
/*  80 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  89 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 101 */     if (this == obj)
/* 102 */       return true; 
/* 104 */     if (!(obj instanceof HorizontalAlignment))
/* 105 */       return false; 
/* 107 */     HorizontalAlignment that = (HorizontalAlignment)obj;
/* 108 */     if (!this.name.equals(that.name))
/* 109 */       return false; 
/* 111 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 120 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 131 */     HorizontalAlignment result = null;
/* 132 */     if (equals(LEFT)) {
/* 133 */       result = LEFT;
/* 135 */     } else if (equals(RIGHT)) {
/* 136 */       result = RIGHT;
/* 138 */     } else if (equals(CENTER)) {
/* 139 */       result = CENTER;
/*     */     } 
/* 141 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\HorizontalAlignment.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */