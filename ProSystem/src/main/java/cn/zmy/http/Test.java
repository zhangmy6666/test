package cn.zmy.http;

public class Test {
	public static void main(String[] args) {
		String line = "20171109141718267|1|{'meeting':{\"accountId\":\"61000070\","
				+ "\"beginTime\":1510208216999,\"boxAccount\":\"\","
				+ "\"createTime\":1510208217027,\"deleteFlag\":false,"
				+ "\"endTime\":1541744216999,\"host\":\"61000070\","
				+ "\"id\":17750,\"meetingNo\":\"90017750\",\"meetingPwd\":\"qsqsqs\","
				+ "\"meetingState\":2,\"meetingType\":1,\"realBeginTime\":1510208216999,"
				+ "\"speakersNum\":6,\"updateTime\":1510208217027,\"visiable\":false},"
				+ "'meetingEquipment':[{\"accountId\":\"14000155\","
				+ "\"createTime\":1510208217051,\"deleteFlag\":false,\"id\":0,"
				+ "\"meetingId\":17750,\"meetingNo\":\"90017750\","
				+ "\"updateTime\":1510208217051}],'meetingInviter':[{"
				+ "\"createTime\":1510208217134,\"id\":0,"
				+ "\"inviteeAccountId\":\"14000155\",\"inviterAccountId\":\"61000070\","
				+ "\"meetingId\":17750,\"meetingNo\":\"90017750\"}]}";
		StringBuilder sb = new StringBuilder();
		sb.append("service=UpdateDatabase&params={'line':\"");
	    sb.append(line.replace("\"", "'"));
	    sb.append("\"}");
	    String strJSON = HttpUtil.sendPost("http://10.134.101.164:8080/SyncService/callService", sb.toString());
	}

	
}
