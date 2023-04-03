/*    */ package gnu.trove;
/*    */ 
/*    */ public class Version {
/*    */   public static void main(String[] args) {
/* 39 */     System.out.println(getVersion());
/*    */   }
/*    */   
/*    */   public static String getVersion() {
/* 52 */     String version = Version.class.getPackage().getImplementationVersion();
/* 54 */     if (version != null)
/* 55 */       return "trove4j version " + version; 
/* 58 */     return "Sorry no Implementation-Version manifest attribute available";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\Version.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */