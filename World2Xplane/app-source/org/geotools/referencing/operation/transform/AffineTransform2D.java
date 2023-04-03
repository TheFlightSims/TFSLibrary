/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.prefs.Preferences;
/*     */ import org.geotools.geometry.GeneralDirectPosition;
/*     */ import org.geotools.referencing.operation.LinearTransform;
/*     */ import org.geotools.referencing.operation.matrix.Matrix2;
/*     */ import org.geotools.referencing.operation.matrix.Matrix3;
/*     */ import org.geotools.referencing.operation.matrix.XAffineTransform;
/*     */ import org.geotools.referencing.wkt.Formattable;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.referencing.wkt.Symbols;
/*     */ import org.geotools.resources.Formattable;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransform2D;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class AffineTransform2D extends XAffineTransform implements MathTransform2D, LinearTransform, Formattable, Cloneable {
/*     */   private static final long serialVersionUID = -5299837898367149069L;
/*     */   
/*     */   private transient AffineTransform2D inverse;
/*     */   
/*     */   public AffineTransform2D(AffineTransform transform) {
/*  74 */     super(transform);
/*     */   }
/*     */   
/*     */   public AffineTransform2D(double m00, double m10, double m01, double m11, double m02, double m12) {
/*  86 */     super(m00, m10, m01, m11, m02, m12);
/*     */   }
/*     */   
/*     */   protected final void checkPermission() throws UnsupportedOperationException {
/*  95 */     super.checkPermission();
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 106 */     return ProjectiveTransform.getParameterValues(getMatrix());
/*     */   }
/*     */   
/*     */   public final int getSourceDimensions() {
/* 113 */     return 2;
/*     */   }
/*     */   
/*     */   public final int getTargetDimensions() {
/* 120 */     return 2;
/*     */   }
/*     */   
/*     */   public DirectPosition transform(DirectPosition ptSrc, DirectPosition ptDst) {
/*     */     GeneralDirectPosition generalDirectPosition;
/* 127 */     if (ptDst == null) {
/* 128 */       generalDirectPosition = new GeneralDirectPosition(2);
/*     */     } else {
/* 130 */       int dimension = generalDirectPosition.getDimension();
/* 131 */       if (dimension != 2)
/* 132 */         throw new MismatchedDimensionException(Errors.format(94, "ptDst", Integer.valueOf(dimension), Integer.valueOf(2))); 
/*     */     } 
/* 136 */     double[] array = ptSrc.getCoordinate();
/* 137 */     transform(array, 0, array, 0, 1);
/* 138 */     generalDirectPosition.setOrdinate(0, array[0]);
/* 139 */     generalDirectPosition.setOrdinate(1, array[1]);
/* 140 */     return (DirectPosition)generalDirectPosition;
/*     */   }
/*     */   
/*     */   public Shape createTransformedShape(Shape shape) {
/* 151 */     return transform((AffineTransform)this, shape, false);
/*     */   }
/*     */   
/*     */   public Matrix getMatrix() {
/* 158 */     return (Matrix)new Matrix3((AffineTransform)this);
/*     */   }
/*     */   
/*     */   public Matrix derivative(Point2D point) {
/* 166 */     return (Matrix)new Matrix2(getScaleX(), getShearX(), getShearY(), getScaleY());
/*     */   }
/*     */   
/*     */   public Matrix derivative(DirectPosition point) {
/* 175 */     return derivative((Point2D)null);
/*     */   }
/*     */   
/*     */   public MathTransform2D inverse() throws NoninvertibleTransformException {
/* 184 */     if (this.inverse == null)
/* 185 */       if (isIdentity()) {
/* 186 */         this.inverse = this;
/*     */       } else {
/*     */         try {
/* 188 */           synchronized (this) {
/* 189 */             this.inverse = new AffineTransform2D(createInverse());
/* 190 */             this.inverse.inverse = this;
/*     */           } 
/* 192 */         } catch (NoninvertibleTransformException exception) {
/* 193 */           throw new NoninvertibleTransformException(exception.getLocalizedMessage(), exception);
/*     */         } 
/*     */       }  
/* 196 */     return this.inverse;
/*     */   }
/*     */   
/*     */   public AffineTransform clone() {
/* 207 */     return new AffineTransform((AffineTransform)this);
/*     */   }
/*     */   
/*     */   public String formatWKT(Formatter formatter) {
/* 219 */     ParameterValueGroup parameters = getParameterValues();
/* 220 */     formatter.append(formatter.getName((IdentifiedObject)parameters.getDescriptor()));
/* 221 */     formatter.append((GeneralParameterValue)parameters);
/* 222 */     return "PARAM_MT";
/*     */   }
/*     */   
/*     */   public String toWKT() {
/* 229 */     int indentation = 2;
/*     */     try {
/* 231 */       indentation = Preferences.userNodeForPackage(Formattable.class).getInt("Indentation", indentation);
/* 233 */     } catch (SecurityException ignore) {}
/* 236 */     Formatter formatter = new Formatter(Symbols.DEFAULT, indentation);
/* 237 */     formatter.append((MathTransform)this);
/* 238 */     return formatter.toString();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 246 */     return toWKT();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 251 */     if (!(obj instanceof AffineTransform))
/* 252 */       return false; 
/* 255 */     AffineTransform a = (AffineTransform)obj;
/* 257 */     return (Utilities.equals(getScaleX(), a.getScaleX()) && Utilities.equals(getScaleY(), a.getScaleY()) && Utilities.equals(getShearX(), a.getShearX()) && Utilities.equals(getShearY(), a.getShearY()) && Utilities.equals(getTranslateX(), a.getTranslateX()) && Utilities.equals(getTranslateY(), a.getTranslateY()));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\AffineTransform2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */