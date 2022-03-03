package com.otlb.semi.chat.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

public class OtoChatInfo {

	/**
	 ****************************************************************
	 * 알람을 위한 저장소
	 **************************************************************** 
	 */
	public static Map<String, Session> alarmClients = new HashMap<>();

	// 알람 정보 저장 
	public static void addAlarmClients(String userId, Session session) { 
		alarmClients.put(userId, session); 
		
		logAlramClients();
	}
	
	// 알람 정보 로그 출력 
	public static void logAlramClients() {
		System.out.printf("OtoChatAlarmWebsocket.logClients(%d) : %s%n", alarmClients.size(), alarmClients.keySet());
	}	
	//	알람 대상자 세션 조회 
	public static Map<String, Session>  getAlramClients(){
		return alarmClients;
	}
	
	public static Collection<Session>  getAlramClientList(){
		return alarmClients.values();
	}
	
	//	알람을 주려는 대상자를 조회한다 
	public static Session  getAlramClientUser(String userId){
		Collection<Session> sessions = alarmClients.values();
		
		for(Session sess : sessions) {
			//	사용자 아이디를 체크한다  
			Map<String, Object> sessionUserProp = sess.getUserProperties();
System.out.println("[OtoChatInfo][sessionUserProp]"+sessionUserProp);
System.out.println("[OtoChatInfo][userId]"+userId);
			

			String sessUserId	=	""+sessionUserProp.get("userId");
			//	대상이 같을 때만 메세지를 출력한다 
			if(userId.equals(sessUserId)) {
				return sess;
			}
		}		
		return null;
	}
	public static void  removeAlramClientsUser(String userId){
		//alarmClients.remove(userId);
	}
	
	/**
	 ****************************************************************
	 * 채팅을 위한 저장소
	 **************************************************************** 
	 */
	public static Map<String, Session> clients = new HashMap<>();  //  사용자의 세션 정보 저장 
	public static Map<String, String> partyMap = new HashMap<>();  //  사용자의 대화 상대방을 저장 
	
	
	public static void logOtoClients() {
		System.out.printf("OtoChatInfo.logOtoClients(%d) : %s%n", clients.size(), clients.keySet());
    } 
	
	// 채팅 정보 저장 
	public static void addOtoChatClients(String userId, Session session) { 
		clients.put(userId, session);
	}	
	
    //	채팅 대상자 세션 조회 
	public static Map<String, Session> getOtoChatClients(){
		return clients;
	}
	
	// 파티 대상자 세션 조회
	public static Map<String, String> getOtoChatParty() {
		return partyMap;
	}
	
	// 파티 대상자 세션 설정 
	public static void setOtoChatParty(String userId,String partyStr) {
		partyMap.put(userId, partyStr);
	}
	
	public static Collection<Session> etOtoChatClientList(){
		return clients.values();
	}
	
	//	채팅을 주려는 대상자를 조회한다 
	public static Session getOtoChatClientUser(String userId){
		Collection<Session> sessions = clients.values();
		
		for(Session sess : sessions) {
			//	사용자 아이디를 체크한다  
			Map<String, Object> sessionUserProp = sess.getUserProperties();
			String sessUserId =	""+sessionUserProp.get("userId");
			//	대상이 같을 때만 메세지를 출력한다 
			if(userId.equals(sessUserId)) {
				return sess;
			}
		}		
		return null;
	}
	public static void removeOtoChatClientsUser(String userId){
		clients.remove(userId);
	}
 
}
