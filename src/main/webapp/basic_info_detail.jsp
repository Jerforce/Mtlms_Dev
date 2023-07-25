<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false"   language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html >
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="renderer" content="webkit|ie-comp|ie-stand">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="css/style.css"/>
  <link href="assets/css/codemirror.css" rel="stylesheet">
  <link rel="stylesheet" href="assets/css/ace.min.css" />
  <link rel="stylesheet" href="font/css/font-awesome.min.css" />
  <!--[if lte IE 8]>
  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
  <![endif]-->
  <script src="js/jquery-1.9.1.min.js"></script>
  <script src="assets/js/bootstrap.min.js"></script>
  <script src="assets/js/typeahead-bs2.min.js"></script>
  <script src="js/lrtk.js" type="text/javascript" ></script>
  <script src="assets/js/jquery.dataTables.min.js"></script>
  <script src="assets/js/jquery.dataTables.bootstrap.js"></script>
  <script src="assets/layer/layer.js" type="text/javascript" ></script>
  <script src="assets/laydate/laydate.js" type="text/javascript"></script>
  <script src="js/H-ui.js" type="text/javascript"></script>
  <script src="js/displayPart.js" type="text/javascript"></script>
  <title>评估选项</title>
</head>

<body>
<div class="clearfix">
  <div class="article_style" id="article_style">
    <div id="scrollsidebar" class="left_Treeview">
      <div class="show_btn" id="rightArrow"><span></span></div>
      <div class="widget-box side_content" >
        <div class="side_title"><a title="隐藏" class="close_btn"><span></span></a></div>
        <div class="side_list">
          <div class="widget-header header-color-green2">
            <h4 class="lighter smaller">评估类目列表</h4>
          </div>
          <div class="widget-body">
            <ul class="b_P_Sort_list" id="basicInfoList">
<%--              <li><i class="fa fa-newspaper-o pink "></i> <a href="#">型号</a></li>--%>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <!--文章列表-->
    <div class="Ads_list">
      <div class="border clearfix" style="width: 100%;">
       <span class="l_f">
        <a href="basic_info_detail_add.jsp"  title="添加评估选项" id="add_page" class="btn btn-warning">
          <i class="fa fa-plus"></i> 添加评估选项
        </a>
        <a href="javascript:ovid()" class="btn btn-danger"><i class="fa fa-trash"></i> 批量删除</a>
       </span>
      </div>
      <div class="article_list">
        <table class="table table-striped table-bordered table-hover" id="sample-table">
          <thead>
          <tr>
            <th width="25"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
            <th width="80px">ID</th>
            <th width="120px">选项名称</th>
            <th width="260">选项描述</th>
            <th width="150px">操作</th>
          </tr>
          </thead>
          <tbody id="infoDetailTbody">
              <%-- <tr>
                <td><label><input type="checkbox" class="ace"><span class="lbl"></span></label></td>
                <td>12</td>
                <td>2</td>
                <td>帮助中心</td>
                <td class="td-manage">
                  <a title="编辑" href="javascript:;" class="btn btn-xs btn-info" ><i class="fa fa-edit bigger-120"></i></a>
                  <a title="删除" href="javascript:;" class="btn btn-xs btn-danger" ><i class="fa fa-trash  bigger-120"></i></a>
                </td>
         </tr>--%>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
