/*    */ package org.geotools.data.shapefile.fid;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.URL;
/*    */ import java.util.logging.Logger;
/*    */ import org.geotools.data.shapefile.files.ShpFileType;
/*    */ import org.geotools.data.shapefile.files.ShpFiles;
/*    */ import org.geotools.data.shapefile.files.StorageFile;
/*    */ import org.geotools.data.shapefile.shp.IndexFile;
/*    */ import org.geotools.util.logging.Logging;
/*    */ 
/*    */ public class FidIndexer {
/* 39 */   static Logger LOGGER = Logging.getLogger("org.geotools.data.shapefile");
/*    */   
/*    */   public static synchronized void generate(URL shpURL) throws IOException {
/* 46 */     generate(new ShpFiles(shpURL));
/*    */   }
/*    */   
/*    */   public static void generate(ShpFiles shpFiles) throws IOException {
/* 53 */     LOGGER.fine("Generating fids for " + shpFiles.get(ShpFileType.SHP));
/* 56 */     IndexFile indexFile = null;
/* 57 */     StorageFile file = shpFiles.getStorageFile(ShpFileType.FIX);
/* 58 */     IndexedFidWriter writer = null;
/*    */     try {
/* 61 */       indexFile = new IndexFile(shpFiles, false);
/* 64 */       writer = new IndexedFidWriter(shpFiles, file);
/* 66 */       for (int i = 0, j = indexFile.getRecordCount(); i < j; i++)
/* 67 */         writer.next(); 
/*    */     } finally {
/*    */       try {
/* 72 */         if (writer != null)
/* 73 */           writer.close(); 
/* 75 */         file.replaceOriginal();
/*    */       } finally {
/* 77 */         if (indexFile != null)
/* 78 */           indexFile.close(); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\fid\FidIndexer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */