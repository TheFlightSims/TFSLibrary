/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.KernelJAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class RenderableDescriptor extends OperationDescriptorImpl {
/* 101 */   private static final float[] DEFAULT_KERNEL_1D = new float[] { 0.05F, 0.25F, 0.4F, 0.25F, 0.05F };
/*     */   
/* 108 */   private static final String[][] resources = new String[][] { 
/* 108 */       { "GlobalName", "Renderable" }, { "LocalName", "Renderable" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("RenderableDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/RenderableDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("RenderableDescriptor1") }, { "arg1Desc", JaiI18N.getString("RenderableDescriptor2") }, { "arg2Desc", JaiI18N.getString("RenderableDescriptor3") }, { "arg3Desc", JaiI18N.getString("RenderableDescriptor4") }, 
/* 108 */       { "arg4Desc", JaiI18N.getString("RenderableDescriptor5") } };
/*     */   
/* 123 */   private static final Class[] paramClasses = new Class[] { RenderedOp.class, Integer.class, Float.class, Float.class, Float.class };
/*     */   
/* 128 */   private static final String[] paramNames = new String[] { "downSampler", "maxLowResDim", "minX", "minY", "height" };
/*     */   
/* 133 */   private static final Object[] paramDefaults = new Object[] { null, new Integer(64), new Float(0.0F), new Float(0.0F), new Float(1.0F) };
/*     */   
/*     */   public RenderableDescriptor() {
/* 140 */     super(resources, null, new Class[] { RenderedImage.class }, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public boolean isRenderedSupported() {
/* 148 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/* 153 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(ParameterBlock args, StringBuffer msg) {
/* 160 */     if (args.getNumParameters() == 0 || args.getObjectParameter(0) == null) {
/* 169 */       ParameterBlock pb = new ParameterBlock();
/* 170 */       KernelJAI kernel = new KernelJAI(DEFAULT_KERNEL_1D.length, DEFAULT_KERNEL_1D.length, DEFAULT_KERNEL_1D.length / 2, DEFAULT_KERNEL_1D.length / 2, DEFAULT_KERNEL_1D, DEFAULT_KERNEL_1D);
/* 176 */       pb.add(kernel);
/* 177 */       BorderExtender extender = BorderExtender.createInstance(1);
/* 179 */       RenderingHints hints = JAI.getDefaultInstance().getRenderingHints();
/* 181 */       if (hints == null) {
/* 182 */         hints = new RenderingHints(JAI.KEY_BORDER_EXTENDER, extender);
/*     */       } else {
/* 184 */         hints.put(JAI.KEY_BORDER_EXTENDER, extender);
/*     */       } 
/* 187 */       RenderedOp filter = new RenderedOp("convolve", pb, hints);
/* 190 */       pb = new ParameterBlock();
/* 191 */       pb.addSource(filter);
/* 192 */       pb.add(new Float(0.5F)).add(new Float(0.5F));
/* 193 */       pb.add(new Float(0.0F)).add(new Float(0.0F));
/* 194 */       pb.add(Interpolation.getInstance(0));
/* 195 */       RenderedOp downSampler = new RenderedOp("scale", pb, null);
/* 196 */       args.set(downSampler, 0);
/*     */     } 
/* 200 */     if (!super.validateParameters(args, msg))
/* 201 */       return false; 
/* 205 */     if (args.getIntParameter(1) <= 0) {
/* 206 */       msg.append(getName() + " " + JaiI18N.getString("RenderableDescriptor6"));
/* 208 */       return false;
/*     */     } 
/* 209 */     if (args.getFloatParameter(4) <= 0.0F) {
/* 210 */       msg.append(getName() + " " + JaiI18N.getString("RenderableDescriptor7"));
/* 212 */       return false;
/*     */     } 
/* 215 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderedImage source0, RenderedOp downSampler, Integer maxLowResDim, Float minX, Float minY, Float height, RenderingHints hints) {
/* 253 */     ParameterBlockJAI pb = new ParameterBlockJAI("Renderable", "renderable");
/* 257 */     pb.setSource("source0", source0);
/* 259 */     pb.setParameter("downSampler", downSampler);
/* 260 */     pb.setParameter("maxLowResDim", maxLowResDim);
/* 261 */     pb.setParameter("minX", minX);
/* 262 */     pb.setParameter("minY", minY);
/* 263 */     pb.setParameter("height", height);
/* 265 */     return JAI.createRenderable("Renderable", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\RenderableDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */