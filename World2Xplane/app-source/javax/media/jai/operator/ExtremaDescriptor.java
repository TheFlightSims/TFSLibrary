/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.ROI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class ExtremaDescriptor extends OperationDescriptorImpl {
/* 140 */   private static final String[][] resources = new String[][] { 
/* 140 */       { "GlobalName", "Extrema" }, { "LocalName", "Extrema" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("ExtremaDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/ExtremaDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("ExtremaDescriptor1") }, { "arg1Desc", JaiI18N.getString("ExtremaDescriptor2") }, { "arg2Desc", JaiI18N.getString("ExtremaDescriptor3") }, { "arg3Desc", JaiI18N.getString("ExtremaDescriptor4") }, 
/* 140 */       { "arg4Desc", JaiI18N.getString("ExtremaDescriptor5") } };
/*     */   
/* 155 */   private static final String[] paramNames = new String[] { "roi", "xPeriod", "yPeriod", "saveLocations", "maxRuns" };
/*     */   
/* 160 */   private static final Class[] paramClasses = new Class[] { ROI.class, Integer.class, Integer.class, Boolean.class, Integer.class };
/*     */   
/* 169 */   private static final Object[] paramDefaults = new Object[] { null, new Integer(1), new Integer(1), Boolean.FALSE, new Integer(1) };
/*     */   
/*     */   public ExtremaDescriptor() {
/* 176 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public Number getParamMinValue(int index) {
/* 184 */     if (index == 0 || index == 3)
/* 185 */       return null; 
/* 186 */     if (index == 1 || index == 2 || index == 4)
/* 187 */       return new Integer(1); 
/* 189 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, ROI roi, Integer xPeriod, Integer yPeriod, Boolean saveLocations, Integer maxRuns, RenderingHints hints) {
/* 228 */     ParameterBlockJAI pb = new ParameterBlockJAI("Extrema", "rendered");
/* 232 */     pb.setSource("source0", source0);
/* 234 */     pb.setParameter("roi", roi);
/* 235 */     pb.setParameter("xPeriod", xPeriod);
/* 236 */     pb.setParameter("yPeriod", yPeriod);
/* 237 */     pb.setParameter("saveLocations", saveLocations);
/* 238 */     pb.setParameter("maxRuns", maxRuns);
/* 240 */     return JAI.create("Extrema", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\ExtremaDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */