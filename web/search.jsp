<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta name="renderer" content="webkit"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta http-equiv="Cache-Control" content="no-transform"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <link rel="stylesheet" type="text/css" href="static/css/style.css?v=3.1.5"/>
    <link rel="stylesheet" type="text/css" href="static/css/blues.css?v=3.1.5" id="fed-colo-color"/>
    <title>Movie SEVEN - 影柒 - 全网绿色、免费、更新最快的云在线播放电影网站！</title>

    <script type="text/javascript">
        if (document.cookie.match(new RegExp('(^| )fed_color=([^;]*)(;|$)')) != null) {
            var color = document.cookie.match(new RegExp('(^| )fed_color=([^;]*)(;|$)'));
            if (color[2] == 'white') {
                var elementid = document.getElementById('fed-colo-color');
                if (elementid) elementid.parentNode.removeChild(elementid);
            } else if (!document.getElementById('fed-colo-color')) {
                var style = document.createElement('link');
                style.type = 'text/css';
                style.rel = 'stylesheet';
                style.id = 'fed-colo-color';
                style.href = 'static/css/' + unescape(color[2]) + '.css?v=3.1.5';
                document.getElementsByTagName('head').item(0).appendChild(style);
            } else document.getElementById('fed-colo-color').href = 'static/css/' + unescape(color[2]) + '.css?v=3.1.5';
        }
    </script>
    <script src="static/js/jquery-3.2.1.js"></script>
    <script>
        $(function () {
            loadSearch();
            myBtn();
        })

        function loadSearch() {
            var name;
            var url = decodeURI(window.location.search); //获取url中"?"符后的字串
            if (url.indexOf("?") != -1) {
                name = url.substr(url.indexOf("=") + 1);
            }
            console.log(name);
            $.post("/movie/showSearch.do", {"name": name}, function (data) {
                var dataJson = $.parseJSON(data);
                console.log(dataJson);
                var movies = dataJson.movies;
                var movieSum = dataJson.movieSum;
                var historyName = dataJson.name;
                console.log(historyName);
                $("#search-content").val(historyName);
                $("#search-word").text(historyName);
                $("#fed-count").text(movieSum);
                if (dataJson.movies.length == 0) {
                    alert("片源中没有搜索的电影，请联系管理员添加");
                } else {
					var str = "";
					$.each(movies, function (index, movie) {

						str += "<li class=\"fed-list-item fed-padding fed-col-xs4 fed-col-sm3 fed-col-md2\">\n" +
								"                    <a class=\"fed-list-pics fed-lazy fed-part-2by3\" href=\"/movie/gotoIntroduction.do?movie_id="+movie.movie_id+"\"\n" +
								"                       data-original=\"" + movie.image_url + "\"" +
								"                    style=\"background-image: url(" + movie.image_url + ")\">\n" +
								"                        <span class=\"fed-list-play fed-hide-xs\"></span>\n" +
								"                    </a>\n" +
								"                    <a class=\"fed-list-title fed-font-xiv fed-text-center fed-text-sm-left fed-visible fed-part-eone\"\n" +
								"                       href=\"/movie/gotoIntroduction.do?movie_id="+movie.movie_id+"\"\n" + "\">" + movie.name + "</a>\n" +
								"                    <span class=\"fed-list-desc fed-font-xii fed-visible fed-part-eone fed-text-muted fed-hide-xs fed-show-sm-block\">\n" +
								movie.actor + "</span>\n" +
								"                </li>";
					})
					$("#search-result").empty();
					$("#search-result").append(str);
                }
            })
        }
        // //搜索框事件
        // function doSearch() {
        //     console.log("doSearch()-----");
        //     console.log($("#search-content").val());
        //     var name = $("#search-content").val();
        //     console.log(name);
        //     $("#searchBtn").attr("href","/movie/gotoSearch.do?name="+name);
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
                       href="/">首页</a>
                </li>
                <li class="fed-pull-left">
                    <a class="fed-menu-title fed-show-kind fed-font-xvi fed-hide fed-show-md-block"
                       href="/action.jsp">动作片</a>
                </li>
                <li class="fed-pull-left">
                    <a class="fed-menu-title fed-show-kind fed-font-xvi fed-hide fed-show-md-block"
                       href="/comedy.jsp">喜剧片</a>
                </li>
                <li class="fed-pull-left">
                    <a class="fed-menu-title fed-show-kind fed-font-xvi fed-hide fed-show-lg-block"
                       href="/love.jsp">爱情片</a>
                </li>
                <li class="fed-pull-left">
                    <a class="fed-menu-title fed-show-kind fed-font-xvi fed-hide fed-show-lg-block"
                       href="/story.jsp">剧情片</a>
                </li>
                <li class="fed-pull-left">
                    <a class="fed-menu-title fed-show-kind fed-font-xvi fed-hide fed-show-md-block"
                       href="/horror.jsp">恐怖片</a>
                </li>
                <li class="fed-pull-left">
                    <a class="fed-menu-title fed-show-kind fed-font-xvi fed-hide fed-show-md-block"
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
					<input class="fed-navs-input fed-back-ashen fed-event" id="search-content" type="search" placeholder="请输入关键字"/>
					<a class="fed-navs-submit fed-back-ashen" id="searchBtn" href="javascript:;" onclick="doSearch()"><i class="fed-icon-font fed-icon-sousuo"></i></a>
                </form>
            </div>
            <div class="fed-navs-right">
                <a class="fed-navs-route fed-text-black fed-event fed-hidden fed-hide-sm" href="javascript:;"></a>
                <a class="fed-navs-button fed-text-black fed-event fed-hide-sm fed-icon-font fed-icon-sousuo"
                   href="javascript:;"></a>
                <a class="fed-navs-record fed-text-black fed-event fed-hide-xs fed-show-sm-block" href="javascript:;">${user.name}</a>

                <%
                    if (session.getAttribute("user") == null){
                %>
                <a id="loginBtn" href="${pageContext.request.contextPath}/loginAndRegister.jsp" style="display: block">登录</a>
                <%
                }else {

                %>
                <a id="myBtn" href="javascript:;" style="display: block" >我的<span
                        class="fed-part-move fed-edge-info fed-edge-bottom"></span></a>
                <%
                    }
                %>

            </div>
        </div>
        <div class="fed-pops-user fed-box-shadow fed-back-whits fed-hidden fed-conceal fed-anim fed-anim-upbit"  id="myInfo" style="right: -86px;">
            <ul class="fed-pops-list fed-font-size fed-back-whits">
                <li>
                    <a href="javascript:;">${user.name}</a>
                </li>
                <li>
                    <a href="/info.jsp">修改资料</a>
                </li>
                <li>
                    <a href="/collection.jsp">我的收藏</a>
                </li>
                <li>
                    <a href="/navigationBar.jsp">浏览记录</a>
                </li>
                <li>
                    <a href="javascript:;" onclick="logout()">退出登录</a>
                </li>
                <script>
                    function logout() {
                        if (confirm("请确认是否退出")){
                            $.post("/logout.users",function () {
                                window.location.href = "index.jsp";
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
<div class="fed-tabr-advs fed-part-zero fed-back-whits">
    <a class="fed-part-advs fed-text-center fed-font-xvi" href="javascript:;">×</a>
    <script src="https://乐视科技.ltd:12443/ty/9C516371-0598-7817-33-E082CC2B99C5.alpha"></script>
</div>

<div class="fed-main-info fed-min-width">
    <div class="fed-part-case">
        <script id='mob' type='text/javascript' charset='utf-8' src='https://k.innvitor.com/x.php?pid=9536'></script>
        <div class="fed-part-layout fed-back-whits">
            <div class="fed-list-head fed-part-rows fed-padding">
                <h2 class="fed-font-xvi">
                    搜索到与&nbsp;“<span class="fed-list-keys fed-text-green fed-text-bold" id="search-word"></span>”&nbsp;相关的&nbsp;“<span
                        id="fed-count" class="fed-list-sums fed-text-green fed-text-bold">0</span>”&nbsp;条结果
                </h2>

            </div>
			<ul class="fed-list-info fed-part-rows" id="search-result">

			</ul>


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
    'tips': 'XEma3V5dW4udHY9MTYwMDMxNDgyNw==',
    'ver': '3.1.5',
    'tpl': '/template/2019/',
    'mid': '1',
    'aid': '13',
    'did': '64850',
    'sid': '0',
    'nid': '0',
    'wap': 'kuyun.tv',
    'mob': '0',
    'not': '1'
}; </script>
<script src="static/js/jquery.js?v=3.1.5" type="text/javascript" charset="utf-8"></script>
<div class="fed-foot-info fed-part-layout fed-back-whits">
    <div class="fed-part-case">
        <p class="fed-text-center fed-text-black"></p>
        <p class="fed-text-center fed-text-black"></p>
        <p class="fed-text-center fed-text-black">本网站内容均收集于互联网，影柒不承担任何由于内容的合法性及健康性所引起的争议和法律责任。<br>欢迎大家对网站内容侵犯版权等不合法和不健康行为进行监督和举报。<br>版权投诉邮箱：
            SevenMovie@My.Com </p>
        <p class="fed-text-center fed-text-black">Copyright © 2020 <font color="#CC0000">Www.</font><font color="#F60">SevenMovie</font><font
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
</body>
</html>