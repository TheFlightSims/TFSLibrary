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
/*     */ public class LogDescriptor extends OperationDescriptorImpl {
/*  66 */   private static final String[][] resources = new String[][] { { "GlobalName", "Log" }, { "LocalName", "Log" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("LogDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/LogDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*     */   public LogDescriptor() {
/*  77 */     super(resources, 1, null, null, null);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/*  82 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderingHints hints) {
/* 105 */     ParameterBlockJAI pb = new ParameterBlockJAI("Log", "rendered");
/* 109 */     pb.setSource("source0", source0);
/* 111 */     return JAI.create("Log", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderingHints hints) {
/* 133 */     ParameterBlockJAI pb = new ParameterBlockJAI("Log", "renderable");
/* 137 */     pb.setSource("source0", source0);
/* 139 */     return JAI.createRenderable("Log", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\LogDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */