package com.example.workmanager.worker

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

@Suppress("UNREACHABLE_CODE")
class CatStretchingWorker(ctx : Context, workerParameters: WorkerParameters): Worker(ctx,workerParameters){
    override fun doWork(): Result {
        TODO("Not yet implemented")
        val catAgentId = inputData.getString(INPUT_CAT_AGENT_ID)
        Thread.sleep(3000)
        val outputData = Data.Builder()
            .putString(OUTPUT_CAT_AGENT_ID,catAgentId)
            .build()
        return Result.success(outputData)
    }
    companion object{
        const val INPUT_CAT_AGENT_ID ="input_id"
        const val OUTPUT_CAT_AGENT_ID ="output_id"
    }

}