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
/*     */ import javax.media.jai.util.Range;
/*     */ 
/*     */ public class ColorQuantizerDescriptor extends OperationDescriptorImpl {
/* 170 */   public static final ColorQuantizerType MEDIANCUT = new ColorQuantizerType("MEDIANCUT", 1);
/*     */   
/* 173 */   public static final ColorQuantizerType NEUQUANT = new ColorQuantizerType("NEUQUANT", 2);
/*     */   
/* 176 */   public static final ColorQuantizerType OCTTREE = new ColorQuantizerType("OCTTREE", 3);
/*     */   
/* 183 */   private static final String[][] resources = new String[][] { 
/* 183 */       { "GlobalName", "ColorQuantizer" }, { "LocalName", "ColorQuantizer" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("ColorQuantizerDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/ColorQuantizerDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion2") }, { "arg0Desc", JaiI18N.getString("ColorQuantizerDescriptor1") }, { "arg1Desc", JaiI18N.getString("ColorQuantizerDescriptor2") }, { "arg2Desc", JaiI18N.getString("ColorQuantizerDescriptor3") }, { "arg3Desc", JaiI18N.getString("ColorQuantizerDescriptor4") }, 
/* 183 */       { "arg4Desc", JaiI18N.getString("ColorQuantizerDescriptor5") }, { "arg5Desc", JaiI18N.getString("ColorQuantizerDescriptor6") } };
/*     */   
/* 199 */   private static final String[] paramNames = new String[] { "quantizationAlgorithm", "maxColorNum", "upperBound", "roi", "xPeriod", "yPeriod" };
/*     */   
/* 209 */   private static final Class[] paramClasses = new Class[] { ColorQuantizerType.class, Integer.class, Integer.class, ROI.class, Integer.class, Integer.class };
/*     */   
/* 219 */   private static final Object[] paramDefaults = new Object[] { MEDIANCUT, new Integer(256), null, null, new Integer(1), new Integer(1) };
/*     */   
/* 228 */   private static final String[] supportedModes = new String[] { "rendered" };
/*     */   
/*     */   public ColorQuantizerDescriptor() {
/* 234 */     super(resources, supportedModes, 1, paramNames, paramClasses, paramDefaults, null);
/*     */   }
/*     */   
/*     */   public Range getParamValueRange(int index) {
/* 244 */     switch (index) {
/*     */       case 1:
/*     */       case 2:
/*     */       case 4:
/*     */       case 5:
/* 249 */         return new Range(Integer.class, new Integer(1), null);
/*     */     } 
/* 251 */     return null;
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(String modeName, ParameterBlock args, StringBuffer msg) {
/* 269 */     if (args == null || msg == null)
/* 270 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 273 */     if (!super.validateParameters(modeName, args, msg))
/* 274 */       return false; 
/* 276 */     ColorQuantizerType algorithm = (ColorQuantizerType)args.getObjectParameter(0);
/* 278 */     if (algorithm != MEDIANCUT && algorithm != NEUQUANT && algorithm != OCTTREE) {
/* 280 */       msg.append(getName() + " " + JaiI18N.getString("ColorQuantizerDescriptor7"));
/* 282 */       return false;
/*     */     } 
/* 285 */     Integer secondOne = (Integer)args.getObjectParameter(2);
/* 286 */     if (secondOne == null) {
/* 287 */       int upperBound = 0;
/* 288 */       if (algorithm.equals(MEDIANCUT)) {
/* 289 */         upperBound = 32768;
/* 290 */       } else if (algorithm.equals(NEUQUANT)) {
/* 291 */         upperBound = 100;
/* 292 */       } else if (algorithm.equals(OCTTREE)) {
/* 293 */         upperBound = 65536;
/*     */       } 
/* 295 */       args.set(upperBound, 2);
/*     */     } 
/* 298 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, ColorQuantizerType algorithm, Integer maxColorNum, Integer upperBound, ROI roi, Integer xPeriod, Integer yPeriod, RenderingHints hints) {
/* 333 */     ParameterBlockJAI pb = new ParameterBlockJAI("ColorQuantizer", "rendered");
/* 337 */     pb.setSource("source0", source0);
/* 339 */     pb.setParameter("quantizationAlgorithm", algorithm);
/* 340 */     pb.setParameter("maxColorNum", maxColorNum);
/* 341 */     pb.setParameter("upperBound", upperBound);
/* 342 */     pb.setParameter("roi", roi);
/* 343 */     pb.setParameter("xPeriod", xPeriod);
/* 344 */     pb.setParameter("yPeriod", yPeriod);
/* 346 */     return JAI.create("ColorQuantizer", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\ColorQuantizerDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */