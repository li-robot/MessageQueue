package messageQueue;

public class MessageLooper extends Thread{
	
	private static MessageLooper mMsgLooper = new MessageLooper();
	
	private static int EMPTY_MSG_CMD = - 10;
	
	private static int STOP_LOOP = -100;
	
	private boolean mFlag = true;
	
	private MessageLooper(){
		
	}
	
	public static MessageLooper getInstance(){
		if(mMsgLooper == null){
			mMsgLooper = new MessageLooper();
		}
		return mMsgLooper;
	}

	@Override
	public void run() {
		while(mFlag){
			try {
				Message msg = MessageQueue.getInstance().getMessageQueue().take();
				if(mOnMessageHandleListener != null
						&& msg.getCommand() != EMPTY_MSG_CMD 
						&& msg.getCommand() != STOP_LOOP){
					
					mOnMessageHandleListener.onMesageHandle(msg);
					
				}
				if(msg.getCommand() == STOP_LOOP){
					mFlag = false;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loop(){
		mFlag = true;
		getInstance().start();
	}
	
	public void stopLoop(){
		MessageQueue.getInstance().addMessage(new Message(){
			@Override
			public int getCommand() {
				return STOP_LOOP;
			}
		});
	}
	
	private OnMessageHandleListener mOnMessageHandleListener;
	
	public interface OnMessageHandleListener{
		public void onMesageHandle(Message msg);
	}
	
	public void setOnMessageHandleListener(OnMessageHandleListener mOnMessageHandleListener){
		this.mOnMessageHandleListener = mOnMessageHandleListener;
	}
	
}
