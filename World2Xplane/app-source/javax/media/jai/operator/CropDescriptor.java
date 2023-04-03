/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class CropDescriptor extends OperationDescriptorImpl {
/*  85 */   private static final String[][] resources = new String[][] { { "GlobalName", "Crop" }, { "LocalName", "Crop" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("CropDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/CropDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("CropDescriptor1") }, { "arg1Desc", JaiI18N.getString("CropDescriptor2") }, { "arg2Desc", JaiI18N.getString("CropDescriptor3") }, { "arg3Desc", JaiI18N.getString("CropDescriptor4") } };
/*     */   
/*  99 */   private static final Class[] paramClasses = new Class[] { Float.class, Float.class, Float.class, Float.class };
/*     */   
/* 107 */   private static final String[] paramNames = new String[] { "x", "y", "width", "height" };
/*     */   
/* 112 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT, NO_PARAMETER_DEFAULT, NO_PARAMETER_DEFAULT, NO_PARAMETER_DEFAULT };
/*     */   
/* 116 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public CropDescriptor() {
/* 123 */     super(resources, supportedModes, 1, paramNames, paramClasses, paramDefaults, null);
/*     */   }
/*     */   
/*     */   public boolean validateArguments(String modeName, ParameterBlock args, StringBuffer msg) {
/* 138 */     if (!super.validateArguments(modeName, args, msg))
/* 139 */       return false; 
/* 142 */     if (modeName.equalsIgnoreCase("rendered"))
/* 143 */       return validateRenderedArgs(args, msg); 
/* 145 */     if (modeName.equalsIgnoreCase("renderable"))
/* 146 */       return validateRenderableArgs(args, msg); 
/* 148 */     return true;
/*     */   }
/*     */   
/*     */   private boolean validateRenderedArgs(ParameterBlock args, StringBuffer msg) {
/* 163 */     float x_req = args.getFloatParameter(0);
/* 164 */     float y_req = args.getFloatParameter(1);
/* 165 */     float w_req = args.getFloatParameter(2);
/* 166 */     float h_req = args.getFloatParameter(3);
/* 169 */     Rectangle rect_req = (new Rectangle2D.Float(x_req, y_req, w_req, h_req)).getBounds();
/* 173 */     if (rect_req.isEmpty()) {
/* 174 */       msg.append(getName() + " " + JaiI18N.getString("CropDescriptor5"));
/* 176 */       return false;
/*     */     } 
/* 180 */     RenderedImage src = (RenderedImage)args.getSource(0);
/* 182 */     Rectangle srcBounds = new Rectangle(src.getMinX(), src.getMinY(), src.getWidth(), src.getHeight());
/* 188 */     if (!srcBounds.contains(rect_req)) {
/* 189 */       msg.append(getName() + " " + JaiI18N.getString("CropDescriptor6"));
/* 191 */       return false;
/*     */     } 
/* 194 */     return true;
/*     */   }
/*     */   
/*     */   private boolean validateRenderableArgs(ParameterBlock args, StringBuffer msg) {
/* 209 */     float x_req = args.getFloatParameter(0);
/* 210 */     float y_req = args.getFloatParameter(1);
/* 211 */     float w_req = args.getFloatParameter(2);
/* 212 */     float h_req = args.getFloatParameter(3);
/* 215 */     Rectangle2D rect_req = new Rectangle2D.Float(x_req, y_req, w_req, h_req);
/* 219 */     if (rect_req.isEmpty()) {
/* 220 */       msg.append(getName() + " " + JaiI18N.getString("CropDescriptor5"));
/* 222 */       return false;
/*     */     } 
/* 226 */     RenderableImage src = (RenderableImage)args.getSource(0);
/* 228 */     Rectangle2D rect_src = new Rectangle2D.Float(src.getMinX(), src.getMinY(), src.getWidth(), src.getHeight());
/* 234 */     if (!rect_src.contains(rect_req)) {
/* 235 */       msg.append(getName() + " " + JaiI18N.getString("CropDescriptor6"));
/* 237 */       return false;
/*     */     } 
/* 240 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, Float x, Float y, Float width, Float height, RenderingHints hints) {
/* 275 */     ParameterBlockJAI pb = new ParameterBlockJAI("Crop", "rendered");
/* 279 */     pb.setSource("source0", source0);
/* 281 */     pb.setParameter("x", x);
/* 282 */     pb.setParameter("y", y);
/* 283 */     pb.setParameter("width", width);
/* 284 */     pb.setParameter("height", height);
/* 286 */     return JAI.create("Crop", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, Float x, Float y, Float width, Float height, RenderingHints hints) {
/* 320 */     ParameterBlockJAI pb = new ParameterBlockJAI("Crop", "renderable");
/* 324 */     pb.setSource("source0", source0);
/* 326 */     pb.setParameter("x", x);
/* 327 */     pb.setParameter("y", y);
/* 328 */     pb.setParameter("width", width);
/* 329 */     pb.setParameter("height", height);
/* 331 */     return JAI.createRenderable("Crop", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\CropDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */