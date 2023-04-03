package scala.collection.mutable;

import scala.collection.GenTraversableOnce;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.script.Message;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\005ugaB\001\003!\003\r\t!\003\002\023'ft7\r\033:p]&TX\r\032\"vM\032,'O\003\002\004\t\0059Q.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001QC\001\006\026'\r\0011b\004\t\003\0315i\021AB\005\003\035\031\021a!\0218z%\0264\007c\001\t\022'5\t!!\003\002\023\005\t1!)\0364gKJ\004\"\001F\013\r\001\021)a\003\001b\001/\t\t\021)\005\002\0317A\021A\"G\005\0035\031\021qAT8uQ&tw\r\005\002\r9%\021QD\002\002\004\003:L\b\"B\020\001\t\003\001\023A\002\023j]&$H\005F\001\"!\ta!%\003\002$\r\t!QK\\5u\021\031)\003\001%C\001M\0051A.\0328hi\",\022a\n\t\003\031!J!!\013\004\003\007%sG\017\003\004,\001A%\t\001L\001\tSR,'/\031;peV\tQ\006E\002/_Mi\021\001B\005\003a\021\021\001\"\023;fe\006$xN\035\005\007e\001\001J\021A\032\002\013\005\004\b\017\\=\025\005M!\004\"B\0332\001\0049\023!\0018\t\r]\002\001\023\"\0019\003!!\003\017\\;tI\025\fHCA\035;\033\005\001\001\"B\0367\001\004\031\022\001B3mK6DQ!\020\001\005By\n!\002\n9mkN$\003\017\\;t)\tyD\t\005\002:\001&\021\021I\021\002\005'\026dg-\003\002D\t\tyAK]1wKJ\034\030M\0317f\031&\\W\rC\003Fy\001\007a)\001\002ygB\031afR\n\n\005!#!AE$f]R\023\030M^3sg\006\024G.Z(oG\026DQA\023\001\005B-\013Q\002\n9mkN$\003\017\\;tI\025\fHCA\035M\021\025)\025\n1\001N!\rqcjE\005\003\037\022\021q\002\026:bm\026\0248/\0312mK>s7-\032\005\006#\002!\tEU\001\007CB\004XM\0343\025\005\005\032\006\"\002+Q\001\004)\026!B3mK6\034\bc\001\007W'%\021qK\002\002\013yI,\007/Z1uK\022t\004\"B-\001\t\003R\026!C1qa\026tG-\0217m)\t\t3\fC\003F1\002\007Q\n\003\004^\001A%\tAX\001\017IAdWo\035\023fc\022\032w\016\\8o)\tIt\fC\003<9\002\0071\003C\003b\001\021\005#-A\n%a2,8\017\n9mkN$S-\035\023d_2|g\016\006\002:G\")Q\t\031a\001\033\")Q\r\001C!M\0069\001O]3qK:$GCA\021h\021\025!F\r1\001V\021\025I\007\001\"\021k\003)\001(/\0329f]\022\fE\016\034\013\003C-DQ!\0225A\0025CQ!\034\001\005B9\fa!\0338tKJ$HcA\021pa\")Q\007\034a\001O!)A\013\034a\001+\"1!\017\001I\005\002M\f\021\"\0338tKJ$\030\t\0347\025\007\005\"X\017C\0036c\002\007q\005C\003Fc\002\007a\017E\002/oNI!\001\037\003\003\027Q\023\030M^3sg\006\024G.\032\005\007u\002\001J\021A>\002\rU\004H-\031;f)\r\tC0 \005\006ke\004\ra\n\005\006}f\004\raE\001\b]\026<X\r\\3n\021!\t\t\001\001I\005\002\005\r\021A\002:f[>4X\rF\002\024\003\013AQ!N@A\002\035Bq!!\003\001!\023\005\001%A\003dY\026\f'\017C\004\002\016\001!\t%a\004\002\025\021bWm]:%Y\026\0348\017F\002\"\003#A\001\"a\005\002\f\001\007\021QC\001\004G6$\007#BA\f\003;\031RBAA\r\025\r\tY\002B\001\007g\016\024\030\016\035;\n\t\005}\021\021\004\002\b\033\026\0348/Y4f\021\035\t\031\003\001C!\003K\tQa\0317p]\026$\022a\020\005\b\003S\001A\021IA\026\003!A\027m\0355D_\022,G#A\024\t\027\005=\002!!A\001\n\0231\023\021G\001\rgV\004XM\035\023mK:<G\017[\005\004K\005M\022bAA\033\005\tQ!)\0364gKJd\025n[3\t\027\005e\002!!A\001\n\023a\0231H\001\017gV\004XM\035\023ji\026\024\030\r^8s\023\rY\023QH\005\004\003!!\001D%uKJ\f'\r\\3MS.,\007\002DA\"\001\005\005\t\021\"\003\002F\005%\023aC:va\026\024H%\0319qYf$2aEA$\021\031)\024\021\ta\001O%\031!'a\r\t\031\0055\003!!A\001\n\023\ty%a\025\002\035M,\b/\032:%IAdWo\035\023fcR\031\021(!\025\t\rm\nY\0051\001\024\023\r9\0241\007\005\r\003/\002\021\021!A\005\n\005e\023QL\001\021gV\004XM\035\023%a2,8\017\n9mkN$2aDA.\021\031)\025Q\013a\001\r&\031Q(a\r\t\031\005\005\004!!A\001\n\023\t\031'a\032\002'M,\b/\032:%IAdWo\035\023qYV\034H%Z9\025\007e\n)\007\003\004F\003?\002\r!T\005\004\025\006%\024\002BA6\003[\022\001b\022:po\006\024G.\032\006\004\003_\"\021aB4f]\026\024\030n\031\005\r\003g\002\021\021!A\005\n\005U\024\021P\001\020gV\004XM\035\023baB,g\016Z!mYR\031\021%a\036\t\r\025\013\t\b1\001N\023\rI\0261\007\005\r\003{\002\021\021!A\005\n\005}\0241Q\001\025gV\004XM\035\023%a2,8\017J3rI\r|Gn\0348\025\007e\n\t\t\003\004<\003w\002\raE\005\004;\006M\002\002DAD\001\005\005\t\021\"\003\002\n\0065\025!G:va\026\024H\005\n9mkN$\003\017\\;tI\025\fHeY8m_:$2!OAF\021\031)\025Q\021a\001\033&\031\021-a\r\t\031\005E\005!!A\001\n\023\t\031*a&\002!M,\b/\032:%aJ,\007/\0328e\0032dGcA\021\002\026\"1Q)a$A\0025K1![A\032\0211\tY\nAA\001\002\023%\021QTAR\003=\031X\017]3sI%t7/\032:u\0032dG#B\021\002 \006\005\006BB\033\002\032\002\007q\005\003\004U\0033\003\rA^\005\004e\006M\002\002DAT\001\005\005\t\021\"\003\002*\006=\026\001D:va\026\024H%\0369eCR,G#B\021\002,\0065\006BB\033\002&\002\007q\005\003\004\003K\003\raE\005\004u\006M\002\002DAZ\001\005\005\t\021\"\003\0026\006e\026\001D:va\026\024HE]3n_Z,GcA\n\0028\"1Q'!-A\002\035JA!!\001\0024!Y\021Q\030\001\002\002\003%I\001IA`\003-\031X\017]3sI\rdW-\031:\n\t\005%\0211\007\005\r\003\007\004\021\021!A\005\n\005\025\027\021Z\001\021gV\004XM\035\023%Y\026\0348\017\n7fgN$2!IAd\021!\t\031\"!1A\002\005U\021\002BA\007\003gAA\"!4\001\003\003\005I\021BAh\003#\f1b];qKJ$3\r\\8oKR\tq\"\003\003\002$\005M\002\002DAk\001\005\005\t\021\"\003\002,\005]\027AD:va\026\024H\005[1tQ\016{G-Z\005\005\003S\tI.C\002\002\\\022\021!bR3o'\026\fH*[6f\001")
public interface SynchronizedBuffer<A> extends Buffer<A> {
  int scala$collection$mutable$SynchronizedBuffer$$super$length();
  
