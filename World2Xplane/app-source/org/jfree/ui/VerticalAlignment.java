/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class VerticalAlignment implements Serializable {
/*     */   private static final long serialVersionUID = 7272397034325429853L;
/*     */   
/*  60 */   public static final VerticalAlignment TOP = new VerticalAlignment("VerticalAlignment.TOP");
/*     */   
/*  64 */   public static final VerticalAlignment BOTTOM = new VerticalAlignment("VerticalAlignment.BOTTOM");
/*     */   
/*  68 */   public static final VerticalAlignment CENTER = new VerticalAlignment("VerticalAlignment.CENTER");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private VerticalAlignment(String name) {
/*  80 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  89 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 102 */     if (this == o)
/* 103 */       return true; 
/* 105 */     if (!(o instanceof VerticalAlignment))
/* 106 */       return false; 
/* 109 */     VerticalAlignment alignment = (VerticalAlignment)o;
/* 110 */     if (!this.name.equals(alignment.name))
/* 111 */       return false; 
/* 114 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 123 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 134 */     if (equals(TOP))
/* 135 */       return TOP; 
/* 137 */     if (equals(BOTTOM))
/* 138 */       return BOTTOM; 
/* 140 */     if (equals(CENTER))
/* 141 */       return CENTER; 
/* 144 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\VerticalAlignment.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */