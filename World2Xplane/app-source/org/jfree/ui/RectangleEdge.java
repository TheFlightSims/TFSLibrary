/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class RectangleEdge implements Serializable {
/*     */   private static final long serialVersionUID = -7400988293691093548L;
/*     */   
/*  60 */   public static final RectangleEdge TOP = new RectangleEdge("RectangleEdge.TOP");
/*     */   
/*  64 */   public static final RectangleEdge BOTTOM = new RectangleEdge("RectangleEdge.BOTTOM");
/*     */   
/*  68 */   public static final RectangleEdge LEFT = new RectangleEdge("RectangleEdge.LEFT");
/*     */   
/*  72 */   public static final RectangleEdge RIGHT = new RectangleEdge("RectangleEdge.RIGHT");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private RectangleEdge(String name) {
/*  84 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  93 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 106 */     if (this == o)
/* 107 */       return true; 
/* 109 */     if (!(o instanceof RectangleEdge))
/* 110 */       return false; 
/* 113 */     RectangleEdge order = (RectangleEdge)o;
/* 114 */     if (!this.name.equals(order.name))
/* 115 */       return false; 
/* 118 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 128 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */   public static boolean isTopOrBottom(RectangleEdge edge) {
/* 140 */     return (edge == TOP || edge == BOTTOM);
/*     */   }
/*     */   
/*     */   public static boolean isLeftOrRight(RectangleEdge edge) {
/* 152 */     return (edge == LEFT || edge == RIGHT);
/*     */   }
/*     */   
/*     */   public static RectangleEdge opposite(RectangleEdge edge) {
/* 163 */     RectangleEdge result = null;
/* 164 */     if (edge == TOP) {
/* 165 */       result = BOTTOM;
/* 167 */     } else if (edge == BOTTOM) {
/* 168 */       result = TOP;
/* 170 */     } else if (edge == LEFT) {
/* 171 */       result = RIGHT;
/* 173 */     } else if (edge == RIGHT) {
/* 174 */       result = LEFT;
/*     */     } 
/* 176 */     return result;
/*     */   }
/*     */   
/*     */   public static double coordinate(Rectangle2D rectangle, RectangleEdge edge) {
/* 189 */     double result = 0.0D;
/* 190 */     if (edge == TOP) {
/* 191 */       result = rectangle.getMinY();
/* 193 */     } else if (edge == BOTTOM) {
/* 194 */       result = rectangle.getMaxY();
/* 196 */     } else if (edge == LEFT) {
/* 197 */       result = rectangle.getMinX();
/* 199 */     } else if (edge == RIGHT) {
/* 200 */       result = rectangle.getMaxX();
/*     */     } 
/* 202 */     return result;
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 213 */     RectangleEdge result = null;
/* 214 */     if (equals(TOP)) {
/* 215 */       result = TOP;
/* 217 */     } else if (equals(BOTTOM)) {
/* 218 */       result = BOTTOM;
/* 220 */     } else if (equals(LEFT)) {
/* 221 */       result = LEFT;
/* 223 */     } else if (equals(RIGHT)) {
/* 224 */       result = RIGHT;
/*     */     } 
/* 226 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\RectangleEdge.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */