package com.tripproject.calorie.service;

import com.tripproject.calorie.domain.Calorie;
import com.tripproject.calorie.dto.request.CreateCalorieRequest;
import com.tripproject.calorie.dto.request.SelectCalorieListRequest;
import com.tripproject.calorie.dto.response.CalorieResponse;
import com.tripproject.calorie.dto.response.ListCaloriePageResponse;
import com.tripproject.calorie.dto.response.ListCalorieResponse;
import com.tripproject.calorie.repository.CalorieRepository;
import com.tripproject.totalCalories.domain.TotalCalories;
import com.tripproject.totalCalories.repository.TotalCalorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CalorieService {

    private final CalorieRepository calorieRepository;
    private final TotalCalorieRepository totalCalorieRepository;

    @Transactional
    public CreateCalorieRequest createTotalCalorieJoin(final Long userId,final CreateCalorieRequest createCalorieRequest){

        Optional<TotalCalories> resultDate = totalCalorieRepository.findByDate(userId,LocalDate.now());
        resultDate.ifPresent(date ->createCalorieRequest.setTotalCalories(date));
        if(resultDate.isEmpty()){
            TotalCalories newTotalCalorie = totalCalorieRepository.save(TotalCalories.builder().build());
            createCalorieRequest.setTotalCalories(newTotalCalorie);
        }
        return createCalorieRequest;
    }

    @Transactional
    public void createCaloriePost(final Calorie calorie){
        calorieRepository.save(calorie);
    }

    @Transactional
    public void deleteCaloriePost(final Long id){
        calorieRepository.deleteById(id);
    }

    public ListCaloriePageResponse calorieList(final Long id,final Pageable pageable){
        Page<ListCalorieResponse> listCalorieResponses = calorieRepository.findByUserId(id,LocalDate.now(), pageable)
                .map(calorie -> new ListCalorieResponse(calorie.getId(), calorie.getName(), calorie.getCalorie(), calorie.getMemo()));
        return ListCaloriePageResponse.of(listCalorieResponses);
    }

    public ListCaloriePageResponse selectCalorieList(final Long id,final SelectCalorieListRequest selectCalorieListRequest){
        Page<ListCalorieResponse> listCalorieResponses = calorieRepository.findByUserId(id,selectCalorieListRequest.dateFormatChange(), PageRequest.of(0,5,Sort.by("id").descending()))
                .map(calorie -> new ListCalorieResponse(calorie.getId(), calorie.getName(), calorie.getCalorie(), calorie.getMemo()));
        return ListCaloriePageResponse.of(listCalorieResponses);
    }

    public CalorieResponse calorieDetail(final Long id){
        Optional<Calorie> detailByUserId = calorieRepository.findById(id);

        Calorie calorie = detailByUserId.orElseThrow(() -> new IllegalStateException("칼로리 정보가 없습니다."));
        return new CalorieResponse(calorie);
    }

}
