package com.robot.messageQueue;

import java.util.ArrayList;

public class MessageLooper extends Thread {

	private static MessageLooper mMsgLooper = new MessageLooper();

	private static int EMPTY_MSG_CMD = -10;

	private static int STOP_LOOP = -100;

	private boolean mFlag = false;

	private MessageLooper() {
		// NO-OP
	}

	public static MessageLooper getInstance() {
		if (mMsgLooper == null) {
			mMsgLooper = new MessageLooper();
		}
		return mMsgLooper;
	}

	@Override
	public void run() {
		mFlag = true;
		while (mFlag) {
			try {
				Message msg = MessageQueue.getInstance().getMessageQueue().take();

				for (OnMessageHandleListener mOnMessageHandleListener : mOnMessageHandleListenerList) {
					if (mOnMessageHandleListener != null
							&& msg.getCommand() != EMPTY_MSG_CMD
							&& msg.getCommand() != STOP_LOOP) {

						mOnMessageHandleListener.onMesageHandle(msg);

					}
				}

				if (msg.getCommand() == STOP_LOOP) {
					mFlag = false;
					mOnMessageHandleListenerList.clear();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void loop() {
		if(mFlag){
			return;
		}
		getInstance().start();
	}

	public void stopLoop() {
		MessageQueue.getInstance().addMessage(new Message() {
			@Override
			public int getCommand() {
				return STOP_LOOP;
			}
		});
	}

	private ArrayList<OnMessageHandleListener> mOnMessageHandleListenerList = new ArrayList<OnMessageHandleListener>();

	public interface OnMessageHandleListener {
		public void onMesageHandle(Message msg);
	}

	public void addOnMessageHandleListener(OnMessageHandleListener mOnMessageHandleListener) {
		mOnMessageHandleListenerList.add(mOnMessageHandleListener);
	}

}
