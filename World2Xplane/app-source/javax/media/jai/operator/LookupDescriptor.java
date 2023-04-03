/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.LookupTableJAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class LookupDescriptor extends OperationDescriptorImpl {
/* 123 */   private static final String[][] resources = new String[][] { { "GlobalName", "Lookup" }, { "LocalName", "Lookup" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("LookupDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/LookupDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("LookupDescriptor1") } };
/*     */   
/* 134 */   private static final Class[] paramClasses = new Class[] { LookupTableJAI.class };
/*     */   
/* 139 */   private static final String[] paramNames = new String[] { "table" };
/*     */   
/* 144 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT };
/*     */   
/* 148 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public LookupDescriptor() {
/* 155 */     super(resources, supportedModes, 1, paramNames, paramClasses, paramDefaults, null);
/*     */   }
/*     */   
/*     */   protected boolean validateSources(String modeName, ParameterBlock args, StringBuffer msg) {
/* 169 */     if (!super.validateSources(modeName, args, msg))
/* 170 */       return false; 
/* 173 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 174 */       return true; 
/* 176 */     RenderedImage src = args.getRenderedSource(0);
/* 178 */     int dtype = src.getSampleModel().getDataType();
/* 180 */     if (dtype != 0 && dtype != 1 && dtype != 2 && dtype != 3) {
/* 184 */       msg.append(getName() + " " + JaiI18N.getString("LookupDescriptor2"));
/* 186 */       return false;
/*     */     } 
/* 189 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, LookupTableJAI table, RenderingHints hints) {
/* 215 */     ParameterBlockJAI pb = new ParameterBlockJAI("Lookup", "rendered");
/* 219 */     pb.setSource("source0", source0);
/* 221 */     pb.setParameter("table", table);
/* 223 */     return JAI.create("Lookup", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, LookupTableJAI table, RenderingHints hints) {
/* 248 */     ParameterBlockJAI pb = new ParameterBlockJAI("Lookup", "renderable");
/* 252 */     pb.setSource("source0", source0);
/* 254 */     pb.setParameter("table", table);
/* 256 */     return JAI.createRenderable("Lookup", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\LookupDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */