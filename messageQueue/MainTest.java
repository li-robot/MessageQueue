import messageQueue.Message;
import messageQueue.MessageImpl;
import messageQueue.MessageLooper;
import messageQueue.MessageLooper.OnMessageHandleListener;
import messageQueue.MessageQueue;

public class MainTest {

	public static final void main(String[] args) {

		MessageLooper.getInstance().start();
		MessageLooper.getInstance().setOnMessageHandleListener(new OnMessageHandleListener() {

			@Override
			public void onMesageHandle(Message msg) {

				System.out.println(msg.getCommand());

			}
		});

		MessageImpl msgImpl = new MessageImpl(MessageImpl.CMD_CAPUTURE_SCREEN);

		MessageQueue.getInstance().addMessage(msgImpl);

		MessageImpl msgImpl1 = new MessageImpl(20);
		MessageQueue.getInstance().addMessage(msgImpl1);

		MessageLooper.getInstance().stopLoop();

	}
}
