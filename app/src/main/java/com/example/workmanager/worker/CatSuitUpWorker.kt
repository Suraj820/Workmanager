package com.example.workmanager.worker

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class CatSuitUpWorker(ctx : Context,workerParameters: WorkerParameters):Worker(ctx,workerParameters) {
    override fun doWork(): Result {
        val catAgentID = inputData.getString(INPUT_CAT_AGENT_ID)
        Thread.sleep(3000)
        val outPutData = Data.Builder()
            .putString(OUTPUT_CAT_AGENT_ID,catAgentID)
            .build()
        return Result.success(outPutData)

    }
    companion object{
        const val INPUT_CAT_AGENT_ID ="input_id"
        const val OUTPUT_CAT_AGENT_ID ="output_id"
    }
}