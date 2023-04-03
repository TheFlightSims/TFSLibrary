/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.PropertyGenerator;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class TransposeDescriptor extends OperationDescriptorImpl {
/* 174 */   public static final TransposeType FLIP_VERTICAL = new TransposeType("FLIP_VERTICAL", 0);
/*     */   
/* 176 */   public static final TransposeType FLIP_HORIZONTAL = new TransposeType("FLIP_HORIZONTAL", 1);
/*     */   
/* 178 */   public static final TransposeType FLIP_DIAGONAL = new TransposeType("FLIP_DIAGONAL", 2);
/*     */   
/* 180 */   public static final TransposeType FLIP_ANTIDIAGONAL = new TransposeType("FLIP_ANTIDIAGONAL", 3);
/*     */   
/* 182 */   public static final TransposeType ROTATE_90 = new TransposeType("ROTATE_90", 4);
/*     */   
/* 184 */   public static final TransposeType ROTATE_180 = new TransposeType("ROTATE_180", 5);
/*     */   
/* 186 */   public static final TransposeType ROTATE_270 = new TransposeType("ROTATE_270", 6);
/*     */   
/* 193 */   private static final String[][] resources = new String[][] { { "GlobalName", "Transpose" }, { "LocalName", "Transpose" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("TransposeDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/TransposeDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion2") }, { "arg0Desc", JaiI18N.getString("TransposeDescriptor1") } };
/*     */   
/* 204 */   private static final Class[] paramClasses = new Class[] { TransposeType.class };
/*     */   
/* 209 */   private static final String[] paramNames = new String[] { "type" };
/*     */   
/* 214 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT };
/*     */   
/*     */   public TransposeDescriptor() {
/* 220 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/* 225 */     return true;
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators() {
/* 235 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 236 */     pg[0] = (PropertyGenerator)new TransposePropertyGenerator();
/* 237 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, TransposeType type, RenderingHints hints) {
/* 263 */     ParameterBlockJAI pb = new ParameterBlockJAI("Transpose", "rendered");
/* 267 */     pb.setSource("source0", source0);
/* 269 */     pb.setParameter("type", type);
/* 271 */     return JAI.create("Transpose", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, TransposeType type, RenderingHints hints) {
/* 296 */     ParameterBlockJAI pb = new ParameterBlockJAI("Transpose", "renderable");
/* 300 */     pb.setSource("source0", source0);
/* 302 */     pb.setParameter("type", type);
/* 304 */     return JAI.createRenderable("Transpose", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\TransposeDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */