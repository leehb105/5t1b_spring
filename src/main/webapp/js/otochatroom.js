//	채팅 체크 소켓 
var otoChatHostAddr	= window.location.host+"/5t1b/";
var otoChatHostHttpAddr	= "http://"+window.location.host+"/5t1b/";


const otoChatAlarmWS = new WebSocket("ws://"+otoChatHostAddr+"/otoChatAlarmWebsocket");
otoChatAlarmWS.onopen = (e) => {
};
otoChatAlarmWS.onmessage = (e) => {
	const msg = JSON.parse(e.data);
	otoChatAlarmMsgToHtmlRcv(msg);
};
otoChatAlarmWS.onerror = (e) => {
	//console.log("error!", e);
};
otoChatAlarmWS.onclose = (e) => {
	//console.log("close!", e);
};
 
//	채팅 요청 
function fnGoOtoChat(otoSenderId, otoReceiverId, otoSRTp, otoSenderNm, otoReceiverNm,otoChatNm ){ 
//alert("otoSenderId"+otoSenderId+otoReceiverId+otoSRTp+otoSenderNm+otoReceiverNm+otoChatNm);
	const reqChatMsg = {
			type: "REQCHAT",
			sender: otoSenderId,
			receiver : otoReceiverId,
			srTp: otoSRTp,
			senderNm: otoSenderNm,
			receiverNm : otoReceiverNm,
			msg: otoChatNm,
			time: Date.now()
		};
	
	// 전송처리 
	const jsonStr = JSON.stringify(reqChatMsg);
	otoChatAlarmWS.send(jsonStr); // 웹소켓 통해 전송
}
 
//	결과 수신 한 처리 
const otoChatAlarmMsgToHtmlRcv = ({type, sender,receiver,sRTp,senderNm,receiverNm,msg,time}) => {
//	alert(type+ ":" + msg+ ":" + sender+ ":" +receiver+ ":" +sRTp+ ":" +senderNm+ ":" +receiverNm+ ":" +time);

	//	에러이면 메세지 출력후 종료 
	if(type == "ERRCHAT"){
		alert(msg);
		return;
	}

	//	REQCHAT가 오면 채팅창을 출력해준다 
	if(type == "REQCHAT"){
		var	otoNm =	"";
		if(sRTp == "R"){
			otoNm =	senderNm;
		}else{
			otoNm =	receiverNm;
		}		
		fnOpenOtoChatPop(sender, receiver, sRTp, otoNm+"님과 1:1 대화중");
	}  	
}; 
// 채팅 팝업 출력 
function fnOpenOtoChatPop(otoSenderId, otoReceiverId, otoSRTp, otoChatNm){
    var otoPopUrl = otoChatHostHttpAddr+"otochat/otochatroom?otoSenderId="+otoSenderId+"&otoReceiverId="+otoReceiverId+"&otoSRTp=" +otoSRTp;
    var otoPopName = otoChatNm +"님과 1:1 채팅";
	var otoPopOptions = 'top=10, left=10, width=330, height=500, status=no, menubar=no, toolbar=no, resizable=no, titlebar=no, location=no';
    
	var otoPopWin	= window.open(otoPopUrl, otoPopName, otoPopOptions);
} 