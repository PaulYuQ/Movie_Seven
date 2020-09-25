//搜索框事件
function doSearch() {
    console.log("doSearch()-----");
    console.log($("#search-content").val());
    var name = $("#search-content").val();
    console.log(name);
    // $.post("gotoSearch.movie",{"name":name})
    $("#searchBtn").attr("href", "gotoSearch.movie?name=" + name);
}


// function myBtn() {
//     // $("#myInfo").css("display","block");
//     $("#myInfo").show();
// }


function myBtn(){
    $("#myBtn").on("click", function(e){
        console.log("dianji");
        if ($("#myInfo").css("display")=="none"){
            $("#myInfo").show();
            $(document).one("click", function(){
                $("#myInfo").hide();
            });
            e.stopPropagation();
        }else {
            $("#myInfo").hide();
        }

    });
    $("#myInfo").on("click", function(e){
        e.stopPropagation();
    });
}

