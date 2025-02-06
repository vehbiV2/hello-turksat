package com.vehbiozcan.hello_turksat.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.scheduling.annotation.EnableAsync;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

import java.util.concurrent.Executor;


@Configuration
@EnableAsync
public class AsyncConfig {

    /*@Bean(name = "FileUploadTaskExecutor")
    public Executor taskExecutor() {
       *//* ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //havuzda bekleyecek minimum thread sayısı
        executor.setCorePoolSize(25);
        // Max kaç thread işlenebileceği
        executor.setMaxPoolSize(100);
        // Kuyruğa alıncak thread sayısı
        executor.setQueueCapacity(500);
        // Thread namelere eklenecek prefixler
        executor.setThreadNamePrefix("FileUploadAsync-");
        executor.initialize();
        return new DelegatingSecurityContextAsyncTaskExecutor(executor);*//*

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("FileUploadAsync-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(30);
        executor.initialize();
        return executor;
    }*/

    @Bean(name = "FileUploadTaskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(100);  // Başlangıçta 100 thread
        executor.setMaxPoolSize(200);   // Maksimum 200 thread
        executor.setQueueCapacity(1500); // Kuyruk kapasitesi 1000
        executor.setThreadNamePrefix("FileUpload-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }


}
