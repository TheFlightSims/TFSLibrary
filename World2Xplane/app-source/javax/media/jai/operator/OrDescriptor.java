/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class OrDescriptor extends OperationDescriptorImpl {
/*  82 */   private static final String[][] resources = new String[][] { { "GlobalName", "Or" }, { "LocalName", "Or" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("OrDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/OrDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*  91 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public OrDescriptor() {
/*  98 */     super(resources, supportedModes, 2, null, null, null, null);
/*     */   }
/*     */   
/*     */   protected boolean validateSources(String modeName, ParameterBlock args, StringBuffer msg) {
/* 111 */     if (!super.validateSources(modeName, args, msg))
/* 112 */       return false; 
/* 115 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 116 */       return true; 
/* 118 */     for (int i = 0; i < 2; i++) {
/* 119 */       RenderedImage src = args.getRenderedSource(i);
/* 121 */       int dtype = src.getSampleModel().getDataType();
/* 123 */       if (dtype != 0 && dtype != 1 && dtype != 2 && dtype != 3) {
/* 127 */         msg.append(getName() + " " + JaiI18N.getString("OrDescriptor1"));
/* 129 */         return false;
/*     */       } 
/*     */     } 
/* 133 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderedImage source1, RenderingHints hints) {
/* 159 */     ParameterBlockJAI pb = new ParameterBlockJAI("Or", "rendered");
/* 163 */     pb.setSource("source0", source0);
/* 164 */     pb.setSource("source1", source1);
/* 166 */     return JAI.create("Or", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderableImage source1, RenderingHints hints) {
/* 191 */     ParameterBlockJAI pb = new ParameterBlockJAI("Or", "renderable");
/* 195 */     pb.setSource("source0", source0);
/* 196 */     pb.setSource("source1", source1);
/* 198 */     return JAI.createRenderable("Or", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\OrDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */