/*     */ package org.geotools.metadata.iso.extent;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.UndeclaredThrowableException;
/*     */ import java.util.Locale;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.geometry.Envelope;
/*     */ import org.opengis.metadata.extent.GeographicBoundingBox;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class GeographicBoundingBoxImpl extends GeographicExtentImpl implements GeographicBoundingBox {
/*     */   private static final long serialVersionUID = -3278089380004172514L;
/*     */   
/*     */   private static Method constructor;
/*     */   
/*     */   private static Method toString;
/*     */   
/*     */   public static final GeographicBoundingBox WORLD;
/*     */   
/*     */   private double westBoundLongitude;
/*     */   
/*     */   private double eastBoundLongitude;
/*     */   
/*     */   private double southBoundLatitude;
/*     */   
/*     */   private double northBoundLatitude;
/*     */   
/*     */   static {
/*  78 */     GeographicBoundingBoxImpl world = new GeographicBoundingBoxImpl(-180.0D, 180.0D, -90.0D, 90.0D);
/*  79 */     world.freeze();
/*  80 */     WORLD = world;
/*     */   }
/*     */   
/*     */   public GeographicBoundingBoxImpl() {}
/*     */   
/*     */   public GeographicBoundingBoxImpl(GeographicBoundingBox box) {
/* 129 */     setBounds(box);
/*     */   }
/*     */   
/*     */   public GeographicBoundingBoxImpl(Envelope envelope) throws TransformException {
/* 154 */     super(true);
/* 155 */     if (constructor == null)
/* 157 */       constructor = getMethod("copy", new Class[] { Envelope.class, GeographicBoundingBoxImpl.class }); 
/*     */     try {
/* 161 */       invoke(constructor, new Object[] { envelope, this });
/* 162 */     } catch (InvocationTargetException exception) {
/* 163 */       Throwable cause = exception.getTargetException();
/* 164 */       if (cause instanceof TransformException)
/* 165 */         throw (TransformException)cause; 
/* 167 */       throw new UndeclaredThrowableException(cause);
/*     */     } 
/*     */   }
/*     */   
/*     */   public GeographicBoundingBoxImpl(Rectangle2D bounds) {
/* 178 */     this(bounds.getMinX(), bounds.getMaxX(), bounds.getMinY(), bounds.getMaxY());
/*     */   }
/*     */   
/*     */   public GeographicBoundingBoxImpl(double westBoundLongitude, double eastBoundLongitude, double southBoundLatitude, double northBoundLatitude) {
/* 200 */     super(true);
/* 201 */     setBounds(westBoundLongitude, eastBoundLongitude, southBoundLatitude, northBoundLatitude);
/*     */   }
/*     */   
/*     */   public double getWestBoundLongitude() {
/* 213 */     return this.westBoundLongitude;
/*     */   }
/*     */   
/*     */   public synchronized void setWestBoundLongitude(double newValue) {
/* 224 */     checkWritePermission();
/* 225 */     this.westBoundLongitude = newValue;
/*     */   }
/*     */   
/*     */   public double getEastBoundLongitude() {
/* 236 */     return this.eastBoundLongitude;
/*     */   }
/*     */   
/*     */   public synchronized void setEastBoundLongitude(double newValue) {
/* 247 */     checkWritePermission();
/* 248 */     this.eastBoundLongitude = newValue;
/*     */   }
/*     */   
/*     */   public double getSouthBoundLatitude() {
/* 259 */     return this.southBoundLatitude;
/*     */   }
/*     */   
/*     */   public synchronized void setSouthBoundLatitude(double newValue) {
/* 270 */     checkWritePermission();
/* 271 */     this.southBoundLatitude = newValue;
/*     */   }
/*     */   
/*     */   public double getNorthBoundLatitude() {
/* 282 */     return this.northBoundLatitude;
/*     */   }
/*     */   
/*     */   public synchronized void setNorthBoundLatitude(double newValue) {
/* 293 */     checkWritePermission();
/* 294 */     this.northBoundLatitude = newValue;
/*     */   }
/*     */   
/*     */   public synchronized void setBounds(double westBoundLongitude, double eastBoundLongitude, double southBoundLatitude, double northBoundLatitude) {
/* 317 */     checkWritePermission();
/* 318 */     this.westBoundLongitude = westBoundLongitude;
/* 319 */     this.eastBoundLongitude = eastBoundLongitude;
/* 320 */     this.southBoundLatitude = southBoundLatitude;
/* 321 */     this.northBoundLatitude = northBoundLatitude;
/*     */   }
/*     */   
/*     */   public void setBounds(GeographicBoundingBox box) {
/* 332 */     ensureNonNull("box", box);
/* 333 */     setInclusion(box.getInclusion());
/* 334 */     setBounds(box.getWestBoundLongitude(), box.getEastBoundLongitude(), box.getSouthBoundLatitude(), box.getNorthBoundLatitude());
/*     */   }
/*     */   
/*     */   public synchronized void add(GeographicBoundingBox box) {
/* 351 */     checkWritePermission();
/* 352 */     double xmin = box.getWestBoundLongitude();
/* 353 */     double xmax = box.getEastBoundLongitude();
/* 354 */     double ymin = box.getSouthBoundLatitude();
/* 355 */     double ymax = box.getNorthBoundLatitude();
/* 361 */     Boolean inc1 = getInclusion();
/* 361 */     ensureNonNull("inclusion", inc1);
/* 362 */     Boolean inc2 = box.getInclusion();
/* 362 */     ensureNonNull("inclusion", inc2);
/* 363 */     if (inc1.booleanValue() == inc2.booleanValue()) {
/* 364 */       if (xmin < this.westBoundLongitude)
/* 364 */         this.westBoundLongitude = xmin; 
/* 365 */       if (xmax > this.eastBoundLongitude)
/* 365 */         this.eastBoundLongitude = xmax; 
/* 366 */       if (ymin < this.southBoundLatitude)
/* 366 */         this.southBoundLatitude = ymin; 
/* 367 */       if (ymax > this.northBoundLatitude)
/* 367 */         this.northBoundLatitude = ymax; 
/*     */     } else {
/* 369 */       if (ymin <= this.southBoundLatitude && ymax >= this.northBoundLatitude) {
/* 370 */         if (xmin > this.westBoundLongitude)
/* 370 */           this.westBoundLongitude = xmin; 
/* 371 */         if (xmax < this.eastBoundLongitude)
/* 371 */           this.eastBoundLongitude = xmax; 
/*     */       } 
/* 373 */       if (xmin <= this.westBoundLongitude && xmax >= this.eastBoundLongitude) {
/* 374 */         if (ymin > this.southBoundLatitude)
/* 374 */           this.southBoundLatitude = ymin; 
/* 375 */         if (ymax < this.northBoundLatitude)
/* 375 */           this.northBoundLatitude = ymax; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void intersect(GeographicBoundingBox box) {
/* 389 */     checkWritePermission();
/* 390 */     Boolean inc1 = getInclusion();
/* 390 */     ensureNonNull("inclusion", inc1);
/* 391 */     Boolean inc2 = box.getInclusion();
/* 391 */     ensureNonNull("inclusion", inc2);
/* 392 */     if (inc1.booleanValue() != inc2.booleanValue())
/* 393 */       throw new IllegalArgumentException(Errors.format(57, "box")); 
/* 395 */     double xmin = box.getWestBoundLongitude();
/* 396 */     double xmax = box.getEastBoundLongitude();
/* 397 */     double ymin = box.getSouthBoundLatitude();
/* 398 */     double ymax = box.getNorthBoundLatitude();
/* 399 */     if (xmin > this.westBoundLongitude)
/* 399 */       this.westBoundLongitude = xmin; 
/* 400 */     if (xmax < this.eastBoundLongitude)
/* 400 */       this.eastBoundLongitude = xmax; 
/* 401 */     if (ymin > this.southBoundLatitude)
/* 401 */       this.southBoundLatitude = ymin; 
/* 402 */     if (ymax < this.northBoundLatitude)
/* 402 */       this.northBoundLatitude = ymax; 
/* 403 */     if (this.westBoundLongitude > this.eastBoundLongitude)
/* 404 */       this.westBoundLongitude = this.eastBoundLongitude = 0.5D * (this.westBoundLongitude + this.eastBoundLongitude); 
/* 406 */     if (this.southBoundLatitude > this.northBoundLatitude)
/* 407 */       this.southBoundLatitude = this.northBoundLatitude = 0.5D * (this.southBoundLatitude + this.northBoundLatitude); 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 420 */     return (this.eastBoundLongitude <= this.westBoundLongitude || this.northBoundLatitude <= this.southBoundLatitude);
/*     */   }
/*     */   
/*     */   public synchronized boolean equals(Object object) {
/* 431 */     if (object == this)
/* 432 */       return true; 
/* 435 */     if (object != null && object.getClass().equals(GeographicBoundingBoxImpl.class)) {
/* 436 */       GeographicBoundingBoxImpl that = (GeographicBoundingBoxImpl)object;
/* 437 */       return (Utilities.equals(getInclusion(), that.getInclusion()) && Double.doubleToLongBits(this.southBoundLatitude) == Double.doubleToLongBits(that.southBoundLatitude) && Double.doubleToLongBits(this.northBoundLatitude) == Double.doubleToLongBits(that.northBoundLatitude) && Double.doubleToLongBits(this.eastBoundLongitude) == Double.doubleToLongBits(that.eastBoundLongitude) && Double.doubleToLongBits(this.westBoundLongitude) == Double.doubleToLongBits(that.westBoundLongitude));
/*     */     } 
/* 447 */     return super.equals(object);
/*     */   }
/*     */   
/*     */   public synchronized int hashCode() {
/* 457 */     if (!getClass().equals(GeographicBoundingBoxImpl.class))
/* 458 */       return super.hashCode(); 
/* 460 */     Boolean inclusion = getInclusion();
/* 461 */     int code = (inclusion != null) ? inclusion.hashCode() : 0;
/* 462 */     code += hashCode(this.southBoundLatitude);
/* 463 */     code += hashCode(this.northBoundLatitude);
/* 464 */     code += hashCode(this.eastBoundLongitude);
/* 465 */     code += hashCode(this.westBoundLongitude);
/* 466 */     return code;
/*     */   }
/*     */   
/*     */   private static int hashCode(double value) {
/* 473 */     long code = Double.doubleToLongBits(value);
/* 474 */     return (int)code ^ (int)(code >>> 32L);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 482 */     return toString(this, "DD°MM'SS.s\"", (Locale)null);
/*     */   }
/*     */   
/*     */   public static String toString(GeographicBoundingBox box, String pattern, Locale locale) {
/* 500 */     if (toString == null)
/* 502 */       toString = getMethod("toString", new Class[] { GeographicBoundingBox.class, String.class, Locale.class }); 
/*     */     try {
/* 506 */       return String.valueOf(invoke(toString, new Object[] { box, pattern, locale }));
/* 507 */     } catch (InvocationTargetException exception) {
/* 508 */       throw new UndeclaredThrowableException(exception.getTargetException());
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Method getMethod(String name, Class<?>[] arguments) {
/*     */     try {
/* 518 */       return Class.forName("org.geotools.resources.BoundingBoxes").getMethod(name, arguments);
/* 519 */     } catch (ClassNotFoundException exception) {
/* 520 */       throw new UnsupportedOperationException(Errors.format(98, "referencing"), exception);
/* 522 */     } catch (NoSuchMethodException exception) {
/* 524 */       throw new AssertionError(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Object invoke(Method method, Object[] arguments) throws InvocationTargetException {
/*     */     try {
/* 535 */       return method.invoke(null, arguments);
/* 536 */     } catch (IllegalAccessException exception) {
/* 538 */       throw new AssertionError(exception);
/* 539 */     } catch (InvocationTargetException exception) {
/* 540 */       Throwable cause = exception.getTargetException();
/* 541 */       if (cause instanceof RuntimeException)
/* 542 */         throw (RuntimeException)cause; 
/* 544 */       if (cause instanceof Error)
/* 545 */         throw (Error)cause; 
/* 547 */       throw exception;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\extent\GeographicBoundingBoxImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */