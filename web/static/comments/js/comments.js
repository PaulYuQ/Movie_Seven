/**
 * 检查是否登录：
 * 没有登录：不能执行，评论操作、回复和删除操作
 * 登录了：可以评论、回复和删除。
 */
function checkLogin(){
    let name = $('.user_name').text();
    if (name==""||name == null){
        console.log("没有登录！！");
        /*不能执行评论*/
        $(".ipt-txt").val("你现在不能评论，请先登录！！");
        $('.ipt-txt').attr("disabled","disabled");
        $(".ipt-txt").css("background-color","#E5E9EF");
        $('.comment-submit').attr("disabled","disabled");
    }else{
        /*可以执行评论操作*/
        $('.ipt-txt').removeAttr("disable");
        $('.comment-submit').removeAttr("disable");
        $('.ipt-txt').css("background-color","");
        $('.ipt-txt').attr('placeholder',"发条友善的评论！");
        $('.reply').removeAttr("disable");
    }
}

/**
 * 全局变量，获取电影id和user_id。
 * @type {jQuery}
 */
var user_id = $('.userId').text();
var movie_id = getQueryVariable("movie_id");

/**
 * 全局样式，实现点击空白处，弹出评论和删除框框，不显示
 */
document.onclick = function () {
    $('.opera-list').css("display", "none");
    $('.comment-reply').css("display", "none");
};


/**
 * 获取全部评论的数量
 */
function getCount() {
    $.post(
        "/comment/getAllCount.do",
        {'movie_id': getQueryVariable("movie_id")},
        function (date) {
            console.log("总评论为：" + date);
            $("#result").html(date);
        }
        , "json");
}

/**
 * 对发表评论进行,添加处理
 */
function addComments(comment_id, flag) {
        if (flag.toString() == "1") {
            $('.comment-reply').css("display", "none");
            var content = $(".ipt-txte").val();
            $.post(
                "/comment/addComment.do",
                {'movie_id': movie_id, 'parent_id': comment_id, 'content': content, 'user_id': user_id},
                function (data) {
                    if (data.toString() == "true") {
                        getCount();
                        getAllComments(1);
                        console.log("添加成功！");
                        $(".ipt-txt").val("");
                    } else if (data.toString() == "false"){
                        alert("请注意文明用语！");
                        $(".ipt-txt").val("");
                        console.log("添加失败！");
                    }else {
                        alert("你还没有评论！");
                        console.log(data);
                    }
                }, "json"
            );
        } else {
            $('.comment-reply').css("display", "none");
            /*获取user_id*/
            /*var user_Id = user_id;
            var movie_Id = movie_id;*/
            /*获取content信息*/
            var content = $(".ipt-txt").val();
            if (content== 'null'||content == ''){
                alert("你还没有评论！");
            }
            $.post(
                "/comment/addComment.do",
                {'movie_id': movie_id, 'parent_id': -1, 'content': content, 'user_id': user_id},
                function (data) {
                    if (data.toString() == "true") {
                        getCount();
                        getAllComments(1);
                        $(".ipt-txt").val("");
                    } else if (data.toString() == "false"){
                        alert("请注意文明用语！");
                        $(".ipt-txt").val("");
                        console.log("添加失败！");
                    }else {
                        alert("你还没有评论！");
                        console.log(data);
                    }
                }, "json"
            );
        }
}

/**
 * 显示所有的评论
 */

function getAllComments(flag) {
    var movie_id = getQueryVariable("movie_id");
    $('.comment-list').empty();
    $.post(
        "/comment/getAllComments.do",
        {'movie_id': movie_id, 'flag': flag},
        /*返回comments集合*/
        function (data) {
            /*"comment_id":1, "content":"我的父亲是3，用户2，评论电影1，我好喜欢", "date":1599069005000, "movie_id":1, "name":"lsx",  "parent_id":3,  "user_id":2*/
            let str = "";
            $.each(data, function (index, comment) {
                getCount();
                if (comment.child != null) {
                    str += "<div class=\"list-item reply-wrap \" data-id=\"3491425413\" data-index=\"1\" scrollshow=\"true\">\n" +
                        "    <div class=\"user-face\">\n" +
                        "        <span class=\"parent-id\" style=\"display: none\">" + comment.parent_id + "</span>\n" +
                        "        <span class=\"user-nickname\">" + comment.name + " : </span>\n" +
                        "    </div>\n" +
                        "    <div class=\"con \">\n" +
                        "        <div class=\"user\"></div>\n" +
                        "<p class=\"text\">" + comment.content + "</p>\n" +
                        "        <div class=\"info\">\n" +
                        "            <span class=\"time\">" + datetimeFormat(comment.date) + "</span>\n" +
                        "            <span class=\"like \"><i></i><span></span></span>\n" +
                        "            <span class=\"hate \"></span>\n" +
                        "            <span class=\"reply btn-hover\"  onclick='launchSend(this," + comment.comment_id + ",\"" + comment.name + "\")'>回复</span>\n";
                    if (comment.user_id == user_id) {
                        str += "        <div class=\"operation\">\n" +
                            "                <div class=\"spot\" onclick='checkDel(this)'>\n" +
                            "                </div>\n" +
                            "                <div class=\"opera-list\" style=\"display: none;\" onclick='deleteComment(this," + comment.comment_id + ")'>\n" +
                            "                    <ul>\n" +
                            "                        <li class=\"del\" data-mid=\"435084660\">删除</li>\n" +
                            "                    </ul>\n" +
                            "                </div>\n" +
                            "            </div>\n";
                    } else {
                    }
                    str += "        </div>\n" +
                        "        <!--实现二级评论区域-->\n" +
                        "        <div class=\"reply-box\">\n";

                    $.each(comment.child, function (index, commentChild) {
                        str += "<div class=\"reply-item reply-wrap\" data-id=\"3491422064\" data-index=\"0\">\n" +
                            "                <div class=\"reply-con\">\n" +
                            "                    <div class=\"user\">\n" +
                            "                        <span class=\"parent-id\" style=\"display: none\">" + comment.parent_id + "</span>\n" +
                            "                        <a data-usercard-mid=\"435084660\" class=\"name \">" + commentChild.name + " : </a>\n" +
                            "                        <span class=\"text-con\">" + commentChild.content + "</span>\n" +
                            "                    </div>\n" +
                            "                </div>\n" +
                            "                <div class=\"child-info\">\n" +
                            "                    <span class=\"time\">" + datetimeFormat(commentChild.date) + "</span>\n" +
                            "                    <span class=\"like \">\n" +
                            "                        <i></i>\n" +
                            "                        <span></span>\n" +
                            "                    </span>\n" +
                            "                    <span class=\"hate \">\n" +
                            "                        <i></i>\n" +
                            "                    </span>\n" +
                            "                    <span class=\"reply btn-hover\" onclick='launchSend(this,\"" + comment.comment_id + "\",\"" + commentChild.name + "\")'>回复</span>\n";
                        if (commentChild.user_id == user_id) {
                            str += "                    <div class=\"operation btn-hover btn-hide-re\">\n" +
                                "                        <div class=\"spot\" onclick='checkDel(this)'></div>\n" +
                                "                        <div class=\"opera-list\" style=\"display: none;\" onclick='deleteComment(this," + commentChild.comment_id + ")'>\n" +
                                "                            <ul>\n" +
                                "                                <li class=\"del\" data-mid=\"435084660\">删除</li>\n" +
                                "                            </ul>\n" +
                                "                        </div>\n" +
                                "                    </div>\n";
                        } else {
                        }

                        str += "                </div>\n" +
                            "         </div>";
                    });
                    str += "<div class=\"paging-box\"></div>\n" +
                        "         </div>\n" +
                        "         </div>\n" +
                        "    </div>";
                } else {
                    str += "    <div class=\"list-item reply-wrap \" data-id=\"3491425413\" data-index=\"1\" scrollshow=\"true\">\n" +
                        "        <div class=\"user-face\">\n" +
                        "            <span class=\"parent-id\" style=\"display: none\">" + comment.parent_id + "</span>\n" +
                        "            <span class=\"user-nickname\">" + comment.name + " : </span>\n" +
                        "        </div>\n" +
                        "        <div class=\"con \">\n" +
                        "            <div class=\"user\"></div>\n" +
                        "<p class=\"text\">" + comment.content + "</p>\n" +
                        "            <div class=\"info\">\n" +
                        "                <span class=\"time\">" + datetimeFormat(comment.date) + "</span>\n" +
                        "                <span class=\"like \"><i></i><span></span></span>\n" +
                        "                <span class=\"hate \"></span>\n" +
                        "                <span class=\"reply btn-hover\" onclick='launchSend(this," + comment.comment_id + ",\"" + comment.name + "\")'>回复</span>\n" +
                        "                <div class=\"operation\">\n" +
                        "                    <div class=\"spot\" onclick='checkDel(this)'>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"opera-list\" style=\"display: none;\" onclick='deleteComment(this," + comment.comment_id + ")'>\n" +
                        "                        <ul>\n" +
                        "                            <li class=\"del\" data-mid=\"435084660\">删除</li>\n" +
                        "                        </ul>\n" +
                        "                    </div>\n" +
                        "                </div>\n" +
                        "            </div>\n" +
                        "            <div class=\"reply-box\"></div>\n" +
                        "            <div class=\"paging-box\"></div>\n" +
                        "        </div>\n" +
                        "    </div>";
                }
            });
            $('.comment-list').empty();
            $('.comment-list').prepend(str);
        }, "json");
}

/**
 * 按时间来排序
 */
function orderByTimer(flag) {
    getAllComments(flag)
}
/**
 * 按热度来排序
 */
function orderByHot(flag) {
    getAllComments(flag)

}

/**
 * 实现回复跳出弹框
 */
function launchSend(e, id, name) {
    event.stopPropagation();
    let username = $('.user_name').text();
    if( username == "undefined" || username == null || username == ""){
        alert("请登录！");
    }else {
        $('.comment-reply').remove();
        let str = "回复@" + name + " ";
        var comment_id = id;
        var flag = 1;
        var conNode = e.parentElement.parentElement;
        var commentSend = document.createElement('div');
        commentSend.className = 'comment-reply';
        commentSend.innerHTML = '<div class="comment-reply " >\n' +
            '                <div class="user-name">\n' +
            '                    <span class="user_name"></span>\n' +
            '                </div>\n' +
            '                <div class="textarea-container">\n' +
            '                    <i class="ipt-arrow"></i>\n' +
            '                    <textarea cols="80" name="msg" rows="5" placeholder="发条友善的评论" class="ipt-txte" ></textarea>\n' +
            '                    <!--hasmessagebox-->\n' +
            '                    <button type="submit" class="comment-submit" onclick="addComments(' + comment_id + ',' + flag + ')" >发表评论</button>\n' +
            '                </div>\n' +
            '                <div class="comment-emoji">\n' +
            '                    <i class="face"></i>\n' +
            '                    <span class="text">表情</span>\n' +
            '                </div>\n' +
            '            </div>';
        conNode.append(commentSend);

        $(".ipt-txte").val(str);
        $(".comment-reply").click(function (event) {
            event.stopPropagation();
        });
    }
}

/**
 * 判断是否需要删除
 * @param e
 */
function checkDel(e) {
    event.stopPropagation();
    let node = e.parentElement.lastElementChild;
    node.style.display = "table";
}

/**
 * 删除评论
 * @param e
 * @param id
 */
function deleteComment(e, id) {
    let username = $('.user_name').text();
    if( username == "undefined" || username == null || username == "" ) {
        alert("请登录！");
    } else {
        $.post(
            "/comment/delComment.do",
            {'comment_id': id},
            function (data) {
                if (data.toString() == "true") {
                    getAllComments(1);
                    console.log("删除成功！");
                    //e.style.display="none";
                } else {
                    console.log("删除失败！");
                    //this.style.display="none";
                }
            }
        )
    }
}

/**
 * 获取URL中的指定name的参数
 */
function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    return (null);
}

/*格式化：将long类型转换成 date 类型*/

function datetimeFormat(longTypeDate) {
    var datetimeType = "";
    var date = new Date();
    date.setTime(longTypeDate - 28800000);
    //yyyy-MM-dd 00:00 格式日期
    datetimeType = date.getFullYear() + "-" + getMonth(date) + "-" + getDay(date) + "&nbsp;" + getHours(date) + ":" + getMinutes(date);
    return datetimeType;
}

//返回 01-12 的月份值
function getMonth(date) {
    var month = "";
    month = date.getMonth() + 1; //getMonth()得到的月份是0-11
    if (month < 10) {
        month = "0" + month;
    }
    return month;
}

//返回01-30的日期
function getDay(date) {
    var day = "";
    day = date.getDate();
    if (day < 10) {
        day = "0" + day;
    }
    return day;
}

//返回小时
function getHours(date) {
    var hours = "";
    hours = date.getHours();
    if (hours < 10) {
        hours = "0" + hours;
    }
    return hours;

}

//返回分
function getMinutes(date) {
    var minute = "";
    minute = date.getMinutes();
    if (minute < 10) {
        minute = "0" + minute;
    }
    return minute;
}
