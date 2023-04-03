package scala.collection.parallel;

import scala.Function1;
import scala.Function2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\005%a\001C\001\003!\003\r\t\001\002\005\003)\005+x-\\3oi\026$7+Z9Ji\026\024\030\r^8s\025\t\031A!\001\005qCJ\fG\016\\3m\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\013\003\023Q\0312\001\001\006\017!\tYA\"D\001\007\023\tiaA\001\004B]f\024VM\032\t\004\037A\021R\"\001\002\n\005E\021!!G!vO6,g\016^3e\023R,'/\0312mK&#XM]1u_J\004\"a\005\013\r\001\0211Q\003\001CC\002]\021\021\001V\002\001#\tA2\004\005\002\f3%\021!D\002\002\b\035>$\b.\0338h!\tYA$\003\002\036\r\t\031\021I\\=\t\013}\001A\021\001\021\002\r\021Jg.\033;%)\005\t\003CA\006#\023\t\031cA\001\003V]&$\b\"B\023\001\r\0031\023!\003:f[\006Lg.\0338h+\0059\003CA\006)\023\tIcAA\002J]RDQa\013\001\005\0021\nA\002\035:fM&DH*\0328hi\"$\"aJ\027\t\0139R\003\031A\030\002\tA\024X\r\032\t\005\027A\022\"'\003\0022\r\tIa)\0368di&|g.\r\t\003\027MJ!\001\016\004\003\017\t{w\016\\3b]\")a\007\001C!o\005Q\021N\0343fq^CWM]3\025\005\035B\004\"\002\0306\001\004y\003\"\002\036\001\t\003Y\024A\0047bgRLe\016Z3y/\",'/\032\013\003OqBQAL\035A\002=BQA\020\001\005\002}\n1bY8se\026\034\bo\0348egV\021\001)\025\013\003\003N#\"A\r\"\t\013\rk\004\031\001#\002\tQD\027\r\036\t\004\0136\003fB\001$L\035\t9%*D\001I\025\tIe#\001\004=e>|GOP\005\002\017%\021AJB\001\ba\006\0347.Y4f\023\tquJ\001\005Ji\026\024\030\r^8s\025\tae\001\005\002\024#\022)!+\020b\001/\t\t1\013C\003U{\001\007Q+\001\003d_J\024\b#B\006W%A\023\024BA,\007\005%1UO\\2uS>t'\007C\003Z\001\021\005!,\001\tsKZ,'o]33G>l'-\0338feV\0311\f\0313\025\005q3\007\003B\b^?\016L!A\030\002\003\021\r{WNY5oKJ\004\"a\0051\005\013\005D&\031\0012\003\003U\013\"AE\016\021\005M!G!B3Y\005\0049\"\001\002+iSNDQa\032-A\002q\013!a\0312\t\013%\004A\021\0016\002'I,g/\032:tK6\013\007OM2p[\nLg.\032:\026\007-t\007\017F\002meV\004BaD/n_B\0211C\034\003\006%\"\024\ra\006\t\003'A$Q!\0355C\002]\021A\001\0265bi\")1\017\033a\001i\006\ta\r\005\003\faIi\007\"B4i\001\004a\007\"B<\001\t\003A\030\001E;qI\006$X\r\032\032d_6\024\027N\\3s+\rIHP \013\007u~\f\031!a\002\021\t=i60 \t\003'q$Q!\031<C\002\t\004\"a\005@\005\013E4(\031A\f\t\r\005\005a\0171\001(\003\025Ig\016Z3y\021\031\t)A\036a\001w\006!Q\r\\3n\021\0259g\0171\001{\001")
public interface AugmentedSeqIterator<T> extends AugmentedIterableIterator<T> {
  int remaining();
  
  int prefixLength(Function1<T, Object> paramFunction1);
  
  int indexWhere(Function1<T, Object> paramFunction1);
  
  int lastIndexWhere(Function1<T, Object> paramFunction1);
  
  <S> boolean corresponds(Function2<T, S, Object> paramFunction2, Iterator<S> paramIterator);
  
  <U, This> Combiner<U, This> reverse2combiner(Combiner<U, This> paramCombiner);
  
  <S, That> Combiner<S, That> reverseMap2combiner(Function1<T, S> paramFunction1, Combiner<S, That> paramCombiner);
  
  <U, That> Combiner<U, That> updated2combiner(int paramInt, U paramU, Combiner<U, That> paramCombiner);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\AugmentedSeqIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */