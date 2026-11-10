# 📝 MY_WORK: Student Information, Development Log, Reflection & Answers

> This is the **only file** your instructor reads to grade Parts 3 and 4 (documentation and video). Everything you write here must be **in your own words**.

---

## 🛑 STOP: Read This Before You Do Anything Else

> ### 1️⃣ Read the whole `README.md` first
> The `README.md` in this repository contains the full instructions: how this assignment continues Assignment 1, the Chapter 5 recap, class descriptions, feature specifications, question prompts and the video script. **If you skip it, you will lose marks.**
>
> ### 2️⃣ Understand the full code before answering any question
> Open `SchedulerSimulationSync.java` and read it **from top to bottom**. You must be able to explain what `SharedStats`, `ReadyQueue`, `CpuWorker`, `RaceWindow.pause()`, `ReentrantLock` and `Semaphore` do **before** you write a single answer in Parts B and C. **Run the original starter first** (it is intentionally broken) and **save its output**: you need it for Question 1 of Part C.
>
> ### 3️⃣ Use only the code of this repository
> Work **only** on `SchedulerSimulationSync.java` from this repository. Do **not** copy or paste code from your previous assignments.
>
> ### 4️⃣ Commit many times, not once
> A single commit, or all commits made in the last hour, costs you **-0.5 mark**. See the [Commit Rules](#-commit-rules-mandatory) below.

**How to use this file:**
1. Fill in your **Student Information** (below) right now.
2. Follow the steps in the **Work Roadmap** in order.
3. Update the **Development Log** *every time* you work on the assignment, not at the end.
4. Do not delete any section header. Replace the `[...]` placeholders with your own text.

---

## 👤 Student Information

> ⚠️ **WARNING:** Fill this in first. Your name and ID must match the student ID you set in `SchedulerSimulationSync.java` (line 304) and the one you say in your video.

| Field | Your Answer |
|-------|-------------|
| **Full Name** | [Aisha Mohamed Alsayed] |
| **Student ID** | [445052832] |
| **University Email** | [445052832]@std.psau.edu.sa |
| **GitHub Username** | [Aisha449] |
| **Repository Link** | [(https://github.com/Aisha449/OS-Assignment3-Starter-Aisha-Alsayed/tree/main)] |
 
---

## 🎥 Video Link

**Video Link**: [Paste your video link here]

> ⚠️ **WARNING:** The video must be **publicly accessible** ("Anyone with the link can view") on **Google Drive**, **YouTube (Unlisted or Public)** or any other cloud file-sharing system. A private, restricted or broken link counts as a **missing video (-1 mark)**.
>
> 💡 **TIP:** Open the link in a **private/incognito window** before you submit. If it asks you to log in or request access, it is not public.
>
> 📌 **NOTE:** The link goes in **this file only** (`MY_WORK.md`), **not** in `README.md`. Name your video file `StudentID_Assignment3_Demo.mp4`. It must last **2 to 3 minutes**.

---

## 🗺️ Work Roadmap (follow in this order)

| Step | What to do | Where | Marks |
|:----:|------------|-------|:-----:|
| 0 | Read `README.md`, read the full code, **run the original starter and save its output** (it fails on purpose) | Your IDE | – |
| 1 | Fork, rename, keep the repo **PUBLIC**, set your student ID (line 304), **commit** | GitHub + code | Part 1 (1) |
| 2 | Feature 1: Mutex lock on the ready queue, **commit** | Code | Part 2 (0.25) |
| 3 | Feature 2: Mutex lock(s) on the shared statistics, **commit** | Code | Part 2 (0.25) |
| 4 | Feature 3: Counting semaphore instead of busy waiting, **commit** | Code | Part 2 (0.5) |
| 5 | Development Log (5+ entries, different dates) | This file, Part A | Part 3 (0.5) |
| 6 | Reflection (4 questions) | This file, Part B | Part 3 (0.5) |
| 7 | Technical Answers (4 questions) | This file, Part C | Part 3 (0.5) |
| 8 | Record the video, upload it, paste the link above | Video + this file | Part 4 (1.5) |
| 9 | Final check, then submit the repo link on Blackboard | Blackboard | – |

> 💡 **TIP:** Tick each step off as you go. Do not leave the log, the reflection or the video for the last day.

---

## 🔁 Commit Rules (MANDATORY)

> ### ⚠️ MANY COMMITS ARE REQUIRED. A single bulk commit is penalized (-0.5 mark).

**Minimum: 4 meaningful commits. Aim for 7 or more.**

| # | Commit | Example message |
|:-:|--------|-----------------|
| 1 | Student ID set | `Set my student ID: 441234567` |
| 2 | Feature 1: Ready queue lock | `Feature 1: Protected the ready queue with a ReentrantLock` |
| 3 | Feature 2: Statistics locks | `Feature 2: Added locks to the shared statistics counters` |
| 4 | Feature 3: Semaphore | `Feature 3: Replaced busy waiting with a counting semaphore` |
| 5 | Development log entries | `Docs: Added development log entries 1-3` |
| 6 | Reflection and answers | `Docs: Completed reflection and technical answers` |
| 7 | Video link | `Docs: Added demo video link` |

**Rules:**
- ✅ **One commit per feature.** Do not put all three features in one commit.
- ✅ **Commit after each work session**, and after each part of this file.
- ✅ **Spread your commits over different dates.** Not all in one day.
- ❌ **Do not make all commits in the last hour** before the deadline (November 14, 2026).
- ❌ **No vague messages** like `done`, `update` or `final version`.

> 💡 **TIP:** Your commit history is checked and you **show it in your video** (at least 4 commits visible). Your development log dates should match your commit dates.
>
> 💡 **TIP:** **Use VS Code** (see *Recommended Development Environment* in `README.md` for the full setup). Sign in to GitHub in VS Code, then commit from the Source Control panel (Ctrl+Shift+G) → stage → write a message → Commit → Sync/Push. You can edit and commit this file the same way. **Pushing** matters: commits that are not pushed to GitHub are invisible to the instructor.

---

# Part A: Development Log (0.5 mark)

> ⚠️ **WARNING:** Minimum **5 entries**, spread over **different dates**. Five entries written on the same day, or written all at once at the end, will lose marks and look like a copy. Entry dates should be **between the start of the assignment and the deadline (November 14, 2026)**.
>
> 💡 **TIP:** Write an entry at the **end of each work session**, while you still remember what happened. It takes 5 minutes.
>
> 💡 **TIP:** Be specific. "Worked on the code" is a weak entry. "Added a `ReentrantLock queueLock` to `ReadyQueue` and wrapped the body of `poll()` in `lock()` / `try` / `finally { unlock() }`" is a strong one.
>
> 📌 **NOTE:** Each entry needs: date and time, what you did, details, challenges, solution, and time spent. Real challenges are fine (and expected). Do not invent fake ones.

## Example Entry (do not copy it, write your own)

### Entry 1 - [November 2, 2026, 4:30 PM]
**What I did**: Forked the repository, set my student ID, and ran the original starter

**Details**:
- Forked the starter repository and renamed it
- Changed student ID on line 304 to my actual ID (441234567)
- Ran the original program: 4 CPUs started, three of them picked `P1`, then `IllegalThreadStateException` and the watchdog stopped the program
- Saved the output in a text file for Question 1
- Committed and pushed: `Set my student ID: 441234567`

**Challenges**: I did not understand why the same process was picked by three CPUs

**Solution**: Read `ReadyQueue.poll()` again and found the `RaceWindow.pause()` between reading the head and removing it

**Time spent**: 45 minutes

---

## Your Development Log

### Entry 1 - [Nov 5,2026 3:30]
**what I did**:update the id to my id

**Details**: I opened SchedulerSimulationSync.java and replaced the default student ID with my own student ID.

**Challenges**:I needed to locate the correct place where the ID was stored.

**Solution**:I updated the ID in the source code.

**Time spent**:10 min

---

### Entry 2 - [November 5, 2026 3:41]
**What I did**:Implemented Feature 1: Protected the ready queue with a ReentrantLock.

**Details**:I added a ReentrantLock to protect the ready queue and prevent multiple CPU threads from accessing and modifying it simultaneously. I used the lock in the queue operations.

**Challenges**:I needed to make sure that only one thread could modify the queue at a time.

**Solution**:I used the lock to protect the critical sections and released it using finally.

**Time spent**:20 min

---

### Entry 3 - [November 6, 2026 3:00]
**What I did**:Implemented Feature 2: Added locks to the shared statistics counters.

**Details**:I added a ReentrantLock to protect the shared statistics, including the context switch count, completed process count, and total service time.

**Challenges**:Multiple CPU threads could update the statistics simultaneously, which could lead to incorrect values.

**Solution**: I protected the shared statistics updates and getters using a lock.

**Time spent**:20 min

---

### Entry 4 - [November 6, 2026 3:21]
**What I did**:Implemented Feature 3: Replaced busy-waiting with a counting semaphore.

**Details**:I added a counting semaphore to notify CPU threads when processes are available in the ready queue. I also implemented a mechanism to wake up the CPU threads when all processes have completed.

**Challenges**:I needed to prevent CPU threads from continuously checking the queue when it was empty.

**Solution**: I used Semaphore.acquire() and Semaphore.release() to coordinate the CPU threads.

**Time spent**:25 min

---

### Entry 5 - [November 6, 2026 4:00]
**What I did**:Updated and refined SchedulerSimulationSync.java.

**Details**:I made additional updates to the scheduler implementation after adding the synchronization features.

**Challenges**: I needed to review the implementation and ensure that the changes were consistent.

**Solution**:I reviewed and updated the source code through several commits.

**Time spent**:10 min

---

### Entry 6 - [November 7, 2026]
**What I did**:Updated MY_WORK.md and modified the first Development Log entry.

**Details**: I started documenting my work in MY_WORK.md and revised the first entry to make the development log clearer and more accurate.

**Challenges**:I needed to organize the documentation and describe my implementation work clearly.

**Solution**: I updated the documentation and committed the changes to GitHub.

**Time spent**:10 min

---

## Development Log Summary

> 💡 **TIP:** Fill this in **last**, after all entries are written.

**Total time spent on assignment**: [X hours]

**Most challenging part**:

**Most interesting learning**:

**What I would do differently next time**:

---

# Part B: Reflection (0.5 mark)

> 🛑 **STOP:** Do **not** start this part until you have read the `README.md`, read the **entire** `SchedulerSimulationSync.java`, run it, and finished the three features.
>
> ⚠️ **WARNING:** Each answer must be **5 to 7 sentences**, in **your own words**. Copied or AI-generated answers without understanding get **0 marks for the whole assignment**. You may be asked to explain them in person.
>
> 💡 **TIP:** Mention concrete things you actually did: a method you wrote, an error you hit, a line of output you saw. Generic answers score low.
>
> 💡 **TIP:** Draft your answer in a few bullet points first, then turn them into sentences.

## Question 1: What did you learn about synchronization?

> 💡 **TIP:** Talk about race conditions (read-modify-write), critical sections, mutual exclusion with `ReentrantLock` and `try/finally`, the role of a counting semaphore, and what surprised you when the starter failed.

**Your Answer:** *(5-7 sentences)*

[I learned that synchronization is important when multiple threads access shared data at the same time. A race condition can happen when two threads read and update the same variable before either update is completed. In this assignment, I used a ReentrantLock to protect critical sections in the ready queue and shared statistics. I used try and finally to make sure that each lock is released even if an operation is interrupted by an exception. I also learned that a counting semaphore allows CPU threads to wait when the ready queue is empty instead of continuously checking it. The most surprising part was that the original starter could fail because multiple CPUs might select the same process.]

## Question 2: What was the most challenging part of this assignment?

> 💡 **TIP:** Pick **one** specific challenge (understanding why the starter failed, choosing the lock granularity, the initial value of the semaphore, the shutdown of the CPUs, Git, the video) and say *why* it was hard.

**Your Answer:** *(5-7 sentences)*

[The most challenging part was understanding how to coordinate multiple CPU threads safely. The ready queue is shared, so two CPUs must not remove or modify its elements at the same time. I also had to understand the difference between protecting the queue and protecting the shared statistics. Another challenge was understanding how the semaphore should wake up CPU threads when processes become available. I learned that the last completed process must also wake up CPUs that are still blocked, so the program can terminate correctly.]

## Question 3: How did you overcome the challenges you faced?

> 💡 **TIP:** Describe your method: reading the slides and the documentation, adding `System.out.println` to debug, running the program many times, re-reading the README, testing after each small change, asking for help.

**Your Answer:** *(5-7 sentences)*

[I worked through the assignment one feature at a time instead of changing everything at once. First, I reviewed the ready queue methods and added a lock around the operations that access the shared list and map. Then, I protected the statistics updates and getters using the statistics lock. After that, I used a counting semaphore to replace the busy-waiting loop in the CPU workers. I also reviewed the code after each feature and used separate Git commits to track the changes. This approach helped me understand the purpose of each synchronization mechanism.]

## Question 4: Where can synchronization concepts be applied in real-world applications?

> 💡 **TIP:** Use real applications you know (banking and money transfers, ticket or seat booking, a shared counter on a website, a print queue, a database) and connect each one to what you built here (shared data, critical section, lock or semaphore).

**Your Answer:** *(5-7 sentences)*

[Synchronization is used in banking systems when multiple transactions access the same account balance. A lock can prevent two transactions from updating the balance at the same time and producing an incorrect result. It is also useful in online ticket booking, where multiple users may try to reserve the same seat. Databases use synchronization to protect shared data when many users access or modify records. Print queues are another example because several users may send documents to a shared printer. These examples are similar to my assignment because multiple threads or users need safe access to shared resources.]

### Optional: What would you like to learn more about?

[Any topics related to synchronization or operating systems that you're curious about?]

### Optional: How confident do you feel about synchronization concepts now?

[Beginner / Intermediate / Confident. What do you understand well? What needs more practice?]

### Optional: Feedback on the assignment

[Any comments? Was it helpful? Too easy or hard? Suggestions?]

---

# Part C: Technical Answers (0.5 mark)

> 🛑 **STOP:** You cannot answer these questions without understanding the code. Re-read `SchedulerSimulationSync.java` and **run it** first. Your answers must reference **your own code and your own output** (your student ID makes your output and your expected values unique).
>
> ⚠️ **WARNING:** Each answer must be **3 to 5 sentences**, with specific examples from your code or output. Use correct terms: race condition, critical section, mutual exclusion, mutex lock, semaphore, busy waiting, deadlock, producer, consumer.
>
> 💡 **TIP:** Keep the output of the **original starter** and the output of your **final version** in text files or screenshots, so you can copy real snippets for Question 1.

## Question 1: Race Condition in Your Own Output

**Question**: Run the **original starter** (before any fix) and paste the **verification block** of that run. Which value was wrong (expected vs observed)? Explain **step by step**, with two named threads/CPUs, how **one lost update** happens on one of the shared variables (read, `RaceWindow.pause()`, write). Why can the same bug happen on a real machine even without `RaceWindow`, and why is it hard to detect?

> ⚠️ **WARNING:** The snippet must come from **your own run** (with your student ID), not from a classmate or from this README.
>
> 💡 **TIP:** Use a small timeline: *CPU-1 reads `contextSwitchCount` = 12 → CPU-2 reads 12 → CPU-1 writes 13 → CPU-2 writes 13 → one increment is lost.* If your starter run crashed before printing numbers, run it several times (or paste the exception and the watchdog message and explain it).

**Your Answer:** *(3-5 sentences)*

[In the original starter version, multiple CPU worker threads accessed and modified shared statistical counters simultaneously without synchronization, resulting in lost updates. In my specific run (Student ID: 445052832), the expected context switches were 25 while the observed count was lower, and completed processes were lost. A lost update occurs when two CPUs read the same value of a shared variable before either writes back its increment. On a real machine, CPU preemption or hardware parallelism creates this tiny timing window during read-modify-write instructions even without RaceWindow.pause(). These bugs are notoriously difficult to detect because they depend on unpredictable thread interleaving and rare hardware scheduling timings]

Verification block of the **original starter**:
```
[═══ VERIFICATION (expected values are computed from YOUR seed) ═══
  Context switches (quanta executed)     expected 25       observed 7        ✘ MISMATCH
  Completed processes                    expected 12       observed 4        ✘ MISMATCH
  Total service time (ms)                expected 73187    observed 26119    ✘ MISMATCH
  Busy-waiting polls (must be 0)         expected 0        observed 18452    ✘ MISMATCH
  ✘ At least one check failed (see the ✘ lines above): a race condition or busy waiting is still present.]
```

Verification block of **my final version**:
```
[═══ VERIFICATION (expected values are computed from YOUR seed) ═══
  Context switches (quanta executed)     expected 25       observed 25       ✔
  Completed processes                    expected 12       observed 12       ✔
  Total service time (ms)                expected 73187    observed 73187    ✔
  Busy-waiting polls (must be 0)         expected 0        observed 0        ✔
  ✔ All shared values are consistent.]
```

**Explanation of the timeline:**
[CPU-1 executes incrementContextSwitch() and reads contextSwitchCount = 12.

Before CPU-1 can write, RaceWindow.pause() simulates preemption, allowing CPU-2 to enter the method and read the same un-updated contextSwitchCount = 12.

CPU-1 resumes, calculates 12 + 1 = 13, and writes 13 back to contextSwitchCount.

CPU-2 resumes, calculates 12 + 1 = 13, and overwrites contextSwitchCount with 13.

Both threads performed an increment, but contextSwitchCount only increased by 1 instead of 2, resulting in a lost update]

## Question 2: Critical Sections and Lock Granularity

**Question**: List the **critical sections** you protected (variables and methods) and the lock(s) you used. **Lock granularity:** did you use **one lock** for the three statistics or **one lock per variable**? Why, and what is the trade-off? Why is the **ready queue** protected by a **different** lock than the statistics? Explain how your solution provides **mutual exclusion**, and discuss **progress** and **bounded waiting** (a default `ReentrantLock` is not fair: what does that imply?).

> 💡 **TIP:** The three counters are independent: does updating `contextSwitchCount` need to block an update of `totalServiceTime`? Also think about what could go wrong (deadlock, or long waiting) if the queue lock and the statistics lock were the **same** lock and a thread held it while doing something slow.

**Your Answer:** *(3-5 sentences)*

[I used two separate ReentrantLock instances: statsLock to protect the global performance counters and queueLock to protect the shared ready queue data structures (LinkedList and HashMap). I chose a single lock for all three statistical variables (statsLock) because these counters are updated together during quantum execution events, keeping locking logic simple without creating measurable contention overhead. The ready queue is protected by a separate lock to decouple queue operations from stats updates, preventing CPUs from blocking queue accesses while performing logging or counter updates. Mutual exclusion is guaranteed by enforcing lock() and try-finally { unlock() } blocks, while using standard non-fair ReentrantLock trades off strict FIFO thread acquisition order for higher system throughput.]

| Critical section (variable / method) | Lock used | Why it needs protection |
| :--- | :--- | :--- |
| `SharedStats.incrementContextSwitch()`, `incrementCompletedProcess()`, `addServiceTime()` | `statsLock` | Prevents race conditions during read-modify-write operations on shared counters. |
| `SharedStats.getContextSwitchCount()`, `getCompletedProcessCount()`, `getTotalServiceTime()` | `statsLock` | Ensures memory visibility and prevents dirty reads while updates are in progress. |
| `ReadyQueue.add()` | `queueLock` | Protects simultaneous insertions into `list` and `map` to prevent structural corruption. |
| `ReadyQueue.poll()` | `queueLock` | Prevents two CPUs from retrieving and popping the exact same head thread. |
| `ReadyQueue.processOf()`, `snapshot()` | `queueLock` | Ensures safe concurrent map lookups and prevents concurrent modification exceptions during iteration. |

## Question 3: Busy Waiting vs Semaphore

**Question**: Describe the **busy waiting** of the starter and its cost (give the `Busy-waiting polls` number from your **starter** run). What does the **value of your semaphore** represent? What is its **initial value** and why? What is its value right after the `numProcesses` initial `add()` calls? Why does the last CPU call `release(totalCPUs)` at the end, and what would happen if it did not?

> 💡 **TIP:** Think about what a CPU does when the queue is empty in the starter, and what a CPU does in your final version (is it running, ready or blocked?). Also check what happens to a CPU blocked in `acquire()` when no process will ever be added again.

**Your Answer:** *(3-5 sentences)*

[In the starter code, idle CPU worker threads continuously polled the ready queue in an active while loop when no processes were available, wasting significant CPU cycles through busy waiting (resulting in over 18,000 polls in the starter output). In the fixed version, the counting semaphore readyCount tracks the exact number of processes waiting in the ready queue. The semaphore is initialized to 0 because the queue starts empty before processes are queued, and its value increases to 12 (numProcesses) after main populates the initial jobs. At the end of execution, the last completing CPU calls readyCount.release(totalCPUs) to unblock any idle worker CPUs waiting inside acquire(), allowing them to check the completion condition and cleanly exit their execution loops.]

## Question 4: Classical Problems and Deadlock

**Question**: The CPUs and the ready queue look like a classical synchronization problem of Chapter 5. **Name it**, and say who plays the **producer** and the **consumer** roles (careful: here the CPUs also re-enqueue processes). If the ready queue were **bounded** (capacity `K`), name the **three semaphores** you would need and their **initial values**. Describe a **deadlock scenario** that appears if a thread acquired the **mutex before** waiting on the semaphore (or if two locks were acquired in different orders), and say **which of the four necessary conditions** you would break to prevent it.

> 💡 **TIP:** Recall the bounded-buffer solution of the slides (`empty`, `full`, `mutex`) and the order of `wait()` calls in the producer and the consumer. For the deadlock, think about a producer that holds the mutex while waiting for a free slot that only a consumer (who needs the mutex) can create.

**Your Answer:** *(3-5 sentences)*

**Classical problem and roles**:
[This system represents the Producer-Consumer Problem (or Multi-Producer Multi-Consumer Bounded-Buffer variant). The main thread acts as an initial Producer by adding processes, while CPU worker threads act as Consumers when taking processes to execute, and as Producers when re-enqueueing unfinished processes that yield after a quantum.]

**Semaphores for a bounded queue (name and initial value)**:
[mutex = 1 (Binary Semaphore / Mutex for mutual exclusion on queue modification)

emptySlots = K (Counting Semaphore tracking empty buffer slots, initialized to queue capacity K)

fullSlots = 0 (Counting Semaphore tracking available processes, initialized to 0)]

**Deadlock scenario and prevention**:
[A deadlock occurs if a CPU worker thread acquires mutex before waiting on emptySlots.acquire() when the queue is full. The CPU holds the mutex while waiting for an empty slot, but the consumer thread that could free a slot is blocked indefinitely from acquiring mutex. To prevent this deadlock, we break the Hold and Wait condition by ensuring threads wait on counting semaphores (fullSlots/emptySlots) before acquiring the binary mutex lock..]

## Summary

**Key concepts I understood through these questions:**
1.Race conditions during read-modify-write operations and critical section protection using ReentrantLock.
2.Efficient thread synchronization using counting semaphores to eliminate CPU busy-waiting.
3.Preventing Producer-Consumer deadlocks by establishing strict lock acquisition order.

**Concepts I need to study more:**
1.Fair vs. Non-fair locking policies and their impact on thread starvation.
2.Advanced lock-free data structures using Atomic references and Compare-And-Swap (CAS) primitives.

---

# ✅ Final Checklist (complete before submitting)

> ⚠️ **WARNING:** Go through every line. Blackboard does not accept submissions after the deadline, which is **November 14, 2026**.

**Repository**
- [ ] Repository is **PUBLIC** (Settings → Danger Zone → Visibility)
- [ ] Repository is renamed to `OS-Assignment3-YourFirstName-YourLastName`
- [ ] GitHub account uses the university email (`@std.psau.edu.sa`)

**Code**
- [ ] Student ID is set in `SchedulerSimulationSync.java` (line 304)
- [ ] My code is based **only** on the starter of this repository (nothing copied from previous assignments)
- [ ] Code compiles and runs with no errors, and terminates by itself (no `⛔ WATCHDOG`)
- [ ] Feature 1 (ready queue lock), Feature 2 (statistics locks) and Feature 3 (semaphore, no busy waiting) all work
- [ ] **The four verification lines are ✔ in 5 consecutive runs**
- [ ] Every lock is released in a `finally` block
- [ ] `RaceWindow`, `Config`, the verification block and the watchdog are **unchanged**
- [ ] Each feature has clear comments

**Commits**
- [ ] **At least 4 meaningful commits, ideally 7 or more**
- [ ] **One commit per feature**
- [ ] Commits are spread over **different dates** (not all in the last hour)
- [ ] Everything is **pushed** to GitHub

**This file (`MY_WORK.md`)**
- [ ] Full name and student ID filled in at the top
- [ ] Development log has **5+ entries** on different dates
- [ ] Reflection: 4 questions, 5-7 sentences each
- [ ] Technical answers: 4 questions, 3-5 sentences each, with examples from **your** output (starter output **and** final output pasted in Question 1)
- [ ] No `[...]` placeholders left
- [ ] No section headers deleted

**Video**
- [ ] 2-3 minutes long, named `StudentID_Assignment3_Demo.mp4`
- [ ] Shows your name, ID, repository, 3 features (and your lock granularity choice), IDE execution with the verification block, one synchronization concept, and commit history
- [ ] Link is **public** (tested in an incognito window) and pasted in the **Video Link** section above

**Blackboard**
- [ ] Submit **only** the link to your public GitHub repository

> 🎯 **Good luck!** Start early, commit regularly, and make sure you can explain every line you submit.
