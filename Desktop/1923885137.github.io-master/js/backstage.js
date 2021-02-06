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

  $(function(){
    $(".to-edit-first>a").on("click",function(){
      $(".indication-first").css("display","none");
      $(".indication-first-settings").css("display","block");
    });
    $(".to-edit-second>a").on("click",function(){
      $(".indication-second").css("display","none");
      $(".indication-second-settings").css("display","block");
    });
    $(".edit-button-content-first>button").on("click",function(){
      $(".indication-first").css("display","block");
      $(".indication-first-settings").css("display","none");
    })
    $(".edit-button-content-second>button").on("click",function(){
      $(".indication-second").css("display","block");
      $(".indication-second-settings").css("display","none");
    })
  })
  
}

