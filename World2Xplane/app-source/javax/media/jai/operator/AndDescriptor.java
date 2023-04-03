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
/*     */ public class AndDescriptor extends OperationDescriptorImpl {
/*  82 */   private static final String[][] resources = new String[][] { { "GlobalName", "And" }, { "LocalName", "And" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("AndDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/AndDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*  91 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public AndDescriptor() {
/*  98 */     super(resources, supportedModes, 2, null, null, null, null);
/*     */   }
/*     */   
/*     */   protected boolean validateSources(String modeName, ParameterBlock args, StringBuffer msg) {
/* 112 */     if (!super.validateSources(modeName, args, msg))
/* 113 */       return false; 
/* 116 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 117 */       return true; 
/* 119 */     for (int i = 0; i < 2; i++) {
/* 121 */       RenderedImage src = args.getRenderedSource(0);
/* 123 */       int dtype = src.getSampleModel().getDataType();
/* 125 */       if (dtype != 0 && dtype != 1 && dtype != 2 && dtype != 3) {
/* 129 */         msg.append(getName() + " " + JaiI18N.getString("AndDescriptor1"));
/* 131 */         return false;
/*     */       } 
/*     */     } 
/* 135 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderedImage source1, RenderingHints hints) {
/* 161 */     ParameterBlockJAI pb = new ParameterBlockJAI("And", "rendered");
/* 165 */     pb.setSource("source0", source0);
/* 166 */     pb.setSource("source1", source1);
/* 168 */     return JAI.create("And", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderableImage source1, RenderingHints hints) {
/* 193 */     ParameterBlockJAI pb = new ParameterBlockJAI("And", "renderable");
/* 197 */     pb.setSource("source0", source0);
/* 198 */     pb.setSource("source1", source1);
/* 200 */     return JAI.createRenderable("And", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\AndDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */