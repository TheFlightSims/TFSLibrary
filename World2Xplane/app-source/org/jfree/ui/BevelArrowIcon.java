/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.UIManager;
/*     */ 
/*     */ public class BevelArrowIcon implements Icon {
/*     */   public static final int UP = 0;
/*     */   
/*     */   public static final int DOWN = 1;
/*     */   
/*     */   private static final int DEFAULT_SIZE = 11;
/*     */   
/*     */   private Color edge1;
/*     */   
/*     */   private Color edge2;
/*     */   
/*     */   private Color fill;
/*     */   
/*     */   private int size;
/*     */   
/*     */   private int direction;
/*     */   
/*     */   public BevelArrowIcon(int direction, boolean isRaisedView, boolean isPressedView) {
/*  97 */     if (isRaisedView) {
/*  98 */       if (isPressedView) {
/*  99 */         init(UIManager.getColor("controlLtHighlight"), UIManager.getColor("controlDkShadow"), UIManager.getColor("controlShadow"), 11, direction);
/*     */       } else {
/* 105 */         init(UIManager.getColor("controlHighlight"), UIManager.getColor("controlShadow"), UIManager.getColor("control"), 11, direction);
/*     */       } 
/* 112 */     } else if (isPressedView) {
/* 113 */       init(UIManager.getColor("controlDkShadow"), UIManager.getColor("controlLtHighlight"), UIManager.getColor("controlShadow"), 11, direction);
/*     */     } else {
/* 119 */       init(UIManager.getColor("controlShadow"), UIManager.getColor("controlHighlight"), UIManager.getColor("control"), 11, direction);
/*     */     } 
/*     */   }
/*     */   
/*     */   public BevelArrowIcon(Color edge1, Color edge2, Color fill, int size, int direction) {
/* 141 */     init(edge1, edge2, fill, size, direction);
/*     */   }
/*     */   
/*     */   public void paintIcon(Component c, Graphics g, int x, int y) {
/* 156 */     switch (this.direction) {
/*     */       case 1:
/* 157 */         drawDownArrow(g, x, y);
/*     */         break;
/*     */       case 0:
/* 158 */         drawUpArrow(g, x, y);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getIconWidth() {
/* 168 */     return this.size;
/*     */   }
/*     */   
/*     */   public int getIconHeight() {
/* 176 */     return this.size;
/*     */   }
/*     */   
/*     */   private void init(Color edge1, Color edge2, Color fill, int size, int direction) {
/* 193 */     this.edge1 = edge1;
/* 194 */     this.edge2 = edge2;
/* 195 */     this.fill = fill;
/* 196 */     this.size = size;
/* 197 */     this.direction = direction;
/*     */   }
/*     */   
/*     */   private void drawDownArrow(Graphics g, int xo, int yo) {
/* 208 */     g.setColor(this.edge1);
/* 209 */     g.drawLine(xo, yo, xo + this.size - 1, yo);
/* 210 */     g.drawLine(xo, yo + 1, xo + this.size - 3, yo + 1);
/* 211 */     g.setColor(this.edge2);
/* 212 */     g.drawLine(xo + this.size - 2, yo + 1, xo + this.size - 1, yo + 1);
/* 213 */     int x = xo + 1;
/* 214 */     int y = yo + 2;
/* 215 */     int dx = this.size - 6;
/* 216 */     while (y + 1 < yo + this.size) {
/* 217 */       g.setColor(this.edge1);
/* 218 */       g.drawLine(x, y, x + 1, y);
/* 219 */       g.drawLine(x, y + 1, x + 1, y + 1);
/* 220 */       if (0 < dx) {
/* 221 */         g.setColor(this.fill);
/* 222 */         g.drawLine(x + 2, y, x + 1 + dx, y);
/* 223 */         g.drawLine(x + 2, y + 1, x + 1 + dx, y + 1);
/*     */       } 
/* 225 */       g.setColor(this.edge2);
/* 226 */       g.drawLine(x + dx + 2, y, x + dx + 3, y);
/* 227 */       g.drawLine(x + dx + 2, y + 1, x + dx + 3, y + 1);
/* 228 */       x++;
/* 229 */       y += 2;
/* 230 */       dx -= 2;
/*     */     } 
/* 232 */     g.setColor(this.edge1);
/* 233 */     g.drawLine(xo + this.size / 2, yo + this.size - 1, xo + this.size / 2, yo + this.size - 1);
/*     */   }
/*     */   
/*     */   private void drawUpArrow(Graphics g, int xo, int yo) {
/* 246 */     g.setColor(this.edge1);
/* 247 */     int x = xo + this.size / 2;
/* 248 */     g.drawLine(x, yo, x, yo);
/* 249 */     x--;
/* 250 */     int y = yo + 1;
/* 251 */     int dx = 0;
/* 252 */     while (y + 3 < yo + this.size) {
/* 253 */       g.setColor(this.edge1);
/* 254 */       g.drawLine(x, y, x + 1, y);
/* 255 */       g.drawLine(x, y + 1, x + 1, y + 1);
/* 256 */       if (0 < dx) {
/* 257 */         g.setColor(this.fill);
/* 258 */         g.drawLine(x + 2, y, x + 1 + dx, y);
/* 259 */         g.drawLine(x + 2, y + 1, x + 1 + dx, y + 1);
/*     */       } 
/* 261 */       g.setColor(this.edge2);
/* 262 */       g.drawLine(x + dx + 2, y, x + dx + 3, y);
/* 263 */       g.drawLine(x + dx + 2, y + 1, x + dx + 3, y + 1);
/* 264 */       x--;
/* 265 */       y += 2;
/* 266 */       dx += 2;
/*     */     } 
/* 268 */     g.setColor(this.edge1);
/* 269 */     g.drawLine(xo, yo + this.size - 3, xo + 1, yo + this.size - 3);
/* 270 */     g.setColor(this.edge2);
/* 271 */     g.drawLine(xo + 2, yo + this.size - 2, xo + this.size - 1, yo + this.size - 2);
/* 272 */     g.drawLine(xo, yo + this.size - 1, xo + this.size, yo + this.size - 1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\BevelArrowIcon.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */