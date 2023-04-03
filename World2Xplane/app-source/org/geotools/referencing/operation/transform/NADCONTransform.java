/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Serializable;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.util.prefs.Preferences;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.parameter.DefaultParameterDescriptor;
/*     */ import org.geotools.parameter.Parameter;
/*     */ import org.geotools.parameter.ParameterGroup;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.referencing.factory.gridshift.GridShiftLocator;
/*     */ import org.geotools.referencing.factory.gridshift.NADCONGridShiftFactory;
/*     */ import org.geotools.referencing.factory.gridshift.NADConGridShift;
/*     */ import org.geotools.referencing.operation.MathTransformProvider;
/*     */ import org.geotools.referencing.operation.builder.LocalizationGrid;
/*     */ import org.geotools.resources.Arguments;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.NoSuchIdentifierException;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransform2D;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ import org.opengis.referencing.operation.Transformation;
/*     */ 
/*     */ public class NADCONTransform extends AbstractMathTransform implements MathTransform2D, Serializable {
/*     */   private static final long serialVersionUID = -4707304160205218546L;
/*     */   
/* 140 */   private static NADCONGridShiftFactory FACTORY = new NADCONGridShiftFactory();
/*     */   
/*     */   private static final String GRID_LOCATION = "Grid location";
/*     */   
/*     */   private static final String DEFAULT_GRID_LOCATION = ".";
/*     */   
/*     */   private static final double TOL = 5.0E-10D;
/*     */   
/*     */   private static final int MAX_ITER = 10;
/*     */   
/*     */   private static final double SEC_2_DEG = 3600.0D;
/*     */   
/*     */   private final URI latGridName;
/*     */   
/*     */   private final URI longGridName;
/*     */   
/*     */   private LocalizationGrid gridShift;
/*     */   
/*     */   private MathTransform gridShiftTransform;
/*     */   
/*     */   private transient MathTransform2D inverse;
/*     */   
/*     */   NADConGridShift grid;
/*     */   
/*     */   public NADCONTransform(URI latGridName, URI longGridName) throws ParameterNotFoundException, FactoryException {
/* 221 */     if (latGridName == null)
/* 222 */       throw new NoSuchIdentifierException("Latitud grid shift file name is null", null); 
/* 225 */     if (longGridName == null)
/* 226 */       throw new NoSuchIdentifierException("Latitud grid shift file name is null", null); 
/* 229 */     this.latGridName = latGridName;
/* 230 */     this.longGridName = longGridName;
/* 232 */     URL latGridURL = locateGrid(latGridName);
/* 233 */     URL longGridURL = locateGrid(longGridName);
/* 235 */     this.grid = FACTORY.loadGridShift(latGridURL, longGridURL);
/* 236 */     this.gridShiftTransform = (MathTransform)this.grid.getMathTransform();
/*     */   }
/*     */   
/*     */   protected URL locateGrid(URI uri) throws FactoryException {
/* 240 */     String grid = uri.toString();
/* 241 */     for (GridShiftLocator locator : ReferencingFactoryFinder.getGridShiftLocators(null)) {
/* 242 */       URL result = locator.locateGrid(grid);
/* 243 */       if (result != null)
/* 244 */         return result; 
/*     */     } 
/* 248 */     throw new FactoryException("Could not locate grid file " + grid);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 256 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 266 */     Parameter parameter1 = new Parameter(Provider.LAT_DIFF_FILE);
/* 267 */     parameter1.setValue(this.latGridName);
/* 269 */     Parameter parameter2 = new Parameter(Provider.LONG_DIFF_FILE);
/* 270 */     parameter2.setValue(this.longGridName);
/* 272 */     return (ParameterValueGroup)new ParameterGroup(getParameterDescriptors(), new GeneralParameterValue[] { (GeneralParameterValue)parameter1, (GeneralParameterValue)parameter2 });
/*     */   }
/*     */   
/*     */   public int getSourceDimensions() {
/* 283 */     return 2;
/*     */   }
/*     */   
/*     */   public int getTargetDimensions() {
/* 292 */     return 2;
/*     */   }
/*     */   
/*     */   public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) throws TransformException {
/* 321 */     int step = 0;
/* 323 */     if (srcPts == dstPts && srcOff < dstOff && srcOff + numPts * getSourceDimensions() > dstOff) {
/* 325 */       step = -getSourceDimensions();
/* 326 */       srcOff -= (numPts - 1) * step;
/* 327 */       dstOff -= (numPts - 1) * step;
/*     */     } 
/* 330 */     while (--numPts >= 0) {
/* 331 */       double x = srcPts[srcOff++];
/* 332 */       double y = srcPts[srcOff++];
/* 335 */       if (x < this.grid.getMinX() || x > this.grid.getMaxX() || y < this.grid.getMinY() || y > this.grid.getMaxY())
/* 336 */         throw new TransformException("Point (" + x + " " + y + ") is not outside of ((" + this.grid.getMinX() + " " + this.grid.getMinY() + ")(" + this.grid.getMaxX() + " " + this.grid.getMaxY() + "))"); 
/* 341 */       double xgrid = (x - this.grid.getMinX()) / this.grid.getDx();
/* 342 */       double ygrid = (y - this.grid.getMinY()) / this.grid.getDy();
/* 343 */       double[] array = { xgrid, ygrid };
/* 347 */       this.gridShiftTransform.transform(array, 0, array, 0, 1);
/* 349 */       dstPts[dstOff++] = x - array[0] / 3600.0D;
/* 350 */       dstPts[dstOff++] = y + array[1] / 3600.0D;
/* 351 */       srcOff += step;
/* 352 */       dstOff += step;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void inverseTransform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) throws TransformException {
/* 377 */     int step = 0;
/* 379 */     if (srcPts == dstPts && srcOff < dstOff && srcOff + numPts * getSourceDimensions() > dstOff) {
/* 381 */       step = -getSourceDimensions();
/* 382 */       srcOff -= (numPts - 1) * step;
/* 383 */       dstOff -= (numPts - 1) * step;
/*     */     } 
/* 386 */     while (--numPts >= 0) {
/* 387 */       double x = srcPts[srcOff++];
/* 388 */       double y = srcPts[srcOff++];
/* 389 */       double xtemp = x;
/* 390 */       double ytemp = y;
/* 392 */       int i = 10;
/*     */       while (true) {
/* 393 */         double[] array = { xtemp, ytemp };
/* 394 */         transform(array, 0, array, 0, 1);
/* 395 */         double xdif = array[0] - x;
/* 396 */         double ydif = array[1] - y;
/* 398 */         if (Math.abs(xdif) > 5.0E-10D)
/* 399 */           xtemp -= xdif; 
/* 401 */         if (Math.abs(ydif) > 5.0E-10D)
/* 402 */           ytemp -= ydif; 
/* 405 */         if (Math.abs(xdif) <= 5.0E-10D && Math.abs(ydif) <= 5.0E-10D) {
/* 406 */           dstPts[dstOff++] = xtemp;
/* 407 */           dstPts[dstOff++] = ytemp;
/*     */           break;
/*     */         } 
/* 410 */         if (--i < 0)
/* 411 */           throw new TransformException(Errors.format(129)); 
/*     */       } 
/* 415 */       srcOff += step;
/* 416 */       dstOff += step;
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized MathTransform2D inverse() {
/* 427 */     if (this.inverse == null)
/* 428 */       this.inverse = new Inverse(); 
/* 430 */     return this.inverse;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 435 */     return this.grid.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 440 */     if (object == this)
/* 442 */       return true; 
/* 445 */     if (super.equals(object)) {
/* 446 */       NADCONTransform that = (NADCONTransform)object;
/* 448 */       return this.grid.equals(that.grid);
/*     */     } 
/* 450 */     return false;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 474 */     Arguments arguments = new Arguments(args);
/* 475 */     PrintWriter out = arguments.out;
/* 476 */     Preferences prefs = Preferences.userNodeForPackage(NADCONTransform.class);
/* 478 */     if (args.length == 1) {
/* 479 */       if (args[0].equalsIgnoreCase("default")) {
/* 480 */         prefs.remove("Grid location");
/*     */       } else {
/* 482 */         prefs.put("Grid location", args[0]);
/*     */       } 
/*     */       return;
/*     */     } 
/* 487 */     String location = prefs.get("Grid location", ".");
/* 489 */     out.println("Usage: java org.geotools.referencing.operation.transform.NADCONTransform <defalult grid file location (path)>");
/* 492 */     out.print("Grid location: \"");
/* 493 */     out.print(location);
/* 494 */     out.println('"');
/*     */   }
/*     */   
/*     */   private final class Inverse extends AbstractMathTransform.Inverse implements MathTransform2D, Serializable {
/*     */     private static final long serialVersionUID = -4707304160205218546L;
/*     */     
/*     */     public ParameterValueGroup getParameterValues() {
/* 526 */       return null;
/*     */     }
/*     */     
/*     */     public void transform(double[] source, int srcOffset, double[] dest, int dstOffset, int length) throws TransformException {
/* 544 */       NADCONTransform.this.inverseTransform(source, srcOffset, dest, dstOffset, length);
/*     */     }
/*     */     
/*     */     public MathTransform2D inverse() {
/* 553 */       return (MathTransform2D)super.inverse();
/*     */     }
/*     */     
/*     */     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 565 */       in.defaultReadObject();
/* 566 */       NADCONTransform.this.inverse = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Provider extends MathTransformProvider {
/*     */     private static final long serialVersionUID = -4707304160205218546L;
/*     */     
/* 587 */     public static final ParameterDescriptor LAT_DIFF_FILE = (ParameterDescriptor)new DefaultParameterDescriptor("Latitude difference file", URI.class, null, null);
/*     */     
/* 594 */     public static final ParameterDescriptor LONG_DIFF_FILE = (ParameterDescriptor)new DefaultParameterDescriptor("Longitude difference file", URI.class, null, null);
/*     */     
/* 600 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "NADCON"), new NamedIdentifier(Citations.EPSG, "NADCON"), new NamedIdentifier(Citations.EPSG, "9613"), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(145)) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { LAT_DIFF_FILE, LONG_DIFF_FILE });
/*     */     
/*     */     public Provider() {
/* 615 */       super(2, 2, PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<Transformation> getOperationType() {
/* 623 */       return Transformation.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup values) throws ParameterNotFoundException, FactoryException {
/* 640 */       return new NADCONTransform((URI)getParameter(LAT_DIFF_FILE, values).getValue(), (URI)getParameter(LONG_DIFF_FILE, values).getValue());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\NADCONTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */