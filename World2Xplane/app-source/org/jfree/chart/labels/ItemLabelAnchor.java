/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class ItemLabelAnchor implements Serializable {
/*     */   private static final long serialVersionUID = -1233101616128695658L;
/*     */   
/*  62 */   public static final ItemLabelAnchor CENTER = new ItemLabelAnchor("ItemLabelAnchor.CENTER");
/*     */   
/*  66 */   public static final ItemLabelAnchor INSIDE1 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE1");
/*     */   
/*  70 */   public static final ItemLabelAnchor INSIDE2 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE2");
/*     */   
/*  74 */   public static final ItemLabelAnchor INSIDE3 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE3");
/*     */   
/*  78 */   public static final ItemLabelAnchor INSIDE4 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE4");
/*     */   
/*  82 */   public static final ItemLabelAnchor INSIDE5 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE5");
/*     */   
/*  86 */   public static final ItemLabelAnchor INSIDE6 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE6");
/*     */   
/*  90 */   public static final ItemLabelAnchor INSIDE7 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE7");
/*     */   
/*  94 */   public static final ItemLabelAnchor INSIDE8 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE8");
/*     */   
/*  98 */   public static final ItemLabelAnchor INSIDE9 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE9");
/*     */   
/* 102 */   public static final ItemLabelAnchor INSIDE10 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE10");
/*     */   
/* 106 */   public static final ItemLabelAnchor INSIDE11 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE11");
/*     */   
/* 110 */   public static final ItemLabelAnchor INSIDE12 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE12");
/*     */   
/* 114 */   public static final ItemLabelAnchor OUTSIDE1 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE1");
/*     */   
/* 118 */   public static final ItemLabelAnchor OUTSIDE2 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE2");
/*     */   
/* 122 */   public static final ItemLabelAnchor OUTSIDE3 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE3");
/*     */   
/* 126 */   public static final ItemLabelAnchor OUTSIDE4 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE4");
/*     */   
/* 130 */   public static final ItemLabelAnchor OUTSIDE5 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE5");
/*     */   
/* 134 */   public static final ItemLabelAnchor OUTSIDE6 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE6");
/*     */   
/* 138 */   public static final ItemLabelAnchor OUTSIDE7 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE7");
/*     */   
/* 142 */   public static final ItemLabelAnchor OUTSIDE8 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE8");
/*     */   
/* 146 */   public static final ItemLabelAnchor OUTSIDE9 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE9");
/*     */   
/* 150 */   public static final ItemLabelAnchor OUTSIDE10 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE10");
/*     */   
/* 154 */   public static final ItemLabelAnchor OUTSIDE11 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE11");
/*     */   
/* 158 */   public static final ItemLabelAnchor OUTSIDE12 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE12");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private ItemLabelAnchor(String name) {
/* 170 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 179 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 192 */     if (this == o)
/* 193 */       return true; 
/* 195 */     if (!(o instanceof ItemLabelAnchor))
/* 196 */       return false; 
/* 199 */     ItemLabelAnchor order = (ItemLabelAnchor)o;
/* 200 */     if (!this.name.equals(order.toString()))
/* 201 */       return false; 
/* 204 */     return true;
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 216 */     ItemLabelAnchor result = null;
/* 217 */     if (equals(CENTER)) {
/* 218 */       result = CENTER;
/* 220 */     } else if (equals(INSIDE1)) {
/* 221 */       result = INSIDE1;
/* 223 */     } else if (equals(INSIDE2)) {
/* 224 */       result = INSIDE2;
/* 226 */     } else if (equals(INSIDE3)) {
/* 227 */       result = INSIDE3;
/* 229 */     } else if (equals(INSIDE4)) {
/* 230 */       result = INSIDE4;
/* 232 */     } else if (equals(INSIDE5)) {
/* 233 */       result = INSIDE5;
/* 235 */     } else if (equals(INSIDE6)) {
/* 236 */       result = INSIDE6;
/* 238 */     } else if (equals(INSIDE7)) {
/* 239 */       result = INSIDE7;
/* 241 */     } else if (equals(INSIDE8)) {
/* 242 */       result = INSIDE8;
/* 244 */     } else if (equals(INSIDE9)) {
/* 245 */       result = INSIDE9;
/* 247 */     } else if (equals(INSIDE10)) {
/* 248 */       result = INSIDE10;
/* 250 */     } else if (equals(INSIDE11)) {
/* 251 */       result = INSIDE11;
/* 253 */     } else if (equals(INSIDE12)) {
/* 254 */       result = INSIDE12;
/* 256 */     } else if (equals(OUTSIDE1)) {
/* 257 */       result = OUTSIDE1;
/* 259 */     } else if (equals(OUTSIDE2)) {
/* 260 */       result = OUTSIDE2;
/* 262 */     } else if (equals(OUTSIDE3)) {
/* 263 */       result = OUTSIDE3;
/* 265 */     } else if (equals(OUTSIDE4)) {
/* 266 */       result = OUTSIDE4;
/* 268 */     } else if (equals(OUTSIDE5)) {
/* 269 */       result = OUTSIDE5;
/* 271 */     } else if (equals(OUTSIDE6)) {
/* 272 */       result = OUTSIDE6;
/* 274 */     } else if (equals(OUTSIDE7)) {
/* 275 */       result = OUTSIDE7;
/* 277 */     } else if (equals(OUTSIDE8)) {
/* 278 */       result = OUTSIDE8;
/* 280 */     } else if (equals(OUTSIDE9)) {
/* 281 */       result = OUTSIDE9;
/* 283 */     } else if (equals(OUTSIDE10)) {
/* 284 */       result = OUTSIDE10;
/* 286 */     } else if (equals(OUTSIDE11)) {
/* 287 */       result = OUTSIDE11;
/* 289 */     } else if (equals(OUTSIDE12)) {
/* 290 */       result = OUTSIDE12;
/*     */     } 
/* 292 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\ItemLabelAnchor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */