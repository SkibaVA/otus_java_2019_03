package ru.otus;

import java.lang.management.GarbageCollectorMXBean;
import java.util.List;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;

import com.sun.management.GarbageCollectionNotificationInfo;

public final class GcMonitoring {
    public static void startMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    
                    String gcAction = info.getGcAction();
                    long duration = info.getGcInfo().getDuration();

                    if (gcAction.contains("minor")) {
                    	GcStatistic.incrementMinorCount();
                    	GcStatistic.incrementMinorTime(duration);
                    }
                    else if (gcAction.contains("major")) {
                    	GcStatistic.incrementMajorCount();
                    	GcStatistic.incrementMajorTime(duration);
                    }
                }
            };
            emitter.addNotificationListener(listener, null, null);
        }
    }
}
