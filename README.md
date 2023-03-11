# Workmanager
Learning Workmanager

 # what are services?</br>
Services are application components designed to run in the background, even when
an app is not running. With the exception of foreground services, which are tied to
a notification, services have no user interface. It is important to note that services
run on the main thread of their hosting process.</br>

# What is Work Manager?</br>
Work Manager is a library part of Android Jetpack which makes it easy to schedule deferrable,
asynchronous tasks that are expected to run even if the app exits or device restarts i.e. even your
app restarts due to any issue Work Manager makes sure the scheduled task executes again.</br>

# To use WorkManager , we need to know about its Five main classes: </br>
 1) <b>WorkManager :</b>  WorkManager receives work and enqueues it
    based on provided arguments and constraints (such as internet connectivity and
    the device charging).</br>
 2) <b>Worker :</b>  it's a wrapper around the work that needs
    doing. It has one function, doWork() , which we override to implement the
    background work code. doWork() will be executed in a background thread.</br>
 3) <b>WorkRequest :</b> This class binds a Worker class to arguments and constraints.
    There are two types of WorkRequest :OneTimeWorkRequest , which runs the work once, and
    PeriodicWorkRequest , which can be used to schedule work to run at a fixed interval.
 4) <b>ListenableWorker.Result :</b> this class holding the result of the executed work. The result can be one of
    Success , Failure , or Retry .
 5) <b>Data :</b>  which holds data passed to and from the worker.

# Steps to Implement
1) Add dependency in our app build.gradle
   ```gradle
   dependencies {
    ......
    ........
    implementation "androidx.work:work-runtime:2.8.0"
    ......
    ........
    }
   ```
   
2) Create worker class which extends Worker:
  ``` kotlin
class TaskWorker(ctx : Context, workerParameters: WorkerParameters): Worker(ctx,workerParameters){
    override fun doWork(): Result {
       ........
       .............
        return Result
    }
}
 ```

3) Create Constraints

```kotlin
val networkConstraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
```

4) Create  Workrequest 

```kotlin
val workRequst = OneTimeWorkRequest.Builder(TaskWorker::class.java)
            .setConstraints(networkConstraints)
            .setInputData(Data.Builder().putString(catAgentIdKey,catAgentIdValue).build())
            .build()
```

5) Define WorkManager
```kotlin
 private val workManager = WorkManager.getInstance(this)
```

6) Call work manger

```kotlin 
workManager.beginWith(workRequst)
            .then(workRequst2)
            .then(workRequst3)
            .then(workRequst4)
            .enqueue()
```
# Reference
1)<a href="https://developer.android.com/guide/background/persistent/getting-started">developer.android.com</a></br>
2)<a href="https://blog.mindorks.com/integrating-work-manager-in-android/">blog.mindorks.comm</a>




