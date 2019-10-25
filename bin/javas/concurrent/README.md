Interfaces:
-----------
	Callable
	CompletionService
	Executor
	ExecutorService
	ForkJoinPool.ForkJoinWorkerThreadFactory
	ForkJoinPool.ManagedBlocker
	Future
	ScheduledExecutorService
	ScheduledFuture

	BlockingDeque
	BlockingQueue
	ConcurrentMap
	ConcurrentNavigableMap

	Delayed
RejectedExecutionHandler
RunnableFuture
RunnableScheduledFuture
ThreadFactory
	TransferQueue>				?

Classes:
--------
	ExecutorCompletionService
	Executors
	ForkJoinPool
	ForkJoinTask
	ForkJoinWorkerThread
	RecursiveAction
	ThreadPoolExecutor 	
	ThreadPoolExecutor.AbortPolicy 	
	ThreadPoolExecutor.CallerRunsPolicy 	
	ThreadPoolExecutor.DiscardOldestPolicy 	
	ThreadPoolExecutor.DiscardPolicy
	ScheduledThreadPoolExecutor

	AbstractExecutorService
	ArrayBlockingQueue
	LinkedBlockingDeque
	LinkedBlockingQueue
	ConcurrentHashMap
	ConcurrentLinkedDeque
	ConcurrentLinkedQueue
	ConcurrentSkipListMap
	ConcurrentSkipListSet
	CopyOnWriteArrayList
	CopyOnWriteArraySet
	DelayQueue> 						expects some delay in every element.
	PriorityBlockingQueue> 				prioritizes based on value rather than order.

	CountDownLatch
	CyclicBarrier
	Exchanger
	FutureTask> child class of RunnableFuture interface
	LinkedTransferQueue>				?	
Phaser 	
RecursiveTask 	
	Semaphore 	
	SynchronousQueue>fair = true > the waiting threads will be granted access in FIFO (First-In First-Out) order.
	ThreadLocalRandom 	

Exceptions:
-----------
BrokenBarrierException 	
CancellationException 	
ExecutionException 	
RejectedExecutionException 	
TimeoutException


1. ForkJoin, RecursiveAction
2. Executor[I], Executors[U], ExecutorService[I], ScheduledExecutorService[I], ThreadPoolExecutor[C], CompletionService[I], ExecutorCompletionService[C] Non Blocking, Callable[I], Future[I], ScheduledFuture, newWorkStealingPool
3. BlockingDeque, BlockingQueue, ConcurrentMap, ConcurrentNavigableMap, ArrayBlockingQueue, ConcurrentHashMap, ConcurrentLinkedDeque, ConcurrentLinkedQueue, ConcurrentSkipListMap[Navigable and Sorted], ConcurrentSkipListSet, CopyOnWriteArrayList, CopyOnWriteArraySet
   LinkedBlockingDeque, LinkedBlockingQueue, DelayQueue,Delayed[I] PriorityBlockingQueue, LinkedTransferQueue, TransferQueue, SynchronousQueue, Semaphore, Exchanger, CountDownLatch, CyclicBarrier