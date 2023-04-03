package ch.qos.logback.core.net.server;

public interface ClientVisitor<T extends Client> {
  void visit(T paramT);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\net\server\ClientVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */