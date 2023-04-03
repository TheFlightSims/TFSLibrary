/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.ROI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class MosaicDescriptor extends OperationDescriptorImpl {
/* 273 */   public static final MosaicType MOSAIC_TYPE_BLEND = new MosaicType("MOSAIC_TYPE_BLEND", 1);
/*     */   
/* 279 */   public static final MosaicType MOSAIC_TYPE_OVERLAY = new MosaicType("MOSAIC_TYPE_OVERLAY", 0);
/*     */   
/* 286 */   private static final String[][] resources = new String[][] { 
/* 286 */       { "GlobalName", "Mosaic" }, { "LocalName", "Mosaic" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("MosaicDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/MosaicDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("MosaicDescriptor1") }, { "arg1Desc", JaiI18N.getString("MosaicDescriptor2") }, { "arg2Desc", JaiI18N.getString("MosaicDescriptor3") }, { "arg3Desc", JaiI18N.getString("MosaicDescriptor4") }, 
/* 286 */       { "arg4Desc", JaiI18N.getString("MosaicDescriptor5") } };
/*     */   
/* 301 */   private static final Class[] paramClasses = new Class[] { MosaicType.class, (array$Ljavax$media$jai$PlanarImage == null) ? (array$Ljavax$media$jai$PlanarImage = class$("[Ljavax.media.jai.PlanarImage;")) : array$Ljavax$media$jai$PlanarImage, (array$Ljavax$media$jai$ROI == null) ? (array$Ljavax$media$jai$ROI = class$("[Ljavax.media.jai.ROI;")) : array$Ljavax$media$jai$ROI, (array$$D == null) ? (array$$D = class$("[[D")) : array$$D, (array$D == null) ? (array$D = class$("[D")) : array$D };
/*     */   
/* 310 */   private static final String[] paramNames = new String[] { "mosaicType", "sourceAlpha", "sourceROI", "sourceThreshold", "backgroundValues" };
/*     */   
/* 319 */   private static final Object[] paramDefaults = new Object[] { MOSAIC_TYPE_OVERLAY, null, null, { { 1.0D } }, { 0.0D } };
/*     */   
/*     */   static Class array$Ljavax$media$jai$PlanarImage;
/*     */   
/*     */   static Class array$Ljavax$media$jai$ROI;
/*     */   
/*     */   static Class array$$D;
/*     */   
/*     */   static Class array$D;
/*     */   
/*     */   public MosaicDescriptor() {
/* 329 */     super(resources, new String[] { "rendered" }, 0, paramNames, paramClasses, paramDefaults, null);
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage[] sources, MosaicType mosaicType, PlanarImage[] sourceAlpha, ROI[] sourceROI, double[][] sourceThreshold, double[] backgroundValues, RenderingHints hints) {
/* 374 */     ParameterBlockJAI pb = new ParameterBlockJAI("Mosaic", "rendered");
/* 378 */     int numSources = sources.length;
/* 379 */     for (int i = 0; i < numSources; i++)
/* 380 */       pb.addSource(sources[i]); 
/* 383 */     pb.setParameter("mosaicType", mosaicType);
/* 384 */     pb.setParameter("sourceAlpha", sourceAlpha);
/* 385 */     pb.setParameter("sourceROI", sourceROI);
/* 386 */     pb.setParameter("sourceThreshold", sourceThreshold);
/* 387 */     pb.setParameter("backgroundValues", backgroundValues);
/* 389 */     return JAI.create("Mosaic", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\MosaicDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */