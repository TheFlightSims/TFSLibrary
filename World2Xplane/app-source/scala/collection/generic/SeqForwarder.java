package scala.collection.generic;

import scala.Function1;
import scala.Function2;
import scala.collection.GenSeq;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.immutable.Range;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\005-gaB\001\003!\003\r\t!\003\002\r'\026\fhi\034:xCJ$WM\035\006\003\007\021\tqaZ3oKJL7M\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!\006\002\013+M!\001aC\b\037!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\t\004!E\031R\"\001\003\n\005I!!aA*fcB\021A#\006\007\001\t\0311\002\001\"b\001/\t\t\021)\005\002\0317A\021A\"G\005\0035\031\021qAT8uQ&tw\r\005\002\r9%\021QD\002\002\004\003:L\bcA\020!'5\t!!\003\002\"\005\t\t\022\n^3sC\ndWMR8so\006\024H-\032:\t\013\r\002A\021\001\023\002\r\021Jg.\033;%)\005)\003C\001\007'\023\t9cA\001\003V]&$\b\"B\025\001\r#R\023AC;oI\026\024H._5oOV\tq\002C\003-\001\021\005S&\001\004mK:<G\017[\013\002]A\021AbL\005\003a\031\0211!\0238u\021\025\021\004\001\"\0214\003\025\t\007\017\0357z)\t\031B\007C\0036c\001\007a&A\002jIbDQa\016\001\005Ba\nQ\002\\3oORD7i\\7qCJ,GC\001\030:\021\025Qd\0071\001/\003\raWM\034\005\006y\001!\t%P\001\fSN$UMZ5oK\022\fE\017\006\002?\003B\021AbP\005\003\001\032\021qAQ8pY\026\fg\016C\003Cw\001\007a&A\001y\021\025!\005\001\"\021F\0035\031XmZ7f]RdUM\\4uQR\031aFR&\t\013\035\033\005\031\001%\002\003A\004B\001D%\024}%\021!J\002\002\n\rVt7\r^5p]FBQ\001T\"A\0029\nAA\032:p[\")a\n\001C!\037\006a\001O]3gSbdUM\\4uQR\021a\006\025\005\006\0176\003\r\001\023\005\006%\002!\teU\001\013S:$W\r_,iKJ,GC\001\030U\021\0259\025\0131\001I\021\025\021\006\001\"\021W)\rqs\013\027\005\006\017V\003\r\001\023\005\006\031V\003\rA\f\005\0065\002!\teW\001\bS:$W\r_(g+\ta\006\r\006\002/;\")a,\027a\001?\006!Q\r\\3n!\t!\002\rB\003b3\n\007!MA\001C#\t\0312\004C\003[\001\021\005C-\006\002fQR\031aFZ5\t\013y\033\007\031A4\021\005QAG!B1d\005\004\021\007\"\002'd\001\004q\003\"B6\001\t\003b\027a\0037bgRLe\016Z3y\037\032,\"!\0349\025\0059r\007\"\0020k\001\004y\007C\001\013q\t\025\t'N1\001c\021\025Y\007\001\"\021s+\t\031h\017F\002/i^DQAX9A\002U\004\"\001\006<\005\013\005\f(\031\0012\t\013a\f\b\031\001\030\002\007\025tG\rC\003{\001\021\00530\001\bmCN$\030J\0343fq^CWM]3\025\0059b\b\"B$z\001\004A\005\"\002>\001\t\003rH\003\002\030\000\003\003AQaR?A\002!CQ\001_?A\0029Bq!!\002\001\t\003\n9!A\bsKZ,'o]3Ji\026\024\030\r^8s+\t\tI\001\005\003\021\003\027\031\022bAA\007\t\tA\021\n^3sCR|'\017C\004\002\022\001!\t%a\005\002\025M$\030M\035;t/&$\b.\006\003\002\026\005\rB#\002 \002\030\005\025\002\002CA\r\003\037\001\r!a\007\002\tQD\027\r\036\t\006!\005u\021\021E\005\004\003?!!AB$f]N+\027\017E\002\025\003G!a!YA\b\005\0049\002bBA\024\003\037\001\rAL\001\007_\03247/\032;\t\017\005E\001\001\"\021\002,U!\021QFA\033)\rq\024q\006\005\t\0033\tI\0031\001\0022A)\001#!\b\0024A\031A#!\016\005\r\005\fIC1\001\030\021\035\tI\004\001C!\003w\t\001\"\0328eg^KG\017[\013\005\003{\t)\005F\002?\003A\001\"!\007\0028\001\007\021\021\t\t\006!\005u\0211\t\t\004)\005\025CAB1\0028\t\007q\003C\004\002J\001!\t%a\023\002\031%tG-\032=PMNc\027nY3\026\t\0055\023Q\013\013\004]\005=\003\002CA\r\003\017\002\r!!\025\021\013A\ti\"a\025\021\007Q\t)\006\002\004b\003\017\022\rA\031\005\b\003\023\002A\021IA-+\021\tY&a\031\025\0139\ni&!\032\t\021\005e\021q\013a\001\003?\002R\001EA\017\003C\0022\001FA2\t\031\t\027q\013b\001E\"1A*a\026A\0029Bq!!\033\001\t\003\nY'\001\tmCN$\030J\0343fq>37\013\\5dKV!\021QNA;)\rq\023q\016\005\t\0033\t9\0071\001\002rA)\001#!\b\002tA\031A#!\036\005\r\005\f9G1\001c\021\035\tI\007\001C!\003s*B!a\037\002\004R)a&! \002\006\"A\021\021DA<\001\004\ty\bE\003\021\003;\t\t\tE\002\025\003\007#a!YA<\005\004\021\007B\002=\002x\001\007a\006C\004\002\n\002!\t%a#\002\033\r|g\016^1j]N\034F.[2f+\021\ti)!&\025\007y\ny\t\003\005\002\032\005\035\005\031AAI!\025\001\022QDAJ!\r!\022Q\023\003\007C\006\035%\031A\f\t\017\005e\005\001\"\021\002\034\006A1m\0348uC&t7\017F\002?\003;CaAXAL\001\004Y\002bBAQ\001\021\005\0231U\001\fG>\024(/Z:q_:$7/\006\003\002&\006MF\003BAT\003k#2APAU\021\0359\025q\024a\001\003W\003r\001DAW'\005Ef(C\002\0020\032\021\021BR;oGRLwN\034\032\021\007Q\t\031\f\002\004b\003?\023\ra\006\005\t\0033\ty\n1\001\0028B)\001#!\b\0022\"9\0211\030\001\005B\005u\026aB5oI&\034Wm]\013\003\003\003B!!1\002H6\021\0211\031\006\004\003\013$\021!C5n[V$\030M\0317f\023\021\tI-a1\003\013I\013gnZ3")
public interface SeqForwarder<A> extends Seq<A>, IterableForwarder<A> {
  Seq<A> underlying();
  
  int length();
  
  A apply(int paramInt);
  
  int lengthCompare(int paramInt);
  
  boolean isDefinedAt(int paramInt);
  
  int segmentLength(Function1<A, Object> paramFunction1, int paramInt);
  
  int prefixLength(Function1<A, Object> paramFunction1);
  
  int indexWhere(Function1<A, Object> paramFunction1);
  
  int indexWhere(Function1<A, Object> paramFunction1, int paramInt);
  
  <B> int indexOf(B paramB);
  
  <B> int indexOf(B paramB, int paramInt);
  
  <B> int lastIndexOf(B paramB);
  
  <B> int lastIndexOf(B paramB, int paramInt);
  
  int lastIndexWhere(Function1<A, Object> paramFunction1);
  
  int lastIndexWhere(Function1<A, Object> paramFunction1, int paramInt);
  
  Iterator<A> reverseIterator();
  
  <B> boolean startsWith(GenSeq<B> paramGenSeq, int paramInt);
  
  <B> boolean startsWith(GenSeq<B> paramGenSeq);
  
  <B> boolean endsWith(GenSeq<B> paramGenSeq);
  
  <B> int indexOfSlice(GenSeq<B> paramGenSeq);
  
  <B> int indexOfSlice(GenSeq<B> paramGenSeq, int paramInt);
  
  <B> int lastIndexOfSlice(GenSeq<B> paramGenSeq);
  
  <B> int lastIndexOfSlice(GenSeq<B> paramGenSeq, int paramInt);
  
  <B> boolean containsSlice(GenSeq<B> paramGenSeq);
  
  boolean contains(Object paramObject);
  
  <B> boolean corresponds(GenSeq<B> paramGenSeq, Function2<A, B, Object> paramFunction2);
  
  Range indices();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\SeqForwarder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */