package scala.collection;

import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\t4q!\001\002\021\002\007\005qA\001\005Ji\026\024\030M\0317f\025\t\031A!\001\006d_2dWm\031;j_:T\021!B\001\006g\016\fG.Y\002\001+\tA1c\005\004\001\0235arD\n\t\003\025-i\021\001B\005\003\031\021\021a!\0218z%\0264\007c\001\b\020#5\t!!\003\002\021\005\tYAK]1wKJ\034\030M\0317f!\t\0212\003\004\001\005\rQ\001AQ1\001\026\005\005\t\025C\001\f\032!\tQq#\003\002\031\t\t9aj\034;iS:<\007C\001\006\033\023\tYBAA\002B]f\0042AD\017\022\023\tq\"AA\006HK:LE/\032:bE2,\007\003\002\021$#\025j\021!\t\006\003E\t\tqaZ3oKJL7-\003\002%C\tQr)\0328fe&\034GK]1wKJ\034\030M\0317f)\026l\007\017\\1uKB\021a\002\001\t\005\035\035\n\022&\003\002)\005\ta\021\n^3sC\ndW\rT5lKB\031a\002A\t\t\013-\002A\021\001\027\002\r\021Jg.\033;%)\005i\003C\001\006/\023\tyCA\001\003V]&$\b\"B\031\001\t\003\022\024!C2p[B\fg.[8o+\005\031\004c\001\0215K%\021Q'\t\002\021\017\026tWM]5d\007>l\007/\0318j_:DQa\016\001\005Ba\n1a]3r+\005Is!\002\036\003\021\003Y\024\001C%uKJ\f'\r\\3\021\0059ad!B\001\003\021\003i4c\001\037?\003B\031\001eP\023\n\005\001\013#!F$f]R\023\030M^3sg\006\024G.\032$bGR|'/\037\t\004A\t+\023BA\"\"\005I!&/\031<feN\f'\r\\3GC\016$xN]=\t\013\025cD\021\001$\002\rqJg.\033;?)\005Y\004\"\002%=\t\007I\025\001D2b]\n+\030\016\0343Ge>lWC\001&T+\005Y\005#\002\021M\035J#\026BA'\"\0051\031\025M\034\"vS2$gI]8n!\ty\005+D\001=\023\t\tFG\001\003D_2d\007C\001\nT\t\025!rI1\001\026!\rq\001A\025\005\006-r\"\taV\001\013]\026<()^5mI\026\024XC\001-a+\005I\006\003\002.^?\006l\021a\027\006\0039\n\tq!\\;uC\ndW-\003\002_7\n9!)^5mI\026\024\bC\001\na\t\025!RK1\001\026!\rq\001a\030")
public interface Iterable<A> extends Traversable<A>, GenIterable<A>, GenericTraversableTemplate<A, Iterable>, IterableLike<A, Iterable<A>> {
  GenericCompanion<Iterable> companion();
  
  Iterable<A> seq();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\Iterable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */