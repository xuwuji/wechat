package com.xuwuji.wechat.model.user;

import com.xuwuji.wechat.model.UserMessage;
import com.xuwuji.wechat.util.TimeUtil;

/**
 * model for normal text message from user
 * 
 * @author wuxu
 * @time 2016Äê1ÔÂ8ÈÕ
 */
public class UserTextMessage implements UserMessage {

	private int id;
	// wechat developer count
	private String ToUserName;
	// openId for sender
	private String FromUserName;
	// unix timestamp of creating time
	private long CreateTime;
	// message type
	private String MsgType = "text";
	// message content
	private String Content;
	// message id
	private long MsgId;

	public UserTextMessage() {

	}

	public UserTextMessage(String toUserName, String fromUserName, int createTime, String msgType, String content,
			long MsgId) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
		Content = content;
		this.MsgId = MsgId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long MsgId) {
		this.MsgId = MsgId;
	}

	public void display() {
		System.out.println("The ToUserName of input message : " + this.ToUserName);
		System.out.println("The FromUserName of input message : " + this.FromUserName);
		System.out.println("The CreateTime of input message : " + TimeUtil.converUnix(this.CreateTime));
		System.out.println("The Type of input message : " + this.MsgType);
		System.out.println("The Content of input message : " + this.Content);
		System.out.println("The Message Id of input message : " + this.MsgId);
	}

	@Override
	public String toString() {
		return "TextMessage [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime
				+ ", MsgType=" + MsgType + ", Content=" + Content + ", MsgId=" + MsgId + "]";
	}

}
