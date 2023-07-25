<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
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
  <script src="js/jquery-1.9.1.min.js"></script>
  <script src="assets/js/bootstrap.min.js"></script>
  <script src="assets/js/typeahead-bs2.min.js"></script>
  <script src="assets/js/jquery.dataTables.min.js"></script>
  <script src="assets/js/jquery.dataTables.bootstrap.js"></script>
  <script src="assets/layer/layer.js" type="text/javascript" ></script>
  <script src="assets/laydate/laydate.js" type="text/javascript"></script>
  <title>角色管理</title>
</head>

<body>
<div class="margin clearfix">
  <div class="border clearfix">
     <span class="l_f">
      <a href="MenuListAllServlet" id="role_add" class="btn btn-warning" title="添加新角色">
        <i class="fa fa-plus"></i> 添加新角色
      </a>
       <a href="javascript:;" class="btn btn-danger" onclick="doSubmit()">
         <i class="fa fa-trash"></i> 批量删除
       </a>
       <script type="text/javascript">
         function doSubmit(){
           layer.confirm('确认要删除吗？',function(index){
             var count = 0;
             $('table tr > td:first-child input:checkbox').each(function(){
               //this 表示 each遍历的对象
               if(this.checked){
                 count++;
               }
             });
             if(count > 0 ){
               document.forms[0].submit();
             }else{
               layer.msg('请选择要删除的角色!',{
                 icon:1,
                 time:2000
               });
             }
           });
         }
       </script>
     </span>
    <span class="r_f">${tips}</span>
  </div>
  <div class="compete_list">
    <form action="RoleMutilDeleteServlet" method="post">
      <table id="sample-table-1" class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
          <th class="center"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
          <th width="150">角色编号</th>
          <th width="300">角色名称</th>
          <th class="hidden-480">角色描述</th>
          <th class="hidden-480">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${roleList}" var="role">
          <tr>
            <td class="center">
              <label>
                <input type="checkbox" class="ace" name="roleId" value="${role.roleId}">
                <span class="lbl"></span>
              </label>
            </td>
            <td>${role.roleId}</td>
            <td>${role.roleName}</td>
            <td>${role.roleDesc}</td>
            <td>
              <a title="编辑" href="RoleQueryServlet?roleId=${role.roleId}"  class="btn btn-xs btn-info" >
                <i class="fa fa-edit bigger-120"></i></a>
              <a title="删除" href="javascript:;"  onclick="role_del(this,${role.roleId})" class="btn btn-xs btn-warning" >
                <i class="fa fa-trash  bigger-120"></i></a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </form>
  </div>
</div>
</body>
</html>
<script type="text/javascript">
  $("#sample-table-1").DataTable({});
</script>

<script type="text/javascript">
  /*删除角色信息*/
  function role_del(obj,id){
    console.log(id);
    // id就表示点击删除按钮之后传递进来的 角色ID
    layer.confirm('确认要删除吗？',function(index){
      // 发送ajax请求到RoleDeleteServlet
      $.post("RoleDeleteServlet",{roleId:id},function(res){
        console.log(res);
        if(res.code == 1000){
          //如果删除成功，则从前端表格中移出行，同时提示已删除
          $(obj).parents("tr").remove();
          layer.msg('已删除!',{
            icon:1,
            time:1000
          });
        }else if(res.code == 1001){
          //如果删除失败
          layer.msg('删除失败!',{
            icon:1,
            time:1000
          });
        }
      },"json");
    });
  }
  /*修改*/
  function role_modify(id){
    window.location.href ="admin_role_modify.html?="+id;
  };
  /*字数限制*/
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
  //面包屑返回值
  var index = parent.layer.getFrameIndex(window.name);
  parent.layer.iframeAuto(index);
  $('.Order_form ,#role_add').on('click', function(){
    var cname = $(this).attr("title");
    var cnames = parent.$('.Current_page').html();
    var herf = parent.$("#iframe").attr("src");
    parent.$('#parentIframe span').html(cname);
    parent.$('#parentIframe').css("display","inline-block");
    parent.$('.Current_page').attr("name",herf).css({"color":"#4c8fbd","cursor":"pointer"});
    parent.layer.close(index);
  });

  $('table th input:checkbox').on('click' , function(){
    var that = this;
    $(this).closest('table').find('tr > td:first-child input:checkbox').each(function(){
      this.checked = that.checked;
      $(this).closest('tr').toggleClass('selected');
    });
  });

</script>