/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import au.com.objectix.jgridshift.GridShift;
/*     */ import au.com.objectix.jgridshift.GridShiftFile;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.Serializable;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.parameter.DefaultParameterDescriptor;
/*     */ import org.geotools.parameter.Parameter;
/*     */ import org.geotools.parameter.ParameterGroup;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.referencing.factory.gridshift.GridShiftLocator;
/*     */ import org.geotools.referencing.factory.gridshift.NTv2GridShiftFactory;
/*     */ import org.geotools.referencing.operation.MathTransformProvider;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValue;
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
/*     */ public class NTv2Transform extends AbstractMathTransform implements MathTransform2D, Serializable {
/*     */   private static final long serialVersionUID = -3082112044314062512L;
/*     */   
/*  74 */   protected static final Logger LOGGER = Logging.getLogger("org.geotools.referencing");
/*     */   
/*  79 */   private URI grid = null;
/*     */   
/*  82 */   private URL gridLocation = null;
/*     */   
/*     */   private GridShiftFile gridShift;
/*     */   
/*  92 */   private static NTv2GridShiftFactory FACTORY = new NTv2GridShiftFactory();
/*     */   
/*     */   private transient MathTransform2D inverse;
/*     */   
/*     */   public NTv2Transform(URI file) throws NoSuchIdentifierException {
/* 109 */     if (file == null)
/* 110 */       throw new NoSuchIdentifierException("No NTv2 Grid File specified.", null); 
/* 113 */     this.grid = file;
/* 115 */     this.gridLocation = locateGrid(this.grid.toString());
/* 116 */     if (this.gridLocation == null)
/* 117 */       throw new NoSuchIdentifierException("Could not locate NTv2 Grid File " + file, null); 
/* 121 */     if (!FACTORY.isNTv2Grid(this.gridLocation))
/* 122 */       throw new NoSuchIdentifierException("NTv2 Grid File not available.", file.toString()); 
/*     */   }
/*     */   
/*     */   URL locateGrid(String grid) {
/* 128 */     for (GridShiftLocator locator : ReferencingFactoryFinder.getGridShiftLocators(null)) {
/* 129 */       URL result = locator.locateGrid(grid);
/* 130 */       if (result != null)
/* 131 */         return result; 
/*     */     } 
/* 135 */     return null;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 143 */     return this.grid.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 160 */     if (object == this)
/* 160 */       return true; 
/* 162 */     if (object != null && getClass().equals(object.getClass())) {
/* 163 */       NTv2Transform that = (NTv2Transform)object;
/* 164 */       return Utilities.equals(getParameterValues(), that.getParameterValues());
/*     */     } 
/* 167 */     return false;
/*     */   }
/*     */   
/*     */   public synchronized MathTransform2D inverse() {
/* 177 */     if (this.inverse == null)
/* 178 */       this.inverse = new Inverse(); 
/* 180 */     return this.inverse;
/*     */   }
/*     */   
/*     */   public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) throws TransformException {
/* 208 */     bidirectionalTransform(srcPts, srcOff, dstPts, dstOff, numPts, true);
/*     */   }
/*     */   
/*     */   public void inverseTransform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) throws TransformException {
/* 228 */     bidirectionalTransform(srcPts, srcOff, dstPts, dstOff, numPts, false);
/*     */   }
/*     */   
/*     */   private void bidirectionalTransform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts, boolean forward) throws TransformException {
/* 251 */     if (this.gridShift == null)
/*     */       try {
/* 253 */         this.gridShift = FACTORY.createNTv2Grid(this.gridLocation);
/* 254 */       } catch (FactoryException e) {
/* 255 */         throw new TransformException("NTv2 Grid " + this.gridLocation + " Could not be created", e);
/*     */       }  
/*     */     try {
/* 261 */       GridShift shift = new GridShift();
/* 262 */       while (--numPts >= 0) {
/*     */         boolean shifted;
/* 263 */         shift.setLonPositiveEastDegrees(srcPts[srcOff++]);
/* 264 */         shift.setLatDegrees(srcPts[srcOff++]);
/* 265 */         if (forward) {
/* 266 */           shifted = this.gridShift.gridShiftForward(shift);
/*     */         } else {
/* 268 */           shifted = this.gridShift.gridShiftReverse(shift);
/*     */         } 
/* 270 */         if (shifted) {
/* 271 */           dstPts[dstOff++] = shift.getShiftedLonPositiveEastDegrees();
/* 272 */           dstPts[dstOff++] = shift.getShiftedLatDegrees();
/*     */           continue;
/*     */         } 
/* 274 */         if (LOGGER.isLoggable(Level.FINE))
/* 275 */           LOGGER.log(Level.FINE, "Point (" + srcPts[srcOff - 2] + ", " + srcPts[srcOff - 1] + ") is not covered by '" + this.grid + "' NTv2 grid," + " it will not be shifted."); 
/* 279 */         dstPts[dstOff++] = srcPts[srcOff - 2];
/* 280 */         dstPts[dstOff++] = srcPts[srcOff - 1];
/*     */       } 
/* 283 */     } catch (IOException e) {
/* 284 */       throw new TransformException(e.getLocalizedMessage(), e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getSourceDimensions() {
/* 290 */     return 2;
/*     */   }
/*     */   
/*     */   public int getTargetDimensions() {
/* 295 */     return 2;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 305 */     Parameter parameter = new Parameter((ParameterDescriptor)Provider.FILE);
/* 306 */     parameter.setValue(this.grid);
/* 308 */     return (ParameterValueGroup)new ParameterGroup(Provider.PARAMETERS, (GeneralParameterValue[])new ParameterValue[] { (ParameterValue)parameter });
/*     */   }
/*     */   
/*     */   private final class Inverse extends AbstractMathTransform.Inverse implements MathTransform2D, Serializable {
/*     */     private static final long serialVersionUID = -4707304160205218546L;
/*     */     
/*     */     public ParameterValueGroup getParameterValues() {
/* 339 */       return null;
/*     */     }
/*     */     
/*     */     public void transform(double[] source, int srcOffset, double[] dest, int dstOffset, int length) throws TransformException {
/* 357 */       NTv2Transform.this.inverseTransform(source, srcOffset, dest, dstOffset, length);
/*     */     }
/*     */     
/*     */     public MathTransform2D inverse() {
/* 366 */       return (MathTransform2D)super.inverse();
/*     */     }
/*     */     
/*     */     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 378 */       in.defaultReadObject();
/* 379 */       NTv2Transform.this.inverse = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Provider extends MathTransformProvider {
/*     */     private static final long serialVersionUID = -3710592152744574801L;
/*     */     
/* 396 */     public static final DefaultParameterDescriptor<URI> FILE = new DefaultParameterDescriptor(toMap((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.EPSG, "Latitude and longitude difference file"), new NamedIdentifier(Citations.EPSG, "8656") }), URI.class, null, null, null, null, null, true);
/*     */     
/* 406 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.EPSG, "NTv2"), new NamedIdentifier(Citations.EPSG, "9615") }(GeneralParameterDescriptor[])new ParameterDescriptor[] { (ParameterDescriptor)FILE });
/*     */     
/*     */     public Provider() {
/* 417 */       super(2, 2, PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<Transformation> getOperationType() {
/* 425 */       return Transformation.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup values) throws ParameterNotFoundException, FactoryException {
/* 442 */       return new NTv2Transform((URI)value((ParameterDescriptor)FILE, values));
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\NTv2Transform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */