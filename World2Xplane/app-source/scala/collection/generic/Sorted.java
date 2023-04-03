package scala.collection.generic;

import scala.Option;
import scala.collection.Iterator;
import scala.collection.SortedSet;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001I4q!\001\002\021\002\007\005\021B\001\004T_J$X\r\032\006\003\007\021\tqaZ3oKJL7M\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!F\002\013MM\032\"\001A\006\021\0051iQ\"\001\004\n\00591!AB!osJ+g\rC\003\021\001\021\005\021#\001\004%S:LG\017\n\013\002%A\021AbE\005\003)\031\021A!\0268ji\")a\003\001D\001/\005AqN\0353fe&tw-F\001\031!\rI\022\005\n\b\0035}q!a\007\020\016\003qQ!!\b\005\002\rq\022xn\034;?\023\0059\021B\001\021\007\003\035\001\030mY6bO\026L!AI\022\003\021=\023H-\032:j]\036T!\001\t\004\021\005\0252C\002\001\003\006O\001\021\r\001\013\002\002\027F\021\021\006\f\t\003\031)J!a\013\004\003\0179{G\017[5oOB\021A\"L\005\003]\031\0211!\0218z\021\025\001\004A\"\0052\003\021\021X\r\035:\026\003I\002\"!J\032\005\rQ\002AQ1\0016\005\021!\006.[:\022\005%2\004\003B\034\001IIj\021A\001\005\006s\0011\tAO\001\007W\026L8+\032;\026\003m\0022\001P\037%\033\005!\021B\001 \005\005%\031vN\035;fIN+G\017C\003A\001\031\005\021)\001\005gSJ\034HoS3z+\005!\003\"B\"\001\r\003\t\025a\0027bgR\\U-\037\005\006\013\002!\tAR\001\bG>l\007/\031:f)\r9%\n\024\t\003\031!K!!\023\004\003\007%sG\017C\003L\t\002\007A%\001\002la!)Q\n\022a\001I\005\0211.\r\005\006\037\0021\t\001U\001\ne\006tw-Z%na2$2AM)W\021\025\021f\n1\001T\003\0211'o\\7\021\0071!F%\003\002V\r\t1q\n\035;j_:DQa\026(A\002M\013Q!\0368uS2DQA\025\001\005\002e#\"A\r.\t\013IC\006\031\001\023\t\013]\003A\021\001/\025\005Ij\006\"B,\\\001\004!\003\"B0\001\t\003\001\027!\002:b]\036,Gc\001\032bE\")!K\030a\001I!)qK\030a\001I!)A\r\001C\001K\006\021Ao\034\013\003e\031DQ\001Z2A\002\021BQ\001\033\001\005\022%\fa\001[1t\0032dGC\0016n!\ta1.\003\002m\r\t9!i\\8mK\006t\007\"\0028h\001\004y\027!\0016\021\007q\002H%\003\002r\t\tA\021\n^3sCR|'\017")
public interface Sorted<K, This extends Sorted<K, This>> {
  Ordering<K> ordering();
  
  This repr();
  
  SortedSet<K> keySet();
  
  K firstKey();
  
  K lastKey();
  
  int compare(K paramK1, K paramK2);
  
  This rangeImpl(Option<K> paramOption1, Option<K> paramOption2);
  
  This from(K paramK);
  
  This until(K paramK);
  
  This range(K paramK1, K paramK2);
  
  This to(K paramK);
  
  boolean hasAll(Iterator<K> paramIterator);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\Sorted.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */