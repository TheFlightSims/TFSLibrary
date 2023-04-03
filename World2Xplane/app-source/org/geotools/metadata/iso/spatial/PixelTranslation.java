/*     */ package org.geotools.metadata.iso.spatial;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.geotools.referencing.operation.LinearTransform;
/*     */ import org.geotools.referencing.operation.matrix.MatrixFactory;
/*     */ import org.geotools.referencing.operation.matrix.XMatrix;
/*     */ import org.geotools.referencing.operation.transform.ConcatenatedTransform;
/*     */ import org.geotools.referencing.operation.transform.ProjectiveTransform;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.metadata.spatial.PixelOrientation;
/*     */ import org.opengis.referencing.datum.PixelInCell;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ 
/*     */ public final class PixelTranslation implements Serializable {
/*     */   private static final long serialVersionUID = 2616596940766158984L;
/*     */   
/*  59 */   private static final MathTransform[] translations = new MathTransform[16];
/*     */   
/*     */   public final PixelOrientation orientation;
/*     */   
/*     */   public final double dx;
/*     */   
/*     */   public final double dy;
/*     */   
/*  81 */   private static Map<PixelOrientation, PixelTranslation> ORIENTATIONS = new HashMap<PixelOrientation, PixelTranslation>(12);
/*     */   
/*     */   static {
/*  84 */     new PixelTranslation(PixelOrientation.CENTER, 0.0D, 0.0D);
/*  85 */     new PixelTranslation(PixelOrientation.UPPER_LEFT, -0.5D, -0.5D);
/*  86 */     new PixelTranslation(PixelOrientation.UPPER_RIGHT, 0.5D, -0.5D);
/*  87 */     new PixelTranslation(PixelOrientation.LOWER_LEFT, -0.5D, 0.5D);
/*  88 */     new PixelTranslation(PixelOrientation.LOWER_RIGHT, 0.5D, 0.5D);
/*  89 */     new PixelTranslation("LEFT", -0.5D, 0.0D);
/*  90 */     new PixelTranslation("RIGHT", 0.5D, 0.0D);
/*  91 */     new PixelTranslation("UPPER", 0.0D, -0.5D);
/*  92 */     new PixelTranslation("LOWER", 0.0D, 0.5D);
/*     */   }
/*     */   
/*     */   private PixelTranslation(PixelOrientation orientation, double dx, double dy) {
/* 101 */     this.orientation = orientation;
/* 102 */     this.dx = dx;
/* 103 */     this.dy = dy;
/* 104 */     if (ORIENTATIONS.put(orientation, this) != null)
/* 105 */       throw new AssertionError(this); 
/*     */   }
/*     */   
/*     */   private PixelTranslation(String orientation, double dx, double dy) {
/* 115 */     this(PixelOrientation.valueOf(orientation), dx, dy);
/*     */   }
/*     */   
/*     */   public static PixelOrientation getPixelOrientation(PixelInCell anchor) throws IllegalArgumentException {
/* 128 */     if (PixelInCell.CELL_CENTER.equals(anchor))
/* 129 */       return PixelOrientation.CENTER; 
/* 130 */     if (PixelInCell.CELL_CORNER.equals(anchor))
/* 131 */       return PixelOrientation.UPPER_LEFT; 
/* 132 */     if (anchor == null)
/* 133 */       return null; 
/* 135 */     throw new IllegalArgumentException(Errors.format(58, "anchor", anchor));
/*     */   }
/*     */   
/*     */   public static double getPixelTranslation(PixelInCell anchor) {
/* 157 */     if (PixelInCell.CELL_CENTER.equals(anchor))
/* 158 */       return 0.0D; 
/* 159 */     if (PixelInCell.CELL_CORNER.equals(anchor))
/* 160 */       return -0.5D; 
/* 162 */     throw new IllegalArgumentException(Errors.format(58, "anchor", anchor));
/*     */   }
/*     */   
/*     */   public static PixelTranslation getPixelTranslation(PixelOrientation anchor) throws IllegalArgumentException {
/* 189 */     PixelTranslation offset = ORIENTATIONS.get(anchor);
/* 190 */     if (offset == null)
/* 191 */       throw new IllegalArgumentException(Errors.format(58, "anchor", anchor)); 
/* 194 */     return offset;
/*     */   }
/*     */   
/*     */   public static PixelOrientation getPixelOrientation(double dx, double dy) {
/* 206 */     for (PixelTranslation candidate : ORIENTATIONS.values()) {
/* 207 */       if (candidate.dx == dx && candidate.dy == dy)
/* 208 */         return candidate.orientation; 
/*     */     } 
/* 211 */     return null;
/*     */   }
/*     */   
/*     */   public static MathTransform translate(MathTransform gridToCRS, PixelInCell current, PixelInCell expected) {
/*     */     int index;
/*     */     LinearTransform linearTransform;
/* 226 */     if (Utilities.equals(current, expected))
/* 227 */       return gridToCRS; 
/* 229 */     if (gridToCRS == null)
/* 230 */       return null; 
/* 232 */     int dimension = gridToCRS.getSourceDimensions();
/* 233 */     double offset = getPixelTranslation(expected) - getPixelTranslation(current);
/* 235 */     if (offset == -0.5D) {
/* 236 */       index = 2 * dimension;
/* 237 */     } else if (offset == 0.5D) {
/* 238 */       index = 2 * dimension + 1;
/*     */     } else {
/* 240 */       index = translations.length;
/*     */     } 
/* 243 */     if (index >= translations.length) {
/* 244 */       linearTransform = ProjectiveTransform.createTranslation(dimension, offset);
/*     */     } else {
/* 245 */       synchronized (translations) {
/* 246 */         MathTransform mt = translations[index];
/* 247 */         if (mt == null) {
/* 248 */           linearTransform = ProjectiveTransform.createTranslation(dimension, offset);
/* 249 */           translations[index] = (MathTransform)linearTransform;
/*     */         } 
/*     */       } 
/*     */     } 
/* 252 */     return ConcatenatedTransform.create((MathTransform)linearTransform, gridToCRS);
/*     */   }
/*     */   
/*     */   public static MathTransform translate(MathTransform gridToCRS, PixelOrientation current, PixelOrientation expected, int xDimension, int yDimension) {
/*     */     LinearTransform linearTransform;
/* 271 */     if (Utilities.equals(current, expected))
/* 272 */       return gridToCRS; 
/* 274 */     if (gridToCRS == null)
/* 275 */       return null; 
/* 277 */     int dimension = gridToCRS.getSourceDimensions();
/* 278 */     if (xDimension < 0 || xDimension >= dimension)
/* 279 */       throw illegalDimension("xDimension", Integer.valueOf(xDimension)); 
/* 281 */     if (yDimension < 0 || yDimension >= dimension)
/* 282 */       throw illegalDimension("yDimension", Integer.valueOf(yDimension)); 
/* 284 */     if (xDimension == yDimension)
/* 285 */       throw illegalDimension("xDimension", "yDimension"); 
/* 287 */     PixelTranslation source = getPixelTranslation(current);
/* 288 */     PixelTranslation target = getPixelTranslation(expected);
/* 289 */     double dx = target.dx - source.dx;
/* 290 */     double dy = target.dy - source.dy;
/* 292 */     if (dimension == 2 && (xDimension | yDimension) == 1 && dx == dy && Math.abs(dx) == 0.5D) {
/* 293 */       int index = (dx >= 0.0D) ? 5 : 4;
/* 294 */       synchronized (translations) {
/* 295 */         MathTransform mt = translations[index];
/* 296 */         if (mt == null) {
/* 297 */           linearTransform = ProjectiveTransform.createTranslation(dimension, dx);
/* 298 */           translations[index] = (MathTransform)linearTransform;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 302 */       XMatrix xMatrix = MatrixFactory.create(dimension + 1);
/* 303 */       xMatrix.setElement(xDimension, dimension, dx);
/* 304 */       xMatrix.setElement(yDimension, dimension, dy);
/* 305 */       linearTransform = ProjectiveTransform.create((Matrix)xMatrix);
/*     */     } 
/* 307 */     return ConcatenatedTransform.create((MathTransform)linearTransform, gridToCRS);
/*     */   }
/*     */   
/*     */   private static IllegalArgumentException illegalDimension(String name, Object dimension) {
/* 314 */     return new IllegalArgumentException(Errors.format(58, name, dimension));
/*     */   }
/*     */   
/*     */   public String toString() {
/* 322 */     return String.valueOf(this.orientation) + '[' + this.dx + ", " + this.dy + ']';
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\spatial\PixelTranslation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */