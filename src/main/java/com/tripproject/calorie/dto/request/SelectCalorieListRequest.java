package com.tripproject.calorie.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.Access;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SelectCalorieListRequest {
    /** 달력 클릭시 파라미터  */
    private String date;


    public LocalDate dateFormatChange(){
        String[] dateSplit = date.split("-");
        int[] dateSplitInteger = Stream.of(dateSplit).mapToInt(Integer::parseInt).toArray();
        return LocalDate.of(dateSplitInteger[0],dateSplitInteger[1],dateSplitInteger[2]);
    }
}