</body>
</html>
<script>
  $(function () {
    $(".displayPart").displayPart();
  });
  //面包屑返回值
  var index = parent.layer.getFrameIndex(window.name);
  parent.layer.iframeAuto(index);
  $('#add_page').on('click', function(){
    var cname = $(this).attr("title");
    var chref = $(this).attr("href");
    var cnames = parent.$('.Current_page').html();
    var herf = parent.$("#iframe").attr("src");
    parent.$('#parentIframe').html(cname);
    parent.$('#iframe').attr("src",chref).ready();;
    parent.$('#parentIframe').css("display","inline-block");
    parent.$('.Current_page').attr({"name":herf,"href":"javascript:void(0)"}).css({"color":"#4c8fbd","cursor":"pointer"});
    //parent.$('.Current_page').html("<a href='javascript:void(0)' name="+herf+" class='iframeurl'>" + cnames + "</a>");
    parent.layer.close(index);

  });

  $(function() {
    $("#article_style").fix({
      float : 'left',
      //minStatue : true,
      skin : 'green',
      durationTime :false,
      stylewidth:'220',
      spacingw:30,//设置隐藏时的距离
      spacingh:250,//设置显示时间距
      set_scrollsidebar:'.Ads_style',
      table_menu:'.Ads_list'
    });
  });

  //初始化宽度、高度
  $(".widget-box").height($(window).height());
  $(".Ads_list").width($(window).width()-220);
  //当文档窗口发生改变时 触发
  $(window).resize(function(){
    $(".widget-box").height($(window).height());
    $(".Ads_list").width($(window).width()-220);
  });

  /*文章-删除*/
  function member_del(obj,id){
    layer.confirm('确认要删除吗？',{icon:0,},function(index){
      $(obj).parents("tr").remove();
      layer.msg('已删除!',{icon:1,time:1000});
    });
  }
</script>
<script type="text/javascript">
  $(function(){
    //发送ajax请求到 BasicInfoLoadServlet 获取评估类目
    $.post("BasicInfoLoadServlet",function(res){
      if(res.code == 1000){
        //1.显示评估类目列表  res==>ResultVO   {code:1000,msg:success,data:[{},{},{}]}
        var arr = res.data;
        //通过JS，将arr中的评估类目显示到表格中
        for(var i=0; i<arr.length; i++){
           var basicInfo = arr[i];  //每遍历一次就从集合中获取一个评估类目
           var liStr = "<li>" +
                       "<i class='fa fa-newspaper-o pink'></i> " +
                       "<a href='javascript:;' onclick='loadInfoDetail(this,"+basicInfo.basicInfoId+")'>"+basicInfo.basicInfoName+"</a>" +
                       "</li>";
           $("#basicInfoList").append(liStr);
        }

        //2.当类目列表显示完毕，默认加载第一个类目下的选项[arr[0].basicInfoId表示评估类目列表中第一个类目ID]
        $.post("InfoDetailListByBasicServlet",{basicInfoId:arr[0].basicInfoId},function(res){
            //res就是返回的结果，res.data就是选项的集合
           showInfoDetail(res);
          //当显示所有的类目列表之后，将第一个类目的样式修改为绿色
          $("#basicInfoList li i:first").removeClass("pink");
          $("#basicInfoList li i:first").addClass("green");
        },"json");
      }
    },"json");
  });

  //显示评估选项
  function showInfoDetail(res){
    if(res.code == 1000){
      var detailArr =  res.data;
      $("#infoDetailTbody").html(""); //在显示评估选项之前，将tbody清空
      for(var j=0; j<detailArr.length; j++){
        var infoDetail = detailArr[j];  //infoDetail就表示一个选项,每个选项构造一行
        var trStr = "<tr>" +
                "<td><label><input type='checkbox' class='ace'><span class='lbl'></span></label></td>" +
                "<td>"+infoDetail.infoDetailId+"</td>" +
                "<td>"+infoDetail.infoDetailName+"</td>" +
                "<td>"+infoDetail.infoDetailDesc+"</td>" +
                "<td class='td-manage'>" +
                "<a title='编辑' href='javascript:;' class='btn btn-xs btn-info' ><i class='fa fa-edit bigger-120'></i></a>\n" +
                "<a title='删除' href='javascript:;' class='btn btn-xs btn-danger' ><i class='fa fa-trash  bigger-120'></i></a>\n" +
                "</td>" +
                "</tr>";
        $("#infoDetailTbody").append(trStr);
      }
    }
  }

  function loadInfoDetail(obj,id){
    // obj 就表示点击的评估类目的a标签
    // id  就表示传递过来的类目的id
    $("#basicInfoList li i").removeClass("green");
    $("#basicInfoList li i").addClass("pink");
    //将点击的a标签前面的 i 标签设置为绿色
    $(obj).parent("li").children("i").removeClass("pink");
    $(obj).parent("li").children("i").addClass("green");

    //重新载入当前点击的类目下的选项
    $.post("InfoDetailListByBasicServlet",{basicInfoId:id},function(res){
      showInfoDetail(res);
    },"json");
  }

</script>

