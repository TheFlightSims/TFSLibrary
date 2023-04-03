/*    */ package org.geotools.styling;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ import java.util.logging.Logger;
/*    */ import org.geotools.data.DataUtilities;
/*    */ import org.geotools.util.logging.Logging;
/*    */ 
/*    */ public class DefaultResourceLocator implements ResourceLocator {
/*    */   URL sourceUrl;
/*    */   
/* 36 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.styling");
/*    */   
/*    */   public void setSourceUrl(URL sourceUrl) {
/* 40 */     this.sourceUrl = sourceUrl;
/*    */   }
/*    */   
/*    */   public URL locateResource(String uri) {
/* 44 */     URL url = null;
/*    */     try {
/* 46 */       url = new URL(uri);
/* 49 */       File f = DataUtilities.urlToFile(url);
/* 50 */       if (f != null && !f.isAbsolute())
/* 52 */         if (!f.exists() && this.sourceUrl != null) {
/* 53 */           URL relativeUrl = makeRelativeURL(f.getPath());
/* 54 */           if (relativeUrl != null) {
/* 55 */             f = DataUtilities.urlToFile(relativeUrl);
/* 56 */             if (f.exists())
/* 58 */               url = relativeUrl; 
/*    */           } 
/*    */         }  
/* 63 */     } catch (MalformedURLException mfe) {
/* 64 */       LOGGER.fine("Looks like " + uri + " is a relative path..");
/* 65 */       if (this.sourceUrl != null)
/* 66 */         url = makeRelativeURL(uri); 
/* 68 */       if (url == null) {
/* 70 */         url = getClass().getResource(uri);
/* 71 */         if (url == null)
/* 72 */           LOGGER.warning("can't parse " + uri + " as a java resource present in the classpath"); 
/*    */       } 
/*    */     } 
/* 75 */     return url;
/*    */   }
/*    */   
/*    */   URL makeRelativeURL(String uri) {
/*    */     try {
/* 80 */       return new URL(this.sourceUrl, uri);
/* 81 */     } catch (MalformedURLException e) {
/* 82 */       LOGGER.warning("can't parse " + uri + " as relative to" + this.sourceUrl.toExternalForm());
/* 85 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\DefaultResourceLocator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */