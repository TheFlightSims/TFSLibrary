/*    */ package org.geotools.data.shapefile;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.net.URL;
/*    */ import java.util.Map;
/*    */ import org.geotools.data.DataAccessFactory;
/*    */ import org.geotools.data.DataUtilities;
/*    */ 
/*    */ public class ShapefileDirectoryFactory extends ShapefileDataStoreFactory {
/* 37 */   public static final DataAccessFactory.Param URLP = new DataAccessFactory.Param("url", URL.class, "Directory containing geospatial files", true);
/*    */   
/*    */   public String getDisplayName() {
/* 41 */     return "Directory of spatial files (shapefiles)";
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 45 */     return "Takes a directory of shapefiles and exposes it as a data store";
/*    */   }
/*    */   
/*    */   public boolean canProcess(Map params) {
/* 50 */     if (super.canProcess(params))
/*    */       try {
/* 52 */         URL url = (URL)URLP.lookUp(params);
/* 53 */         File f = DataUtilities.urlToFile(url);
/* 54 */         return (f != null && f.exists() && f.isDirectory());
/* 55 */       } catch (Exception e) {
/* 56 */         return false;
/*    */       }  
/* 59 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\ShapefileDirectoryFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */