/*    */ package scala.concurrent;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001y3q!\001\002\021\002G\005qA\001\007CY>\0347nQ8oi\026DHO\003\002\004\t\005Q1m\0348dkJ\024XM\034;\013\003\025\tQa]2bY\006\034\001a\005\002\001\021A\021\021BC\007\002\t%\0211\002\002\002\007\003:L(+\0324\t\0135\001a\021\001\b\002\017\tdwnY6P]V\021qb\005\013\003!\t\"\"!\005\017\021\005I\031B\002\001\003\006)1\021\r!\006\002\002)F\021a#\007\t\003\023]I!\001\007\003\003\0179{G\017[5oOB\021\021BG\005\0037\021\0211!\0218z\021\025iB\002q\001\037\003)\001XM]7jgNLwN\034\t\003?\001j\021AA\005\003C\t\021\001bQ1o\003^\f\027\016\036\005\007G1!\t\031\001\023\002\013QDWO\\6\021\007%)\023#\003\002'\t\tAAHY=oC6,ghB\003)\005!\005\021&\001\007CY>\0347nQ8oi\026DH\017\005\002 U\031)\021A\001E\001WM\021!\006\003\005\006[)\"\tAL\001\007y%t\027\016\036 \025\003%:Q\001\r\026\t\nE\n1\003R3gCVdGO\0217pG.\034uN\034;fqR\004\"AM\032\016\003)2Q\001\016\026\t\nU\0221\003R3gCVdGO\0217pG.\034uN\034;fqR\0342a\r\0057!\ty\002\001C\003.g\021\005\001\bF\0012\021\025i1\007\"\021;+\tYd\b\006\002=\001R\021Qh\020\t\003%y\"Q\001F\035C\002UAQ!H\035A\004yAaaI\035\005\002\004\t\005cA\005&{!91I\013b\001\n\023!\025\001D2p]R,\007\020\036'pG\006dW#A#\021\007\031[e'D\001H\025\tA\025*\001\003mC:<'\"\001&\002\t)\fg/Y\005\003\031\036\0231\002\0265sK\006$Gj\\2bY\"1aJ\013Q\001\n\025\013QbY8oi\026DH\017T8dC2\004\003\"\002)+\t\003\t\026aB2veJ,g\016^\013\002m!)1K\013C\001)\006\001r/\033;i\0052|7m[\"p]R,\007\020^\013\003+b#\"A\026/\025\005]K\006C\001\nY\t\025!\"K1\001\026\021\031Q&\013\"a\0017\006!!m\0343z!\rIQe\026\005\006;J\003\rAN\001\rE2|7m[\"p]R,\007\020\036")
/*    */ public interface BlockContext {
/*    */   <T> T blockOn(Function0<T> paramFunction0, CanAwait paramCanAwait);
/*    */   
/*    */   public static class DefaultBlockContext$ implements BlockContext {
/*    */     public static final DefaultBlockContext$ MODULE$;
/*    */     
/*    */     public DefaultBlockContext$() {
/* 52 */       MODULE$ = this;
/*    */     }
/*    */     
/*    */     public <T> T blockOn(Function0 thunk, CanAwait permission) {
/* 53 */       return (T)thunk.apply();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\BlockContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */