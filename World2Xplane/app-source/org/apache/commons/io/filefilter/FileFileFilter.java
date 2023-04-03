/*    */ package org.apache.commons.io.filefilter;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class FileFileFilter extends AbstractFileFilter implements Serializable {
/* 43 */   public static final IOFileFilter FILE = new FileFileFilter();
/*    */   
/*    */   public boolean accept(File file) {
/* 59 */     return file.isFile();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\io\filefilter\FileFileFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */