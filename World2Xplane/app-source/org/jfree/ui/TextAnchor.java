/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class TextAnchor implements Serializable {
/*     */   private static final long serialVersionUID = 8219158940496719660L;
/*     */   
/*  61 */   public static final TextAnchor TOP_LEFT = new TextAnchor("TextAnchor.TOP_LEFT");
/*     */   
/*  65 */   public static final TextAnchor TOP_CENTER = new TextAnchor("TextAnchor.TOP_CENTER");
/*     */   
/*  69 */   public static final TextAnchor TOP_RIGHT = new TextAnchor("TextAnchor.TOP_RIGHT");
/*     */   
/*  73 */   public static final TextAnchor HALF_ASCENT_LEFT = new TextAnchor("TextAnchor.HALF_ASCENT_LEFT");
/*     */   
/*  77 */   public static final TextAnchor HALF_ASCENT_CENTER = new TextAnchor("TextAnchor.HALF_ASCENT_CENTER");
/*     */   
/*  81 */   public static final TextAnchor HALF_ASCENT_RIGHT = new TextAnchor("TextAnchor.HALF_ASCENT_RIGHT");
/*     */   
/*  85 */   public static final TextAnchor CENTER_LEFT = new TextAnchor("TextAnchor.CENTER_LEFT");
/*     */   
/*  89 */   public static final TextAnchor CENTER = new TextAnchor("TextAnchor.CENTER");
/*     */   
/*  92 */   public static final TextAnchor CENTER_RIGHT = new TextAnchor("TextAnchor.CENTER_RIGHT");
/*     */   
/*  96 */   public static final TextAnchor BASELINE_LEFT = new TextAnchor("TextAnchor.BASELINE_LEFT");
/*     */   
/* 100 */   public static final TextAnchor BASELINE_CENTER = new TextAnchor("TextAnchor.BASELINE_CENTER");
/*     */   
/* 104 */   public static final TextAnchor BASELINE_RIGHT = new TextAnchor("TextAnchor.BASELINE_RIGHT");
/*     */   
/* 108 */   public static final TextAnchor BOTTOM_LEFT = new TextAnchor("TextAnchor.BOTTOM_LEFT");
/*     */   
/* 112 */   public static final TextAnchor BOTTOM_CENTER = new TextAnchor("TextAnchor.BOTTOM_CENTER");
/*     */   
/* 116 */   public static final TextAnchor BOTTOM_RIGHT = new TextAnchor("TextAnchor.BOTTOM_RIGHT");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private TextAnchor(String name) {
/* 128 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 137 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 150 */     if (this == o)
/* 151 */       return true; 
/* 153 */     if (!(o instanceof TextAnchor))
/* 154 */       return false; 
/* 157 */     TextAnchor order = (TextAnchor)o;
/* 158 */     if (!this.name.equals(order.name))
/* 159 */       return false; 
/* 162 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 171 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 182 */     TextAnchor result = null;
/* 183 */     if (equals(TOP_LEFT)) {
/* 184 */       result = TOP_LEFT;
/* 186 */     } else if (equals(TOP_CENTER)) {
/* 187 */       result = TOP_CENTER;
/* 189 */     } else if (equals(TOP_RIGHT)) {
/* 190 */       result = TOP_RIGHT;
/* 192 */     } else if (equals(BOTTOM_LEFT)) {
/* 193 */       result = BOTTOM_LEFT;
/* 195 */     } else if (equals(BOTTOM_CENTER)) {
/* 196 */       result = BOTTOM_CENTER;
/* 198 */     } else if (equals(BOTTOM_RIGHT)) {
/* 199 */       result = BOTTOM_RIGHT;
/* 201 */     } else if (equals(BASELINE_LEFT)) {
/* 202 */       result = BASELINE_LEFT;
/* 204 */     } else if (equals(BASELINE_CENTER)) {
/* 205 */       result = BASELINE_CENTER;
/* 207 */     } else if (equals(BASELINE_RIGHT)) {
/* 208 */       result = BASELINE_RIGHT;
/* 210 */     } else if (equals(CENTER_LEFT)) {
/* 211 */       result = CENTER_LEFT;
/* 213 */     } else if (equals(CENTER)) {
/* 214 */       result = CENTER;
/* 216 */     } else if (equals(CENTER_RIGHT)) {
/* 217 */       result = CENTER_RIGHT;
/* 219 */     } else if (equals(HALF_ASCENT_LEFT)) {
/* 220 */       result = HALF_ASCENT_LEFT;
/* 222 */     } else if (equals(HALF_ASCENT_CENTER)) {
/* 223 */       result = HALF_ASCENT_CENTER;
/* 225 */     } else if (equals(HALF_ASCENT_RIGHT)) {
/* 226 */       result = HALF_ASCENT_RIGHT;
/*     */     } 
/* 228 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\TextAnchor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */