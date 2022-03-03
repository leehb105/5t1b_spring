package com.otlb.semi.chat.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
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

@ServerEndpoint(value = "/otoChatAlarmWebsocket", configurator = OtoChatAlarmWebsocketConfig.class)
public class OtoChatAlarmWebsocket {

	@OnOpen
	public void onOpen(EndpointConfig config, Session session) throws IOException {

		// 소켓 세션 조회
		Map<String, Object> userProp = session.getUserProperties();
		String userId = (String) userProp.get("userId");
		String loginYn = (String) userProp.get("loginYn");

		// 로그인 한 경우 채팅 창 오픈, 아닐 경우 소멸
		if (loginYn != null && loginYn.equals("Y")) {
			// 아이디 설정 처리
			OtoChatInfo.addAlarmClients(userId, session);
		}
	}

	/**
	 * make json data type
	 * 
	 * @param type
	 * @param sndId
	 * @param rcvId
	 * @param msg
	 * @return
	 */
	private String msgToJson(String type, String sndId, String rcvId, String sRTp, String sndNm, String rcvNm,
			String msg) {
		Map<String, Object> map = new HashMap<>();
		map.put("type", type);
		map.put("sender", sndId);
		map.put("receiver", rcvId);
		map.put("sRTp", sRTp);
		map.put("senderNm", sndNm);
		map.put("receiverNm", rcvNm);
		map.put("msg", msg);
		map.put("time", System.currentTimeMillis());
		return new Gson().toJson(map);
	}

	@OnMessage
	public void onMessage(String msgInfo, Session session) throws IOException {
System.out.println("onMessage111>>"+msgInfo);	
		Map<String, Object> msgMap = new Gson().fromJson(msgInfo, HashMap.class);
		Map<String, Session> alarmClients = OtoChatInfo.getAlramClients();

		// 대상의 소켓 세션을 가져온다
		Map<String, Object> userProp = session.getUserProperties();
		String userId = (String) userProp.get("userId");
		String loginYn = (String) userProp.get("loginYn");

		
System.out.println("onMessage>>"+msgMap);		
		// 메세지를 정리
		String type = "" + msgMap.get("type");
		String otoSenderId = "" + msgMap.get("sender");
		String otoReceiverId = "" + msgMap.get("receiver");
		String otoSenderNm = "" + msgMap.get("senderNm");
		String otoReceiverNm = "" + msgMap.get("receiverNm");
		String otoMsg = "" + msgMap.get("msg");

		if (type != null && type.equals("REQCHAT")) {
			// 본인의 세션을 조회한다 (본인이 로그인 되어 있는지 체크한다)
			Session thisUserSession = OtoChatInfo.getAlramClientUser(otoSenderId);

			// 본인이 로그인 안된 경우 오류 처리
			if (thisUserSession == null) {
				System.out.println("[OtoChatAlarmWebsocket][onMessage]" + thisUserSession + ":" + otoSenderId);
				String chatReqErrMsg = "로그인 후 사용 가능합니다.";
				Basic basic = session.getBasicRemote();
				basic.sendText(msgToJson("ERRCHAT", otoSenderId, otoReceiverId, "S", otoSenderNm, otoReceiverNm,
						chatReqErrMsg)); // 액션을 실행하게 한다
				return;
			}
			// 본인에게 채팅요청 할 경우 오류 처리
			if (otoSenderId.equals(otoReceiverId)) {
				System.out.println("[OtoChatAlarmWebsocket][onMessage]" + thisUserSession + ":" + otoSenderId + ":"
						+ otoReceiverId);
				String chatReqErrMsg = "본인 입니다.";
				Basic basic = session.getBasicRemote();
				basic.sendText(msgToJson("ERRCHAT", otoSenderId, otoReceiverId, "S", otoSenderNm, otoReceiverNm,
						chatReqErrMsg)); // 액션을 실행하게 한다
				return;
			}
			// 세션의 아이디와 요청자의 아이디가 다른 경우 오류
			if (!otoSenderId.equals(userId)) {
				System.out.println("[OtoChatAlarmWebsocket][onMessage]" + thisUserSession + ":" + otoSenderId + ":"
						+ otoReceiverId);
				String chatReqErrMsg = "로그인 사용자가 요청자가 아닙니다.";
				Basic basic = session.getBasicRemote();
				basic.sendText(msgToJson("ERRCHAT", otoSenderId, otoReceiverId, "S", otoSenderNm, otoReceiverNm,
						chatReqErrMsg)); // 액션을 실행하게 한다
				return;
			}
			// 본인이 대화중인지 확인한다
			Map<String, String> partyMap = OtoChatInfo.getOtoChatParty();

			String thisPartyStr = partyMap.get(userId);
			if (thisPartyStr == null) {
				thisPartyStr = "";
			}
			if (thisPartyStr.indexOf(otoReceiverId) != -1) {
				System.out.println("[OtoChatAlarmWebsocket][onMessage]" + thisPartyStr + ":" + thisUserSession + ":"
						+ otoSenderId + ":" + otoReceiverId);
				String chatReqErrMsg = "이미 대화중입니다.";
				Basic basic = session.getBasicRemote();
				basic.sendText(msgToJson("ERRCHAT", otoSenderId, otoReceiverId, "S", otoSenderNm, otoReceiverNm,
						chatReqErrMsg)); // 액션을 실행하게 한다
				return;
			}

			// 상대방 세션을 조회한다
			Session alramSession = OtoChatInfo.getAlramClientUser(otoReceiverId);
			// 현재 시간을 조회한다
			int thisHH = 0;

			try {
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat thisFmt = new SimpleDateFormat("HH");
				thisHH = Integer.parseInt(thisFmt.format(cal.getTime()));
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 상대방이 로그인 아니면 오류 처리
			if (alramSession == null) {
				System.out.println("[OtoChatAlarmWebsocket][onMessage]" + thisUserSession + ":" + otoSenderId + ":"
						+ otoReceiverId + ":" + alramSession);
				String chatReqErrMsg = "상대가 로그인 상태가 아닙니다.";
				Basic basic = session.getBasicRemote();
				basic.sendText(msgToJson("ERRCHAT", otoSenderId, otoReceiverId, "S", otoSenderNm, otoReceiverNm,
						chatReqErrMsg)); // 액션을 실행하게 한다
				return;
			} else if (thisHH > 18 || thisHH < 9) {
				// 현재 시간이 9시에서 6시 사이면 채팅 불가처리 한다
				String chatReqErrMsg = "채팅 가능 시간이 아닙니다.";
				Basic basic = session.getBasicRemote();
				basic.sendText(msgToJson("ERRCHAT", otoSenderId, otoReceiverId, "S", otoSenderNm, otoReceiverNm,
						chatReqErrMsg));
				return;
			} else {
				// 송수신 모두 채팅을 실행한다
				// 송신자
				Basic sndBasic = session.getBasicRemote();
				// 액션 실행
				sndBasic.sendText(msgToJson(type, otoSenderId, otoReceiverId, "S", otoSenderNm, otoReceiverNm, otoMsg));

				// 수신자
				Basic rcvBasic = alramSession.getBasicRemote();
				// 액션 실행
				rcvBasic.sendText(msgToJson(type, otoSenderId, otoReceiverId, "R", otoSenderNm, otoReceiverNm, otoMsg));
			}
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
	}
}
