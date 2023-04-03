/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import com.sun.media.jai.codec.ImageDecodeParam;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class FileLoadDescriptor extends OperationDescriptorImpl {
/*  84 */   private static final String[][] resources = new String[][] { { "GlobalName", "FileLoad" }, { "LocalName", "FileLoad" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("FileLoadDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/FileLoadDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("FileLoadDescriptor1") }, { "arg1Desc", JaiI18N.getString("FileLoadDescriptor4") }, { "arg2Desc", JaiI18N.getString("FileLoadDescriptor5") } };
/*     */   
/*  97 */   private static final String[] paramNames = new String[] { "filename", "param", "checkFileLocally" };
/*     */   
/* 102 */   private static final Class[] paramClasses = new Class[] { String.class, ImageDecodeParam.class, Boolean.class };
/*     */   
/* 109 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT, null, Boolean.TRUE };
/*     */   
/*     */   public FileLoadDescriptor() {
/* 115 */     super(resources, 0, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(ParameterBlock args, StringBuffer msg) {
/* 128 */     if (!super.validateParameters(args, msg))
/* 129 */       return false; 
/* 132 */     Boolean checkFile = (Boolean)args.getObjectParameter(2);
/* 133 */     if (checkFile.booleanValue()) {
/* 134 */       String filename = (String)args.getObjectParameter(0);
/* 135 */       File f = new File(filename);
/* 136 */       boolean fileExists = f.exists();
/* 137 */       if (!fileExists) {
/* 141 */         InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
/* 142 */         if (is == null) {
/* 143 */           msg.append("\"" + filename + "\": " + JaiI18N.getString("FileLoadDescriptor2"));
/* 145 */           return false;
/*     */         } 
/* 148 */       } else if (!f.canRead()) {
/* 149 */         msg.append("\"" + filename + "\": " + JaiI18N.getString("FileLoadDescriptor3"));
/* 151 */         return false;
/*     */       } 
/*     */     } 
/* 155 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(String filename, ImageDecodeParam param, Boolean checkFileLocally, RenderingHints hints) {
/* 184 */     ParameterBlockJAI pb = new ParameterBlockJAI("FileLoad", "rendered");
/* 188 */     pb.setParameter("filename", filename);
/* 189 */     pb.setParameter("param", param);
/* 190 */     pb.setParameter("checkFileLocally", checkFileLocally);
/* 192 */     return JAI.create("FileLoad", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\FileLoadDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */