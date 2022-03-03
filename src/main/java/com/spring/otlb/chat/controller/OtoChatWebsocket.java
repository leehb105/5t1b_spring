package com.otlb.semi.chat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

@ServerEndpoint(value="/otoChatWebsocket", configurator=OtoChatWebsocketConfig.class)
public class OtoChatWebsocket {

	@OnOpen
	public void onOpen(EndpointConfig config, Session session) throws IOException {
		
		System.out.println("[getParameterMap][onOpen]");	
	
		// userId - session 
		Map<String, Object> userProp = config.getUserProperties();
		
		// 1.first get UserId from session
		String otoSenderId = (String) userProp.get("otoSenderId");
		String otoReceiverId = (String) userProp.get("otoReceiverId");
		String otoSenderNm = (String) userProp.get("otoSenderNm");
		String otoReceiverNm = (String) userProp.get("otoReceiverNm");
		String otoSRTp = (String) userProp.get("otoSRTp");
		String userId = (String) userProp.get("userId");
		String userNm = (String) userProp.get("userNm");

		System.out.println("[OtoChatWebsocket][userProp]"+userProp); 
		
		//	아이디 세션 설정 처리 
		OtoChatInfo.addOtoChatClients(userId, session);
		
		// 대화 상대방을 저장한다
		// 123004,3000,2000
		Map<String, String>	partyMap = OtoChatInfo.getOtoChatParty();
		
		String thisUserListStr = partyMap.get(userId);
		if (thisUserListStr == null) {
			thisUserListStr = "";
		}

		String partyId = ""; // 대화 상대방 아이디

		if (otoSRTp.equals("S")) {
			partyId = otoReceiverId;
		} else {
			partyId = otoSenderId;
		}
		// 아이디가 있는지 체크 한다
		// 없으면 추가한다
		if (thisUserListStr.indexOf(partyId) == -1) {
			OtoChatInfo.setOtoChatParty(userId, thisUserListStr+","+partyId);
		}
		
	}
	
	/**
	 * make json data type 
	 * @param type
	 * @param sndId
	 * @param rcvId
	 * @param msg
	 * @return
	 */
	private String msgToJson(String type, String sndId,String rcvId, String msg) {
		Map<String, Object> map = new HashMap<>();
		map.put("type", type);
		map.put("sender", sndId);
		map.put("receiver", rcvId);
		map.put("msg", msg);
		map.put("time", System.currentTimeMillis());
		return new Gson().toJson(map);
	}
	
	@OnMessage
	public void onMessage(String msgInfo, Session session) throws IOException {
		Map<String, Object> msgMap = new Gson().fromJson(msgInfo, HashMap.class);
		
		// userId - session 
		Map<String, Object> userProp = session.getUserProperties();
		
		// 1.first get UserId from session
		String userId = (String) userProp.get("userId");
		String userNm = (String) userProp.get("userNm");

//		System.out.println("[OtoChatWebsocket][userProp]"+userProp); 
		System.out.println("[OtoChatWebsocket][msgMap]"+msgMap);		
		
		//	메세지를 정리한다 	
		String type = "" + msgMap.get("type");
		String senderId = "" + msgMap.get("sender");
		String receiverId = "" + msgMap.get("receiver");
		String senderNm = "" + msgMap.get("senderNm");
		String receiverNm = "" + msgMap.get("receiverNm");
		String otoMsg = "" + msgMap.get("msg");
		
		//	본인의 세션 정보를 가져온다  
		Session	sndSess = null;
		Session	rcvSess = null;
		
		Map<String, Session> clients = OtoChatInfo.getOtoChatClients();
		
		//	소켓 세션을 확인한다 
		synchronized(clients) {
			Collection<Session> sessionsList = clients.values();
			
			for(Session thisSess : sessionsList) {
				//	현재 세션 정보 조회 
				Map<String, Object> thisSessionUserProp = thisSess.getUserProperties();
				String thisSessUserId =	""+thisSessionUserProp.get("userId");

				
				//	본인의 아이디와 같으면 해당 세션 조회
				if(thisSessUserId.equals(senderId)) {
					sndSess	= thisSess;
				}
				// 상대방의 아이디와 같으면 상대방 세션 조회
				if(thisSessUserId.equals(receiverId)) {
					rcvSess	= thisSess;
				}
			}
		}
		//	세션 정보가 없으면 오류를 처리한다 
		//	본인 소켓 세션 없을때 
		if(sndSess == null || rcvSess == null) {
			System.out.println("[OtoChatWebsocket][onMessage1][대화창모두 종료]"+sndSess+":"+senderId+":"+receiverId+":"+rcvSess);			
			return;
		}
		try {
			//	상대방에게 대화를 보낸다 
			//	만일 오류시 에러 메세지를 본인에게 보낸다 
			Basic basic = rcvSess.getBasicRemote();
			basic.sendText(msgInfo);
		}catch(Exception e) {
			System.out.println("[OtoChatWebsocket][채팅불가3]"+sndSess+":"+senderId+":"+receiverId+":"+rcvSess);			
			String	chatReqErrMsg =	"대화 상대방이 채팅 가능 상태가 아닙니다.(대화창종료)";
			Basic basic = session.getBasicRemote();
			basic.sendText(msgToJson("",senderId, receiverId, chatReqErrMsg)); // 오류 메세지 출력 
		}
	}
	
	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session session) throws IOException {
		// clients 
		Map<String, Object> userProp = session.getUserProperties();
		String userId = (String) userProp.get("userId");
		String userNm = (String) userProp.get("userNm");
		String otoReceiverNm = (String) userProp.get("otoReceiverNm");
		String otoSenderId	=(String) userProp.get("otoSenderId");
		String otoReceiverId =(String) userProp.get("otoReceiverId");
		String otoSRTp	=(String) userProp.get("otoSRTp");
		String partyId	=	""; // 대화의 상대방 
		
System.out.println("[OtoChatWebsocket][onClose][userProp]>>"+userProp);

		//	대화의 상대방을 찾아낸다 (메세지를 전달하기 위해서)
		if(otoSRTp.equals("S")) {
			partyId	= otoReceiverId;
		}else {
			partyId	= otoSenderId;
		}


		// 상대방에 채팅 종료 메세지를 출력한다 
		Map<String, String>	partyMap = OtoChatInfo.getOtoChatParty();
		
		String  thisPartyStr = partyMap.get(userId);
		if (thisPartyStr == null) {
			thisPartyStr = "";
		}	
		//	sender, recever 모두 삭제 한다 
		String senderPartyStr =	partyMap.get(otoSenderId);
		senderPartyStr = senderPartyStr.replaceAll(otoReceiverId, "");
		OtoChatInfo.setOtoChatParty(otoSenderId, senderPartyStr);
		
		String receiverPartyStr	= partyMap.get(otoReceiverId);
		receiverPartyStr = receiverPartyStr.replaceAll(otoSenderId, "");
		OtoChatInfo.setOtoChatParty(otoReceiverId, receiverPartyStr);
		
		
		
		//	상대방들에게 모두 메세지를 출력한다 
		System.out.println("[OtoChatWebsocket][대화창 종료시 상대방]"+userId+">>"+partyId);
		Map<String, Session> clients  = OtoChatInfo.getOtoChatClients();
		Collection<Session> sessionsList = clients.values();
		
		for(Session thisSess : sessionsList) {
			//	현재 세션 정보 조회 
			Map<String, Object> thisSessionUserProp = thisSess.getUserProperties();
			String thisSessUserId =	""+thisSessionUserProp.get("userId");
			
			if(thisSessUserId.equals(partyId)) {
				//	상대방에게 대화를 보낸다 
				//	만일 오류시 에러 메세지를 본인에게 보낸다 
				Basic basic = thisSess.getBasicRemote();
				String closeMsg = msgToJson("CLOSE", userId,thisSessUserId, "채팅이 종료 되었습니다");
				basic.sendText(closeMsg);
			}
		}
		
		try {
			Thread.sleep(1000); // 5초 정도 대기한다 
		}catch(Exception e) {
			
		}
		clients.remove(userId);
	}
}
