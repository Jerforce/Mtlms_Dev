<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
  <meta charset="utf-8">
  <meta name="renderer" content="webkit|ie-comp|ie-stand">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <!--[if lt IE 9]>
  <script type="text/javascript" src="js/html5.js"></script>
  <script type="text/javascript" src="js/respond.min.js"></script>
  <script type="text/javascript" src="js/PIE_IE678.js"></script>
  <![endif]-->
  <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="css/style.css"/>
  <link href="assets/css/codemirror.css" rel="stylesheet">
  <link rel="stylesheet" href="assets/css/ace.min.css" />
  <link rel="stylesheet" href="Widget/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
  <!--[if IE 7]>
  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
  <![endif]-->
  <link href="Widget/icheck/icheck.css" rel="stylesheet" type="text/css" />
  <link href="Widget/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />

  <title>新增分类</title>
</head>
<body>
<div class="clearfix" id="add_picture">
  <div class="page_right_style" style="left: 0px;">
    <div class="type_title">修改分类</div>
    <div style="width: 70%; margin: auto;">
      <form action="CategoryUpdateServlet" method="post" enctype="multipart/form-data" class="form form-horizontal" id="form-article-add">
        <div class=" clearfix cl"  style="margin-top: 30px;">
          <label class="form-label col-2">分类编号：</label>
          <input type="hidden" name="categoryId" value="${category.categoryId}">${category.categoryId}
        </div>
        <div class=" clearfix cl"  style="margin-top: 30px;">
          <label class="form-label col-2">分类名称：</label>
          <input type="text" class="input-text" style="height: 35px;"  name="categoryName" value="${category.categoryName}">
        </div>
        <div class=" clearfix cl" style="margin-top: 30px;">
          <label class="form-label col-2">分类图片：</label>
          <input type="hidden" name="oldCategoryIcon" value="${category.categoryIcon}"/>
          <img src="${category.categoryIcon}" height="50"/><br/>
          <input type="file" class="input-text" style="height: 35px;" name="categoryIcon">
        </div>
        <div class=" clearfix cl"  style="margin-top: 30px;">
          <label class="form-label col-2">分类状态：</label>
          <c:if test="${category.categoryStatus == '1'}">
            <input type="radio" name="status" value="1" checked >启用  <input type="radio" name="status" value="0" >停用
          </c:if>
          <c:if test="${category.categoryStatus == '0'}">
            <input type="radio" name="status" value="1" >启用  <input type="radio" name="status" value="0" checked>停用
          </c:if>
        </div>
        <div class="clearfix cl" style="margin-top: 30px;">
          <div class="Button_operation">
            <button class="btn btn-primary radius" type="submit">提交保存</button>
            <button class="btn btn-warning radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
</div>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/typeahead-bs2.min.js"></script>
<script src="assets/layer/layer.js" type="text/javascript" ></script>
<script src="assets/laydate/laydate.js" type="text/javascript"></script>
<script type="text/javascript" src="Widget/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="Widget/icheck/jquery.icheck.min.js"></script>
<script type="text/javascript" src="Widget/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="Widget/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="Widget/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript" src="Widget/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript" src="Widget/ueditor/1.4.3/ueditor.all.min.js"> </script>
<script type="text/javascript" src="Widget/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script src="js/lrtk.js" type="text/javascript" ></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script>
  $(function() {
    $("#add_picture").fix({
      float : 'left',
      skin : 'green',
      durationTime :false,
      stylewidth:'220',
      spacingw:0,
      spacingh:260,
    });
  });
  $( document).ready(function(){
//初始化宽度、高度

    $(".widget-box").height($(window).height());
    $(".page_right_style").height($(window).height());
    $(".page_right_style").width($(window).width()-20);
    //当文档窗口发生改变时 触发
    $(window).resize(function(){

      $(".widget-box").height($(window).height());
      $(".page_right_style").height($(window).height());
      $(".page_right_style").width($(window).width()-20);
    });
  });

</script>
</body>
</html>