/*     */ package org.openstreetmap.osmosis.core.domain.v0_6;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.LinkedList;
/*     */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*     */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*     */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*     */ 
/*     */ public class Bound extends Entity implements Comparable<Bound> {
/*     */   private static final double MIN_LATITUDE = -90.0D;
/*     */   
/*     */   private static final double MAX_LATITUDE = 90.0D;
/*     */   
/*     */   private static final double MIN_LONGITUDE = -180.0D;
/*     */   
/*     */   private static final double MAX_LONGITUDE = 180.0D;
/*     */   
/*     */   private double right;
/*     */   
/*     */   private double left;
/*     */   
/*     */   private double top;
/*     */   
/*     */   private double bottom;
/*     */   
/*     */   private String origin;
/*     */   
/*     */   public Bound(String origin) {
/*  40 */     this(180.0D, -180.0D, 90.0D, -90.0D, origin);
/*     */   }
/*     */   
/*     */   public Bound(double right, double left, double top, double bottom, String origin) {
/*  59 */     super(new CommonEntityData(0L, 0, new Date(), OsmUser.NONE, 0L));
/*  62 */     if (Double.compare(right, 181.0D) > 0 || Double.compare(right, -181.0D) < 0 || Double.compare(left, 181.0D) > 0 || Double.compare(left, -181.0D) < 0 || Double.compare(top, 91.0D) > 0 || Double.compare(top, -91.0D) < 0 || Double.compare(bottom, 91.0D) > 0 || Double.compare(bottom, -91.0D) < 0)
/*  70 */       throw new IllegalArgumentException("Bound coordinates outside of valid range"); 
/*  72 */     if (Double.compare(top, bottom) < 0)
/*  73 */       throw new IllegalArgumentException("Bound top < bottom"); 
/*  75 */     this.right = right;
/*  76 */     this.left = left;
/*  77 */     this.top = top;
/*  78 */     this.bottom = bottom;
/*  79 */     this.origin = origin;
/*     */   }
/*     */   
/*     */   public Bound(StoreReader sr, StoreClassRegister scr) {
/*  92 */     super(sr, scr);
/*  94 */     this.right = sr.readDouble();
/*  95 */     this.left = sr.readDouble();
/*  96 */     this.top = sr.readDouble();
/*  97 */     this.bottom = sr.readDouble();
/*  98 */     this.origin = sr.readString();
/*     */   }
/*     */   
/*     */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 107 */     super.store(sw, scr);
/* 109 */     sw.writeDouble(this.right);
/* 110 */     sw.writeDouble(this.left);
/* 111 */     sw.writeDouble(this.top);
/* 112 */     sw.writeDouble(this.bottom);
/* 113 */     sw.writeString(this.origin);
/*     */   }
/*     */   
/*     */   public EntityType getType() {
/* 122 */     return EntityType.Bound;
/*     */   }
/*     */   
/*     */   public double getRight() {
/* 130 */     return this.right;
/*     */   }
/*     */   
/*     */   public double getLeft() {
/* 138 */     return this.left;
/*     */   }
/*     */   
/*     */   public double getTop() {
/* 146 */     return this.top;
/*     */   }
/*     */   
/*     */   public double getBottom() {
/* 154 */     return this.bottom;
/*     */   }
/*     */   
/*     */   public String getOrigin() {
/* 162 */     return this.origin;
/*     */   }
/*     */   
/*     */   public Bound intersect(Bound intersectingBound) {
/*     */     String newOrigin;
/* 175 */     double newRight = 0.0D, newLeft = 0.0D;
/* 179 */     if (intersectingBound == null)
/* 180 */       return null; 
/* 183 */     double newTop = Math.min(getTop(), intersectingBound.getTop());
/* 184 */     double newBottom = Math.max(getBottom(), intersectingBound.getBottom());
/* 185 */     if (Double.compare(newBottom, newTop) >= 0)
/* 186 */       return null; 
/* 189 */     boolean intersect180 = (Double.compare(intersectingBound.getLeft(), intersectingBound.getRight()) > 0);
/* 190 */     boolean this180 = (Double.compare(getLeft(), getRight()) > 0);
/* 192 */     if ((intersect180 && this180) || (!intersect180 && !this180)) {
/* 194 */       newRight = Math.min(getRight(), intersectingBound.getRight());
/* 195 */       newLeft = Math.max(getLeft(), intersectingBound.getLeft());
/* 196 */       if (!intersect180 && !this180 && Double.compare(newLeft, newRight) >= 0)
/* 201 */         return null; 
/*     */     } else {
/*     */       Bound b1;
/*     */       Bound b2;
/* 206 */       if (intersect180 && !this180) {
/* 208 */         b1 = this;
/* 209 */         b2 = intersectingBound;
/*     */       } else {
/* 212 */         b1 = intersectingBound;
/* 213 */         b2 = this;
/*     */       } 
/* 215 */       if (Double.compare(b1.getRight(), b2.getLeft()) > 0 && Double.compare(b1.getLeft(), b2.getRight()) < 0) {
/* 219 */         Double diff1 = Double.valueOf(b1.getRight() - b1.getLeft());
/* 220 */         Double diff2 = Double.valueOf(b2.getRight() - -180.0D + 180.0D - b2.getLeft());
/* 221 */         if (Double.compare(diff1.doubleValue(), diff2.doubleValue()) <= 0) {
/* 222 */           newRight = b1.getRight();
/* 223 */           newLeft = b1.getLeft();
/*     */         } else {
/* 225 */           newRight = b2.getRight();
/* 226 */           newLeft = b2.getLeft();
/*     */         } 
/* 228 */       } else if (Double.compare(b1.getRight(), b2.getLeft()) > 0) {
/* 230 */         newRight = b1.getRight();
/* 231 */         newLeft = b2.getLeft();
/* 232 */       } else if (Double.compare(b1.getLeft(), b2.getRight()) < 0) {
/* 234 */         newRight = b2.getRight();
/* 235 */         newLeft = b1.getLeft();
/*     */       } 
/*     */     } 
/* 238 */     if (Double.compare(newRight, newLeft) == 0)
/* 239 */       return null; 
/* 244 */     if (this.origin != "") {
/* 245 */       newOrigin = this.origin;
/*     */     } else {
/* 247 */       newOrigin = intersectingBound.origin;
/*     */     } 
/* 250 */     return new Bound(newRight, newLeft, newTop, newBottom, newOrigin);
/*     */   }
/*     */   
/*     */   public Bound union(Bound unionBound) {
/*     */     String newOrigin;
/* 264 */     double newRight = 0.0D, newLeft = 0.0D;
/* 267 */     if (unionBound == null)
/* 268 */       return this; 
/* 272 */     double newTop = Math.max(getTop(), unionBound.getTop());
/* 273 */     double newBottom = Math.min(getBottom(), unionBound.getBottom());
/* 274 */     if (Double.compare(newBottom, newTop) >= 0)
/* 275 */       return null; 
/* 278 */     if ((Double.compare(getLeft(), -180.0D) == 0 && Double.compare(getRight(), 180.0D) == 0) || (Double.compare(unionBound.getLeft(), -180.0D) == 0 && Double.compare(unionBound.getRight(), 180.0D) == 0)) {
/* 284 */       newRight = 180.0D;
/* 285 */       newLeft = -180.0D;
/*     */     } else {
/* 290 */       boolean union180 = (Double.compare(unionBound.getLeft(), unionBound.getRight()) > 0);
/* 291 */       boolean this180 = (Double.compare(getLeft(), getRight()) > 0);
/* 293 */       if (union180 && this180) {
/* 295 */         newRight = Math.max(getRight(), unionBound.getRight());
/* 296 */         newLeft = Math.min(getLeft(), unionBound.getLeft());
/* 297 */       } else if (!union180 && !this180) {
/* 301 */         double size1 = Math.max(getRight(), unionBound.getRight()) - Math.min(getLeft(), unionBound.getLeft());
/* 304 */         double size2 = Math.min(getRight(), unionBound.getRight()) - -180.0D + 180.0D - Math.max(getLeft(), unionBound.getLeft());
/* 308 */         if (Double.compare(size1, size2) <= 0) {
/* 309 */           newRight = Math.max(getRight(), unionBound.getRight());
/* 310 */           newLeft = Math.min(getLeft(), unionBound.getLeft());
/*     */         } else {
/* 312 */           newRight = Math.min(getRight(), unionBound.getRight());
/* 313 */           newLeft = Math.max(getLeft(), unionBound.getLeft());
/*     */         } 
/*     */       } else {
/*     */         Bound b1;
/*     */         Bound b2;
/* 318 */         if (union180 && !this180) {
/* 320 */           b1 = unionBound;
/* 321 */           b2 = this;
/*     */         } else {
/* 324 */           b1 = this;
/* 325 */           b2 = unionBound;
/*     */         } 
/* 330 */         if (Double.compare(b1.getRight(), b2.getLeft()) >= 0 && Double.compare(b1.getLeft(), b2.getRight()) <= 0) {
/* 332 */           newLeft = -180.0D;
/* 333 */           newRight = 180.0D;
/*     */         } else {
/* 336 */           double size1 = Math.max(b1.getRight(), b2.getRight()) - -180.0D + 180.0D - b1.getLeft();
/* 339 */           double size2 = b1.getRight() - -180.0D + 180.0D - Math.min(b1.getLeft(), b2.getLeft());
/* 343 */           if (Double.compare(size1, size2) <= 0) {
/* 344 */             newRight = Math.max(b1.getRight(), b2.getRight());
/* 345 */             newLeft = b1.getLeft();
/*     */           } else {
/* 347 */             newRight = b1.getRight();
/* 348 */             newLeft = Math.min(b1.getLeft(), b2.getLeft());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 354 */     if (Double.compare(newRight, newLeft) == 0)
/* 355 */       return null; 
/* 360 */     if (getOrigin() != null && !getOrigin().equals("")) {
/* 361 */       newOrigin = getOrigin();
/*     */     } else {
/* 363 */       newOrigin = unionBound.getOrigin();
/*     */     } 
/* 366 */     return new Bound(newRight, newLeft, newTop, newBottom, newOrigin);
/*     */   }
/*     */   
/*     */   public Iterable<Bound> toSimpleBound() {
/* 378 */     Collection<Bound> c = new LinkedList<Bound>();
/* 379 */     if (Double.compare(getLeft(), getRight()) < 0) {
/* 381 */       c.add(this);
/*     */     } else {
/* 384 */       c.add(new Bound(180.0D, getLeft(), getTop(), getBottom(), getOrigin()));
/* 390 */       c.add(new Bound(getRight(), -180.0D, getTop(), getBottom(), getOrigin()));
/*     */     } 
/* 397 */     return Collections.unmodifiableCollection(c);
/*     */   }
/*     */   
/*     */   public int compareTo(Bound comparisonBound) {
/* 411 */     double areaT = 0.0D, areaC = 0.0D;
/* 418 */     for (Bound b : toSimpleBound())
/* 419 */       areaT += (b.getRight() - b.getLeft()) * (b.getTop() - b.getBottom()); 
/* 421 */     for (Bound b : comparisonBound.toSimpleBound())
/* 422 */       areaC += (b.getRight() - b.getLeft()) * (b.getTop() - b.getBottom()); 
/* 426 */     int result = Double.compare(areaT, areaC);
/* 427 */     if (result != 0)
/* 428 */       return result; 
/* 431 */     result = Double.compare(getTop(), comparisonBound.getTop());
/* 432 */     if (result != 0)
/* 433 */       return result; 
/* 436 */     result = Double.compare(getBottom(), comparisonBound.getBottom());
/* 437 */     if (result != 0)
/* 438 */       return result; 
/* 441 */     result = Double.compare(getLeft(), comparisonBound.getLeft());
/* 442 */     if (result != 0)
/* 443 */       return result; 
/* 446 */     result = Double.compare(getRight(), comparisonBound.getRight());
/* 447 */     if (result != 0)
/* 448 */       return result; 
/* 451 */     String myOrigin = getOrigin();
/* 452 */     String otherOrigin = comparisonBound.getOrigin();
/* 455 */     if (myOrigin == null) {
/* 456 */       if (otherOrigin == null)
/* 457 */         return 0; 
/* 459 */       return -1;
/*     */     } 
/* 462 */     if (otherOrigin == null)
/* 463 */       return 1; 
/* 465 */     return myOrigin.compareTo(otherOrigin);
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 476 */     if (o instanceof Bound)
/* 477 */       return (compareTo((Bound)o) == 0); 
/* 479 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 495 */     return (int)getId() + getVersion();
/*     */   }
/*     */   
/*     */   public Bound getWriteableInstance() {
/* 504 */     return this;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 512 */     return "Bound(top=" + getTop() + ", bottom=" + getBottom() + ", left=" + getLeft() + ", right=" + getRight() + ")";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\v0_6\Bound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */