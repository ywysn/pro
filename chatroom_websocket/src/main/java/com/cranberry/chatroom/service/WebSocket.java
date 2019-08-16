package com.cranberry.chatroom.service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket")
public class WebSocket {
    private static CopyOnWriteArraySet<WebSocket> clients = new CopyOnWriteArraySet<WebSocket>();
    private Session session;

    @OnOpen
    public void OnOpen(Session session){
        this.session = session;
        clients.add(this);
        System.out.println("有新的连接，当前的SessionId为：" + session.getId()+",当前聊天室共有"+clients.size()+"人");
    }

    @OnError
    public void OnError(Throwable e){
        System.err.println("websocket连接失败");
        e.printStackTrace();
    }
     @OnMessage
    public void OnMessage(String msg){
         System.out.println("浏览器发来的信息为" + msg);
         for(WebSocket webSocket : clients){
             webSocket.sendMessage(msg);
         }
     }
     public void sendMessage(String msg){
         try {
             this.session.getBasicRemote().sendText(msg);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     @OnClose
    public void OnClose(){
         System.out.println("有用户退出聊天室");
         clients.remove(this);
         System.out.println("当前聊天室还剩下："+clients.size()+"人");
     }
}
