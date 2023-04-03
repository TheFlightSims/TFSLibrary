package scala.math;

import scala.Function1;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001%4q!\001\002\021\002\007\005qA\001\tQCJ$\030.\0317ms>\023H-\032:fI*\0211\001B\001\005[\006$\bNC\001\006\003\025\0318-\0317b\007\001)\"\001C\025\024\005\001I\001C\001\006\f\033\005!\021B\001\007\005\005\031\te.\037*fM\")a\002\001C\001\037\0051A%\0338ji\022\"\022\001\005\t\003\025EI!A\005\003\003\tUs\027\016\036\005\006)\0011\t!F\001\riJL8i\\7qCJ,Gk\\\013\003-\025\"\"a\006\033\025\005aq\002c\001\006\0327%\021!\004\002\002\007\037B$\030n\0348\021\005)a\022BA\017\005\005\rIe\016\036\005\b?M\t\t\021q\001!\003))g/\0333f]\016,G%\r\t\005\025\005\032#'\003\002#\t\tIa)\0368di&|g.\r\t\003I\025b\001\001B\003''\t\007qEA\001C#\tAs\006\005\002%S\0211!\006\001CC\002-\022\021!Q\t\003Y=\002\"AC\027\n\0059\"!a\002(pi\"Lgn\032\t\003\025AJ!!\r\003\003\007\005s\027\020E\0024\001\rj\021A\001\005\006kM\001\raI\001\005i\"\fG\017C\0038\001\021\005\001(A\003%Y\026\0348/\006\002:\005R\021!\b\022\013\003wy\002\"A\003\037\n\005u\"!a\002\"p_2,\027M\034\005\bY\n\t\021q\001A\003))g/\0333f]\016,GE\r\t\005\025\005\n5\t\005\002%\005\022)aE\016b\001OA\0311\007A!\t\013U2\004\031A!\t\013\031\003A\021A$\002\021\021:'/Z1uKJ,\"\001\023(\025\005%\003FCA\036K\021\035YU)!AA\0041\013!\"\032<jI\026t7-\032\0234!\021Q\021%T(\021\005\021rE!\002\024F\005\0049\003cA\032\001\033\")Q'\022a\001\033\")!\013\001C\001'\006AA\005\\3tg\022*\027/\006\002U5R\021Q\013\030\013\003wYCqaV)\002\002\003\017\001,\001\006fm&$WM\\2fIQ\002BAC\021Z7B\021AE\027\003\006ME\023\ra\n\t\004g\001I\006\"B\033R\001\004I\006\"\0020\001\t\003y\026a\003\023he\026\fG/\032:%KF,\"\001\0314\025\005\005DGCA\036c\021\035\031W,!AA\004\021\f!\"\032<jI\026t7-\032\0236!\021Q\021%Z4\021\005\0212G!\002\024^\005\0049\003cA\032\001K\")Q'\030a\001K\002")
public interface PartiallyOrdered<A> {
  <B> Option<Object> tryCompareTo(B paramB, Function1<B, PartiallyOrdered<B>> paramFunction1);
  
  <B> boolean $less(B paramB, Function1<B, PartiallyOrdered<B>> paramFunction1);
  
  <B> boolean $greater(B paramB, Function1<B, PartiallyOrdered<B>> paramFunction1);
  
  <B> boolean $less$eq(B paramB, Function1<B, PartiallyOrdered<B>> paramFunction1);
  
  <B> boolean $greater$eq(B paramB, Function1<B, PartiallyOrdered<B>> paramFunction1);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\PartiallyOrdered.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */