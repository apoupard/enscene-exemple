package io.enscene.exemple.websocket.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket")
public class MyEndpoint {

  @OnMessage
  public String echoText(String name) {
    return name;
  }

  @OnMessage
  public void echoBinary(byte[] data, Session session) throws IOException {
    System.out.println("echoBinary: " + data);
    StringBuilder builder = new StringBuilder();
    for (byte b : data) {
      builder.append(b);
    }
    System.out.println(builder);
    session.getBasicRemote().sendBinary(ByteBuffer.wrap(data));
  }

  // @WebSocketMessage
  // public void echoBinary(ByteBuffer data, Session session) throws IOException {
  // System.out.println("echoBinary: " + data);
  // StringBuilder builder = new StringBuilder();
  // for (byte b : data.array()) {
  // builder.append(b);
  // }
  // System.out.println(builder);
  // session.getRemote().sendBytes(data);
  // }
}
