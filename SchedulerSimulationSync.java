import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;
// TODO (Feature 1 and Feature 2): import java.util.concurrent.locks.ReentrantLock;
// TODO (Feature 3):              import java.util.concurrent.Semaphore;

// ANSI Color Codes for enhanced terminal output (same as Assignment 1)
class Colors {
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String WHITE = "\u001B[37m";
    public static final String BRIGHT_WHITE = "\u001B[97m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
}

// ⚠️ DO NOT REMOVE OR MODIFY THIS CLASS ⚠️
// A race condition happens when a thread is preempted BETWEEN reading a shared value and writing it back.
// On a real machine this window is only a few nanoseconds wide, so the bug appears rarely and randomly
// (which is exactly what makes race conditions so dangerous!). To make the bug show up reliably in this
// assignment, the starter code calls RaceWindow.pause() inside the unprotected code: it simulates the
// operating system preempting the thread at the worst possible moment.
class RaceWindow {
    static void pause() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Global simulation settings
class Config {
    // All sleeps are divided by TIME_SCALE so that the simulation runs 5x faster than in Assignment 1.
    // Times displayed in the output are still the "nominal" times (burst time, quantum, ...) in ms.
    static final int TIME_SCALE = 5;
}

// ⚠️ SHARED STATISTICS: updated by ALL the process threads at the same time ⚠️
// (these are the three "features" you counted in Assignment 1: context switches, completed processes, time)
class SharedStats {
    static int totalProcesses = 0;            // set once by main (never modified afterwards)
    static int totalCPUs = 0;                // set once by main (never modified afterwards)
    static int contextSwitchCount = 0;        // shared counter: incremented every time a process starts a quantum
    static int completedProcessCount = 0;    // shared counter: incremented every time a process finishes
    static long totalServiceTime = 0;        // shared accumulator: sum of the CPU time (ms) actually executed
    // Number of times a CPU found the ready queue empty and polled it again (busy waiting). Must be 0 after Feature 3.
    static final java.util.concurrent.atomic.AtomicInteger busyWaitPolls = new java.util.concurrent.atomic.AtomicInteger(0);
    private static final ReentrantLock statsLock = new ReentrantLock();
    // TODO Feature 2: declare the lock(s) protecting the three shared variables above here.
    // Think about it: ONE lock for the three variables, or ONE lock PER variable? (you must justify it in MY_WORK.md)

    public static void incrementContextSwitch() {
        // TODO Feature 2: protect this critical section
        statsLock.lock();
        try {
            int tmp = contextSwitchCount;       // 1) read
            RaceWindow.pause();                 //    <- another thread can read the SAME value here
            contextSwitchCount = tmp + 1;       // 2) write  (one increment is lost if someone else did the same)
        } finally {
            statsLock.unlock();
        }
    }

    public static void incrementCompletedProcess() {
        // TODO Feature 2: protect this critical section
        statsLock.lock();
        try {
            int tmp = completedProcessCount;
            RaceWindow.pause();
            completedProcessCount = tmp + 1;
        } finally {
           statsLock.unlock();
        }
    } 

    public static void addServiceTime(int ms) {
        // TODO Feature 2: protect this critical section
        statsLock.lock();
        try {
            long tmp = totalServiceTime;
            RaceWindow.pause();
            totalServiceTime = tmp + ms;
        } finally {
            statsLock.unlock();
        }
    }
    // Readers must also be protected (a read in the middle of an update is not safe either)
    public static int getCompletedProcessCount() {
        // TODO Feature 2: protect this read
        statsLock.lock();
        try {
            return completedProcessCount;
        } finally {
            statsLock.unlock();
        }
    }

    public static int getContextSwitchCount() {
        // TODO Feature 2: protect this read
         statsLock.lock();
        try {
            return contextSwitchCount;
        } finally {
            statsLock.unlock();
        }
    }   

    public static long getTotalServiceTime() {
        // TODO Feature 2: protect this read
         statsLock.lock();
         try {
            return totalServiceTime;
        } finally {
            statsLock.unlock();
        }
    }
}

// ⚠️ THE READY QUEUE: shared by ALL the CPU threads ⚠️
// In Assignment 1 only the main thread used the queue. Now several CPUs poll and re-enqueue processes
// at the same time, so every access to `list` and `map` is a critical section.
class ReadyQueue {
    private final LinkedList<Thread> list = new LinkedList<>();   // FIFO queue of threads
    private final Map<Thread, Process> map = new HashMap<>();    // thread -> process (same idea as Assignment 1)

    private final ReentrantLock queueLock = new ReentrantLock();
    final Semaphore readyCount = new Semaphore(0);

    // TODO Feature 3: declare the counting semaphore that counts the processes waiting in the ready queue here

    // Add a process to the ready queue (new process, or a process that did not finish its quantum)
    public void add(Process process) {
        // TODO Feature 1: protect this critical section
        Thread thread = new Thread(process);
        queueLock.lock();
        try {
            list.add(thread);
            map.put(thread, process);
            // TODO Feature 3: signal that one more process is available in the queue
            readyCount.release();
        } finally {
            queueLock.unlock();
        }

        System.out.println(Colors.BLUE + "  ➕ " + Colors.BOLD + Colors.CYAN + process.getName() + Colors.RESET +
                Colors.BLUE + " (Priority: " + process.getPriority() + ") added to ready queue" + Colors.RESET +
                " │ Remaining: " + Colors.YELLOW + process.getRemainingTime() + "ms" + Colors.RESET +
                " of " + process.getBurstTime() + "ms");
    }

    // Remove and return the thread at the head of the queue (or null if the queue is empty)
    public Thread poll() {
        // TODO Feature 1: protect this critical section
        queueLock.lock();
        try {
            if (list.isEmpty()) {
                return null;
            }
            Thread head = list.getFirst();      // 1) read the head of the queue
            RaceWindow.pause();                 //    <- another CPU can read the SAME head here
            list.removeFirst();                 // 2) remove the head (it may be a different thread by now!)
            return head;
        } finally {
            queueLock.unlock();
        }
    }

    public Process processOf(Thread thread) {
        // TODO Feature 1: protect this access
        queueLock.lock();
        try {
            return map.get(thread);
        } finally {
            queueLock.unlock();
        }
    }

    // Names of the processes currently waiting in the queue (used to display the ready queue)
    public String snapshot() {
        // TODO Feature 1: protect this critical section
        queueLock.lock();
        try {
            StringBuilder sb = new StringBuilder();
            for (Thread t : list) {
                if (sb.length() > 0) sb.append(" → ");
                sb.append(map.get(t).getName());
            }
            return sb.length() == 0 ? "empty" : sb.toString();
        } finally {
            queueLock.unlock();
        } 
    }
}

// Class representing a process (same as Assignment 1) - executes ONE time quantum per thread start
class Process implements Runnable {
    private final String name;
    private final int burstTime;             // total CPU time needed (nominal ms)
    private final int timeQuantum;         // maximum time per CPU access (nominal ms)
    private int remainingTime;
    private final int priority;              // Assignment 1 - Feature 1: priority 1..10 (10 = highest), display only
    private final long creationTime;         // Assignment 1 - Feature 3: for waiting / turnaround time
    private long completionTime = 0;
    private volatile int cpuId = 0;         // the CPU thread that is currently running this process

    public Process(String name, int burstTime, int timeQuantum, int priority) {
        this.name = name;
        this.burstTime = burstTime;
        this.timeQuantum = timeQuantum;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.creationTime = System.currentTimeMillis();
    }

    public void setCpuId(int cpuId) {
        this.cpuId = cpuId;
    }

    @Override
    public void run() {
        int runTime = Math.min(timeQuantum, remainingTime);

        // Assignment 1 - Feature 2: a context switch happens every time a process starts running on a CPU
        SharedStats.incrementContextSwitch();

        System.out.println(Colors.BRIGHT_GREEN + "  ▶ [CPU-" + cpuId + "] " + Colors.BOLD + Colors.CYAN + name +
                Colors.RESET + Colors.GREEN + " (Priority: " + priority + ") executing quantum" + Colors.RESET +
                " [" + runTime + "ms]");

        try {
            Thread.sleep(runTime / Config.TIME_SCALE);      // simulate the execution
        } catch (InterruptedException e) {
            System.out.println(Colors.RED + "  ✗ " + name + " was interrupted." + Colors.RESET);
        }

        remainingTime -= runTime;
        SharedStats.addServiceTime(runTime);

        int overallProgress = (int) (((double) (burstTime - remainingTime) / burstTime) * 100);
        System.out.println(Colors.YELLOW + "  ⏸ [CPU-" + cpuId + "] " + Colors.CYAN + name + Colors.RESET +
                " completed quantum " + Colors.BRIGHT_YELLOW + runTime + "ms" + Colors.RESET +
                " │ Overall progress: " + createProgressBar(overallProgress, 20) +
                Colors.MAGENTA + "  remaining: " + remainingTime + "ms" + Colors.RESET);

        if (remainingTime > 0) {
            System.out.println(Colors.BLUE + "  ↻ " + Colors.CYAN + name + Colors.RESET +
                    " yields CPU for context switch");
        } else {
            completionTime = System.currentTimeMillis();
            SharedStats.incrementCompletedProcess();
            System.out.println(Colors.BRIGHT_GREEN + "  ✓ " + Colors.BOLD + Colors.CYAN + name + Colors.RESET +
                    Colors.BRIGHT_GREEN + " finished execution!" + Colors.RESET);
        }
    }

    private String createProgressBar(int progress, int width) {
        int filled = (progress * width) / 100;
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < width; i++) {
            bar.append(i < filled ? Colors.GREEN + "█" + Colors.RESET : Colors.WHITE + "░" + Colors.RESET);
        }
        bar.append("] ").append(progress).append("%");
        return bar.toString();
    }

    public String getName() { return name; }
    public int getBurstTime() { return burstTime; }
    public int getRemainingTime() { return remainingTime; }
    public int getPriority() { return priority; }
    public boolean isFinished() { return remainingTime <= 0; }

    // Nominal turnaround time (ms) = (completion - creation) scaled back to the nominal time
    public long getTurnaroundTime() {
        return completionTime == 0 ? 0 : (completionTime - creationTime) * Config.TIME_SCALE;
    }

    // Nominal waiting time (ms) = turnaround time - burst time
    public long getWaitingTime() {
        return completionTime == 0 ? 0 : Math.max(0, getTurnaroundTime() - burstTime);
    }
}

// A CPU of the simulated machine: takes a process from the shared ready queue, runs ONE quantum, and repeats.
// (In Assignment 1 this loop was executed by the main thread: now several CPUs execute it at the same time.)
class CpuWorker extends Thread {
    private final int cpuId;
    private final ReadyQueue readyQueue;

    public CpuWorker(int cpuId, ReadyQueue readyQueue) {
        super("CPU-" + cpuId);
        this.cpuId = cpuId;
        this.readyQueue = readyQueue;
    }

    @Override
    public void run() {
