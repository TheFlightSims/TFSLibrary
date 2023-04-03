/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class Warp implements Serializable {
/*     */   public int[] warpRect(int x, int y, int width, int height, int subsampleBitsH, int subsampleBitsV, int[] destRect) {
/* 107 */     if (destRect != null && destRect.length < width * height * 2)
/* 108 */       throw new IllegalArgumentException(JaiI18N.getString("Warp0")); 
/* 110 */     return warpSparseRect(x, y, width, height, 1, 1, subsampleBitsH, subsampleBitsV, destRect);
/*     */   }
/*     */   
/*     */   public float[] warpRect(int x, int y, int width, int height, float[] destRect) {
/* 142 */     if (destRect != null && destRect.length < width * height * 2)
/* 143 */       throw new IllegalArgumentException(JaiI18N.getString("Warp0")); 
/* 145 */     return warpSparseRect(x, y, width, height, 1, 1, destRect);
/*     */   }
/*     */   
/*     */   public int[] warpPoint(int x, int y, int subsampleBitsH, int subsampleBitsV, int[] destRect) {
/* 179 */     if (destRect != null && destRect.length < 2)
/* 180 */       throw new IllegalArgumentException(JaiI18N.getString("Warp0")); 
/* 182 */     return warpSparseRect(x, y, 1, 1, 1, 1, subsampleBitsH, subsampleBitsV, destRect);
/*     */   }
/*     */   
/*     */   public float[] warpPoint(int x, int y, float[] destRect) {
/* 209 */     if (destRect != null && destRect.length < 2)
/* 210 */       throw new IllegalArgumentException(JaiI18N.getString("Warp0")); 
/* 212 */     return warpSparseRect(x, y, 1, 1, 1, 1, destRect);
/*     */   }
/*     */   
/*     */   public int[] warpSparseRect(int x, int y, int width, int height, int periodX, int periodY, int subsampleBitsH, int subsampleBitsV, int[] destRect) {
/* 254 */     int nVals = 2 * (width + periodX - 1) / periodX * (height + periodY - 1) / periodY;
/* 255 */     if (destRect != null && destRect.length < nVals)
/* 256 */       throw new IllegalArgumentException(JaiI18N.getString("Warp0")); 
/* 258 */     float[] fdestRect = warpSparseRect(x, y, width, height, periodX, periodY, (float[])null);
/* 260 */     int size = fdestRect.length;
/* 262 */     if (destRect == null)
/* 263 */       destRect = new int[size]; 
/* 266 */     int precH = 1 << subsampleBitsH;
/* 267 */     int precV = 1 << subsampleBitsV;
/* 269 */     for (int i = 0; i < size; i += 2) {
/* 270 */       destRect[i] = (int)Math.floor((fdestRect[i] * precH));
/* 271 */       destRect[i + 1] = (int)Math.floor((fdestRect[i + 1] * precV));
/*     */     } 
/* 274 */     return destRect;
/*     */   }
/*     */   
/*     */   public abstract float[] warpSparseRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, float[] paramArrayOffloat);
/*     */   
/*     */   public Rectangle mapSourceRect(Rectangle sourceRect) {
/* 320 */     return null;
/*     */   }
/*     */   
/*     */   public Rectangle mapDestRect(Rectangle destRect) {
/* 339 */     if (destRect == null)
/* 340 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 343 */     int x = destRect.x;
/* 344 */     int y = destRect.y;
/* 345 */     int w = destRect.width;
/* 346 */     int h = destRect.height;
/* 349 */     float[] warpPoints = new float[Math.max(w * 2, (h - 2) * 2)];
/* 354 */     int length = w * 2;
/* 355 */     warpSparseRect(x, y, w, 1, 1, 1, warpPoints);
/* 358 */     float minX = warpPoints[0];
/* 359 */     float maxX = warpPoints[0];
/* 360 */     float minY = warpPoints[1];
/* 361 */     float maxY = warpPoints[1];
/*     */     int i;
/* 365 */     for (i = 2; i < length; i += 2) {
/* 366 */       float thisX = warpPoints[i];
/* 367 */       float thisY = warpPoints[i + 1];
/* 369 */       if (thisX < minX) {
/* 370 */         minX = thisX;
/* 371 */       } else if (thisX > maxX) {
/* 372 */         maxX = thisX;
/*     */       } 
/* 375 */       if (thisY < minY) {
/* 376 */         minY = thisY;
/* 377 */       } else if (thisY > maxY) {
/* 378 */         maxY = thisY;
/*     */       } 
/*     */     } 
/* 383 */     warpSparseRect(x, y + h - 1, w, 1, 1, 1, warpPoints);
/* 385 */     for (i = 0; i < length; i += 2) {
/* 386 */       float thisX = warpPoints[i];
/* 387 */       float thisY = warpPoints[i + 1];
/* 389 */       if (thisX < minX) {
/* 390 */         minX = thisX;
/* 391 */       } else if (thisX > maxX) {
/* 392 */         maxX = thisX;
/*     */       } 
/* 395 */       if (thisY < minY) {
/* 396 */         minY = thisY;
/* 397 */       } else if (thisY > maxY) {
/* 398 */         maxY = thisY;
/*     */       } 
/*     */     } 
/* 403 */     length = (h - 2) * 2;
/* 404 */     warpSparseRect(x, y + 1, 1, h - 2, 1, 1, warpPoints);
/* 406 */     for (i = 0; i < length; i += 2) {
/* 407 */       float thisX = warpPoints[i];
/* 408 */       float thisY = warpPoints[i + 1];
/* 410 */       if (thisX < minX) {
/* 411 */         minX = thisX;
/* 412 */       } else if (thisX > maxX) {
/* 413 */         maxX = thisX;
/*     */       } 
/* 416 */       if (thisY < minY) {
/* 417 */         minY = thisY;
/* 418 */       } else if (thisY > maxY) {
/* 419 */         maxY = thisY;
/*     */       } 
/*     */     } 
/* 424 */     warpSparseRect(x + w - 1, y + 1, 1, h - 2, 1, 1, warpPoints);
/* 426 */     for (i = 0; i < length; i += 2) {
/* 427 */       float thisX = warpPoints[i];
/* 428 */       float thisY = warpPoints[i + 1];
/* 430 */       if (thisX < minX) {
/* 431 */         minX = thisX;
/* 432 */       } else if (thisX > maxX) {
/* 433 */         maxX = thisX;
/*     */       } 
/* 436 */       if (thisY < minY) {
/* 437 */         minY = thisY;
/* 438 */       } else if (thisY > maxY) {
/* 439 */         maxY = thisY;
/*     */       } 
/*     */     } 
/* 443 */     x = (int)Math.floor(minX);
/* 444 */     y = (int)Math.floor(minY);
/* 445 */     w = (int)Math.ceil((maxX - x)) + 1;
/* 446 */     h = (int)Math.ceil((maxY - y)) + 1;
/* 448 */     return new Rectangle(x, y, w, h);
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt) {
/* 481 */     if (destPt == null)
/* 482 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 485 */     float[] sourceXY = warpSparseRect((int)destPt.getX(), (int)destPt.getY(), 1, 1, 1, 1, null);
/* 488 */     Point2D pt = (Point2D)destPt.clone();
/* 489 */     pt.setLocation(sourceXY[0], sourceXY[1]);
/* 491 */     return pt;
/*     */   }
/*     */   
/*     */   public Point2D mapSourcePoint(Point2D sourcePt) {
/* 511 */     if (sourcePt == null)
/* 512 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 515 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\Warp.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */