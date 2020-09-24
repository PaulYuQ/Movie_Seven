<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html >

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title>影七后台系统</title>
  <!-- Font Awesome -->
  <link rel="stylesheet" href="css/all.css">
  <!-- Bootstrap core CSS -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <!-- Your custom styles (optional) -->
  <link href="css/style.min.css" rel="stylesheet">
  <link rel="stylesheet" href="css/pagination.css">
  <link href="css/history.css" rel="stylesheet">




  <style>
    img{

    }
    .table>tbody>tr>td{
      border:0px;
    }
    th{
      font-weight: normal;
    }
    .navbar-collapse{
      background: white;
    }
    .form-control{
      margin-right: 20px;
    }



    .map-container{
      overflow:hidden;
      padding-bottom:56.25%;
      position:relative;
      height:0;
    }
    .map-container iframe{
      left:0;
      top:0;
      height:100%;
      width:100%;
      position:absolute;
    }



  </style>
</head>

<body class="grey lighten-3">



<!--Main Navigation-->
<header>


  <!-- 导航栏 -->
  <nav class="navbar fixed-top navbar-expand-lg navbar-light white scrolling-navbar">
    <div class="container-fluid">

      <!-- Brand -->


      <!-- Collapse -->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <!-- Links -->
      <div class="collapse navbar-collapse" id="navbarSupportedContent">

        <!-- Left -->
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link waves-effect" href="#">主页
              <span class="sr-only">(current)</span>
            </a>
          </li>

          <li class="nav-item">
            <a class="nav-link waves-effect"  onclick="exit()" >退出</a>
          </li>

        </ul>

        <!-- Right -->
        <ul class="navbar-nav nav-flex-icons">
          <li class="nav-item">
            <a href="https://gitee.com/misskunstudy/seven_movie" class="nav-link border border-light rounded waves-effect"
               target="_blank" style="font-weight: bold">
              <img src="img/svg/logo-black.svg" width="30px" height="20px">
              SevenMovie Gitee
            </a>
          </li>

        </ul>

      </div>

    </div>
  </nav>
  <!-- Navbar -->

  <!-- 侧边栏 -->
  <div class="sidebar-fixed position-fixed" >

    <a class="navbar-brand waves-effect" href="#" target="_blank" style="margin-top: 20px;">
      <strong class="blue-text" style="font-size:38px">SevenMovie</strong>
    </a>


    <!--        <img src="img/logo3.png"  width="220px" height="80px" style="margin-top: 20px;margin-bottom: 20px">-->


    <div class="list-group list-group-flush" style="margin-top: 80px">
      <a href="admin.jsp" class="list-group-item  waves-effect list-group-item-action">
        <img  src="img/svg/admin.svg" width="25px" height="25px" style="margin-right: 20px"> 管理员表
      </a>
      <a href="user.jsp" class="list-group-item  list-group-item-action waves-effect">
        <img  src="img/svg/user.svg" width="25px" height="25px" style="margin-right: 20px"> 用户表
      </a>
      <a href="movie.jsp" class="list-group-item active list-group-item-action waves-effect">
        <img  src="img/svg/movie.svg" width="25px" height="25px" style="margin-right: 20px"> 电影表</a>
    </div>

  </div>
  <!-- Sidebar -->

</header>
<!--Main Navigation-->

<!--Main layout-->
<main class="pt-5 mx-lg-5">
  <div class="container-fluid mt-5">

    <!-- 搜索框 -->
    <div class="card mb-4 wow fadeIn">

      <!--Card content-->
      <div class="card-body d-sm-flex justify-content-between">

        <h4 class="mb-2 mb-sm-0 pt-1">
          <a href="#" target="_blank" id="count">电影</a>
        </h4>
        <button class="btn btn-primary"  onclick="getEndpage()">显示全部电影</button>

        <button type="button" class="btn btn-primary" style="margin-left: 30px;" data-toggle="modal" data-target="#myModal">增加电影</button>



        <form class="d-flex justify-content-center">
          <!-- Default input -->
          <input type="text" placeholder="你要查询的电影名" aria-label="Search" class="form-control" id="search">
          <button class="btn btn-primary btn-sm my-0 p" type="button" onclick="getMovie()">
            <img src="img/svg/find.svg" width="25px" height="25px">
          </button>

        </form>

      </div>

    </div>

    <!-- 用户表体 -->
    <div class="card mb-4 wow fadeIn" >


    <div id="movie">
      <ul id="historyBody">

      </ul>

    </div>






      <div id="pages">

      </div>


    </div>
  </div>
</main>


<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">新增电影</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>

      </div>

        <div class="modal-body">

          <div class="form-group">
            <label for="txt_name">电影名</label>
            <input type="text"  class="form-control" id="txt_name"   >
          </div>
          <div class="form-group">
            <label for="txt_type">电影类型</label>
            <input type="text"  class="form-control" id="txt_type">
          </div>
          <div class="form-group">
            <label for="txt_actor">电影主演</label>
            <input type="text" class="form-control" id="txt_actor" >
          </div>
          <div class="form-group">
            <label for="txt_img">电影图片</label>
            <input type="text" class="form-control" id="txt_img" >
          </div>
          <div class="form-group">
            <label for="txt_img1">电影轮播图片</label>
            <input type="text" class="form-control" id="txt_img1" >
          </div>
          <div class="form-group">
            <label for="txt_introduce">电影简介</label>
            <input type="text" class="form-control" id="txt_introduce" >
          </div>
          <div class="form-group">
            <label for="txt_url">电影链接</label>
            <input type="text" class="form-control" id="txt_url" >
          </div>



        </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
        <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal" onclick="addMovie()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true" ></span>提交</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="myModal_1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel1">电影信息</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
      </div>
      <div class="modal-body">
        <div class="form-group">
          <label for="txt_ID1">ID</label>
          <input type="text" disabled="disabled"  class="form-control" id="txt_ID1" >
        </div>

        <div class="form-group">
          <label for="txt_name1">电影名</label>
          <input type="text"  class="form-control" id="txt_name1"   >
        </div>
        <div class="form-group">
          <label for="txt_type1">电影类型</label>
          <input type="text"  class="form-control" id="txt_type1">
        </div>
        <div class="form-group">
          <label for="txt_actor1">电影主演</label>
          <input type="text" class="form-control" id="txt_actor1" >
        </div>
        <div class="form-group">
          <label for="txt_img11">电影图片</label>
          <input type="text" class="form-control" id="txt_img11" >
        </div>
        <div class="form-group">
          <label for="txt_img111">电影轮播图片</label>
          <input type="text" class="form-control" id="txt_img111" >
        </div>
        <div class="form-group">
          <label for="txt_introduce1">电影简介</label>
          <input type="text" class="form-control" id="txt_introduce1" >
        </div>
        <div class="form-group">
          <label for="txt_url1">电影链接</label>
          <input type="text" class="form-control" id="txt_url1" >
        </div>



      </div>
      <div class="modal-footer">
        <button type="button" id="btn_submit1" class="btn btn-primary" data-dismiss="modal" onclick="del()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true" ></span>删除</button>
        <button type="button" id="btn_submit2" class="btn btn-primary" data-dismiss="modal" onclick="update()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true" ></span>提交</button>

      </div>
    </div>
  </div>
</div>



<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->

<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!--  &lt;!&ndash; MDB core JavaScript &ndash;&gt;-->
<script src="js/pagination.js"></script>
<!-- Initializations -->
<script type="text/javascript">
  // Animations initialization

  let pageAmount=15;



  function exit() {
    if (window.confirm("确认退出吗?")) {

      window.location.replace("login.jsp");
    }
  }
  let page;

  function getEndpage() {
    $.post("/movie/getcount.do",{},function (data) {
      console.log(data);
      $("#count").html("电影(共"+data+"条记录)");
      page=new Pagination({
        element: '#pages', // 元素
        type: 1, // 样式类型，可选[1,2]
        pageIndex: 1, // 初始页码
        pageSize: pageAmount, // 每页数量
        pageCount: 9, // 页码数量
        total: data, // 数据总条数
        jumper: false, // 显示输入框跳转
        singlePageHide: false, // 只有一页时不显示分页
        prevText: '上一页', // 上一页文字
        nextText: '下一页', // 下一页文字
        disabled: true, // 是否显示禁用
        currentChange: function(index) {
          // 页码改变时回调
          console.log(index);
          doShow(index);
        }
      });
     doShow(1);

    })
  }







  function doShow(page) {
    $.post("/movie/getMovies.do", {"page": page,"pageAmount":pageAmount}, function (data) {
      if (data == "false") {
        alert("错误");
      }
      console.log(data);
      $("#historyBody").empty();
      $.each($.parseJSON(data), function (i, obj) {
        var movie_id = obj.movie_id;
        $("#historyBody").append(
                "<li class='historyLi' onclick=edit("+movie_id+")>" +
                "<div class='historyDiv'>" +
                "<div class='historyImg'>" +
                "<a target='_parent' href='/movie/gotoIntroduction.do?movie_id=" + movie_id + "'><img src=" + obj.image_url + " onerror='imgError(this)'></a>" +
                "</div>" +
                "<div class='historyInfo'>" +
                "<div class='historyName'>" +
                "<span>" + obj.movie_id +    "、</span>" +
                "<span>【" + obj.type + "】</span>" +
                "<span>" + obj.name + "</span>" +
                "</div>" +
                "<div class='historyActor'>" +
                "<span>" + obj.actor + "</span>" +
                "</div>" +
                "<div class='historyTime'>" +
                "</div>" +
                "</div>" +
                "<div class='historyInput'>" +
                "</div>" +
                "</div>" +
                "</li>"
        );
      }, "json");
    });
  }


  $(document).ready(getEndpage());

  function addMovie() {

    var da={
      "name":$("#txt_name").val(),
      "type":$("#txt_type").val(),
      "actor":$("#txt_actor").val(),
      "image_url":$("#txt_img").val(),
      "banner_url":$("#txt_img1").val(),
      "introduction":$("#txt_introduce").val(),
      "url":$("#txt_url").val()
    };

    $.post("/movie/addmovie.do",da,function (data) {
      console.log("修改结果"+data);
      if(data=='true') {
        alert("添加成功");
        getEndpage();
      }
      else alert("添加失败");
    });

  }

 function edit(id){
   $.post("/movie/getmoviebyid.do",{"id":id},function (data) {
     console.log(data);
     $("#myModal_1").modal();
     $.each(data, function (index, obj){
       $("#txt_ID1").val(id);
       $("#txt_name1").val(obj.name);
       $("#txt_type1").val(obj.type);
       $("#txt_actor1").val(obj.actor);
       $("#txt_img11").val(obj.image_url);
       $("#txt_img111").val(obj.banner_url);
       $("#txt_introduce1").val(obj.introduction);
       $("#txt_url1").val(obj.url);
     });
   },"json");
  }

  function del() {
   let id= $("#txt_ID1").val();
    if (window.confirm("确认删除该行数据吗?")) {
      $.post("/movie/delmoviebyid.do",{"id":id},function (data) {
        if(!data){alert("删除失败");return;}
        getEndpage();
      });
    }

  }

  function update() {
    let id= $("#txt_ID1").val();
    var da={"id":$("#txt_ID1").val(),
      "name":$("#txt_name1").val(),
      "type":$("#txt_type1").val(),
      "actor":$("#txt_actor1").val(),
      "image_url":$("#txt_img11").val(),
      "banner_url":$("#txt_img111").val(),
      "introduction":$("#txt_introduce1").val(),
      "url":$("#txt_url1").val()
    };

    $.post("/movie/updatemoviebyid.do",da,function (data) {
      console.log("修改结果"+data);
      if(data=='true') {
        alert("修改成功");
        getEndpage();
      }
      else alert("修改失败");

    });

  }

  function getMovie() {
    let text=$("#search").val();
    $.post("/movie/searchbyname.do",{"name":text},function (data) {
      console.log(data);

      $("#historyBody").empty();
      $.each($.parseJSON(data), function (i, obj) {
        var movie_id = obj.movie_id;
        $("#historyBody").append(
                "<li class='historyLi' onclick=edit("+movie_id+")>" +
                "<div class='historyDiv'>" +
                "<div class='historyImg'>" +
                "<a target='_parent' href='/movie/gotoIntroduction.do?movie_id=" + movie_id + "'><img src=" + obj.image_url + " onerror='imgError(this)'></a>" +
                "</div>" +
                "<div class='historyInfo'>" +
                "<div class='historyName'>" +
                "<span>" + obj.movie_id +    "、</span>" +
                "<span>【" + obj.type + "】</span>" +
                "<span>" + obj.name + "</span>" +
                "</div>" +
                "<div class='historyActor'>" +
                "<span>" + obj.actor + "</span>" +
                "</div>" +
                "<div class='historyTime'>" +
                "</div>" +
                "</div>" +
                "<div class='historyInput'>" +
                "</div>" +
                "</div>" +
                "</li>"
        );
    },"json");

  });

    page=new Pagination({
      element: '#pages', // 元素
      type: 1, // 样式类型，可选[1,2]
      pageIndex: 1, // 初始页码
      pageSize: pageAmount, // 每页数量
      pageCount: 9, // 页码数量
      total: 1, // 数据总条数
      jumper: false, // 显示输入框跳转
      singlePageHide: false, // 只有一页时不显示分页
      prevText: '上一页', // 上一页文字
      nextText: '下一页', // 下一页文字
      disabled: true, // 是否显示禁用
      currentChange: function(index) {
        // 页码改变时回调
        console.log(index);

      }
    });
  }
</script>




</body>

</html>
