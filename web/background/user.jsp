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




  <style>
    .form-control{
      margin-right: 20px;
    }
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
              <img src="https://s1.ax1x.com/2020/09/24/0SN3zn.png" width="30px" height="30px">
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




    <div class="list-group list-group-flush" style="margin-top: 80px">
      <a href="admin.jsp" class="list-group-item  waves-effect list-group-item-action">
        <img src="https://s1.ax1x.com/2020/09/24/0St5rV.png" width="25px" height="25px" style="margin-right: 20px"> 管理员表
      </a>
      <a href="user.jsp" class="list-group-item active list-group-item-action waves-effect">
        <img src="https://s1.ax1x.com/2020/09/24/0SYh7D.png" width="25px" height="25px" style="margin-right: 20px"> 用户表
      </a>
      <a href="movie.jsp" class="list-group-item list-group-item-action waves-effect">
        <img src="https://s1.ax1x.com/2020/09/24/0StUCd.png" width="25px" height="25px" style="margin-right: 20px"> 电影表</a>
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
          <a href="#" target="_blank" id="count">用户表</a>
        </h4>
        <button class="btn btn-primary"  onclick="getEndpage()">显示全部用户</button>

               <button type="button" class="btn btn-primary" style="margin-left: 30px;" data-toggle="modal" data-target="#myModal">增加用户</button>



        <form class="d-flex justify-content-center">
          <!-- Default input -->
          <input type="number" placeholder="你要查询的用户ID" aria-label="Search" class="form-control" id="search">
          <button class="btn btn-primary btn-sm my-0 p" type="button" onclick="getAdmin()">
            <img src="https://s1.ax1x.com/2020/09/24/0SJk2q.png" width="25px" height="25px">
          </button>

        </form>

      </div>

    </div>

    <!-- 用户表体 -->
    <div class="card mb-4 wow fadeIn" >


      <table class="table table-hover" id="table1"  >
        <thead class="blue-grey lighten-4">
        <tr style="background: #007bff">
          <th style="color: white">ID</th>
          <th style="color: white">用户名</th>
          <th style="color: white">密码</th>
          <th style="color: white">电话号码</th>
          <th style="color: white">注册时间</th>
         <th style="color: white">操作</th>

        </tr>
        </thead>
        <!-- Table head -->

        <!-- Table body -->
        <tbody id="tbody">
        </tbody>
      </table>






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
        <h4 class="modal-title" id="myModalLabel">新增用户</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>

      </div>
      <div class="modal-body">

        <div class="form-group">
          <label for="txt_name">用户名</label>
          <input type="text" name="txt_departmentname" class="form-control" id="txt_name" >
        </div>
        <div class="form-group">
          <label for="txt_password">密码</label>
          <input type="password" name="txt_parentdepartment" class="form-control" id="txt_password">
        </div>
        <div class="form-group">
          <label for="txt_phone">电话号码</label>
          <input type="number" name="txt_departmentlevel" class="form-control" id="txt_phone" >
        </div>


      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
        <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal" onclick="addAdmin()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true" ></span>提交</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="myModal_1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel1">修改用户</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>

      </div>
      <div class="modal-body">
        <div class="form-group">
          <label for="txt_ID1">ID</label>
          <input type="text" disabled="disabled"  class="form-control" id="txt_ID1" >
        </div>

        <div class="form-group">
          <label for="txt_name1">账户名</label>
          <input type="text"  class="form-control" id="txt_name1" disabled="disabled"  >
        </div>
        <div class="form-group">
          <label for="txt_password1">密码</label>
          <input type="text"  class="form-control" id="txt_password1">
        </div>
        <div class="form-group">
          <label for="txt_phone1">电话号码</label>
          <input type="number" class="form-control" id="txt_phone1" >
        </div>



      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
        <button type="button" id="btn_submit1" class="btn btn-primary" data-dismiss="modal" onclick="updateAdmin()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true" ></span>提交</button>
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

  let pageAmount=7;



  function exit() {
    if (window.confirm("确认退出吗?")) {

      window.location.replace("login.jsp");
    }
  }
  let page;

  function getEndpage() {
    $.post("queryUsers.users",{},function (data) {
      console.log(data);
      $("#count").html("用户(共"+data+"条记录)");
      page=new Pagination({
        element: '#pages', // 元素
        type: 1, // 样式类型，可选[1,2]
        pageIndex: 1, // 初始页码
        pageSize: pageAmount, // 每页数量
        pageCount: 5, // 页码数量
        total: data, // 数据总条数
        jumper: false, // 显示输入框跳转
        singlePageHide: false, // 只有一页时不显示分页
        prevText: '上一页', // 上一页文字
        nextText: '下一页', // 下一页文字
        disabled: true, // 是否显示禁用
        currentChange: function(index) {
          // 页码改变时回调
          console.log(index);
          getPage(index);
        }
      });
      getPage(1);

    })
  }



  function getPage(num) {
    $("#search").val("");
    $.post("queryPage.users",{"page":num,"pageAmount":pageAmount},function (data) {
      show(data)
    },"json")
  }



  function addAdmin() {
    var da={"name":$("#txt_name").val(),"password":$("#txt_password").val(),"phone":$("#txt_phone").val()};
    console.log(da);
    $.post("addUser.users",da,function (data) {
      if(data=='true') {
        alert("添加成功");
        getEndpage();
      }
      else alert("添加失败");
    });
  }

  function updateAdmin() {
    var da={"id":$("#txt_ID1").val(),"name":$("#txt_name1").val(),"password":$("#txt_password1").val(),"phone":$("#txt_phone1").val(),"control":$("#txt_control").val()};
    $.post("updateUser.users",da,function (data) {
      console.log("修改结果"+data);
      if(data=='true') {
        alert("修改成功");
        getEndpage();
      }
      else alert("修改失败");

    });
  }

  function getAdmin() {
    if($("#search").val()==""||$("#search").val()==null)
      return;
    let num=$("#search").val();
    console.log(num);
    let str="";
    $.post("search.users",{"id":num},function (data) {
      console.log(data);
      show(data);
      page=new Pagination({
        element: '#pages', // 元素
        type: 1, // 样式类型，可选[1,2]
        pageIndex: 1, // 初始页码
        pageSize: pageAmount, // 每页数量
        pageCount: 5, // 页码数量
        total: 1, // 数据总条数
        jumper: false, // 显示输入框跳转
        singlePageHide: false, // 只有一页时不显示分页
        prevText: '上一页', // 上一页文字
        nextText: '下一页', // 下一页文字
        disabled: true, // 是否显示禁用
        currentChange: function(index) {
          // 页码改变时回调
          console.log(index);
          getPage(index);
        }
      });
    },"json");}



  function doDel(id) {
    if (window.confirm("确认删除该行数据吗?")) {
      $.post("deleteById.users",{"id":id},function (data) {
        if(!data){alert("删除失败");return;}
        getEndpage();
      });
    }

  }

  function doEdit(id) {
    $.post("search.users",{"id":id},function (data) {
      console.log(data);
      $("#myModal_1").modal();
      $.each(data, function (index, obj){
        $("#txt_ID1").val(id);
        $("#txt_name1").val(obj.name);
        $("#txt_password1").val(obj.password);
        $("#txt_phone1").val(obj.phone);
      });
    },"json");

  }

  function show(data) {
    var str="";
    let s=1;
    $.each(data, function (index, obj) {
      str += "<tr>" +
              "<td>" + obj.user_id + "</td>"
              + "<td>" + obj.name+ "</td>"
              + "<td>" + obj.password + "</td>"
              + "<td>" + obj.phone + "</td>"
              + "<td>" + obj.date + "</td>" ;
      if(s==1){
        str=str+"<td> <a href=\"javascript:void(0);\" onclick='doDel("+ obj.user_id + ",this)'> <img src=\"https://s1.ax1x.com/2020/09/24/0S8f0g.png\" style=\"width: 30px;height: 30px;margin-right: 20px\"></a>" +
                "<a href=\"javascript:void(0);\" value='编辑' " +
                "onclick='doEdit("
                + obj.user_id +",this)'> <img src=\"https://s1.ax1x.com/2020/09/24/0SGqPA.png\" style=\"width: 30px;height: 30px;\"></a>" +
                "</td>"
        ;
      }

      str=str+ "</tr>";

    });
    $("#tbody").empty();
    $("#tbody").append(str);
  }



  $(document).ready(getEndpage());

</script>




</body>

</html>
