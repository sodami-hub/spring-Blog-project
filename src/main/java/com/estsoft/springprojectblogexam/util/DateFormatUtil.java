package com.estsoft.springprojectblogexam.util;

import java.time.format.DateTimeFormatter;

// 날짜 형식을 위한 공용 유틸 클래스
public class DateFormatUtil {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
