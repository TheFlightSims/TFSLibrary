/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import com.sun.media.jai.codec.ImageCodec;
/*     */ import com.sun.media.jai.codec.ImageEncodeParam;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class FileStoreDescriptor extends OperationDescriptorImpl {
/*  96 */   private static final String[][] resources = new String[][] { { "GlobalName", "FileStore" }, { "LocalName", "FileStore" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("FileStoreDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/FileStoreDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("FileStoreDescriptor1") }, { "arg1Desc", JaiI18N.getString("FileStoreDescriptor2") }, { "arg2Desc", JaiI18N.getString("FileStoreDescriptor3") }, { "arg3Desc", JaiI18N.getString("FileStoreDescriptor11") } };
/*     */   
/* 110 */   private static final String[] paramNames = new String[] { "filename", "format", "param", "checkFileLocally" };
/*     */   
/* 115 */   private static final Class[] paramClasses = new Class[] { String.class, String.class, ImageEncodeParam.class, Boolean.class };
/*     */   
/* 123 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT, "tiff", null, Boolean.TRUE };
/*     */   
/* 127 */   private static final String[] supportedModes = new String[] { "rendered" };
/*     */   
/*     */   public FileStoreDescriptor() {
/* 133 */     super(resources, supportedModes, 1, paramNames, paramClasses, paramDefaults, null);
/*     */   }
/*     */   
/*     */   public boolean validateArguments(String modeName, ParameterBlock args, StringBuffer msg) {
/* 149 */     if (!super.validateArguments(modeName, args, msg))
/* 150 */       return false; 
/* 153 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 154 */       return true; 
/* 157 */     String format = (String)args.getObjectParameter(1);
/* 160 */     ImageCodec codec = ImageCodec.getCodec(format);
/* 163 */     if (codec == null) {
/* 164 */       msg.append(getName() + " " + JaiI18N.getString("FileStoreDescriptor4"));
/* 166 */       return false;
/*     */     } 
/* 170 */     ImageEncodeParam param = (ImageEncodeParam)args.getObjectParameter(2);
/* 173 */     RenderedImage src = args.getRenderedSource(0);
/* 176 */     if (!codec.canEncodeImage(src, param)) {
/* 177 */       msg.append(getName() + " " + JaiI18N.getString("FileStoreDescriptor5"));
/* 179 */       return false;
/*     */     } 
/* 183 */     String pathName = (String)args.getObjectParameter(0);
/* 184 */     if (pathName == null) {
/* 185 */       msg.append(getName() + " " + JaiI18N.getString("FileStoreDescriptor6"));
/* 187 */       return false;
/*     */     } 
/* 192 */     Boolean checkFile = (Boolean)args.getObjectParameter(3);
/* 193 */     if (checkFile.booleanValue())
/*     */       try {
/* 195 */         File f = new File(pathName);
/* 196 */         if (f.exists()) {
/* 197 */           if (!f.canWrite()) {
/* 199 */             msg.append(getName() + " " + JaiI18N.getString("FileStoreDescriptor7"));
/* 201 */             return false;
/*     */           } 
/*     */         } else {
/* 204 */           if (!f.createNewFile()) {
/* 206 */             msg.append(getName() + " " + JaiI18N.getString("FileStoreDescriptor8"));
/* 208 */             return false;
/*     */           } 
/* 210 */           f.delete();
/*     */         } 
/* 212 */       } catch (IOException ioe) {
/* 214 */         msg.append(getName() + " " + JaiI18N.getString("FileStoreDescriptor9") + " " + ioe.getMessage());
/* 217 */         return false;
/* 218 */       } catch (SecurityException se) {
/* 221 */         msg.append(getName() + " " + JaiI18N.getString("FileStoreDescriptor10") + " " + se.getMessage());
/* 224 */         return false;
/*     */       }  
/* 228 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isImmediate() {
/* 238 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, String filename, String format, ImageEncodeParam param, Boolean checkFileLocally, RenderingHints hints) {
/* 274 */     ParameterBlockJAI pb = new ParameterBlockJAI("FileStore", "rendered");
/* 278 */     pb.setSource("source0", source0);
/* 280 */     pb.setParameter("filename", filename);
/* 281 */     pb.setParameter("format", format);
/* 282 */     pb.setParameter("param", param);
/* 283 */     pb.setParameter("checkFileLocally", checkFileLocally);
/* 285 */     return JAI.create("FileStore", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\FileStoreDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */