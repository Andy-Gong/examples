# message AMQ example
ActiveMQSession 
It provides a way to create <CODE>Queue</CODE> or <CODE>Topic</CODE> objects for those clients that need to dynamically manipulate provider-specific destination names.
It defines a serial order for the messages it consumes and the messages it produces.
 <LI>It retains messages it consumes until they have been acknowledged.
 <LI>It serializes execution of message listeners registered with its message consumers.
 If a client desires to have one thread produce messages while others consume them, the client should use a separate session for its producing thread.
  
 ActiveMQSessionExecutor
 A utility class used by the Session for dispatching messages asynchronously to consumers.
 
 ActiveMQSessionExecutor.iterate(), this method dispatches messages to consumers one by one.
 ActiveMQSessionExecutor has (MessageDispatchChannel messageQueue) to store the message by consumer. if there are 3 consumers, a produced message will generate 3 messages in messageQueue.
 
 FifoMessageDispatchChannel.dequeueNoWait() acquires the message, it contains LinkedList<MessageDispatch> list to store the messages.
 
 ActiveMQSessionExecutor.dispatch(MessageDispatch message)
 
 
 ActiveMQMessageConsumer.dispatch(MessageDispatch md), 
 It calls MessageListener to handle the message, and it also catches the exception.
 A client may either synchronously receive a message consumer's messages or have the consumer asynchronously deliver them as they arrive.
  * <P>
  * For synchronous receipt, a client can request the next message from a message
  * consumer using one of its <CODE> receive</CODE> methods. There are several
  * variations of <CODE>receive</CODE> that allow a client to poll or wait for
  * the next message.
  * <P>
  * For asynchronous delivery, a client can register a
  * <CODE>MessageListener</CODE> object with a message consumer. As messages
  * arrive at the message consumer, it delivers them by calling the
  * <CODE>MessageListener</CODE>'s<CODE>
  * onMessage</CODE> method.