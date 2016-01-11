package com.xuwuji.wechat.app.model.result;

import com.xuwuji.wechat.app.model.ResultMessage;

/**
 * Model for Normal Text Message From BackEnd and Back to User
 * 
 * @author wuxu
 * @time 2016Äê1ÔÂ8ÈÕ
 */
public class ResultTextMessage implements ResultMessage {
	// receiver open id
	private String ToUserName;
	// wechat count of developer
	private String FromUserName;
	// unix timestamp of creating time
	private long CreateTime;
	// message type
	private String MsgType = "text";
	// message content
	private String Content;

	public ResultTextMessage() {
		super();
		this.MsgType = "text";
	}

	public ResultTextMessage(String toUserName, String fromUserName, long createTime, String msgType, String content) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = "text";
		Content = content;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	@Override
	public String toString() {
		return "ResultTextMessage [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime="
				+ CreateTime + ", MsgType=" + MsgType + ", Content=" + Content + "]";
	}

}
