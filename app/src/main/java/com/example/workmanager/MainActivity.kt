package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.workmanager.worker.CatFurGroomingWorker
import com.example.workmanager.worker.CatLitterBoxSittingWorker
import com.example.workmanager.worker.CatStretchingWorker
import com.example.workmanager.worker.CatSuitUpWorker

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val workManager = WorkManager.getInstance(this)

        val networkConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED).build()

        val catAgentId = "CatAgent1"

        val catStretchingWorkRequst = OneTimeWorkRequest.Builder(CatStretchingWorker::class.java)
            .setConstraints(networkConstraints)
            .setInputData(getCatAgentIdInputData(CatStretchingWorker.INPUT_CAT_AGENT_ID,catAgentId))
            .build()
        val catSuitUpWorker = OneTimeWorkRequest.Builder(CatSuitUpWorker::class.java)
            .setConstraints(networkConstraints)
            .setInputData(getCatAgentIdInputData(CatSuitUpWorker.INPUT_CAT_AGENT_ID,catAgentId))
            .build()
        val catFurGroomingWorker = OneTimeWorkRequest.Builder(CatFurGroomingWorker::class.java)
            .setConstraints(networkConstraints)
            .setInputData(getCatAgentIdInputData(CatFurGroomingWorker.INPUT_CAT_AGENT_ID,catAgentId))
            .build()
        val catLitterBoxSittingWorker = OneTimeWorkRequest.Builder(CatLitterBoxSittingWorker::class.java)
            .setConstraints(networkConstraints)
            .setInputData(getCatAgentIdInputData(CatStretchingWorker.INPUT_CAT_AGENT_ID,catAgentId))
            .build()

        workManager.beginWith(catStretchingWorkRequst)
            .then(catSuitUpWorker)
            .then(catFurGroomingWorker)
            .then(catLitterBoxSittingWorker)
            .enqueue()

        workManager.getWorkInfoByIdLiveData(catStretchingWorkRequst.id).observe(this) { info ->
            if (info.state.isFinished) {
                showResult("Agent done stretching")
            }
        }
        workManager.getWorkInfoByIdLiveData(catSuitUpWorker.id).observe(this) { info ->
            if (info.state.isFinished) {
                showResult("Agent done SuitUpWorker")
            }
        }
        workManager.getWorkInfoByIdLiveData(catFurGroomingWorker.id).observe(this) { info ->
            if (info.state.isFinished) {
                showResult("Agent done GroomingWorker")
            }
        }

        workManager.getWorkInfoByIdLiveData(catFurGroomingWorker.id).observe(this) { info ->
            if (info.state.isFinished) {
                showResult("Agent done GroomingWorker")
            }
        }

    }
    fun getCatAgentIdInputData(catAgentIdKey:String,
                               catAgentIdValue: String) = Data.Builder().putString(catAgentIdKey,catAgentIdValue).build()

    private fun showResult(message: String) {
        Toast.makeText(this, message, LENGTH_SHORT).show()
    }


    }

