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
/*     */ public class NotDescriptor extends OperationDescriptorImpl {
/*  72 */   private static final String[][] resources = new String[][] { { "GlobalName", "Not" }, { "LocalName", "Not" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("NotDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/NotDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*  81 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public NotDescriptor() {
/*  88 */     super(resources, supportedModes, 1, null, null, null, null);
/*     */   }
/*     */   
/*     */   protected boolean validateSources(String modeName, ParameterBlock args, StringBuffer msg) {
/* 101 */     if (!super.validateSources(modeName, args, msg))
/* 102 */       return false; 
/* 105 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 106 */       return true; 
/* 108 */     RenderedImage src = args.getRenderedSource(0);
/* 110 */     int dtype = src.getSampleModel().getDataType();
/* 112 */     if (dtype != 0 && dtype != 1 && dtype != 2 && dtype != 3) {
/* 116 */       msg.append(getName() + " " + JaiI18N.getString("NotDescriptor1"));
/* 117 */       return false;
/*     */     } 
/* 120 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderingHints hints) {
/* 143 */     ParameterBlockJAI pb = new ParameterBlockJAI("Not", "rendered");
/* 147 */     pb.setSource("source0", source0);
/* 149 */     return JAI.create("Not", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderingHints hints) {
/* 171 */     ParameterBlockJAI pb = new ParameterBlockJAI("Not", "renderable");
/* 175 */     pb.setSource("source0", source0);
/* 177 */     return JAI.createRenderable("Not", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\NotDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */