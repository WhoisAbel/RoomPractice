package ir.whoisAbel.roompractice.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers

suspend fun getBitmap(context: Context): Bitmap {
    val loading = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data("https://avatars.githubusercontent.com/u/92686655?s=96&v=4")
        .dispatcher(Dispatchers.IO)
        .listener(
            onSuccess = { request, metadata ->
                Log.d("coil", metadata.toString())
            },
            onError = { request, throwable ->
                Log.d("coil", throwable.toString())
            }
        )
        .build()

    val result = (loading.execute(request) as SuccessResult).drawable
    return (result as BitmapDrawable).bitmap

}