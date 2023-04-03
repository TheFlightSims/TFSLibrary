/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class RectangleAnchor implements Serializable {
/*     */   private static final long serialVersionUID = -2457494205644416327L;
/*     */   
/*  64 */   public static final RectangleAnchor CENTER = new RectangleAnchor("RectangleAnchor.CENTER");
/*     */   
/*  68 */   public static final RectangleAnchor TOP = new RectangleAnchor("RectangleAnchor.TOP");
/*     */   
/*  72 */   public static final RectangleAnchor TOP_LEFT = new RectangleAnchor("RectangleAnchor.TOP_LEFT");
/*     */   
/*  76 */   public static final RectangleAnchor TOP_RIGHT = new RectangleAnchor("RectangleAnchor.TOP_RIGHT");
/*     */   
/*  80 */   public static final RectangleAnchor BOTTOM = new RectangleAnchor("RectangleAnchor.BOTTOM");
/*     */   
/*  84 */   public static final RectangleAnchor BOTTOM_LEFT = new RectangleAnchor("RectangleAnchor.BOTTOM_LEFT");
/*     */   
/*  88 */   public static final RectangleAnchor BOTTOM_RIGHT = new RectangleAnchor("RectangleAnchor.BOTTOM_RIGHT");
/*     */   
/*  92 */   public static final RectangleAnchor LEFT = new RectangleAnchor("RectangleAnchor.LEFT");
/*     */   
/*  96 */   public static final RectangleAnchor RIGHT = new RectangleAnchor("RectangleAnchor.RIGHT");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private RectangleAnchor(String name) {
/* 108 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 117 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 130 */     if (this == obj)
/* 131 */       return true; 
/* 133 */     if (!(obj instanceof RectangleAnchor))
/* 134 */       return false; 
/* 137 */     RectangleAnchor order = (RectangleAnchor)obj;
/* 138 */     if (!this.name.equals(order.name))
/* 139 */       return false; 
/* 142 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 151 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */   public static Point2D coordinates(Rectangle2D rectangle, RectangleAnchor anchor) {
/* 164 */     Point2D result = new Point2D.Double();
/* 165 */     if (anchor == CENTER) {
/* 166 */       result.setLocation(rectangle.getCenterX(), rectangle.getCenterY());
/* 168 */     } else if (anchor == TOP) {
/* 169 */       result.setLocation(rectangle.getCenterX(), rectangle.getMinY());
/* 171 */     } else if (anchor == BOTTOM) {
/* 172 */       result.setLocation(rectangle.getCenterX(), rectangle.getMaxY());
/* 174 */     } else if (anchor == LEFT) {
/* 175 */       result.setLocation(rectangle.getMinX(), rectangle.getCenterY());
/* 177 */     } else if (anchor == RIGHT) {
/* 178 */       result.setLocation(rectangle.getMaxX(), rectangle.getCenterY());
/* 180 */     } else if (anchor == TOP_LEFT) {
/* 181 */       result.setLocation(rectangle.getMinX(), rectangle.getMinY());
/* 183 */     } else if (anchor == TOP_RIGHT) {
/* 184 */       result.setLocation(rectangle.getMaxX(), rectangle.getMinY());
/* 186 */     } else if (anchor == BOTTOM_LEFT) {
/* 187 */       result.setLocation(rectangle.getMinX(), rectangle.getMaxY());
/* 189 */     } else if (anchor == BOTTOM_RIGHT) {
/* 190 */       result.setLocation(rectangle.getMaxX(), rectangle.getMaxY());
/*     */     } 
/* 192 */     return result;
/*     */   }
/*     */   
/*     */   public static Rectangle2D createRectangle(Size2D dimensions, double anchorX, double anchorY, RectangleAnchor anchor) {
/* 210 */     Rectangle2D result = null;
/* 211 */     double w = dimensions.getWidth();
/* 212 */     double h = dimensions.getHeight();
/* 213 */     if (anchor == CENTER) {
/* 214 */       result = new Rectangle2D.Double(anchorX - w / 2.0D, anchorY - h / 2.0D, w, h);
/* 218 */     } else if (anchor == TOP) {
/* 219 */       result = new Rectangle2D.Double(anchorX - w / 2.0D, anchorY - h / 2.0D, w, h);
/* 223 */     } else if (anchor == BOTTOM) {
/* 224 */       result = new Rectangle2D.Double(anchorX - w / 2.0D, anchorY - h / 2.0D, w, h);
/* 228 */     } else if (anchor == LEFT) {
/* 229 */       result = new Rectangle2D.Double(anchorX, anchorY - h / 2.0D, w, h);
/* 233 */     } else if (anchor == RIGHT) {
/* 234 */       result = new Rectangle2D.Double(anchorX - w, anchorY - h / 2.0D, w, h);
/* 238 */     } else if (anchor == TOP_LEFT) {
/* 239 */       result = new Rectangle2D.Double(anchorX - w / 2.0D, anchorY - h / 2.0D, w, h);
/* 243 */     } else if (anchor == TOP_RIGHT) {
/* 244 */       result = new Rectangle2D.Double(anchorX - w / 2.0D, anchorY - h / 2.0D, w, h);
/* 248 */     } else if (anchor == BOTTOM_LEFT) {
/* 249 */       result = new Rectangle2D.Double(anchorX - w / 2.0D, anchorY - h / 2.0D, w, h);
/* 253 */     } else if (anchor == BOTTOM_RIGHT) {
/* 254 */       result = new Rectangle2D.Double(anchorX - w / 2.0D, anchorY - h / 2.0D, w, h);
/*     */     } 
/* 258 */     return result;
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 269 */     RectangleAnchor result = null;
/* 270 */     if (equals(CENTER)) {
/* 271 */       result = CENTER;
/* 273 */     } else if (equals(TOP)) {
/* 274 */       result = TOP;
/* 276 */     } else if (equals(BOTTOM)) {
/* 277 */       result = BOTTOM;
/* 279 */     } else if (equals(LEFT)) {
/* 280 */       result = LEFT;
/* 282 */     } else if (equals(RIGHT)) {
/* 283 */       result = RIGHT;
/* 285 */     } else if (equals(TOP_LEFT)) {
/* 286 */       result = TOP_LEFT;
/* 288 */     } else if (equals(TOP_RIGHT)) {
/* 289 */       result = TOP_RIGHT;
/* 291 */     } else if (equals(BOTTOM_LEFT)) {
/* 292 */       result = BOTTOM_LEFT;
/* 294 */     } else if (equals(BOTTOM_RIGHT)) {
/* 295 */       result = BOTTOM_RIGHT;
/*     */     } 
/* 297 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\RectangleAnchor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */