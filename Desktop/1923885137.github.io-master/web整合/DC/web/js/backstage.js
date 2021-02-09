window.onload =function(){

  $(function(){
   $(".btn_nav").on("click",function(){
     $(this).siblings(".smenu").toggleClass("maxmore");
     $(this).parent().siblings(".item").find(".smenu").removeClass("maxmore");
   });
 })
 

var d = new DateJs({
    inputEl: '#inputdate',
	el: '#date'
            })
$("#city").on("click",function (e) {
    SelCity(this,e);
    console.log("inout",$(this).val(),new Date())
});

laydate.render({
    elem: '#year-select'
    ,type: 'year'
    ,range: true
  });
}

// //getBasicInfo
// $("#basic_submit").click(function () {
//     let user_id=$("#id").val();
//     let user_name=$("#name").val();
//     let user_date=$("#date").val();
//     let user_city=$("#city").val();
//     let user_introduction=$("#introduction").val();
//     let sex_male=$("#inlineRadio1");
//     let sex_female=$("#inlineRadio2");
//     let user_sex=sex_male.checked===true?sex_male.value:sex_female.value;
//     alert(user_sex);
// })