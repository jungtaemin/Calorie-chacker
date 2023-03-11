package com.tripproject.calorie.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ListCaloriePageResponse {
    /** list contents*/
    private List<ListCalorieResponse> calories;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private int startPage;
    private int tempEndPage;
    private int endPage;
    private int totalCalorie;

    private ListCaloriePageResponse(List<ListCalorieResponse> calories, int pageNumber, int pageSize, int totalPages,int totalCalorie) {
        this.calories = calories;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalCalorie = totalCalorie;
    }

    public static ListCaloriePageResponse of(Page<ListCalorieResponse> listCalorieResponses){

        int totalCalorie = listCalorieResponses.getContent().stream().mapToInt(ListCalorieResponse::getCalorie).sum();

        ListCaloriePageResponse listCaloriePageResponse = new ListCaloriePageResponse(listCalorieResponses.getContent(), listCalorieResponses.getPageable().getPageNumber(),
                listCalorieResponses.getPageable().getPageSize(), listCalorieResponses.getTotalPages(),totalCalorie);

        listCaloriePageResponse.createPageField();
        return listCaloriePageResponse;
    }

    private void createPageField(){
        startPageCreate();
        tempEndPageCreate();
        endPageCreate();
    }
    /** 시작 페이지 로직 */
    private void startPageCreate(){
       this.startPage = (int)Math.floor(pageNumber / pageSize)*pageSize+1;
    }
    /** 끝 페이지 로직 */
    private void tempEndPageCreate(){
        this.tempEndPage = startPage+pageSize -1;
    }
    /** 끝 페이지 로직 */
    private void endPageCreate(){
        this.endPage = tempEndPage > totalPages ? totalPages : tempEndPage;
    }
}
