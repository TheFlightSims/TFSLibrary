/*     */ package org.geotools.referencing.operation.builder;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.Arrays;
/*     */ import org.geotools.referencing.operation.matrix.MatrixFactory;
/*     */ import org.geotools.referencing.operation.matrix.XMatrix;
/*     */ import org.geotools.referencing.operation.transform.ProjectiveTransform;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.coverage.grid.GridEnvelope;
/*     */ import org.opengis.geometry.Envelope;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.datum.PixelInCell;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ 
/*     */ public class GridToEnvelopeMapper {
/*     */   public static final int SWAP_XY = 1;
/*     */   
/*     */   public static final int REVERSE_AXIS = 2;
/*     */   
/*     */   private int defined;
/*     */   
/*     */   private GridEnvelope gridRange;
/*     */   
/*     */   private Envelope envelope;
/*     */   
/* 131 */   private PixelInCell anchor = PixelInCell.CELL_CENTER;
/*     */   
/*     */   private Boolean swapXY;
/*     */   
/*     */   private boolean[] reverseAxis;
/*     */   
/*     */   private MathTransform transform;
/*     */   
/*     */   public GridToEnvelopeMapper() {}
/*     */   
/*     */   public GridToEnvelopeMapper(GridEnvelope gridRange, Envelope userRange) throws MismatchedDimensionException {
/* 171 */     ensureNonNull("gridRange", gridRange);
/* 172 */     ensureNonNull("userRange", userRange);
/* 173 */     int gridDim = gridRange.getDimension();
/* 174 */     int userDim = userRange.getDimension();
/* 175 */     if (userDim != gridDim)
/* 176 */       throw new MismatchedDimensionException(Errors.format(93, Integer.valueOf(gridDim), Integer.valueOf(userDim))); 
/* 179 */     this.gridRange = gridRange;
/* 180 */     this.envelope = userRange;
/*     */   }
/*     */   
/*     */   private static void ensureNonNull(String name, Object object) throws IllegalArgumentException {
/* 189 */     if (object == null)
/* 190 */       throw new IllegalArgumentException(Errors.format(143, name)); 
/*     */   }
/*     */   
/*     */   private static void ensureDimensionMatch(GridEnvelope gridRange, Envelope envelope, boolean checkingRange) {
/* 201 */     if (gridRange != null && envelope != null) {
/*     */       String label;
/*     */       int dim1;
/*     */       int dim2;
/* 204 */       if (checkingRange) {
/* 205 */         label = "gridRange";
/* 206 */         dim1 = gridRange.getDimension();
/* 207 */         dim2 = envelope.getDimension();
/*     */       } else {
/* 209 */         label = "envelope";
/* 210 */         dim1 = envelope.getDimension();
/* 211 */         dim2 = gridRange.getDimension();
/*     */       } 
/* 213 */       if (dim1 != dim2)
/* 214 */         throw new MismatchedDimensionException(Errors.format(94, label, Integer.valueOf(dim1), Integer.valueOf(dim2))); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void reset() {
/* 224 */     this.transform = null;
/* 225 */     if (isAutomatic(2))
/* 226 */       this.reverseAxis = null; 
/* 228 */     if (isAutomatic(1))
/* 229 */       this.swapXY = null; 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public PixelInCell getGridType() {
/* 241 */     return getPixelAnchor();
/*     */   }
/*     */   
/*     */   public PixelInCell getPixelAnchor() {
/* 254 */     return this.anchor;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setGridType(PixelInCell anchor) {
/* 265 */     setPixelAnchor(anchor);
/*     */   }
/*     */   
/*     */   public void setPixelAnchor(PixelInCell anchor) {
/* 278 */     ensureNonNull("anchor", anchor);
/* 279 */     if (!Utilities.equals(this.anchor, anchor)) {
/* 280 */       this.anchor = anchor;
/* 281 */       reset();
/*     */     } 
/*     */   }
/*     */   
/*     */   public GridEnvelope getGridRange() throws IllegalStateException {
/* 292 */     if (this.gridRange == null)
/* 293 */       throw new IllegalStateException(Errors.format(100, "gridRange")); 
/* 296 */     return this.gridRange;
/*     */   }
/*     */   
/*     */   public void setGridRange(GridEnvelope gridRange) {
/* 305 */     ensureNonNull("gridRange", gridRange);
/* 306 */     ensureDimensionMatch(gridRange, this.envelope, true);
/* 307 */     if (!Utilities.equals(this.gridRange, gridRange)) {
/* 308 */       this.gridRange = gridRange;
/* 309 */       reset();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Envelope getEnvelope() throws IllegalStateException {
/* 321 */     if (this.envelope == null)
/* 322 */       throw new IllegalStateException(Errors.format(100, "envelope")); 
/* 325 */     return this.envelope;
/*     */   }
/*     */   
/*     */   public void setEnvelope(Envelope envelope) {
/* 335 */     ensureNonNull("envelope", envelope);
/* 336 */     ensureDimensionMatch(this.gridRange, envelope, false);
/* 337 */     if (!Utilities.equals(this.envelope, envelope)) {
/* 338 */       this.envelope = envelope;
/* 339 */       reset();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean swapXY(CoordinateSystem cs) {
/* 347 */     if (cs != null && cs.getDimension() >= 2)
/* 348 */       return (AxisDirection.NORTH.equals(cs.getAxis(0).getDirection().absolute()) && AxisDirection.EAST.equals(cs.getAxis(1).getDirection().absolute())); 
/* 351 */     return false;
/*     */   }
/*     */   
/*     */   public boolean getSwapXY() {
/* 378 */     if (this.swapXY == null) {
/* 379 */       boolean value = false;
/* 380 */       if (isAutomatic(1))
/* 381 */         value = swapXY(getCoordinateSystem()); 
/* 383 */       this.swapXY = Boolean.valueOf(value);
/*     */     } 
/* 385 */     return this.swapXY.booleanValue();
/*     */   }
/*     */   
/*     */   public void setSwapXY(boolean swapXY) {
/* 396 */     Boolean newValue = Boolean.valueOf(swapXY);
/* 397 */     if (!newValue.equals(this.swapXY))
/* 398 */       reset(); 
/* 400 */     this.swapXY = newValue;
/* 401 */     this.defined |= 0x1;
/*     */   }
/*     */   
/*     */   public boolean[] getReverseAxis() {
/* 423 */     if (this.reverseAxis == null) {
/* 424 */       CoordinateSystem cs = getCoordinateSystem();
/* 425 */       if (cs != null) {
/* 426 */         int dimension = cs.getDimension();
/* 427 */         this.reverseAxis = new boolean[dimension];
/* 428 */         if (isAutomatic(2)) {
/*     */           int i;
/* 429 */           for (i = 0; i < dimension; i++) {
/* 430 */             AxisDirection direction = cs.getAxis(i).getDirection();
/* 431 */             AxisDirection absolute = direction.absolute();
/* 432 */             this.reverseAxis[i] = direction.equals(absolute.opposite());
/*     */           } 
/* 434 */           if (dimension >= 2) {
/* 435 */             i = getSwapXY() ? 0 : 1;
/* 436 */             this.reverseAxis[i] = !this.reverseAxis[i];
/*     */           } 
/*     */         } 
/*     */       } else {
/* 442 */         int length = 0;
/* 443 */         if (this.gridRange != null) {
/* 444 */           length = this.gridRange.getDimension();
/* 445 */         } else if (this.envelope != null) {
/* 446 */           length = this.envelope.getDimension();
/*     */         } 
/* 448 */         if (length >= 2) {
/* 449 */           this.reverseAxis = new boolean[length];
/* 450 */           this.reverseAxis[1] = true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 454 */     return this.reverseAxis;
/*     */   }
/*     */   
/*     */   public void setReverseAxis(boolean[] reverse) {
/* 467 */     if (!Arrays.equals(this.reverseAxis, reverse))
/* 468 */       reset(); 
/* 470 */     this.reverseAxis = reverse;
/* 471 */     this.defined |= 0x2;
/*     */   }
/*     */   
/*     */   public void reverseAxis(int dimension) {
/* 483 */     if (this.reverseAxis == null) {
/*     */       int length;
/* 485 */       if (this.gridRange != null) {
/* 486 */         length = this.gridRange.getDimension();
/*     */       } else {
/* 488 */         ensureNonNull("envelope", this.envelope);
/* 489 */         length = this.envelope.getDimension();
/*     */       } 
/* 491 */       this.reverseAxis = new boolean[length];
/*     */     } 
/* 493 */     if (!this.reverseAxis[dimension])
/* 494 */       reset(); 
/* 496 */     this.reverseAxis[dimension] = true;
/* 497 */     this.defined |= 0x2;
/*     */   }
/*     */   
/*     */   public boolean isAutomatic(int mask) {
/* 508 */     return ((this.defined & mask) == 0);
/*     */   }
/*     */   
/*     */   public void setAutomatic(int mask) {
/* 520 */     this.defined &= mask ^ 0xFFFFFFFF;
/*     */   }
/*     */   
/*     */   private CoordinateSystem getCoordinateSystem() {
/* 527 */     if (this.envelope != null) {
/* 529 */       CoordinateReferenceSystem crs = this.envelope.getCoordinateReferenceSystem();
/* 530 */       if (crs != null)
/* 531 */         return crs.getCoordinateSystem(); 
/*     */     } 
/* 534 */     return null;
/*     */   }
/*     */   
/*     */   public MathTransform createTransform() throws IllegalStateException {
/* 544 */     if (this.transform == null) {
/*     */       double translate;
/* 545 */       GridEnvelope gridRange = getGridRange();
/* 546 */       Envelope userRange = getEnvelope();
/* 547 */       boolean swapXY = getSwapXY();
/* 548 */       boolean[] reverse = getReverseAxis();
/* 549 */       PixelInCell gridType = getPixelAnchor();
/* 550 */       int dimension = gridRange.getDimension();
/* 557 */       if (PixelInCell.CELL_CENTER.equals(gridType)) {
/* 558 */         translate = 0.5D;
/* 559 */       } else if (PixelInCell.CELL_CORNER.equals(gridType)) {
/* 560 */         translate = 0.0D;
/*     */       } else {
/* 562 */         throw new IllegalStateException(Errors.format(58, "gridType", gridType));
/*     */       } 
/* 565 */       XMatrix xMatrix = MatrixFactory.create(dimension + 1);
/* 566 */       for (int i = 0; i < dimension; i++) {
/*     */         double offset;
/* 569 */         int j = i;
/* 570 */         if (swapXY && j <= 1)
/* 571 */           j = 1 - j; 
/* 573 */         double scale = userRange.getSpan(j) / gridRange.getSpan(i);
/* 575 */         if (reverse == null || j >= reverse.length || !reverse[j]) {
/* 576 */           offset = userRange.getMinimum(j);
/*     */         } else {
/* 578 */           scale = -scale;
/* 579 */           offset = userRange.getMaximum(j);
/*     */         } 
/* 581 */         offset -= scale * (gridRange.getLow(i) - translate);
/* 582 */         xMatrix.setElement(j, j, 0.0D);
/* 583 */         xMatrix.setElement(j, i, scale);
/* 584 */         xMatrix.setElement(j, dimension, offset);
/*     */       } 
/* 586 */       this.transform = (MathTransform)ProjectiveTransform.create((Matrix)xMatrix);
/*     */     } 
/* 588 */     return this.transform;
/*     */   }
/*     */   
/*     */   public AffineTransform createAffineTransform() throws IllegalStateException {
/* 598 */     MathTransform transform = createTransform();
/* 599 */     if (transform instanceof AffineTransform)
/* 600 */       return (AffineTransform)transform; 
/* 602 */     throw new IllegalStateException(Errors.format(118));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\GridToEnvelopeMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */