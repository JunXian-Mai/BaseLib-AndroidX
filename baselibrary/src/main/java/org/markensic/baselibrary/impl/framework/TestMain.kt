package org.markensic.baselibrary.impl.framework

import org.markensic.baselibrary.api.utils.LogFileUtils
import org.markensic.baselibrary.api.utils.ThreadUtils
import org.markensic.baselibrary.global.AppLog
import java.util.concurrent.ThreadPoolExecutor

fun main() {
    val b = ResizableCapacityLinkedBlockIngQueue<Runnable>(1000)
    println(b.getCapacity())
    b.setCapacity(999)
    println(b.getCapacity())
}