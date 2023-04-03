/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Some;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001E3A!\001\002\001\017\tAQK\0349beN,GM\003\002\004\t\005\031\0010\0347\013\003\025\tQa]2bY\006\034\001a\005\002\001\021A\031\021B\003\007\016\003\tI!a\003\002\003\t\005#x.\034\t\003\033Eq!AD\b\016\003\021I!\001\005\003\002\rA\023X\rZ3g\023\t\0212C\001\004TiJLgn\032\006\003!\021A\021\"\006\001\003\002\003\006I\001\004\f\002\t\021\fG/Y\005\003+)AQ\001\007\001\005\002e\ta\001P5oSRtDC\001\016\034!\tI\001\001C\003\026/\001\007A\002C\003\036\001\021\005c$A\006ck&dGm\025;sS:<GCA\020,!\t\001\003F\004\002\"M9\021!%J\007\002G)\021AEB\001\007yI|w\016\036 \n\003\025I!a\n\003\002\017A\f7m[1hK&\021\021F\013\002\016'R\024\030N\\4Ck&dG-\032:\013\005\035\"\001\"\002\027\035\001\004y\022AA:c\017\025q#\001#\0010\003!)f\016]1sg\026$\007CA\0051\r\025\t!\001#\0012'\r\001$'\016\t\003\035MJ!\001\016\003\003\r\005s\027PU3g!\tqa'\003\0028\t\ta1+\032:jC2L'0\0312mK\")\001\004\rC\001sQ\tq\006C\003<a\021\005A(A\003baBd\027\020\006\002\033{!)QC\017a\001\031!)q\b\rC\001\001\0069QO\\1qa2LHCA!E!\rq!\tD\005\003\007\022\021AaU8nK\")QI\020a\0015\005\t\001\020C\004Ha\005\005I\021\002%\002\027I,\027\r\032*fg>dg/\032\013\002\023B\021!jT\007\002\027*\021A*T\001\005Y\006twMC\001O\003\021Q\027M^1\n\005A[%AB(cU\026\034G\017")
/*    */ public class Unparsed extends Atom<String> {
/*    */   public Unparsed(String data) {
/* 17 */     super(data);
/*    */   }
/*    */   
/*    */   public StringBuilder buildString(StringBuilder sb) {
/* 23 */     return sb.append((String)data());
/*    */   }
/*    */   
/*    */   public static Some<String> unapply(Unparsed paramUnparsed) {
/*    */     return Unparsed$.MODULE$.unapply(paramUnparsed);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Unparsed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */