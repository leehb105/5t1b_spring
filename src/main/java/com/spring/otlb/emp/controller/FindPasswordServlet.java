package com.otlb.semi.emp.controller;

import java.io.IOException;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.otlb.semi.common.EmpUtils;
import com.otlb.semi.emp.model.service.EmpService;
import com.otlb.semi.emp.model.vo.Emp;

import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * Servlet implementation class FindPasswordServlet
 */
@WebServlet("/emp/findPassword")
public class FindPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmpService empService = new EmpService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/emp/findPassword.jsp").forward(request, response);
	}

	/**
	 * select count(*) from emp where emp_no = ? and emp_name = ? and email = ?
	 * update emp set password = ? where emp_no = ?
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. 사용자 입력값 처리
		int empNo = Integer.parseInt(request.getParameter("empNo"));
		String empName = request.getParameter("empName");
		String email = request.getParameter("email");
		System.out.println("empNo = " + empNo);
		System.out.println("empName = " + empName);
		System.out.println("email = " + email);

		// 2. 업무요청
		HttpSession httpSession = request.getSession();
		int result = empService.checkEmpInfo(empNo, empName, email);
		System.out.println("result = " + result);
		if (result > 0) {
			// 사원정보 일치 -> 임시비밀번호 생성 & 메일 전송
			
			// 임시비밀번호 발급
			String randomPassword = getRandomPassword();
			// 임시 비밀번호 암호화
			String encryptedPassword = EmpUtils.getEncryptedPassword(randomPassword);
			
			// 2. 업무 요청
			Emp emp = new Emp();
			emp.setEmpNo(empNo);
			emp.setPassword(encryptedPassword);
			int result1 = empService.updatePassword(emp);
			
			// 메일 보내는 코드
			// 메일 인코딩
			final String bodyEncoding = "UTF-8"; // 콘텐츠 인코딩

			String subject = "5T1B - 임시 비밀번호";
			String fromEmail = "semi5taku@gmail.com";
			String fromUsername = "관리자";
			String toEmail = "yaa4500@naver.com"; // 콤마(,)로 여러개 나열

			final String username = "semi5taku@gmail.com";
			final String password = "dhxkzn123!";

			// 메일에 출력할 텍스트
			StringBuffer sb = new StringBuffer();
			sb.append("임시 비밀번호는 " + randomPassword + "입니다. 비밀번호 변경 후 사용하세요.\n");
			String html = sb.toString();

			// 메일 옵션 설정
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.auth", "true");

			props.put("mail.smtp.quitwait", "false");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");

			try {
				// 메일 서버 인증 계정 설정
				Authenticator auth = new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				};

				// 메일 세션 생성
				Session session = Session.getInstance(props, auth);

				// 메일 송/수신 옵션 설정
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(fromEmail, fromUsername));
				message.setRecipients(RecipientType.TO, InternetAddress.parse(toEmail, false));
				message.setSubject(subject);
				message.setSentDate(new Date());

				// 메일 콘텐츠 설정
				Multipart mParts = new MimeMultipart();
				MimeBodyPart mTextPart = new MimeBodyPart();
				MimeBodyPart mFilePart = null;

				// 메일 콘텐츠 - 내용
				mTextPart.setText(html, bodyEncoding, "html");
				mParts.addBodyPart(mTextPart);

				// 메일 콘텐츠 설정
				message.setContent(mParts);

				// MIME 타입 설정
				MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
				MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
				MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
				MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
				MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
				MailcapCmdMap
						.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
				CommandMap.setDefaultCommandMap(MailcapCmdMap);

				// 메일 발송
				Transport.send(message);

			} catch (Exception e) {
				e.printStackTrace();
			}

			// 3. 응답요청
			httpSession.setAttribute("modalHeader", "이메일 전송 성공");
			httpSession.setAttribute("modalBody", "이메일을 확인하세요.");
			String location = request.getContextPath() + "/emp/login";
			response.sendRedirect(location);

		} else {
			// 사원정보 불일치
			System.out.println("요청실패");
			// 3. 응답요청
			httpSession.setAttribute("modalHeader", "회원 정보 오류");
			httpSession.setAttribute("modalBody", "일치하는 회원이 없습니다. 입력한 값을 확인해주세요.");
			String location = request.getContextPath() + "/emp/findPassword";
			response.sendRedirect(location);
		}

	}

	/**
	 * 임시비밀번호 생성
	 */
	public String getRandomPassword() {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
				'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&' };
		StringBuffer sb = new StringBuffer();
		SecureRandom sr = new SecureRandom();
		sr.setSeed(new Date().getTime());
		
		int idx = 0;
		final int LENGTH_OF_RANDOM_PASSWORD = 8;
		int len = charSet.length;
		for(int i = 0; i < LENGTH_OF_RANDOM_PASSWORD; i++) {
			idx = sr.nextInt(len);
			sb.append(charSet[idx]);
		}

		return sb.toString();
	}

}
