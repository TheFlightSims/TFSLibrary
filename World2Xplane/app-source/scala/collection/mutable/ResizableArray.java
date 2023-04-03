package scala.collection.mutable;

import scala.Function1;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate;
import scala.reflect.ScalaSignature;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes = "\006\001\005\035daB\001\003!\003\r\t!\003\002\017%\026\034\030N_1cY\026\f%O]1z\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005))2#\002\001\f\037y)\003C\001\007\016\033\0051\021B\001\b\007\005\031\te.\037*fMB\031\001#E\n\016\003\tI!A\005\002\003\025%sG-\032=fIN+\027\017\005\002\025+1\001A!\002\f\001\005\0049\"!A!\022\005aY\002C\001\007\032\023\tQbAA\004O_RD\027N\\4\021\0051a\022BA\017\007\005\r\te.\037\t\005?\t\032B%D\001!\025\t\tC!A\004hK:,'/[2\n\005\r\002#AG$f]\026\024\030n\031+sCZ,'o]1cY\026$V-\0349mCR,\007C\001\t\001!\021\001be\005\025\n\005\035\022!aE%oI\026DX\rZ*fc>\003H/[7ju\026$\007c\001\t\001'!)!\006\001C\001W\0051A%\0338ji\022\"\022\001\f\t\003\0315J!A\f\004\003\tUs\027\016\036\005\006a\001!\t%M\001\nG>l\007/\0318j_:,\022A\r\t\004?M\"\023B\001\033!\005A9UM\\3sS\016\034u.\0349b]&|g\016C\0037\001\021Eq'A\006j]&$\030.\0317TSj,W#\001\035\021\0051I\024B\001\036\007\005\rIe\016\036\005\by\001\001\r\021\"\005>\003\025\t'O]1z+\005q\004c\001\007@\027%\021\001I\002\002\006\003J\024\030-\037\005\b\005\002\001\r\021\"\005D\003%\t'O]1z?\022*\027\017\006\002-\t\"9Q)QA\001\002\004q\024a\001=%c!1q\t\001Q!\ny\na!\031:sCf\004\003bB%\001\001\004%\tbN\001\006g&TX\r\r\005\b\027\002\001\r\021\"\005M\003%\031\030N_31?\022*\027\017\006\002-\033\"9QISA\001\002\004A\004BB(\001A\003&\001(\001\004tSj,\007\007\t\005\006#\002!\taN\001\007Y\026tw\r\0365\t\013M\003A\021\001+\002\013\005\004\b\017\\=\025\005M)\006\"\002,S\001\004A\024aA5eq\")\001\f\001C\0013\0061Q\017\0353bi\026$2\001\f.\\\021\0251v\0131\0019\021\025av\0131\001\024\003\021)G.Z7\t\013y\003A\021I0\002\017\031|'/Z1dQV\021\001m\032\013\003Y\005DQAY/A\002\r\f\021A\032\t\005\031\021\034b-\003\002f\r\tIa)\0368di&|g.\r\t\003)\035$Q\001[/C\002]\021\021!\026\005\006U\002!\te[\001\fG>\004\030\020V8BeJ\f\0270\006\002mcR!A&\034;w\021\025q\027\0161\001p\003\tA8\017E\002\rA\004\"\001F9\005\013IL'\031A:\003\003\t\013\"aE\016\t\013UL\007\031\001\035\002\013M$\030M\035;\t\013]L\007\031\001\035\002\0071,g\016C\003z\001\021\005!0\001\007sK\022,8-\032+p'&TX\r\006\002-w\")A\020\037a\001q\005\0211O\037\005\006}\002!\tb`\001\013K:\034XO]3TSj,Gc\001\027\002\002!1\0211A?A\002a\n\021A\034\005\b\003\017\001A\021CA\005\003\021\031x/\0319\025\0131\nY!a\004\t\017\0055\021Q\001a\001q\005\t\021\rC\004\002\022\005\025\001\031\001\035\002\003\tDq!!\006\001\t#\t9\"\001\003d_BLHc\002\027\002\032\005u\021q\004\005\b\0037\t\031\0021\0019\003\005i\007bBA\002\003'\001\r\001\017\005\007o\006M\001\031\001\035\b\017\005\r\"\001#\001\002&\005q!+Z:ju\006\024G.Z!se\006L\bc\001\t\002(\0311\021A\001E\001\003S\031B!a\n\002,A!q$!\f%\023\r\ty\003\t\002\013'\026\fh)Y2u_JL\b\002CA\032\003O!\t!!\016\002\rqJg.\033;?)\t\t)\003\003\005\002:\005\035B1AA\036\0031\031\027M\034\"vS2$gI]8n+\021\ti$a\024\026\005\005}\002#C\020\002B\005\025\023QJA)\023\r\t\031\005\t\002\r\007\006t')^5mI\032\023x.\034\t\005\003\017\nI%\004\002\002(%\031\0211J\032\003\t\r{G\016\034\t\004)\005=CA\002\f\0028\t\007q\003\005\003\021\001\0055\003\002CA+\003O!\t!a\026\002\0259,wOQ;jY\022,'/\006\003\002Z\005\rTCAA.!\035\001\022QLA1\003KJ1!a\030\003\005\035\021U/\0337eKJ\0042\001FA2\t\0311\0221\013b\001/A!\001\003AA1\001")
public interface ResizableArray<A> extends IndexedSeq<A>, GenericTraversableTemplate<A, ResizableArray>, IndexedSeqOptimized<A, ResizableArray<A>> {
  GenericCompanion<ResizableArray> companion();
  
  int initialSize();
  
  Object[] array();
  
  @TraitSetter
  void array_$eq(Object[] paramArrayOfObject);
  
  int size0();
  
  @TraitSetter
  void size0_$eq(int paramInt);
  
  int length();
  
  A apply(int paramInt);
  
  void update(int paramInt, A paramA);
  
  <U> void foreach(Function1<A, U> paramFunction1);
  
  <B> void copyToArray(Object paramObject, int paramInt1, int paramInt2);
  
  void reduceToSize(int paramInt);
  
  void ensureSize(int paramInt);
  
  void swap(int paramInt1, int paramInt2);
  
  void copy(int paramInt1, int paramInt2, int paramInt3);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ResizableArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */