/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0313Q!\001\002\001\t\031\021A\004V=qK\022\034%/Z1u_J4UO\\2uS>t7i\0348tk6,'O\003\002\004\t\005)\021m\031;pe*\tQ!\001\003bW.\f7c\001\001\b\033A\021\001bC\007\002\023)\t!\"A\003tG\006d\027-\003\002\r\023\t1\021I\\=SK\032\004\"AD\b\016\003\tI!\001\005\002\003+%sG-\033:fGR\f5\r^8s!J|G-^2fe\"A!\003\001B\001B\003%A#A\002dYj\034\001\001\r\002\026=A\031a#\007\017\017\005!9\022B\001\r\n\003\031\001&/\0323fM&\021!d\007\002\006\0072\f7o\035\006\0031%\001\"!\b\020\r\001\021Iq$EA\001\002\003\025\t\001\t\002\005?\022\nD'\005\002\"IA\021\001BI\005\003G%\021qAT8uQ&tw\r\005\002\017K%\021aE\001\002\006\003\016$xN\035\005\tQ\001\021\t\021)A\005S\00591M]3bi>\024\bc\001\005+I%\0211&\003\002\n\rVt7\r^5p]BBQ!\f\001\005\0029\na\001P5oSRtDcA\0301kA\021a\002\001\005\006%1\002\r!\r\031\003eQ\0022AF\r4!\tiB\007B\005 a\005\005\t\021!B\001A!)\001\006\fa\001S!)q\007\001C!q\005Q\021m\031;pe\016c\027m]:\026\003e\002$A\017\"\021\007m\002\025)D\001=\025\tid(\001\003mC:<'\"A \002\t)\fg/Y\005\0035q\002\"!\b\"\005\023}1\024\021!A\001\006\003\001\003\"\002#\001\t\003*\025a\0029s_\022,8-\032\013\002I\001")
/*     */ public class TypedCreatorFunctionConsumer implements IndirectActorProducer {
/*     */   private final Class<? extends Actor> clz;
/*     */   
/*     */   private final Function0<Actor> creator;
/*     */   
/*     */   public TypedCreatorFunctionConsumer(Class<? extends Actor> clz, Function0<Actor> creator) {}
/*     */   
/*     */   public Class<? extends Actor> actorClass() {
/* 339 */     return this.clz;
/*     */   }
/*     */   
/*     */   public Actor produce() {
/* 340 */     return (Actor)this.creator.apply();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\TypedCreatorFunctionConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */