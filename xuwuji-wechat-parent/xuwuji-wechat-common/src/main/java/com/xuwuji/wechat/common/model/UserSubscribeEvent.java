package com.xuwuji.wechat.common.model;

/**
 * Model for User Input Subscribe Event
 * 
 * @author wuxu
 * @time 2016年1月12日
 */
public class UserSubscribeEvent {

	private int id;

	// dev count
	private String ToUserName;

	// user open id
	private String FromUserName;

	private long CreateTime;

	private String MsgType = "event";

	private String Event;

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

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	@Override
	public String toString() {
		return "UserSubscribeEvent [id=" + id + ", ToUserName=" + ToUserName + ", FromUserName=" + FromUserName
				+ ", CreateTime=" + CreateTime + ", MsgType=" + MsgType + ", Event=" + Event + "]";
	}

}
