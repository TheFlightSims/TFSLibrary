package scala.collection.parallel;

import scala.collection.Parallel;
import scala.collection.generic.Sizing;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes = "\006\001!4q!\001\002\021\002\007\005\021B\001\005D_6\024\027N\\3s\025\t\031A!\001\005qCJ\fG\016\\3m\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\rQq#I\n\006\001-y1%\013\t\003\0315i\021AB\005\003\035\031\021a!\0218z%\0264\007\003\002\t\024+\001j\021!\005\006\003%\021\tq!\\;uC\ndW-\003\002\025#\t9!)^5mI\026\024\bC\001\f\030\031\001!a\001\007\001\t\006\004I\"\001B#mK6\f\"AG\017\021\0051Y\022B\001\017\007\005\035qu\016\0365j]\036\004\"\001\004\020\n\005}1!aA!osB\021a#\t\003\007E\001!)\031A\r\003\005Q{\007C\001\023(\033\005)#B\001\024\005\003\0359WM\\3sS\016L!\001K\023\003\rMK'0\0338h!\tQ3&D\001\005\023\taCA\001\005QCJ\fG\016\\3m\021\025q\003\001\"\0010\003\031!\023N\\5uIQ\t\001\007\005\002\rc%\021!G\002\002\005+:LG\017C\0045\001\001\007I\021A\033\002)}\033w.\0342j]\026\024H+Y:l'V\004\bo\034:u+\0051\004CA\0349\033\005\021\021BA\035\003\005-!\026m]6TkB\004xN\035;\t\017m\002\001\031!C\001y\005ArlY8nE&tWM\035+bg.\034V\017\0359peR|F%Z9\025\005Aj\004b\002 ;\003\003\005\rAN\001\004q\022\n\004B\002!\001A\003&a'A\013`G>l'-\0338feR\0137o[*vaB|'\017\036\021)\005}\022\005C\001\007D\023\t!eA\001\005w_2\fG/\0337fQ\tyd\t\005\002\r\017&\021\001J\002\002\niJ\fgn]5f]RDQA\023\001\005\002U\n1cY8nE&tWM\035+bg.\034V\017\0359peRDQ\001\024\001\005\0025\013qcY8nE&tWM\035+bg.\034V\017\0359peR|F%Z9\025\005Ar\005\"B(L\001\0041\024aA2ug\")\021\013\001D\001%\00691m\\7cS:,WcA*W5R\021A+\030\t\005o\001)\026\f\005\002\027-\022)q\013\025b\0011\n\ta*\005\002\033+A\021aC\027\003\0067B\023\r\001\030\002\006\035\026<Hk\\\t\003AuAQA\030)A\002Q\013Qa\034;iKJDQ\001\031\001\005\002\005\f1bY1o\005\026\034\006.\031:fIV\t!\r\005\002\rG&\021AM\002\002\b\005>|G.Z1o\021\0251\007\001\"\001h\003U\021Xm];mi^KG\017\033+bg.\034V\017\0359peR,\022\001\t")
public interface Combiner<Elem, To> extends Builder<Elem, To>, Sizing, Parallel {
  TaskSupport _combinerTaskSupport();
  
  @TraitSetter
  void _combinerTaskSupport_$eq(TaskSupport paramTaskSupport);
  
  TaskSupport combinerTaskSupport();
  
  void combinerTaskSupport_$eq(TaskSupport paramTaskSupport);
  
  <N extends Elem, NewTo> Combiner<N, NewTo> combine(Combiner<N, NewTo> paramCombiner);
  
  boolean canBeShared();
  
  To resultWithTaskSupport();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\Combiner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */