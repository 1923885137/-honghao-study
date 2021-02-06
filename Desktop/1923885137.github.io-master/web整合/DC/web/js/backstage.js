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

