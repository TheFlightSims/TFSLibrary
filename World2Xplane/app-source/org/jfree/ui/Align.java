/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ 
/*     */ public final class Align {
/*     */   public static final int CENTER = 0;
/*     */   
/*     */   public static final int TOP = 1;
/*     */   
/*     */   public static final int BOTTOM = 2;
/*     */   
/*     */   public static final int LEFT = 4;
/*     */   
/*     */   public static final int RIGHT = 8;
/*     */   
/*     */   public static final int TOP_LEFT = 5;
/*     */   
/*     */   public static final int TOP_RIGHT = 9;
/*     */   
/*     */   public static final int BOTTOM_LEFT = 6;
/*     */   
/*     */   public static final int BOTTOM_RIGHT = 10;
/*     */   
/*     */   public static final int FIT_HORIZONTAL = 12;
/*     */   
/*     */   public static final int FIT_VERTICAL = 3;
/*     */   
/*     */   public static final int FIT = 15;
/*     */   
/*     */   public static final int NORTH = 1;
/*     */   
/*     */   public static final int SOUTH = 2;
/*     */   
/*     */   public static final int WEST = 4;
/*     */   
/*     */   public static final int EAST = 8;
/*     */   
/*     */   public static final int NORTH_WEST = 5;
/*     */   
/*     */   public static final int NORTH_EAST = 9;
/*     */   
/*     */   public static final int SOUTH_WEST = 6;
/*     */   
/*     */   public static final int SOUTH_EAST = 10;
/*     */   
/*     */   public static void align(Rectangle2D rect, Rectangle2D frame, int align) {
/* 131 */     double x = frame.getCenterX() - rect.getWidth() / 2.0D;
/* 132 */     double y = frame.getCenterY() - rect.getHeight() / 2.0D;
/* 133 */     double w = rect.getWidth();
/* 134 */     double h = rect.getHeight();
/* 136 */     if ((align & 0x3) == 3)
/* 137 */       h = frame.getHeight(); 
/* 140 */     if ((align & 0xC) == 12)
/* 141 */       w = frame.getWidth(); 
/* 144 */     if ((align & 0x1) == 1)
/* 145 */       y = frame.getMinY(); 
/* 148 */     if ((align & 0x2) == 2)
/* 149 */       y = frame.getMaxY() - h; 
/* 152 */     if ((align & 0x4) == 4)
/* 153 */       x = frame.getX(); 
/* 156 */     if ((align & 0x8) == 8)
/* 157 */       x = frame.getMaxX() - w; 
/* 160 */     rect.setRect(x, y, w, h);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\Align.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */