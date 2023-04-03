/*    */ package akka.actor;
/*    */ 
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.Nil$;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ 
/*    */ public abstract class PathUtils$class {
/*    */   public static void $init$(PathUtils $this) {}
/*    */   
/*    */   private static final List rec$1(PathUtils $this, int pos, List acc, String s$1, String fragment$1) {
/*    */     while (true) {
/* 85 */       int from = s$1.lastIndexOf('/', pos - 1);
/* 86 */       String sub = s$1.substring(from + 1, pos);
/* 88 */       String str1 = (new StringBuilder()).append(sub).append("#").append(fragment$1).toString();
/* 89 */       String str2 = sub;
/* 89 */       List l = (fragment$1 != null && acc.isEmpty()) ? acc.$colon$colon(str1) : acc.$colon$colon(str2);
/* 90 */       if (from == -1)
/* 90 */         return l; 
/* 90 */       acc = l;
/* 90 */       pos = from;
/* 90 */       $this = $this;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static List split(PathUtils $this, String s, String fragment) {
/* 92 */     return rec$1($this, s.length(), (List)Nil$.MODULE$, s, fragment);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\PathUtils$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */