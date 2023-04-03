/*     */ package org.jfree.text;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class TextBlockAnchor implements Serializable {
/*     */   private static final long serialVersionUID = -3045058380983401544L;
/*     */   
/*  60 */   public static final TextBlockAnchor TOP_LEFT = new TextBlockAnchor("TextBlockAnchor.TOP_LEFT");
/*     */   
/*  64 */   public static final TextBlockAnchor TOP_CENTER = new TextBlockAnchor("TextBlockAnchor.TOP_CENTER");
/*     */   
/*  69 */   public static final TextBlockAnchor TOP_RIGHT = new TextBlockAnchor("TextBlockAnchor.TOP_RIGHT");
/*     */   
/*  74 */   public static final TextBlockAnchor CENTER_LEFT = new TextBlockAnchor("TextBlockAnchor.CENTER_LEFT");
/*     */   
/*  79 */   public static final TextBlockAnchor CENTER = new TextBlockAnchor("TextBlockAnchor.CENTER");
/*     */   
/*  83 */   public static final TextBlockAnchor CENTER_RIGHT = new TextBlockAnchor("TextBlockAnchor.CENTER_RIGHT");
/*     */   
/*  88 */   public static final TextBlockAnchor BOTTOM_LEFT = new TextBlockAnchor("TextBlockAnchor.BOTTOM_LEFT");
/*     */   
/*  92 */   public static final TextBlockAnchor BOTTOM_CENTER = new TextBlockAnchor("TextBlockAnchor.BOTTOM_CENTER");
/*     */   
/*  96 */   public static final TextBlockAnchor BOTTOM_RIGHT = new TextBlockAnchor("TextBlockAnchor.BOTTOM_RIGHT");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private TextBlockAnchor(String name) {
/* 108 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 117 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 130 */     if (this == o)
/* 131 */       return true; 
/* 133 */     if (!(o instanceof TextBlockAnchor))
/* 134 */       return false; 
/* 137 */     TextBlockAnchor other = (TextBlockAnchor)o;
/* 138 */     if (!this.name.equals(other.name))
/* 139 */       return false; 
/* 142 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 151 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 162 */     if (equals(TOP_CENTER))
/* 163 */       return TOP_CENTER; 
/* 165 */     if (equals(TOP_LEFT))
/* 166 */       return TOP_LEFT; 
/* 168 */     if (equals(TOP_RIGHT))
/* 169 */       return TOP_RIGHT; 
/* 171 */     if (equals(CENTER))
/* 172 */       return CENTER; 
/* 174 */     if (equals(CENTER_LEFT))
/* 175 */       return CENTER_LEFT; 
/* 177 */     if (equals(CENTER_RIGHT))
/* 178 */       return CENTER_RIGHT; 
/* 180 */     if (equals(BOTTOM_CENTER))
/* 181 */       return BOTTOM_CENTER; 
/* 183 */     if (equals(BOTTOM_LEFT))
/* 184 */       return BOTTOM_LEFT; 
/* 186 */     if (equals(BOTTOM_RIGHT))
/* 187 */       return BOTTOM_RIGHT; 
/* 189 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\text\TextBlockAnchor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */