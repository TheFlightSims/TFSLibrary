/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.OperationNode;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class NullDescriptor extends OperationDescriptorImpl {
/*  67 */   private static final String[][] resources = new String[][] { { "GlobalName", "Null" }, { "LocalName", "Null" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("NullDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/NullDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*  76 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public NullDescriptor() {
/*  83 */     super(resources, supportedModes, 1, null, null, null, null);
/*     */   }
/*     */   
/*     */   private static ParameterBlock foolSourceValidation(ParameterBlock args) {
/*  93 */     if (args.getNumSources() > 1) {
/*  94 */       Vector singleSource = new Vector();
/*  95 */       singleSource.add(args.getSource(0));
/*  96 */       args = new ParameterBlock(singleSource, args.getParameters());
/*     */     } 
/*  98 */     return args;
/*     */   }
/*     */   
/*     */   protected boolean validateSources(String modeName, ParameterBlock args, StringBuffer msg) {
/* 114 */     if (args == null || msg == null)
/* 115 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 118 */     return super.validateSources(modeName, foolSourceValidation(args), msg);
/*     */   }
/*     */   
/*     */   public Object getInvalidRegion(String modeName, ParameterBlock oldParamBlock, RenderingHints oldHints, ParameterBlock newParamBlock, RenderingHints newHints, OperationNode node) {
/* 156 */     if (modeName == null || oldParamBlock == null || newParamBlock == null)
/* 157 */       throw new IllegalArgumentException(JaiI18N.getString("NullDescriptor1")); 
/* 160 */     if (oldParamBlock.getNumSources() < 1 || newParamBlock.getNumSources() < 1)
/* 163 */       throw new IllegalArgumentException(JaiI18N.getString("NullDescriptor2")); 
/* 166 */     return oldParamBlock.getSource(0).equals(newParamBlock.getSource(0)) ? new Rectangle() : null;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderingHints hints) {
/* 190 */     ParameterBlockJAI pb = new ParameterBlockJAI("Null", "rendered");
/* 194 */     pb.setSource("source0", source0);
/* 196 */     return JAI.create("Null", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderingHints hints) {
/* 218 */     ParameterBlockJAI pb = new ParameterBlockJAI("Null", "renderable");
/* 222 */     pb.setSource("source0", source0);
/* 224 */     return JAI.createRenderable("Null", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\NullDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */