/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import org.geotools.referencing.operation.matrix.XAffineTransform;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public final class TransformedShape extends AffineTransform implements Shape {
/*     */   public Shape shape;
/*     */   
/*  55 */   private final Point2D.Double point = new Point2D.Double();
/*     */   
/*  60 */   private final Rectangle2D.Double rectangle = new Rectangle2D.Double();
/*     */   
/*     */   public TransformedShape() {}
/*     */   
/*     */   public TransformedShape(Shape shape, AffineTransform at) {
/*  72 */     this.shape = shape;
/*  73 */     setTransform(at);
/*     */   }
/*     */   
/*     */   public void getMatrix(float[] matrix, int offset) {
/*  80 */     matrix[offset] = (float)getScaleX();
/*  81 */     matrix[++offset] = (float)getShearY();
/*  82 */     matrix[++offset] = (float)getShearX();
/*  83 */     matrix[++offset] = (float)getScaleY();
/*  84 */     matrix[++offset] = (float)getTranslateX();
/*  85 */     matrix[++offset] = (float)getTranslateY();
/*     */   }
/*     */   
/*     */   public void setTransform(float[] matrix, int offset) {
/*  97 */     setTransform(matrix[offset], matrix[++offset], matrix[++offset], matrix[++offset], matrix[++offset], matrix[++offset]);
/*     */   }
/*     */   
/*     */   public void setTransform(double[] matrix) {
/* 109 */     setTransform(matrix[0], matrix[1], matrix[2], matrix[3], matrix[4], matrix[5]);
/*     */   }
/*     */   
/*     */   public void scale(double s) {
/* 117 */     scale(s, s);
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 125 */     this.point.x = x;
/* 126 */     this.point.y = y;
/* 127 */     return contains(this.point);
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/*     */     try {
/* 136 */       return this.shape.contains(inverseTransform(p, this.point));
/* 137 */     } catch (NoninvertibleTransformException exception) {
/* 138 */       exceptionOccured(exception, "contains");
/* 139 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y, double width, double height) {
/* 148 */     this.rectangle.x = x;
/* 149 */     this.rectangle.y = y;
/* 150 */     this.rectangle.width = width;
/* 151 */     this.rectangle.height = height;
/* 152 */     return contains(this.rectangle);
/*     */   }
/*     */   
/*     */   public boolean contains(Rectangle2D r) {
/*     */     try {
/* 162 */       return this.shape.contains(XAffineTransform.inverseTransform(this, r, this.rectangle));
/* 164 */     } catch (NoninvertibleTransformException exception) {
/* 165 */       exceptionOccured(exception, "contains");
/* 166 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean intersects(double x, double y, double width, double height) {
/* 175 */     this.rectangle.x = x;
/* 176 */     this.rectangle.y = y;
/* 177 */     this.rectangle.width = width;
/* 178 */     this.rectangle.height = height;
/* 179 */     return intersects(this.rectangle);
/*     */   }
/*     */   
/*     */   public boolean intersects(Rectangle2D r) {
/*     */     try {
/* 189 */       return this.shape.intersects(XAffineTransform.inverseTransform(this, r, this.rectangle));
/* 191 */     } catch (NoninvertibleTransformException exception) {
/* 192 */       exceptionOccured(exception, "intersects");
/* 193 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Rectangle getBounds() {
/* 202 */     Rectangle2D rect = getBounds2D();
/* 203 */     int minx = (int)Math.floor(rect.getMinX());
/* 204 */     int miny = (int)Math.floor(rect.getMinY());
/* 205 */     int maxx = (int)Math.ceil(rect.getMaxX());
/* 206 */     int maxy = (int)Math.ceil(rect.getMaxY());
/* 207 */     return new Rectangle(minx, miny, maxx - minx, maxy - miny);
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 218 */     Rectangle2D rect = this.shape.getBounds2D();
/* 219 */     return XAffineTransform.transform(this, rect, null);
/*     */   }
/*     */   
/*     */   public PathIterator getPathIterator(AffineTransform at) {
/* 230 */     if (!isIdentity()) {
/* 231 */       if (at == null || at.isIdentity())
/* 232 */         return this.shape.getPathIterator(this); 
/* 234 */       at = new AffineTransform(at);
/* 235 */       at.concatenate(this);
/*     */     } 
/* 237 */     return this.shape.getPathIterator(at);
/*     */   }
/*     */   
/*     */   public PathIterator getPathIterator(AffineTransform at, double flatness) {
/* 247 */     if (!isIdentity()) {
/* 248 */       if (at == null || at.isIdentity())
/* 249 */         return this.shape.getPathIterator(this, flatness); 
/* 251 */       at = new AffineTransform(at);
/* 252 */       at.concatenate(this);
/*     */     } 
/* 254 */     return this.shape.getPathIterator(at, flatness);
/*     */   }
/*     */   
/*     */   private static void exceptionOccured(NoninvertibleTransformException exception, String method) {
/* 266 */     LogRecord record = new LogRecord(Level.WARNING, exception.getLocalizedMessage());
/* 268 */     record.setSourceClassName(TransformedShape.class.getName());
/* 269 */     record.setSourceMethodName(method);
/* 270 */     record.setThrown(exception);
/* 271 */     Logging.getLogger("org.geotools.renderer.lite").log(record);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\TransformedShape.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */