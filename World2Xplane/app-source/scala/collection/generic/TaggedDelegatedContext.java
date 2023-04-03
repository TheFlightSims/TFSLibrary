/*     */ package scala.collection.generic;
/*     */ 
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t2A!\001\002\001\023\t1B+Y4hK\022$U\r\\3hCR,GmQ8oi\026DHO\003\002\004\t\0059q-\0328fe&\034'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\0011C\001\001\013!\tYA\"D\001\003\023\ti!A\001\tEK2,w-\031;fI\016{g\016^3yi\"Aq\002\001B\001B\003%\001#A\003eK2,w\r\005\002\f#%\021!C\001\002\013'&<g.\0317mS:<\007\002\003\013\001\005\013\007I\021I\013\002\007Q\fw-F\001\027!\t9\002$D\001\007\023\tIbAA\002J]RD\001b\007\001\003\002\003\006IAF\001\005i\006<\007\005C\003\036\001\021\005a$\001\004=S:LGO\020\013\004?\001\n\003CA\006\001\021\025yA\0041\001\021\021\025!B\0041\001\027\001")
/*     */ public class TaggedDelegatedContext extends DelegatedContext {
/*     */   private final int tag;
/*     */   
/*     */   public int tag() {
/* 186 */     return this.tag;
/*     */   }
/*     */   
/*     */   public TaggedDelegatedContext(Signalling deleg, int tag) {
/* 186 */     super(deleg);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\TaggedDelegatedContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */