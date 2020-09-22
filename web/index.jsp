<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta name="renderer" content="webkit"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta http-equiv="Cache-Control" content="no-transform"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-title" content="Movie SEVEN,影柒，中国最牛逼的电影网站！"/>
    <meta name="viewport" content="initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link rel="alternate" type="application/rss+xml" title="RSS 2.0" href="/rss/index.xml"/>
    <!--[if lt IE 9]>
    <script type="text/javascript">window.location.href = '/map/index.jsp';</script><![endif]-->
    <link rel="stylesheet" href="static/banner/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="static/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="static/css/blues.css" id="fed-colo-color"/>
    <title>Movie SEVEN,影柒，做中国最牛逼的电影网站</title>

    <script src="static/js/jquery-3.2.1.js"></script>
    <script>

        $(function () {
            load();
            myBtn();
            $("#home-a").css("color", "#2196F3");
        });

        function load() {
            $.get("/movie/initAllData.do", function (data) {
                var dataJson = $.parseJSON(data);
                var movieSum = dataJson.movieSum;
                $("#movie-sum").text(movieSum);
                console.log(movieSum);
                var limitMovies = dataJson.limitMovies;
                var actionMovies = dataJson.actionMovies;
                var comedy = dataJson.comedy;
                var loveMovies = dataJson.loveMovies;
                var storyMovies = dataJson.storyMovies;
                var horrorMovies = dataJson.horrorMovies;
                var scienceMovies = dataJson.scienceMovies;
                var documentaryMovies = dataJson.documentaryMovies;
                var str = "";
                var str2 = "";
                var str3 = "";
                var str4 = "";
                var str5 = "";
                var str6 = "";
                var str7 = "";
                var str8 = "";
                $.each(limitMovies, function (index, movie) {

                    str += "<li class=\"fed-list-item fed-padding fed-col-xs4 fed-col-sm3 fed-col-md2\">\n" +
                        // "                    <a class=\"fed-list-pics fed-lazy fed-part-2by3\" href=\"" + movie.url + "\"\n" +
                        "                    <a class=\"fed-list-pics fed-lazy fed-part-2by3\" href=\"/movie/gotoIntroduction.do?movie_id=" + movie.movie_id + "\"\n" +
                        "                       data-original=\"" + movie.image_url + "\"" +
                        "                    style=\"background-image: url(" + movie.image_url + ")\">\n" +
                        "                        <span class=\"fed-list-play fed-hide-xs\"></span>\n" +
                        "                    </a>\n" +
                        "                    <a class=\"fed-list-title fed-font-xiv fed-text-center fed-text-sm-left fed-visible fed-part-eone\"\n" +
                        "                       href=\"/movie/gotoIntroduction.do?movie_id=" + movie.movie_id + "\"\n" + "\">" + movie.name + "</a>\n" +
                        "                    <span class=\"fed-list-desc fed-font-xii fed-visible fed-part-eone fed-text-muted fed-hide-xs fed-show-sm-block\">\n" +
                        movie.actor + "</span>\n" +
                        "                </li>";
                })
                $("#first-line-info").empty();
                $("#first-line-info").append(str);
                $.each(actionMovies, function (index, movie) {
                    str2 += "<li class=\"fed-list-item fed-padding fed-col-xs4 fed-col-sm3 fed-col-md2\">\n" +
                        "                    <a class=\"fed-list-pics fed-lazy fed-part-2by3\" href=\"/movie/gotoIntroduction.do?movie_id=" + movie.movie_id + "\"\n" +
                        "                       data-original=\"" + movie.image_url + "\"" +
                        "                    style=\"background-image: url(" + movie.image_url + ")\">\n" +
                        "                        <span class=\"fed-list-play fed-hide-xs\"></span>\n" +
                        "                    </a>\n" +
                        "                    <a class=\"fed-list-title fed-font-xiv fed-text-center fed-text-sm-left fed-visible fed-part-eone\"\n" +
                        "                       href=\"/movie/gotoIntroduction.do?movie_id=" + movie.movie_id + "\"\n" + "\">" + movie.name + "</a>\n" +
                        "                    <span class=\"fed-list-desc fed-font-xii fed-visible fed-part-eone fed-text-muted fed-hide-xs fed-show-sm-block\">\n" +
                        movie.actor + "</span>\n" +
                        "                </li>";
                })
                $("#second-line-info").empty();
                $("#second-line-info").append(str2);
                $.each(comedy, function (index, movie) {
                    str3 += "<li class=\"fed-list-item fed-padding fed-col-xs4 fed-col-sm3 fed-col-md2\">\n" +
                        "                    <a class=\"fed-list-pics fed-lazy fed-part-2by3\" href=\"/movie/gotoIntroduction.do?movie_id=" + movie.movie_id + "\"\n" +
                        "                       data-original=\"" + movie.image_url + "\"" +
                        "                    style=\"background-image: url(" + movie.image_url + ")\">\n" +
                        "                        <span class=\"fed-list-play fed-hide-xs\"></span>\n" +
                        "                    </a>\n" +
                        "                    <a class=\"fed-list-title fed-font-xiv fed-text-center fed-text-sm-left fed-visible fed-part-eone\"\n" +
                        "                       href=\"/movie/gotoIntroduction.do?movie_id=" + movie.movie_id + "\"\n" + "\">" + movie.name + "</a>\n" +
                        "                    <span class=\"fed-list-desc fed-font-xii fed-visible fed-part-eone fed-text-muted fed-hide-xs fed-show-sm-block\">\n" +
                        movie.actor + "</span>\n" +
                        "                </li>";
                })
                $("#third-line-info").empty();
                $("#third-line-info").append(str3);
                $.each(loveMovies, function (index, movie) {
                    str4 += "<li class=\"fed-list-item fed-padding fed-col-xs4 fed-col-sm3 fed-col-md2\">\n" +
                        "                    <a class=\"fed-list-pics fed-lazy fed-part-2by3\" href=\"/movie/gotoIntroduction.do?movie_id=" + movie.movie_id + "\"\n" +
                        "                       data-original=\"" + movie.image_url + "\"" +
                        "                    style=\"background-image: url(" + movie.image_url + ")\">\n" +
                        "                        <span class=\"fed-list-play fed-hide-xs\"></span>\n" +
                        "                    </a>\n" +
                        "                    <a class=\"fed-list-title fed-font-xiv fed-text-center fed-text-sm-left fed-visible fed-part-eone\"\n" +
                        "                       href=\"/movie/gotoIntroduction.do?movie_id=" + movie.movie_id + "\"\n" + "\">" + movie.name + "</a>\n" +
                        "                    <span class=\"fed-list-desc fed-font-xii fed-visible fed-part-eone fed-text-muted fed-hide-xs fed-show-sm-block\">\n" +
                        movie.actor + "</span>\n" +
                        "                </li>";
                })
                $("#forth-line-info").empty();
                $("#forth-line-info").append(str4);
                $.each(storyMovies, function (index, movie) {
                    str5 += "<li class=\"fed-list-item fed-padding fed-col-xs4 fed-col-sm3 fed-col-md2\">\n" +
                        "                    <a class=\"fed-list-pics fed-lazy fed-part-2by3\" href=\"/movie/gotoIntroduction.do?movie_id=" + movie.movie_id + "\"\n" +
                        "                       data-original=\"" + movie.image_url + "\"" +
                        "                    style=\"background-image: url(" + movie.image_url + ")\">\n" +
                        "                        <span class=\"fed-list-play fed-hide-xs\"></span>\n" +
                        "                    </a>\n" +
                        "                    <a class=\"fed-list-title fed-font-xiv fed-text-center fed-text-sm-left fed-visible fed-part-eone\"\n" +
                        "                       href=\"/movie/gotoIntroduction.do?movie_id=" + movie.movie_id + "\"\n" + "\">" + movie.name + "</a>\n" +
                        "                    <span class=\"fed-list-desc fed-font-xii fed-visible fed-part-eone fed-text-muted fed-hide-xs fed-show-sm-block\">\n" +
                        movie.actor + "</span>\n" +
                        "                </li>";
                })
                $("#fifth-line-info").empty();
                $("#fifth-line-info").append(str5);
                $.each(horrorMovies, function (index, movie) {
                    str6 += "<li class=\"fed-list-item fed-padding fed-col-xs4 fed-col-sm3 fed-col-md2\">\n" +
                        "                    <a class=\"fed-list-pics fed-lazy fed-part-2by3\" href=\"/movie/gotoIntroduction.do?movie_id=" + movie.movie_id + "\"\n" +
                        "                       data-original=\"" + movie.image_url + "\"" +
                        "                    style=\"background-image: url(" + movie.image_url + ")\">\n" +
                        "                        <span class=\"fed-list-play fed-hide-xs\"></span>\n" +
                        "                    </a>\n" +
                        "                    <a class=\"fed-list-title fed-font-xiv fed-text-center fed-text-sm-left fed-visible fed-part-eone\"\n" +
                        "                       href=\"/movie/gotoIntroduction.do?movie_id=" + movie.movie_id + "\"\n" + "\">" + movie.name + "</a>\n" +
                        "                    <span class=\"fed-list-desc fed-font-xii fed-visible fed-part-eone fed-text-muted fed-hide-xs fed-show-sm-block\">\n" +
                        movie.actor + "</span>\n" +
                        "                </li>";
                })
                $("#sixth-line-info").empty();
                $("#sixth-line-info").append(str6);
                $.each(scienceMovies, function (index, movie) {
                    str7 += "<li class=\"fed-list-item fed-padding fed-col-xs4 fed-col-sm3 fed-col-md2\">\n" +
                        "                    <a class=\"fed-list-pics fed-lazy fed-part-2by3\" href=\"/movie/gotoIntroduction.do?movie_id=" + movie.movie_id + "\"\n" +
                        "                       data-original=\"" + movie.image_url + "\"" +
                        "                    style=\"background-image: url(" + movie.image_url + ")\">\n" +
                        "                        <span class=\"fed-list-play fed-hide-xs\"></span>\n" +
                        "                    </a>\n" +
                        "                    <a class=\"fed-list-title fed-font-xiv fed-text-center fed-text-sm-left fed-visible fed-part-eone\"\n" +
                        "                       href=\"/movie/gotoIntroduction.do?movie_id=" + movie.movie_id + "\"\n" + "\">" + movie.name + "</a>\n" +
                        "                    <span class=\"fed-list-desc fed-font-xii fed-visible fed-part-eone fed-text-muted fed-hide-xs fed-show-sm-block\">\n" +
                        movie.actor + "</span>\n" +
                        "                </li>";
                })
                $("#seventh-line-info").empty();
                $("#seventh-line-info").append(str7);
                $.each(documentaryMovies, function (index, movie) {
                    str8 += "<li class=\"fed-list-item fed-padding fed-col-xs4 fed-col-sm3 fed-col-md2\">\n" +
                        "                    <a class=\"fed-list-pics fed-lazy fed-part-2by3\" href=\"/movie/gotoIntroduction.do?movie_id=" + movie.movie_id + "\"\n" +
                        "                       data-original=\"" + movie.image_url + "\"" +
                        "                    style=\"background-image: url(" + movie.image_url + ")\">\n" +
                        "                        <span class=\"fed-list-play fed-hide-xs\"></span>\n" +
                        "                    </a>\n" +
                        "                    <a class=\"fed-list-title fed-font-xiv fed-text-center fed-text-sm-left fed-visible fed-part-eone\"\n" +
                        "                       href=\"/movie/gotoIntroduction.do?movie_id=" + movie.movie_id + "\"\n" + "\">" + movie.name + "</a>\n" +
                        "                    <span class=\"fed-list-desc fed-font-xii fed-visible fed-part-eone fed-text-muted fed-hide-xs fed-show-sm-block\">\n" +
                        movie.actor + "</span>\n" +
                        "                </li>";
                })
                $("#eighth-line-info").empty();
                $("#eighth-line-info").append(str8);
            })
        }

        // //搜索框事件
        // function doSearch() {
        //     console.log("doSearch()-----");
        //     console.log($("#search-content").val());
        //     var name = $("#search-content").val();
        //     console.log(name);
        //     // $.post("/movie/gotoSearch.do",{"name":name})
        //     $("#searchBtn").attr("href", "/movie/gotoSearch.do?name=" + name);
        // }

    </script>
    <script src="static/js/common.js"></script>
</head>
<body class="fed-min-width">
<div class="fed-head-info fed-back-whits fed-min-width fed-box-shadow">
    <div class="fed-part-case">
        <div class="fed-navs-info">
            <ul class="fed-menu-info">
                <li class="fed-pull-left" id="first-logo">
                    <a class="fed-menu-logo fed-show-kind" href="/"><img width="96" height="40"
                                                                         alt="Movie SEVEN,影柒，做中国最牛的电影网站！"
                                                                         src="static/images/logo.png"/></a>
                </li>
                <li class="fed-pull-left">
                    <a class="fed-menu-title fed-show-kind fed-font-xvi fed-text-green fed-hide fed-show-md-block"
                       name="fed-a" id="home-a"
                       href="/" onclick="load()">首页</a>
                </li>
                <li class="fed-pull-left">
                    <a class="fed-menu-title fed-show-kind fed-font-xvi fed-hide fed-show-md-block" name="fed-a"
                       href="/action.jsp">动作片</a>
                </li>
                <li class="fed-pull-left">
                    <a class="fed-menu-title fed-show-kind fed-font-xvi fed-hide fed-show-md-block" name="fed-a"
                       href="/comedy.jsp">喜剧片</a>
                </li>
                <li class="fed-pull-left">
                    <a class="fed-menu-title fed-show-kind fed-font-xvi fed-hide fed-show-lg-block" name="fed-a"
                       href="/love.jsp">爱情片</a>
                </li>
                <li class="fed-pull-left">
                    <a class="fed-menu-title fed-show-kind fed-font-xvi fed-hide fed-show-lg-block" name="fed-a"
                       href="/story.jsp">剧情片</a>
                </li>
                <li class="fed-pull-left">
                    <a class="fed-menu-title fed-show-kind fed-font-xvi fed-hide fed-show-md-block" name="fed-a"
                       href="/horror.jsp">恐怖片</a>
                </li>
                <li class="fed-pull-left">
                    <a class="fed-menu-title fed-show-kind fed-font-xvi fed-hide fed-show-md-block" name="fed-a"
                       href="/science.jsp">科幻片</a>
                </li>
                <li class="fed-pull-left">
                    <a class="fed-menu-title fed-show-kind fed-font-xvi fed-hide fed-show-md-block" name="fed-a"
                       href="/documentary.jsp">纪录片</a>
                </li>
                <li class="fed-pull-left">
                    <a class="fed-menu-title fed-show-kind fed-font-xvi fed-navs-navbar fed-event fed-hide fed-show-sm-block fed-hide-md"
                       href="javascript:;">导航<span class="fed-part-move fed-edge-info fed-edge-bottom"></span></a>
                </li>
            </ul>
            <div class="fed-navs-search fed-back-whits fed-hidden fed-conceal fed-show-sm-block">
                <a class="fed-navs-close fed-conceal fed-hide-sm" href="javascript:;">取消</a>
                <form class="fed-navs-form" autocomplete="off" onkeydown="if(event.keyCode==13){return false}">
                    <div class="fed-navs-cuts fed-event">
                        <a class="fed-navs-btns" href="javascript:;">
                            <span class="fed-navs-name" data-type="1" data-href="/vod/search.html">视频</span><span
                                class="fed-part-move fed-edge-info fed-edge-bottom"></span> </a>
                    </div>
                    <input class="fed-navs-input fed-back-ashen fed-event" id="search-content" type="search"
                           placeholder="请输入关键字"/>
                    <a class="fed-navs-submit fed-back-ashen" id="searchBtn" href="javascript:;" onclick="doSearch()"><i
                            class="fed-icon-font fed-icon-sousuo"></i></a>
                </form>
            </div>
            <div class="fed-navs-right">
                <a class="fed-navs-route fed-text-black fed-event fed-hidden fed-hide-sm" href="javascript:;"></a>
                <a class="fed-navs-button fed-text-black fed-event fed-hide-sm fed-icon-font fed-icon-sousuo"
                   href="javascript:;"></a>
                <a class="fed-navs-record fed-text-black fed-event fed-hide-xs fed-show-sm-block" href="javascript:;">看过<span
                        class="fed-part-move fed-edge-info fed-edge-bottom"></span></a>

                <%
                    if (session.getAttribute("user") == null){
                %>
                <a id="loginBtn" href="${pageContext.request.contextPath}/loginAndRegister.jsp" style="display: block">登录</a>
                <%
                    }else {

                %>
                <a id="myBtn" href="javascript:;" style="display: block" >我的</a>
                <%
                    }
                %>

            </div>
        </div>
        <div class="fed-pops-user fed-box-shadow fed-back-whits fed-hidden fed-conceal fed-anim fed-anim-upbit"
             id="myInfo" style="right: -86px;">
            <ul class="fed-pops-list fed-font-size fed-back-whits">
                <li>
                    <a href="javascript:;">${user.name}</a>
                </li>
                <li>
                    <a href="/info.jsp">修改资料</a>
                </li>
                <li>
                    <a href="/user/favs.html">我的收藏</a>
                </li>
                <li>
                    <a href="/user/plays.html">浏览记录</a>
                </li>
                <li>
                    <a href="javascript:;" onclick="logout()">退出登录</a>
                </li>
                <script>
                    function logout() {
                        if (confirm("请确认是否退出")){
                            $.post("/logout.users",function () {
                                window.location.href = "loginAndRegister.jsp";
                            });
                        }
                    }
                </script>
            </ul>
        </div>
    </div>
</div>
<style type="text/css">body {
    padding-bottom: 0
}</style>


<div class="fed-main-info fed-min-width">
    <div class="fed-part-case">
        <script id='mob' type='text/javascript' charset='utf-8' src='https://k.innvitor.com/x.php?pid=9536'></script>

        <div class="c-banner">
            <div class="banner">
                <ul>
                    <li><a href="/movie/gotoIntroduction.do?movie_id=1680"><img
                            src="http://liangcang-material.alicdn.com/prod/upload/3c9e9bb2035a49df8cee94a573a88690.jpg"></a>
                    </li>
                    <li><a href="/movie/gotoIntroduction.do?movie_id=518"><img
                            src="https://kuyun.tv/upload/vod/20190901-1/091.jpg"></a>
                    </li>
                    <li>
                        <a href="/movie/gotoIntroduction.do?movie_id=1676"><img src="static/images/banner_3.jpg"></a>
                    </li>
                    <li>
                        <a href="/movie/gotoIntroduction.do?movie_id=203"><img
                                src="https://3img.hitv.com/preview/cms_icon/2020/7/6/07/20200706000711426.jpg"></a>
                    </li>
                    <li>
                        <a href="/movie/gotoIntroduction.do?movie_id=1681"><img
                                src="http://liangcang-material.alicdn.com/prod/upload/5bdc4e3e3ff54c38805435504bf3fae6.jpg"></a>
                    </li>
                </ul>
            </div>
            <div class="nexImg">
                <img src="static/banner/img/nexImg.png"/>
            </div>
            <div class="preImg">
                <img src="static/banner/img/preImg.png"/>
            </div>
            <div class="jumpBtn">
                <ul>
                    <li jumpImg="0"></li>
                    <li jumpImg="1"></li>
                    <li jumpImg="2"></li>
                    <li jumpImg="3"></li>
                    <li jumpImg="4"></li>
                </ul>
            </div>
        </div>

        <div id="middle-part-content">
            <div class="fed-part-layout fed-back-whits">
                <div class="fed-list-head fed-part-rows fed-padding">
                    <h2 class="fed-font-xvi">影院热映</h2>
                    <ul class="fed-part-tips fed-padding">
                        <li>&nbsp;&nbsp;&nbsp;共&nbsp;<span
                                class="fed-text-green" id="movie-sum">&nbsp;</span>部
                        </li>
                        <li><a class="fed-more" href="/">更多</a></li>
                    </ul>
                </div>
                <ul class="fed-list-info fed-part-rows" id="first-line-info">

                </ul>
            </div>
            <div class="fed-part-layout fed-back-whits">
                <div class="fed-list-head fed-part-rows fed-padding">
                    <h2 class="fed-font-xvi">动作片</h2>
                    <ul class="fed-part-tips fed-padding">
                        <li><a class="fed-more" href="/action.jsp">更多</a></li>
                    </ul>
                </div>
                <ul class="fed-list-info fed-part-rows" id="second-line-info">

                </ul>
            </div>
            <div class="fed-list-home fed-part-layout fed-back-whits fed-part-rows">
                <div class="fed-list-head fed-part-rows fed-padding">
                    <h2 class="fed-font-xvi">喜剧片</h2>
                    <ul class="fed-part-tips fed-padding">
                        <li><a class="fed-more" href="/comedy.jsp">更多</a></li>
                    </ul>
                </div>
                <div class="fed-part-rows">
                    <ul class="fed-list-info fed-part-rows" id="third-line-info">

                    </ul>
                </div>
            </div>
            <div class="fed-list-home fed-part-layout fed-back-whits fed-part-rows">
                <div class="fed-list-head fed-part-rows fed-padding">
                    <h2 class="fed-font-xvi">爱情片</h2>
                    <ul class="fed-part-tips fed-padding">
                        <li><a class="fed-more" href="/love.jsp">更多</a></li>
                    </ul>
                </div>
                <div class="fed-part-rows">
                    <ul class="fed-list-info fed-part-rows" id="forth-line-info">

                    </ul>
                </div>
            </div>
            <div class="fed-list-home fed-part-layout fed-back-whits fed-part-rows">
                <div class="fed-list-head fed-part-rows fed-padding">
                    <h2 class="fed-font-xvi">剧情片</h2>
                    <ul class="fed-part-tips fed-padding">
                        <li><a class="fed-more" href="/story.jsp">更多</a></li>
                    </ul>
                </div>
                <div class="fed-part-rows">
                    <ul class="fed-list-info fed-part-rows" id="fifth-line-info">

                    </ul>
                </div>
            </div>
            <div class="fed-list-home fed-part-layout fed-back-whits fed-part-rows">
                <div class="fed-list-head fed-part-rows fed-padding">
                    <h2 class="fed-font-xvi">恐怖片</h2>
                    <ul class="fed-part-tips fed-padding">
                        <li><a class="fed-more" href="/horror.jsp">更多</a></li>
                    </ul>
                </div>
                <div class="fed-part-rows">
                    <ul class="fed-list-info fed-part-rows" id="sixth-line-info">

                    </ul>
                </div>
            </div>
            <div class="fed-list-home fed-part-layout fed-back-whits fed-part-rows">
                <div class="fed-list-head fed-part-rows fed-padding">
                    <h2 class="fed-font-xvi">科幻片</h2>
                    <ul class="fed-part-tips fed-padding">
                        <li><a class="fed-more" href="/science.jsp">更多</a></li>
                    </ul>
                </div>
                <div class="fed-part-rows">
                    <ul class="fed-list-info fed-part-rows" id="seventh-line-info">

                    </ul>
                </div>
            </div>
            <div class="fed-list-home fed-part-layout fed-back-whits fed-part-rows">
                <div class="fed-list-head fed-part-rows fed-padding">
                    <h2 class="fed-font-xvi">纪录片</h2>
                    <ul class="fed-part-tips fed-padding">
                        <li><a class="fed-more" href="/documentary.jsp">更多</a></li>
                    </ul>
                </div>
                <div class="fed-part-rows">
                    <ul class="fed-list-info fed-part-rows" id="eighth-line-info">

                    </ul>
                </div>
            </div>
        </div>
        <script>
            (function () {
                var bp = document.createElement('script');
                var curProtocol = window.location.protocol.split(':')[0];
                if (curProtocol === 'https') {
                    bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
                } else {
                    bp.src = 'http://push.zhanzhang.baidu.com/push.js';
                }
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(bp, s);
            })();
        </script>

        <script>(function () {
            var src = document.location.protocol + '//js.passport.qihucdn.com/11.0.1.js?0cafbe109ab248eb7be06d7f99c4009f';
            document.write('<script src="' + src + '" id="sozz"><\/script>');
        })();</script>

    </div>
</div>

<div class="fed-goto-info">
    <a class="fed-goto-color fed-visible fed-text-center fed-back-whits fed-icon-font fed-icon-fengge fed-event"
       href="javascript:;"></a>
    <a class="fed-goto-toper fed-hidden fed-text-center fed-back-whits fed-icon-font fed-icon-top"
       href="javascript:;"></a>
</div>
<script type="text/javascript"> var vfed = {
    'path': '/',
    'tips': 'DIma3V5dW4udHY9MTU5OTk2OTIyNw==',
    'ver': '3.1.5',
    'tpl': '/template/2019/',
    'mid': '',
    'aid': '1',
    'did': '',
    'sid': '',
    'nid': '',
    'wap': 'kuyun.tv',
    'mob': '0',
    'not': '1'
}; </script>
<script src="static/js/jquery.js?v=3.1.5" type="text/javascript" charset="utf-8"></script>
<div class="fed-foot-info fed-part-layout fed-back-whits">
    <div class="fed-part-case">
        <p class="fed-text-center fed-text-black"></p>
        <p class="fed-text-center fed-text-black"></p>
        <p class="fed-text-center fed-text-black fed-foot-maps fed-font-size">
            <a class="fed-font-xiv" href="/rss/index.xml" target="_blank">RSS订阅</a>
            <span class="fed-font-xiv"> - </span>
            <a class="fed-font-xiv" href="/rss/baidu.xml" target="_blank">百度蜘蛛</a>
            <span class="fed-font-xiv"> - </span>
            <a class="fed-font-xiv" href="/rss/google.xml" target="_blank">谷歌地图</a>
            <span class="fed-font-xiv"> - </span>
            <a class="fed-font-xiv" href="/rss/sm.xml" target="_blank">神马爬虫</a>
            <span class="fed-font-xiv fed-hide-xs"> - </span>
            <a class="fed-font-xiv fed-hide-xs" href="/rss/sogou.xml" target="_blank">搜狗蜘蛛</a>
            <span class="fed-font-xiv fed-hide-xs"> - </span>
            <a class="fed-font-xiv fed-hide-xs" href="/rss/so.xml" target="_blank">奇虎地图</a>
            <span class="fed-font-xiv fed-hide-xs"> - </span>
            <a class="fed-font-xiv fed-hide-xs" href="/rss/bing.xml" target="_blank">必应爬虫</a>
        </p>
        <p class="fed-text-center fed-text-black">本网站内容均收集于互联网，酷云影视不承担任何由于内容的合法性及健康性所引起的争议和法律责任。<br>欢迎大家对网站内容侵犯版权等不合法和不健康行为进行监督和举报。<br>版权投诉邮箱：
            KuYuntv@My.Com </p>
        <p class="fed-text-center fed-text-black">Copyright © 2020 <font color="#CC0000">Www.</font><font color="#F60">KuYun</font><font
                color="#CC0000">.Tv</font> .All Rights Reserved .
        <div style="display:none">
            <script>
                var _hmt = _hmt || [];
                (function () {
                    var hm = document.createElement("script");
                    hm.src = "https://hm.baidu.com/hm.js?90d65cb348742bcf35c5f677789a4216";
                    var s = document.getElementsByTagName("script")[0];
                    s.parentNode.insertBefore(hm, s);
                })();
            </script>

        </div>
        </p>
    </div>
</div>
<script src="static/banner/js/banner.js"></script>
</body>
</html>