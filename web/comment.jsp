<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>评论页面</title>
    <%--<link  type="text/css" rel="stylesheet" href="static/comments/pagination/css/pagination.css">--%>
    <link type="text/css"  rel="stylesheet" href="static/comments/css/comments.css">
</head>
<body>

<%--获取用户的id--%>
<span class="userId" style="display: none">${user.user_id}</span>
<!--总div-->
<div class="common">
    <!--评论上面的导航-->
    <div class="comment" style="position: relative;">
        <div class="bb-comment ">
            <div class="reply-notice"></div>
            <div class="comment-header clearfix">
                <div class="tabs-order">
                    <ul class="clearfix">
                        <li class="default-sort" data-sort="0" style="display: none;">全部评论</li>
                        <li data-sort="2" class="hot-sort  on" onclick="orderByHot(0)">按热度排序</li>
                        <li data-sort="0" class="new-sort" style="display: list-item;" onclick="orderByTimer(1)">按时间排序</li>
                    </ul>
                </div>
                <div class="paging-box"></div>
            </div>


            <!--添加评论框-->
            <div class="comment-send ">
                <div class="user-name">
                    <%--从session中获取用户的姓名  ${user.name}}--%>
                    <span class="user_name">${user.name}</span>
                </div>
                <div class="textarea-container">
                    <i class="ipt-arrow"></i>
                    <textarea cols="80" name="msg" rows="5" placeholder="发条友善的评论" class="ipt-txt" ></textarea>
                    <button type="submit" class="comment-submit" onclick="addComments(0,0)">发表评论</button>
                </div>
                <div class="comment-emoji">
                    <i class="face"></i>
                    <span class="text">表情</span>
                </div>
            </div>
            <div class="b-head">
                <span id="result" class="results" ></span><!--显示数量-->
                <span class="b-head-t ">评论</span>
            </div>
            <!--动态添加评论列表-->
            <div class="comment-list ">
                <!--显示头信息 总评论+评论数目-->
            <!--第一个评论显示-->
            </div>
        </div>
        <div  id="pages"></div>
    </div>
</div>
</body>
<script type="text/javascript" src="static/comments/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="static/comments/js/comments.js"></script>
<script>
    $(function () {
        /*显示所有评论的数量*/
        getCount();
        /*显示所有评论*/
        getAllComments(1);
        /*检查是否登录*/
        checkLogin();
    });
</script>
</html>