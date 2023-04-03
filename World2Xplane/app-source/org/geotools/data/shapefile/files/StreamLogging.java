/*    */ package org.geotools.data.shapefile.files;
/*    */ 
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import org.geotools.util.logging.Logging;
/*    */ 
/*    */ public class StreamLogging {
/* 28 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.data.shapefile");
/*    */   
/*    */   private String name;
/*    */   
/* 32 */   private int open = 0;
/*    */   
/*    */   public StreamLogging(String name) {
/* 40 */     this.name = name;
/*    */   }
/*    */   
/*    */   public synchronized void open() {
/* 47 */     this.open++;
/* 48 */     if (LOGGER.isLoggable(Level.FINER))
/* 49 */       LOGGER.finest(this.name + " has been opened. Number open: " + this.open); 
/*    */   }
/*    */   
/*    */   public synchronized void close() {
/* 54 */     this.open--;
/* 55 */     if (LOGGER.isLoggable(Level.FINER))
/* 56 */       LOGGER.finest(this.name + " has been closed. Number open: " + this.open); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\files\StreamLogging.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */