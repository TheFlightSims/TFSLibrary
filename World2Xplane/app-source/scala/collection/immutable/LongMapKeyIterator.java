/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001q2Q!\001\002\001\005!\021!\003T8oO6\013\007oS3z\023R,'/\031;pe*\0211\001B\001\nS6lW\017^1cY\026T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b+\tI\001c\005\002\001\025A!1\002\004\b\034\033\005\021\021BA\007\003\005=auN\\4NCBLE/\032:bi>\024\bCA\b\021\031\001!Q!\005\001C\002M\021\021AV\002\001#\t!\002\004\005\002\026-5\ta!\003\002\030\r\t9aj\034;iS:<\007CA\013\032\023\tQbAA\002B]f\004\"\001H\020\017\005-i\022B\001\020\003\0031auN\\4NCB,F/\0337t\023\t\001\023E\001\003M_:<\027B\001\021#\025\t\031C%A\007CSR|\005/\032:bi&|gn\035\006\003K\021\tqaZ3oKJL7\r\003\005(\001\t\005\t\025!\003)\003\tIG\017E\002\fS9I!A\013\002\003\0171{gnZ'ba\")A\006\001C\001[\0051A(\0338jiz\"\"AL\030\021\007-\001a\002C\003(W\001\007\001\006C\0032\001\021\005!'A\004wC2,Xm\0244\025\005m\031\004\"\002\0331\001\004)\024a\001;jaB\031a'\017\b\017\005-9\024B\001\035\003\003\035auN\\4NCBL!AO\036\003\007QK\007O\003\0029\005\001")
/*     */ public class LongMapKeyIterator<V> extends LongMapIterator<V, Object> {
/*     */   public LongMapKeyIterator(LongMap<V> it) {
/* 135 */     super(it);
/*     */   }
/*     */   
/*     */   public long valueOf(LongMap.Tip tip) {
/* 136 */     return tip.key();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\LongMapKeyIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */