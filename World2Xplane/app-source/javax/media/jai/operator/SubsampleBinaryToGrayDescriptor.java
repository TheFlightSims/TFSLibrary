/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.PixelAccessor;
/*     */ import javax.media.jai.PropertyGenerator;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class SubsampleBinaryToGrayDescriptor extends OperationDescriptorImpl {
/* 192 */   private static final String[][] resources = new String[][] { { "GlobalName", "SubsampleBinaryToGray" }, { "LocalName", "SubsampleBinaryToGray" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("SubsampleBinaryToGray0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/SubsampleBinaryToGrayDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("SubsampleBinaryToGray1") }, { "arg1Desc", JaiI18N.getString("SubsampleBinaryToGray2") } };
/*     */   
/* 204 */   private static final Class[] paramClasses = new Class[] { Float.class, Float.class };
/*     */   
/* 209 */   private static final String[] paramNames = new String[] { "xScale", "yScale" };
/*     */   
/* 214 */   private static final Object[] paramDefaults = new Object[] { new Float(1.0F), new Float(1.0F) };
/*     */   
/*     */   public SubsampleBinaryToGrayDescriptor() {
/* 220 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/* 225 */     return true;
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators() {
/* 235 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 236 */     pg[0] = (PropertyGenerator)new SubsampleBinaryToGrayPropertyGenerator();
/* 237 */     return pg;
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(ParameterBlock args, StringBuffer msg) {
/* 252 */     if (!super.validateParameters(args, msg))
/* 253 */       return false; 
/* 259 */     RenderedImage src = (RenderedImage)args.getSource(0);
/* 261 */     PixelAccessor srcPA = new PixelAccessor(src);
/* 262 */     if (!srcPA.isPacked || !srcPA.isMultiPixelPackedSM) {
/* 263 */       msg.append(getName() + " " + JaiI18N.getString("SubsampleBinaryToGray3"));
/* 265 */       return false;
/*     */     } 
/* 268 */     float xScale = args.getFloatParameter(0);
/* 269 */     float yScale = args.getFloatParameter(1);
/* 270 */     if (xScale <= 0.0F || yScale <= 0.0F || xScale > 1.0F || yScale > 1.0F) {
/* 271 */       msg.append(getName() + " " + JaiI18N.getString("SubsampleBinaryToGray1") + " or " + JaiI18N.getString("SubsampleBinaryToGray2"));
/* 274 */       return false;
/*     */     } 
/* 277 */     return true;
/*     */   }
/*     */   
/*     */   public Number getParamMinValue(int index) {
/* 289 */     if (index == 0 || index == 1)
/* 290 */       return new Float(0.0F); 
/* 292 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, Float xScale, Float yScale, RenderingHints hints) {
/* 322 */     ParameterBlockJAI pb = new ParameterBlockJAI("SubsampleBinaryToGray", "rendered");
/* 326 */     pb.setSource("source0", source0);
/* 328 */     pb.setParameter("xScale", xScale);
/* 329 */     pb.setParameter("yScale", yScale);
/* 331 */     return JAI.create("SubsampleBinaryToGray", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, Float xScale, Float yScale, RenderingHints hints) {
/* 359 */     ParameterBlockJAI pb = new ParameterBlockJAI("SubsampleBinaryToGray", "renderable");
/* 363 */     pb.setSource("source0", source0);
/* 365 */     pb.setParameter("xScale", xScale);
/* 366 */     pb.setParameter("yScale", yScale);
/* 368 */     return JAI.createRenderable("SubsampleBinaryToGray", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\SubsampleBinaryToGrayDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */