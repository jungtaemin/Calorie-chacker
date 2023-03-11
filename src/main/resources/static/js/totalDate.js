let date = new Date();

const renderCalendar = () => {
  const viewYear = date.getFullYear();
  const viewMonth = date.getMonth();
  
  // year-month 채우기
  document.querySelector('.year-month').textContent = `${viewYear}년 ${viewMonth + 1}월`;

  // 지난 달 마지막 Date, 이번 달 마지막 Date
  const prevLast = new Date(viewYear, viewMonth, 0);
  const thisLast = new Date(viewYear, viewMonth + 1, 0);

  const PLDate = prevLast.getDate();
  const PLDay = prevLast.getDay();

  const TLDate = thisLast.getDate();
  const TLDay = thisLast.getDay();

  // Dates 기본 배열들
  const prevDates = [];
  const thisDates = [...Array(TLDate + 1).keys()].slice(1);
  const nextDates = [];

  // prevDates 계산
  if (PLDay !== 6) {
    for (let i = 0; i < PLDay + 1; i++) {
      prevDates.unshift(PLDate - i);
    }
  }

  // nextDates 계산
  for (let i = 1; i < 7 - TLDay; i++) {
    nextDates.push(i)
  }

  // Dates 합치기
  const dates = prevDates.concat(thisDates, nextDates);
  const firstDateIndex = dates.indexOf(1);
  const lastDateIndex = dates.lastIndexOf(TLDate);


  // ajax 
  var param ={"viewYear" :viewYear,"viewMonth":viewMonth};
  $.ajax({
    url: "/totalCalorie/calendarList",
    dataType: "json",
    type: "GET",
    data: param,
    success: function (data) {
      
    },
  error: function (e) {
  console.log("ERROR : ", e);
  }
  });


  // Dates 정리
  dates.forEach((date, i) => {
    const condition = i >= firstDateIndex && i < lastDateIndex + 1
    ? 'this'
    : 'other';

    dates[i] = `<div class="date" style="cursor:pointer" onclick="goDate(${date});"><span class="${condition}">${date}</span></div>`;
  })

  // Dates 그리기
  document.querySelector('.dates').innerHTML = dates.join('');
}

renderCalendar();


const prevMonth = () => {
  date.setMonth(date.getMonth() - 1);
  renderCalendar();
}

const nextMonth = () => {
  date.setMonth(date.getMonth() + 1);
  renderCalendar();
}

const goToday = () => {
  date = new Date();
  renderCalendar();
}


function goDate(date){
var yearMonth = $(".year-month").html();
var newYearMonth= yearMonth.replaceAll('년 ','-').replaceAll('월','');
var newDate = newYearMonth+'-'+date;
console.log(newDate);
  var param ={"date" :newDate};

  $.ajax({
    url: "/calorie/selectList",
    dataType: "json",
    type: "POST",
    data: param,
    success: function (data) {
      $("#myCalories").html('');
      $("#totalDateCalorieModal").modal('show');
      $("#staticBackdropLabel").html('');
        $("#staticBackdropLabel").html(newDate);
        $.each(data.calories, function(index, item){
            $("#myCalories").append('<tr><td><div>'+item.name+'</div></td><td><div>'+item.calorie+'Kcal</div></td></tr>');
  });
      $("#totalCal").html(data.totalCalorie);
    },
  error: function (e) {
  console.log("ERROR : ", e);
  }
  });
}





function reloadList(){
  $.ajax({
  url: "/calorie/list?page=0&size=5&sort=id,desc",
  dataType: "json",
  type: "GET",
  contentType: "application/json",
  success: function (data) {
      $("#myCalories").html('');
      $.each(data.calories, function(index, item){
          $("#myCalories").append('<tr><td><div>'+item.name+'</div></td><td><div>'+item.calorie+'Kcal</div></td><td></td></tr>');
});
     $("#totalCal").html(data.totalCalorie);
  },
error: function (e) {
console.log("ERROR : ", e);
}
});
}

function reloadWeight(){
  $.ajax({
  url: "/totalCalorie/weight",
  dataType: "json",
  type: "GET",
  contentType: "application/json",
  success: function (data) {
      if(data.weight ==0){    
      }else{
          $("#inputWeight").html('<input type="text" style="width: 120px;" maxlength="5" class="normalFont" id="totalWeight" value="'+data.weight+'"></input>');
      }
  },
error: function (e) {
console.log("ERROR : ", e);
}
});
}



function formReset(){
  $("#createCalorieForm")[0].reset();
  $("#count").html('0');
  $("#createCalorie").modal('show');
}

function writing(){
  
  $("#totalCalorieAlert").modal('show');
}



