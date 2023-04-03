/*    */ package org.postgresql.translation;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Hashtable;
/*    */ import java.util.MissingResourceException;
/*    */ import java.util.ResourceBundle;
/*    */ 
/*    */ public class messages_ru extends ResourceBundle {
/*    */   private static final Hashtable table;
/*    */   
/*    */   static {
/*  6 */     Hashtable hashtable = new Hashtable();
/*  7 */     hashtable.put("", "Project-Id-Version: JDBC Driver for PostgreSQL 8.x.x\nReport-Msgid-Bugs-To: \nPOT-Creation-Date: 2006-05-22 00:03-0700\nPO-Revision-Date: 2004-11-15 12:19-0500\nLast-Translator: Serguei A. Mokhov <mokhov@cs.concordia.ca>\nLanguage-Team: pgsql-rus <pgsql-rus@yahoogroups.com>\nMIME-Version: 1.0\nContent-Type: text/plain; charset=UTF-8\nContent-Transfer-Encoding: 8bit\nX-Poedit-Language: Russian\nX-Poedit-Country: RUSSIAN FEDERATION\n");
/*  8 */     hashtable.put("Something unusual has occured to cause the driver to fail. Please report this exception.", "Случилось что-то необычное, что заставило драйвер произвести ошибку. Пожалуйста сообщите это исключение.");
/*  9 */     hashtable.put("Premature end of input stream, expected {0} bytes, but only read {1}.", "Раннее завершение входного потока, ожидалось байт: {0}, но считано только {1}.");
/* 10 */     hashtable.put("The driver does not support SSL.", "Драйвер не поддерживает SSL.");
/* 11 */     hashtable.put("Connection refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections.", "Подсоединение отклонено. Проверьте что хост и порт указаны правильно и что postmaster принимает TCP/IP-подсоединения.");
/* 12 */     hashtable.put("The connection attempt failed.", "Ошибка при попытке подсоединения.");
/* 13 */     hashtable.put("The server does not support SSL.", "Сервер не поддерживает SSL.");
/* 14 */     hashtable.put("An error occured while setting up the SSL connection.", "Ошибка при установке SSL-подсоединения.");
/* 15 */     hashtable.put("Connection rejected: {0}.", "Подсоединение отвергнуто: {0}.");
/* 16 */     hashtable.put("The server requested password-based authentication, but no password was provided.", "Сервер запросил парольную аутентификацию, но пароль не был указан.");
/* 17 */     hashtable.put("Protocol error.  Session setup failed.", "Ошибка протокола.  Установление сессии не удалось.");
/* 18 */     hashtable.put("Backend start-up failed: {0}.", "Запуск бэкенда не удался: {0}.");
/* 19 */     hashtable.put("An unexpected result was returned by a query.", "Запрос вернул неожиданный результат.");
/* 20 */     hashtable.put("The column index is out of range: {0}, number of columns: {1}.", "Индекс колонки вне диапазона: {0}, число колонок: {1}.");
/* 21 */     hashtable.put("No value specified for parameter {0}.", "Не указано значение для параметра {0}.");
/* 22 */     hashtable.put("Expected command status BEGIN, got {0}.", "Ожидался статус команды BEGIN, но получен для {0}.");
/* 23 */     hashtable.put("Unexpected command status: {0}.", "Неожиданный статус команды: {0}.");
/* 24 */     hashtable.put("An I/O error occured while sending to the backend.", "Ошибка ввода/ввывода при отправке бэкенду.");
/* 25 */     hashtable.put("Unknown Response Type {0}.", "Неизвестный тип ответа {0}.");
/* 26 */     hashtable.put("Zero bytes may not occur in string parameters.", "Ноль байт не может быть в строковых параметрах.");
/* 27 */     hashtable.put("Unable to bind parameter values for statement.", "Не в состоянии ассоциировать значения параметров для команды.");
/* 28 */     hashtable.put("The driver currently does not support COPY operations.", "Драйвер в данный момент не поддерживате операции COPY.");
/* 29 */     hashtable.put("DataSource has been closed.", "DataSource закрыт.");
/* 30 */     hashtable.put("This PooledConnection has already been closed.", "Это PooledConnection уже было закрыто.");
/* 31 */     hashtable.put("Connection has been closed.", "Это Connection было закрыт.");
/* 32 */     hashtable.put("Statement has been closed.", "Statement закрыт.");
/* 33 */     hashtable.put("The array index is out of range: {0}", "Индекс массива вне диапазона: {0}");
/* 34 */     hashtable.put("The array index is out of range: {0}, number of elements: {1}.", "Индекс массива вне диапазона: {0}, число элементов: {1}.");
/* 35 */     hashtable.put("No results were returned by the query.", "Запрос не вернул результатов.");
/* 36 */     hashtable.put("A result was returned when none was expected.", "Результат возвращён когда его не ожидалось.");
/* 37 */     hashtable.put("Failed to create object for: {0}.", "Ошибка при создании объект для: {0}.");
/* 38 */     hashtable.put("Transaction isolation level {0} not supported.", "Уровень изоляции транзакций {0} не поддерживается.");
/* 39 */     hashtable.put("Invalid character data was found.  This is most likely caused by stored data containing characters that are invalid for the character set the database was created in.  The most common example of this is storing 8bit data in a SQL_ASCII database.", "Найдены неверные символьные данные.  Причиной этого скорее всего являются хранимые данные содержащие символы не соответствующие набору символов базы.  Типичным примером этого является хранение 8-битных данных в базе SQL_ASCII.");
/* 40 */     hashtable.put("The column name {0} was not found in this ResultSet.", "Имя колонки {0} не найдено в этом ResultSet''е.");
/* 41 */     hashtable.put("This ResultSet is closed.", "ResultSet закрыт.");
/* 42 */     hashtable.put("Unknown Types value.", "Неизвестное значение Types.");
/* 43 */     hashtable.put("Invalid stream length {0}.", "Неверная длина потока {0}.");
/* 44 */     hashtable.put("Unknown type {0}.", "Неизвестный тип {0}.");
/* 45 */     hashtable.put("This statement has been closed.", "Этот Sstatement был закрыт.");
/* 46 */     hashtable.put("Too many update results were returned.", "Возвращено слишком много результатов обновления.");
/* 47 */     hashtable.put("The class {0} does not implement org.postgresql.util.PGobject.", "Класс {0} не реализует org.postgresql.util.PGobject.");
/* 48 */     hashtable.put("Failed to initialize LargeObject API", "Ошибка при инициализации LargeObject API");
/* 49 */     hashtable.put("Large Objects may not be used in auto-commit mode.", "Большие объекты не могут использоваться в режиме авто-подтверждения (auto-commit).");
/* 50 */     hashtable.put("The SSLSocketFactory class provided {0} could not be instantiated.", "Предоставленный класс SSLSocketFactory {0} нельзя инстанциировать.");
/* 51 */     hashtable.put("Conversion of money failed.", "Ошибка при преобразовании типа money.");
/* 52 */     hashtable.put("Exception: {0}", "Ошибка/исключение: {0}");
/* 53 */     hashtable.put("Detail: {0}", "Подробности: {0}");
/* 54 */     hashtable.put("Hint: {0}", "Подсказка: {0}");
/* 55 */     hashtable.put("Position: {0}", "Позиция: {0}");
/* 56 */     hashtable.put("Where: {0}", "Где: {0}");
/* 57 */     hashtable.put("Location: File: {0}, Routine: {1}, Line: {2}", "Местонахождение: Файл {0}, Процедура: {1}, Строка: {2}");
/* 58 */     hashtable.put("Server SQLState: {0}", "SQLState сервера: {0}");
/* 59 */     table = hashtable;
/*    */   }
/*    */   
/*    */   public Object handleGetObject(String paramString) throws MissingResourceException {
/* 62 */     return table.get(paramString);
/*    */   }
/*    */   
/*    */   public Enumeration getKeys() {
/* 65 */     return table.keys();
/*    */   }
/*    */   
/*    */   public ResourceBundle getParent() {
/* 68 */     return this.parent;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\translation\messages_ru.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */