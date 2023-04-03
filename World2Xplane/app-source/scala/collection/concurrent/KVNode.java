package scala.collection.concurrent;

import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\r2\001\"\001\002\021\002G\005!\001\003\002\007\027Zsu\016Z3\013\005\r!\021AC2p]\016,(O]3oi*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\026\007%9\022e\005\002\001\025A\0211\002D\007\002\r%\021QB\002\002\007\003:L(+\0324\t\013=\001a\021A\t\002\r-4\b+Y5s\007\001)\022A\005\t\005\027M)\002%\003\002\025\r\t1A+\0369mKJ\002\"AF\f\r\001\021)\001\004\001b\0013\t\t1*\005\002\033;A\0211bG\005\0039\031\021qAT8uQ&tw\r\005\002\f=%\021qD\002\002\004\003:L\bC\001\f\"\t\025\021\003A1\001\032\005\0051\006")
public interface KVNode<K, V> {
  Tuple2<K, V> kvPair();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\KVNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */