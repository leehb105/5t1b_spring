package com.otlb.semi.chat.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import com.otlb.semi.emp.model.vo.Emp;

public class OtoChatAlarmWebsocketConfig extends Configurator {

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		//
		HttpSession session = (HttpSession) request.getHttpSession();
		Emp ssEmp = (Emp) session.getAttribute("loginEmp");
System.out.println("[OtoChatAlarmWebsocketConfig][ssEmp]"+ssEmp);
		Map<String, Object> userProp = sec.getUserProperties();

		if (ssEmp != null) {
			String userId = "" + ssEmp.getEmpNo();

			// config
			userProp.put("userId", userId); // 현재 사용자 아이디
			userProp.put("loginYn", "Y"); // 로그인 여부
		} else {
			// 로그인 아닌경우는 로그인 아님을 알려준다
			userProp.put("userId", ""); // 현재 사용자 아이디
			userProp.put("loginYn", "N"); // 로그인 여부
		}

		userProp.put("alarmYn", "Y"); // 알람인지 여부
	}

}