  Iterator<A> scala$collection$mutable$SynchronizedBuffer$$super$iterator();
  
  A scala$collection$mutable$SynchronizedBuffer$$super$apply(int paramInt);
  
  SynchronizedBuffer<A> scala$collection$mutable$SynchronizedBuffer$$super$$plus$eq(A paramA);
  
  Buffer<A> scala$collection$mutable$SynchronizedBuffer$$super$$plus$plus(GenTraversableOnce<A> paramGenTraversableOnce);
  
  SynchronizedBuffer<A> scala$collection$mutable$SynchronizedBuffer$$super$$plus$plus$eq(TraversableOnce<A> paramTraversableOnce);
  
  void scala$collection$mutable$SynchronizedBuffer$$super$appendAll(TraversableOnce<A> paramTraversableOnce);
  
  SynchronizedBuffer<A> scala$collection$mutable$SynchronizedBuffer$$super$$plus$eq$colon(A paramA);
  
  SynchronizedBuffer<A> scala$collection$mutable$SynchronizedBuffer$$super$$plus$plus$eq$colon(TraversableOnce<A> paramTraversableOnce);
  
  void scala$collection$mutable$SynchronizedBuffer$$super$prependAll(TraversableOnce<A> paramTraversableOnce);
  
  void scala$collection$mutable$SynchronizedBuffer$$super$insertAll(int paramInt, Traversable<A> paramTraversable);
  
  void scala$collection$mutable$SynchronizedBuffer$$super$update(int paramInt, A paramA);
  
  A scala$collection$mutable$SynchronizedBuffer$$super$remove(int paramInt);
  
  void scala$collection$mutable$SynchronizedBuffer$$super$clear();
  
  void scala$collection$mutable$SynchronizedBuffer$$super$$less$less(Message<A> paramMessage);
  
  Buffer<A> scala$collection$mutable$SynchronizedBuffer$$super$clone();
  
  int scala$collection$mutable$SynchronizedBuffer$$super$hashCode();
  
  int length();
  
  Iterator<A> iterator();
  
  A apply(int paramInt);
  
  SynchronizedBuffer<A> $plus$eq(A paramA);
  
  Buffer<A> $plus$plus(GenTraversableOnce<A> paramGenTraversableOnce);
  
  SynchronizedBuffer<A> $plus$plus$eq(TraversableOnce<A> paramTraversableOnce);
  
  void append(Seq<A> paramSeq);
  
  void appendAll(TraversableOnce<A> paramTraversableOnce);
  
  SynchronizedBuffer<A> $plus$eq$colon(A paramA);
  
  SynchronizedBuffer<A> $plus$plus$eq$colon(TraversableOnce<A> paramTraversableOnce);
  
  void prepend(Seq<A> paramSeq);
  
  void prependAll(TraversableOnce<A> paramTraversableOnce);
  
  void insert(int paramInt, Seq<A> paramSeq);
  
  void insertAll(int paramInt, Traversable<A> paramTraversable);
  
  void update(int paramInt, A paramA);
  
  A remove(int paramInt);
  
  void clear();
  
  void $less$less(Message<A> paramMessage);
  
  Buffer<A> clone();
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\SynchronizedBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */