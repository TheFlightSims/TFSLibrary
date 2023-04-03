/*    */ package com.world2xplane.XPlane;
/*    */ 
/*    */ public class DSFTool {
/*    */   public static String getDSFToolPath() {
/* 31 */     String os = System.getProperty("os.name");
/* 32 */     if (os.toLowerCase().contains("mac"))
/* 33 */       return "./resources/DSFToolMac"; 
/* 34 */     if (os.toLowerCase().contains("windows"))
/* 35 */       return "./resources/DSFToolWin.exe"; 
/* 37 */     return "./resources/DSFToolLinux";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\DSFTool.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */