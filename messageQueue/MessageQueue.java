package messageQueue;

import java.util.concurrent.ArrayBlockingQueue;

public class MessageQueue {

	private ArrayBlockingQueue<Message> msgQueueList = new ArrayBlockingQueue<Message>(20);

	private static MessageQueue mMsgQueue = new MessageQueue();

	public static MessageQueue getInstance() {

		if (mMsgQueue == null) {
			mMsgQueue = new MessageQueue();
			return mMsgQueue;
		}
		return mMsgQueue;
	}

	private MessageQueue() {
		// NO-OP
	}

	public ArrayBlockingQueue<Message> getMessageQueue() {
		return msgQueueList;
	}

	public void addMessage(Message msg) {
		getInstance().getMessageQueue().add(msg);
	}
}
