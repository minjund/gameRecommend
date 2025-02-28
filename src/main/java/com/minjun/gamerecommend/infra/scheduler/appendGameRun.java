package com.minjun.gamerecommend.infra.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class appendGameRun implements ApplicationRunner {

    private final Scheduler scheduler;

    @Autowired
    public appendGameRun(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 서버 시작 시 반복 실행될 스케줄 작업을 등록합니다
        scheduleAppendGameJob();

        System.out.println("==========================================================");
        System.out.println("Quartz 스케줄러가 초기화되었습니다. 게임 추가 작업이 스케줄링 되었습니다.");
        System.out.println("==========================================================");
    }

    private void scheduleAppendGameJob() throws SchedulerException {
        String jobId = "appendGameJob";
        String triggerId = "appendGameTrigger";
        String groupId = "gameGroup";

        // JobDetail 생성
        JobDetail jobDetail = JobBuilder.newJob(appendGameJob.class)
                .withIdentity(jobId, groupId)
                .build();

        // 매일 자정(00:00)에 실행되는 트리거
        Trigger dailyTrigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerId, groupId)
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(0, 0)) // 매일 00:00
                .build();

        // 또는 1시간마다 실행하는 트리거
        Trigger hourlyTrigger = TriggerBuilder.newTrigger()
                .withIdentity("hourlyTrigger", groupId)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .startNow()
                .build();

        // 이미 등록된 작업이 있다면 삭제 후 다시 등록
        if (scheduler.checkExists(jobDetail.getKey())) {
            scheduler.deleteJob(jobDetail.getKey());
        }

        // 스케줄러에 작업 등록
        scheduler.scheduleJob(jobDetail, hourlyTrigger); // 1시간마다 실행

        // 만약 서버 시작 후 특정 시간에 한 번만 실행하려면 아래와 같이 설정
        scheduleOneTimeJob();
    }

    private void scheduleOneTimeJob() throws SchedulerException {
        // 특정 시간에 한 번만 실행되는 작업
        String jobId = "oneTimeJob_" + System.currentTimeMillis();

        // 현재 시간으로부터 1시간 후에 실행되도록 설정
        LocalDateTime executeTime = LocalDateTime.now().plusHours(1);

        JobDetail jobDetail = JobBuilder.newJob(appendGameJob.class)
                .withIdentity(jobId, "oneTimeGroup")
                .usingJobData("postId", "initialGame") // JobDataMap에 데이터 저장
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger_" + jobId, "oneTimeGroup")
                .startAt(Date.from(executeTime.atZone(ZoneId.systemDefault()).toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        System.out.println("한 번만 실행되는 작업이 등록되었습니다. 실행 예정 시간: " + executeTime);
    }
}