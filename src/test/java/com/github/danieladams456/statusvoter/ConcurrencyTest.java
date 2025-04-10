package com.github.danieladams456.statusvoter;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.github.danieladams456.statusvoter.TestData.STATUSES_IN_ORDER;
import static org.assertj.core.api.Assertions.assertThat;

public class ConcurrencyTest {
    /**
     * This test checks for atomic compare-and-set merges.
     * Set each status on a separate thread, and always expect the final result to be the most severe.
     */
    @Test
    void multipleConcurrentUpdates() throws InterruptedException {
        final int testIterations = 10_000;
        int failureCount = 0;  // should not need AtomicInteger

        for (int i = 0; i < testIterations; i++) {
            try (ExecutorService executor = Executors.newFixedThreadPool(STATUSES_IN_ORDER.size())) {
                CountDownLatch startLatch = new CountDownLatch(1);
                CountDownLatch doneLatch = new CountDownLatch(STATUSES_IN_ORDER.size());
                StatusVoter voter = new StatusVoter();

                for (StatusClassification statusClassification : STATUSES_IN_ORDER) {
                    executor.submit(() -> {
                        try {
                            startLatch.await();
                            Thread.sleep(1);
                            voter.merge(statusClassification);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } finally {
                            doneLatch.countDown();
                        }
                    });
                }

                // single countDown() will unblock
                startLatch.countDown();
                // wait until all threads have completed their operations, then shut down thread pool.
                doneLatch.await();
                executor.shutdown();
                assertThat(executor.awaitTermination(100, TimeUnit.MILLISECONDS)).isTrue();

                // result should always be the most critical status
                if (voter.getClassification() != StatusClassification.INTERNAL_ERROR) {
                    failureCount++;
                }
            }
        }
        assertThat(failureCount).isEqualTo(0);
    }
}
