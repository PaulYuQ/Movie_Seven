<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>收藏页面</title>
    <link href="static/collection/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="static/collection/css/pagination.css">
    <script src="static/collection/js/jquery-3.2.1.js"></script>
    <script src="static/collection/js/bootstrap.min.js"></script>
    <script src="static/collection/js/pagination.js"></script>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>
    <script>

        /*<div class="span2 divbg">
            <div class="hero-unit well">
                <input type="hidden" class="hinput" value="collection.id"/>
                <a href=""><img src="https://kuyun.tv/upload/vod/20200825-1/0da879accd61955c1210a732fe311aa3.jpg" style="width: 100%;height:222px"></a>
                <a href="" style="text-decoration:none;"><p>潜行天下第一季</p></a>
                <p>收藏于：2020-09-25 16:18:55</p>
                <p><a class="btn btn-primary btn-large" href="#" onclick="doDelete(collection.id, this)">移出收藏夹</a></p>
            </div>
        </div>*/

        let page;

        $.post("/getCollectionCountByUserId.collection", function (data) {
            console.log(data);
            new Pagination({
                element: '#historyPage', // 元素
                type: 1, // 样式类型，可选[1,2]
                pageIndex: 1, // 初始页码
                pageSize: 6, // 每页数量
                pageCount: 5, // 页码数量
                total: data, // 数据总条数
                jumper: false, // 显示输入框跳转
                singlePageHide: false, // 只有一页时不显示分页
                prevText: '上一页', // 上一页文字
                nextText: '下一页', // 下一页文字
                disabled: true, // 是否显示禁用
                currentChange: function (index) {
                    // 页码改变时回调
                    console.log(index);
                    doShow(index);
                }
            });
            doShow(1);
        });

        function doShow(page) {
            $.ajax({
                type: "post",
                async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                url: "/queryAll.collection",    //请求发送到/collection/queryAll.movie处
                data: {'page': page},
                dataType: "json",        //返回数据形式为json
                success: function (data) {
                    console.log(data);
                    console.log(data.length);
                    let str = "";
                    $.each(data, function (index, obj) {
                        console.log(obj.name);
                        str += "<div class=\"span2 divbg\">" + "<div class=\"hero-unit well chbg\">"
                            + "<input type=\"hidden\" class=\"hinput\" value=\"" + obj.collection_id + "\"/>"
                            + "<a target='_parent' href='gotoIntroduction.movie?movie_id=" + obj.movie_id + "'><img src=\"" + obj.image_url + "\" style=\"width: 100%;height:222px\"></a>"
                            + "<a target='_parent' href='gotoIntroduction.movie?movie_id=" + obj.movie_id + "' style=\"text-decoration:none;\"><p><label>" + obj.name + "</label></p></a>"
                            + "<p style='color: #0e90d2;font-size: small'>收藏于：<br/>" + obj.date + "</p>"
                            + "<p class='pword'><a class=\"btn btn-primary btn-large button10\" href=\"#\" onclick=\'doDelete(" + obj.collection_id + ",this)'><label>移出</label></a></p>"
                            + "</div>" + "</div>";
                    });
                    $("#list").empty();
                    $("#list").append(str);
                },
                error: function (XMLHttpRequest, textStatus, errorMsg) {
                    //请求失败时执行该函数
                    alert("错误码:" + XMLHttpRequest.status);
                    alert("错误状态:" + XMLHttpRequest.readyState);
                    alert("数据请求数据失败!" + errorMsg);
                }
            });
        }//函数结束

        function doDelete(id) {
            $.ajax({
                type: "post",
                async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                url: "/delete.collection",    //请求发送到/collection/delete.movie处
                data: {'collectionId': id},
                dataType: "json",        //返回数据形式为json
                success: function (data) {
                    console.log(data);
                    let res = data.res;
                    if (res > 0) {
                        doShow(1);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorMsg) {
                    //请求失败时执行该函数
                    alert("错误码:" + XMLHttpRequest.status);
                    alert("错误状态:" + XMLHttpRequest.readyState);
                    alert("数据请求数据失败!" + errorMsg);
                }
            });
        }

        function oper() {
            let la = $(".hinput").attr("type");
            let id1 = document.getElementById("button1");
            let id2 = document.getElementById("button2");
            let id3 = document.getElementById("button3");
            if (la == "hidden") {
                $(".hinput").attr('type', 'checkbox');
                if (id1.style.display == "none") {
                    id1.style.display = "inline-block";
                }
                if (id2.style.display == "none") {
                    id2.style.display = "inline-block";
                }
                if (id3.style.display == "none") {
                    id3.style.display = "inline-block";
                }
            } else {
                $(".hinput").attr('type', 'hidden');
                if (id1.style.display == "inline-block") {
                    id1.style.display = "none";
                }
                if (id2.style.display == "inline-block") {
                    id2.style.display = "none";
                }
                if (id3.style.display == "inline-block") {
                    id3.style.display = "none";
                }
            }
        }

        function doChooseAll() {
            $(".hinput").prop("checked", true);
        }

        function doChooseReverse() {
            $(".hinput").each(function () {
                if ($(this).prop("checked") == true) {
                    $(this).prop("checked", false);
                } else {
                    $(this).prop("checked", true);
                }
            })
        }

        function batch_del() {
            $(".hinput").each(function () {
                if ($(this).prop("checked") == true) {
                    doDelete($(this).val());
                }
            });
        }

        function doQuerylike() {
            let id4 = document.getElementById("button4");
            /*if(id4.style.display == "none") {
                id4.style.display="inline-block";
            } else {
                id4.style.display="none";
            }*/
            let keyword = $("#keyword").val();
            if (keyword == "") {
                id4.style.display = "none";
                bootbox.alert("关键字 <b>不能为空</b>");
                doShow(1);
            } else {
                id4.style.display = "inline-block";

                let page;

                $.post("/getCollectionCountByKeyWord.collection",{'keyword':keyword} ,function (data) {
                    console.log(data);
                    new Pagination({
                        element: '#historyPage', // 元素
                        type: 1, // 样式类型，可选[1,2]
                        pageIndex: 1, // 初始页码
                        pageSize: 6, // 每页数量
                        pageCount: 5, // 页码数量
                        total: data, // 数据总条数
                        jumper: false, // 显示输入框跳转
                        singlePageHide: false, // 只有一页时不显示分页
                        prevText: '上一页', // 上一页文字
                        nextText: '下一页', // 下一页文字
                        disabled: true, // 是否显示禁用
                        currentChange: function (index) {
                            // 页码改变时回调
                            console.log(index);
                            doShow(index);
                        }
                    });
                    doShow(1);
                });

                function doShow(page) {
                    $.ajax({
                        type: "post",
                        async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                        url: "/queryAlllike.collection",    //请求发送到/collection/queryAll.movie处
                        data: {'page': page,'keyword':keyword},
                        dataType: "json",        //返回数据形式为json
                        success: function (data) {
                            console.log(data);
                            console.log(data.length);
                            let str = "";
                            $.each(data, function (index, obj) {
                                console.log(obj.name);
                                str += "<div class=\"span2 divbg\">" + "<div class=\"hero-unit well chbg\">"
                                    + "<input type=\"hidden\" class=\"hinput\" value=\"" + obj.collection_id + "\"/>"
                                    + "<a target='_parent' href='gotoIntroduction.movie?movie_id=" + obj.movie_id + "'><img src=\"" + obj.image_url + "\" style=\"width: 100%;height:222px\"></a>"
                                    + "<a target='_parent' href='gotoIntroduction.movie?movie_id=" + obj.movie_id + "' style=\"text-decoration:none;\"><p><label>" + obj.name + "</label></p></a>"
                                    + "<p style='color: #0e90d2;font-size: small'>收藏于：<br/>" + obj.date + "</p>"
                                    + "<p class='pword'><a class=\"btn btn-primary btn-large button10\" href=\"#\" onclick=\'doDelete(" + obj.collection_id + ",this)'><label>移出</label></a></p>"
                                    + "</div>" + "</div>";
                            });
                            $("#list").empty();
                            $("#list").append(str);
                        },
                        error: function (XMLHttpRequest, textStatus, errorMsg) {
                            //请求失败时执行该函数
                            alert("错误码:" + XMLHttpRequest.status);
                            alert("错误状态:" + XMLHttpRequest.readyState);
                            alert("数据请求数据失败!" + errorMsg);
                        }
                    });
                }//函数结束
            }
        }

        function returnindex() {
            document.getElementById("button4").style.display = "none";
            doShow(1);
        }


    </script>
    <style type="text/css">
        /*背景模糊*/
        /*.bg {
            width: 100%;
            height: 100%;
            position: relative;
            background: url("static/img/33.png") no-repeat fixed;
            background-size: cover;
            box-sizing: border-box;
            z-index: -1;
        }*/
        .divbg {
            background: rgba(0, 0, 0, 0);
            height: 310px;
        }

        .chbg {
            background: rgba(0, 0, 0, 0);
            padding: 40px;
            height: 413px;
            width: 160px;
            border: 0px;
            line-height: 17px;
        }

        #car {
            background: rgba(0, 0, 0, 0);
        }

        .button10 {
            padding: 4px 20px;
            border-radius: 16px;
        }

        #keyword {
            border: .0625rem solid #eee;
            border-radius: 1.875rem;
            width: 200px;
            height: 30px;
            background-color: #f7f7f7 !important;
            margin-left: 707px;
        }

        #list {
            margin-left: -21px;
        }

        body::-webkit-scrollbar {
            display: none;
        }
    </style>
</head>
<body class="bg">
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <div class="carousel slide" id="carousel-956640">
                <div class="carousel-inner">
                    <div class="item active">
                        <img alt="" src="https://s1.ax1x.com/2020/09/24/0p8ev6.jpg" style="width: 100%"/>
                        <div class="carousel-caption" id="car">
                            <p style="font-weight: bold;font-size: 20px">
                                ${user.name}
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <p class="form-inline" style="font-size: 30px;">我的收藏</p>
            <input class="input-medium search-query" id="keyword" type="text" value=""/>
            <button class="btn" onclick="doQuerylike()">查找</button>
            <button style="display: none" id="button4" class="btn" onclick="returnindex()">返回</button>
            <button class="btn" onclick="oper()">批量操作</button>
            <button style="display: none" id="button1" class="btn" onClick="batch_del()">批量删除</button>
            <button style="display: none" id="button2" class="btn" onClick="doChooseAll()">全选</button>
            <button style="display: none" id="button3" class="btn" onClick="doChooseReverse()">反选</button>
            <p class="form-inline"></p>
        </div>
    </div>
    <div class="row-fluid" id="list">

        <!--<div class="span4">
            <div class="hero-unit well">
                <h1>
                    Hello, world!
                </h1>
                <p>
                    这是一个可视化布局模板, 你可以点击模板里的文字进行修改, 也可以通过点击弹出的编辑框进行富文本修改. 拖动区块能实现排序.
                </p>
                <p>
                    <a class="btn btn-primary btn-large" href="#">参看更多 »</a>
                </p>
            </div>
        </div>
        <div class="span4">
            <div class="hero-unit well">
                <h1>
                    Hello, world!
                </h1>
                <p>
                    这是一个可视化布局模板, 你可以点击模板里的文字进行修改, 也可以通过点击弹出的编辑框进行富文本修改. 拖动区块能实现排序.
                </p>
                <p>
                    <a class="btn btn-primary btn-large" href="#">参看更多 »</a>
                </p>
            </div>
        </div>
        <div class="span4">
            <div class="hero-unit well">
                <h1>
                    Hello, world!
                </h1>
                <p>
                    这是一个可视化布局模板, 你可以点击模板里的文字进行修改, 也可以通过点击弹出的编辑框进行富文本修改. 拖动区块能实现排序.
                </p>
                <p>
                    <a class="btn btn-primary btn-large" href="#">参看更多 »</a>
                </p>
            </div>
        </div>-->
    </div>
    <div class="row-fluid">
        <div class="span12">
            <div id="historyPage">

            </div>
        </div>
    </div>
</div>
</body>
</html>