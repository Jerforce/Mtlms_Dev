<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="renderer" content="webkit|ie-comp|ie-stand">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
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
  <script src="assets/js/typeahead-bs2.min.js"></script>
  <script src="js/lrtk.js" type="text/javascript" ></script>
  <script src="assets/js/jquery.dataTables.min.js"></script>
  <script src="assets/js/jquery.dataTables.bootstrap.js"></script>
  <script src="assets/layer/layer.js" type="text/javascript" ></script>
  <title>分类管理</title>
</head>

<body>
<div class="page-content clearfix">
  <div class="sort_style">
    <div class="border clearfix">
       <span class="l_f">
        <a href="javascript:ovid()" id="sort_add" class="btn btn-warning">
          <i class="fa fa-plus"></i> 添加评估类目
        </a>
        <a href="javascript:ovid()" class="btn btn-danger"><i class="fa fa-trash"></i> 批量删除</a>
       </span>
       <span class="r_f">${tips}</span>
    </div>
    <div class="sort_list">
      <table class="table table-striped table-bordered table-hover" id="sample-table">
        <thead>
          <tr>
            <th width="25px"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
            <th width="100px">类目ID</th>
            <th width="250px">类目名称</th>
            <th width="100px">状态</th>
            <th width="250px">操作</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${basicInfoList}" var="basicInfo">
          <tr>
            <td><label><input type="checkbox" class="ace"><span class="lbl"></span></label></td>
            <td>${basicInfo.basicInfoId}</td>
            <td>${basicInfo.basicInfoName}</td>
            <td class="td-status">
              <c:if test="${basicInfo.basicInfoStatus == 1}">
                <span class="label label-success radius">已启用</span>
              </c:if>
              <c:if test="${basicInfo.basicInfoStatus == 0}">
                <span class="label label-defaunt radius">已停用</span>
              </c:if>
            </td>
            <td class="td-manage">
              <a onClick="member_stop(this,'10001')"  href="javascript:;" title="停用"  class="btn btn-xs btn-success">
                <i class="fa fa-check  bigger-120"></i>
              </a>
              <a title="编辑" onclick="member_edit('编辑','member-add.html','4','','510')" href="javascript:;"  class="btn btn-xs btn-info" >
                <i class="fa fa-edit bigger-120"></i>
              </a>
              <a title="删除" href="javascript:;"  onclick="basicInfo_delete(this,${basicInfo.basicInfoId})" class="btn btn-xs btn-warning" >
                <i class="fa fa-trash  bigger-120"></i>
              </a>
            </td>
          </tr>
        </c:forEach>


        </tbody>
      </table>
    </div>
  </div>
</div>
<!--添加类目-->
<div class="sort_style_add margin" id="sort_style_add" style="display:none">
  <div class="">
    <form action="BasicInfoAddServlet" method="post" id="form1">
      <ul>
        <li>
          <label class="label_name">类目名称</label>
          <div class="col-sm-9">
            <input name="basicInfoName" type="text" id="basicInfoName" placeholder="请输入新的类目名称"
                   class="col-xs-10 col-sm-5" style="width: 500px;">
          </div>
        </li>
      </ul>
    </form>
  </div>
</div>
</body>
</html>
<script type="text/javascript">
  $('#sort_add').on('click', function(){
    layer.open({
      type: 1,
      title: '添加评估类名',
      maxmin: true,
      shadeClose: false, //点击遮罩关闭层
      area : ['750px' , ''],
      content:$('#sort_style_add'),
      btn:['提交','取消'],
      yes:function(index,layero){
         // 检查类目名称是否输入
         if($("#basicInfoName").val() == ""){
           layer.msg('请输入类目名称!',{
             icon:5,
             time:1000
           });
         }else{
           $("#form1").submit();
         }
      }
    });
  });


  function checkLength(which) {
    var maxChars = 200; //
    if(which.value.length > maxChars){
      layer.open({
        icon:2,
        title:'提示框',
        content:'您出入的字数超多限制!',
      });
      // 超过限制的字数了就将 文本框中的内容按规定的字数 截取
      which.value = which.value.substring(0,maxChars);
      return false;
    }else{
      var curr = maxChars - which.value.length; //250 减去 当前输入的
      document.getElementById("sy").innerHTML = curr.toString();
      return true;
    }
  };
  /*广告图片-停用*/
  function member_stop(obj,id){
    layer.confirm('确认要关闭吗？',{icon:0,},function(index){
      $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="member_start(this,id)" href="javascript:;" title="显示"><i class="fa fa-close bigger-120"></i></a>');
      $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已关闭</span>');
      $(obj).remove();
      layer.msg('关闭!',{icon: 5,time:1000});
    });
  }
  /*广告图片-启用*/
  function member_start(obj,id){
    layer.confirm('确认要显示吗？',{icon:0,},function(index){
      $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="member_stop(this,id)" href="javascript:;" title="关闭"><i class="fa fa-check  bigger-120"></i></a>');
      $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">显示</span>');
      $(obj).remove();
      layer.msg('显示!',{icon: 6,time:1000});
    });
  }


//基本评估选项删除
  function basicInfo_delete(obj,id){
    layer.confirm('确认要删除当前分类吗？',function(index){
      //1.发送ajax请求到CategoryDeleteServlet，并将要删除的类别ID传递过去
      $.post("BasicInfoDeleteServlet",{cid:id},function(res){
        //2.对删除操作返回的结果进行处理
        if(res.code == 1000 ){
          //a.删除成功
          //移出表格中的当前行（JS处理前端效果）
          $(obj).parents("tr").remove();
          //删除成功提示
          layer.msg('分类已删除!',{
            icon:1,
            time:1000
          });
        }
        else if(res.code == 1001 ){
          //删除成功提示
          layer.msg('分类删除失败!',{
            icon:1,
            time:1000
          });
        }
      },"json");
    });
  }






  //面包屑返回值
  var index = parent.layer.getFrameIndex(window.name);
  parent.layer.iframeAuto(index);
  $('.Order_form ,.ads_link').on('click', function(){
    var cname = $(this).attr("title");
    var cnames = parent.$('.Current_page').html();
    var herf = parent.$("#iframe").attr("src");
    parent.$('#parentIframe span').html(cname);
    parent.$('#parentIframe').css("display","inline-block");
    parent.$('.Current_page').attr("name",herf).css({"color":"#4c8fbd","cursor":"pointer"});
    parent.layer.close(index);

  });
  function AdlistOrders(id){
    window.location.href = "Ads_list.html?="+id;
  };
</script>
<script type="text/javascript">
  jQuery(function($) {
    var oTable1 = $('#sample-table').dataTable( {
      "aaSorting": [[ 1, "desc" ]],//默认第几个排序
      "bStateSave": true,//状态保存
      "aoColumnDefs": [
        //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
        {"orderable":false,"aTargets":[0,2,4,6,7,]}// 制定列不参与排序
      ] } );
    $('table th input:checkbox').on('click' , function(){
      var that = this;
      $(this).closest('table').find('tr > td:first-child input:checkbox')
              .each(function(){
                this.checked = that.checked;
                $(this).closest('tr').toggleClass('selected');
              });

    });
    $('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
    function tooltip_placement(context, source) {
      var $source = $(source);
      var $parent = $source.closest('table')
      var off1 = $parent.offset();
      var w1 = $parent.width();

      var off2 = $source.offset();
      var w2 = $source.width();

      if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
      return 'left';
    }

  })



</script>