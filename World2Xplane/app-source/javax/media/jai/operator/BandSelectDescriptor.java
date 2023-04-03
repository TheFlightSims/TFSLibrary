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
/*     */ public class BandSelectDescriptor extends OperationDescriptorImpl {
/*  78 */   private static final String[][] resources = new String[][] { { "GlobalName", "BandSelect" }, { "LocalName", "BandSelect" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("BandSelectDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/BandSelectDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("BandSelectDescriptor1") } };
/*     */   
/*  89 */   private static final Class[] paramClasses = new Class[] { (array$I == null) ? (array$I = class$("[I")) : array$I };
/*     */   
/*     */   static Class class$(String x0) {
/*     */     try {
/*  90 */       return Class.forName(x0);
/*     */     } catch (ClassNotFoundException x1) {
/*  90 */       throw new NoClassDefFoundError(x1.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*  94 */   private static final String[] paramNames = new String[] { "bandIndices" };
/*     */   
/*  99 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT };
/*     */   
/* 103 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   static Class array$I;
/*     */   
/*     */   public BandSelectDescriptor() {
/* 110 */     super(resources, supportedModes, 1, paramNames, paramClasses, paramDefaults, null);
/*     */   }
/*     */   
/*     */   public boolean validateArguments(String modeName, ParameterBlock args, StringBuffer message) {
/* 125 */     if (!super.validateArguments(modeName, args, message))
/* 126 */       return false; 
/* 129 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 130 */       return true; 
/* 132 */     int[] indices = (int[])args.getObjectParameter(0);
/* 133 */     if (indices.length < 1) {
/* 134 */       message.append(getName() + " " + JaiI18N.getString("BandSelectDescriptor2"));
/* 136 */       return false;
/*     */     } 
/* 139 */     RenderedImage src = args.getRenderedSource(0);
/* 141 */     int bands = src.getSampleModel().getNumBands();
/* 142 */     for (int i = 0; i < indices.length; i++) {
/* 143 */       if (indices[i] < 0 || indices[i] >= bands) {
/* 144 */         message.append(getName() + " " + JaiI18N.getString("BandSelectDescriptor3"));
/* 146 */         return false;
/*     */       } 
/*     */     } 
/* 150 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, int[] bandIndices, RenderingHints hints) {
/* 176 */     ParameterBlockJAI pb = new ParameterBlockJAI("BandSelect", "rendered");
/* 180 */     pb.setSource("source0", source0);
/* 182 */     pb.setParameter("bandIndices", bandIndices);
/* 184 */     return JAI.create("BandSelect", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, int[] bandIndices, RenderingHints hints) {
/* 209 */     ParameterBlockJAI pb = new ParameterBlockJAI("BandSelect", "renderable");
/* 213 */     pb.setSource("source0", source0);
/* 215 */     pb.setParameter("bandIndices", bandIndices);
/* 217 */     return JAI.createRenderable("BandSelect", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\BandSelectDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */