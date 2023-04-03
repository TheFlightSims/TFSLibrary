/*    */ package org.apache.commons.compress.archivers;
/*    */ 
/*    */ import java.io.BufferedInputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public final class Lister {
/* 35 */   private static final ArchiveStreamFactory factory = new ArchiveStreamFactory();
/*    */   
/*    */   public static void main(String[] args) throws Exception {
/*    */     ArchiveInputStream ais;
/* 38 */     if (args.length == 0) {
/* 39 */       usage();
/*    */       return;
/*    */     } 
/* 42 */     System.out.println("Analysing " + args[0]);
/* 43 */     File f = new File(args[0]);
/* 44 */     if (!f.isFile())
/* 45 */       System.err.println(f + " doesn't exist or is a directory"); 
/* 47 */     InputStream fis = new BufferedInputStream(new FileInputStream(f));
/* 49 */     if (args.length > 1) {
/* 50 */       ais = factory.createArchiveInputStream(args[1], fis);
/*    */     } else {
/* 52 */       ais = factory.createArchiveInputStream(fis);
/*    */     } 
/* 54 */     System.out.println("Created " + ais.toString());
/*    */     ArchiveEntry ae;
/* 56 */     while ((ae = ais.getNextEntry()) != null)
/* 57 */       System.out.println(ae.getName()); 
/* 59 */     ais.close();
/* 60 */     fis.close();
/*    */   }
/*    */   
/*    */   private static void usage() {
/* 64 */     System.out.println("Parameters: archive-name [archive-type]");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\Lister.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */