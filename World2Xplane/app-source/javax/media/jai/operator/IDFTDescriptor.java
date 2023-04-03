/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.EnumeratedParameter;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.PropertyGenerator;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class IDFTDescriptor extends OperationDescriptorImpl {
/* 141 */   public static final DFTScalingType SCALING_NONE = DFTDescriptor.SCALING_NONE;
/*     */   
/* 148 */   public static final DFTScalingType SCALING_UNITARY = DFTDescriptor.SCALING_UNITARY;
/*     */   
/* 155 */   public static final DFTScalingType SCALING_DIMENSIONS = DFTDescriptor.SCALING_DIMENSIONS;
/*     */   
/* 162 */   public static final DFTDataNature REAL_TO_COMPLEX = DFTDescriptor.REAL_TO_COMPLEX;
/*     */   
/* 168 */   public static final DFTDataNature COMPLEX_TO_COMPLEX = DFTDescriptor.COMPLEX_TO_COMPLEX;
/*     */   
/* 175 */   public static final DFTDataNature COMPLEX_TO_REAL = DFTDescriptor.COMPLEX_TO_REAL;
/*     */   
/* 182 */   private static final String[][] resources = new String[][] { { "GlobalName", "IDFT" }, { "LocalName", "IDFT" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("IDFTDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/IDFTDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion2") }, { "arg0Desc", JaiI18N.getString("IDFTDescriptor1") }, { "arg1Desc", JaiI18N.getString("IDFTDescriptor2") } };
/*     */   
/* 194 */   private static final Class[] paramClasses = new Class[] { DFTScalingType.class, DFTDataNature.class };
/*     */   
/* 199 */   private static final String[] paramNames = new String[] { "scalingType", "dataNature" };
/*     */   
/* 204 */   private static final Object[] paramDefaults = new Object[] { SCALING_DIMENSIONS, COMPLEX_TO_REAL };
/*     */   
/* 208 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public IDFTDescriptor() {
/* 215 */     super(resources, supportedModes, 1, paramNames, paramClasses, paramDefaults, null);
/*     */   }
/*     */   
/*     */   public boolean validateArguments(String modeName, ParameterBlock args, StringBuffer msg) {
/* 235 */     if (!super.validateArguments(modeName, args, msg))
/* 236 */       return false; 
/* 239 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 240 */       return true; 
/* 243 */     EnumeratedParameter dataNature = (EnumeratedParameter)args.getObjectParameter(1);
/* 246 */     if (!dataNature.equals(REAL_TO_COMPLEX)) {
/* 247 */       RenderedImage src = args.getRenderedSource(0);
/* 249 */       if (src.getSampleModel().getNumBands() % 2 != 0) {
/* 250 */         msg.append(getName() + " " + JaiI18N.getString("IDFTDescriptor5"));
/* 252 */         return false;
/*     */       } 
/*     */     } 
/* 256 */     return true;
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators(String modeName) {
/* 266 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 267 */     pg[0] = (PropertyGenerator)new IDFTPropertyGenerator();
/* 268 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, DFTScalingType scalingType, DFTDataNature dataNature, RenderingHints hints) {
/* 297 */     ParameterBlockJAI pb = new ParameterBlockJAI("IDFT", "rendered");
/* 301 */     pb.setSource("source0", source0);
/* 303 */     pb.setParameter("scalingType", scalingType);
/* 304 */     pb.setParameter("dataNature", dataNature);
/* 306 */     return JAI.create("IDFT", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, DFTScalingType scalingType, DFTDataNature dataNature, RenderingHints hints) {
/* 334 */     ParameterBlockJAI pb = new ParameterBlockJAI("IDFT", "renderable");
/* 338 */     pb.setSource("source0", source0);
/* 340 */     pb.setParameter("scalingType", scalingType);
/* 341 */     pb.setParameter("dataNature", dataNature);
/* 343 */     return JAI.createRenderable("IDFT", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\IDFTDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */