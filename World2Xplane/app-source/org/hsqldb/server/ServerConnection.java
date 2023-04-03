package org.hsqldb.server;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.hsqldb.ClientConnection;
import org.hsqldb.ColumnBase;
import org.hsqldb.DatabaseManager;
import org.hsqldb.HsqlException;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.DataOutputStream;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.resources.ResourceBundleHandler;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.rowio.RowInputBinary;
import org.hsqldb.rowio.RowOutputBinary;
import org.hsqldb.rowio.RowOutputInterface;
import org.hsqldb.types.Type;

class ServerConnection implements Runnable {
  boolean keepAlive;
  
  private String user;
  
  int dbID;
  
  int dbIndex;
  
  private volatile Session session;
  
  private Socket socket;
  
  private Server server;
  
  private DataInputStream dataInput;
  
  private DataOutputStream dataOutput;
  
  private int mThread;
  
  static final int BUFFER_SIZE = 4096;
  
  final byte[] mainBuffer = new byte[4096];
  
  RowOutputInterface rowOut;
  
  RowInputBinary rowIn;
  
  Thread runnerThread;
  
  private static AtomicInteger mCurrentThread = new AtomicInteger(0);
  
  protected static String TEXTBANNER_PART1 = null;
  
  protected static String TEXTBANNER_PART2 = null;
  
  private CleanExit cleanExit = new CleanExit();
  
  private OdbcPacketOutputStream outPacket = null;
  
  public static long MAX_WAIT_FOR_CLIENT_DATA;
  
  public static long CLIENT_DATA_POLLING_PERIOD;
  
  private Map sessionOdbcPsMap = new HashMap<Object, Object>();
  
  private Map sessionOdbcPortalMap = new HashMap<Object, Object>();
  
  private int streamProtocol = 0;
  
  static final int UNDEFINED_STREAM_PROTOCOL = 0;
  
  static final int HSQL_STREAM_PROTOCOL = 1;
  
  static final int ODBC_STREAM_PROTOCOL = 2;
  
  int odbcCommMode = 0;
  
  ServerConnection(Socket paramSocket, Server paramServer) {
    RowOutputBinary rowOutputBinary = new RowOutputBinary(this.mainBuffer);
    this.rowIn = new RowInputBinary(rowOutputBinary);
    this.rowOut = (RowOutputInterface)rowOutputBinary;
    this.socket = paramSocket;
    this.server = paramServer;
    this.mThread = mCurrentThread.getAndIncrement();
    synchronized (paramServer.serverConnSet) {
      paramServer.serverConnSet.add(this);
    } 
  }
  
  void signalClose() {
    this.keepAlive = false;
    if (Thread.currentThread().equals(this.runnerThread)) {
      Result result = Result.updateZeroResult;
      try {
        result.write((SessionInterface)this.session, this.dataOutput, this.rowOut);
      } catch (Throwable throwable) {}
    } 
    close();
  }
  
  private void close() {
    if (this.session != null) {
      this.session.close();
      this.session = null;
    } 
    synchronized (this) {
      try {
        if (this.socket != null) {
          this.socket.close();
          this.socket = null;
        } 
      } catch (IOException iOException) {}
      this.socket = null;
    } 
    synchronized (this.server.serverConnSet) {
      this.server.serverConnSet.remove(this);
    } 
    try {
      this.runnerThread.setContextClassLoader(null);
    } catch (Throwable throwable) {}
  }
  
  private void init() {
    this.runnerThread = Thread.currentThread();
    this.keepAlive = true;
    try {
      Result result1;
      Result result2;
      this.socket.setTcpNoDelay(true);
      this.dataInput = new DataInputStream(new BufferedInputStream(this.socket.getInputStream()));
      this.dataOutput = new DataOutputStream(this.socket.getOutputStream());
      int i = handshake();
      switch (this.streamProtocol) {
        case 1:
          if (i != -2010000) {
            if (i == -1900000)
              i = -2000000; 
            String str = ClientConnection.toNetCompVersionString(i);
            throw Error.error(null, 403, 0, new String[] { str, "2.3.0" });
          } 
          result1 = Result.newResult(this.dataInput, this.rowIn);
          result1.readAdditionalResults((SessionInterface)this.session, this.dataInput, this.rowIn);
          result2 = setDatabase(result1);
          result2.write((SessionInterface)this.session, this.dataOutput, this.rowOut);
          return;
        case 2:
          odbcConnect(i);
          return;
      } 
      this.keepAlive = false;
    } catch (Exception exception) {
      StringBuffer stringBuffer = new StringBuffer(this.mThread + ":Failed to connect client.");
      if (this.user != null)
        stringBuffer.append("  User '" + this.user + "'."); 
      this.server.printWithThread(stringBuffer.toString() + "  Stack trace follows.");
      this.server.printStackTrace(exception);
    } 
  }
  
  private void receiveResult(int paramInt) throws CleanExit, IOException {
    boolean bool = false;
    Result result1 = Result.newResult(this.session, paramInt, this.dataInput, this.rowIn);
    result1.readLobResults((SessionInterface)this.session, this.dataInput, this.rowIn);
    this.server.printRequest(this.mThread, result1);
    Result result2 = null;
    switch (result1.getType()) {
      case 31:
        result2 = setDatabase(result1);
        break;
      case 32:
        result2 = Result.updateZeroResult;
        bool = true;
        break;
      case 10:
        this.session.resetSession();
        result2 = Result.updateZeroResult;
        break;
      case 21:
        result2 = Result.newErrorResult((Throwable)Error.error(1252));
        break;
      default:
        result2 = this.session.execute(result1);
        break;
    } 
    result2.write((SessionInterface)this.session, this.dataOutput, this.rowOut);
    this.rowOut.reset(this.mainBuffer);
    this.rowIn.resetRow(this.mainBuffer.length);
    if (bool)
      throw this.cleanExit; 
  }
  
  private void receiveOdbcPacket(char paramChar) throws IOException, CleanExit {
    boolean bool = false;
    String str = null;
    OdbcPacketInputStream odbcPacketInputStream = null;
    try {
      odbcPacketInputStream = OdbcPacketInputStream.newOdbcPacketInputStream(paramChar, this.dataInput);
      this.server.printWithThread("Got op (" + odbcPacketInputStream.packetType + ')');
      this.server.printWithThread("Got packet length of " + odbcPacketInputStream.available() + " + type byte + 4 size header");
      if (odbcPacketInputStream.available() >= 1000000000)
        throw new IOException("Insane packet length: " + odbcPacketInputStream.available() + " + type byte + 4 size header"); 
    } catch (SocketException socketException) {
      this.server.printWithThread("Ungraceful client exit: " + socketException);
      throw this.cleanExit;
    } catch (IOException iOException) {
      this.server.printWithThread("Fatal ODBC protocol failure: " + iOException);
      try {
        OdbcUtil.alertClient(1, iOException.toString(), "08P01", this.dataOutput);
      } catch (Exception exception) {}
      throw this.cleanExit;
    } 
    switch (this.odbcCommMode) {
      case 2:
        if (odbcPacketInputStream.packetType != 'S') {
          if (this.server.isTrace())
            this.server.printWithThread("Ignoring a '" + odbcPacketInputStream.packetType + "'"); 
          return;
        } 
        this.odbcCommMode = 1;
        this.server.printWithThread("EXTENDED comm session being recovered");
        break;
      case 0:
        switch (odbcPacketInputStream.packetType) {
          case 'B':
          case 'C':
          case 'D':
          case 'E':
          case 'H':
          case 'P':
          case 'S':
            this.odbcCommMode = 1;
            this.server.printWithThread("Switching mode from SIMPLE to EXTENDED");
            break;
        } 
        break;
      case 1:
        switch (odbcPacketInputStream.packetType) {
          case 'Q':
            this.odbcCommMode = 0;
            this.server.printWithThread("Switching mode from EXTENDED to SIMPLE");
            break;
        } 
        break;
      default:
        throw new RuntimeException("Unexpected ODBC comm mode value: " + this.odbcCommMode);
    } 
    this.outPacket.reset();
    try {
      char c;
      String str1;
      String str2;
      String str3;
      Result result1;
      int i;
      int j;
      OdbcPreparedStatement odbcPreparedStatement;
      StatementPortal statementPortal;
      ResultMetaData resultMetaData1;
      Type[] arrayOfType1;
      PgType[] arrayOfPgType;
      String str4;
      String str5;
      String str6;
      byte b1;
      Result result2;
      Type[] arrayOfType2;
      ResultMetaData resultMetaData2;
      String[] arrayOfString;
      ColumnBase[] arrayOfColumnBase;
      int k;
      boolean[] arrayOfBoolean;
      byte b2;
      Object[] arrayOfObject;
      int m;
      int n;
      RowSetNavigator rowSetNavigator;
      byte b3;
      int i1;
      switch (odbcPacketInputStream.packetType) {
        case 'Q':
          str4 = odbcPacketInputStream.readString();
          if (str4.startsWith("BEGIN;") || str4.equals("BEGIN")) {
            str4 = str4.equals("BEGIN") ? null : str4.substring("BEGIN;".length());
            this.server.printWithThread("ODBC Trans started.  Session AutoCommit -> F");
            try {
              this.session.setAutoCommit(false);
            } catch (HsqlException hsqlException) {
              throw new RecoverableOdbcFailure("Failed to change transaction state: " + hsqlException.getMessage(), hsqlException.getSQLState());
            } 
            this.outPacket.write("BEGIN");
            this.outPacket.xmit('C', this.dataOutput);
            if (str4 == null) {
              bool = true;
              break;
            } 
          } 
          if (str4.startsWith("SAVEPOINT ") && str4.indexOf(';') > 0) {
            int i2 = str4.indexOf(';');
            this.server.printWithThread("Interposing BEFORE primary statement: " + str4.substring(0, i2));
            odbcExecDirect(str4.substring(0, i2));
            str4 = str4.substring(i2 + 1);
          } 
          j = str4.lastIndexOf(';');
          if (j > 0) {
            String str7 = str4.substring(j + 1);
            if (str7.startsWith("RELEASE ")) {
              str = str7;
              str4 = str4.substring(0, j);
            } 
          } 
          str5 = str4.trim().toLowerCase();
          if (this.server.isTrace())
            this.server.printWithThread("Received query (" + str4 + ')'); 
          if (str5.startsWith("select current_schema()")) {
            this.server.printWithThread("Implement 'select current_schema() emulation!");
            throw new RecoverableOdbcFailure("current_schema() not supported yet", "0A000");
          } 
          if (str5.startsWith("select n.nspname,")) {
            this.server.printWithThread("Swallowing 'select n.nspname,...'");
            this.outPacket.writeShort(1);
            this.outPacket.write("oid");
            this.outPacket.writeInt(201);
            this.outPacket.writeShort(1);
            this.outPacket.writeInt(23);
            this.outPacket.writeShort(4);
            this.outPacket.writeInt(-1);
            this.outPacket.writeShort(0);
            this.outPacket.xmit('T', this.dataOutput);
            this.outPacket.write("SELECT");
            this.outPacket.xmit('C', this.dataOutput);
            bool = true;
            break;
          } 
          if (str5.startsWith("select oid, typbasetype from")) {
            this.server.printWithThread("Simulating 'select oid, typbasetype...'");
            this.outPacket.writeShort(2);
            this.outPacket.write("oid");
            this.outPacket.writeInt(101);
            this.outPacket.writeShort(102);
            this.outPacket.writeInt(26);
            this.outPacket.writeShort(4);
            this.outPacket.writeInt(-1);
            this.outPacket.writeShort(0);
            this.outPacket.write("typbasetype");
            this.outPacket.writeInt(101);
            this.outPacket.writeShort(103);
            this.outPacket.writeInt(26);
            this.outPacket.writeShort(4);
            this.outPacket.writeInt(-1);
            this.outPacket.writeShort(0);
            this.outPacket.xmit('T', this.dataOutput);
            this.outPacket.write("SELECT");
            this.outPacket.xmit('C', this.dataOutput);
            bool = true;
            break;
          } 
          if (str5.startsWith("select ")) {
            this.server.printWithThread("Performing a real non-prepared SELECT...");
            Result result3 = Result.newExecuteDirectRequest();
            result3.setPrepareOrExecuteProperties(str4, 0, 0, 2, 0, 0, 2, null, null);
            Result result4 = this.session.execute(result3);
            switch (result4.getType()) {
              case 3:
                break;
              case 2:
                throw new RecoverableOdbcFailure(result4);
              default:
                throw new RecoverableOdbcFailure("Output Result from Query execution is of unexpected type: " + result4.getType());
            } 
            RowSetNavigator rowSetNavigator1 = result4.getNavigator();
            ResultMetaData resultMetaData = result4.metaData;
            if (resultMetaData == null)
              throw new RecoverableOdbcFailure("Failed to get metadata for query results"); 
            int i2 = resultMetaData.getColumnCount();
            String[] arrayOfString1 = resultMetaData.getGeneratedColumnNames();
            Type[] arrayOfType = resultMetaData.columnTypes;
            PgType[] arrayOfPgType1 = new PgType[i2];
            for (byte b4 = 0; b4 < arrayOfPgType1.length; b4++)
              arrayOfPgType1[b4] = PgType.getPgType(arrayOfType[b4], resultMetaData.isTableColumn(b4)); 
            ColumnBase[] arrayOfColumnBase1 = resultMetaData.columns;
            this.outPacket.writeShort(i2);
            byte b5;
            for (b5 = 0; b5 < i2; b5++) {
              if (arrayOfString1[b5] != null) {
                this.outPacket.write(arrayOfString1[b5]);
              } else {
                this.outPacket.write(arrayOfColumnBase1[b5].getNameString());
              } 
              this.outPacket.writeInt(OdbcUtil.getTableOidForColumn(b5, resultMetaData));
              this.outPacket.writeShort(OdbcUtil.getIdForColumn(b5, resultMetaData));
              this.outPacket.writeInt(arrayOfPgType1[b5].getOid());
              this.outPacket.writeShort(arrayOfPgType1[b5].getTypeWidth());
              this.outPacket.writeInt(arrayOfPgType1[b5].getLPConstraint());
              this.outPacket.writeShort(0);
            } 
            this.outPacket.xmit('T', this.dataOutput);
            b5 = 0;
            while (rowSetNavigator1.next()) {
              b5++;
              Object[] arrayOfObject1 = rowSetNavigator1.getCurrent();
              if (arrayOfObject1 == null)
                throw new RecoverableOdbcFailure("Null row?"); 
              if (arrayOfObject1.length < i2)
                throw new RecoverableOdbcFailure("Data element mismatch. " + i2 + " metadata cols, yet " + arrayOfObject1.length + " data elements for row " + b5); 
              this.outPacket.writeShort(i2);
              for (byte b = 0; b < i2; b++) {
                if (arrayOfObject1[b] == null) {
                  this.outPacket.writeInt(-1);
                } else {
                  String str7 = arrayOfPgType1[b].valueString(arrayOfObject1[b]);
                  this.outPacket.writeSized(str7);
                  if (this.server.isTrace())
                    this.server.printWithThread("R" + b5 + "C" + (b + 1) + " => (" + arrayOfObject1[b].getClass().getName() + ") [" + str7 + ']'); 
                } 
              } 
              this.outPacket.xmit('D', this.dataOutput);
            } 
            this.outPacket.write("SELECT");
            this.outPacket.xmit('C', this.dataOutput);
            bool = true;
            break;
          } 
          if (str5.startsWith("deallocate \"") && str5.charAt(str5.length() - 1) == '"') {
            String str8 = str4.trim().substring("deallocate \"".length()).trim();
            String str7 = str8.substring(0, str8.length() - 1);
            OdbcPreparedStatement odbcPreparedStatement1 = (OdbcPreparedStatement)this.sessionOdbcPsMap.get(str7);
            if (odbcPreparedStatement1 != null)
              odbcPreparedStatement1.close(); 
            StatementPortal statementPortal1 = (StatementPortal)this.sessionOdbcPortalMap.get(str7);
            if (statementPortal1 != null)
              statementPortal1.close(); 
            if (odbcPreparedStatement1 == null && statementPortal1 == null)
              this.server.printWithThread("Ignoring bad 'DEALLOCATE' cmd"); 
            if (this.server.isTrace())
              this.server.printWithThread("Deallocated PS/Portal '" + str7 + "'"); 
            this.outPacket.write("DEALLOCATE");
            this.outPacket.xmit('C', this.dataOutput);
            bool = true;
            break;
          } 
          if (str5.startsWith("set client_encoding to ")) {
            this.server.printWithThread("Stubbing EXECDIR for: " + str4);
            this.outPacket.write("SET");
            this.outPacket.xmit('C', this.dataOutput);
            bool = true;
            break;
          } 
          this.server.printWithThread("Performing a real EXECDIRECT...");
          odbcExecDirect(str4);
          bool = true;
          break;
        case 'X':
          if (this.sessionOdbcPsMap.size() > (this.sessionOdbcPsMap.containsKey("") ? 1 : 0))
            this.server.printWithThread("Client left " + this.sessionOdbcPsMap.size() + " PS objects open"); 
          if (this.sessionOdbcPortalMap.size() > (this.sessionOdbcPortalMap.containsKey("") ? 1 : 0))
            this.server.printWithThread("Client left " + this.sessionOdbcPortalMap.size() + " Portal objects open"); 
          OdbcUtil.validateInputPacketSize(odbcPacketInputStream);
          throw this.cleanExit;
        case 'H':
          break;
        case 'S':
          if (this.session.isAutoCommit())
            try {
              this.server.printWithThread("Silly implicit commit by Sync");
              this.session.commit(true);
            } catch (HsqlException hsqlException) {
              this.server.printWithThread("Implicit commit failed: " + hsqlException);
              OdbcUtil.alertClient(2, "Implicit commit failed", hsqlException.getSQLState(), this.dataOutput);
            }  
          bool = true;
          break;
        case 'P':
          str1 = odbcPacketInputStream.readString();
          str6 = OdbcUtil.revertMungledPreparedQuery(odbcPacketInputStream.readString());
          i = odbcPacketInputStream.readUnsignedShort();
          for (b1 = 0; b1 < i; b1++) {
            if (odbcPacketInputStream.readInt() != 0)
              throw new RecoverableOdbcFailure(null, "Parameter-type OID specifiers not supported yet", "0A000"); 
          } 
          if (this.server.isTrace())
            this.server.printWithThread("Received Prepare request for query (" + str6 + ") with handle '" + str1 + "'"); 
          if (str1.length() > 0 && this.sessionOdbcPsMap.containsKey(str1))
            throw new RecoverableOdbcFailure(null, "PS handle '" + str1 + "' already in use.  " + "You must close it before recreating", "08P01"); 
          new OdbcPreparedStatement(str1, str6, this.sessionOdbcPsMap, this.session);
          this.outPacket.xmit('1', this.dataOutput);
          break;
        case 'D':
          c = odbcPacketInputStream.readByteChar();
          str3 = odbcPacketInputStream.readString();
          odbcPreparedStatement = null;
          statementPortal = null;
          if (c == 'S') {
            odbcPreparedStatement = (OdbcPreparedStatement)this.sessionOdbcPsMap.get(str3);
          } else if (c == 'P') {
            statementPortal = (StatementPortal)this.sessionOdbcPortalMap.get(str3);
          } else {
            throw new RecoverableOdbcFailure(null, "Description packet request type invalid: " + c, "08P01");
          } 
          if (this.server.isTrace())
            this.server.printWithThread("Received Describe request for " + c + " of  handle '" + str3 + "'"); 
          if (odbcPreparedStatement == null && statementPortal == null)
            throw new RecoverableOdbcFailure(null, "No object present for " + c + " handle: " + str3, "08P01"); 
          result2 = (odbcPreparedStatement == null) ? statementPortal.ackResult : odbcPreparedStatement.ackResult;
          resultMetaData1 = result2.parameterMetaData;
          i = resultMetaData1.getColumnCount();
          arrayOfType2 = resultMetaData1.getParameterTypes();
          if (i != arrayOfType2.length)
            throw new RecoverableOdbcFailure("Parameter count mismatch.  Count of " + i + " reported, but there are " + arrayOfType2.length + " param md objects"); 
          if (c == 'S') {
            this.outPacket.writeShort(i);
            for (byte b = 0; b < arrayOfType2.length; b++)
              this.outPacket.writeInt(PgType.getPgType(arrayOfType2[b], true).getOid()); 
            this.outPacket.xmit('t', this.dataOutput);
          } 
          resultMetaData2 = result2.metaData;
          if (resultMetaData2.getColumnCount() < 1) {
            if (this.server.isTrace())
              this.server.printWithThread("Non-rowset query so returning NoData packet"); 
            this.outPacket.xmit('n', this.dataOutput);
            break;
          } 
          arrayOfString = resultMetaData2.getGeneratedColumnNames();
          if (resultMetaData2.getColumnCount() != arrayOfString.length)
            throw new RecoverableOdbcFailure("Couldn't get all column names: " + resultMetaData2.getColumnCount() + " cols. but only got " + arrayOfString.length + " col. names"); 
          arrayOfType1 = resultMetaData2.columnTypes;
          arrayOfPgType = new PgType[arrayOfString.length];
          arrayOfColumnBase = resultMetaData2.columns;
          for (k = 0; k < arrayOfPgType.length; k++)
            arrayOfPgType[k] = PgType.getPgType(arrayOfType1[k], resultMetaData2.isTableColumn(k)); 
          if (arrayOfString.length != arrayOfColumnBase.length)
            throw new RecoverableOdbcFailure("Col data mismatch.  " + arrayOfColumnBase.length + " col instances but " + arrayOfString.length + " col names"); 
          this.outPacket.writeShort(arrayOfString.length);
          for (k = 0; k < arrayOfString.length; k++) {
            this.outPacket.write(arrayOfString[k]);
            this.outPacket.writeInt(OdbcUtil.getTableOidForColumn(k, resultMetaData2));
            this.outPacket.writeShort(OdbcUtil.getIdForColumn(k, resultMetaData2));
            this.outPacket.writeInt(arrayOfPgType[k].getOid());
            this.outPacket.writeShort(arrayOfPgType[k].getTypeWidth());
            this.outPacket.writeInt(arrayOfPgType[k].getLPConstraint());
            this.outPacket.writeShort(0);
          } 
          this.outPacket.xmit('T', this.dataOutput);
          break;
        case 'B':
          str2 = odbcPacketInputStream.readString();
          str1 = odbcPacketInputStream.readString();
          k = odbcPacketInputStream.readUnsignedShort();
          arrayOfBoolean = new boolean[k];
          for (b2 = 0; b2 < k; b2++) {
            arrayOfBoolean[b2] = (odbcPacketInputStream.readUnsignedShort() != 0);
            if (this.server.isTrace() && arrayOfBoolean[b2])
              this.server.printWithThread("Binary param #" + b2); 
          } 
          i = odbcPacketInputStream.readUnsignedShort();
          arrayOfObject = new Object[i];
          for (m = 0; m < arrayOfObject.length; m++) {
            if (m < arrayOfBoolean.length && arrayOfBoolean[m]) {
              arrayOfObject[m] = odbcPacketInputStream.readSizedBinaryData();
            } else {
              arrayOfObject[m] = odbcPacketInputStream.readSizedString();
            } 
          } 
          m = odbcPacketInputStream.readUnsignedShort();
          for (n = 0; n < m; n++) {
            if (odbcPacketInputStream.readUnsignedShort() != 0)
              throw new RecoverableOdbcFailure(null, "Binary output values not supported", "0A000"); 
          } 
          if (this.server.isTrace())
            this.server.printWithThread("Received Bind request to make Portal from (" + str1 + ")' with handle '" + str2 + "'"); 
          odbcPreparedStatement = (OdbcPreparedStatement)this.sessionOdbcPsMap.get(str1);
          if (odbcPreparedStatement == null)
            throw new RecoverableOdbcFailure(null, "No object present for PS handle: " + str1, "08P01"); 
          if (str2.length() > 0 && this.sessionOdbcPortalMap.containsKey(str2))
            throw new RecoverableOdbcFailure(null, "Portal handle '" + str2 + "' already in use.  " + "You must close it before recreating", "08P01"); 
          resultMetaData1 = odbcPreparedStatement.ackResult.parameterMetaData;
          if (i != resultMetaData1.getColumnCount())
            throw new RecoverableOdbcFailure(null, "Client didn't specify all " + resultMetaData1.getColumnCount() + " parameters (" + i + ')', "08P01"); 
          new StatementPortal(str2, odbcPreparedStatement, arrayOfObject, this.sessionOdbcPortalMap);
          this.outPacket.xmit('2', this.dataOutput);
          break;
        case 'E':
          str2 = odbcPacketInputStream.readString();
          n = odbcPacketInputStream.readInt();
          if (this.server.isTrace())
            this.server.printWithThread("Received Exec request for " + n + " rows from portal handle '" + str2 + "'"); 
          statementPortal = (StatementPortal)this.sessionOdbcPortalMap.get(str2);
          if (statementPortal == null)
            throw new RecoverableOdbcFailure(null, "No object present for Portal handle: " + str2, "08P01"); 
          statementPortal.bindResult.setPreparedExecuteProperties(statementPortal.parameters, n, 0, 0, 0);
          result1 = this.session.execute(statementPortal.bindResult);
          switch (result1.getType()) {
            case 1:
              this.outPacket.write(OdbcUtil.echoBackReplyString(statementPortal.lcQuery, result1.getUpdateCount()));
              this.outPacket.xmit('C', this.dataOutput);
              if (statementPortal.lcQuery.equals("commit") || statementPortal.lcQuery.startsWith("commit ") || statementPortal.lcQuery.equals("rollback") || statementPortal.lcQuery.startsWith("rollback "))
                try {
                  this.session.setAutoCommit(true);
                } catch (HsqlException hsqlException) {
                  throw new RecoverableOdbcFailure("Failed to change transaction state: " + hsqlException.getMessage(), hsqlException.getSQLState());
                }  
              break;
            case 3:
              break;
            case 2:
              throw new RecoverableOdbcFailure(result1);
            default:
              throw new RecoverableOdbcFailure("Output Result from Portal execution is of unexpected type: " + result1.getType());
          } 
          rowSetNavigator = result1.getNavigator();
          b3 = 0;
          i1 = statementPortal.ackResult.metaData.getColumnCount();
          while (rowSetNavigator.next()) {
            b3++;
            Object[] arrayOfObject1 = rowSetNavigator.getCurrent();
            if (arrayOfObject1 == null)
              throw new RecoverableOdbcFailure("Null row?"); 
            if (arrayOfObject1.length < i1)
              throw new RecoverableOdbcFailure("Data element mismatch. " + i1 + " metadata cols, yet " + arrayOfObject1.length + " data elements for row " + b3); 
            this.outPacket.writeShort(i1);
            arrayOfType1 = statementPortal.ackResult.metaData.columnTypes;
            arrayOfPgType = new PgType[i1];
            byte b;
            for (b = 0; b < arrayOfPgType.length; b++)
              arrayOfPgType[b] = PgType.getPgType(arrayOfType1[b], statementPortal.ackResult.metaData.isTableColumn(b)); 
            for (b = 0; b < i1; b++) {
              if (arrayOfObject1[b] == null) {
                this.outPacket.writeInt(-1);
              } else {
                String str7 = arrayOfPgType[b].valueString(arrayOfObject1[b]);
                this.outPacket.writeSized(str7);
                if (this.server.isTrace())
                  this.server.printWithThread("R" + b3 + "C" + (b + 1) + " => (" + arrayOfObject1[b].getClass().getName() + ") [" + str7 + ']'); 
              } 
            } 
            this.outPacket.xmit('D', this.dataOutput);
          } 
          if (rowSetNavigator.afterLast()) {
            this.outPacket.write("SELECT");
            this.outPacket.xmit('C', this.dataOutput);
            break;
          } 
          this.outPacket.xmit('s', this.dataOutput);
          break;
        case 'C':
          c = odbcPacketInputStream.readByteChar();
          str3 = odbcPacketInputStream.readString();
          odbcPreparedStatement = null;
          statementPortal = null;
          if (c == 'S') {
            odbcPreparedStatement = (OdbcPreparedStatement)this.sessionOdbcPsMap.get(str3);
            if (odbcPreparedStatement != null)
              odbcPreparedStatement.close(); 
          } else if (c == 'P') {
            statementPortal = (StatementPortal)this.sessionOdbcPortalMap.get(str3);
            if (statementPortal != null)
              statementPortal.close(); 
          } else {
            throw new RecoverableOdbcFailure(null, "Description packet request type invalid: " + c, "08P01");
          } 
          if (this.server.isTrace())
            this.server.printWithThread("Closed " + c + " '" + str3 + "'? " + ((odbcPreparedStatement != null || statementPortal != null) ? 1 : 0)); 
          this.outPacket.xmit('3', this.dataOutput);
          break;
        default:
          throw new RecoverableOdbcFailure(null, "Unsupported operation type (" + odbcPacketInputStream.packetType + ')', "0A000");
      } 
      OdbcUtil.validateInputPacketSize(odbcPacketInputStream);
      if (str != null) {
        this.server.printWithThread("Interposing AFTER primary statement: " + str);
        odbcExecDirect(str);
      } 
      if (bool) {
        this.outPacket.reset();
        this.outPacket.writeByte(this.session.isAutoCommit() ? 73 : 84);
        this.outPacket.xmit('Z', this.dataOutput);
      } 
    } catch (RecoverableOdbcFailure recoverableOdbcFailure) {
      Result result = recoverableOdbcFailure.getErrorResult();
      if (result == null) {
        String str1 = recoverableOdbcFailure.getSqlStateCode();
        String str2 = recoverableOdbcFailure.toString();
        String str3 = recoverableOdbcFailure.getClientMessage();
        if (str2 != null) {
          this.server.printWithThread(str2);
        } else if (this.server.isTrace()) {
          this.server.printWithThread("Client error: " + str3);
        } 
        if (str3 != null)
          OdbcUtil.alertClient(2, str3, str1, this.dataOutput); 
      } else {
        if (this.server.isTrace())
          this.server.printWithThread("Result object error: " + result.getMainString()); 
        OdbcUtil.alertClient(2, result.getMainString(), result.getSubString(), this.dataOutput);
      } 
      switch (this.odbcCommMode) {
        case 0:
          this.outPacket.reset();
          this.outPacket.writeByte(69);
          this.outPacket.xmit('Z', this.dataOutput);
          break;
        case 1:
          this.odbcCommMode = 2;
          this.server.printWithThread("Reverting to EXT_RECOVER mode");
          break;
      } 
    } 
  }
  
  public void run() {
    init();
    if (this.session != null)
      try {
        while (this.keepAlive) {
          byte b = this.dataInput.readByte();
          if (b < 48) {
            receiveResult(b);
            continue;
          } 
          receiveOdbcPacket((char)b);
        } 
      } catch (CleanExit cleanExit) {
        this.keepAlive = false;
      } catch (IOException iOException) {
        this.server.printWithThread(this.mThread + ":disconnected " + this.user);
      } catch (HsqlException hsqlException) {
        if (this.keepAlive)
          this.server.printStackTrace((Throwable)hsqlException); 
      } catch (Throwable throwable) {
        if (this.keepAlive)
          this.server.printStackTrace(throwable); 
      }  
    close();
  }
  
  private Result setDatabase(Result paramResult) {
    try {
      String str = paramResult.getDatabaseName();
      this.dbIndex = this.server.getDBIndex(str);
      this.dbID = this.server.dbID[this.dbIndex];
      this.user = paramResult.getMainString();
      if (!this.server.isSilent())
        this.server.printWithThread(this.mThread + ":Trying to connect user '" + this.user + "' to DB (" + str + ')'); 
      this.session = DatabaseManager.newSession(this.dbID, this.user, paramResult.getSubString(), paramResult.getZoneString(), paramResult.getUpdateCount());
      if (!this.server.isSilent())
        this.server.printWithThread(this.mThread + ":Connected user '" + this.user + "'"); 
      return Result.newConnectionAcknowledgeResponse(this.session.getDatabase(), this.session.getId(), this.session.getDatabase().getDatabaseID());
    } catch (HsqlException hsqlException) {
      this.session = null;
      return Result.newErrorResult((Throwable)hsqlException);
    } catch (RuntimeException runtimeException) {
      this.session = null;
      return Result.newErrorResult(runtimeException);
    } 
  }
  
  String getConnectionThreadName() {
    return "HSQLDB Connection @" + Integer.toString(hashCode(), 16);
  }
  
  public int handshake() throws IOException {
    long l = (new Date()).getTime() + MAX_WAIT_FOR_CLIENT_DATA;
    if (!(this.socket instanceof javax.net.ssl.SSLSocket)) {
      do {
        try {
          Thread.sleep(CLIENT_DATA_POLLING_PERIOD);
        } catch (InterruptedException interruptedException) {}
      } while (this.dataInput.available() < 5 && (new Date()).getTime() < l);
      if (this.dataInput.available() < 1) {
        this.dataOutput.write((TEXTBANNER_PART1 + "2.1.0.0" + TEXTBANNER_PART2 + '\n').getBytes());
        this.dataOutput.flush();
        throw Error.error(404);
      } 
    } 
    int i = this.dataInput.readInt();
    switch (i >> 24) {
      case 80:
        this.server.print("Rejected attempt from client using hsql HTTP protocol");
        return 0;
      case 0:
        this.streamProtocol = 2;
        return i;
    } 
    this.streamProtocol = 1;
    return i;
  }
  
  private void odbcConnect(int paramInt) throws IOException {
    int i = this.dataInput.readUnsignedShort();
    int j = this.dataInput.readUnsignedShort();
    if (i == 1 && j == 7) {
      this.server.print("A pre-version 2.0 client attempted to connect.  We rejected them.");
      return;
    } 
    if (i == 1234 && j == 5679) {
      this.dataOutput.writeByte(78);
      odbcConnect(this.dataInput.readInt());
      return;
    } 
    if (i == 1234 && j == 5678) {
      if (paramInt != 16)
        this.server.print("ODBC cancellation request sent wrong packet length: " + paramInt); 
      this.server.print("Got an ODBC cancelation request for thread ID " + this.dataInput.readInt() + ", but we don't support " + "OOB cancellation yet.  " + "Ignoring this request and closing the connection.");
      return;
    } 
    this.server.printWithThread("ODBC client connected.  ODBC Protocol Compatibility Version " + i + '.' + j);
    OdbcPacketInputStream odbcPacketInputStream = OdbcPacketInputStream.newOdbcPacketInputStream(false, this.dataInput, paramInt - 8);
    Map map = odbcPacketInputStream.readStringPairs();
    if (this.server.isTrace())
      this.server.print("String Pairs from ODBC client: " + map); 
    try {
      try {
        OdbcUtil.validateInputPacketSize(odbcPacketInputStream);
      } catch (RecoverableOdbcFailure recoverableOdbcFailure) {
        throw new ClientFailure(recoverableOdbcFailure.toString(), recoverableOdbcFailure.getClientMessage());
      } 
      odbcPacketInputStream.close();
      if (!map.containsKey("database"))
        throw new ClientFailure("Client did not identify database", "Target database not identified"); 
      if (!map.containsKey("user"))
        throw new ClientFailure("Client did not identify user", "Target account not identified"); 
      String str1 = (String)map.get("database");
      this.user = (String)map.get("user");
      if (str1.equals("/"))
        str1 = ""; 
      this.dataOutput.writeByte(82);
      this.dataOutput.writeInt(8);
      this.dataOutput.writeInt(3);
      this.dataOutput.flush();
      char c = Character.MIN_VALUE;
      try {
        c = (char)this.dataInput.readByte();
      } catch (EOFException eOFException) {
        this.server.printWithThread("Looks like we got a goofy psql no-auth attempt.  Will probably retry properly very shortly");
        return;
      } 
      if (c != 'p')
        throw new ClientFailure("Expected password prefix 'p', but got '" + c + "'", "Password value not prefixed with 'p'"); 
      int k = this.dataInput.readInt() - 5;
      if (k < 0)
        throw new ClientFailure("Client submitted invalid password length " + k, "Invalid password length " + k); 
      String str2 = readNullTermdUTF(k, this.dataInput);
      this.dbIndex = this.server.getDBIndex(str1);
      this.dbID = this.server.dbID[this.dbIndex];
      if (!this.server.isSilent())
        this.server.printWithThread(this.mThread + ":Trying to connect user '" + this.user + "' to DB (" + str1 + ')'); 
      try {
        this.session = DatabaseManager.newSession(this.dbID, this.user, str2, null, 0);
      } catch (Exception exception) {
        throw new ClientFailure("User name or password denied: " + exception, "Login attempt rejected");
      } 
    } catch (ClientFailure clientFailure) {
      this.server.print(clientFailure.toString());
      OdbcUtil.alertClient(1, clientFailure.getClientMessage(), "08006", this.dataOutput);
      return;
    } 
    this.outPacket = OdbcPacketOutputStream.newOdbcPacketOutputStream();
    this.outPacket.writeInt(0);
    this.outPacket.xmit('R', this.dataOutput);
    for (byte b = 0; b < OdbcUtil.hardcodedParams.length; b++)
      OdbcUtil.writeParam(OdbcUtil.hardcodedParams[b][0], OdbcUtil.hardcodedParams[b][1], this.dataOutput); 
    this.outPacket.writeByte(73);
    this.outPacket.xmit('Z', this.dataOutput);
    OdbcUtil.alertClient(7, "MHello\nYou have connected to HyperSQL ODBC Server", this.dataOutput);
    this.dataOutput.flush();
  }
  
  private static String readNullTermdUTF(int paramInt, InputStream paramInputStream) throws IOException {
    int i = 0;
    byte[] arrayOfByte = new byte[paramInt + 3];
    arrayOfByte[0] = (byte)(paramInt >>> 8);
    arrayOfByte[1] = (byte)paramInt;
    while (i < paramInt + 1)
      i += paramInputStream.read(arrayOfByte, 2 + i, paramInt + 1 - i); 
    if (arrayOfByte[arrayOfByte.length - 1] != 0)
      throw new IOException("String not null-terminated"); 
    for (byte b = 2; b < arrayOfByte.length - 1; b++) {
      if (arrayOfByte[b] == 0)
        throw new RuntimeException("Null internal to String at offset " + (b - 2)); 
    } 
    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(arrayOfByte));
    String str = dataInputStream.readUTF();
    dataInputStream.close();
    return str;
  }
  
  private void odbcExecDirect(String paramString) throws RecoverableOdbcFailure, IOException {
    String str1 = paramString;
    String str2 = str1.trim().toLowerCase();
    if (str2.startsWith("release ") && !str2.startsWith("release savepoint")) {
      this.server.printWithThread("Transmogrifying 'RELEASE ...' to 'RELEASE SAVEPOINT...");
      str1 = str1.trim().substring(0, "release ".length()) + "SAVEPOINT " + str1.trim().substring("release ".length());
    } 
    Result result1 = Result.newExecuteDirectRequest();
    result1.setPrepareOrExecuteProperties(str1, 0, 0, 1, 0, 0, 2, null, null);
    Result result2 = this.session.execute(result1);
    switch (result2.getType()) {
      case 1:
        break;
      case 2:
        throw new RecoverableOdbcFailure(result2);
      default:
        throw new RecoverableOdbcFailure("Output Result from execution is of unexpected type: " + result2.getType());
    } 
    this.outPacket.reset();
    this.outPacket.write(OdbcUtil.echoBackReplyString(str2, result2.getUpdateCount()));
    this.outPacket.xmit('C', this.dataOutput);
    if (str2.equals("commit") || str2.startsWith("commit ") || str2.equals("rollback") || str2.startsWith("rollback "))
      try {
        this.session.setAutoCommit(true);
      } catch (HsqlException hsqlException) {
        throw new RecoverableOdbcFailure("Failed to change transaction state: " + hsqlException.getMessage(), hsqlException.getSQLState());
      }  
  }
  
  static {
    int i = ResourceBundleHandler.getBundleHandle("org_hsqldb_server_Server_messages", null);
    if (i < 0)
      throw new RuntimeException("MISSING Resource Bundle.  See source code"); 
    TEXTBANNER_PART1 = ResourceBundleHandler.getString(i, "textbanner.part1");
    TEXTBANNER_PART2 = ResourceBundleHandler.getString(i, "textbanner.part2");
    if (TEXTBANNER_PART1 == null || TEXTBANNER_PART2 == null)
      throw new RuntimeException("MISSING Resource Bundle msg definition.  See source code"); 
    MAX_WAIT_FOR_CLIENT_DATA = 1000L;
    CLIENT_DATA_POLLING_PERIOD = 100L;
  }
  
  private static class ClientFailure extends Exception {
    private String clientMessage = null;
    
    public ClientFailure(String param1String1, String param1String2) {
      super(param1String1);
      this.clientMessage = param1String2;
    }
    
    public String getClientMessage() {
      return this.clientMessage;
    }
  }
  
  private static class CleanExit extends Exception {
    private CleanExit() {}
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\ServerConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */