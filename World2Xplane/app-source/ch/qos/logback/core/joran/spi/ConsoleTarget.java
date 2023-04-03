/*    */ package ch.qos.logback.core.joran.spi;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ 
/*    */ public enum ConsoleTarget {
/* 29 */   SystemOut("System.out", new OutputStream() {
/*    */       public void write(int b) throws IOException {
/* 32 */         System.out.write(b);
/*    */       }
/*    */       
/*    */       public void write(byte[] b) throws IOException {
/* 36 */         System.out.write(b);
/*    */       }
/*    */       
/*    */       public void write(byte[] b, int off, int len) throws IOException {
/* 40 */         System.out.write(b, off, len);
/*    */       }
/*    */       
/*    */       public void flush() throws IOException {
/* 44 */         System.out.flush();
/*    */       }
/*    */     }),
/* 48 */   SystemErr("System.err", new OutputStream() {
/*    */       public void write(int b) throws IOException {
/* 51 */         System.err.write(b);
/*    */       }
/*    */       
/*    */       public void write(byte[] b) throws IOException {
/* 55 */         System.err.write(b);
/*    */       }
/*    */       
/*    */       public void write(byte[] b, int off, int len) throws IOException {
/* 59 */         System.err.write(b, off, len);
/*    */       }
/*    */       
/*    */       public void flush() throws IOException {
/* 63 */         System.err.flush();
/*    */       }
/*    */     });
/*    */   
/*    */   private final String name;
/*    */   
/*    */   private final OutputStream stream;
/*    */   
/*    */   public static ConsoleTarget findByName(String name) {
/* 68 */     for (ConsoleTarget target : values()) {
/* 69 */       if (target.name.equalsIgnoreCase(name))
/* 70 */         return target; 
/*    */     } 
/* 73 */     return null;
/*    */   }
/*    */   
/*    */   ConsoleTarget(String name, OutputStream stream) {
/* 80 */     this.name = name;
/* 81 */     this.stream = stream;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 85 */     return this.name;
/*    */   }
/*    */   
/*    */   public OutputStream getStream() {
/* 89 */     return this.stream;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\joran\spi\ConsoleTarget.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */