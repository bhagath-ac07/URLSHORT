package com.url.shortner.core;

import com.url.shortner.repository.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class CleanupRecord {

    @Autowired
    URLRepository urlRepository;
    @Scheduled(fixedDelay = 1 * 60 * 1000, initialDelay = 1 * 60 * 1000)
    public void performTweetCleanup(){
        //calculate date
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.MONTH, -1);
        Date previousMonth = cal.getTime();

        //call the method
        urlRepository.deleteByCreatedBefore(previousMonth);
    }

}
