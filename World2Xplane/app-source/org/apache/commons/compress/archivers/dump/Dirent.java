/*    */ package org.apache.commons.compress.archivers.dump;
/*    */ 
/*    */ class Dirent {
/*    */   private int ino;
/*    */   
/*    */   private int parentIno;
/*    */   
/*    */   private int type;
/*    */   
/*    */   private String name;
/*    */   
/*    */   Dirent(int ino, int parentIno, int type, String name) {
/* 39 */     this.ino = ino;
/* 40 */     this.parentIno = parentIno;
/* 41 */     this.type = type;
/* 42 */     this.name = name;
/*    */   }
/*    */   
/*    */   int getIno() {
/* 50 */     return this.ino;
/*    */   }
/*    */   
/*    */   int getParentIno() {
/* 58 */     return this.parentIno;
/*    */   }
/*    */   
/*    */   int getType() {
/* 66 */     return this.type;
/*    */   }
/*    */   
/*    */   String getName() {
/* 74 */     return this.name;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 82 */     return String.format("[%d]: %s", new Object[] { Integer.valueOf(this.ino), this.name });
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\dump\Dirent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */