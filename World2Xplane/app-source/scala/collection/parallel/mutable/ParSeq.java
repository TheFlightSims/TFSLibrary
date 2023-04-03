package scala.collection.parallel.mutable;

import scala.collection.GenSeq;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericParTemplate;
import scala.collection.mutable.Seq;
import scala.collection.parallel.ParSeq;
import scala.collection.parallel.ParSeqLike;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\005%aaB\001\003!\003\r\ta\003\002\007!\006\0248+Z9\013\005\r!\021aB7vi\006\024G.\032\006\003\013\031\t\001\002]1sC2dW\r\034\006\003\017!\t!bY8mY\026\034G/[8o\025\005I\021!B:dC2\f7\001A\013\003\031]\031r\001A\007\022A\021:c\006\005\002\017\0375\t\001\"\003\002\021\021\t1\021I\\=SK\032\0042AE\n\026\033\0051\021B\001\013\007\005\0319UM\\*fcB\021ac\006\007\001\t\025A\002A1\001\032\005\005!\026C\001\016\036!\tq1$\003\002\035\021\t9aj\034;iS:<\007C\001\b\037\023\ty\002BA\002B]f\0042!\t\022\026\033\005\021\021BA\022\003\005-\001\026M]%uKJ\f'\r\\3\021\007\0252S#D\001\005\023\t\tA\001\005\003)WUiS\"A\025\013\005)2\021aB4f]\026\024\030nY\005\003Y%\022!cR3oKJL7\rU1s)\026l\007\017\\1uKB\021\021\005\001\t\006K=*\022GM\005\003a\021\021!\002U1s'\026\fH*[6f!\r\t\003!\006\t\004gU*R\"\001\033\013\005\r1\021B\001\0345\005\r\031V-\035\005\006q\001!\t!O\001\007I%t\027\016\036\023\025\003i\002\"AD\036\n\005qB!\001B+oSRDQA\020\001\005B}\n\021bY8na\006t\027n\0348\026\003\001\0232!Q\"G\r\021\021\005\001\001!\003\031q\022XMZ5oK6,g\016\036 \021\007!\"U&\003\002FS\t\001r)\0328fe&\0347i\\7qC:LwN\034\t\004Q\035k\023B\001%*\005M9UM\\3sS\016\004\026M]\"p[B\fg.[8o\021\025Q\005A\"\001L\003\031)\b\017Z1uKR\031!\bT)\t\0135K\005\031\001(\002\003%\004\"AD(\n\005AC!aA%oi\")!+\023a\001+\005!Q\r\\3n\021\025!\006A\"\001V\003\r\031X-]\013\002e!)q\013\001C!1\006)Ao\\*fcV\t\021gB\003[\005!\0051,\001\004QCJ\034V-\035\t\003Cq3Q!\001\002\t\002u\033\"\001\0300\021\007!zV&\003\002aS\tQ\001+\031:GC\016$xN]=\t\013\tdF\021A2\002\rqJg.\033;?)\005Y\006\"B3]\t\0071\027\001D2b]\n+\030\016\0343Ge>lWCA4q+\005A\007#\002\025jW>\f\030B\0016*\0059\031\025M\\\"p[\nLg.\032$s_6\004\"\001\\7\016\003qK!A\034#\003\t\r{G\016\034\t\003-A$Q\001\0073C\002e\0012!\t\001p\021\025\031H\f\"\001u\003)qWm\036\"vS2$WM]\013\003kj,\022A\036\t\005K]L80\003\002y\t\tA1i\\7cS:,'\017\005\002\027u\022)\001D\035b\0013A\031\021\005A=\t\013udF\021\001@\002\0279,woQ8nE&tWM]\013\004\006\025QCAA\001!\031)s/a\001\002\bA\031a#!\002\005\013aa(\031A\r\021\t\005\002\0211\001")
public interface ParSeq<T> extends GenSeq<T>, ParIterable<T>, ParSeq<T>, GenericParTemplate<T, ParSeq>, ParSeqLike<T, ParSeq<T>, Seq<T>> {
  GenericCompanion<ParSeq> companion();
  
  void update(int paramInt, T paramT);
  
  Seq<T> seq();
  
  ParSeq<T> toSeq();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParSeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */