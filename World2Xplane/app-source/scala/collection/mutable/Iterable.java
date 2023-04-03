package scala.collection.mutable;

import scala.collection.Iterable;
import scala.collection.IterableLike;
import scala.collection.Parallelizable;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParIterable;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001I4q!\001\002\021\002\007\005\021B\001\005Ji\026\024\030M\0317f\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005))2c\002\001\f\037y\t\003\006\f\t\003\0315i\021AB\005\003\035\031\021a!\0218z%\0264\007c\001\t\022'5\t!!\003\002\023\005\tYAK]1wKJ\034\030M\0317f!\t!R\003\004\001\005\013Y\001!\031A\f\003\003\005\013\"\001G\016\021\0051I\022B\001\016\007\005\035qu\016\0365j]\036\004\"\001\004\017\n\005u1!aA!osB\031q\004I\n\016\003\021I!!\001\003\021\t\t*3cJ\007\002G)\021A\005B\001\bO\026tWM]5d\023\t13E\001\016HK:,'/[2Ue\0064XM]:bE2,G+Z7qY\006$X\r\005\002\021\001A!q$K\n,\023\tQCA\001\007Ji\026\024\030M\0317f\031&\\W\rE\002\021\001M\001BaH\027\024_%\021a\006\002\002\017!\006\024\030\r\0347fY&T\030M\0317f!\r\001DgE\007\002c)\0211A\r\006\003g\021\t\001\002]1sC2dW\r\\\005\003kE\0221\002U1s\023R,'/\0312mK\")q\007\001C\001q\0051A%\0338ji\022\"\022!\017\t\003\031iJ!a\017\004\003\tUs\027\016\036\005\006{\001!\tEP\001\nG>l\007/\0318j_:,\022a\020\t\004E\001;\023BA!$\005A9UM\\3sS\016\034u.\0349b]&|g\016\003\004D\001\001&\t\006R\001\fa\006\0248i\\7cS:,'/F\001F!\0211uiE\030\016\003IJ!\001\023\032\003\021\r{WNY5oKJDQA\023\001\005B-\0131a]3r+\005Ys!B'\003\021\003q\025\001C%uKJ\f'\r\\3\021\005Aye!B\001\003\021\003\0016cA(R)B\031!EU\024\n\005M\033#!F$f]R\023\030M^3sg\006\024G.\032$bGR|'/\037\t\004EU;\023B\001,$\005I!&/\031<feN\f'\r\\3GC\016$xN]=\t\013a{E\021A-\002\rqJg.\033;?)\005q\005\"B.P\t\007a\026\001D2b]\n+\030\016\0343Ge>lWCA/g+\005q\006#\002\022`C\026<\027B\0011$\0051\031\025M\034\"vS2$gI]8n!\t\0217-D\001P\023\t!\007I\001\003D_2d\007C\001\013g\t\0251\"L1\001\030!\r\001\002!\032\005\006S>#\tA[\001\013]\026<()^5mI\026\024XCA6q+\005a\007\003\002\tn_FL!A\034\002\003\017\t+\030\016\0343feB\021A\003\035\003\006-!\024\ra\006\t\004!\001y\007")
public interface Iterable<A> extends Traversable<A>, Iterable<A>, GenericTraversableTemplate<A, Iterable>, IterableLike<A, Iterable<A>>, Parallelizable<A, ParIterable<A>> {
  GenericCompanion<Iterable> companion();
  
  Combiner<A, ParIterable<A>> parCombiner();
  
  Iterable<A> seq();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Iterable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */