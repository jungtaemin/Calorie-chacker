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
  var param ={"viewYear" :viewYear,"viewMonth":viewMonth +1};
  $.ajax({
    url: "/totalCalorie/calendarList",
    dataType: "json",
    type: "GET",
    data: param,
    success: function (data) {
      console.log(data);
      console.log(dates);
            // Dates 정리
      dates.forEach((date, i) => {
        const condition = i >= firstDateIndex && i < lastDateIndex + 1
        ? 'this'
        : 'other';

          var weight;
          var total;
          if(condition == 'this'){
              weight = data[date].weight;
              total = data[date].totalCalories;
          }
            
          console.log(weight)
          if(condition == 'this'){
            if(weight == 0  || total ==null){
              dates[i] = `<div class="date" style="cursor:pointer" onclick="goDate(${date});"><span class="${condition}">${date}</span></div>`;
            }else{
              dates[i] = `<div class="date" style="cursor:pointer" onclick="goDate(${date});"><span class="${condition}">${date}</span><div class="smallFont">${total}kcal/${weight}kg</div></div>`;
            }
            
          
          }else{
            dates[i] = `<div class="date" style="cursor:pointer" ><span class="${condition}">${date}</span></div>`;
          }
        
    })


    // Dates 그리기
    document.querySelector('.dates').innerHTML = dates.join('');
      
    },
  error: function (e) {
  console.log("ERROR : ", e);
  $("#totalCalorieErrorAlert").modal('show');
  $("#errorText").html(e.responseJSON.message);
  }
  });

      
    // 오늘 날짜 그리기
    const today = new Date();
    if (viewMonth === today.getMonth() && viewYear === today.getFullYear()) {
      for (let date of document.querySelectorAll('.this')) {
        if (+date.innerText === today.getDate()) {
          date.classList.add('today');
          break;
        }
      }
    }

}

renderCalendar();


const prevMonth = () => {
  date.setDate(1);
  date.setMonth(date.getMonth() - 1);
  renderCalendar();
}

const nextMonth = () => {
  date.setDate(1);
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
        $.each(data.listCaloriePageResponse.calories, function(index, item){
            $("#myCalories").append('<tr><td onclick="listDetailPage('+item.id+');" style="cursor:pointer;"><div>'+item.name+'</div></td><td onclick="listDetailPage('+item.id+');" style="cursor:pointer;"><div>'+item.calorie+'Kcal</div></td></tr>');
  });
      $("#totalCal").html(data.listCaloriePageResponse.totalCalorie);
      if(data.basicTotalCaloriesResponse.weight == 0){
        $("#inputWeight").html('등록된 몸무게 기록이 없습니다.');
      }else{
        $("#inputWeight").html(data.basicTotalCaloriesResponse.weight);
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



function listDetailPage(id){
  $("#detailCalorieForm")[0].reset();

  var param = {"id": id};
  $.ajax({
type: "GET",
url: '/calorie/detail',
  dataType: "json",
data: param,
success: function (data) {
      console.log(data)
      $("#detailCalorieModal").modal('show');
      $("#detailName").html(data.name)
      $("#updateName").val(data.name);
      $("#updateCalorie").val(data.calorie);
      $("#updateMemo").html(data.memo);
      $("#detailImg").attr("src","/upload/downloadImg/"+data.storeFileName);
      $("#detailCount").html($("#updateMemo").val().length);
},
error: function (e) {
console.log("ERROR : ", e);
}
});
}