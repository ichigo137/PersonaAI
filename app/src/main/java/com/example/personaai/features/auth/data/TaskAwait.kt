package com.example.personaai.features.auth.data

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * Bridges a Google Play Services [Task] (Firebase, etc.) to a coroutine.
 * Avoids pulling in `kotlinx-coroutines-play-services` for a single call site.
 */
suspend fun <T> Task<T>.await(): T = suspendCancellableCoroutine { cont ->
    addOnSuccessListener { cont.resume(it) }
    addOnFailureListener { cont.resumeWithException(it) }
}
