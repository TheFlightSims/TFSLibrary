/*    */ package org.openstreetmap.osmosis.core.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.util.logging.Logger;
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ 
/*    */ public class ResourceFileManager {
/* 22 */   private static final Logger LOG = Logger.getLogger(ResourceFileManager.class.getName());
/*    */   
/*    */   public void copyResourceToFile(Class<?> callingClass, String sourceResource, File destinationFile) {
/* 37 */     InputStream is = null;
/* 38 */     OutputStream os = null;
/*    */     try {
/* 44 */       byte[] buffer = new byte[4096];
/* 46 */       is = callingClass.getResourceAsStream(sourceResource);
/* 47 */       os = new FileOutputStream(destinationFile);
/* 49 */       if (is == null)
/* 50 */         throw new FileNotFoundException("Could not find " + sourceResource); 
/*    */       while (true) {
/* 54 */         int bytesRead = is.read(buffer);
/* 57 */         if (bytesRead < 0)
/*    */           break; 
/* 61 */         os.write(buffer, 0, bytesRead);
/*    */       } 
/* 64 */       is.close();
/* 65 */       os.close();
/* 67 */     } catch (IOException e) {
/* 68 */       throw new OsmosisRuntimeException("Unable to copy resource " + sourceResource + " to file " + destinationFile);
/*    */     } finally {
/* 71 */       if (is != null)
/*    */         try {
/* 73 */           is.close();
/* 74 */         } catch (Exception e) {
/* 75 */           LOG.warning("Unable to close input stream for resource " + sourceResource);
/*    */         }  
/* 78 */       if (os != null)
/*    */         try {
/* 80 */           os.close();
/* 81 */         } catch (Exception e) {
/* 82 */           LOG.warning("Unable to close output stream for file " + destinationFile);
/*    */         }  
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\cor\\util\ResourceFileManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */