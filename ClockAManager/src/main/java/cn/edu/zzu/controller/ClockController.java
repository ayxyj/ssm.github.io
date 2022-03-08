package cn.edu.zzu.controller;

import cn.edu.zzu.service.IClockService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.plaf.ListUI;

@Controller
@RequestMapping("/quartz")
public class ClockController {
    @Autowired
    IClockService iClockService;

    @GetMapping("/sendMail")
    public String sendMail(Model model) {
        try {
            iClockService.sendMail();
            model.addAttribute("startSendMail", "发送邮件任务开启");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "user/allUser";
    }

    @GetMapping("/clockInJob")
    public String clockInJob(Model model) {
        try {
            iClockService.clockInJob();
            model.addAttribute("startClockInJob", "打卡任务开启");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "user/allUser";
    }

    @GetMapping("/resettingJob")
    public String resettingJob(Model model) {
        try {
            iClockService.resettingJob();
            model.addAttribute("startResettingJob", "重置任务开启");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "user/allUser";
    }

    @GetMapping("/shutdownRendMailScheduler")
    public String shutdownRendMailScheduler(Model model) {
        iClockService.shutdownRendMailScheduler();
        model.addAttribute("endSendMail", "发送邮件任务关闭");
        return "user/allUser";
    }

    @GetMapping("/shutdownResettingScheduler")
    public String shutdownResettingScheduler(Model model) {
        iClockService.shutdownResettingScheduler();
        model.addAttribute("endResettingScheduler", "重置任务关闭");
        return "user/allUser";
    }

    @GetMapping("/shutdownClockInScheduler")
    public String shutdownClockInScheduler(Model model) {
        iClockService.shutdownClockInScheduler();
        model.addAttribute("endClockInScheduler", "打卡任务关闭");
        return "user/allUser";
    }
}
