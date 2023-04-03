/*    */ package akka.actor;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.immutable.Iterable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005=s!B\001\003\021\0039\021!C!di>\024\b+\031;i\025\t\031A!A\003bGR|'OC\001\006\003\021\t7n[1\004\001A\021\001\"C\007\002\005\031)!B\001E\001\027\tI\021i\031;peB\013G\017[\n\004\0231\021\002CA\007\021\033\005q!\"A\b\002\013M\034\027\r\\1\n\005Eq!AB!osJ+g\r\005\002\016'%\021AC\004\002\r'\026\024\030.\0317ju\006\024G.\032\005\006-%!\taF\001\007y%t\027\016\036 \025\003\035AQ!G\005\005\002i\t!B\032:p[N#(/\0338h)\rY\022Q\004\t\003\021q1qA\003\002\021\002\007\005Rd\005\003\035\031y\021\002cA\020%75\t\001E\003\002\"E\005!A.\0318h\025\005\031\023\001\0026bm\006L!!\n\021\003\025\r{W\016]1sC\ndW\rC\003(9\021\005\001&\001\004%S:LG\017\n\013\002SA\021QBK\005\003W9\021A!\0268ji\")Q\006\bD\001]\0059\021\r\0323sKN\034X#A\030\021\005!\001\024BA\031\003\005\035\tE\r\032:fgNDQa\r\017\007\002Q\nAA\\1nKV\tQ\007\005\0027s9\021QbN\005\003q9\ta\001\025:fI\0264\027B\001\036<\005\031\031FO]5oO*\021\001H\004\005\006{q1\tAP\001\007a\006\024XM\034;\026\003mAQ\001\021\017\007\002\005\013A\001\n3jmR\0211D\021\005\006\007~\002\r!N\001\006G\"LG\016\032\005\006\007r!\t!\022\013\0037\031CQa\021#A\002UBQ\001\021\017\005\002!#\"aG%\t\013\r;\005\031\001&\021\007-\033VG\004\002M#:\021Q\nU\007\002\035*\021qJB\001\007yI|w\016\036 \n\003=I!A\025\b\002\017A\f7m[1hK&\021A+\026\002\t\023R,'/\0312mK*\021!K\004\005\006/r!\t\001W\001\013I\026\0348-\0328eC:$HCA\016Z\021\025Qf\0131\001\\\003\025q\027-\\3t!\ryB,N\005\003)\002BQA\030\017\007\002}\013\001\"\0327f[\026tGo]\013\002AB\031\021MZ\033\016\003\tT!a\0313\002\023%lW.\036;bE2,'BA3\017\003)\031w\016\0347fGRLwN\\\005\003)\nDQ\001\033\017\005\002%\f1bZ3u\0132,W.\0328ugV\t1\fC\003l9\031\005A.\001\003s_>$X#A7\021\005!q\027BA8\003\0055\021vn\034;BGR|'\017U1uQ\")\021\017\bC\001i\0051Bo\\*ue&twmV5uQ>,H/\0213ee\026\0348\017C\003t9\031\005A/A\nu_N#(/\0338h/&$\b.\0213ee\026\0348\017\006\0026k\")QF\035a\001_!)q\017\bD\001i\005)Bo\\*fe&\fG.\033>bi&|gNR8s[\006$\b\"B=\035\r\003Q\030\001\t;p'\026\024\030.\0317ju\006$\030n\0348G_Jl\027\r^,ji\"\fE\r\032:fgN$\"!N>\t\0135B\b\031A\030\t\rudb\021\001\003\003\r)\030\016Z\013\002B\031Q\"!\001\n\007\005\raBA\002J]RD\001\"a\002\035\r\003!\021\021B\001\bo&$\b.V5e)\rY\0221\002\005\007{\006\025\001\031A@*\tq\tyA\\\005\004\003#\021!AD\"iS2$\027i\031;peB\013G\017\033\025\0069\005U\0211\004\t\004\033\005]\021bAA\r\035\t\0012+\032:jC24VM]:j_:,\026\n\022\020\002\003!1\021q\004\rA\002U\n\021a\035\005\n\003GI!\031!C\001\003K\tA\"\0227f[\026tGOU3hKb,\"!a\n\021\t\005%\0221G\007\003\003WQA!!\f\0020\005AQ.\031;dQ&twMC\002\00229\tA!\036;jY&!\021QGA\026\005\025\021VmZ3y\021!\tI$\003Q\001\n\005\035\022!D#mK6,g\016\036*fO\026D\b\005C\005\002>%\021\r\021\"\002\005?\006qQ-\0349us\006\033Go\034:QCRD\007bBA!\023\001\006i\001Y\001\020K6\004H/_!di>\024\b+\031;iA!I\021QI\005\002\002\023%\021qI\001\fe\026\fGMU3t_24X\r\006\002\002JA\031q$a\023\n\007\0055\003E\001\004PE*,7\r\036")
/*    */ public interface ActorPath extends Comparable<ActorPath>, Serializable {
/*    */   public static final long serialVersionUID = 1L;
/*    */   
/*    */   Address address();
/*    */   
/*    */   String name();
/*    */   
/*    */   ActorPath parent();
/*    */   
/*    */   ActorPath $div(String paramString);
/*    */   
/*    */   ActorPath child(String paramString);
/*    */   
/*    */   ActorPath $div(Iterable<String> paramIterable);
/*    */   
/*    */   ActorPath descendant(Iterable<String> paramIterable);
/*    */   
/*    */   Iterable<String> elements();
/*    */   
/*    */   Iterable<String> getElements();
/*    */   
/*    */   RootActorPath root();
/*    */   
/*    */   String toStringWithoutAddress();
/*    */   
/*    */   String toStringWithAddress(Address paramAddress);
/*    */   
/*    */   String toSerializationFormat();
/*    */   
/*    */   String toSerializationFormatWithAddress(Address paramAddress);
/*    */   
/*    */   int uid();
/*    */   
/*    */   ActorPath withUid(int paramInt);
/*    */   
/*    */   public class ActorPath$$anonfun$$div$1 extends AbstractFunction2<ActorPath, String, ActorPath> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final ActorPath apply(ActorPath path, String elem) {
/* 78 */       return elem.isEmpty() ? path : path.$div(elem);
/*    */     }
/*    */     
/*    */     public ActorPath$$anonfun$$div$1(ActorPath $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorPath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */