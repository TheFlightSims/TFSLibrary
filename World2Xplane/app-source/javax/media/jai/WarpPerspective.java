/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public final class WarpPerspective extends Warp {
/*     */   private PerspectiveTransform transform;
/*     */   
/*     */   private PerspectiveTransform invTransform;
/*     */   
/*     */   public WarpPerspective(PerspectiveTransform transform) {
/*  40 */     if (transform == null)
/*  41 */       throw new IllegalArgumentException(JaiI18N.getString("WarpPerspective0")); 
/*  44 */     this.transform = transform;
/*     */     try {
/*  49 */       this.invTransform = transform.createInverse();
/*  50 */     } catch (NoninvertibleTransformException e) {
/*  51 */       this.invTransform = null;
/*  52 */     } catch (CloneNotSupportedException e) {
/*  53 */       this.invTransform = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public PerspectiveTransform getTransform() {
/*  65 */     return (PerspectiveTransform)this.transform.clone();
/*     */   }
/*     */   
/*     */   public float[] warpSparseRect(int x, int y, int width, int height, int periodX, int periodY, float[] destRect) {
/*  97 */     if (destRect == null)
/*  98 */       destRect = new float[2 * (width + periodX - 1) / periodX * (height + periodY - 1) / periodY]; 
/* 102 */     double[][] matrix = new double[3][3];
/* 103 */     matrix = this.transform.getMatrix(matrix);
/* 104 */     float m00 = (float)matrix[0][0];
/* 105 */     float m01 = (float)matrix[0][1];
/* 106 */     float m02 = (float)matrix[0][2];
/* 107 */     float m10 = (float)matrix[1][0];
/* 108 */     float m11 = (float)matrix[1][1];
/* 109 */     float m12 = (float)matrix[1][2];
/* 110 */     float m20 = (float)matrix[2][0];
/* 111 */     float m21 = (float)matrix[2][1];
/* 112 */     float m22 = (float)matrix[2][2];
/* 119 */     float dx = m00 * periodX;
/* 120 */     float dy = m10 * periodX;
/* 121 */     float dw = m20 * periodX;
/* 123 */     float sx = x + 0.5F;
/* 125 */     width += x;
/* 126 */     height += y;
/* 127 */     int index = 0;
/*     */     int j;
/* 129 */     for (j = y; j < height; j += periodY) {
/* 130 */       float sy = j + 0.5F;
/* 132 */       float wx = m00 * sx + m01 * sy + m02;
/* 133 */       float wy = m10 * sx + m11 * sy + m12;
/* 134 */       float w = m20 * sx + m21 * sy + m22;
/*     */       int i;
/* 136 */       for (i = x; i < width; i += periodX) {
/*     */         float tx, ty;
/*     */         try {
/* 139 */           tx = wx / w;
/* 140 */           ty = wy / w;
/* 141 */         } catch (ArithmeticException e) {
/* 143 */           tx = i + 0.5F;
/* 144 */           ty = j + 0.5F;
/*     */         } 
/* 147 */         destRect[index++] = tx - 0.5F;
/* 148 */         destRect[index++] = ty - 0.5F;
/* 150 */         wx += dx;
/* 151 */         wy += dy;
/* 152 */         w += dw;
/*     */       } 
/*     */     } 
/* 156 */     return destRect;
/*     */   }
/*     */   
/*     */   public Rectangle mapDestRect(Rectangle destRect) {
/* 172 */     if (destRect == null)
/* 173 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 176 */     int x0 = destRect.x;
/* 177 */     int x1 = destRect.x + destRect.width;
/* 178 */     int y0 = destRect.y;
/* 179 */     int y1 = destRect.y + destRect.height;
/* 181 */     Point2D[] pts = new Point2D[4];
/* 182 */     pts[0] = new Point2D.Float(x0, y0);
/* 183 */     pts[1] = new Point2D.Float(x1, y0);
/* 184 */     pts[2] = new Point2D.Float(x0, y1);
/* 185 */     pts[3] = new Point2D.Float(x1, y1);
/* 187 */     this.transform.transform(pts, 0, pts, 0, 4);
/* 189 */     int minX = Integer.MAX_VALUE;
/* 190 */     int maxX = Integer.MIN_VALUE;
/* 191 */     int minY = Integer.MAX_VALUE;
/* 192 */     int maxY = Integer.MIN_VALUE;
/* 194 */     for (int i = 0; i < 4; i++) {
/* 195 */       int px = (int)pts[i].getX();
/* 196 */       int py = (int)pts[i].getY();
/* 198 */       minX = Math.min(minX, px);
/* 199 */       maxX = Math.max(maxX, px);
/* 200 */       minY = Math.min(minY, py);
/* 201 */       maxY = Math.max(maxY, py);
/*     */     } 
/* 204 */     return new Rectangle(minX, minY, maxX - minX, maxY - minY);
/*     */   }
/*     */   
/*     */   public Rectangle mapSourceRect(Rectangle srcRect) {
/* 221 */     if (srcRect == null)
/* 222 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 226 */     if (this.invTransform == null)
/* 227 */       return null; 
/* 230 */     int x0 = srcRect.x;
/* 231 */     int x1 = srcRect.x + srcRect.width;
/* 232 */     int y0 = srcRect.y;
/* 233 */     int y1 = srcRect.y + srcRect.height;
/* 235 */     Point2D[] pts = new Point2D[4];
/* 236 */     pts[0] = new Point2D.Float(x0, y0);
/* 237 */     pts[1] = new Point2D.Float(x1, y0);
/* 238 */     pts[2] = new Point2D.Float(x0, y1);
/* 239 */     pts[3] = new Point2D.Float(x1, y1);
/* 241 */     this.invTransform.transform(pts, 0, pts, 0, 4);
/* 243 */     int minX = Integer.MAX_VALUE;
/* 244 */     int maxX = Integer.MIN_VALUE;
/* 245 */     int minY = Integer.MAX_VALUE;
/* 246 */     int maxY = Integer.MIN_VALUE;
/* 248 */     for (int i = 0; i < 4; i++) {
/* 249 */       int px = (int)pts[i].getX();
/* 250 */       int py = (int)pts[i].getY();
/* 252 */       minX = Math.min(minX, px);
/* 253 */       maxX = Math.max(maxX, px);
/* 254 */       minY = Math.min(minY, py);
/* 255 */       maxY = Math.max(maxY, py);
/*     */     } 
/* 258 */     return new Rectangle(minX, minY, maxX - minX, maxY - minY);
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt) {
/* 279 */     if (destPt == null)
/* 280 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 283 */     return this.transform.transform(destPt, null);
/*     */   }
/*     */   
/*     */   public Point2D mapSourcePoint(Point2D sourcePt) {
/* 306 */     if (sourcePt == null)
/* 307 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 310 */     return (this.invTransform != null) ? this.invTransform.transform(sourcePt, null) : null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\WarpPerspective.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */