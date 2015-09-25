package messageQueue;

public class MessageImpl implements Message{
	
	public  static final int CMD_CAPUTURE_SCREEN = 0x00;
	
	public int mCmd;
	
	public MessageImpl(int mCmd){
		this.mCmd = mCmd;
	}

	@Override
	public int getCommand() {
		return this.mCmd;
	}

}
